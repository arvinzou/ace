package com.huacainfo.ace.society.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.service.TaskCmccService;
import com.huacainfo.ace.society.constant.RegType;
import com.huacainfo.ace.society.model.PersonInfo;
import com.huacainfo.ace.society.model.SocietyOrgInfo;
import com.huacainfo.ace.society.service.RegService;
import com.huacainfo.ace.society.service.SocietyOrgInfoService;
import com.huacainfo.ace.society.vo.CustomerVo;
import com.huacainfo.ace.society.vo.SocietyOrgInfoQVo;
import com.huacainfo.ace.society.vo.SocietyOrgInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/9/19 08:57
 * @Description:
 */
@RestController
@RequestMapping("/www/reg")
public class WRegController extends SocietyBaseController {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private TaskCmccService taskCmccService;
    @Autowired
    private SocietyOrgInfoService societyOrgInfoService;

    @Autowired
    private RegService regService;

    /**
     * 发送注册验证码
     *
     * @param mobile 手机号码
     * @param length 验证码长度 可选项
     * @return
     * @throws Exception
     */
    @RequestMapping("/sendCode")
    public ResultResponse sendCode(String mobile, String length) throws Exception {

        WxUser wxUser = getCurWxUser();
        if (wxUser == null) {
            return new ResultResponse(ResultCode.FAIL, "微信鉴权失败");
        }

        //账号重复验证
        if (regService.isExitByTel(mobile)) {
            return new ResultResponse(ResultCode.FAIL, "该手机号码已被注册");
        }
        //四位随机码
        length = StringUtil.isEmpty(length) ? "4" : length;
        String randCode = CommonUtils.getIdentifyCode(Integer.valueOf(length), 0);
        // 保存进 redis
//        this.getRequest().getSession().setAttribute("j_captcha_cmcc_" + mobile, randCode);
        String redisKey = "sms_code-" + wxUser.getUnionId() + "-" + mobile;
        redisSet(redisKey, randCode, 0);

        TaskCmcc o = new TaskCmcc();
        if (CommonUtils.isBlank(mobile)) {
            return new ResultResponse(ResultCode.FAIL, "手机号不能为空");
        }
        if (!CommonUtils.isValidMobile(mobile)) {
            return new ResultResponse(ResultCode.FAIL, "手机号格式错误");
        }
        Map<String, Object> msg = new HashMap<>();
        msg.put("taskName", "验证码" + mobile);
        msg.put("msg", "本次提交验证码为" + randCode + "，请及时输入。" + "【芙蓉街道】");
        msg.put("tel", mobile + "," + mobile + ";");
        CommonBeanUtils.copyMap2Bean(o, msg);
        logger.debug(mobile + "=>j_captcha_cmcc:{}", redisGet(redisKey));

        return new ResultResponse(taskCmccService.insertTaskCmcc(o));
    }

    /**
     * 统一注册
     *
     * @param regType  注册类型 1 -- 个人/党员 ，2 - 社会/党组织
     * @param mobile   手机号码
     * @param code     手机验证码
     * @param jsonData 注册表单信息
     * @return
     */
    @RequestMapping("/register")
    public ResultResponse register(String regType, String mobile, String code,
                                   String jsonData,
                                   String unionId) throws Exception {
        WxUser wxUser = getCurWxUser();
        if (wxUser == null) {
            return new ResultResponse(ResultCode.FAIL, "微信鉴权失败");
        }
        if (!StringUtil.areNotEmpty(regType, mobile, code, jsonData)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        //验证码校验
//        String sessionCode = (String) this.getRequest().getSession().getAttribute("j_captcha_cmcc_" + mobile);
//        logger.debug("[society]RegController.register=>mobile:{},code:{},sessionCode:{}", mobile, code, sessionCode);
        String redisKey = "sms_code-" + wxUser.getUnionId() + "-" + mobile;
        String redisCode = redisGet(redisKey);

        if (!code.equals(redisCode)) {
            return new ResultResponse(ResultCode.FAIL, "验证码输入有误");
        }
        //获取接口身份
        logger.debug("RegController.register[userinfo]:{}", JsonUtil.toJson(wxUser));
        //openid不能为空
        if (StringUtil.isEmpty(unionId)) {
            return new ResultResponse(ResultCode.FAIL, "获取微信身份失败");
        }
        //个人/党员
        if (RegType.PERSON.equals(regType)) {
            PersonInfo personInfo = JsonUtil.toObject(jsonData, PersonInfo.class);
            personInfo.setMobilePhone(mobile);
            try {
                return regService.register(regType, personInfo, wxUser);
            } catch (CustomException e) {
                return new ResultResponse(ResultCode.FAIL, e.getMsg());
            }
        }//社会/党组织
        else if (RegType.ORG.equals(regType)) {
            SocietyOrgInfo orgInfo = JsonUtil.toObject(jsonData, SocietyOrgInfo.class);
            orgInfo.setContactPhone(mobile);
            try {
                return regService.register(regType, orgInfo, wxUser);

            } catch (CustomException e) {
                return new ResultResponse(ResultCode.FAIL, e.getMsg());
            }
        }

        return new ResultResponse(ResultCode.FAIL, "未知注册类型");
    }

    /**
     * 获取用户信息
     *
     * @param unionId 唯一主键
     * @return ResultResponse
     */
    @RequestMapping("/findByUserId")
    public ResultResponse findByUserId(String unionId) {
        //公众号 or 小程序 获取unionId

        WxUser wxUser = getCurWxUser();
        if (wxUser == null) {
            return new ResultResponse(ResultCode.FAIL, "微信鉴权失败");
        }

        CustomerVo vo = regService.findByUserId(wxUser.getUnionId());
        if (null == vo) {
            return new ResultResponse(ResultCode.FAIL, "用户尚未注册");
        }

        return new ResultResponse(ResultCode.SUCCESS, "信息查询成功", vo);
    }


    /**
     * 获取用户信息
     *
     * @param unionId 唯一主键
     * @return ResultResponse
     */
    @RequestMapping("/findOrgList")
    public ResultResponse orgList(String unionId) throws Exception {
        //公众号 or 小程序 获取unionId

//        WxUser wxUser = getCurWxUser();
//        if (wxUser == null) {
//            return new ResultResponse(ResultCode.FAIL, "微信鉴权失败");
//        }

        SocietyOrgInfoQVo condition = new SocietyOrgInfoQVo();
        condition.setStatus("3");
        List<SocietyOrgInfoVo> vo = societyOrgInfoService.findList(condition, 0, 100, "");


        return new ResultResponse(ResultCode.SUCCESS, "信息查询成功", vo);
    }
}

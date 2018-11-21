package com.huacainfo.ace.jxb.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.jxb.service.MemberSignLogService;
import com.huacainfo.ace.jxb.service.RegService;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.service.TaskCmccService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/www/reg/")
public class RegController extends JxbBaseController {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RegService regService;

    @Autowired
    private TaskCmccService taskCmccService;

    @Autowired
    private MemberSignLogService memberSignLogService;

    /**
     * 每日签到
     *
     * @param userId portal.users.user_id
     * @return ResultResponse
     */
    @RequestMapping("/signIn")
    public ResultResponse signIn(String userId) throws Exception {
        if (StringUtil.isEmpty(userId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return memberSignLogService.signIn(userId);
    }

    /**
     * 发送注册验证码
     *
     * @param mobile 手机号码
     * @param length 验证码长度
     * @return
     * @throws Exception
     */
    @RequestMapping("/sendCode")
    public ResultResponse sendCode(String mobile, String length) throws Exception {
        //账号重复验证
        if (regService.isExitByTel(mobile)) {
            return new ResultResponse(ResultCode.FAIL, "该手机号码已被注册");
        }
        //四位随机码
        length = StringUtil.isEmpty(length) ? "4" : length;
        String randCode = CommonUtils.getIdentifyCode(Integer.valueOf(length), 0);
        // 保存进session
        this.getRequest().getSession().setAttribute("j_captcha_cmcc_" + mobile, randCode);
        TaskCmcc o = new TaskCmcc();
        if (CommonUtils.isBlank(mobile)) {
            return new ResultResponse(ResultCode.FAIL, "手机号不能为空");
        }
        if (!CommonUtils.isValidMobile(mobile)) {
            return new ResultResponse(ResultCode.FAIL, "手机号格式错误");
        }
        Map<String, Object> msg = new HashMap<>();
        msg.put("taskName", "验证码" + mobile);
        msg.put("msg", "本次提交验证码为" + randCode + "，请及时输入。" + "【近心帮】");
        msg.put("tel", mobile + "," + mobile + ";");
        CommonBeanUtils.copyMap2Bean(o, msg);
        logger.debug(mobile + "=>j_captcha_cmcc:{}",
                this.getRequest().getSession().getAttribute("j_captcha_cmcc_" + mobile));

        return new ResultResponse(taskCmccService.insertTaskCmcc(o));
    }

    /**
     * 统一注册接口
     *
     * @param regType 身份标识 1 -- 老师 ，2 - 家长
     * @param mobile  手机号码
     * @param code    手机验证码
     * @return
     */
    @RequestMapping("/register")
    public ResultResponse register(String regType, String mobile, String code, String openId) throws Exception {
        if (!StringUtil.areNotEmpty(regType, mobile, code)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        //验证码校验
        String sessionCode = (String) this.getRequest().getSession().getAttribute("j_captcha_cmcc_" + mobile);
        logger.debug("[jxb]RegController.register=>mobile:{},code:{}", mobile, code);
        if (!code.equals(sessionCode)) {
            return new ResultResponse(ResultCode.FAIL, "验证码输入有误");
        }
        //获取接口身份
        Userinfo userinfo = getCurUserinfo();
        if (StringUtil.isNotEmpty(openId)) {
            userinfo = new Userinfo();
            userinfo.setUnionid(openId);
            userinfo.setOpenid(openId);
            userinfo.setNickname("test");
            userinfo.setSex("1");
        } else {
            openId = userinfo.getOpenid();
        }
        logger.debug("RegController.register[userinfo]:{}", JsonUtil.toJson(userinfo));
        //openid不能为空
        if (StringUtil.isEmpty(openId)) {
            return new ResultResponse(ResultCode.FAIL, "获取微信身份失败");
        }

        return regService.register(regType, mobile, userinfo);
    }

    /**
     * 个人中心数据展示
     *
     * @return
     */
    @RequestMapping("/findInfo")
    public ResultResponse findInfo(String openId) throws Exception {
        Userinfo userinfo = getCurUserinfo();
        if (StringUtil.isNotEmpty(openId)) {
            userinfo = new Userinfo();
            userinfo.setOpenid(openId);
            userinfo.setUnionid(openId);
            userinfo.setNickname("test");
            userinfo.setSex("1");
        }
        logger.debug("RegController.findInfo[userinfo]:{}", JsonUtil.toJson(userinfo));

        return regService.findInfo(userinfo);

    }

    /**
     * 账户清理工具
     */
    @RequestMapping("/cleanAccount")
    public ResultResponse cleanAccount(String userId, String secretKey) {
        if (!"huacainfo".equals(secretKey)) {
            return new ResultResponse(ResultCode.FAIL, "秘钥不匹配");
        }
        if (StringUtil.isEmpty(userId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必备参数");
        }
        return regService.cleanAccount(userId);
    }

}

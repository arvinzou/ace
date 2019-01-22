package com.huacainfo.ace.taa.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.PageParam;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.model.Department;
import com.huacainfo.ace.portal.service.DepartmentService;
import com.huacainfo.ace.portal.vo.DepartmentVo;
import com.huacainfo.ace.taa.service.RegisterService;
import com.huacainfo.ace.taa.vo.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Arvin
 * @Date: 2019/1/9 17:57
 * @Description:
 */
@RestController
@RequestMapping("/www/register")
public class WRegisterController extends TaaBaseController {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private DepartmentService departmentService;

    /**
     * 功能描述: 手机号码是否重复注册
     *
     * @param: mobile 手机号码
     * @return: ResultResponse
     * @auther: Arvin Zou
     * @date: 2019/1/3 9:34
     */
    @RequestMapping("/isExistByMobile")
    public ResultResponse isExistByMobile(String mobile) throws Exception {
        boolean b = registerService.isExistByMobile(mobile);
        if (b) {
            return new ResultResponse(ResultCode.FAIL, "该手机已被注册");
        }
        return new ResultResponse(ResultCode.SUCCESS, "success");
    }


    /**
     * 功能描述: 发送短信验证验证码
     *
     * @param: mobile 手机号码
     * @return: ResultResponse
     * @auther: Arvin Zou
     * @date: 2019/1/3 9:34
     */
    @RequestMapping("/sendSmsCode")
    public ResultResponse sendSmsCode(String mobile, String length) throws Exception {
        //参数验证
        if (CommonUtils.isBlank(mobile)) {
            return new ResultResponse(ResultCode.FAIL, "手机号不能为空");
        }
        if (!CommonUtils.isValidMobile(mobile)) {
            return new ResultResponse(ResultCode.FAIL, "手机号格式错误");
        }
        //重复注册验证
        boolean b = registerService.isExistByMobile(mobile);
        if (b) {
            return new ResultResponse(ResultCode.FAIL, "该手机已被其他人注册");
        }

        //四位随机码
        length = StringUtil.isEmpty(length) ? "4" : length;
        String randCode = CommonUtils.getIdentifyCode(Integer.valueOf(length), 0);
        // 保存进session
        redisSet("j_captcha_cmcc_" + mobile, randCode, 0);
//        getRequest().getSession().setAttribute("j_captcha_cmcc_" + mobile, randCode);
        //发送内容
        String content = "本次提交验证码为" + randCode + "，请及时输入。";
        logger.debug(mobile + "=>j_captcha_cmcc:{}", redisGet("j_captcha_cmcc_" + mobile));

        return registerService.sendSms(mobile, content);
    }

    /**
     * 短信验证
     *
     * @param mobile 手机号码
     * @param code   验证码
     * @return boolean
     */
    private boolean codeCheck(String mobile, String code) {
        //验证码校验
        String checkCode = redisGet("j_captcha_cmcc_" + mobile);

        logger.debug("[taa]WRegisterController.codeCheck=>mobile:{},code:{}", mobile, code);

        return code.equals(checkCode);
    }

    /**
     * 查询归属单位列表
     */
    @RequestMapping("/findDeptList")
    public PageResult<DepartmentVo> findDeptList(Department condition, PageParam page) throws Exception {

        condition.setSyid("taa");
        PageResult<DepartmentVo> rst = departmentService.findDepartmentList(condition,
                page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * register
     *
     * @param name   姓名
     * @param mobile 手机
     * @param copNo  警号
     * @param deptId 所属单位
     * @param code   短信验证码
     * @return
     */
    @RequestMapping("/register")
    public ResultResponse register(String uid, String name, String mobile,
                                   String copNo, String deptId, String code) throws Exception {
        if (!StringUtil.areNotEmpty(name, mobile, copNo, deptId, code)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
//        验证码校验
        if (!codeCheck(mobile, code)) {
            return new ResultResponse(ResultCode.FAIL, "验证码输入有误");
        }
        //微信鉴权信息 --小程序
        WxUser user = getCurWxUser();
        if (StringUtil.isNotEmpty(uid)) {
            user = new WxUser();
            user.setUnionId(uid);
            user.setNickName("test");
        }
        if (user == null) {
            return new ResultResponse(ResultCode.FAIL, "微信授权失败");
        }
        //注册流程
        try {
            ResultResponse rs = registerService.register(user, name, mobile, copNo, deptId);
            return rs;
        } catch (CustomException e) {
            return new ResultResponse(ResultCode.FAIL, e.getMsg());
        }
    }

    /**
     * 获取用户注册信息
     *
     * @param uid 测试用户ID，可选
     * @return ResultResponse
     */
    @RequestMapping("/findCustomerVo")
    public ResultResponse findCustomerVo(String uid) {
        //微信鉴权信息 --小程序
        WxUser user = getCurWxUser();
        if (StringUtil.isNotEmpty(uid)) {
            user = new WxUser();
            user.setUnionId(uid);
            user.setNickName("test");
        } else {
            uid = user.getUnionId();
        }
        if (user == null) {
            return new ResultResponse(ResultCode.FAIL, "微信授权失败");
        }

        CustomerVo customerVo = registerService.findCustomerVo(uid);
        if (customerVo == null) {
            return new ResultResponse(ResultCode.FAIL, "用户尚未注册");
        }
        return new ResultResponse(ResultCode.SUCCESS, "SUCCCESS", customerVo);
    }

    /**
     * 获取用户注册信息
     *
     * @param uid 测试用户ID，可选
     * @return ResultResponse
     */
    @RequestMapping("/updateMobile")
    public ResultResponse updateMobile(String mobile, String code, String uid) {
        if (!StringUtil.areNotEmpty(mobile, code)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        //验证码校验
        if (!codeCheck(mobile, code)) {
            return new ResultResponse(ResultCode.FAIL, "验证码输入有误");
        }
        //微信鉴权信息 --小程序
        WxUser user = getCurWxUser();
        if (StringUtil.isNotEmpty(uid)) {
            user = new WxUser();
            user.setUnionId(uid);
            user.setNickName("test");
        } else {
            uid = user.getUnionId();
        }
        if (user == null) {
            return new ResultResponse(ResultCode.FAIL, "微信授权失败");
        }

        return registerService.updateMobile(uid, mobile);
    }
}



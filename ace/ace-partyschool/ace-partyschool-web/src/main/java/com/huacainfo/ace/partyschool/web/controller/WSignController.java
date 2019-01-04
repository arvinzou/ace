package com.huacainfo.ace.partyschool.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.security.spring.BasicUsers;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonKeys;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.partyschool.constant.CommConstant;
import com.huacainfo.ace.partyschool.service.SignService;
import com.huacainfo.ace.partyschool.vo.StudentVo;
import com.huacainfo.ace.partyschool.vo.TeacherVo;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.TaskCmccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2019/1/3 09:19
 * @Description:
 */
@RestController
@RequestMapping("/www/sign")
public class WSignController extends BisBaseController {

    @Autowired
    private SignService signService;

    @Autowired
    private TaskCmccService taskCmccService;

    /**
     * 功能描述: 发送短信验证验证码
     *
     * @param: mobile 手机号码
     * @return: ResultResponse
     * @auther: Arvin Zou
     * @date: 2019/1/3 9:34
     */
    @RequestMapping("/sendMsgByMobile")
    public ResultResponse sendMsgByMobile(String mobile, String length) throws Exception {
        //账号重复验证
        if (signService.isExistByMobile(mobile)) {
            return new ResultResponse(ResultCode.FAIL, "该手机号码已被注册");
        }
        //四位随机码
        length = StringUtil.isEmpty(length) ? "4" : length;
        String randCode = CommonUtils.getIdentifyCode(Integer.valueOf(length), 0);
        // 保存进session
        sessionSet("j_captcha_cmcc_" + mobile, randCode);

        TaskCmcc o = new TaskCmcc();
        if (CommonUtils.isBlank(mobile)) {
            return new ResultResponse(ResultCode.FAIL, "手机号不能为空");
        }
        if (!CommonUtils.isValidMobile(mobile)) {
            return new ResultResponse(ResultCode.FAIL, "手机号格式错误");
        }

        Map<String, Object> msg = new HashMap<>();
        msg.put("taskName", "验证码" + mobile);
        msg.put("msg", "本次提交验证码为" + randCode + "，请及时输入。" + CommConstant.SMS_SIGN_STR);
        msg.put("tel", mobile + "," + mobile + ";");
        CommonBeanUtils.copyMap2Bean(o, msg);
        logger.debug(mobile + "=>j_captcha_cmcc:{}", sessionGet("j_captcha_cmcc_" + mobile));

        return new ResultResponse(taskCmccService.insertTaskCmcc(o));
    }

    private boolean codeCheck(String mobile, String code) {
        //验证码校验
        String sessionCode = String.valueOf(sessionGet("j_captcha_cmcc_" + mobile));

        logger.debug("[partyschool]SignController.codeCheck=>mobile:{},code:{}", mobile, code);

        return code.equals(sessionCode);
    }


    /**
     * 学员报名&注册
     *
     * @param data name 姓名
     *             mobile 手机号
     *             idCard 身份证
     *             political 政治面貌  normal-普通学员 party -党员
     *             workUnitName 单位
     *             postName 职务
     *             classId 班级
     * @param code 手机验证码
     * @return ResultResponse
     */
    @RequestMapping("/ssign")
    public ResultResponse ssign(StudentVo data, String code, String uid) throws Exception {
        if (!StringUtil.areNotEmpty(
                data.getIsBindWx(),
                data.getSignAcct(),
                data.getSingPwd(),
                data.getMobile(),
                data.getMobile())) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        //验证码校验
//        if (!codeCheck(data.getMobile(), code)) {
//            return new ResultResponse(ResultCode.FAIL, "验证码输入有误");
//        }
        //微信鉴权信息
        Userinfo userinfo = getCurUserinfo();
        if (StringUtil.isNotEmpty(uid)) {
            userinfo = getUserInfo(uid);
        }

        try {
            ResultResponse rs = signService.addNewStudent(data, userinfo);
            return rs;
        } catch (CustomException e) {
            return new ResultResponse(ResultCode.FAIL, e.getMsg());
        }
    }

    /**
     * 教职工注册
     *
     * @param data name 姓名
     *             mobile 手机号
     *             idCard 身份证
     * @param code 手机验证码
     * @return ResultResponse
     */
    @RequestMapping("/tsign")
    public ResultResponse tsign(TeacherVo data, String code, String uid) throws Exception {
        if (!StringUtil.areNotEmpty(
                data.getIsBindWx(),
                data.getSignAcct(),
                data.getSingPwd(),
                data.getMobile())) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        //验证码校验
//        if (!codeCheck(data.getMobile(), code)) {
//            return new ResultResponse(ResultCode.FAIL, "验证码输入有误");
//        }
        //微信鉴权信息
        Userinfo userinfo = getCurUserinfo();
        if (StringUtil.isNotEmpty(uid)) {
            userinfo = getUserInfo(uid);
        }

        data.setCategory(StringUtil.isEmpty(data.getCategory()) ? "0" : data.getCategory());//默认值
        try {
            ResultResponse rs = signService.addNewTeacher(data, userinfo);
            return rs;
        } catch (CustomException e) {
            return new ResultResponse(ResultCode.FAIL, e.getMsg());
        }
    }

    /**
     * 读取身份证信息
     *
     * @param imgIn 图片输入流
     * @return ResultResponse
     */
    @RequestMapping("/readIdCard")
    public ResultResponse readIdCard(@RequestParam("img") MultipartFile imgIn) {


        return null;
    }

    /**
     * 获取微信用户信息
     *
     * @return ResultResponse
     */
    @RequestMapping("/getWxUserinfo")
    public ResultResponse getWxUserinfo() {

        return new ResultResponse(ResultCode.SUCCESS, "获取成功", getCurUserinfo());
    }

    /**
     * 判断是否已经注册
     *
     * @param idCard 身份证号码
     * @param type   身份类型（student 学员 teacher 教职工）
     * @return ResultResponse
     */
    @RequestMapping("/isExistByIdCard")
    public ResultResponse isExistByIdCard(String idCard, String type) {
        if (!StringUtil.areNotEmpty(idCard, type)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return signService.isExistByIdCard(idCard, type);
    }

    /**
     * 账户密码登录
     *
     * @param acct 账户
     * @param pwd  密码
     * @return ResultResponse
     */
    @RequestMapping("/acctLogin")
    public ResultResponse acctLogin(String acct, String pwd) {
        if (!StringUtil.areNotEmpty(acct, pwd)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        //登录逻辑
        ResultResponse loginRs = signService.acctLogin(acct, pwd);
        if (ResultCode.FAIL == loginRs.getStatus()) {
            return loginRs;
        }
        //session登录成功
        Users syUser = (Users) loginRs.getData();
        BasicUsers o = new BasicUsers(syUser.getUserId(),
                syUser.getPassword(), syUser.getAccount(),
                syUser.getName(), syUser.getName(),
                syUser.getDepartmentId(), syUser.getCorpName(),
                syUser.getAreaCode(), syUser.getStauts().equals("1"), true,
                true, true, null, null, syUser.getParentCorpId(),
                syUser.getEmail(), syUser.getAccount(), null, null, syUser.getCurSyid(),
                null, syUser.getOpenId(), syUser.getAppOpenId());
        sessionSet(CommonKeys.SESSION_USERPROP_KEY, o);

        return loginRs;
    }

    /**
     * 获取账户信息
     *
     * @param acct 登录账号
     * @return ResultResponse
     */
    @RequestMapping("/getAcctInfo")
    public ResultResponse getAcctInfo(String acct) {
        if (StringUtil.isEmpty(acct)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return signService.getAcctInfo(acct);
    }


    /**
     * 微信授权登录
     *
     * @return ResultResponse
     */
    @RequestMapping("/wxOauthLogin")
    public ResultResponse wxOauthLogin() {


        return null;//signService.acctLogin(acct, pwd);
    }

}

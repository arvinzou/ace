package com.huacainfo.ace.taa.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.TaskCmccService;
import com.huacainfo.ace.portal.service.UsersService;
import com.huacainfo.ace.taa.dao.RegisterDao;
import com.huacainfo.ace.taa.dao.RegisterRuleDao;
import com.huacainfo.ace.taa.service.RegisterService;
import com.huacainfo.ace.taa.vo.CustomerVo;
import com.huacainfo.ace.taa.vo.RegisterRuleQVo;
import com.huacainfo.ace.taa.vo.RegisterRuleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2019/1/9 18:00
 * @Description:
 */
@Service("/registerService")
public class RegisterServiceImpl implements RegisterService {


    public static final String ACCOUNT_VALID = "1";//portal.users 账户可登陆
    public static final String ACCOUNT_INVALID = "0";//portal.users 账户不可登陆


    @Autowired
    private RegisterDao registerDao;
    @Autowired
    private TaskCmccService taskCmccService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private RegisterRuleDao registerRuleDao;

    /**
     * 校验手机号码是否已注册
     *
     * @param mobile 手机号码
     * @return t/f
     */
    @Override
    public boolean isExistByMobile(String mobile) {
        int temp = registerDao.isExistByMobile(mobile);

        return temp > 0;
    }

    /**
     * 注册portal用户
     *
     * @param regType    注册类别
     * @param uid        uid
     * @param openId     openid
     * @param name       昵称
     * @param account    账号
     * @param mobile     手机号码
     * @param sex        性别
     * @param sysId      系统id
     * @param deptId     部门ID
     * @param roleId     角色ID
     * @param acctStatus 账户是否可登陆
     * @param isSendSms  是否发送密码短信提醒
     * @return MessageResponse
     * @throws Exception
     */
    @Override
    public MessageResponse insertUsers(String regType,
                                       String uid,
                                       String openId,
                                       String name,
                                       String account,
                                       String pwd,
                                       String mobile,
                                       String sex,
                                       String sysId,
                                       String deptId,
                                       String roleId,
                                       String acctStatus,
                                       boolean isSendSms) throws Exception {
        if (CommonUtils.isBlank(name)) {
            return new MessageResponse(ResultCode.FAIL, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(mobile)) {
            return new MessageResponse(ResultCode.FAIL, "手机号不能为空！");
        }
        if (isExistByMobile(mobile)) {
            return new MessageResponse(ResultCode.FAIL, "手机号已注册过，请重新填写另一新的手机号!");
        }
//        String pwd = "123123";// CommonUtils.getIdentifyCode(6, 0);
        Users o = new Users();
        o.setUserId(uid);
        o.setAccount(account);
        o.setPassword(CommonUtils.getMd5(pwd));
        o.setMobile(mobile);
        o.setName(name);
        o.setSex(sex);
        o.setUserLevel(regType);//身份标识 1-学生 2-教职工 3-管理员
        o.setOpenId(openId);
        o.setAppOpenId(openId);
        o.setDepartmentId(deptId);//"0004"
        o.setCurSyid(sysId);//"partyschool"
        o.setStauts(acctStatus);/**状态（0停用正常1）*/
        o.setCreateTime(new java.util.Date());
        registerDao.insertUsers(o, roleId);

        //密码短信通知
        StringBuilder sb = new StringBuilder();
        sb.append(name)
                .append("你好，注册成功；账号：")
                .append(mobile).append(",")
                .append("密码:").append(pwd).append("。");
        String content = sb.toString();
        if (isSendSms) {
            sendSms(mobile, content);
        }
        //******************
        return new MessageResponse(0, "注册成功");
    }

    /**
     * 用户注册
     *
     * @param user   小程序微信用户信息
     * @param name   姓名
     * @param mobile 手机
     * @param copNo  警号
     * @param deptId 所属单位
     * @return ResultResponse
     */
    @Override
    public ResultResponse register(WxUser user, String name,
                                   String mobile, String copNo, String deptId) throws Exception {
        RegisterRuleQVo condition = new RegisterRuleQVo();
        condition.setCopNo(copNo);
        ResultResponse rr = findRegisterInfo(condition);
        if (ResultCode.FAIL == rr.getStatus()) {
            return rr;
        }
        //用户ID
        String uid = copNo;
//        UsersVo u = usersService.selectUsersByPrimaryKey(uid).getValue();
//        if (u != null) {
//            return new ResultResponse(ResultCode.FAIL, "该警号已被注册");
//        }
        //注册portal.users
        String regType = "1";
        String openId = null == user ? "" : user.getUnionId();
        String account = mobile;
        String pwd = "123123";
        String sex = "1";
        String sysId = "taa";
        String roleId = "5661d8ab-94fa-4ba5-b9e5-1f15218f9060";//select * from portal.role t where t.syid= @sysId'
        MessageResponse ms2 = insertUsers(regType, uid, openId, name, account, pwd,
                mobile, sex, sysId, deptId, roleId, ACCOUNT_VALID, false);

        return new ResultResponse(ms2);
    }

    /**
     * 发送短信内容
     *
     * @param mobile  手机号码
     * @param content 发送短信内容 ：无需自带短信签名
     * @return MessageResponse
     * @throws Exception
     */
    @Override
    public ResultResponse sendSms(String mobile, String content) throws Exception {
        TaskCmcc obj = new TaskCmcc();
        Map<String, Object> msg = new HashMap<>();
        msg.put("taskName", "账号" + mobile);
        msg.put("msg", content + "【常德市公安局交通警察支队】");
        msg.put("tel", mobile + "," + mobile + ";");
        CommonBeanUtils.copyMap2Bean(obj, msg);

        return new ResultResponse(taskCmccService.insertTaskCmcc(obj));
    }

    /**
     * 获取用户注册信息
     *
     * @param uid 用户ID
     * @return CustomerVo
     */
    @Override
    public CustomerVo findCustomerVo(String uid) {

        Map<String, Object> p = new HashMap<>();
        p.put("sysId", "taa");
        p.put("uid", uid);


        return registerDao.findCustomerVo(p);
    }

    /**
     * 变更手机号码
     *
     * @param uid    unionid
     * @param mobile 手机号码
     * @return ResultResponse
     */
    @Override
    public ResultResponse updateMobile(String uid, String mobile) {
        CustomerVo customerVo = findCustomerVo(uid);
        if (customerVo == null) {
            return new ResultResponse(ResultCode.FAIL, "未找到用户信息");
        }

        int i = registerDao.updateMobile(customerVo.getCopNo(), mobile);
        if (i > 0) {
            return new ResultResponse(ResultCode.SUCCESS, "修改成功");
        }
        return new ResultResponse(ResultCode.FAIL, "修改失败");
    }

    /**
     * 查询注册规则
     *
     * @param condition copNo-警号 必传
     * @return ResultResponse
     */
    @Override
    public ResultResponse findRegisterInfo(RegisterRuleQVo condition) {
        RegisterRuleVo ruleVo = registerRuleDao.findByCondition(condition);
        if (null == ruleVo) {
            return new ResultResponse(ResultCode.FAIL, "该警号不准许注册，请联系管理员");
        } else if (ruleVo.getRegStatus().equals("1")) {
            return new ResultResponse(ResultCode.FAIL, "该警号已经注册，请勿重复注册");
        }

        return new ResultResponse(ResultCode.SUCCESS, "SUCCESS", ruleVo);
    }


}

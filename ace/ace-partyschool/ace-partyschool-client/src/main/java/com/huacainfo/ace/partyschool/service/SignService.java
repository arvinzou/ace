package com.huacainfo.ace.partyschool.service;

import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.partyschool.vo.StudentVo;
import com.huacainfo.ace.partyschool.vo.TeacherVo;

import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2019/1/3 09:37
 * @Description:
 */
public interface SignService {

    /**
     * 解析身份证号码性别
     *
     * @param cardCode cardCode
     * @return Map<String, Object>
     */
    Map<String, Object> getCarInfo(String cardCode) throws Exception;

    /**
     * 校验手机号码是否已注册过
     *
     * @param mobile 手机号码
     * @return t/f
     */
    boolean isExistByMobile(String mobile);


    /**
     * 学员报名&注册
     *
     * @param data     name 姓名
     *                 mobile 手机号
     *                 idCard 身份证
     *                 political 政治面貌  normal-普通学员 party -党员
     *                 workUnitName 单位
     *                 postName 职务
     *                 classId 班级
     * @param userinfo 微信鉴权信息
     * @return ResultResponse
     */
    ResultResponse addNewStudent(StudentVo data, Userinfo userinfo) throws Exception;

    /**
     * 教职工注册
     *
     * @param data     name 姓名
     *                 mobile 手机号
     *                 idCard 身份证
     * @param userinfo 微信鉴权信息
     * @return ResultResponse
     */
    ResultResponse addNewTeacher(TeacherVo data, Userinfo userinfo) throws Exception;

    /**
     * 判断是否已经注册
     *
     * @param idCard 身份证号码
     * @param type   身份类型（student 学员 teacher 教职工）
     * @return ResultResponse
     */
    ResultResponse isExistByIdCard(String idCard, String type);

    /**
     * 账户密码登录
     *
     * @param acct 账户
     * @param pwd  密码
     * @return ResultResponse
     */
    ResultResponse acctLogin(String acct, String pwd);

    /**
     * 获取账户信息
     *
     * @param acct 登录账号
     * @return ResultResponse
     */
    ResultResponse getAcctInfo(String acct);

    /**
     * 微信登录
     *
     * @param uid 微信 unionid
     * @return ResultResponse
     */
    ResultResponse wxLogin(String uid);

    /***
     * 绑定微信账户
     * @param account 账户ID
     * @param unionid 微信 unionid
     * @return
     */
    ResultResponse wxBind(String account, String unionid);

    /**
     * 修改密码
     *
     * @param account 账户
     * @param newPwd  新密码
     * @return ResultResponse
     */
    ResultResponse updatePwd(String account, String newPwd);

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
     * @return MessageResponse
     * @throws Exception
     */
    MessageResponse insertUsers(String regType,
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
                                String acctStatus) throws Exception;

    /**
     * 修改账户状态
     *
     * @param userId userId
     * @param status 状态值
     * @return int
     */
    int updateUsersStatus(String userId, String status);
}

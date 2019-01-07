package com.huacainfo.ace.partyschool.service;

import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.partyschool.vo.StudentVo;
import com.huacainfo.ace.partyschool.vo.TeacherVo;

/**
 * @Auther: Arvin
 * @Date: 2019/1/3 09:37
 * @Description:
 */
public interface SignService {
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
}

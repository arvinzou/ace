package com.huacainfo.ace.policeschool.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.policeschool.constant.CommConstant;
import com.huacainfo.ace.policeschool.dao.SignDao;
import com.huacainfo.ace.policeschool.service.EnrollRosterService;
import com.huacainfo.ace.policeschool.service.SignService;
import com.huacainfo.ace.policeschool.service.StudentService;
import com.huacainfo.ace.policeschool.service.TeacherService;
import com.huacainfo.ace.policeschool.vo.AccountVo;
import com.huacainfo.ace.policeschool.vo.StudentVo;
import com.huacainfo.ace.policeschool.vo.TeacherVo;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.SystemService;
import com.huacainfo.ace.portal.service.TaskCmccService;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2019/1/3 09:59
 * @Description:
 */
@Service("signService")
public class SignServiceImpl implements SignService {

    public static final String SYS_ID = "policeschool";
    public static final String ACCOUNT_VALID = "1";//portal.users 账户可登陆
    public static final String ACCOUNT_INVALID = "0";//portal.users 账户不可登陆
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SignDao signDao;

    @Autowired
    private TaskCmccService taskCmccService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private EnrollRosterService enrollRosterService;

    @Autowired
    private SqlSessionTemplate sqlSession;

    private SqlSession getSqlSession() {
        SqlSession session = sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);

        return session;
    }

    /**
     * 校验手机号码是否已注册过
     *
     * @param mobile 手机号码
     * @return t/f
     */
    @Override
    public boolean isExistByMobile(String mobile) {

        int temp = signDao.isExistByMobile(mobile);

        return temp > 0;
    }

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
    @Override
    public ResultResponse addNewStudent(StudentVo data, Userinfo userinfo) throws Exception {
        //预先报名条件限制
        boolean isAllowed = enrollRosterService.isAllowed(data.getName());
        if (!isAllowed) {
            return new ResultResponse(ResultCode.FAIL, "您不在注册期，请联系管理员");
        }

        boolean isBindWx = "1".equals(data.getIsBindWx());
        String uid = isBindWx ? userinfo.getUnionid() : GUIDUtil.getGUID();
        data.setId(uid);

        //注册portal.users
        String regType = CommConstant.STUDENT;
        String openId = isBindWx ? userinfo.getUnionid() : "";
        String name = data.getName();
        String account = data.getSignAcct();
        String pwd = data.getSingPwd();
        String mobile = data.getMobile();
        String sex = data.getSex();//String.valueOf(getCarInfo(data.getIdCard()).get("sex"));
        String sysId = "policeschool";
        String deptId = "0004";
        String roleId = "ede24712-e13c-47d5-8cab-fd54589e3fe1";//select * from portal.role t where t.syid='policeschool'
        MessageResponse ms2 = insertUsers(regType, uid, openId, name, account, pwd,
                mobile, sex, sysId, deptId, roleId, ACCOUNT_VALID);
        if (ResultCode.FAIL == ms2.getStatus()) {
            return new ResultResponse(ms2);
        }

        //注册学员信息
        MessageResponse ms = studentService.insertStudent(data, sysUser());
        if (ResultCode.FAIL == ms.getStatus()) {
            throw new CustomException(ms.getErrorMessage());
        }

        return new ResultResponse(ResultCode.SUCCESS, "注册完成");
    }

    /**
     * 教职工注册
     *
     * @param data     name 姓名
     *                 mobile 手机号
     *                 idCard 身份证
     * @param userinfo 微信鉴权信息
     * @return ResultResponse
     */
    @Override
    public ResultResponse addNewTeacher(TeacherVo data, Userinfo userinfo) throws Exception {
        //保持2个表的ID一致
        boolean isBindWx = "1".equals(data.getIsBindWx());
        String uid = isBindWx ? userinfo.getUnionid() : GUIDUtil.getGUID();
        data.setId(uid);
        data.setStatus("0");//默认已注销

        //注册portal.users
        String regType = CommConstant.TEACHER;
        String openId = isBindWx ? userinfo.getUnionid() : "";
        String name = data.getName();
        String account = data.getSignAcct();
        String pwd = data.getSingPwd();
        String mobile = data.getMobile();
        String sex = data.getSex();//String.valueOf(getCarInfo(data.getIdCard()).get("sex"));
        String sysId = "policeschool";
        String deptId = "0004";
        String roleId = "9f8f9043-73e1-4438-bf8a-ef681431df74";//select * from portal.role t where t.syid='policeschool'
        MessageResponse ms2 = insertUsers(regType, uid, openId, name, account, pwd,
                mobile, sex, sysId, deptId, roleId, ACCOUNT_INVALID);
        if (ResultCode.FAIL == ms2.getStatus()) {
            return new ResultResponse(ms2);
        }
        //注册学员信息
        MessageResponse ms = teacherService.insertTeacher(data, sysUser());
        if (ResultCode.FAIL == ms.getStatus()) {

            throw new CustomException(ms.getErrorMessage());
        }

        return new ResultResponse(ResultCode.SUCCESS, "注册完成");
    }

    /**
     * 判断是否已经注册
     *
     * @param idCard 身份证号码
     * @param type   身份类型（student 学员 teacher 教职工）
     * @return ResultResponse
     */
    @Override
    public ResultResponse isExistByIdCard(String idCard, String type) {

        boolean isExist;
        switch (type) {
            case CommConstant.STUDENT:
                isExist = studentService.isExistByIdCard(idCard);
                if (isExist) {
                    return new ResultResponse(ResultCode.FAIL, "该身份证号码已被注册");
                }
                return new ResultResponse(ResultCode.SUCCESS, "该身份证号可以注册");

            case CommConstant.TEACHER:
                isExist = teacherService.isExistByIdCard(idCard);
                if (isExist) {
                    return new ResultResponse(ResultCode.FAIL, "该身份证号码已被注册");
                }
                return new ResultResponse(ResultCode.SUCCESS, "该身份证号可以注册");

            default:
                return new ResultResponse(ResultCode.FAIL, "未知身份类型");
        }
    }

    /**
     * 账户密码登录
     *
     * @param acct 账户
     * @param pwd  密码
     * @return ResultResponse
     */
    @Override
    public ResultResponse acctLogin(String acct, String pwd) {
        Users syUser = systemService.selectUsersByAccount(acct);
        if (null == syUser) {
            return new ResultResponse(ResultCode.FAIL, "账户不存在");
        }
        if (!CommonUtils.getMd5(pwd).equals(syUser.getPassword())) {
            return new ResultResponse(ResultCode.FAIL, "密码不正确");
        }
        if (syUser.getStauts().equals(ACCOUNT_INVALID)) {
            return new ResultResponse(ResultCode.FAIL, "账户已注销，请联系管理员");
        }


        return new ResultResponse(ResultCode.SUCCESS, "登录成功", syUser);
    }

    /**
     * 获取账户信息
     *
     * @param acct 登录账号
     * @return ResultResponse
     */
    @Override
    public ResultResponse getAcctInfo(String acct) {
        SqlSession session = getSqlSession();
        SignDao dao = session.getMapper(SignDao.class);

        try {
            //用户资料
            AccountVo accountVo = signDao.findByAcct(acct);
            if (accountVo != null) {
                return new ResultResponse(ResultCode.SUCCESS, "success", accountVo);
            }
        } catch (Exception e) {
            logger.error("{}", e);
            if (session != null) {
                session.close();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return new ResultResponse(ResultCode.FAIL, "账户信息不存在");
    }

    /**
     * 微信登录
     *
     * @param uid 微信 unionid
     * @return ResultResponse
     */
    @Override
    public ResultResponse wxLogin(String uid) {
        Users users = signDao.findByOpenId(uid, "policeschool");
        if (users == null) {
            return new ResultResponse(ResultCode.FAIL, "该微信尚未绑定对应账户");
        }
        if (users.getStauts().equals(ACCOUNT_INVALID)) {
            return new ResultResponse(ResultCode.FAIL, "账户已注销，请联系管理员");
        }

        return new ResultResponse(ResultCode.SUCCESS, "SUCCESS", users);
    }

    /***
     * 绑定微信账户
     * @param account 账户ID
     * @param unionid 微信 unionid
     * @return
     */
    @Override
    public ResultResponse wxBind(String account, String unionid) {
        Users users = systemService.selectUsersByAccount(account);
        if (users == null) {
            return new ResultResponse(ResultCode.FAIL, "账户信息不存在");
        }
        if (StringUtil.isNotEmpty(users.getOpenId())) {
            return new ResultResponse(ResultCode.FAIL, "该账户已绑定过其他微信号");
        }
        if (users.getStauts().equals(ACCOUNT_INVALID)) {
            return new ResultResponse(ResultCode.FAIL, "账户已注销，请联系管理员");
        }


        users.setOpenId(unionid);
        users.setAppOpenId(unionid);
        int i = signDao.bindUserWx(account, unionid, "policeschool");

        return new ResultResponse(ResultCode.SUCCESS, "绑定成功");
    }

    /**
     * 修改密码
     *
     * @param account 账户
     * @param mobile  手机号码
     * @param newPwd  新密码
     * @return ResultResponse
     */
    @Override
    public ResultResponse updatePwd(String account, String mobile, String newPwd) {

        Users users;
        if (StringUtil.isNotEmpty(account)) {
            users = systemService.selectUsersByAccount(account);
        } else {
            users = signDao.findUsersByMobile(mobile, SYS_ID);
        }
        if (users == null) {
            return new ResultResponse(ResultCode.FAIL, "账户信息不存在");
        }
        //MD5加密
        newPwd = CommonUtils.getMd5(newPwd);
        //数据更新
        int i = signDao.updatePwd(account, newPwd);

        return new ResultResponse(ResultCode.SUCCESS, "密码变更成功");
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
                                       String acctStatus) throws Exception {
        if (CommonUtils.isBlank(name)) {
            return new MessageResponse(ResultCode.FAIL, "名称不能为空！");
        }
        if (CommonUtils.isBlank(account)) {
            return new MessageResponse(ResultCode.FAIL, "账户不能为空！");
        }
        if (isExistByAccount(account)) {
            return new MessageResponse(ResultCode.FAIL, "账号重复!");
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
        o.setCurSyid(sysId);//"policeschool"
        o.setStauts(acctStatus);/**状态（0停用正常1）*/
        o.setCreateTime(new Date());
        signDao.insertReg(o, roleId);

        //密码短信通知
        //sendRegSmsNotice(o, name, mobile, pwd);
        //******************
        return new MessageResponse(0, "注册成功");
    }

    /**
     * 修改账户状态
     *
     * @param userId userId
     * @param status 状态值
     * @return int
     */
    @Override
    public int updateUsersStatus(String userId, String status) {
        return signDao.updateUsersStatus(userId, status);
    }

    /**
     * 更换账号
     *
     * @param userId     用户ID
     * @param newAccount 新账号
     * @return int
     */
    @Override
    public MessageResponse updateAccount(String userId, String newAccount) {

        //验证账号是否重复
        if (isExistByAccount(newAccount)) {
            return new MessageResponse(ResultCode.FAIL, "该账户已重复！");
        }

        int i = signDao.updateAccount(userId, newAccount);
        if (i != 1) {
            return new MessageResponse(ResultCode.FAIL, "账户变更失败！");
        }

        return new MessageResponse(ResultCode.SUCCESS, "账户变更成功！");
    }

    private boolean isExistByAccount(String newAccount) {
        int temp = signDao.isExistByAccount(newAccount);

        return temp > 0;
    }

    private void sendRegSmsNotice(Users o, String nickname, String mobile, String pwd) throws Exception {
        TaskCmcc obj = new TaskCmcc();
        Map<String, Object> msg = new HashMap<>();
        msg.put("taskName", "账号" + mobile);
        msg.put("msg", nickname + "你好，注册成功，账号" + mobile + "，密码" + pwd + "。" + CommConstant.SMS_SIGN_STR);
        msg.put("tel", mobile + "," + mobile + ";");
        CommonBeanUtils.copyMap2Bean(o, msg);

        this.taskCmccService.insertTaskCmcc(obj);
    }

    @Override
    public Map<String, Object> getCarInfo(String cardCode) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String year = cardCode.substring(6).substring(0, 4);// 得到年份
        String yue = cardCode.substring(10).substring(0, 2);// 得到月份
        // String day=CardCode.substring(12).substring(0,2);//得到日
        String sex;
        if (Integer.parseInt(cardCode.substring(16).substring(0, 1)) % 2 == 0) {// 判断性别
            sex = "2";//"女"
        } else {
            sex = "1";//"男"
        }
        Date date = new Date();// 得到当前的系统时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fyear = format.format(date).substring(0, 4);// 当前年份
        String fyue = format.format(date).substring(5, 7);// 月份
        // String fday=format.format(date).substring(8,10);
        int age = 0;
        if (Integer.parseInt(yue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生
            age = Integer.parseInt(fyear) - Integer.parseInt(year) + 1;
        } else {// 当前用户还没过生
            age = Integer.parseInt(fyear) - Integer.parseInt(year);
        }
        map.put("sex", sex);
        map.put("age", age);
        return map;
    }


    private UserProp sysUser() {
        UserProp u = new UserProp();
        u.setUserId("system");
        u.setName("system");
        u.setActiveSyId("policeschool");

        return u;
    }
}

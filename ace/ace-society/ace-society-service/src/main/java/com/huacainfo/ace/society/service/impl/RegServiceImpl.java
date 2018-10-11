package com.huacainfo.ace.society.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.BaseModel;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.society.constant.RegType;
import com.huacainfo.ace.society.dao.RegDao;
import com.huacainfo.ace.society.model.PersonInfo;
import com.huacainfo.ace.society.model.Reg;
import com.huacainfo.ace.society.model.SocietyOrgInfo;
import com.huacainfo.ace.society.service.PersonInfoService;
import com.huacainfo.ace.society.service.RegService;
import com.huacainfo.ace.society.service.SocietyOrgInfoService;
import com.huacainfo.ace.society.vo.CustomerVo;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/9/19 09:04
 * @Description:
 */
@Service("regService")
public class RegServiceImpl implements RegService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RegDao regDao;
    @Autowired
    private PersonInfoService personInfoService;
    @Autowired
    private SocietyOrgInfoService societyOrgInfoService;


    @Autowired
    private SqlSessionTemplate sqlSession;

    private SqlSession getSqlSession() {
        SqlSession session = sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);

        return session;
    }

    /**
     * 判断该号码是否已经注册过
     */
    @Override
    public boolean isExitByTel(String mobile) {
        int temp = this.regDao.isExitByTel(mobile);
        if (temp > 0) {
            return true;
        }

        return false;
    }


    /**
     * 统一注册
     *
     * @param regType  注册类型 1 - 个人/党员 ，2 - 社会/党组织
     * @param regInfo  注册信息
     * @param userinfo 微信识别信息
     * @return
     */
    @Override
    public ResultResponse register(String regType, BaseModel regInfo, Userinfo userinfo) throws Exception {
        String mobile;
        String nickname;
        PersonInfo personInfo = null;
        SocietyOrgInfo orgInfo = null;
        UserProp operator = getDefaultOperator();//默认操作员
        //个人/党员
        if (RegType.PERSON.equals(regType)) {
            personInfo = (PersonInfo) regInfo;
            personInfo.setId(userinfo.getUnionid());
            mobile = personInfo.getMobilePhone();
            nickname = personInfo.getRealName();
        }
        //社会/党组织
        else if (RegType.ORG.equals(regType)) {
            orgInfo = (SocietyOrgInfo) regInfo;
            orgInfo.setId(userinfo.getUnionid());
            mobile = orgInfo.getContactPhone();
            nickname = orgInfo.getOrgName();

        } else {
            return new ResultResponse(ResultCode.FAIL, "注册失败 - 未知注册类型");
        }
        //portal.users 信息
        Reg reg = new Reg();
        reg.setNickname(nickname);
        reg.setMobile(mobile);
        reg.setUnionId(userinfo.getUnionid());
        reg.setSex(userinfo.getSex());
        ResultResponse regRs = new ResultResponse(insertReg(reg, regType));
        if (regRs.getStatus() == ResultCode.FAIL) {
//            throw new CustomException(regRs.getInfo());
            return regRs;
        }
        //附带信息注册
        if (RegType.ORG.equals(regType) && null != orgInfo) {
            MessageResponse rs1 = societyOrgInfoService.insertSocietyOrgInfo(orgInfo, operator);
            if (rs1.getStatus() == ResultCode.FAIL) {
//                return new ResultResponse(rs1);
                throw new CustomException(rs1.getErrorMessage());
            }
        } else if (RegType.PERSON.equals(regType) && null != personInfo) {
            MessageResponse rs1 = personInfoService.insertPersonInfo(personInfo, operator);
            if (rs1.getStatus() == ResultCode.FAIL) {
//                return new ResultResponse(rs1);
                throw new CustomException(rs1.getErrorMessage());
            }
        }

        return regRs;
    }

    /**
     * 注册portal.users
     *
     * @param regType 身份标识 1 -- 个人/党员 ，2 - 社会/党组织
     * @return
     * @throws Exception
     */
    public MessageResponse insertReg(Reg reg, String regType) throws Exception {
        if (CommonUtils.isBlank(reg.getNickname())) {
            return new MessageResponse(1, "昵称不能为空！");
        }
        if (CommonUtils.isBlank(reg.getMobile())) {
            return new MessageResponse(1, "手机号不能为空！");
        }
        if (CommonUtils.isBlank(reg.getUnionId())) {
            return new MessageResponse(1, "微信UnionId不能为空！");
        }
        if (isExitByTel(reg.getMobile())) {
            return new MessageResponse(1, "手机号已注册过，请重新填写另一新的手机号!");
        }
        String pwd = "123123";// CommonUtils.getIdentifyCode(6, 0);
        Users o = new Users();
        o.setUserId(reg.getUnionId());
        o.setAccount(reg.getMobile());
        o.setMobile(reg.getMobile());
        o.setStauts("1");
        o.setPassword(CommonUtils.getMd5(pwd));
        o.setName(reg.getNickname());
        o.setDepartmentId("0010010");
        o.setCurSyid("society");
        o.setOpenId(reg.getUnionId());
        o.setCreateTime(DateUtil.getNowDate());
        o.setUserLevel(regType);//身份标识 1 -- 个人/党员 ，2 - 社会/党组织 3-管理员
        o.setSex(reg.getSex());
        this.regDao.insertReg(o);

        //密码短信通知 -- 暂不启用
//        sendRegSmsNotice(o, reg.getNickname(), reg.getMobile(), pwd);

        //******************
        return new MessageResponse(ResultCode.SUCCESS, "注册成功");
    }

    private void sendRegSmsNotice(Users o, String nickname, String mobile, String pwd) throws Exception {
        TaskCmcc obj = new TaskCmcc();
        Map<String, Object> msg = new HashMap<>();
        msg.put("taskName", "账号" + mobile);
        msg.put("msg", nickname + "你好，注册成功，账号" + mobile + "，密码" + pwd + "。【芙蓉街道】");
        msg.put("tel", mobile + "," + mobile + ";");
        CommonBeanUtils.copyMap2Bean(o, msg);
//        this.taskCmccService.insertTaskCmcc(obj);
    }

    private UserProp getDefaultOperator() {
        UserProp u = new UserProp();
        u.setUserId("system");
        u.setName("system");
        return u;
    }

    /**
     * 根据用户ID，查找客户资料
     *
     * @return
     */
    @Override
    public CustomerVo findByUserId(String userId) {
        SqlSession session = getSqlSession();
        RegDao dao = session.getMapper(RegDao.class);

        try {
            //用户资料
            CustomerVo customerVo = dao.findByUserId(userId);
            return customerVo;
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
        return null;
    }

}

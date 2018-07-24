package com.huacainfo.ace.jxb.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.jxb.constant.RegType;
import com.huacainfo.ace.jxb.dao.MemberRelationDao;
import com.huacainfo.ace.jxb.dao.RegDao;
import com.huacainfo.ace.jxb.model.Counselor;
import com.huacainfo.ace.jxb.model.MemberRelation;
import com.huacainfo.ace.jxb.model.Reg;
import com.huacainfo.ace.jxb.service.CounselorService;
import com.huacainfo.ace.jxb.service.RegService;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.TaskCmccService;
import com.huacainfo.ace.portal.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenxiaoke on 2018/7/12.
 */
@Service("regService")
public class RegServiceImpl implements RegService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RegDao regDao;

    @Autowired
    private TaskCmccService taskCmccService;
    @Autowired
    private CounselorService counselorService;
    @Autowired
    private MemberRelationDao memberRelationDao;
    @Autowired
    private UsersService usersService;

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
     * @param regType
     * @param mobile
     * @param userinfo
     * @return
     */
    @Override
    public ResultResponse register(String regType, String mobile, Userinfo userinfo) throws Exception {
        //查询获取上下级关系
        MemberRelation relation = memberRelationDao.findByOpenid(userinfo.getOpenid());
        String studioId = "";
        if (null != relation) {
            studioId = relation.getStudioId();
        }
        //老师注册流程
        if (RegType.TEACHER.equals(regType)) {
            ResultResponse rs1 = counselorService.register(mobile, studioId, userinfo);
            if (rs1.getStatus() == ResultCode.FAIL) {
                return rs1;
            }
        } else if (RegType.PARENT.equals(regType)) {

        } else {
            return new ResultResponse(ResultCode.FAIL, "注册失败 - 未知注册身份类型");
        }

        Reg reg = new Reg();
        reg.setNickname(userinfo.getNickname());
        reg.setMobile(mobile);
        reg.setUnionId(userinfo.getUnionid());
        reg.setSex(userinfo.getSex());
        return new ResultResponse(insertReg(reg, regType));
    }

    /**
     * 查询会员信息
     *
     * @param userinfo
     * @return
     */
    @Override
    public ResultResponse findInfo(Userinfo userinfo) throws Exception {
        Users users = usersService.selectUsersByPrimaryKey(userinfo.getUnionid()).getValue();
        if (null == users) {
            return new ResultResponse(ResultCode.FAIL, "当前账户尚未注册", "unregister");
        }
        Map<String, Object> rtnMap = new HashMap<>();
        //老师身份
        if (RegType.TEACHER.equals(users.getUserLevel())) {
            Counselor counselor = counselorService.selectCounselorByPrimaryKey(userinfo.getUnionid()).getValue();
            if (null == counselor) {
                return new ResultResponse(ResultCode.FAIL, "咨询师资料丢失");
            }
            rtnMap.put("counselor", counselor);
        } else if (RegType.PARENT.equals(users.getUserLevel())) {
            // TODO: 2018/7/24 家长身份逻辑待续
        }

        rtnMap.put("nickName", userinfo.getNickname());
        rtnMap.put("headimgurl", userinfo.getHeadimgurl());
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rtnMap);
    }

    /**
     * @param regType 身份标识 1 -- 老师 ，2 - 家长
     * @return
     * @throws Exception
     */
    @Override
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
//        int temp = this.regDao.isExitByTel(reg.getMobile());
        if (isExitByTel(reg.getMobile())) {
            return new MessageResponse(1, "手机号已注册过，请重新填写另一新的手机号!");
        }
        String pwd = CommonUtils.getIdentifyCode(6, 0);
        Users o = new Users();
        o.setUserId(reg.getUnionId());
        o.setAccount(reg.getMobile());
        o.setMobile(reg.getMobile());
        o.setStauts("1");
        o.setPassword(CommonUtils.getMd5(pwd));
        o.setName(reg.getNickname());
        o.setDepartmentId("0010007");
        o.setCurSyid("jxb");
        o.setOpenId(reg.getUnionId());
        o.setCreateTime(new java.util.Date());
        o.setUserLevel(regType);//身份标识 1 -- 老师 ，2 - 家长
        o.setSex(reg.getSex());
        this.regDao.insertReg(o);
        //密码短信通知
        TaskCmcc obj = new TaskCmcc();
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("taskName", "账号" + reg.getMobile());
        msg.put("msg", reg.getNickname() + "你好，注册成功，账号" + reg.getMobile() + "，密码" + pwd + "。【近心帮】");
        msg.put("tel", reg.getMobile() + "," + reg.getMobile());
        CommonBeanUtils.copyMap2Bean(o, msg);
        this.taskCmccService.insertTaskCmcc(obj);
        //******************
        return new MessageResponse(0, "注册成功.");
    }

}

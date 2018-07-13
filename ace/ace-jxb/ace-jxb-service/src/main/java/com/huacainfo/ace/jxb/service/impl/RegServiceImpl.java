package com.huacainfo.ace.jxb.service.impl;

import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.jxb.dao.RegDao;
import com.huacainfo.ace.jxb.model.Reg;
import com.huacainfo.ace.jxb.service.RegService;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.TaskCmccService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by chenxiaoke on 2018/7/12.
 */
public class RegServiceImpl implements RegService{

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RegDao regDao;

    @Autowired
    private TaskCmccService taskCmccService;
    @Override
    public MessageResponse insertReg(Reg reg) throws Exception {
        if (CommonUtils.isBlank(reg.getNickname())) {
            return new MessageResponse(1, "昵称不能为空！");
        }
        if (CommonUtils.isBlank(reg.getMobile())) {
            return new MessageResponse(1, "手机号不能为空！");
        }
        if (CommonUtils.isBlank(reg.getUnionId())) {
            return new MessageResponse(1, "微信UnionId不能为空！");
        }
        int temp = this.regDao.isExitByTel(reg.getMobile());
        if (temp > 0) {
            return new MessageResponse(1, "手机号已注册过，请重新填写另一新的手机号!");
        }
        String pwd=getRandCode();
        Users o=new Users();
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
        this.regDao.insertReg(o);
        TaskCmcc obj=new TaskCmcc();
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("taskName", "账号" + reg.getMobile());
        msg.put("msg", reg.getNickname()+"你好，注册成功，账号" + reg.getMobile() + "，密码"+pwd+"。【尽心帮】");
        msg.put("tel", reg.getMobile() + "," + reg.getMobile());
        CommonBeanUtils.copyMap2Bean(o,msg);
        this.taskCmccService.insertTaskCmcc(obj);
        return new MessageResponse(0,"注册成功.");
    }

    private String getRandCode() {
        Random random = new Random();
        String sRand = "";
        for (int i = 0; i < 6; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
        }
        return sRand;
    }
}

package com.huacainfo.ace.fundtown.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.TaskCmccService;
import com.huacainfo.ace.portal.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Auther: Arvin
 * @Date: 2018/7/2 09:42
 * @Description:
 */
@RestController
@RequestMapping("/www/sms")
public class WWWSmsController extends BisBaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskCmccService taskCmccService;
    @Autowired
    private UsersService usersService;

    @RequestMapping("/sendCode")
    public ResultResponse sendCode(String mobile) throws Exception {
        //账号重复验证
        Users users = usersService.selectByAccount(mobile);
        if (null != users) {
            return new ResultResponse(ResultCode.FAIL, "该手机号码已注册");
        }

        String randCode = getRandCode();
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
        msg.put("msg", "本次提交验证码为" + randCode + "，请及时输入。【柳叶湖清科小镇】");
        msg.put("tel", mobile + "," + mobile);
        CommonBeanUtils.copyMap2Bean(o, msg);
        logger.debug("j_captcha_cmcc:{}", this.getRequest().getSession().getAttribute("j_captcha_cmcc_" + mobile));

        return new ResultResponse(taskCmccService.insertTaskCmcc(o));
    }


    /**
     * 随机4位数字验证
     *
     * @return
     */
    private String getRandCode() {
        Random random = new Random();
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
        }

        return sRand;
    }
}

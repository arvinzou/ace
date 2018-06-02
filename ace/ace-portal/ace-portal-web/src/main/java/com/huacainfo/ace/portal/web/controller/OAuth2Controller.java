package com.huacainfo.ace.portal.web.controller;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonKeys;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.service.OAuth2Service;
import com.huacainfo.ace.portal.service.TaskCmccService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/www/oauth2")
/**
 * @author: 陈晓克
 * @version: 2017-12-27
 * @Description: TODO(直播)
 */
public class OAuth2Controller extends PortalBaseController {

    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OAuth2Service oAuth2Service;

    @Autowired
    private TaskCmccService taskCmccService;

    @RequestMapping(value = "/bind.do")
    @ResponseBody
    public SingleResult<Map<String,Object>> bind(String mobile,String captcha)throws Exception {
        if(CommonUtils.isBlank(mobile)){
            return new SingleResult(1,"手机号不能为空");
        }
        if(CommonUtils.isBlank(captcha)){
            return new SingleResult(1,"短信验证码不能为空");
        }
        String j_captcha_cmcc=(String) this.getRequest().getSession().getAttribute("j_captcha_cmcc");
        logger.debug("captcha:{}",captcha);
        logger.debug("j_captcha_cmcc:{}",j_captcha_cmcc);
        if(!captcha.equals(j_captcha_cmcc)){
            return new SingleResult(1,"短信验证码错误");
        }
        SingleResult<Map<String,Object>> rst= this.oAuth2Service.bind(this.getCurUserinfo().getUnionid(),mobile);
        Userinfo userinfo=(Userinfo)rst.getValue().get("userinfo");
        this.getRequest().getSession().setAttribute(CommonKeys.SESSION_USERINFO_KEY, userinfo);
        return rst;
    }

    @RequestMapping(value = "/sendCmccByMobile.do")
    @ResponseBody
    public MessageResponse sendCmccByMobile(String mobile,String signature) throws Exception {
        String sRand=this.getRandCode();
        TaskCmcc o=new TaskCmcc();
        if(CommonUtils.isBlank(mobile)){
            return new MessageResponse(1,"手机号不能为空");
        }
        if(!CommonUtils.isValidMobile(mobile)){
            return new MessageResponse(1,"手机号格式错误");
        }
        if(CommonUtils.isBlank(signature)){
            signature="华彩伟业";
        }
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("taskName", "验证码" + mobile);
        msg.put("msg", "本次提交验证码为" + sRand + "，请及时输入。【"+signature+"】");
        msg.put("tel", mobile + "," + mobile);
        CommonBeanUtils.copyMap2Bean(o,msg);
        logger.debug("j_captcha_cmcc:{}",this.getRequest().getSession().getAttribute("j_captcha_cmcc"));
        return this.taskCmccService.insertTaskCmcc(o);
    }
    private String getRandCode() {
        Random random = new Random();
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
        }
        // 保存进session
        this.getRequest().getSession().setAttribute("j_captcha_cmcc", sRand);
        return sRand;
    }
    @RequestMapping(value = "/userinfo.do")
    @ResponseBody
    public Object userinfo()throws Exception {
        return this.getRequest().getSession().getAttribute(CommonKeys.SESSION_USERINFO_KEY);
    }
}

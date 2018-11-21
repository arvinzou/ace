package com.huacainfo.ace.woc.web.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonKeys;
import com.huacainfo.ace.common.tools.PropertyUtil;
import com.huacainfo.ace.portal.service.OAuth2Service;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/www/oauth2")
/**
 * @author: 陈晓克
 * @version: 2017-12-27
 * @Description: TODO(直播)
 */
public class OAuth2Controller extends WocBaseController {

    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Value("#{config[appid]}")
    private String appid;
    @Value("#{config[secret]}")
    private String secret;
    @Value("#{config[redirect_uri]}")
    private String redirect_uri;
    @Value("#{config[scope]}")
    private String scope;
    @Value("#{config[state]}")
    private String state;

    @Autowired
    private OAuth2Service oAuth2Service;

    @Autowired
    private TaskCmccService taskCmccService;

    @RequestMapping(value = "/redirect.do")
    public String redirect(String code, String state, HttpServletResponse response) throws Exception {
        String viewName = "index";
        this.logger.info("code->{} state -> {}", code, state);
        logger.info("=========================  start get Userinfo from weixin pltfrom======================");
        SingleResult<Userinfo> rst = this.oAuth2Service.saveOrUpdateUserinfo(appid, secret, code, state);
        this.logger.info("{}", rst.getErrorMessage());
        if (rst.getState()) {
            this.logger.info("==================={}  in session ======================", rst.getValue().getNickname());
            this.getRequest().getSession().setAttribute(CommonKeys.SESSION_USERINFO_KEY, rst.getValue());
            logger.info("====================== session id [{}]", this.getRequest().getSession().getId());
        } else {
            viewName = "error";
        }
        logger.info("=========================  complete get Userinfo from weixin pltfrom rst {} ======================", rst.getState());

        this.getRequest().getRequestDispatcher("/www/view/personal/index.jsp").forward(this.getRequest(), response);
        return "success";
    }

    @RequestMapping(value = "/cfg.do")
    public ModelAndView userinfo() throws Exception {
        Object o = this.getSession(CommonKeys.SESSION_USERINFO_KEY);
        Map<String, Object> cfg = new HashMap<>();
        cfg.put("fastdfs_server", PropertyUtil.getProperty("fastdfs_server"));
        cfg.put("websocketurl", PropertyUtil.getProperty("websocketurl"));
        StringBuffer sb = new StringBuffer("");
        if (null == o) {
            logger.info("=========================  wxuser in session is empty======================");
            Map obj = new HashedMap();
            obj.put("openid", "oFvIjw8x1--0lQkUhO1Ta3L59o3c");
            obj.put("nickname", "游客");
            obj.put("headimgurl", "http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJLnWlZ5QwperRWRswicfELLia3cqTuLJapz3jX27VY19mwRianduy9cibSefAlnGRxNH341Qnic5w9aEg/0");
            sb.append("var wxuser=");
            sb.append(JSON.toJSONString(obj));
            sb.append(";");

        } else {
            sb.append("var wxuser=");
            sb.append(JSON.toJSONString(o));
            sb.append(";");
        }
        sb.append("var cfg=");
        sb.append(JSON.toJSONString(cfg));
        sb.append(";");
        ModelAndView mav = new ModelAndView("js");
        mav.addObject("js", sb.toString());
        return mav;
    }

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
    public MessageResponse sendCmccByMobile(String mobile,) throws Exception {
        String sRand=this.getRandCode();
        TaskCmcc o=new TaskCmcc();
        if(CommonUtils.isBlank(mobile)){
            return new MessageResponse(1,"手机号不能为空");
        }
        if(!CommonUtils.isValidMobile(mobile)){
            return new MessageResponse(1,"手机号格式错误");
        }
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("taskName", "验证码" + mobile);
        msg.put("msg", "本次提交验证码为" + sRand + "，请及时输入。【工商联】");
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
}

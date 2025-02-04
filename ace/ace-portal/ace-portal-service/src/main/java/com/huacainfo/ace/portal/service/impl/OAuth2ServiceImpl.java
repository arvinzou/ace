package com.huacainfo.ace.portal.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.HttpUtils;
import com.huacainfo.ace.portal.dao.UserinfoDao;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.OAuth2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


@Service("oAuth2Service")
public class OAuth2ServiceImpl implements OAuth2Service {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserinfoDao userinfoDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    public static void main(String args[]) throws Exception {
        String redirect_uri = "https://www.huacainfo.com/live/www/oauth2/redirect.do";
        String scope = "snsapi_base";
        String state = "huacai";
        String appid = "wx29ecb720b03ea466";
        String secret = "03ea9a47442c14208943043e62114fc6";
        String code = "001liuuO1jPR631FLowO1bCyuO1liuuC";
        StringBuffer url = new StringBuffer("https://api.weixin.qq.com/sns/oauth2/access_token");
        url.append("?appid=");
        url.append(appid);
        url.append("&secret=");
        url.append(secret);
        url.append("&code=");
        url.append(code);
        url.append("&grant_type=authorization_code");
        System.out.println(url.toString());

        url = new StringBuffer("https://open.weixin.qq.com/connect/oauth2/authorize");
        url.append("?appid=");
        url.append(appid);
        url.append("&redirect_uri=");
        url.append(URLEncoder.encode(redirect_uri, "utf-8"));
        url.append("&response_type=code");
        url.append("&scope=");
        url.append(scope);
        url.append("&state=");
        url.append(state);
        url.append("#wechat_redirect");

        System.out.println(url.toString());

    }

    /**
     * @throws
     * @Title:authorize
     * @Description: TODO(同步微信用户)
     * @param: @param appid
     * @param: @param redirect_uri
     * @param: @param scope
     * @param: @param state
     * @param: @throws Exception
     * @return: String
     * @author: 陈晓克
     * @version: 2017-12-29
     */
    @Override
    public String authorize(String appid, String redirect_uri, String scope, String state) throws Exception {
        StringBuffer url = new StringBuffer("https://open.weixin.qq.com/connect/oauth2/authorize");
        url.append("?appid=");
        url.append(appid);
        url.append("&redirect_uri=");
        url.append(URLEncoder.encode(redirect_uri, "utf-8"));
        url.append("&response_type=code");
        url.append("&scope=");
        url.append(scope);
        url.append("&state=");
        url.append(state);
        url.append("#wechat_redirect");
        this.logger.info("{}", url.toString());
        return url.toString();
    }

    /**
     * @throws
     * @Title:insertUserinfo
     * @Description: TODO(同步微信用户)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2017-12-29
     */
    @Override
    public SingleResult<Userinfo> saveOrUpdateUserinfo(String appid, String secret, String code, String state) throws Exception {

        this.logger.info("code-> {} state-> {}", code, state);
        StringBuffer url = new StringBuffer("https://api.weixin.qq.com/sns/oauth2/access_token");
        url.append("?appid=");
        url.append(appid);
        url.append("&secret=");
        url.append(secret);
        url.append("&code=");
        url.append(code);
        url.append("&grant_type=authorization_code");


        String text = HttpUtils.sslPost(url.toString(), null, "utf-8");
        this.logger.info("{}", url.toString());
        this.logger.info("{}", text);
        JSONObject rst = JSON.parseObject(text);
        if (CommonUtils.isNotEmpty(rst.getString("errcode"))) {
            return new SingleResult<Userinfo>(1, rst.getString("errcode") + "|" + rst.getString("errmsg"));
        }
        String access_token = rst.getString("access_token");
        String openid = rst.getString("openid");
        String expires_in = rst.getString("expires_in");
        String refresh_token = rst.getString("refresh_token");

        url = new StringBuffer("https://api.weixin.qq.com/sns/userinfo");
        url.append("?access_token=");
        url.append(access_token);
        url.append("&openid=");
        url.append(openid);
        url.append("&lang=zh_CN");
        text = HttpUtils.sslPost(url.toString(), null, "utf-8");
        this.logger.info("{}", url.toString());
        this.logger.info("{}", text);
        rst = JSON.parseObject(text);

        Userinfo o = JSON.parseObject(text, Userinfo.class);
        if (CommonUtils.isBlank(o.getUnionid())) {
            o.setUnionid(o.getOpenid());
        }
        o.setAppid(appid);
        o.setExpires_in(Long.valueOf(expires_in));
        o.setAccess_token(access_token);
        o.setRefresh_token(refresh_token);

        int t=userinfoDao.isAdmin(o.getUnionid());
        if(t>0){
            o.setRole("admin");
        }
        if (this.userinfoDao.isExit(o) > 0) {
            this.userinfoDao.updateByPrimaryKeySelective(o);
        } else {
            this.userinfoDao.insert(o);
        }
        o = this.userinfoDao.selectByPrimaryKey(o.getUnionid());

        Map<String, Object> userProp = this.userinfoDao.selectSysUserByUnionId(o.getUnionid());
        if (CommonUtils.isNotEmpty(userProp)) {
            o.setUserProp(userProp);
        }
        SingleResult<Userinfo> sr = new SingleResult<Userinfo>(0, "同步微信用户完成！");
        sr.setValue(o);
        return sr;
    }

    @Override
    public SingleResult<Map<String, Object>> bind(String unionid, String mobile) throws Exception {
        SingleResult<Map<String, Object>> rst = new SingleResult<Map<String, Object>>(0, "绑定成功.");
        Map<String, Object> o = new HashMap<String, Object>();
        o.put("status", "1");
        Userinfo user = this.userinfoDao.selectByPrimaryKey(unionid);
        user.setMobile(mobile);
        o.put("userinfo", user);
        this.userinfoDao.updateReg(user);
        if (CommonUtils.isNotEmpty(mobile)) {
            Map<String, Object> userProp = this.userinfoDao.selectSysUserByMobile(mobile);
            if (CommonUtils.isNotEmpty(userProp)) {
                user.setMobile((String) userProp.get("tel"));
                this.userinfoDao.updateBindApp(user.getUnionid(), (String) userProp.get("userId"));
                user.setUserProp(userProp);
                o.put("status", "0");
                o.put("userinfo", user);
            } else {
                rst.setErrorMessage("绑定失败，没有找到系统用户，请确认手机号是否已注册为系统用户。");
            }
        }
        rst.setValue(o);
        return rst;
    }

    @Override
    public MessageResponse unbind(String unionid) throws Exception {
        this.userinfoDao.updateUnbind(unionid);
        return new MessageResponse(0, "解除绑定成功。");
    }
}

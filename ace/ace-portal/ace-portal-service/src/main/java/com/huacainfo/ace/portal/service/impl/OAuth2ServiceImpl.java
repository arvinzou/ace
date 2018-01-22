package com.huacainfo.ace.portal.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.HttpUtils;
import com.huacainfo.ace.portal.dao.UserinfoDao;
import com.huacainfo.ace.portal.dao.WxUserDao;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.portal.service.AuthorityService;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.OAuth2Service;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;


@Service("oAuth2Service")
public class OAuth2ServiceImpl implements OAuth2Service {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserinfoDao userinfoDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


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
        if (this.userinfoDao.isExit(o) > 0) {
            this.userinfoDao.updateByPrimaryKey(o);
        } else {
            this.userinfoDao.insert(o);
        }
        o = this.userinfoDao.selectByPrimaryKey(o.getUnionid());
        SingleResult<Userinfo> sr = new SingleResult<Userinfo>(0, "同步微信用户完成！");
        sr.setValue(o);
        return sr;
    }

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
}

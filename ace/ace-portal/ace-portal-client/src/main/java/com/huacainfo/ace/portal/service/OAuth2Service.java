package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.model.Userinfo;

import java.util.Map;

public interface OAuth2Service {

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
    public abstract SingleResult<Userinfo> saveOrUpdateUserinfo(String appid, String secret, String code, String state) throws Exception;

    public abstract String authorize(String appid, String redirect_uri, String scope, String state) throws Exception;

    public abstract SingleResult<Map<String,Object>> bind(String unionid,String mobile) throws Exception;
    public abstract MessageResponse unbind(String unionid) throws Exception;


}

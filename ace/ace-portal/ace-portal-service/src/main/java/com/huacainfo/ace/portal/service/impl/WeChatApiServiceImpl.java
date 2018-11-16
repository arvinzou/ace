package com.huacainfo.ace.portal.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.api.WeChatApi;
import com.huacainfo.ace.common.plugins.wechat.constant.WeChatConstants;
import com.huacainfo.ace.common.plugins.wechat.entity.UserList;
import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.portal.model.WxCfg;
import com.huacainfo.ace.portal.service.UserinfoService;
import com.huacainfo.ace.portal.service.WeChatApiService;
import com.huacainfo.ace.portal.service.WxCfgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Arvin
 * @Date: 2018/11/12 14:15
 * @Description:
 */
@Service("weChatApiService")
public class WeChatApiServiceImpl implements WeChatApiService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserinfoService userinfoService;

    @Autowired
    private WxCfgService wxCfgService;


    /**
     * 同步 公众号用户列表
     *
     * @param sysId 系统id wx_cfg.sysId
     * @return ResultResponse
     */
    @Override
    public ResultResponse synUserList(String sysId) throws Exception {
        //获取微信配置相关信息
        WxCfg wxCfg = wxCfgService.findBySysId(sysId);
        if (wxCfg == null || StringUtil.isEmpty(wxCfg.getAccessToken())) {
            return new ResultResponse(ResultCode.FAIL, "获取微信配置信息异常");
        }
        String accessToken = wxCfg.getAccessToken();
        String appid = wxCfg.getAppId();
        //用户列表容器
        List<String> all = getAllUserList(accessToken);
        if (null == all) {
            return new ResultResponse(ResultCode.FAIL, "用户列表拉取失败");
        }
        //遍历用户列表
        Userinfo u;
        String url;
        String result;
        for (String openid : all) {
            //1、检查用户是否已入库
            u = userinfoService.findByOpenId(openid, appid);
            if (u == null) {
                //2、微信接口获取用户信息
                try {
                    url = WeChatConstants.TAKE_USER_INFO_URL
                            .replace("ACCESS_TOKEN", accessToken)
                            .replace("OPENID", openid);
                    result = HttpKit.get(url);
                    logger.debug("weChatApiService.synUserList.result={}", result);
                    u = JsonUtil.toObject(result, Userinfo.class);
                    u.setAppid(appid);
                    //3、入库微信用户信息
                    userinfoService.insertUserinfo(u, getDefaultUserProp());
                } catch (Exception e) {
                /*获取用户信息失败*/
                    logger.error("拉取用户信息失败：{}", e);
                    continue;
                }
            }
        }

        return new ResultResponse(ResultCode.SUCCESS, "用户列表拉取成功");
    }

    private UserProp getDefaultUserProp() {
        UserProp up = new UserProp();
        up.setName("system");
        up.setUserId("system");
        return up;
    }

    /**
     * 拉取用户列表
     *
     * @param accessToken token
     * @return List<String>
     */
    private List<String> getAllUserList(String accessToken) {
        List<String> all = new ArrayList<>();
        //第一次post
        String nextOpenid = "";
        UserList list = WeChatApi.getUserList(accessToken, nextOpenid);
        if (list != null) {
            all.addAll(list.getData().getOpenid());
            int count = list.getCount();
            //循环post获取，取出所有关注用户列表
            while (list.getTotal() > UserList.MAX_GET_COUNT
                    && count >= UserList.MAX_GET_COUNT) {
                nextOpenid = list.getNext_openid();
                list = WeChatApi.getUserList(accessToken, nextOpenid);
                count = list.getCount();

                all.addAll(list.getData().getOpenid());
            }

            return all;
        }

        return null;
    }
}

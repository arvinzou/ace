package com.huacainfo.ace.society.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.api.WeChatCustomerMsgApi;
import com.huacainfo.ace.common.plugins.wechat.util.ApiResult;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.portal.model.WxCfg;
import com.huacainfo.ace.portal.service.UserinfoService;
import com.huacainfo.ace.portal.service.WxCfgService;
import com.huacainfo.ace.portal.vo.UserinfoVo;
import com.huacainfo.ace.society.dao.RegDao;
import com.huacainfo.ace.society.service.AuditNoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Arvin
 * @Date: 2018/11/23 10:09
 * @Description:
 */
@Service("auditNoticeService")
public class AuditNoticeServiceImpl implements AuditNoticeService {
    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private RegDao regDao;
    @Autowired
    private WxCfgService wxCfgService;
    @Autowired
    private UserinfoService userinfoService;

    /**
     * 送审消息通知 - 发送给管理员
     *
     * @param content 内容
     * @return ResultResponse
     */
    @Override
    public ResultResponse sendToAdmin(String content) {
        //管理员用户信息
        Userinfo u = regDao.findAdministrator();
        String openId = u == null ? "" : u.getOpenid();// "oBj7Dvo5XzAYo6dlKlkj_4OQXXgM";
        if (u == null || StringUtil.isEmpty(openId)) {
            logger.debug("[society]未配置管理员信息");
            return new ResultResponse(ResultCode.FAIL, "未配置管理员信息");
        }
        //公众号配置信息
        WxCfg wxCfg = wxCfgService.findBySysId("society");
        String accessToken = null == wxCfg ? "" : wxCfg.getAccessToken();
        if (wxCfg == null || StringUtil.isEmpty(accessToken)) {
            logger.debug("[society]公众号配置信息获取失败");
            return new ResultResponse(ResultCode.FAIL, "公众号配置信息获取失败");
        }
        //send
        ApiResult a = WeChatCustomerMsgApi.sendText(accessToken, openId, content);
        logger.debug("[society]发送管理员消息结果：{}, 发送内容：{}", a.getJson(), content);

        return new ResultResponse(ResultCode.SUCCESS, "发送成功");
    }

    /**
     * 审核结果通知 - 发送给提交者
     *
     * @param userId  通知用户ID
     * @param content 内容
     */
    @Override
    public ResultResponse sendToCustomer(String userId, String content) throws Exception {
        //公众号配置信息
        WxCfg wxCfg = wxCfgService.findBySysId("society");
        String accessToken = null == wxCfg ? "" : wxCfg.getAccessToken();
        if (wxCfg == null || StringUtil.isEmpty(accessToken)) {
            logger.debug("[society]公众号配置信息获取失败");
            return new ResultResponse(ResultCode.FAIL, "公众号配置信息获取失败");
        }
        //管理员用户信息
        UserinfoVo u = userinfoService.selectUserinfoByKey(userId);
        String openId = u == null ? "" : u.getOpenid();// "oBj7Dvo5XzAYo6dlKlkj_4OQXXgM";
        if (u == null || StringUtil.isEmpty(openId)) {
            logger.debug("[society]查询用户信息为空：userId = {}", userId);
            return new ResultResponse(ResultCode.FAIL, "查询用户信息为空");
        }
        //send
        ApiResult a = WeChatCustomerMsgApi.sendText(accessToken, openId, content);
        logger.debug("[society]发送管理员消息结果：{}, 发送内容：{}", a.getJson(), content);

        return new ResultResponse(ResultCode.SUCCESS, "发送成功");
    }


}

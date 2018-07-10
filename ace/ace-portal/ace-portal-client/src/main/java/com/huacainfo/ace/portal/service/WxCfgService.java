package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.WxCfg;
import com.huacainfo.ace.portal.model.WxFormid;
import com.huacainfo.ace.portal.vo.WxCfgQVo;
import com.huacainfo.ace.portal.vo.WxCfgVo;

import java.util.List;
import java.util.Map;

public interface WxCfgService {

    public abstract PageResult<WxCfgVo> findWxCfgList(WxCfgQVo condition, int start, int limit, String orderBy)
            throws Exception;

    public abstract MessageResponse insertWxCfg(WxCfg obj, UserProp userProp) throws Exception;

    public abstract MessageResponse updateWxCfg(WxCfg obj, UserProp userProp) throws Exception;

    public abstract SingleResult<WxCfgVo> selectWxCfgByPrimaryKey(String id) throws Exception;

    public abstract MessageResponse deleteWxCfgByWxCfgId(String id, UserProp userProp) throws Exception;

    public abstract void updateAccessTokenTicket(String appid, String accessToken, String ticket, int expiresIn);

    public abstract List<Map<String, Object>> selectAppList();

    public abstract MessageResponse insertFormIds(List<WxFormid> list);

    public abstract SingleResult<Map<String, String>> selectAccessTokenAndTicketByDeptId(String deptId) throws Exception;


    public abstract SingleResult<Map<String, Object>> getSignature(String url, String appId, String accessToken, String jsapi_ticket) throws Exception;


    public abstract SingleResult<Map<String, String>> getRecordFile(String deptId, String serverId) throws Exception;

    /**
     * 根据公众号原始ID，查询公众号配置信息
     *
     * @param originalId
     * @return
     */
    WxCfg findByOriginalId(String originalId);

    /**
     * 功能描述: 根据关键字，查询问题答案
     * <p> 目前仅市工商联使用，查询是工商联数据库数据</p>
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/16 11:54
     */
    List<Map<String, Object>> selectQuestion(String keyWord);

    MessageResponse insertQuestion(Map<String, Object> params);

    WxCfg findBySysId(String sysId);
}

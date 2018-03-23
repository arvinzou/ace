package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.Live;
import com.huacainfo.ace.jxb.vo.LiveVo;
import com.huacainfo.ace.jxb.vo.LiveQVo;

import java.util.Map;

/**
 * @author: 陈晓克
 * @version: 2017-12-27
 * @Description: TODO(直播)
 */
public interface LiveService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(直播分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<LiveVo>
     * @author: 陈晓克
     * @version: 2017-12-27
     */
    public abstract PageResult<LiveVo> findLiveList(LiveQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertLive
     * @Description: TODO(添加直播)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2017-12-27
     */
    public abstract MessageResponse insertLive(Live obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateLive
     * @Description: TODO(更新直播)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2017-12-27
     */
    public abstract MessageResponse updateLive(Live obj, UserProp userProp) throws Exception;


    public abstract MessageResponse updateLiveSelective(Live obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectLiveByPrimaryKey
     * @Description: TODO(获取直播)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Live>
     * @author: 陈晓克
     * @version: 2017-12-27
     */
    public abstract SingleResult<LiveVo> selectLiveByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteLiveByLiveId
     * @Description: TODO(删除直播)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2017-12-27
     */
    public abstract MessageResponse deleteLiveByLiveId(String id, UserProp userProp) throws Exception;


    MessageResponse insertLive(String openid, Live jxb) throws Exception;

    MessageResponse checkIsBandUsers(String openid);
}

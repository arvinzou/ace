package com.huacainfo.ace.live.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.live.model.LiveImg;
import com.huacainfo.ace.live.model.LiveRpt;
import com.huacainfo.ace.live.vo.LiveRptVo;
import com.huacainfo.ace.live.vo.LiveRptQVo;

import java.util.List;

/**
 * @author: 陈晓克
 * @version: 2018-01-03
 * @Description: TODO(图文直播)
 */
public interface LiveRptService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(图文直播分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<LiveRptVo>
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    public abstract PageResult<LiveRptVo> findLiveRptList(LiveRptQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertLiveRpt
     * @Description: TODO(添加图文直播)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    public abstract MessageResponse insertLiveRpt(LiveRpt obj, List<LiveImg> imgs) throws Exception;

    /**
     * @throws
     * @Title:updateLiveRpt
     * @Description: TODO(更新图文直播)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    public abstract MessageResponse updateLiveRpt(LiveRpt obj) throws Exception;

    /**
     * @throws
     * @Title:selectLiveRptByPrimaryKey
     * @Description: TODO(获取图文直播)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LiveRpt>
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    public abstract SingleResult<LiveRptVo> selectLiveRptByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteLiveRptByLiveRptId
     * @Description: TODO(删除图文直播)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    public abstract MessageResponse deleteLiveRptByLiveRptId(String id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateSortByPrimaryKey
     * @Description: TODO(更新图文直播顺序)
     * @param: @param id
     * @param: @param sort
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-07
     */
    public abstract MessageResponse updateSortByPrimaryKey(String id, int sort) throws Exception;


    /**
     * @throws
     * @Title:updateLiveRpt
     * @Description: TODO(更新图文直播)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    public abstract MessageResponse updateLiveRptStatus(String id, String status) throws Exception;


    /**
     *
     * @param data
     * @return
     */
    MessageResponse updateSequence(String data);
}

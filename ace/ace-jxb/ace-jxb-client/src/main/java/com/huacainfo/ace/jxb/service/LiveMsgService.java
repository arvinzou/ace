package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.LiveMsg;
import com.huacainfo.ace.jxb.vo.LiveMsgVo;
import com.huacainfo.ace.jxb.vo.LiveMsgQVo;

/**
 * @author: 陈晓克
 * @version: 2018-01-03
 * @Description: TODO(图文直播)
 */
public interface LiveMsgService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(图文直播分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<LiveMsgVo>
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    public abstract PageResult<LiveMsgVo> findLiveMsgList(LiveMsgQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertLiveMsg
     * @Description: TODO(添加图文直播)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    public abstract MessageResponse insertLiveMsg(LiveMsg obj) throws Exception;

    /**
     * @throws
     * @Title:updateLiveMsg
     * @Description: TODO(更新图文直播)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    public abstract MessageResponse updateLiveMsg(String id, String status) throws Exception;

    /**
     * @throws
     * @Title:selectLiveMsgByPrimaryKey
     * @Description: TODO(获取图文直播)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LiveMsg>
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    public abstract SingleResult<LiveMsgVo> selectLiveMsgByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteLiveMsgByLiveMsgId
     * @Description: TODO(删除图文直播)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    public abstract MessageResponse deleteLiveMsgByLiveMsgId(String id, UserProp userProp) throws Exception;


}

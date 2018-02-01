package com.huacainfo.ace.live.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.live.model.LiveImg;
import com.huacainfo.ace.live.vo.LiveImgVo;
import com.huacainfo.ace.live.vo.LiveImgQVo;
import com.huacainfo.ace.live.vo.LiveQVo;
import com.huacainfo.ace.live.vo.LiveVo;

/**
 * @author: 陈晓克
 * @version: 2018-01-13
 * @Description: TODO(图片)
 */
public interface LiveImgService {


    /**
     * @throws
     * @Title:insertLiveImg
     * @Description: TODO(添加图片)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    public abstract MessageResponse insertLiveImg(LiveImg obj, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:deleteLiveImgByLiveImgId
     * @Description: TODO(删除图片)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    public abstract MessageResponse deleteLiveImgByLiveImgId(String id, UserProp userProp) throws Exception;

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
    public abstract SingleResult<LiveImgVo> selectLiveImgByPrimaryKey(String id) throws Exception;
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
    public abstract PageResult<LiveImgVo> findLiveImgList(LiveImgQVo condition, String orderBy) throws Exception;
}

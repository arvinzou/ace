package com.huacainfo.ace.live.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.live.model.LiveImg;
import com.huacainfo.ace.live.vo.LiveImgVo;
import com.huacainfo.ace.live.vo.LiveImgQVo;

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


}

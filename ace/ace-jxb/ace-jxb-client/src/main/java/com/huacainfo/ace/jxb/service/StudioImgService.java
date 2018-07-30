package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.StudioImg;
import com.huacainfo.ace.jxb.vo.StudioImgQVo;
import com.huacainfo.ace.jxb.vo.StudioImgVo;

/**
 * @author: Arvin
 * @version: 2018-07-25
 * @Description: TODO(工作室图片)
 */
public interface StudioImgService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(工作室图片分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <StudioImgVo>
     * @author: Arvin
     * @version: 2018-07-25
     */
    PageResult
            <StudioImgVo> findStudioImgList(StudioImgQVo condition,
                                            int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertStudioImg
     * @Description: TODO(添加工作室图片)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    MessageResponse insertStudioImg(StudioImg obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateStudioImg
     * @Description: TODO(更新工作室图片)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    MessageResponse updateStudioImg(StudioImg obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectStudioImgByPrimaryKey
     * @Description: TODO(获取工作室图片)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<StudioImg>
     * @author: Arvin
     * @version: 2018-07-25
     */
    SingleResult
            <StudioImgVo> selectStudioImgByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteStudioImgByStudioImgId
     * @Description: TODO(删除工作室图片)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    MessageResponse deleteStudioImgByStudioImgId(String id, UserProp userProp) throws Exception;

}

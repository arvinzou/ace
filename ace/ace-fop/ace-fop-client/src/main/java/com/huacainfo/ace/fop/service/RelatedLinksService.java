package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.RelatedLinks;
import com.huacainfo.ace.fop.vo.RelatedLinksVo;
import com.huacainfo.ace.fop.vo.RelatedLinksQVo;

import java.util.List;

/**
 * @author: huacai003
 * @version: 2018-05-23
 * @Description: TODO(相关链接)
 */
public interface RelatedLinksService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(相关链接分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <RelatedLinksVo>
     * @author: huacai003
     * @version: 2018-05-23
     */
    PageResult
            <RelatedLinksVo> findRelatedLinksList(RelatedLinksQVo condition, int start, int limit, String orderBy) throws
            Exception;

    /**
     * @throws
     * @Title:insertRelatedLinks
     * @Description: TODO(添加相关链接)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-23
     */
    MessageResponse insertRelatedLinks(RelatedLinks obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateRelatedLinks
     * @Description: TODO(更新相关链接)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-23
     */
    MessageResponse updateRelatedLinks(RelatedLinks obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectRelatedLinksByPrimaryKey
     * @Description: TODO(获取相关链接)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<RelatedLinks>
     * @author: huacai003
     * @version: 2018-05-23
     */
    SingleResult
            <RelatedLinksVo> selectRelatedLinksByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteRelatedLinksByRelatedLinksId
     * @Description: TODO(删除相关链接)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-23
     */
    MessageResponse deleteRelatedLinksByRelatedLinksId(String id, UserProp userProp) throws Exception;

    List<RelatedLinksVo> relatedLinksTree(String parentId) throws Exception;

}

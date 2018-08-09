package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.PostLevel;
import com.huacainfo.ace.jxb.vo.PostLevelQVo;
import com.huacainfo.ace.jxb.vo.PostLevelVo;

/**
 * @author: Arvin
 * @version: 2018-08-08
 * @Description: TODO(咨询师岗位级别配置)
 */
public interface PostLevelService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(咨询师岗位级别配置分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <PostLevelVo>
     * @author: Arvin
     * @version: 2018-08-08
     */
    PageResult<PostLevelVo> findPostLevelList(PostLevelQVo condition,
                                              int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertPostLevel
     * @Description: TODO(添加咨询师岗位级别配置)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-08
     */
    MessageResponse insertPostLevel(PostLevel obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updatePostLevel
     * @Description: TODO(更新咨询师岗位级别配置)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-08
     */
    MessageResponse updatePostLevel(PostLevel obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectPostLevelByPrimaryKey
     * @Description: TODO(获取咨询师岗位级别配置)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<PostLevel>
     * @author: Arvin
     * @version: 2018-08-08
     */
    SingleResult<PostLevelVo> selectPostLevelByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deletePostLevelByPostLevelId
     * @Description: TODO(删除咨询师岗位级别配置)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-08
     */
    MessageResponse deletePostLevelByPostLevelId(String id, UserProp userProp) throws Exception;

}

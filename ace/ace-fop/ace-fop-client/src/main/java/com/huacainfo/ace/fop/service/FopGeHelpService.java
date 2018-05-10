package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopGeHelp;
import com.huacainfo.ace.fop.vo.FopGeHelpQVo;
import com.huacainfo.ace.fop.vo.FopGeHelpVo;

/**
 * @author: Arvin
 * @version: 2018-05-08
 * @Description: TODO(政企服务)
 */
public interface FopGeHelpService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(政企服务分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FopGeHelpVo>
     * @author: Arvin
     * @version: 2018-05-08
     */
    PageResult<FopGeHelpVo> findFopGeHelpList(FopGeHelpQVo condition, int start, int limit, String orderBy) throws Exception;


    ResultResponse findGeHelpList(FopGeHelpQVo condition, int page, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertFopGeHelp
     * @Description: TODO(添加政企服务)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-08
     */
    MessageResponse insertFopGeHelp(FopGeHelp obj, UserProp userProp) throws Exception;

    MessageResponse insertGeHelp(FopGeHelp obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopGeHelp
     * @Description: TODO(更新政企服务)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-08
     */
    MessageResponse updateFopGeHelp(FopGeHelp obj, UserProp userProp) throws Exception;

    MessageResponse updateGeHelp(FopGeHelp obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopGeHelpByPrimaryKey
     * @Description: TODO(获取政企服务)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopGeHelp>
     * @author: Arvin
     * @version: 2018-05-08
     */
    SingleResult<FopGeHelpVo> selectFopGeHelpByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopGeHelpByFopGeHelpId
     * @Description: TODO(删除政企服务)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-08
     */
    MessageResponse deleteFopGeHelpByFopGeHelpId(String id, UserProp userProp) throws Exception;

    /**
     * 功能描述: 审核发布
     *
     * @param:id fop_ge_help.id
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/8 18:18
     */
    MessageResponse audit(String id, UserProp curUserProp) throws Exception;

    FopGeHelp selectByPrimaryKey(String fromId);
}
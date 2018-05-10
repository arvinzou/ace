package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopAppealHelp;
import com.huacainfo.ace.fop.vo.FopAppealHelpQVo;
import com.huacainfo.ace.fop.vo.FopAppealHelpVo;

/**
 * @author: Arvin
 * @version: 2018-05-10
 * @Description: TODO(诉求服务)
 */
public interface FopAppealHelpService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(诉求服务分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FopAppealHelpVo>
     * @author: Arvin
     * @version: 2018-05-10
     */
    PageResult<FopAppealHelpVo> findFopAppealHelpList(FopAppealHelpQVo condition,
                                                      int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertFopAppealHelp
     * @Description: TODO(添加诉求服务)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-10
     */
    MessageResponse insertFopAppealHelp(FopAppealHelp obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopAppealHelp
     * @Description: TODO(更新诉求服务)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-10
     */
    MessageResponse updateFopAppealHelp(FopAppealHelp obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopAppealHelpByPrimaryKey
     * @Description: TODO(获取诉求服务)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopAppealHelp>
     * @author: Arvin
     * @version: 2018-05-10
     */
    SingleResult<FopAppealHelpVo> selectFopAppealHelpByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopAppealHelpByFopAppealHelpId
     * @Description: TODO(删除诉求服务)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-10
     */
    MessageResponse deleteFopAppealHelpByFopAppealHelpId(String id, UserProp userProp) throws Exception;

    /**
     * 功能描述: 确认回复
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/10 18:33
     */
    MessageResponse audit(String id, UserProp userProp) throws Exception;

    /**
     * 功能描述: 根据主键查询记录
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/10 18:51
     */
    FopAppealHelp selectByPrimaryKey(String fromId);
}

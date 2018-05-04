package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopMember;
import com.huacainfo.ace.fop.vo.FopMemberQVo;
import com.huacainfo.ace.fop.vo.FopMemberVo;

/**
 * @author: Arvin
 * @version: 2018-05-04
 * @Description: TODO(通知公告)
 */
public interface FopMemberService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(通知公告分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FopMemberVo>
     * @author: Arvin
     * @version: 2018-05-04
     */
    PageResult<FopMemberVo> findFopMemberList(FopMemberQVo condition, int start, int limit, String orderBy) throws
            Exception;

    /**
     * @throws
     * @Title:insertFopMember
     * @Description:
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-04
     */
    MessageResponse insertFopMember(FopMember obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopMember
     * @Description:
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-04
     */
    MessageResponse updateFopMember(FopMember obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopMemberByPrimaryKey
     * @Description:
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopMember>
     * @author: Arvin
     * @version: 2018-05-04
     */
    SingleResult<FopMemberVo> selectFopMemberByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopMemberByFopMemberId
     * @Description:
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-04
     */
    MessageResponse deleteFopMemberByFopMemberId(String id, UserProp userProp) throws
            Exception;


}

package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.MemberRelation;
import com.huacainfo.ace.jxb.vo.MemberRelationQVo;
import com.huacainfo.ace.jxb.vo.MemberRelationVo;

/**
 * @author: Arvin
 * @version: 2018-07-23
 * @Description: TODO(会员关系表)
 */
public interface MemberRelationService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(会员关系表分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <MemberRelationVo>
     * @author: Arvin
     * @version: 2018-07-23
     */
    PageResult
            <MemberRelationVo> findMemberRelationList(MemberRelationQVo condition,
                                                      int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertMemberRelation
     * @Description: TODO(添加会员关系表)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-23
     */
    MessageResponse insertMemberRelation(MemberRelation obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateMemberRelation
     * @Description: TODO(更新会员关系表)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-23
     */
    MessageResponse updateMemberRelation(MemberRelation obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectMemberRelationByPrimaryKey
     * @Description: TODO(获取会员关系表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<MemberRelation>
     * @author: Arvin
     * @version: 2018-07-23
     */
    SingleResult
            <MemberRelationVo> selectMemberRelationByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteMemberRelationByMemberRelationId
     * @Description: TODO(删除会员关系表)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-23
     */
    MessageResponse deleteMemberRelationByMemberRelationId(String id, UserProp userProp) throws Exception;

}

package com.huacainfo.ace.jxb.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.Member;
import com.huacainfo.ace.jxb.vo.MemberVo;
import com.huacainfo.ace.jxb.vo.MemberQVo;
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(会员)
 */
public interface MemberService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(会员分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<MemberVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract PageResult<MemberVo> findMemberList(MemberQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertMember
	    * @Description:  TODO(添加会员)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse insertMember(Member obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateMember
	    * @Description:  TODO(更新会员)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse updateMember(Member obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectMemberByPrimaryKey
	    * @Description:  TODO(获取会员)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Member>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract SingleResult<MemberVo> selectMemberByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteMemberByMemberId
	    * @Description:  TODO(删除会员)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse deleteMemberByMemberId(String id,UserProp userProp) throws Exception;

	
}

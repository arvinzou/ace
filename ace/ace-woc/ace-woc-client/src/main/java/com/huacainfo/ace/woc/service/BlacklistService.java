package com.huacainfo.ace.woc.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.Blacklist;
import com.huacainfo.ace.woc.vo.BlacklistVo;
import com.huacainfo.ace.woc.vo.BlacklistQVo;
/**
 * @author: Arvin
 * @version: 2018-03-12
 * @Description:  TODO(黑名单档案)
 */
public interface BlacklistService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(黑名单档案分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<BlacklistVo>
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract PageResult<BlacklistVo> findBlacklistList(BlacklistQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertBlacklist
	    * @Description:  TODO(添加黑名单档案)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract MessageResponse insertBlacklist(Blacklist obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateBlacklist
	    * @Description:  TODO(更新黑名单档案)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract MessageResponse updateBlacklist(Blacklist obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectBlacklistByPrimaryKey
	    * @Description:  TODO(获取黑名单档案)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Blacklist>
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract SingleResult<BlacklistVo> selectBlacklistByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteBlacklistByBlacklistId
	    * @Description:  TODO(删除黑名单档案)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract MessageResponse deleteBlacklistByBlacklistId(String id,UserProp userProp) throws Exception;

	
}

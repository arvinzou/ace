package com.huacainfo.ace.woc.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.Site;
import com.huacainfo.ace.woc.vo.SiteVo;
import com.huacainfo.ace.woc.vo.SiteQVo;
/**
 * @author: 王恩
 * @version: 2018-03-09
 * @Description:  TODO(卡点档案)
 */
public interface SiteService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(卡点档案分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<SiteVo>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	public abstract PageResult<SiteVo> findSiteList(SiteQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertSite
	    * @Description:  TODO(添加卡点档案)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	public abstract MessageResponse insertSite(Site obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateSite
	    * @Description:  TODO(更新卡点档案)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	public abstract MessageResponse updateSite(Site obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectSiteByPrimaryKey
	    * @Description:  TODO(获取卡点档案)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Site>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	public abstract SingleResult<SiteVo> selectSiteByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteSiteBySiteId
	    * @Description:  TODO(删除卡点档案)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
	public abstract MessageResponse deleteSiteBySiteId(String id,UserProp userProp) throws Exception;

	
}

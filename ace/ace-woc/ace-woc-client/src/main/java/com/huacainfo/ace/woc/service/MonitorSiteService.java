package com.huacainfo.ace.woc.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.MonitorSite;
import com.huacainfo.ace.woc.vo.MonitorSiteVo;
import com.huacainfo.ace.woc.vo.MonitorSiteQVo;
/**
 * @author: Arvin
 * @version: 2018-03-12
 * @Description:  TODO(监控点档案)
 */
public interface MonitorSiteService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(监控点档案分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<MonitorSiteVo>
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract PageResult<MonitorSiteVo> findMonitorSiteList(MonitorSiteQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertMonitorSite
	    * @Description:  TODO(添加监控点档案)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract MessageResponse insertMonitorSite(MonitorSite obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateMonitorSite
	    * @Description:  TODO(更新监控点档案)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract MessageResponse updateMonitorSite(MonitorSite obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectMonitorSiteByPrimaryKey
	    * @Description:  TODO(获取监控点档案)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<MonitorSite>
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract SingleResult<MonitorSiteVo> selectMonitorSiteByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteMonitorSiteByMonitorSiteId
	    * @Description:  TODO(删除监控点档案)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract MessageResponse deleteMonitorSiteByMonitorSiteId(String id,UserProp userProp) throws Exception;

	
}

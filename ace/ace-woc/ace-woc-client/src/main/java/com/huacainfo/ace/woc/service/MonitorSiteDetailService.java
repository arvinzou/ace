package com.huacainfo.ace.woc.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.MonitorSiteDetail;
import com.huacainfo.ace.woc.vo.MonitorSiteDetailVo;
import com.huacainfo.ace.woc.vo.MonitorSiteDetailQVo;
/**
 * @author: Arvin
 * @version: 2018-03-12
 * @Description:  TODO(监控点明细档案)
 */
public interface MonitorSiteDetailService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(监控点明细档案分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<MonitorSiteDetailVo>
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract PageResult<MonitorSiteDetailVo> findMonitorSiteDetailList(MonitorSiteDetailQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertMonitorSiteDetail
	    * @Description:  TODO(添加监控点明细档案)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract MessageResponse insertMonitorSiteDetail(MonitorSiteDetail obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateMonitorSiteDetail
	    * @Description:  TODO(更新监控点明细档案)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract MessageResponse updateMonitorSiteDetail(MonitorSiteDetail obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectMonitorSiteDetailByPrimaryKey
	    * @Description:  TODO(获取监控点明细档案)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<MonitorSiteDetail>
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract SingleResult<MonitorSiteDetailVo> selectMonitorSiteDetailByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteMonitorSiteDetailByMonitorSiteDetailId
	    * @Description:  TODO(删除监控点明细档案)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	public abstract MessageResponse deleteMonitorSiteDetailByMonitorSiteDetailId(String id,UserProp userProp) throws Exception;

	
}

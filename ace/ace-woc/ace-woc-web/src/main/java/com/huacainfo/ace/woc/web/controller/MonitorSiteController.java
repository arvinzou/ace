package com.huacainfo.ace.woc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.MonitorSite;
import com.huacainfo.ace.woc.service.MonitorSiteService;
import com.huacainfo.ace.woc.vo.MonitorSiteVo;
import com.huacainfo.ace.woc.vo.MonitorSiteQVo;

@Controller
@RequestMapping("/monitorSite")
/**
 * @author: Arvin
 * @version: 2018-03-12
 * @Description:  TODO(监控点档案)
 */
public class MonitorSiteController extends WocBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MonitorSiteService monitorSiteService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(监控点档案分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<MonitorSiteVo>
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	@RequestMapping(value = "/findMonitorSiteList")
	@ResponseBody
	public PageResult<MonitorSiteVo> findMonitorSiteList(MonitorSiteQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<MonitorSiteVo> rst = this.monitorSiteService
				.findMonitorSiteList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertMonitorSite
	    * @Description:  TODO(添加监控点档案)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	@RequestMapping(value = "/insertMonitorSite")
	@ResponseBody
	public MessageResponse insertMonitorSite(String jsons) throws Exception {
		MonitorSite obj = JSON.parseObject(jsons, MonitorSite.class);
		return this.monitorSiteService
				.insertMonitorSite(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateMonitorSite
	    * @Description:  TODO(更新监控点档案)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	@RequestMapping(value = "/updateMonitorSite")
	@ResponseBody
	public MessageResponse updateMonitorSite(String jsons) throws Exception {
		MonitorSite obj = JSON.parseObject(jsons, MonitorSite.class);
		return this.monitorSiteService
				.updateMonitorSite(obj, this.getCurUserProp());
	}
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
	@RequestMapping(value = "/selectMonitorSiteByPrimaryKey")
	@ResponseBody
	public SingleResult<MonitorSiteVo> selectMonitorSiteByPrimaryKey(String id)
			throws Exception {
		return this.monitorSiteService.selectMonitorSiteByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteMonitorSiteByMonitorSiteId
	    * @Description:  TODO(删除监控点档案)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-12
	 */
	@RequestMapping(value = "/deleteMonitorSiteByMonitorSiteId")
	@ResponseBody
	public MessageResponse deleteMonitorSiteByMonitorSiteId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.monitorSiteService.deleteMonitorSiteByMonitorSiteId(id,
				this.getCurUserProp());
	}
}

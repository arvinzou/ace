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
import com.huacainfo.ace.woc.model.Traffic;
import com.huacainfo.ace.woc.service.TrafficService;
import com.huacainfo.ace.woc.vo.TrafficVo;
import com.huacainfo.ace.woc.vo.TrafficQVo;

@Controller
@RequestMapping("/traffic")
/**
 * @author: 王恩
 * @version: 2018-03-21
 * @Description:  TODO(案件审核记录)
 */
public class TrafficController extends WocBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TrafficService trafficService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(案件审核记录分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<TrafficVo>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/findTrafficList")
	@ResponseBody
	public PageResult<TrafficVo> findTrafficList(TrafficQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<TrafficVo> rst = this.trafficService
				.findTrafficList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertTraffic
	    * @Description:  TODO(添加案件审核记录)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/insertTraffic")
	@ResponseBody
	public MessageResponse insertTraffic(String jsons) throws Exception {
		Traffic obj = JSON.parseObject(jsons, Traffic.class);
		return this.trafficService
				.insertTraffic(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateTraffic
	    * @Description:  TODO(更新案件审核记录)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/updateTraffic")
	@ResponseBody
	public MessageResponse updateTraffic(String jsons) throws Exception {
		Traffic obj = JSON.parseObject(jsons, Traffic.class);
		return this.trafficService
				.updateTraffic(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectTrafficByPrimaryKey
	    * @Description:  TODO(获取案件审核记录)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Traffic>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/selectTrafficByPrimaryKey")
	@ResponseBody
	public SingleResult<TrafficVo> selectTrafficByPrimaryKey(String id)
			throws Exception {
		return this.trafficService.selectTrafficByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteTrafficByTrafficId
	    * @Description:  TODO(删除案件审核记录)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/deleteTrafficByTrafficId")
	@ResponseBody
	public MessageResponse deleteTrafficByTrafficId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.trafficService.deleteTrafficByTrafficId(id,
				this.getCurUserProp());
	}
}

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
import com.huacainfo.ace.woc.model.TrafficSub;
import com.huacainfo.ace.woc.service.TrafficSubService;
import com.huacainfo.ace.woc.vo.TrafficSubVo;
import com.huacainfo.ace.woc.vo.TrafficSubQVo;

@Controller
@RequestMapping("/trafficSub")
/**
 * @author: 王恩
 * @version: 2018-03-21
 * @Description:  TODO(通行记录子表)
 */
public class TrafficSubController extends WocBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TrafficSubService trafficSubService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(通行记录子表分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<TrafficSubVo>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/findTrafficSubList")
	@ResponseBody
	public PageResult<TrafficSubVo> findTrafficSubList(TrafficSubQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<TrafficSubVo> rst = this.trafficSubService
				.findTrafficSubList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertTrafficSub
	    * @Description:  TODO(添加通行记录子表)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/insertTrafficSub")
	@ResponseBody
	public MessageResponse insertTrafficSub(String jsons) throws Exception {
		TrafficSub obj = JSON.parseObject(jsons, TrafficSub.class);
		return this.trafficSubService
				.insertTrafficSub(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateTrafficSub
	    * @Description:  TODO(更新通行记录子表)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/updateTrafficSub")
	@ResponseBody
	public MessageResponse updateTrafficSub(String jsons) throws Exception {
		TrafficSub obj = JSON.parseObject(jsons, TrafficSub.class);
		return this.trafficSubService
				.updateTrafficSub(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectTrafficSubByPrimaryKey
	    * @Description:  TODO(获取通行记录子表)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<TrafficSub>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/selectTrafficSubByPrimaryKey")
	@ResponseBody
	public SingleResult<TrafficSubVo> selectTrafficSubByPrimaryKey(String id)
			throws Exception {
		return this.trafficSubService.selectTrafficSubByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteTrafficSubByTrafficSubId
	    * @Description:  TODO(删除通行记录子表)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/deleteTrafficSubByTrafficSubId")
	@ResponseBody
	public MessageResponse deleteTrafficSubByTrafficSubId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.trafficSubService.deleteTrafficSubByTrafficSubId(id,
				this.getCurUserProp());
	}
}

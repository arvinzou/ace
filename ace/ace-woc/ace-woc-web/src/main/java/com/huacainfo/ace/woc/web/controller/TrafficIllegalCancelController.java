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
import com.huacainfo.ace.woc.model.TrafficIllegalCancel;
import com.huacainfo.ace.woc.service.TrafficIllegalCancelService;
import com.huacainfo.ace.woc.vo.TrafficIllegalCancelVo;
import com.huacainfo.ace.woc.vo.TrafficIllegalCancelQVo;

@Controller
@RequestMapping("/trafficIllegalCancel")
/**
 * @author: 王恩
 * @version: 2018-03-21
 * @Description:  TODO(通行违章记录)
 */
public class TrafficIllegalCancelController extends WocBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TrafficIllegalCancelService trafficIllegalCancelService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(通行违章记录分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<TrafficIllegalCancelVo>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/findTrafficIllegalCancelList")
	@ResponseBody
	public PageResult<TrafficIllegalCancelVo> findTrafficIllegalCancelList(TrafficIllegalCancelQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<TrafficIllegalCancelVo> rst = this.trafficIllegalCancelService
				.findTrafficIllegalCancelList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertTrafficIllegalCancel
	    * @Description:  TODO(添加通行违章记录)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/insertTrafficIllegalCancel")
	@ResponseBody
	public MessageResponse insertTrafficIllegalCancel(String jsons) throws Exception {
		TrafficIllegalCancel obj = JSON.parseObject(jsons, TrafficIllegalCancel.class);
		return this.trafficIllegalCancelService
				.insertTrafficIllegalCancel(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateTrafficIllegalCancel
	    * @Description:  TODO(更新通行违章记录)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/updateTrafficIllegalCancel")
	@ResponseBody
	public MessageResponse updateTrafficIllegalCancel(String jsons) throws Exception {
		TrafficIllegalCancel obj = JSON.parseObject(jsons, TrafficIllegalCancel.class);
		return this.trafficIllegalCancelService
				.updateTrafficIllegalCancel(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectTrafficIllegalCancelByPrimaryKey
	    * @Description:  TODO(获取通行违章记录)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<TrafficIllegalCancel>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/selectTrafficIllegalCancelByPrimaryKey")
	@ResponseBody
	public SingleResult<TrafficIllegalCancelVo> selectTrafficIllegalCancelByPrimaryKey(String id)
			throws Exception {
		return this.trafficIllegalCancelService.selectTrafficIllegalCancelByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteTrafficIllegalCancelByTrafficIllegalCancelId
	    * @Description:  TODO(删除通行违章记录)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/deleteTrafficIllegalCancelByTrafficIllegalCancelId")
	@ResponseBody
	public MessageResponse deleteTrafficIllegalCancelByTrafficIllegalCancelId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.trafficIllegalCancelService.deleteTrafficIllegalCancelByTrafficIllegalCancelId(id,
				this.getCurUserProp());
	}
}

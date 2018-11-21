package com.huacainfo.ace.woc.web.controller;

import com.huacainfo.ace.common.tools.CommonUtils;
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
import com.huacainfo.ace.woc.model.Road;
import com.huacainfo.ace.woc.service.RoadService;
import com.huacainfo.ace.woc.vo.RoadVo;
import com.huacainfo.ace.woc.vo.RoadQVo;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/road")
/**
 * @author: Arvin
 * @version: 2018-03-09
 * @Description:  TODO(道路档案)
 */
public class RoadController extends WocBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoadService roadService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(道路档案分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<RoadVo>
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-09
	 */
	@RequestMapping(value = "/findRoadList")
	@ResponseBody
	public PageResult<RoadVo> findRoadList(RoadQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<RoadVo> rst = this.roadService
				.findRoadList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertRoad
	    * @Description:  TODO(添加道路档案)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-09
	 */
	@RequestMapping(value = "/insertRoad")
	@ResponseBody
	public MessageResponse insertRoad(String jsons) throws Exception {
		Road obj = JSON.parseObject(jsons, Road.class);
		return this.roadService
				.insertRoad(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateRoad
	    * @Description:  TODO(更新道路档案)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-09
	 */
	@RequestMapping(value = "/updateRoad")
	@ResponseBody
	public MessageResponse updateRoad(String jsons) throws Exception {
		Road obj = JSON.parseObject(jsons, Road.class);
		return this.roadService
				.updateRoad(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectRoadByPrimaryKey
	    * @Description:  TODO(获取道路档案)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Road>
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-09
	 */
	@RequestMapping(value = "/selectRoadByPrimaryKey")
	@ResponseBody
	public SingleResult<RoadVo> selectRoadByPrimaryKey(String id)
			throws Exception {
		return this.roadService.selectRoadByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteRoadByRoadId
	    * @Description:  TODO(删除道路档案)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-09
	 */
	@RequestMapping(value = "/deleteRoadByRoadId")
	@ResponseBody
	public MessageResponse deleteRoadByRoadId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.roadService.deleteRoadByRoadId(id,
				this.getCurUserProp());
	}

	@RequestMapping(value = "/selectRoad")
	@ResponseBody
    public Map<String, Object> selectRoad(String q, String id) throws Exception {
        Map<String,Object> params=new HashMap<String,Object>();
		params.put("q", id);
		if(!CommonUtils.isBlank(q)){
			params.put("q", q);
		}
		this.logger.info("",params);
		return this.roadService.selectRoad(params);
	}
}

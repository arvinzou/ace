package com.huacainfo.ace.iop.web.controller;

import org.apache.log4j.Logger;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.iop.model.EvTaskData;
import com.huacainfo.ace.iop.vo.EvTaskDataQVo;
import com.huacainfo.ace.iop.service.EvTaskDataService;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.iop.vo.EvTaskDataVo;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/evTaskData")
public class EvTaskDataController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private EvTaskDataService evTaskDataService;
	@RequestMapping(value = "/findEvTaskDataList")
	@ResponseBody
	public PageResult<EvTaskDataVo> findEvTaskDataList(EvTaskDataQVo condition, PageParamNoChangeSord page) throws Exception {
		PageResult<EvTaskDataVo> rst = this.evTaskDataService.findEvTaskDataList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}

	@RequestMapping(value = "/insertEvTaskData")
	@ResponseBody
	public MessageResponse insertEvTaskData(String jsons) throws Exception{

		return this.evTaskDataService.insertEvTaskData(jsons, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateEvTaskData")
	@ResponseBody
	public MessageResponse updateEvTaskData(String jsons) throws Exception{
		EvTaskData obj = JSON.parseObject(jsons, EvTaskData.class);
		return this.evTaskDataService.updateEvTaskData(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectEvTaskDataByPrimaryKey")
	@ResponseBody
	public SingleResult<EvTaskDataVo> selectEvTaskDataByPrimaryKey(String id) throws Exception{
		return  this.evTaskDataService.selectEvTaskDataByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteEvTaskDataByEvTaskDataId")
	@ResponseBody
	public MessageResponse deleteEvTaskDataByEvTaskDataId(String jsons) throws Exception{
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.evTaskDataService.deleteEvTaskDataByEvTaskDataId(id, this.getCurUserProp());
	}

	@RequestMapping(value = "/saveOrUpdateEvTaskData")
	@ResponseBody
	public MessageResponse saveOrUpdateTeamPrepare(String jsons) throws Exception{
		EvTaskData obj = JSON.parseObject(jsons, EvTaskData.class);
		return this.evTaskDataService.saveOrUpdateEvTaskData(obj, this.getCurUserProp());
	}

}

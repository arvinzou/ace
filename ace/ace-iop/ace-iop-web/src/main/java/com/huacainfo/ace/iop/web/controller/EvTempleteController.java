package com.huacainfo.ace.iop.web.controller;

import org.apache.log4j.Logger;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.iop.model.EvTemplete;
import com.huacainfo.ace.iop.vo.EvTempleteQVo;
import com.huacainfo.ace.iop.service.EvTempleteService;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.iop.vo.EvTempleteVo;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/evTemplete")
public class EvTempleteController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private EvTempleteService evTempleteService;
	
	@RequestMapping(value = "/findEvTempleteList")
	@ResponseBody
	public PageResult<EvTempleteVo> findEvTempleteList(EvTempleteQVo condition, PageParamNoChangeSord page) throws Exception {
		PageResult<EvTempleteVo> rst = this.evTempleteService.findEvTempleteList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}

	@RequestMapping(value = "/insertEvTemplete")
	@ResponseBody
	public MessageResponse insertEvTemplete(String jsons) throws Exception{
		EvTemplete obj = JSON.parseObject(jsons, EvTemplete.class);
		return this.evTempleteService.insertEvTemplete(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateEvTemplete")
	@ResponseBody
	public MessageResponse updateEvTemplete(String jsons) throws Exception{
		EvTemplete obj = JSON.parseObject(jsons, EvTemplete.class);
		return this.evTempleteService.updateEvTemplete(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectEvTempleteByPrimaryKey")
	@ResponseBody
	public SingleResult<EvTempleteVo> selectEvTempleteByPrimaryKey(String id) throws Exception{
		return  this.evTempleteService.selectEvTempleteByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteEvTempleteByEvTempleteId")
	@ResponseBody
	public MessageResponse deleteEvTempleteByEvTempleteId(String jsons) throws Exception{
		com.alibaba.fastjson.JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.evTempleteService.deleteEvTempleteByEvTempleteId(id, this.getCurUserProp());
	}

	@RequestMapping(value = "/saveOrUpdateEvTemplete")
	@ResponseBody
	public MessageResponse saveOrUpdateTeamPrepare(String jsons) throws Exception{
		EvTemplete obj = JSON.parseObject(jsons, EvTemplete.class);
		return this.evTempleteService.saveOrUpdateEvTemplete(obj, this.getCurUserProp());
	}
}

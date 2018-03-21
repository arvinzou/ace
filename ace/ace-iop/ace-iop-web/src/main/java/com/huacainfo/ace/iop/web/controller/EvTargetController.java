package com.huacainfo.ace.iop.web.controller;
import org.apache.log4j.Logger;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.iop.model.EvTarget;
import com.huacainfo.ace.iop.vo.EvTargetQVo;
import com.huacainfo.ace.iop.service.EvTargetService;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.iop.vo.EvTargetVo;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.alibaba.fastjson.JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/evTarget")
public class EvTargetController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private EvTargetService evTargetService;
	@RequestMapping(value = "/findEvTargetList")
	@ResponseBody
	public PageResult<EvTargetVo> findEvTargetList(EvTargetQVo condition, PageParamNoChangeSord page) throws Exception {
		PageResult<EvTargetVo> rst = this.evTargetService.findEvTargetList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}

	@RequestMapping(value = "/insertEvTarget")
	@ResponseBody
	public MessageResponse insertEvTarget(String jsons) throws Exception{
		EvTarget obj = JSON.parseObject(jsons, EvTarget.class);
		return this.evTargetService.insertEvTarget(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateEvTarget")
	@ResponseBody
	public MessageResponse updateEvTarget(String jsons) throws Exception{
		EvTarget obj = JSON.parseObject(jsons, EvTarget.class);
		return this.evTargetService.updateEvTarget(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectEvTargetByPrimaryKey")
	@ResponseBody
	public SingleResult<EvTargetVo> selectEvTargetByPrimaryKey(String id) throws Exception{
		return  this.evTargetService.selectEvTargetByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteEvTargetByEvTargetId")
	@ResponseBody
	public MessageResponse deleteEvTargetByEvTargetId(String jsons) throws Exception{
		com.alibaba.fastjson.JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.evTargetService.deleteEvTargetByEvTargetId(id, this.getCurUserProp());
	}

	@RequestMapping(value = "/saveOrUpdateEvTarget")
	@ResponseBody
	public MessageResponse saveOrUpdateTeamPrepare(String jsons) throws Exception{
		EvTarget obj = JSON.parseObject(jsons, EvTarget.class);
		return this.evTargetService.saveOrUpdateEvTarget(obj, this.getCurUserProp());
	}
}

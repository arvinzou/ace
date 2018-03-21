package com.huacainfo.ace.iop.web.controller;

import java.util.List;



import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.iop.model.EvScoreTemlete;
import com.huacainfo.ace.iop.service.EvScoreTemleteService;
import com.huacainfo.ace.iop.vo.EvScoreTemleteQVo;
import com.huacainfo.ace.iop.vo.EvScoreTemleteVo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/evScoreTemlete")
public class EvScoreTemleteController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private EvScoreTemleteService evScoreTemleteService;
	@RequestMapping(value = "/findEvScoreTemleteList")
	@ResponseBody
	public PageResult<EvScoreTemleteVo> findEvScoreTemleteList(EvScoreTemleteQVo condition, PageParamNoChangeSord page) throws Exception {
		PageResult<EvScoreTemleteVo> rst = this.evScoreTemleteService.findEvScoreTemleteList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}

	@RequestMapping(value = "/insertEvScoreTemlete")
	@ResponseBody
	public MessageResponse insertEvScoreTemlete(String jsons) throws Exception{
		EvScoreTemlete obj = JSON.parseObject(jsons, EvScoreTemlete.class);
		return this.evScoreTemleteService.insertEvScoreTemlete(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateEvScoreTemlete")
	@ResponseBody
	public MessageResponse updateEvScoreTemlete(String jsons) throws Exception{
		EvScoreTemlete obj = JSON.parseObject(jsons, EvScoreTemlete.class);
		return this.evScoreTemleteService.updateEvScoreTemlete(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectEvScoreTemleteByPrimaryKey")
	@ResponseBody
	public SingleResult<EvScoreTemleteVo> selectEvScoreTemleteByPrimaryKey(String id) throws Exception{
		return  this.evScoreTemleteService.selectEvScoreTemleteByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteEvScoreTemleteByEvScoreTemleteId")
	@ResponseBody
	public MessageResponse deleteEvScoreTemleteByEvScoreTemleteId(String jsons) throws Exception{
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.evScoreTemleteService.deleteEvScoreTemleteByEvScoreTemleteId(id, this.getCurUserProp());
	}

	@RequestMapping(value = "/saveOrUpdateEvScoreTemlete")
	@ResponseBody
	public MessageResponse saveOrUpdateTeamPrepare(String jsons) throws Exception{
		EvScoreTemlete obj = JSON.parseObject(jsons, EvScoreTemlete.class);
		return this.evScoreTemleteService.saveOrUpdateEvScoreTemlete(obj, this.getCurUserProp());
	}
	@RequestMapping(value = "/selectListAll")
	@ResponseBody
	public  List<EvScoreTemlete> selectListAll(){
		try{
			return this.evScoreTemleteService.selectListAll();
		}catch(Exception e){
			return null;
		}
	}
}

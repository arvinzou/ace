package com.huacainfo.ace.iop.web.controller;
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
import com.huacainfo.ace.iop.model.EvScoreTemleteSub;
import com.huacainfo.ace.iop.service.EvScoreTemleteSubService;
import com.huacainfo.ace.iop.vo.EvScoreTemleteSubQVo;
import com.huacainfo.ace.iop.vo.EvScoreTemleteSubVo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping("/evScoreTemleteSub")
public class EvScoreTemleteSubController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private EvScoreTemleteSubService evScoreTemleteSubService;
	@RequestMapping(value = "/findEvScoreTemleteSubList")
	@ResponseBody
	public PageResult<EvScoreTemleteSubVo> findEvScoreTemleteSubList(EvScoreTemleteSubQVo condition, PageParamNoChangeSord page) throws Exception {
		PageResult<EvScoreTemleteSubVo> rst = this.evScoreTemleteSubService.findEvScoreTemleteSubList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}

	@RequestMapping(value = "/insertEvScoreTemleteSub")
	@ResponseBody
	public MessageResponse insertEvScoreTemleteSub(String jsons) throws Exception{
		EvScoreTemleteSub obj = JSON.parseObject(jsons, EvScoreTemleteSub.class);
		return this.evScoreTemleteSubService.insertEvScoreTemleteSub(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateEvScoreTemleteSub")
	@ResponseBody
	public MessageResponse updateEvScoreTemleteSub(String jsons) throws Exception{
		EvScoreTemleteSub obj = JSON.parseObject(jsons, EvScoreTemleteSub.class);
		return this.evScoreTemleteSubService.updateEvScoreTemleteSub(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectEvScoreTemleteSubByPrimaryKey")
	@ResponseBody
	public SingleResult<EvScoreTemleteSubVo> selectEvScoreTemleteSubByPrimaryKey(String id) throws Exception{
		return  this.evScoreTemleteSubService.selectEvScoreTemleteSubByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteEvScoreTemleteSubByEvScoreTemleteSubId")
	@ResponseBody
	public MessageResponse deleteEvScoreTemleteSubByEvScoreTemleteSubId(String jsons) throws Exception{
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.evScoreTemleteSubService.deleteEvScoreTemleteSubByEvScoreTemleteSubId(id, this.getCurUserProp());
	}

	@RequestMapping(value = "/saveOrUpdateEvScoreTemleteSub")
	@ResponseBody
	public MessageResponse saveOrUpdateTeamPrepare(String jsons) throws Exception{
		EvScoreTemleteSub obj = JSON.parseObject(jsons, EvScoreTemleteSub.class);
		return this.evScoreTemleteSubService.saveOrUpdateEvScoreTemleteSub(obj, this.getCurUserProp());
	}
}

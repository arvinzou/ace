package com.huacainfo.ace.operana.web.controller;

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
import com.huacainfo.ace.operana.model.NormCfg;
import com.huacainfo.ace.operana.service.NormCfgService;
import com.huacainfo.ace.operana.vo.NormCfgVo;
import com.huacainfo.ace.operana.vo.NormCfgQVo;

import java.util.Calendar;

@Controller
@RequestMapping("/normCfg")
public class NormCfgController extends OperanaBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NormCfgService normCfgService;

	@RequestMapping(value = "/findNormCfgList.do")
	@ResponseBody
	public PageResult<NormCfgVo> findNormCfgList(NormCfgQVo condition, PageParamNoChangeSord page) throws Exception {
		if(CommonUtils.isBlank(condition.getYear())){
			Calendar a=Calendar.getInstance();
			int year=a.get(Calendar.YEAR);
			//condition.setYear(String.valueOf(year));
		}
		PageResult<NormCfgVo> rst = this.normCfgService.findNormCfgList(condition, page.getStart(), page.getLimit(),
				page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}

		return rst;
	}

	@RequestMapping(value = "/insertNormCfg.do")
	@ResponseBody
	public MessageResponse insertNormCfg(String jsons) throws Exception {
		NormCfg obj = JSON.parseObject(jsons, NormCfg.class);
		return this.normCfgService.insertNormCfg(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateNormCfg.do")
	@ResponseBody
	public MessageResponse updateNormCfg(String jsons) throws Exception {
		NormCfg obj = JSON.parseObject(jsons, NormCfg.class);
		return this.normCfgService.updateNormCfg(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectNormCfgByPrimaryKey.do")
	@ResponseBody
	public SingleResult<NormCfg> selectNormCfgByPrimaryKey(String id) throws Exception {
		return this.normCfgService.selectNormCfgByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteNormCfgByNormCfgId.do")
	@ResponseBody
	public MessageResponse deleteNormCfgByNormCfgId(String jsons) throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.normCfgService.deleteNormCfgByNormCfgId(id, this.getCurUserProp());
	}
}

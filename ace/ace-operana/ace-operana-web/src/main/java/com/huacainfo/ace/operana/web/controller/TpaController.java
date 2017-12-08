package com.huacainfo.ace.operana.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.operana.model.Tpa;
import com.huacainfo.ace.operana.service.TpaService;
import com.huacainfo.ace.operana.vo.TpaQVo;
import com.huacainfo.ace.operana.vo.TpaVo;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/tpa")
public class TpaController extends OperanaBaseController {

	/**
	 *
	 *
	 *
	 *
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TpaService tpaService;

	@RequestMapping(value = "/findTpaList.do")
	@ResponseBody
	public PageResult<TpaVo> findTpaList(TpaQVo condition, PageParamNoChangeSord page) throws Exception {
		PageResult<TpaVo> rst = this.tpaService.findTpaList(condition, page.getStart(), page.getLimit(),
				page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}

		return rst;
	}

	@RequestMapping(value = "/insertTpa.do")
	@ResponseBody
	public MessageResponse insertTpa(String jsons,Tpa o) throws Exception {
		Tpa obj = null;
		if(CommonUtils.isBlank(jsons)){
			obj=o;
		}else{
			obj = JSON.parseObject(jsons, Tpa.class);
		}
		return this.tpaService.insertTpa(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateTpa.do")
	@ResponseBody
	public MessageResponse updateTpa(String jsons,Tpa o,String oper) throws Exception {
		if(oper.equals("del")){
			return this.tpaService.deleteTpaByTpaId(o.getId(),this.getCurUserProp());
		}
		Tpa obj = null;
		if(CommonUtils.isBlank(jsons)){
			obj=o;
		}else{
			obj = JSON.parseObject(jsons, Tpa.class);
		}
		return this.tpaService.updateTpa(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectTpaByPrimaryKey.do")
	@ResponseBody
	public SingleResult<Tpa> selectTpaByPrimaryKey(String id) throws Exception {
		return this.tpaService.selectTpaByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteTpaByTpaId.do")
	@ResponseBody
	public MessageResponse deleteTpaByTpaId(String jsons) throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.tpaService.deleteTpaByTpaId(id, this.getCurUserProp());
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/findTpaUserTaskList.do")
	@ResponseBody
	public PageResult<TpaVo> findTpaUserTaskList(TpaQVo condition, PageParamNoChangeSord page) throws Exception {
		PageResult<TpaVo> rst = this.tpaService.findTpaUserTaskList(condition, page.getStart(), page.getLimit(),
				page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}
	@RequestMapping(value = "/updateTpaById.do")
	@ResponseBody
	public MessageResponse updateTpaById(String jsons,Tpa o,String oper) throws Exception {
		Tpa obj = null;
		if(CommonUtils.isBlank(jsons)){
			obj=o;
		}else{
			obj = JSON.parseObject(jsons, Tpa.class);
		}
		return this.tpaService.updateTpaById(obj, this.getCurUserProp());
	}
}


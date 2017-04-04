package com.huacainfo.ace.portal.web.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParam;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.SyCfg;
import com.huacainfo.ace.portal.service.SyCfgService;
import com.huacainfo.ace.portal.vo.SyCfgQVo;
import com.huacainfo.ace.portal.vo.SyCfgVo;

@Controller
@RequestMapping("/syCfg")
public class SyCfgController extends PortalBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SyCfgService syCfgService;

	@RequestMapping(value = "/findSyCfgList.do")
	@ResponseBody
	public PageResult<SyCfgVo> findSyCfgList(SyCfgQVo condition, PageParam page)
			throws Exception {
		PageResult<SyCfgVo> rst = this.syCfgService.findSyCfgList(condition,
				page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}

		return rst;
	}

	@RequestMapping(value = "/insertSyCfg.do")
	@ResponseBody
	public MessageResponse insertSyCfg(String jsons) throws Exception {
		SyCfg obj = JSON.parseObject(jsons, SyCfg.class);
		return this.syCfgService.insertSyCfg(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateSyCfg.do")
	@ResponseBody
	public MessageResponse updateSyCfg(String jsons) throws Exception {
		SyCfg obj = JSON.parseObject(jsons, SyCfg.class);
		return this.syCfgService.updateSyCfg(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectSyCfgByPrimaryKey.do")
	@ResponseBody
	public SingleResult<SyCfg> selectSyCfgByPrimaryKey(String id)
			throws Exception {
		return this.syCfgService.selectSyCfgByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteSyCfgBySyCfgId.do")
	@ResponseBody
	public MessageResponse deleteSyCfgBySyCfgId(String jsons) throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.syCfgService
				.deleteSyCfgBySyCfgId(id, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectSyCfgByUser.do")
	@ResponseBody
	public ListResult<Map<String, Object>> selectSyCfgByUser() throws Exception {
		return this.syCfgService.selectSyCfgByUser(this.getCurUserProp());
	}

}

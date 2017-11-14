package com.huacainfo.ace.rvc.web.controller;

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
import com.huacainfo.ace.uf.model.DiaoYan;
import com.huacainfo.ace.uf.service.DiaoYanService;
import com.huacainfo.ace.uf.vo.DiaoYanVo;
import com.huacainfo.ace.uf.vo.DiaoYanQVo;

@Controller
@RequestMapping("/diaoYan")
public class DiaoYanController extends UfBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DiaoYanService diaoYanService;

	@RequestMapping(value = "/findDiaoYanList.do")
	@ResponseBody
	public PageResult<DiaoYanVo> findDiaoYanList(DiaoYanQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<DiaoYanVo> rst = this.diaoYanService
				.findDiaoYanList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertDiaoYan.do")
	@ResponseBody
	public MessageResponse insertDiaoYan(String jsons) throws Exception {
		DiaoYan obj = JSON.parseObject(jsons, DiaoYan.class);
		return this.diaoYanService
				.insertDiaoYan(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateDiaoYan.do")
	@ResponseBody
	public MessageResponse updateDiaoYan(String jsons) throws Exception {
		DiaoYan obj = JSON.parseObject(jsons, DiaoYan.class);
		return this.diaoYanService
				.updateDiaoYan(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectDiaoYanByPrimaryKey.do")
	@ResponseBody
	public SingleResult<DiaoYanVo> selectDiaoYanByPrimaryKey(String id)
			throws Exception {
		return this.diaoYanService.selectDiaoYanByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteDiaoYanByDiaoYanId.do")
	@ResponseBody
	public MessageResponse deleteDiaoYanByDiaoYanId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.diaoYanService.deleteDiaoYanByDiaoYanId(id,
				this.getCurUserProp());
	}
}

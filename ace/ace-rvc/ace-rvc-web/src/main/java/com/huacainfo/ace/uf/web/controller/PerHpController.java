package com.huacainfo.ace.uf.web.controller;

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
import com.huacainfo.ace.uf.model.PerHp;
import com.huacainfo.ace.uf.service.PerHpService;
import com.huacainfo.ace.uf.vo.PerHpVo;
import com.huacainfo.ace.uf.vo.PerHpQVo;

@Controller
@RequestMapping("/perHp")
public class PerHpController extends UfBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PerHpService perHpService;

	@RequestMapping(value = "/findPerHpList.do")
	@ResponseBody
	public PageResult<PerHpVo> findPerHpList(PerHpQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<PerHpVo> rst = this.perHpService
				.findPerHpList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertPerHp.do")
	@ResponseBody
	public MessageResponse insertPerHp(String jsons) throws Exception {
		PerHp obj = JSON.parseObject(jsons, PerHp.class);
		return this.perHpService
				.insertPerHp(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updatePerHp.do")
	@ResponseBody
	public MessageResponse updatePerHp(String jsons) throws Exception {
		PerHp obj = JSON.parseObject(jsons, PerHp.class);
		return this.perHpService
				.updatePerHp(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectPerHpByPrimaryKey.do")
	@ResponseBody
	public SingleResult<PerHpVo> selectPerHpByPrimaryKey(String id)
			throws Exception {
		return this.perHpService.selectPerHpByPrimaryKey(id);
	}

	@RequestMapping(value = "/deletePerHpByPerHpId.do")
	@ResponseBody
	public MessageResponse deletePerHpByPerHpId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.perHpService.deletePerHpByPerHpId(id,
				this.getCurUserProp());
	}
}

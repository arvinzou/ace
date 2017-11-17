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
import com.huacainfo.ace.uf.model.PeiXun;
import com.huacainfo.ace.uf.service.PeiXunService;
import com.huacainfo.ace.uf.vo.PeiXunVo;
import com.huacainfo.ace.uf.vo.PeiXunQVo;

@Controller
@RequestMapping("/peiXun")
public class PeiXunController extends UfBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PeiXunService peiXunService;

	@RequestMapping(value = "/findPeiXunList.do")
	@ResponseBody
	public PageResult<PeiXunVo> findPeiXunList(PeiXunQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<PeiXunVo> rst = this.peiXunService
				.findPeiXunList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertPeiXun.do")
	@ResponseBody
	public MessageResponse insertPeiXun(String jsons) throws Exception {
		PeiXun obj = JSON.parseObject(jsons, PeiXun.class);
		return this.peiXunService
				.insertPeiXun(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updatePeiXun.do")
	@ResponseBody
	public MessageResponse updatePeiXun(String jsons) throws Exception {
		PeiXun obj = JSON.parseObject(jsons, PeiXun.class);
		return this.peiXunService.updatePeiXun(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectPeiXunByPrimaryKey.do")
	@ResponseBody
	public SingleResult<PeiXunVo> selectPeiXunByPrimaryKey(String id)
			throws Exception {
		return this.peiXunService.selectPeiXunByPrimaryKey(id);
	}

	@RequestMapping(value = "/deletePeiXunByPeiXunId.do")
	@ResponseBody
	public MessageResponse deletePeiXunByPeiXunId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.peiXunService.deletePeiXunByPeiXunId(id,
				this.getCurUserProp());
	}
}

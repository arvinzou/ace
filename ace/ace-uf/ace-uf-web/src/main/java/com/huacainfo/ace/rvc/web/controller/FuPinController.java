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
import com.huacainfo.ace.uf.model.FuPin;
import com.huacainfo.ace.uf.service.FuPinService;
import com.huacainfo.ace.uf.vo.FuPinVo;
import com.huacainfo.ace.uf.vo.FuPinQVo;

@Controller
@RequestMapping("/fuPin")
public class FuPinController extends UfBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FuPinService fuPinService;

	@RequestMapping(value = "/findFuPinList.do")
	@ResponseBody
	public PageResult<FuPinVo> findFuPinList(FuPinQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<FuPinVo> rst = this.fuPinService
				.findFuPinList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertFuPin.do")
	@ResponseBody
	public MessageResponse insertFuPin(String jsons) throws Exception {
		FuPin obj = JSON.parseObject(jsons, FuPin.class);
		return this.fuPinService
				.insertFuPin(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateFuPin.do")
	@ResponseBody
	public MessageResponse updateFuPin(String jsons) throws Exception {
		FuPin obj = JSON.parseObject(jsons, FuPin.class);
		return this.fuPinService
				.updateFuPin(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectFuPinByPrimaryKey.do")
	@ResponseBody
	public SingleResult<FuPinVo> selectFuPinByPrimaryKey(String id)
			throws Exception {
		return this.fuPinService.selectFuPinByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteFuPinByFuPinId.do")
	@ResponseBody
	public MessageResponse deleteFuPinByFuPinId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.fuPinService.deleteFuPinByFuPinId(id,
				this.getCurUserProp());
	}
}

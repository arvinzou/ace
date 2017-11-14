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
import com.huacainfo.ace.uf.model.PerHobby;
import com.huacainfo.ace.uf.service.PerHobbyService;
import com.huacainfo.ace.uf.vo.PerHobbyVo;
import com.huacainfo.ace.uf.vo.PerHobbyQVo;

@Controller
@RequestMapping("/perHobby")
public class PerHobbyController extends UfBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PerHobbyService perHobbyService;

	@RequestMapping(value = "/findPerHobbyList.do")
	@ResponseBody
	public PageResult<PerHobbyVo> findPerHobbyList(PerHobbyQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<PerHobbyVo> rst = this.perHobbyService
				.findPerHobbyList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertPerHobby.do")
	@ResponseBody
	public MessageResponse insertPerHobby(String jsons) throws Exception {
		PerHobby obj = JSON.parseObject(jsons, PerHobby.class);
		return this.perHobbyService
				.insertPerHobby(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updatePerHobby.do")
	@ResponseBody
	public MessageResponse updatePerHobby(String jsons) throws Exception {
		PerHobby obj = JSON.parseObject(jsons, PerHobby.class);
		return this.perHobbyService
				.updatePerHobby(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectPerHobbyByPrimaryKey.do")
	@ResponseBody
	public SingleResult<PerHobbyVo> selectPerHobbyByPrimaryKey(String id)
			throws Exception {
		return this.perHobbyService.selectPerHobbyByPrimaryKey(id);
	}

	@RequestMapping(value = "/deletePerHobbyByPerHobbyId.do")
	@ResponseBody
	public MessageResponse deletePerHobbyByPerHobbyId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.perHobbyService.deletePerHobbyByPerHobbyId(id,
				this.getCurUserProp());
	}
}

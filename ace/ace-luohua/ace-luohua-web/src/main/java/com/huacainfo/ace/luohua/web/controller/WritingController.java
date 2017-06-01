package com.huacainfo.ace.luohua.web.controller;

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
import com.huacainfo.ace.luohua.model.Writing;
import com.huacainfo.ace.luohua.service.WritingService;
import com.huacainfo.ace.luohua.vo.WritingVo;
import com.huacainfo.ace.luohua.vo.WritingQVo;

@Controller
@RequestMapping("/writing")
public class WritingController extends LuohuaBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private WritingService writingService;

	@RequestMapping(value = "/findWritingList.do")
	@ResponseBody
	public PageResult<WritingVo> findWritingList(WritingQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<WritingVo> rst = this.writingService
				.findWritingList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertWriting.do")
	@ResponseBody
	public MessageResponse insertWriting(String jsons) throws Exception {
		Writing obj = JSON.parseObject(jsons, Writing.class);
		return this.writingService
				.insertWriting(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateWriting.do")
	@ResponseBody
	public MessageResponse updateWriting(String jsons) throws Exception {
		Writing obj = JSON.parseObject(jsons, Writing.class);
		return this.writingService
				.updateWriting(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectWritingByPrimaryKey.do")
	@ResponseBody
	public SingleResult<Writing> selectWritingByPrimaryKey(String id)
			throws Exception {
		return this.writingService.selectWritingByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteWritingByWritingId.do")
	@ResponseBody
	public MessageResponse deleteWritingByWritingId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.writingService.deleteWritingByWritingId(id,
				this.getCurUserProp());
	}
}

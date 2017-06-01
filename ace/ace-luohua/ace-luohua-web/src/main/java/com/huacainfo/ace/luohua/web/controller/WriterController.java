package com.huacainfo.ace.luohua.web.controller;
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
import com.huacainfo.ace.luohua.model.Writer;
import com.huacainfo.ace.luohua.service.WriterService;
import com.huacainfo.ace.luohua.vo.WriterVo;
import com.huacainfo.ace.luohua.vo.WriterQVo;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/writer")
public class WriterController extends LuohuaBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private WriterService writerService;

	@RequestMapping(value = "/findWriterList.do")
	@ResponseBody
	public PageResult<WriterVo> findWriterList(WriterQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<WriterVo> rst = this.writerService
				.findWriterList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertWriter.do")
	@ResponseBody
	public MessageResponse insertWriter(String jsons) throws Exception {
		Writer obj = JSON.parseObject(jsons, Writer.class);
		return this.writerService
				.insertWriter(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateWriter.do")
	@ResponseBody
	public MessageResponse updateWriter(String jsons) throws Exception {
		Writer obj = JSON.parseObject(jsons, Writer.class);
		return this.writerService
				.updateWriter(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectWriterByPrimaryKey.do")
	@ResponseBody
	public SingleResult<Writer> selectWriterByPrimaryKey(String id)
			throws Exception {
		return this.writerService.selectWriterByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteWriterByWriterId.do")
	@ResponseBody
	public MessageResponse deleteWriterByWriterId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.writerService.deleteWriterByWriterId(id,
				this.getCurUserProp());
	}

	@RequestMapping(value = "/selectAuthor.do")
	@ResponseBody
	public Map<String,Object> selectAuthor(String q, String id)throws Exception {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("q", id);
		if(!CommonUtils.isBlank(q)){
			params.put("q", q);
		}
		this.logger.info("",params);
		return this.writerService.selectAuthor(params);
	}
}

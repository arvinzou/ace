package com.huacainfo.ace.kernel.web.controller;
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
import com.huacainfo.ace.kernel.model.Writer;
import com.huacainfo.ace.kernel.service.WriterService;
import com.huacainfo.ace.kernel.vo.WriterVo;
import com.huacainfo.ace.kernel.vo.WriterQVo;

@Controller
@RequestMapping("/writer")
public class WriterController extends KernelBaseController {

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
}

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
import com.huacainfo.ace.uf.model.PerResume;
import com.huacainfo.ace.uf.service.PerResumeService;
import com.huacainfo.ace.uf.vo.PerResumeVo;
import com.huacainfo.ace.uf.vo.PerResumeQVo;

@Controller
@RequestMapping("/perResume")
public class PerResumeController extends UfBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PerResumeService perResumeService;

	@RequestMapping(value = "/findPerResumeList.do")
	@ResponseBody
	public PageResult<PerResumeVo> findPerResumeList(PerResumeQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<PerResumeVo> rst = this.perResumeService
				.findPerResumeList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertPerResume.do")
	@ResponseBody
	public MessageResponse insertPerResume(String jsons) throws Exception {
		PerResume obj = JSON.parseObject(jsons, PerResume.class);
		return this.perResumeService
				.insertPerResume(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updatePerResume.do")
	@ResponseBody
	public MessageResponse updatePerResume(String jsons) throws Exception {
		PerResume obj = JSON.parseObject(jsons, PerResume.class);
		return this.perResumeService
				.updatePerResume(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectPerResumeByPrimaryKey.do")
	@ResponseBody
	public SingleResult<PerResumeVo> selectPerResumeByPrimaryKey(String id)
			throws Exception {
		return this.perResumeService.selectPerResumeByPrimaryKey(id);
	}

	@RequestMapping(value = "/deletePerResumeByPerResumeId.do")
	@ResponseBody
	public MessageResponse deletePerResumeByPerResumeId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.perResumeService.deletePerResumeByPerResumeId(id,
				this.getCurUserProp());
	}
}

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
import com.huacainfo.ace.uf.model.PerJob;
import com.huacainfo.ace.uf.service.PerJobService;
import com.huacainfo.ace.uf.vo.PerJobVo;
import com.huacainfo.ace.uf.vo.PerJobQVo;

@Controller
@RequestMapping("/perJob")
public class PerJobController extends UfBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PerJobService perJobService;

	@RequestMapping(value = "/findPerJobList.do")
	@ResponseBody
	public PageResult<PerJobVo> findPerJobList(PerJobQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<PerJobVo> rst = this.perJobService
				.findPerJobList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertPerJob.do")
	@ResponseBody
	public MessageResponse insertPerJob(String jsons) throws Exception {
		PerJob obj = JSON.parseObject(jsons, PerJob.class);
		return this.perJobService
				.insertPerJob(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updatePerJob.do")
	@ResponseBody
	public MessageResponse updatePerJob(String jsons) throws Exception {
		PerJob obj = JSON.parseObject(jsons, PerJob.class);
		return this.perJobService
				.updatePerJob(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectPerJobByPrimaryKey.do")
	@ResponseBody
	public SingleResult<PerJobVo> selectPerJobByPrimaryKey(String id)
			throws Exception {
		return this.perJobService.selectPerJobByPrimaryKey(id);
	}

	@RequestMapping(value = "/deletePerJobByPerJobId.do")
	@ResponseBody
	public MessageResponse deletePerJobByPerJobId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.perJobService.deletePerJobByPerJobId(id,
				this.getCurUserProp());
	}
}

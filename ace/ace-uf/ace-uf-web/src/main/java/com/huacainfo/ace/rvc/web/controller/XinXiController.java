package com.huacainfo.ace.rvc.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.uf.model.XinXi;
import com.huacainfo.ace.uf.service.XinXiService;
import com.huacainfo.ace.uf.vo.XinXiQVo;
import com.huacainfo.ace.uf.vo.XinXiVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/xinXi")
public class XinXiController extends UfBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private XinXiService xinXiService;

	@RequestMapping(value = "/findXinXiList.do")
	@ResponseBody
	public PageResult<XinXiVo> findXinXiList(XinXiQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<XinXiVo> rst = this.xinXiService
				.findXinXiList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertXinXi.do")
	@ResponseBody
	public MessageResponse insertXinXi(String jsons) throws Exception {
		XinXi obj = JSON.parseObject(jsons, XinXi.class);
		return this.xinXiService
				.insertXinXi(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateXinXi.do")
	@ResponseBody
	public MessageResponse updateXinXi(String jsons) throws Exception {
		XinXi obj = JSON.parseObject(jsons, XinXi.class);
		return this.xinXiService
				.updateXinXi(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectXinXiByPrimaryKey.do")
	@ResponseBody
	public SingleResult<XinXiVo> selectXinXiByPrimaryKey(String id)
			throws Exception {
		return this.xinXiService.selectXinXiByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteXinXiByXinXiId.do")
	@ResponseBody
	public MessageResponse deleteXinXiByXinXiId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.xinXiService.deleteXinXiByXinXiId(id,
				this.getCurUserProp());
	}
}

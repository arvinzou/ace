package com.huacainfo.ace.uf.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.uf.model.XuanChuan;
import com.huacainfo.ace.uf.service.XuanChuanService;
import com.huacainfo.ace.uf.vo.XuanChuanQVo;
import com.huacainfo.ace.uf.vo.XuanChuanVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/xuanChuan")
public class XuanChuanController extends UfBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private XuanChuanService xuanChuanService;

	@RequestMapping(value = "/findXuanChuanList.do")
	@ResponseBody
	public PageResult<XuanChuanVo> findXuanChuanList(XuanChuanQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<XuanChuanVo> rst = this.xuanChuanService
				.findXuanChuanList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertXuanChuan.do")
	@ResponseBody
	public MessageResponse insertXuanChuan(String jsons) throws Exception {
		XuanChuan obj = JSON.parseObject(jsons, XuanChuan.class);
		return this.xuanChuanService
				.insertXuanChuan(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateXuanChuan.do")
	@ResponseBody
	public MessageResponse updateXuanChuan(String jsons) throws Exception {
		XuanChuan obj = JSON.parseObject(jsons, XuanChuan.class);
		return this.xuanChuanService
				.updateXuanChuan(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectXuanChuanByPrimaryKey.do")
	@ResponseBody
	public SingleResult<XuanChuanVo> selectXuanChuanByPrimaryKey(String id)
			throws Exception {
		return this.xuanChuanService.selectXuanChuanByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteXuanChuanByXuanChuanId.do")
	@ResponseBody
	public MessageResponse deleteXuanChuanByXuanChuanId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.xuanChuanService.deleteXuanChuanByXuanChuanId(id,
				this.getCurUserProp());
	}
}

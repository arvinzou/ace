package com.huacainfo.ace.portal.web.controller;

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
import com.huacainfo.ace.portal.model.Province;
import com.huacainfo.ace.portal.service.ProvinceService;
import com.huacainfo.ace.portal.vo.ProvinceVo;
import com.huacainfo.ace.portal.vo.ProvinceQVo;

@Controller
@RequestMapping("/province")
public class ProvinceController extends PortalBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProvinceService provinceService;

	@RequestMapping(value = "/findProvinceList.do")
	@ResponseBody
	public PageResult<ProvinceVo> findProvinceList(ProvinceQVo condition,
			PageParamNoChangeSord page) throws Exception {
		if(CommonUtils.isBlank(condition.getParent_code())){
			condition.setParent_code(this.getCurUserProp().getAreaCode());
		}
		PageResult<ProvinceVo> rst = this.provinceService
				.findProvinceList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertProvince.do")
	@ResponseBody
	public MessageResponse insertProvince(String jsons) throws Exception {
		Province obj = JSON.parseObject(jsons, Province.class);
		return this.provinceService
				.insertProvince(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateProvince.do")
	@ResponseBody
	public MessageResponse updateProvince(String jsons) throws Exception {
		Province obj = JSON.parseObject(jsons, Province.class);
		return this.provinceService
				.updateProvince(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectProvinceByPrimaryKey.do")
	@ResponseBody
	public SingleResult<Province> selectProvinceByPrimaryKey(String id)
			throws Exception {
		return this.provinceService.selectProvinceByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteProvinceByProvinceId.do")
	@ResponseBody
	public MessageResponse deleteProvinceByProvinceId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.provinceService.deleteProvinceByProvinceId(id,
				this.getCurUserProp());
	}
}

package com.huacainfo.ace.operana.web.controller;

import com.alibaba.fastjson.JSONArray;
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
import com.huacainfo.ace.operana.model.Norm;
import com.huacainfo.ace.operana.service.NormService;
import com.huacainfo.ace.operana.vo.NormVo;
import com.huacainfo.ace.operana.vo.NormQVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/norm")
public class NormController extends OperanaBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NormService normService;

	@RequestMapping(value = "/findNormList.do")
	@ResponseBody
	public PageResult<NormVo> findNormList(NormQVo condition, PageParamNoChangeSord page) throws Exception {
		PageResult<NormVo> rst = this.normService.findNormList(condition, page.getStart(), page.getLimit(),
				page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}

		return rst;
	}

	@RequestMapping(value = "/insertNorm.do")
	@ResponseBody
	public MessageResponse insertNorm(String jsons) throws Exception {
		Norm obj = JSON.parseObject(jsons, Norm.class);
		return this.normService.insertNorm(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateNorm.do")
	@ResponseBody
	public MessageResponse updateNorm(String jsons) throws Exception {
		Norm obj = JSON.parseObject(jsons, Norm.class);
		return this.normService.updateNorm(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectNormByPrimaryKey.do")
	@ResponseBody
	public SingleResult<NormVo> selectNormByPrimaryKey(String id) throws Exception {
		return this.normService.selectNormByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteNormByNormId.do")
	@ResponseBody
	public MessageResponse deleteNormByNormId(String jsons) throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.normService.deleteNormByNormId(id, this.getCurUserProp());
	}
	@RequestMapping(value = "/selectAllNorm.do")
	@ResponseBody
	public List<Map<String, Object>> selectAllNorm(String topicId,String name) throws Exception {
		return this.normService.selectAllNorm(topicId,name);
	}

	@RequestMapping(value = "/selectNormByTopicId.do")
	@ResponseBody
	public Map<String, Object> selectNormByTopicId(String topicId) throws Exception{
		return this.normService.selectNormByTopicId(topicId);
	}

	@RequestMapping(value = "/updateSort.do")
	@ResponseBody
	public MessageResponse updateSort(String jsons)
			throws Exception {
		JSONArray jsonArray=JSON.parseArray(jsons);
		List<Map<String, Object>> list=	this.handleJSONArray(jsonArray);
		return this.normService.updateSort(list,
				this.getCurUserProp());
	}

	private  List<Map<String, Object>> handleJSONArray(JSONArray jsonArray){
		List list = new ArrayList();
		for (Object object : jsonArray) {
			JSONObject jsonObject = (JSONObject) object;
			Map map = new HashMap<String, Object>();
			for (Map.Entry entry : jsonObject.entrySet()) {
				if(entry.getValue() instanceof  JSONArray){
					map.put((String)entry.getKey(), handleJSONArray((JSONArray)entry.getValue()));
				}else{
					map.put((String)entry.getKey(), entry.getValue());
				}
			}
			list.add(map);
		}
		return list;
	}
}

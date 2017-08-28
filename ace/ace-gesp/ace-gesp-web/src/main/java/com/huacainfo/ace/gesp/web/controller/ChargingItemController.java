package com.huacainfo.ace.gesp.web.controller;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.model.ChargingItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.gesp.service.ChargingItemService;
import com.huacainfo.ace.gesp.vo.ChargingItemQVo;
import com.huacainfo.ace.gesp.vo.ChargingItemVo;

@Controller
@RequestMapping("/chargingItem")
public class ChargingItemController extends KernelBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ChargingItemService chargingItemService;

	@RequestMapping(value = "/findChargingItemList.do")
	@ResponseBody
	public PageResult<ChargingItemVo> findChargingItemList(ChargingItemQVo condition,
			PageParamNoChangeSord page) throws Exception {
		if(CommonUtils.isBlank(condition.getMemberCode())){
			String code = this.getCurUserProp().getCorpId();
			condition.setMemberCode(code);
		}
		PageResult<ChargingItemVo> rst = this.chargingItemService
				.findChargingItemList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	/**
	 * 
	 * @param jsons
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertChargingItem.do")
	@ResponseBody
	public MessageResponse insertChargingItem(String jsons) throws Exception {
		ChargingItem obj = JSON.parseObject(jsons, ChargingItem.class);
		return this.chargingItemService
				.insertChargingItem(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateChargingItem.do")
	@ResponseBody
	public MessageResponse updateChargingItem(String jsons) throws Exception {
		ChargingItem obj = JSON.parseObject(jsons, ChargingItem.class);
		return this.chargingItemService
				.updateChargingItem(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectChargingItemByPrimaryKey.do")
	@ResponseBody
	public SingleResult<ChargingItem> selectChargingItemByPrimaryKey(String id)
			throws Exception {
		return this.chargingItemService.selectChargingItemByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteChargingItemByChargingItemId.do")
	@ResponseBody
	public MessageResponse deleteChargingItemByChargingItemId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.chargingItemService.deleteChargingItemByChargingItemId(id,
				this.getCurUserProp());
	}
	
	@RequestMapping(value = "/selectListByDeptId.do")
	@ResponseBody
	public List<Map<String,Object>> selectListByDeptId(String deptId,String selected)
			throws Exception {
		if (CommonUtils.isBlank(deptId)) {
			deptId=this.getCurUserProp().getCorpId();
		}
		return this.chargingItemService.selectListByDeptId(deptId,selected).getValue();
	}
	
	@RequestMapping(value = "/getChargingItemTreeList.do")
	@ResponseBody
	public List<Tree> getChargingItemTreeList(String id,String flag)throws Exception {
		if(CommonUtils.isBlank(id)){
			id="0";
		}
		List<Tree> list=this.chargingItemService.selectChargingItemList(id,this.getCurUserProp(),flag);
		return list;
	}
}

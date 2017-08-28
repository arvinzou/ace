package com.huacainfo.ace.gesp.service;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.model.ChargingItem;
import com.huacainfo.ace.gesp.vo.ChargingItemVo;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.gesp.vo.ChargingItemQVo;

public interface ChargingItemService {
	
	public abstract PageResult<ChargingItemVo> findChargingItemList(ChargingItemQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertChargingItem(ChargingItem obj, UserProp userProp) throws Exception;
	public abstract MessageResponse updateChargingItem(ChargingItem obj,UserProp userProp) throws Exception;
	public abstract SingleResult<ChargingItem> selectChargingItemByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteChargingItemByChargingItemId(String id,UserProp userProp) throws Exception;
	public abstract ListResult<Map<String,Object>> selectListByDeptId(String deptId,String selected) throws Exception ;
	public abstract List<Tree>  selectChargingItemList(String id,UserProp userProp, String flag) throws Exception;
	
}

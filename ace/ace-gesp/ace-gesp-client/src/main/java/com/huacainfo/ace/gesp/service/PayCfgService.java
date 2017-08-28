package com.huacainfo.ace.gesp.service;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.vo.PayCfgQVo;
import com.huacainfo.ace.gesp.vo.PayCfgVo;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.gesp.model.PayCfg;

public interface PayCfgService {
	
	public abstract PageResult<PayCfgVo> findPayCfgList(PayCfgQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertPayCfg(PayCfg obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updatePayCfg(PayCfg obj,UserProp userProp) throws Exception;
	public abstract SingleResult<PayCfg> selectPayCfgByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deletePayCfgByPayCfgId(String id,UserProp userProp) throws Exception;
	public abstract List<PayCfg> selectPayCfgList(String memberCode) throws Exception;
	public abstract SingleResult<Map<String,Object>> getPayInfo(String memberCode,String memberLevelId,String chargingItemId,String deptId) throws Exception;
	
	/**
	 * 根据收费项目编号查询
	 * 
	 * (收费项目删除时，判断收费配置是否引用)
	 * @param chargingItemId 收费项目编号
	 * @param deptId 协会编号
	 * @return List<Map<String, Object>>
	 */
	public abstract List<Map<String, Object>> selectPayCfgByChargingItem(String chargingItemId, String deptId)throws Exception;
	
	/**
	 * 校验收费配置是否配置完成
	 * 
	 * (收费处理中已引用)
	 * 
	 * @param memberCode
	 * @param chargingItemId
	 * @return MessageResponse
	 */
	public abstract MessageResponse selectCount(String memberCode, String chargingItemId)throws Exception;
	
}

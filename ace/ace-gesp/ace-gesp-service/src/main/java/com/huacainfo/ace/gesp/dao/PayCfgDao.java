package com.huacainfo.ace.gesp.dao;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.vo.PayCfgQVo;
import com.huacainfo.ace.gesp.vo.PayCfgVo;
import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.gesp.model.PayCfg;

public interface PayCfgDao {
	int deleteByPrimaryKey(String id);

	int insert(PayCfg record);

	int insertSelective(PayCfg record);

	PayCfg selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(PayCfg record);

	int updateByPrimaryKey(PayCfg record);

	List<PayCfgVo> findList(@Param("condition") PayCfgQVo condition,
                            @Param("start") int start, @Param("limit") int limit,
                            @Param("orderBy") String orderBy);

	int findCount(@Param("condition") PayCfgQVo condition);

	/**
	 * 根据条件查询收费配置
	 * 
	 * 已引用(根据收费项目编号查询,添加修改收费配置)
	 * 
	 * @param record
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> isExit(PayCfg record);

	List<PayCfg> selectPayCfgList(String memberCode);

	PayCfg selectByCodeAndMemberCode(
			@Param("memberLevelId") String memberLevelId,
			@Param("memberCode") String memberCode,
			@Param("chargingItemId") String chargingItemId,@Param("deptId") String deptId);
	
	/**
	 * 根据收费项目编号查询
	 * 
	 * (收费项目删除时，判断收费配置是否引用)
	 * @param chargingItemId 收费项目编号
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> selectPayCfgByChargingItem(String chargingItemId);

	/**
	 * 根据协会编号和项目编号查询
	 * 
	 * 引用(校验收费配置是否配置完成方法)
	 * @param memberCode
	 * @param chargingItemId
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> selectByChargindItemAndMemberCode(@Param("memberCode") String memberCode, @Param("chargingItemId") String chargingItemId);
	
}
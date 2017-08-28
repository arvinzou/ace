package com.huacainfo.ace.gesp.dao;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.model.ChargingItem;
import com.huacainfo.ace.gesp.vo.ChargingItemVo;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.gesp.vo.ChargingItemQVo;

public interface ChargingItemDao {
    int deleteByPrimaryKey(String ChargingItemId);

    int insert(ChargingItem record);


    ChargingItem selectByPrimaryKey(String ChargingItemId);


    int updateByPrimaryKey(ChargingItem record);
    
    List<ChargingItemVo> findList(@Param("condition") ChargingItemQVo condition,
                                  @Param("start") int start, @Param("limit") int limit,
                                  @Param("orderBy") String orderBy);

	int findCount(@Param("condition") ChargingItemQVo condition);

	int isExit(ChargingItem record);
	
	List<Map<String,Object>> selectListByDeptId(String deptId);
	
	List<Map<String,Object>> selectChargingItemTreeList(@Param("pid") String pid,@Param("memberCode")String memberCode);

	List<Map<String,Object>> isExitItemType(ChargingItem o);
}
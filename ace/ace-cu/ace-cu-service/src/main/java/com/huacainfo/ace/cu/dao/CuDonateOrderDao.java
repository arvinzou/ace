package com.huacainfo.ace.cu.dao;

import com.huacainfo.ace.cu.model.CuDonateOrder;
import com.huacainfo.ace.cu.vo.CuDonateOrderQVo;
import com.huacainfo.ace.cu.vo.CuDonateOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CuDonateOrderDao {

    CuDonateOrder selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(CuDonateOrder record);

    int insertSelective(CuDonateOrder record);

    int updateByPrimaryKey(CuDonateOrder record);

    int updateByPrimaryKeySelective(CuDonateOrder record);

    CuDonateOrderVo selectVoByPrimaryKey(String id);

    List<CuDonateOrderVo> findList(@Param("condition") CuDonateOrderQVo condition,
                                   @Param("start") int start,
                                   @Param("limit") int limit,
                                   @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CuDonateOrderQVo condition);

    int isExit(CuDonateOrder record);

    Map<String, Object> findDonateReport(String orderId);
    
    /**
     * @description:获取弹幕数据
     */
	List<Map<String, Object>> bulletScreenData(@Param("start")int start, @Param("limit")int limit);
	
	/**
	 * @description:检查当天是否获取善行积分
	 */
	int checkDayPoint(@Param("projectId") String projectId, @Param("userId") String userId);
	
	/**
	 * @description:根据用户获取“日行一善”捐赠明细
	 */
	List<CuDonateOrderVo> getDayDonateDetails(@Param("projectId") String projectId, @Param("userId") String userId);
	
	/**
	 * @description:捐款总量
	 */
	Map<String, Object> getTotalMoney(@Param("projectId") String projectId);
	
	/**
	 * @description:总积分排行
	 */
	List<Map<String,Object>> getTotalPointsRank();
	
	/**
	 * @description:善行积分排行
	 */
	List<Map<String,Object>> getActionPointsRank();
	
	/**
	 * @description:善心积分排行
	 */
	List<Map<String,Object>> getHeartPointsRank();
}
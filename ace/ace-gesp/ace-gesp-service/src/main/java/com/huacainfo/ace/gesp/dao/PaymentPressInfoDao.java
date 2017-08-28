package com.huacainfo.ace.gesp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.gesp.model.PaymentPressInfo;
import com.huacainfo.ace.gesp.vo.PaymentPressInfoQVo;
import com.huacainfo.ace.gesp.vo.PaymentPressInfoVo;

public interface PaymentPressInfoDao {
    int deleteByPrimaryKey(String PaymentPressInfoId);

    int insert(PaymentPressInfo record);


    PaymentPressInfo selectByPrimaryKey(String PaymentPressInfoId);


    int updateByPrimaryKey(PaymentPressInfo record);
    
    List<PaymentPressInfoVo> findList(@Param("condition") PaymentPressInfoQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") PaymentPressInfoQVo condition);

	int isExit(PaymentPressInfo record);
	
	List<Map<String,Object>> selectListByDeptId(String deptId);
}
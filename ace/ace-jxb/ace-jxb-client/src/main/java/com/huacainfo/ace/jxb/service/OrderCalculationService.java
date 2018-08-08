package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.jxb.model.OrderCalculation;
import com.huacainfo.ace.jxb.vo.OrderCalculationQVo;

import java.util.List;

/**
 * @Auther: Arvin
 * @Date: 2018/8/7 11:43
 * @Description:
 */
public interface OrderCalculationService {

    List<OrderCalculation> findList(OrderCalculationQVo condition, int start, int limit, String orderBy);

    int insertOrderCalculation(String orderId, String businessId);


    void grant(OrderCalculation data) throws Exception;

    void compute(OrderCalculation data);

    int updateByPrimaryKeySelective(OrderCalculation data);
}

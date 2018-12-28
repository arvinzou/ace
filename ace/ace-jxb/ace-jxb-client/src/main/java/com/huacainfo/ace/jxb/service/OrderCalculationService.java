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

    /**
     * 订单确认后，奖励待发放列表
     *
     * @param start 0
     * @param limit 500
     * @return List<OrderCalculation>
     */
    List<OrderCalculation> findGrantList(int start, int limit);

    /**
     * 订单付款or确认后，计算待发放的奖励值
     *
     * @param start 0
     * @param limit 500
     * @return List<OrderCalculation>
     */
    List<OrderCalculation> findCpuList(int start, int limit);
}

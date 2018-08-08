package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.jxb.model.OrderCalculation;
import com.huacainfo.ace.jxb.vo.OrderCalculationQVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: Arvin
 * @Date: 2018/8/8 16:29
 * @Description:
 */
@Component("JobSchedule")
public class JobSchedule {
    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private OrderCalculationService orderCalculationService;

    /**
     * 每5分钟 计算一批订单
     */
//    @Scheduled(cron = " 0 0/5 * * * ?")
    public void computeSchedule() {
        OrderCalculationQVo condition = new OrderCalculationQVo();
        condition.setCpuTag("0");

        List<OrderCalculation> dataList = orderCalculationService.findList(condition, 0, 500, "");
        for (OrderCalculation data : dataList) {
            try {
                orderCalculationService.compute(data);
            } catch (Exception e) {
                logger.error("订单计算异常：{}", e);
                data.setRemark(e.getMessage());
                orderCalculationService.updateByPrimaryKeySelective(data);
            }
        }
    }

    /**
     * 每10分钟 发放一批奖励
     */
//    @Scheduled(cron = " 0 0/10 * * * ?")
    public void grantSchedule() {
        OrderCalculationQVo condition = new OrderCalculationQVo();
        condition.setGrantTag("0");

        List<OrderCalculation> dataList = orderCalculationService.findList(condition, 0, 500, "");
        for (OrderCalculation data : dataList) {
            try {
                orderCalculationService.grant(data);
            } catch (Exception e) {
                logger.error("订单发放异常：{}", e);
                data.setRemark(e.getMessage());
                orderCalculationService.updateByPrimaryKeySelective(data);
            }
        }
    }
}

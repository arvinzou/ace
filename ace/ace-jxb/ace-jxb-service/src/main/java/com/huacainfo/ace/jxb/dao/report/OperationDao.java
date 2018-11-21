package com.huacainfo.ace.jxb.dao.report;

import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/9/6 14:44
 * @Description:
 */
public interface OperationDao {
    /**
     * 本周总体营收情况
     */
    Map<String, Object> weekOperation();

    /**
     * 指定某天营收情况
     */
    Map<String, Object> dayOperation(String dateTime);

    /**
     * 指定时间的 注册用户数量，和付费用户数量
     *
     * @param dateTime yyyy 或者 yyyy-MM
     * @return Map<String, Object>
     */
    Map<String, Object> userData(String dateTime);

    /**
     * 指定时间的 订单总数、营收总额
     *
     * @param dateTime yyyy 或者 yyyy-MM
     * @return Map<String, Object>
     */
    Map<String, Object> turnoverData(String dateTime);
}

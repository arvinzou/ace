package com.huacainfo.ace.partyschool.service;

import com.huacainfo.ace.partyschool.model.AttResultVo;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2019/1/4 11:54
 * @Description:
 */
public interface ApiService {


    /**
     * 查询客户列表
     *
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> findCustomerList();


    /**
     * 查询卡号对应的考勤日志
     *
     * @param queryType   查询方式 ：
     *                    year - 年度数据查询
     *                    month - 月度数据查询
     *                    day  - 日期数据查询
     * @param cardNo      卡号 Z0000187
     * @param dateTimeStr 查询日期时间,包含年月（yyyy-mm-dd） demo: 2018-12-03
     * @return List<AttResultVo>
     */
    List<AttResultVo> findAttDataList(String queryType, String cardNo, String dateTimeStr);
}

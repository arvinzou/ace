package com.huacainfo.ace.partyschool.dao;


import com.huacainfo.ace.partyschool.model.AttResultVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2019/1/3 10:11
 * @Description:
 */
public interface ApiDao {

    /**
     * 查询客户列表
     *
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> findCustomerList();


    /**
     * 考勤数据
     *
     * @param tableName 查询表名称
     * @param cardNo    卡号
     * @param params    查询参数：
     *                  yearStr - 年度数据查询
     *                  monthStr - 月度数据查询
     *                  dayStr  - 日期数据查询
     * @return List<AttResultVo>
     */
    List<AttResultVo> findAttDataList(@Param("tableName") String tableName,
                                      @Param("cardNo") String cardNo,
                                      @Param("params") Map<String, String> params);
}

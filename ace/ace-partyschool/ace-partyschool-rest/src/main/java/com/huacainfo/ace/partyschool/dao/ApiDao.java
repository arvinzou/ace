package com.huacainfo.ace.partyschool.dao;


import com.huacainfo.ace.partyschool.model.AttResultVo;
import com.huacainfo.ace.partyschool.model.StudentFinVo;
import com.huacainfo.ace.partyschool.model.TeacherFinRsVo;
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
     * @param tableName 查询表名称 ATT_WATER_INFO_*
     * @param lCardNo   借阅证号
     * @param params    查询参数：
     *                  yearStr - 年度数据查询
     *                  monthStr - 月度数据查询
     *                  dayStr  - 日期数据查询
     * @return List<AttResultVo>
     */
    List<AttResultVo> findAttDataList(@Param("tableName") String tableName,
                                      @Param("lCardNo") String lCardNo,
                                      @Param("params") Map<String, String> params);

    /**
     * 查询教职工消费记录
     *
     * @param tableName 查询表名称 FIN_CUS_DEAL_HIS_*
     * @param lCardNo   借阅证号
     * @param startNum  分页开始
     * @param endNum    分页结束
     * @param params    查询参数：
     *                  yearStr - 年度数据查询
     *                  monthStr - 月度数据查询
     *                  dayStr  - 日期数据查询
     * @return List<TeacherFinRsVo>
     */
    List<TeacherFinRsVo> findTeacherFinDataList(@Param("tableName") String tableName,
                                                @Param("lCardNo") String lCardNo,
                                                @Param("startNum") int startNum,
                                                @Param("endNum") int endNum,
                                                @Param("params") Map<String, String> params);

    /**
     * 查询教职工余额
     *
     * @param lCardNo 借阅证号
     * @return TeacherFinRsVo
     */
    TeacherFinRsVo findTeacherBalance(String lCardNo);

    /**
     * 查询 学员 年分内刷卡累计次数
     *
     * @return int
     */
    int findStudentFinCount(@Param("sql") String sql);

    /**
     * 查询 学员 打卡记录
     *
     * @return findStudentFinList
     */
    List<StudentFinVo> findStudentFinList(@Param("lCardNo") String lCardNo,
                                          @Param("tableName") String tableName,
                                          @Param("startNum") int startNum,
                                          @Param("endNum") int endNum);
}

package com.huacainfo.ace.partyschool.service;

import com.huacainfo.ace.partyschool.model.AttResultVo;
import com.huacainfo.ace.partyschool.model.StudentFinVo;
import com.huacainfo.ace.partyschool.model.TeacherFinRsVo;

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
     * @param lCardNo     借阅证号 Z0000187
     * @param dateTimeStr 查询日期时间,包含年月（yyyy-mm-dd） demo: 2018-12-03
     * @return List<AttResultVo>
     */
    List<AttResultVo> findAttDataList(String queryType, String lCardNo, String dateTimeStr);

    /**
     * 查询教师消费记录
     *
     * @param dateTimeStr 日期时间，包含年月：yyyy-mm
     * @param lCardNo     借阅证号
     * @param startNum    分页开始
     * @param endNum      分页结束
     * @param startNum    分页开始位置
     * @param endNum      分页结束位置
     * @return List<TeacherFinRsVo>
     */
    List<TeacherFinRsVo> findTeacherFinDataList(String lCardNo,
                                                String dateTimeStr,
                                                int startNum,
                                                int endNum);

    /**
     * 查询教职工余额
     *
     * @param lCardNo 借阅证号
     * @return TeacherFinRsVo
     */
    TeacherFinRsVo findTeacherBalance(String lCardNo);

    /**
     * 查询 学员 消费打卡累计次数
     *
     * @param lCardNo 借阅证号
     * @return ResultResponse
     */
    int findStudentFinCount(String lCardNo, String year);

    /**
     * 查询 学员 打卡记录
     *
     * @param lCardNo     借阅证号
     * @param dateTimeStr 日期-年月
     * @param startNum    分页开始位置
     * @param endNum      分页结束位置
     * @return List<StudentFinVo>
     */
    List<StudentFinVo> findStudentFinList(String lCardNo,
                                          String dateTimeStr,
                                          int startNum,
                                          int endNum);

    /**
     * 查找借阅历史
     *
     * @return ResultResponse
     */
    List<Map<String, Object>> findBorrowList(String lCardNo);
}

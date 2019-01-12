package com.huacainfo.ace.partyschool.service.impl;

import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.partyschool.dao.ApiDao;
import com.huacainfo.ace.partyschool.model.AttResultVo;
import com.huacainfo.ace.partyschool.model.StudentFinVo;
import com.huacainfo.ace.partyschool.model.TeacherFinRsVo;
import com.huacainfo.ace.partyschool.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2019/1/4 11:54
 * @Description:
 */
@Service("/apiService")
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiDao aipDao;


    @Override
    public List<Map<String, Object>> findCustomerList() {
        return aipDao.findCustomerList();
    }

    /**
     * 查询卡号对应的考勤日志
     *
     * @param queryType   查询方式 ： year-年度查询, month-月度查询, day-日查询
     * @param lCardNo     借阅证号
     * @param dateTimeStr 查询日期时间,包含年月（yyyy-mm-dd） demo: 2018-12-03
     * @return List<AttResultVo>
     */
    @Override
    public List<AttResultVo> findAttDataList(String queryType, String lCardNo, String dateTimeStr) {
        String tableName = "ATT_WATER_INFO_" + dateTimeStr.substring(0, 7).replace("-", "_");
        Map<String, String> params = getDateStrMap(queryType, dateTimeStr);

        return aipDao.findAttDataList(tableName, lCardNo, params);
    }

    /**
     * 查询教师消费记录
     *
     * @param lCardNo  借阅证号
     * @param startNum 分页开始
     * @param endNum   分页结束
     * @param startNum 分页开始位置：
     * @param endNum   分页结束位置：
     * @return List<TeacherFinRsVo>
     */
    @Override
    public List<TeacherFinRsVo> findTeacherFinDataList(String lCardNo,
                                                       String dateTimeStr,
                                                       int startNum,
                                                       int endNum) {
        String queryType = "month";
        String tableName = "FIN_CUS_DEAL_HIS_" + dateTimeStr.substring(0, 7).replace("-", "_");
        Map<String, String> params = getDateStrMap(queryType, dateTimeStr);

        return aipDao.findTeacherFinDataList(tableName, lCardNo, startNum, endNum, params);
    }

    /**
     * 查询教职工余额
     *
     * @param lCardNo 借阅证号
     * @return TeacherFinRsVo
     */
    @Override
    public TeacherFinRsVo findTeacherBalance(String lCardNo) {

        return aipDao.findTeacherBalance(lCardNo);
    }

    /**
     * 查询 学员 消费打卡累计次数
     *
     * @param lCardNo 借阅证号
     * @return ResultResponse
     */
    @Override
    public int findStudentFinCount(String lCardNo, String year) {
        String tbNameTmpl = "ATT_WATER_INFO_";
        String iMonth;
        if (StringUtil.isEmpty(year)) {
            String nowDateTime = DateUtil.getNow();
            year = nowDateTime.substring(0, 4);
            iMonth = nowDateTime.substring(5, 7);
        } else {
            iMonth = "12";
        }
        String tbName;
        //执行语句
        StringBuilder sql = new StringBuilder();
        sql.append("select \n" +
                "sum(A.iCount) accCount\n" +
                "from (")
                .append("  select count(1) as iCount\n" +
                        "  from ATT_WATER_INFO_2018_12 t\n" +
                        "  left join TB_CUSTOMER_INFO c\n" +
                        "  on t.account_id = c.customerid\n" +
                        "  where c.customerdept = '00010003'\n" +
                        "  and c.customerindustryno = '" + lCardNo + "'");

        for (int i = 1; i < Integer.parseInt(iMonth); i++) {
            if (i < 10) {
                tbName = tbNameTmpl + year + "_0" + i;
            } else {
                tbName = tbNameTmpl + year + "_" + i;
            }
            sql.append("\n")
                    .append(" union all\n" +
                            " select count(1) as iCount\n" +
                            " from " + tbName + "  t\n" +
                            " left join TB_CUSTOMER_INFO c on t.account_id = c.customerid\n" +
                            " where c.customerdept = '00010003'\n" +
                            " and c.customerindustryno = '" + lCardNo + "'");
        }
        sql.append(") A");

        return aipDao.findStudentFinCount(sql.toString());
    }

    /**
     * 查询 学员 打卡记录
     *
     * @param lCardNo     借阅证号
     * @param dateTimeStr 日期-年月
     * @param startNum    分页开始位置
     * @param endNum      分页结束位置
     * @return List<StudentFinVo>
     */
    @Override
    public List<StudentFinVo> findStudentFinList(String lCardNo, String dateTimeStr, int startNum, int endNum) {
        String tbName = "ATT_WATER_INFO_" + dateTimeStr.substring(0, 7).replace("-", "_");
        return aipDao.findStudentFinList(lCardNo, tbName, startNum, endNum);
    }


    private Map<String, String> getDateStrMap(String queryType, String dateTimeStr) {
        Map<String, String> params = new HashMap<>();

        switch (queryType) {
            case "year":
                params.put("yearStr", dateTimeStr.substring(0, 4));
                break;
            case "month":
                params.put("monthStr", dateTimeStr.substring(0, 7));
                break;
            case "day":
                params.put("dayStr", dateTimeStr);
                break;
            default:
                break;
        }

        return params;
    }
}

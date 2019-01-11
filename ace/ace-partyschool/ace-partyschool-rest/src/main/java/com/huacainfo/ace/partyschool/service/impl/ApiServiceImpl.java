package com.huacainfo.ace.partyschool.service.impl;

import com.huacainfo.ace.partyschool.dao.ApiDao;
import com.huacainfo.ace.partyschool.model.AttResultVo;
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
     * @param cardNo      卡号
     * @param dateTimeStr 查询日期时间,包含年月（yyyy-mm-dd） demo: 2018-12-03
     * @return List<AttResultVo>
     */
    @Override
    public List<AttResultVo> findAttDataList(String queryType, String cardNo, String dateTimeStr) {
        String tableName = "ATT_WATER_INFO_" + dateTimeStr.substring(0, 7).replace("-", "_");

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

        return aipDao.findAttDataList(tableName, cardNo, params);
    }


}

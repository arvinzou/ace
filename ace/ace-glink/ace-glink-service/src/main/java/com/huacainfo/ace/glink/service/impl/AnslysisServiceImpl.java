package com.huacainfo.ace.glink.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.glink.dao.report.ReportDao;
import com.huacainfo.ace.glink.service.AnalysisService;
import com.huacainfo.ace.glink.dao.report.ReportDao;
import com.huacainfo.ace.glink.service.AnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("analysisService")
public class AnslysisServiceImpl implements AnalysisService {
    private static final Logger logger = LoggerFactory.getLogger(AnslysisServiceImpl.class);

    @Override
    public ListResult<Map<String, Object>> query(Map<String, Object> condition, String reportId) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<Map<String, Object>>();
        List<Map<String, Object>> p = new ArrayList<Map<String, Object>>();
        ReportDao dao = (ReportDao) SpringUtils.getBean(reportId);
        List<Map<String, Object>> list = dao.query(condition);
        for (Map<String, Object> e : list) {
            p.add(CommonUtils.sortMapByKey(e));
        }
        rst.setValue(p);
        return rst;
    }

}

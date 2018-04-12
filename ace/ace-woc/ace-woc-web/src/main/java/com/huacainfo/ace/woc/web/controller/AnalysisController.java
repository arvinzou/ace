package com.huacainfo.ace.woc.web.controller;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.woc.service.AnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author HuaCai003
 */
@Controller
@RequestMapping("/anslysis")
public class AnalysisController extends WocBaseController {
    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AnalysisService analysisService;

    @RequestMapping(value = "/query")
    @ResponseBody
    public ListResult<Map<String, Object>> query(String reportId) throws Exception {
        Map<String, Object> condition = getParams();
        return analysisService.query(condition, reportId, 0, 0);
    }


    /**
     * 查询 以下累计数据
     * 1.今日通行记录数
     * 2.今日违章记录数
     * 3.待审通行记录数
     * 4.待审案件数
     *
     * @return
     */
    @RequestMapping(value = "/queryCounts")
    @ResponseBody
    public Map<String, Object> queryCounts() {
        return analysisService.queryCounts(getCurUserProp());
    }


    /**
     * 今日通行记录折线图
     *
     * @return
     */
    @RequestMapping(value = "/tadayCounts")
    @ResponseBody
    public Map<String, Object> todayChart() {
        return analysisService.todayChart(getCurUserProp());
    }
}

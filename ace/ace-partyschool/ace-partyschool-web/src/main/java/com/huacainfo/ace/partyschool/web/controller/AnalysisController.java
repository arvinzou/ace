package com.huacainfo.ace.partyschool.web.controller;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.partyschool.service.AnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/anslysis")
public class AnalysisController extends BisBaseController {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(BisBaseController.class);
    @Autowired
    private AnalysisService analysisService;

    @RequestMapping(value = "/query")
    @ResponseBody
    public ListResult<Map<String, Object>> query(
            String reportId)
            throws Exception {
        Map<String, Object> condition = this.getParams();
        if (CommonUtils.isBlank(condition.get("deptId"))) {
            condition.put("deptId", this.getCurUserProp().getCorpId());
        }
        this.logger.info("condition ->{}", condition);
        return analysisService.query(condition, reportId);
    }
}

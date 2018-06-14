package com.huacainfo.ace.fop.web.controller;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.fop.service.AnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.Map;

@Controller
@RequestMapping("/anslysis")
public class AnalysisController extends BaseController implements Serializable {
    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AnalysisService analysisService;

    @RequestMapping(value = "/query.do")
    @ResponseBody
    public ListResult<Map<String, Object>> query(String reportId) throws Exception {
        Map<String, Object> condition = this.getParams();
        if (CommonUtils.isBlank(condition.get("deptId"))) {
            condition.put("deptId", this.getCurUserProp().getCorpId());
        }
        if (CommonUtils.isBlank(condition.get("areaCode"))) {
            condition.put("areaCode", this.getCurUserProp().getAreaCode());
        }
        return analysisService.query(condition, reportId, 0, 0);
    }


    @RequestMapping(value = "/portalCount")
    @ResponseBody
    public Map<String, Object> portalCount() throws Exception {
        return analysisService.portalCount();
    }


}

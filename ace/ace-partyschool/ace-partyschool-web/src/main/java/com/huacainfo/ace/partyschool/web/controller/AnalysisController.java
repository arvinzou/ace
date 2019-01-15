package com.huacainfo.ace.partyschool.web.controller;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.partyschool.service.AnalysisService;
import com.huacainfo.ace.partyschool.service.StudentService;
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


    @Autowired
    private StudentService studentService;



    @RequestMapping(value = "/query")
    @ResponseBody
    public ListResult<Map<String, Object>> query(
            String reportId)
            throws Exception {
        Map<String, Object> condition = this.getParams();
        String classId=studentService.getClassId(this.getCurUserProp()).getValue();
        condition.put("classId", classId);
        this.logger.info("condition ->{}", condition);
        return analysisService.query(condition, reportId);
    }
}

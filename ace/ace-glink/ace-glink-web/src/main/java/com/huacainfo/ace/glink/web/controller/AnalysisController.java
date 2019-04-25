package com.huacainfo.ace.glink.web.controller;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.glink.service.AnalysisService;
import com.huacainfo.ace.glink.service.PagePortalService;
import com.huacainfo.ace.glink.service.SeNodeService;
import com.huacainfo.ace.glink.vo.SeNodeMonitorVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/anslysis")
public class AnalysisController extends GLinkBaseController {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(AnalysisController.class);
    @Autowired
    private AnalysisService analysisService;
    @Autowired
    private PagePortalService pagePortalService;
    @Autowired
    private SeNodeService seNodeService;

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

    /**
     * 大屏展示数据接口 -1
     *
     * @return Map<String, Object>
     */
    @ResponseBody
    @RequestMapping(value = "/screenData")
    public Map<String, Object> screenLeftData() {
        Map<String, String> params = new HashMap<>();

        return pagePortalService.screenData(params);
    }


    /**
     * 大屏展示数据接口 -2
     *
     * @return Map<String, Object>
     */
    @ResponseBody
    @RequestMapping(value = "/screenNodeData")
    public SeNodeMonitorVo screenNodeData(String nodeId) {

        return seNodeService.findByNodeId(nodeId);
    }

    /**
     * 大屏展示数据接口 -3
     *
     * @return Map<String, Object>
     */
    @ResponseBody
    @RequestMapping(value = "/errorChart")
    public List errorChart() {

//        return seNodeService.findByNodeId(nodeId);

        return null;
    }

    /**
     * 大屏展示数据接口 -4
     *
     * @return Map<String, Object>
     */
    @ResponseBody
    @RequestMapping(value = "/errorList")
    public List errorList() {

//        return seNodeService.findByNodeId(nodeId);
        return null;
    }
}

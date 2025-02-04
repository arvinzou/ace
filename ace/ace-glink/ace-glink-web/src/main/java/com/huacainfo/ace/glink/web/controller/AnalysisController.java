package com.huacainfo.ace.glink.web.controller;

import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.glink.service.*;
import com.huacainfo.ace.glink.vo.LeBrokenLampQVo;
import com.huacainfo.ace.glink.vo.LeBrokenLampVo;
import com.huacainfo.ace.glink.vo.LeLampStatusVo;
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
    @Autowired
    private LeLampStatusService leLampStatusService;
    @Autowired
    private LeBrokenLampService leBrokenLampService;

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
     * @param year  yyyy
     * @param month MM
     * @return Map<String, Object>
     */
    @ResponseBody
    @RequestMapping(value = "/errorChart")
    public List<LeLampStatusVo> errorChart(String year, String month) {

        return leLampStatusService.getErrorChartData(year, month);
    }

    /**
     * 大屏展示数据接口 -4
     *
     * @return Map<String, Object>
     */
    @ResponseBody
    @RequestMapping(value = "/errorList")
    public PageResult<LeBrokenLampVo> errorList(LeBrokenLampQVo condition, PageParamNoChangeSord page) throws Exception {

        //
        PageResult<LeBrokenLampVo> rst =
                leBrokenLampService.findLeBrokenLampList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }
}

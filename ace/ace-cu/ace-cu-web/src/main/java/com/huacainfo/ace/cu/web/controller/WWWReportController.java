package com.huacainfo.ace.cu.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.cu.service.AnalysisService;
import com.huacainfo.ace.cu.service.CuDonateListService;
import com.huacainfo.ace.cu.service.CuProjectUseRecordService;
import com.huacainfo.ace.cu.vo.CuDonateListQVo;
import com.huacainfo.ace.cu.vo.CuDonateListVo;
import com.huacainfo.ace.cu.vo.CuProjectUseRecordQVo;
import com.huacainfo.ace.cu.vo.CuProjectUseRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/6/21 17:43
 * @Description:
 */
@RestController
@RequestMapping("/www/report")
public class WWWReportController extends CuBaseController {

    @Autowired
    private AnalysisService analysisService;
    @Autowired
    private CuDonateListService cuDonateListService;
    @Autowired
    private CuProjectUseRecordService cuProjectUseRecordService;

    /**
     * 财务公开数额统计
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/financeStatistics")
    @ResponseBody
    public ResultResponse financeStatistics() throws Exception {
        Map<String, Object> data = analysisService.financeStatistics();
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", data);
    }

    /**
     * 查询捐献记录
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findDonateList")
    @ResponseBody
    public ResultResponse findDonateList(int start, int limit, String orderBy) throws Exception {

        CuDonateListQVo condition = new CuDonateListQVo();
        PageResult<CuDonateListVo> list = cuDonateListService.findCuDonateListList(condition, start, limit, orderBy);

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", list);
    }

    /**
     * 查询捐献记录
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findUsedRecord")
    @ResponseBody
    public ResultResponse findUsedRecord(int start, int limit, String orderBy) throws Exception {

        CuProjectUseRecordQVo condition = new CuProjectUseRecordQVo();
        PageResult<CuProjectUseRecordVo> list = cuProjectUseRecordService.findCuProjectUseRecordList(condition,
                start, limit, orderBy);
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", list);
    }

    /**
     * 慈善榜单
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/donateRank")
    @ResponseBody
    public ResultResponse donateRank(int start, int limit, String orderBy) throws Exception {

        List<Map<String, Object>> list = analysisService.donateRank(start, limit, orderBy);
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", list);
    }
}

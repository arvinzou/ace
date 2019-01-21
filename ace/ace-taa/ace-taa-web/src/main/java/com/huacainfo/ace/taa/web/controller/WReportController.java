package com.huacainfo.ace.taa.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.taa.service.TraAccService;
import com.huacainfo.ace.taa.vo.TraAccQVo;
import com.huacainfo.ace.taa.vo.TraAccVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报表数据
 *
 * @Auther: Arvin
 * @Date: 2019/1/12 12:50
 * @Description:
 */
@RestController
@RequestMapping("/www/report")
public class WReportController {

    @Autowired
    private TraAccService traAccService;

    /**
     * 获取事故报表
     *
     * @param condition 条件查询
     * @param page      分页条件
     * @return PageResult<TraAccVo>
     * @throws Exception
     */
    @RequestMapping("/findTraAccList")
    public PageResult<TraAccVo> findTraAccList(TraAccQVo condition, PageParamNoChangeSord page) throws Exception {


        PageResult<TraAccVo> rst = traAccService.findTraAccList(condition,
                page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * 查询获取事故详情
     *
     * @param traAccId 事故ID
     * @return SingleResult<TraAccVo>
     * @throws Exception
     */
    @RequestMapping(value = "/selectTraAccInfo")
    public SingleResult<TraAccVo> selectTraAccInfo(String traAccId) throws Exception {
        return traAccService.selectTraAccByPrimaryKey(traAccId);
    }


    /**
     * 交通事故倒序表
     * 路段交通事故次数倒叙表 & 路段交通死亡人数倒叙表
     *
     * @param category  查询类目 occurTimes/deathNum
     * @param areaCode  行政区域编码
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return PageResult<Map<String, Object>>
     * @throws Exception
     */
    @RequestMapping(value = "/reverseReport")
    public PageResult<Map<String, Object>> reverseReport(String category,
                                                         String areaCode,
                                                         String startDate,
                                                         String endDate,
                                                         int start,
                                                         int limit) throws Exception {
        String orderBy = category.equals("occurTimes") ? "" : " v.deathNum DESC ";
        Map<String, Object> params = new HashMap<>();
        params.put("areaCode", areaCode);
        params.put("startDate", startDate);
        params.put("endDate", endDate);

        List<Map<String, Object>> list = traAccService.reverseReport(params, start, limit, orderBy);
        PageResult<Map<String, Object>> rst = new PageResult<>();
        rst.setRows(list);
        if (start <= 1) {
            rst.setTotal(list.size());
        }
        return rst;
    }


    /**
     * 事故死亡人数同期对比 报表
     *
     * @param areaCode  行政区划
     * @param roadManId 路长ID
     * @param year      查询年限，四位有效数值;默认为当前日期年限
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/contrastiveReport")
    public SingleResult<Map<String, Object>> contrastiveReport(String areaCode,
                                                               String roadManId,
                                                               String year) throws Exception {

        Map<String, String> params = new HashMap<>();
        params.put("areaCode", areaCode);
        params.put("year", year);
        params.put("roadManId", roadManId);

        Map<String, Object> data = traAccService.contrastiveReport(params);
        SingleResult<Map<String, Object>> rst = new SingleResult<>();
        rst.setValue(data);
        return rst;
    }


    /**
     * 掌上驾驶仓 - 综合查询
     *
     * @param areaCode    行政区划
     * @param dateTimeStr 查询年月;7位有效数据，默认当前年月
     * @return Map<String, Object>
     */
    @RequestMapping(value = "/multipleReport")
    public ResultResponse monthReport(String areaCode, String dateTimeStr) throws Exception {


        Map<String, Object> rst = traAccService.multipleReport(areaCode, dateTimeStr);
        return new ResultResponse(ResultCode.SUCCESS, "SUCCESS", rst);
    }
}


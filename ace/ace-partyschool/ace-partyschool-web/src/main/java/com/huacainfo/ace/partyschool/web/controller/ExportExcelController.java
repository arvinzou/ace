package com.huacainfo.ace.partyschool.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.plugins.excel.ExportExcelKit;
import com.huacainfo.ace.common.plugins.excel.ExportExcelWrapper;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.TimestampKit;
import com.huacainfo.ace.partyschool.service.AttRecordService;
import com.huacainfo.ace.partyschool.vo.AttRecordExcel;
import com.huacainfo.ace.partyschool.vo.AttRecordQVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ExportExcelController
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/3/22 14:52
 */
@Controller
@RequestMapping("/exportExcel/")
public class ExportExcelController extends BisBaseController {
    @Autowired
    private AttRecordService attRecordService;

    /**
     * 获取导出考勤数据
     *
     * @param condition 查询条件
     * @param response  httpResponse
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/exportAttRecord")
    public ResultResponse getExcel(AttRecordQVo condition, HttpServletResponse response) {
        String userType = condition.getUserType();
        if (StringUtil.isEmpty(userType)) {
            return new ResultResponse(ResultCode.FAIL, "请选择身份类型！");
        }
        Date startDate = DateUtil.getDate(condition.getStartDate(), DateUtil.DEFAULT_DATE_REGEX);
        Date endDate = DateUtil.getDate(condition.getEndDate(), DateUtil.DEFAULT_DATE_REGEX);
        if (startDate.compareTo(endDate) > 0) {
            return new ResultResponse(ResultCode.FAIL, "时间区间有误！");
        }
        //excel data
        List<AttRecordExcel> list;
        try {
            list = attRecordService.exportAttRecord(condition);
        } catch (CustomException e) {
            return new ResultResponse(ResultCode.FAIL, e.getMsg());
        }
        if (CollectionUtils.isEmpty(list)) {
            return new ResultResponse(ResultCode.FAIL, "查无数据");
        }

        // 准备数据
        String[] columnNames = {"姓名", "身份类型", "班次", "日期",
                "上午签到", "上午签退", "下午签到", "下午签退", "晚上签到"};
        String fileName = TimestampKit.getTimestamp10() + "";
        ExportExcelWrapper<AttRecordExcel> util = new ExportExcelWrapper<>();
        util.exportExcel(fileName, fileName, columnNames, list, response, ExportExcelKit.EXCEL_FILE_2003);

        return new ResultResponse(ResultCode.SUCCESS, "导出成功");
    }
}

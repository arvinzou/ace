package com.huacainfo.ace.partyschool.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.plugins.excel.ExportExcelKit;
import com.huacainfo.ace.common.plugins.excel.ExportExcelWrapper;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.TimestampKit;
import com.huacainfo.ace.partyschool.constant.CommConstant;
import com.huacainfo.ace.partyschool.service.AttRecordService;
import com.huacainfo.ace.partyschool.vo.AttRecordExport;
import com.huacainfo.ace.partyschool.vo.AttRecordQVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ExportExcelController
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/3/22 14:52
 */
@RestController
@RequestMapping("/exportExcel/")
public class ExportExcelController extends BisBaseController {
    /**
     * 最大查询天数
     */
    private static final int MAX_QRY_DAY = 7;

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
        if (CommConstant.STUDENT.equals(userType) && StringUtil.isEmpty(condition.getClsId())) {
            return new ResultResponse(ResultCode.FAIL, "请选择班次！");
        } else if (CommConstant.TEACHER.equals(userType)) {
            condition.setClsId("");
        }
        Date startDate = DateUtil.getDate(condition.getStartDate(), DateUtil.DEFAULT_DATE_REGEX);
        Date endDate = DateUtil.getDate(condition.getEndDate(), DateUtil.DEFAULT_DATE_REGEX);
        if (startDate.compareTo(endDate) > 0) {
            return new ResultResponse(ResultCode.FAIL, "时间区间有误！");
        }
        //excel data
        List<AttRecordExport> list;
        try {
            list = attRecordService.exportAttRecord(condition);
        } catch (CustomException e) {
            return new ResultResponse(ResultCode.FAIL, e.getMsg());
        }
        if (CollectionUtils.isEmpty(list)) {
            return new ResultResponse(ResultCode.FAIL, "查无数据");
        }
        // excel 表格
        String[] columnNames;
        if (CommConstant.STUDENT.equals(userType)) {
            columnNames = new String[]{"姓名", "身份类型", "班次", "日期",
                    "上午签到", "上午签退", "下午签到", "下午签退", "晚上签到"};
        } else {
            columnNames = new String[]{"姓名", "身份类型", "日期", "上午签到", "下午签退"};
        }
        String fileName = TimestampKit.getTimestamp10() + "";
        ExportExcelWrapper<AttRecordExport> util = new ExportExcelWrapper<>();
        util.exportExcel(fileName, fileName, columnNames, list, response, ExportExcelKit.EXCEL_FILE_2003);

        return new ResultResponse(ResultCode.SUCCESS, "导出成功");
    }

    /**
     * 教职工考勤报表
     *
     * @param queryDate 日期列表，多个日期用英文逗号隔开,最多支持7个；
     *                  2019/05/21,2019/05/22,2019/05/23,2019/05/24...
     * @return ResultResponse
     */
    @RequestMapping("/teacherReport")
    public ResultResponse teacherReport(String queryDate) {
        if (StringUtil.isEmpty(queryDate)) {
            return new ResultResponse(ResultCode.FAIL, "缺少查询日期");
        }
        String[] dateArray = queryDate.replace("/", "-").split(",");
        if (dateArray.length > MAX_QRY_DAY) {
            return new ResultResponse(ResultCode.FAIL, "‘查询日期’最多支持7天");
        }
        //数据集合
        List<Map<String, Object>> rst = attRecordService.teacherAttReport(dateArray);

        return new ResultResponse(ResultCode.SUCCESS, "导出成功", rst);
    }

    /**
     * 学员考勤报表
     *
     * @param queryDate  日期列表，多个日期用英文逗号隔开,最多支持7个；
     *                   2019/05/21,2019/05/22,2019/05/23,2019/05/24...
     * @param classId    班次ID
     * @param stuAttType 考勤类别;0-上课考勤，1-住宿考勤
     * @return ResultResponse
     */
    @RequestMapping("/studentReport")
    public ResultResponse studentReport(String classId, String stuAttType, String queryDate) {

        if (StringUtil.isEmpty(queryDate)) {
            return new ResultResponse(ResultCode.FAIL, "缺少查询日期");
        }
        if (StringUtil.isEmpty(stuAttType)) {
            return new ResultResponse(ResultCode.FAIL, "缺少考勤类别");
        }
        if (StringUtil.isEmpty(classId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少班次ID");
        }
        String[] dateArray = queryDate.replace("/", "-").split(",");
        if (dateArray.length > MAX_QRY_DAY) {
            return new ResultResponse(ResultCode.FAIL, "‘查询日期’最多支持7天");
        }
        //数据集合
        List<Map<String, Object>> rst = attRecordService.studentReport(classId, stuAttType, dateArray);

        return new ResultResponse(ResultCode.SUCCESS, "导出成功", rst);
    }
}

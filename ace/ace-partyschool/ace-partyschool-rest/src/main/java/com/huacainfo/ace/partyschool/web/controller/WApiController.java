package com.huacainfo.ace.partyschool.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.partyschool.model.AttResultVo;
import com.huacainfo.ace.partyschool.model.StudentFinVo;
import com.huacainfo.ace.partyschool.model.TeacherFinRsVo;
import com.huacainfo.ace.partyschool.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/12/28 16:30
 * @Description:
 */
@RestController
@RequestMapping("/www/api")
public class WApiController {

    @Autowired
    private ApiService apiService;

    /**
     * 查询卡号对应的考勤日志
     *
     * @param queryType   查询方式 ：
     *                    year - 年度数据查询
     *                    month - 月度数据查询
     *                    day  - 日期数据查询
     * @param lCardNo     借阅证号 Z0000187
     * @param dateTimeStr 查询日期时间,包含年月（yyyy-mm-dd） demo: 2018-12-03
     * @return List<AttResultVo>
     */
    @RequestMapping("/findAttDataList")
    public ResultResponse findAttDataList(String queryType, String lCardNo, String dateTimeStr) {
        if (!StringUtil.areNotEmpty(lCardNo, dateTimeStr)) {
            return new ResultResponse(ResultCode.FAIL, "缺少查询方式||查询卡号||日期");
        }

        List<AttResultVo> data = apiService.findAttDataList(queryType, lCardNo, dateTimeStr);
        return new ResultResponse(ResultCode.SUCCESS, "success", data);
    }

    /**
     * 查询卡号对应指定日期的考勤日志 --
     *
     * @param lCardNo     借阅证号 Z0000187
     * @param dateTimeStr 查询日期时间,包含年月（yyyy-mm-dd） demo: 2018-12-03
     * @return List<AttResultVo>
     */
    @RequestMapping("/findAttDataByDay")
    public ResultResponse findAttDataByDay(String lCardNo, String dateTimeStr) {
        if (!StringUtil.areNotEmpty(lCardNo, dateTimeStr)) {
            return new ResultResponse(ResultCode.FAIL, "缺少" + "查询卡号||日期");
        }

        List<AttResultVo> data = apiService.findAttDataList("day", lCardNo, dateTimeStr);
        Map<String, List<AttResultVo>> view = new HashMap<>();
        List<AttResultVo> am = new LinkedList<>();
        List<AttResultVo> pm = new LinkedList<>();
        List<AttResultVo> night = new LinkedList<>();
        view.put("am", am);
        view.put("pm", pm);
        view.put("night", night);
        String hour;
        int iHour;
        for (AttResultVo item : data) {
            if (item.getDealTime().length() == 19) {
                hour = item.getDealTime().substring(11, 13);
                iHour = Integer.parseInt(hour);
                if (iHour < 12) {//上午
                    am = view.get("am");
                    am.add(item);
                } else if (iHour >= 18) {//晚上
                    night = view.get("night");
                    night.add(item);
                } else {
                    pm = view.get("pm");
                    pm.add(item);
                }
            }
        }

        return new ResultResponse(ResultCode.SUCCESS, "success", view);
    }

    /**
     * 查询教师消费记录
     *
     * @param lCardNo     借阅证号
     * @param dateTimeStr 查询时间日期
     * @param startNum    分页开始位置
     * @param endNum      分页结束位置
     * @return
     */
    @RequestMapping("/findTeacherFinDataList")
    public ResultResponse findTeacherFinDataList(String lCardNo, String dateTimeStr,
                                                 String startNum, String endNum) {
        if (!StringUtil.areNotEmpty(lCardNo, dateTimeStr)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        startNum = StringUtil.isEmpty(startNum) ? "0" : startNum.trim().replace(" ", "");
        endNum = StringUtil.isEmpty(endNum) ? "5" : endNum.trim().replace(" ", "");

        List<TeacherFinRsVo> data = apiService.findTeacherFinDataList(lCardNo, dateTimeStr,
                Integer.parseInt(startNum), Integer.parseInt(endNum));


        return new ResultResponse(ResultCode.SUCCESS, "success", data);
    }

    /**
     * 查询 教职工 消费记录
     *
     * @param lCardNo 借阅证号
     * @return ResultResponse
     */
    @RequestMapping("/findTeacherBalance")
    public ResultResponse findTeacherBalance(String lCardNo) {
        if (!StringUtil.areNotEmpty(lCardNo)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return new ResultResponse(ResultCode.SUCCESS, "success", apiService.findTeacherBalance(lCardNo));
    }


    /**
     * 查询 学员 消费打卡累计次数
     *
     * @param lCardNo 借阅证号
     * @return ResultResponse
     */
    @RequestMapping("/findStudentFinCount")
    public ResultResponse findStudentFinCount(String lCardNo, String year) {
        if (!StringUtil.areNotEmpty(lCardNo)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        int i = apiService.findStudentFinCount(lCardNo, year);
        Map<String, Object> view = new HashMap<>();
        view.put("lCardNo", lCardNo);
        view.put("acctNum", i);

        return new ResultResponse(ResultCode.SUCCESS, "success", view);
    }


    /**
     * 查询 学员 打卡记录
     *
     * @param lCardNo     借阅证号
     * @param dateTimeStr 日期-年月
     * @param startNum    分页开始位置
     * @param endNum      分页结束位置
     * @return List<StudentFinVo>
     */
    @RequestMapping("/findStudentFinList")
    public ResultResponse findStudentFinList(String lCardNo, String dateTimeStr,
                                             String startNum, String endNum) {
        if (!StringUtil.areNotEmpty(lCardNo, dateTimeStr)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        startNum = StringUtil.isEmpty(startNum) ? "0" : startNum.trim().replace(" ", "");
        endNum = StringUtil.isEmpty(endNum) ? "5" : endNum.trim().replace(" ", "");

        List<StudentFinVo> view = apiService.findStudentFinList(lCardNo, dateTimeStr,
                Integer.parseInt(startNum), Integer.parseInt(endNum));


        return new ResultResponse(ResultCode.SUCCESS, "success", view);
    }


}

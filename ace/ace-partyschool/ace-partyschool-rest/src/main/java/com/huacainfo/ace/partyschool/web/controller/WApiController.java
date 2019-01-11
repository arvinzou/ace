package com.huacainfo.ace.partyschool.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.partyschool.model.AttResultVo;
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
     * @param cardNo      卡号 Z0000187
     * @param dateTimeStr 查询日期时间,包含年月（yyyy-mm-dd） demo: 2018-12-03
     * @return List<AttResultVo>
     */
    @RequestMapping("/findAttDataList")
    public ResultResponse findAttDataList(String queryType, String cardNo, String dateTimeStr) {
        if (!StringUtil.areNotEmpty(cardNo, dateTimeStr)) {
            return new ResultResponse(ResultCode.FAIL, "缺少查询方式||查询卡号||日期");
        }

        List<AttResultVo> data = apiService.findAttDataList(queryType, cardNo, dateTimeStr);
        return new ResultResponse(ResultCode.SUCCESS, "success", data);
    }

    /**
     * 查询卡号对应指定日期的考勤日志 --
     *
     * @param cardNo      卡号 Z0000187
     * @param dateTimeStr 查询日期时间,包含年月（yyyy-mm-dd） demo: 2018-12-03
     * @return List<AttResultVo>
     */
    @RequestMapping("/findAttDataByDay")
    public ResultResponse findAttDataByDay(String cardNo, String dateTimeStr) {
        if (!StringUtil.areNotEmpty(cardNo, dateTimeStr)) {
            return new ResultResponse(ResultCode.FAIL, "缺少" + "查询卡号||日期");
        }

        List<AttResultVo> data = apiService.findAttDataList("day", cardNo, dateTimeStr);
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


}

package com.huacainfo.ace.policeschool.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.policeschool.model.AttRecord;
import com.huacainfo.ace.policeschool.service.AttRecordService;
import com.huacainfo.ace.policeschool.vo.AttRecordQVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Arvin
 * @Date: 2019/2/21 15:23
 * @Description:
 */
@RestController
@RequestMapping("/www/att")
public class WAttRecordController extends BisBaseController {
    @Autowired
    private AttRecordService attRecordService;


    /**
     * 考勤登记
     *
     * @param json 考勤数据
     * @return ResultResponse
     */
    @RequestMapping("/record")
    public ResultResponse record(String json) throws Exception {
        UserProp userProp = getCurUserProp();
        if (userProp == null) {
            return new ResultResponse(ResultCode.FAIL, "未获取登录信息");
        }
        AttRecord data = JsonUtil.toObject(json, AttRecord.class);

        MessageResponse ms = null;//attRecordService.insertAttRecord(data, userProp);
        return new ResultResponse(ms);
    }

    /**
     * 查询登录用户考勤信息 -- 查询某一天的考勤数据
     *
     * @param dateTimeStr 日期字符串 ，包含年月日；示例： 2019-02-21
     * @return ResultResponse
     */
    @RequestMapping("/findList")
    public ResultResponse findList(AttRecordQVo condition,
                                   PageParamNoChangeSord page) throws Exception {
        UserProp userProp = getCurUserProp();
        if (userProp == null) {
            return new ResultResponse(ResultCode.FAIL, "未获取登录信息");
        }

        return attRecordService.findViewList(userProp, condition, page);

    }
}

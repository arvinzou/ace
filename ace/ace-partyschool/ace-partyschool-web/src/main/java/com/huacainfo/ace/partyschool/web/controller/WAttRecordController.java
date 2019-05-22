package com.huacainfo.ace.partyschool.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.partyschool.model.AttRecord;
import com.huacainfo.ace.partyschool.service.AttRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
        AttRecord data = null;
        try {
            data = JsonUtil.toObject(json, AttRecord.class);
        } catch (Exception e) {
            logger.error("[/www/att/record]json格式转换异常：{}", e);
        }
        if (StringUtil.isEmpty(json) || data == null) {
            return new ResultResponse(ResultCode.FAIL, "参数获取异常");
        }
        UserProp userProp = getCurUserProp();
        if (userProp == null) {
            return new ResultResponse(ResultCode.FAIL, "未获取登录信息");
        }


        MessageResponse ms = attRecordService.insertAttRecord(data, userProp);
        return new ResultResponse(ms);
    }

    /**
     * 获取考勤配置信息
     *
     * @return ResultResponse
     */
    @RequestMapping("/location")
    public ResultResponse location() throws Exception {
        UserProp userProp = getCurUserProp();
        if (userProp == null) {
            return new ResultResponse(ResultCode.FAIL, "未获取登录信息");
        }

        return attRecordService.location(userProp);
    }

    /**
     * 查询登录用户考勤信息 -- 查询某一天的考勤数据
     *
     * @param dateTimeStr 日期字符串 ，包含年月日；示例： 2019-02-21
     * @return ResultResponse
     */
    @RequestMapping("/findList")
    public ResultResponse findList(String dateTimeStr) {
        UserProp userProp = getCurUserProp();
        if (userProp == null) {
            return new ResultResponse(ResultCode.FAIL, "未获取登录信息");
        }

        return attRecordService.findList(userProp.getUserId(), dateTimeStr);
    }

    /**
     * 获取系统考勤数据配置
     *
     * @return ResultResponse
     */
    @RequestMapping("/getAttSrc")
    public ResultResponse getAttSrc() {

        Map<String, Object> config = attRecordService.getAttSrc();
        if (config == null) {
            return new ResultResponse(ResultCode.FAIL, "未配置考勤数据来源");
        }

        return new ResultResponse(ResultCode.SUCCESS, "SUCCESS", config);
    }
}

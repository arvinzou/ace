package com.huacainfo.ace.policeschool.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.plugins.qyplugin.pojo.DeviceRst;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.policeschool.service.QyCrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName YunKQController
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/3/19 16:38
 */
@RestController
@RequestMapping("/www/qy")
public class YunKQController {
    @Autowired
    private QyCrmService qyCrmService;

    /**
     * 获取接入设备列表
     *
     * @param sn 设备序列号 - 可选
     * @return ResultResponse
     */
    @RequestMapping("/getDevice")
    public ResultResponse getDevice(String sn) {
        sn = StringUtil.isEmpty(sn) ? "" : sn;
        DeviceRst rstObj = qyCrmService.getDevice(sn);
        if (rstObj.getStatus() != 1) {
            return new ResultResponse(ResultCode.FAIL, rstObj.getError());
        }
        return new ResultResponse(ResultCode.SUCCESS, "success", rstObj);
    }
}

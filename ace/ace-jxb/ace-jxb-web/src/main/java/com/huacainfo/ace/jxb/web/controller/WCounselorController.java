package com.huacainfo.ace.jxb.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.jxb.service.CounselorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Arvin
 * @Date: 2018/7/24 14:16
 * @Description:
 */
@RestController
@RequestMapping("/www/counselor")
public class WCounselorController extends JxbBaseController {
    @Autowired
    private CounselorService counselorService;


    /**
     * 咨询师"我"的账户信息
     *
     * @param counselorId 咨询师id
     * @return ResultResponse data=>Map
     */
    @RequestMapping("/accountInfo")
    public ResultResponse accountInfo(String counselorId) {
        if (StringUtil.isEmpty(counselorId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return counselorService.accountInfo(counselorId);
    }

}

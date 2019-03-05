package com.huacainfo.ace.portal.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.portal.service.SysInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName WSysInitController
 * @Description TODO
 * @Author HuaCai008
 * @Date 2019/3/5 9:00
 */
@RestController
@RequestMapping("/www/init")
public class WSysInitController extends PortalBaseController {

    @Autowired
    private SysInitService sysInitService;

    /**
     * 初始化新系统
     *
     * @param sysId   系统识别ID
     * @param sysName 系统名称
     * @param acct    管理员账号
     * @param pwd     管理员密码
     * @return ResultResponse 处理结果
     */
    @RequestMapping("/addNewSystem")
    public ResultResponse addNewSystem(String sysId, String sysName, String acct, String pwd) throws Exception {
        if (!StringUtil.areNotEmpty(sysId, sysName, acct, pwd)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        try {
            return sysInitService.addNewSystem(sysId, sysName, acct, pwd);
        } catch (CustomException e) {
            return new ResultResponse(ResultCode.FAIL, e.getMsg());
        }
    }
}

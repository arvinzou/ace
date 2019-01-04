package com.huacainfo.ace.partyschool.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.partyschool.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping("/hello")
    public String hello() {
        return "hello, it's me [party school]";
    }


    @RequestMapping("test")
    public ResultResponse test() {
        List data = apiService.findCustomerList();
        return new ResultResponse(ResultCode.SUCCESS, "success", data);
    }

}

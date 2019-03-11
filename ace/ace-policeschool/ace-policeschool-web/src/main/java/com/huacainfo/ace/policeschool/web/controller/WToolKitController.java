package com.huacainfo.ace.policeschool.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Arvin
 * @Date: 2018/12/28 16:30
 * @Description:
 */
@RestController
@RequestMapping("/www/toolKit")
public class WToolKitController extends BisBaseController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello, it's me [police school]";
    }
}

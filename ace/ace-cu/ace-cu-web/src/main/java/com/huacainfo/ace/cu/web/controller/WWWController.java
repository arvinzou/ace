package com.huacainfo.ace.cu.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/www")
public class WWWController extends CuBaseController {


    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.sendRedirect("http://localhost/cu/www/oauth2/redirect.do");
        return "success";
    }
}

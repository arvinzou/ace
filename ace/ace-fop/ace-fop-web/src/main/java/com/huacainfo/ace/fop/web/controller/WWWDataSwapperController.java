package com.huacainfo.ace.fop.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.fop.service.DataSwapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Arvin
 * @Date: 2018/6/20 16:07
 * @Description:
 */
@RestController
@RequestMapping("/www/dataSwapper")
public class WWWDataSwapperController extends FopBaseController {


    @Autowired
    private DataSwapperService dataSwapperService;


    /**
     * 数据交换统一查询
     *
     * @param keyword 查询关键字
     * @param type    查询类别 0-主体身份代码、1-统一社会信用代码、2-纳税人识别号、3-组织机构代码
     * @return map
     */
    @RequestMapping(value = "/search")
    public ResultResponse search(String keyword, int type) {
        if (CommonUtils.isEmpty(keyword)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return dataSwapperService.search(keyword, type);
    }
}


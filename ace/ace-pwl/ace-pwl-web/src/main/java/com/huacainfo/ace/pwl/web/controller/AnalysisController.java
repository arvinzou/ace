package com.huacainfo.ace.pwl.web.controller;

import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.pwl.model.Writing;
import com.huacainfo.ace.pwl.service.WritingService;
import com.huacainfo.ace.pwl.vo.WritingQVo;
import com.huacainfo.ace.pwl.vo.WritingVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/www/anslysis")
public class AnalysisController extends PwlBaseController {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WritingService writingService;


    @RequestMapping(value = "/query.do")
    @ResponseBody
    public PageResult<WritingVo> findWritingList(WritingQVo condition, String reportId, int page, int limit) throws Exception {
        PageResult<WritingVo> rst = this.writingService.handleWritingList(condition, reportId, page, limit);
        return rst;
    }

    @RequestMapping(value = "/updataLike.do")
    @ResponseBody
    public MessageResponse updatalike(Writing condition) throws Exception {
        return this.writingService.updatalike(condition);
    }
}

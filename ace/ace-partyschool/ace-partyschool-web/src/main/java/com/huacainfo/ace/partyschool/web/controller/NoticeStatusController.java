package com.huacainfo.ace.partyschool.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.partyschool.model.NoticeStatus;
import com.huacainfo.ace.partyschool.service.NoticeStatusService;
import com.huacainfo.ace.partyschool.vo.NoticeStatusVo;
import com.huacainfo.ace.partyschool.vo.NoticeStatusQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
@Controller
@RequestMapping("/noticeStatus")
/**
* @author: 陈晓克
* @version: 2019-01-12
* @Description:  TODO(公告通知)
*/
public class NoticeStatusController extends BisBaseController {


private static final long serialVersionUID = 1L;
Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
private NoticeStatusService noticeStatusService;

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version:2019-01-12
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.noticeStatusService.updateStatus(id, this.getCurUserProp());
    }
}

package com.huacainfo.ace.policeschool.web.controller;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.policeschool.service.NoticeStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/noticeStatus")
/**
 * @author: 陈晓克
 * @version: 2019-01-12
 * @Description: TODO(公告通知)
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


    /**
     * @throws
     * @Title:insertNoticeStatus
     * @Description: TODO(批量插入)
     * @param: @param id
     * @param: @param userIds
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-16
     */
    @RequestMapping(value = "/insertNoticeStatus")
    @ResponseBody
    public MessageResponse insertNoticeStatus(String id, String userIds, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(userIds)) {
            return new MessageResponse(1, "请选择发布对象！");
        }
        return this.noticeStatusService.insertNoticeStatus(id, userIds.split(","), this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getPushUsersList
     * @Description: TODO(获取已经发送人员列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ListResult<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2019-01-16
     */
    @RequestMapping(value = "/getPushUsersList")
    @ResponseBody
    public ListResult<Map<String, Object>> getPushUsersList(String id) throws Exception {
        return this.noticeStatusService.getPushUsersList(id);
    }
}

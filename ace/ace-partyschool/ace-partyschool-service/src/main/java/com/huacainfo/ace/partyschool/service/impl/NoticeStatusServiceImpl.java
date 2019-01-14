package com.huacainfo.ace.partyschool.service.impl;


import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.partyschool.dao.NoticeStatusDao;
import com.huacainfo.ace.partyschool.model.NoticeStatus;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.partyschool.service.NoticeStatusService;
import com.huacainfo.ace.partyschool.vo.NoticeStatusVo;
import com.huacainfo.ace.partyschool.vo.NoticeStatusQVo;
@Service("noticeStatusService")
/**
* @author: 陈晓克
* @version: 2019-01-12
* @Description:  TODO(公告通知)
*/
public class NoticeStatusServiceImpl implements NoticeStatusService {
Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
private NoticeStatusDao noticeStatusDao;
@Autowired
private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-12
     */
    @Override
    public MessageResponse updateStatus(String id, UserProp userProp) throws Exception {
        this.noticeStatusDao.updateStatus(id);
        this.dataBaseLogService.log("跟新状态", "公告通知", id, id, "公告通知", userProp);
        return new MessageResponse(0, "成功！");
    }

 }

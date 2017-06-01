package com.huacainfo.ace.luohua.service.impl;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.luohua.dao.FeedbackDao;
import com.huacainfo.ace.luohua.model.Feedback;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.luohua.service.FeedbackService;
import com.huacainfo.ace.luohua.vo.FeedbackVo;
import com.huacainfo.ace.luohua.vo.FeedbackQVo;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FeedbackDao feedbackDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    public PageResult<FeedbackVo> findFeedbackList(FeedbackQVo condition, int start,
                                                   int limit, String orderBy) throws Exception {
        PageResult<FeedbackVo> rst = new PageResult<FeedbackVo>();
        List<FeedbackVo> list = this.feedbackDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.feedbackDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    public MessageResponse insertFeedback(Feedback o, UserProp userProp)
            throws Exception {
        o.setId(UUID.randomUUID().toString());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "主题不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "分类不能为空！");
        }
        if (CommonUtils.isBlank(o.getDocText())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        int temp = this.feedbackDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "意见反馈名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("0");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.feedbackDao.insert(o);
        this.dataBaseLogService.log("添加意见反馈", "意见反馈", "", o.getTitle(),
                o.getTitle(), userProp);
        return new MessageResponse(0, "添加意见反馈完成！");
    }

    public MessageResponse updateFeedback(Feedback o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "主题不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "分类不能为空！");
        }
        if (CommonUtils.isBlank(o.getDocText())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.feedbackDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更意见反馈", "意见反馈", "", o.getTitle(),
                o.getTitle(), userProp);
        return new MessageResponse(0, "变更意见反馈完成！");
    }

    public SingleResult<Feedback> selectFeedbackByPrimaryKey(String id) throws Exception {
        SingleResult<Feedback> rst = new SingleResult<Feedback>();
        rst.setValue(this.feedbackDao.selectByPrimaryKey(id));
        return rst;
    }

    public MessageResponse deleteFeedbackByFeedbackId(String id,
                                                      UserProp userProp) throws Exception {
        this.feedbackDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除意见反馈", "意见反馈", String.valueOf(id),
                String.valueOf(id), "意见反馈", userProp);
        return new MessageResponse(0, "意见反馈删除完成！");
    }
}

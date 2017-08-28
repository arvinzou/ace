package com.huacainfo.ace.uf.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.huacainfo.ace.common.model.WxUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.uf.dao.ActivityCommentDao;
import com.huacainfo.ace.uf.model.ActivityComment;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.uf.service.ActivityCommentService;
import java.util.Map;
@Service("activityCommentService")
public class ActivityCommentServiceImpl implements ActivityCommentService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActivityCommentDao activityCommentDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public List<Map<String,Object>> selectListByActivityId(String id) throws Exception {
		return this.activityCommentDao.selectListByActivityId(id);
	}

	public MessageResponse insertActivityComment(ActivityComment o ,WxUser user) throws Exception {
		o.setId(UUID.randomUUID().toString());
		o.setUserId(user.getUnionId());
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getActivityId())) {
			return new MessageResponse(1, "活动主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getUserId())) {
			return new MessageResponse(1, "评论人不能为空！");
		}
		if (CommonUtils.isBlank(o.getDocText())) {
			return new MessageResponse(1, "评论内容不能为空！");
		}
		o.setCreateDate(new Date());
		this.activityCommentDao.insert(o);
		return new MessageResponse(0, "添加活动评论完成！");
	}


	public MessageResponse deleteActivityCommentByActivityCommentId(String id, UserProp userProp) throws Exception {
		this.activityCommentDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除活动评论", "活动评论", String.valueOf(id), String.valueOf(id), "活动评论", userProp);
		return new MessageResponse(0, "活动评论删除完成！");
	}
}

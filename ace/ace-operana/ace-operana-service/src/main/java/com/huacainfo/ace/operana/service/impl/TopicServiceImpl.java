package com.huacainfo.ace.operana.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.huacainfo.ace.operana.dao.MeetingDao;
import com.huacainfo.ace.operana.model.Meeting;
import com.huacainfo.ace.operana.model.TopicNorm;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.operana.dao.TopicDao;
import com.huacainfo.ace.operana.dao.TopicNormDao;
import com.huacainfo.ace.operana.model.Topic;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.operana.service.TopicService;
import com.huacainfo.ace.operana.vo.TopicVo;
import com.huacainfo.ace.operana.vo.TopicQVo;
@Service("topicService")
public class TopicServiceImpl implements TopicService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TopicDao topicDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Autowired
	private MeetingDao meetingDao;
	@Autowired
	private SqlSessionTemplate sqlSession;

	public PageResult<TopicVo> findTopicList(TopicQVo condition, int start, int limit, String orderBy)
			throws Exception {

		PageResult<TopicVo> rst = new PageResult<TopicVo>();
		List<TopicVo> list = this.topicDao.findList(condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.topicDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertTopic(Topic o, UserProp userProp) throws Exception {
		// o.setId(UUID.randomUUID().toString());
		o.setId(String.valueOf(new Date().getTime()));
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "议题编码不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "议题名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getCategory())) {
			return new MessageResponse(1, "分类不能为空！");
		}
		int temp = this.topicDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "议题名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		o.setPcode(CommonUtils.getPinYinHeadChar(o.getName()));
		this.topicDao.insert(o);
		this.dataBaseLogService.log("添加议题", "议题", "", o.getName(), o.getName(), userProp);
		return new MessageResponse(0, "添加议题完成！");
	}

	public MessageResponse updateTopic(Topic o, UserProp userProp) throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "议题编码不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "议题名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getCategory())) {
			return new MessageResponse(1, "分类不能为空！");
		}
		if (CommonUtils.isBlank(o.getStatus())) {
			return new MessageResponse(1, "状态不能为空！");
		}
		o.setPcode(CommonUtils.getPinYinHeadChar(o.getName()));
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.topicDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更议题", "议题", "", o.getName(), o.getName(), userProp);
		return new MessageResponse(0, "变更议题完成！");
	}

	public SingleResult<Topic> selectTopicByPrimaryKey(String id) throws Exception {
		SingleResult<Topic> rst = new SingleResult<Topic>();
		rst.setValue(this.topicDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteTopicByTopicId(String id, UserProp userProp) throws Exception {
		this.topicDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除议题", "议题", String.valueOf(id), String.valueOf(id), "议题", userProp);
		return new MessageResponse(0, "议题删除完成！");
	}
	public  MessageResponse insertTopicNorm(List<TopicNorm> list,String topicId,boolean del,UserProp userProp) throws Exception{
		if (CommonUtils.isBlank(topicId)) {
			return new MessageResponse(1, "议题编号不能为空！");
		}
		SqlSession session = this.sqlSession.getSqlSessionFactory()
				.openSession(ExecutorType.BATCH, false);
		try {
			TopicNormDao topicNormDao = session.getMapper(TopicNormDao.class);
			if(del){
				topicNormDao.deleteByTopicId(topicId);
			}
			int i=0;
			for (TopicNorm o : list) {
				o.setId(UUID.randomUUID().toString());
				o.setCreateDate(new Date());
				o.setStatus("1");
				o.setCreateUserName(userProp.getName());
				o.setCreateUserId(userProp.getUserId());
				topicNormDao.insert(o);
				i++;
				if(i%200==0){
					session.commit();
				}
			}
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.clearCache();
			session.close();
		}
		this.dataBaseLogService.log("议题指标配置", "议题", "", topicId, topicId, userProp);
		return new MessageResponse(0, "成功！");
	}
}

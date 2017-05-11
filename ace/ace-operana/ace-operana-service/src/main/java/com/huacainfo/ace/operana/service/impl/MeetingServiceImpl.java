package com.huacainfo.ace.operana.service.impl;

import java.util.*;

import com.huacainfo.ace.operana.dao.TopicNormDao;
import com.huacainfo.ace.operana.model.MeetingTopic;
import com.huacainfo.ace.operana.model.MeetingUser;
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
import com.huacainfo.ace.operana.dao.MeetingDao;
import com.huacainfo.ace.operana.dao.MeetingTopicDao;
import com.huacainfo.ace.operana.dao.MeetingUserDao;
import com.huacainfo.ace.operana.model.Meeting;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.operana.service.MeetingService;
import com.huacainfo.ace.operana.vo.MeetingVo;
import com.huacainfo.ace.operana.vo.MeetingQVo;
@Service("meetingService")
public class MeetingServiceImpl implements MeetingService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MeetingDao meetingDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	@Autowired
	private MeetingTopicDao meetingTopicDao;

	@Autowired
	private MeetingUserDao meetingUserDao;
	@Autowired
	private SqlSessionTemplate sqlSession;

	public PageResult<MeetingVo> findMeetingList(MeetingQVo condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<MeetingVo> rst = new PageResult<MeetingVo>();
		List<MeetingVo> list = this.meetingDao.findList(condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.meetingDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertMeeting(Meeting o, UserProp userProp) throws Exception {
		// o.setId(UUID.randomUUID().toString());
		o.setId(String.valueOf(new Date().getTime()));
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "会议编码不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "会议名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getMeetingDate())) {
			return new MessageResponse(1, "会议时间不能为空！");
		}
		if (CommonUtils.isBlank(o.getSite())) {
			return new MessageResponse(1, "会议地点不能为空！");
		}
		if (CommonUtils.isBlank(o.getDivision())) {
			return new MessageResponse(1, "会议部门不能为空！");
		}
		if (CommonUtils.isBlank(o.getOwner())) {
			return new MessageResponse(1, "主持人不能为空！");
		}
		int temp = this.meetingDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "会议名称重复！");
		}
		o.setPcode(CommonUtils.getPinYinHeadChar(o.getName()));
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.meetingDao.insert(o);
		this.dataBaseLogService.log("添加会议", "会议", "", o.getName(), o.getName(), userProp);
		return new MessageResponse(0, "添加会议完成！");
	}

	public MessageResponse updateMeeting(Meeting o, UserProp userProp) throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "会议编码不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "会议名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getMeetingDate())) {
			return new MessageResponse(1, "会议时间不能为空！");
		}
		if (CommonUtils.isBlank(o.getSite())) {
			return new MessageResponse(1, "会议地点不能为空！");
		}
		if (CommonUtils.isBlank(o.getDivision())) {
			return new MessageResponse(1, "会议部门不能为空！");
		}
		if (CommonUtils.isBlank(o.getOwner())) {
			return new MessageResponse(1, "主持人不能为空！");
		}
		o.setPcode(CommonUtils.getPinYinHeadChar(o.getName()));
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.meetingDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更会议", "会议", "", o.getName(), o.getName(), userProp);
		return new MessageResponse(0, "变更会议完成！");
	}

	public SingleResult<Meeting> selectMeetingByPrimaryKey(String id) throws Exception {
		SingleResult<Meeting> rst = new SingleResult<Meeting>();
		rst.setValue(this.meetingDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteMeetingByMeetingId(String id, UserProp userProp) throws Exception {
		this.meetingDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除会议", "会议", String.valueOf(id), String.valueOf(id), "会议", userProp);
		return new MessageResponse(0, "会议删除完成！");
	}

	public  List<Map<String,Object>> selectAllTopic(String meetingId,String name){
		List<Map<String,Object>> rst=new ArrayList<Map<String,Object>>();
		if(CommonUtils.isBlank(name)){
			List<Map<String,Object>> categorys=this.meetingDao.selectAllTopicCategory();
			for(Map<String,Object> o:categorys){
				Map<String,Object> e=new HashMap<String,Object>();
				e.put("category",o);
				List<Map<String,Object>> items=this.meetingDao.selectTopicByCategory((String)o.get("code"),meetingId);
				e.put("items",items);
				rst.add(e);
			}
		}else{
			Map<String,Object> e=new HashMap<String,Object>();
			Map<String,Object> o=new HashMap<String,Object>();
			o.put("code","01");
			o.put("name","默认");
			e.put("category",o);
			List<Map<String,Object>> items=this.meetingDao.selectTopicByName(name,meetingId);
			e.put("items",items);
			rst.add(e);
		}

		return rst;
	}

	public  Map<String, Object> selectTopicByMeetingId(String meetingId) throws Exception{
		Map<String, Object> rst=new HashMap<String, Object>();
		List<Map<String, Object>> data=this.meetingDao.selectTopicByMeetingId(meetingId);
		rst.put("data",data);
		return rst;
	}

	public  MessageResponse insertMeetingTopic(List<MeetingTopic> list, String meetingId,boolean del, UserProp userProp) throws Exception{
		if (CommonUtils.isBlank(meetingId)) {
			return new MessageResponse(1, "会议编号不能为空！");
		}
		SqlSession session = this.sqlSession.getSqlSessionFactory()
				.openSession(ExecutorType.BATCH, false);
		try {
			MeetingTopicDao meetingTopicDao = session.getMapper(MeetingTopicDao.class);
			if(del){
				meetingTopicDao.deleteByMeetingId(meetingId);
			}
			int i=0;
			for (MeetingTopic o : list) {
				o.setId(UUID.randomUUID().toString());
				o.setCreateDate(new Date());
				o.setStatus("1");
				o.setCreateUserName(userProp.getName());
				o.setCreateUserId(userProp.getUserId());
				meetingTopicDao.insert(o);
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
		this.dataBaseLogService.log("议题指标配置", "议题", "", meetingId, meetingId, userProp);
		return new MessageResponse(0, "成功！");
	}
	public  MessageResponse updateTopicOwner(String userId,String meetingId,String topicId, UserProp userProp)throws Exception{
		if (CommonUtils.isBlank(userId)) {
			return new MessageResponse(1, "负责人不能为空！");
		}
		if (CommonUtils.isBlank(meetingId)) {
			return new MessageResponse(1, "会议编码不能为空！");
		}
		if (CommonUtils.isBlank(topicId)) {
			return new MessageResponse(1, "议题编码不能为空！");
		}
		this.meetingTopicDao.updateTopicOwner(userId,meetingId,topicId);
		this.dataBaseLogService.log("议题负责人配置", "议题", "", meetingId, meetingId, userProp);
		return new MessageResponse(0, "成功！");
	}

	public  MessageResponse deleteByMeetingIdAndTopicId(String meetingId,String topicId, UserProp userProp)throws Exception{

		if (CommonUtils.isBlank(meetingId)) {
			return new MessageResponse(1, "会议编码不能为空！");
		}
		if (CommonUtils.isBlank(topicId)) {
			return new MessageResponse(1, "议题编码不能为空！");
		}
		this.meetingTopicDao.deleteByMeetingIdAndTopicId(meetingId,topicId);
		this.dataBaseLogService.log("删除议题", "议题", "", meetingId, meetingId, userProp);
		return new MessageResponse(0, "成功！");
	}
	/**
	 *
	 */

	public  List<Map<String,Object>> selectAllUserDeptId(String meetingId,String name){
		List<Map<String,Object>> rst=new ArrayList<Map<String,Object>>();
		if(CommonUtils.isBlank(name)){
			List<Map<String,Object>> categorys=this.meetingDao.selectAllUserDeptId();
			for(Map<String,Object> o:categorys){
				Map<String,Object> e=new HashMap<String,Object>();
				e.put("category",o);
				List<Map<String,Object>> items=this.meetingDao.selectUserByDeptId((String)o.get("code"),meetingId);
				e.put("items",items);
				rst.add(e);
			}
		}else{
			Map<String,Object> e=new HashMap<String,Object>();
			Map<String,Object> o=new HashMap<String,Object>();
			o.put("code","01");
			o.put("name","默认");
			e.put("category",o);
			List<Map<String,Object>> items=this.meetingDao.selectUserByName(name,meetingId);
			e.put("items",items);
			rst.add(e);
		}

		return rst;
	}

	public  Map<String, Object> selectUserByMeetingId(String meetingId) throws Exception{
		Map<String, Object> rst=new HashMap<String, Object>();
		List<Map<String, Object>> data=this.meetingDao.selectUserByMeetingId(meetingId);
		rst.put("data",data);
		return rst;
	}

	public  MessageResponse insertMeetingUser(List<MeetingUser> list, String meetingId, boolean del, UserProp userProp) throws Exception{
		if (CommonUtils.isBlank(meetingId)) {
			return new MessageResponse(1, "会议编号不能为空！");
		}
		SqlSession session = this.sqlSession.getSqlSessionFactory()
				.openSession(ExecutorType.BATCH, false);
		try {
			MeetingUserDao meetingUserDao = session.getMapper(MeetingUserDao.class);
			if(del){
				meetingUserDao.deleteByMeetingId(meetingId);
			}
			int i=0;
			for (MeetingUser o : list) {
				o.setId(UUID.randomUUID().toString());
				o.setCreateDate(new Date());
				o.setStatus("1");
				o.setCreateUserName(userProp.getName());
				o.setCreateUserId(userProp.getUserId());
				meetingUserDao.insert(o);
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
		this.dataBaseLogService.log("与会人员配置", "会议", "", meetingId, meetingId, userProp);
		return new MessageResponse(0, "成功！");
	}


	public  MessageResponse updateMandatory(String userId,String meetingId,String mandatory, UserProp userProp)throws Exception{
		this.meetingUserDao.updateMandatory(userId,meetingId,mandatory);
		this.dataBaseLogService.log("是否必须参与", "会议", "", userId, meetingId, userProp);
		return new MessageResponse(0, "成功！");

	}

	public  MessageResponse deleteByMeetingIdAndUserId(String meetingId,String userId, UserProp userProp)throws Exception{
		this.meetingUserDao.deleteByMeetingIdAndUserId(userId,meetingId);
		this.dataBaseLogService.log("删除与会人员", "会议", "", userId, meetingId, userProp);
		return new MessageResponse(0, "成功！");
	}


	public  MessageResponse updatePresent(String meetingId,List<Map<String,Object>> list, UserProp userProp)throws Exception{
		for(Map<String,Object> o:list){
			this.meetingUserDao.updatePresent((String) o.get("userId"),meetingId,(String) o.get("present"));
		}
		this.dataBaseLogService.log("签到", "会议", "", meetingId, meetingId, userProp);
		return new MessageResponse(0, "成功！");

	}


}

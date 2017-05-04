package com.huacainfo.ace.operana.web.controller;

import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.operana.model.MeetingTopic;
import com.huacainfo.ace.operana.model.MeetingUser;
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
import com.huacainfo.ace.operana.model.Meeting;
import com.huacainfo.ace.operana.service.MeetingService;
import com.huacainfo.ace.operana.vo.MeetingVo;
import com.huacainfo.ace.operana.vo.MeetingQVo;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/meeting")
public class MeetingController extends OperanaBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MeetingService meetingService;

	@RequestMapping(value = "/findMeetingList.do")
	@ResponseBody
	public PageResult<MeetingVo> findMeetingList(MeetingQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<MeetingVo> rst = this.meetingService
				.findMeetingList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertMeeting.do")
	@ResponseBody
	public MessageResponse insertMeeting(String jsons) throws Exception {
		Meeting obj = JSON.parseObject(jsons, Meeting.class);
		return this.meetingService
				.insertMeeting(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateMeeting.do")
	@ResponseBody
	public MessageResponse updateMeeting(String jsons) throws Exception {
		Meeting obj = JSON.parseObject(jsons, Meeting.class);
		return this.meetingService
				.updateMeeting(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectMeetingByPrimaryKey.do")
	@ResponseBody
	public SingleResult<Meeting> selectMeetingByPrimaryKey(String id)
			throws Exception {
		return this.meetingService.selectMeetingByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteMeetingByMeetingId.do")
	@ResponseBody
	public MessageResponse deleteMeetingByMeetingId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.meetingService.deleteMeetingByMeetingId(id,
				this.getCurUserProp());
	}


	@RequestMapping(value = "/selectAllTopic.do")
	@ResponseBody
	public List<Map<String, Object>> selectAllTopic(String meetingId,String name) throws Exception {
		return this.meetingService.selectAllTopic(meetingId,name);
	}

	@RequestMapping(value = "/selectTopicByMeetingId.do")
	@ResponseBody
	public Map<String, Object> selectTopicByMeetingId(String meetingId) throws Exception{
		return this.meetingService.selectTopicByMeetingId(meetingId);
	}

	@RequestMapping(value = "/insertMeetingTopic.do")
	@ResponseBody
	MessageResponse insertMeetingTopic(String jsons, String meetingId,String name) throws Exception {
		List<MeetingTopic> list = JSON.parseArray(jsons, MeetingTopic.class);
		boolean del=true;
		if(CommonUtils.isNotBlank(name)){
			del=false;
		}
		return this.meetingService.insertMeetingTopic(list, meetingId,del, this.getCurUserProp());
	}
	@RequestMapping(value = "/updateTopicOwner.do")
	@ResponseBody
	public  MessageResponse updateTopicOwner(String userId,String meetingId,String topicId) throws Exception{
		return this.meetingService.updateTopicOwner(userId,meetingId,topicId,this.getCurUserProp());
	}
	@RequestMapping(value = "/deleteByMeetingIdAndTopicId.do")
	@ResponseBody
	public  MessageResponse deleteByMeetingIdAndTopicId(String meetingId,String topicId) throws Exception{
		return this.meetingService.deleteByMeetingIdAndTopicId(meetingId,topicId,this.getCurUserProp());
	}
	/*
	* */


	@RequestMapping(value = "/selectAllUserDeptId.do")
	@ResponseBody
	public List<Map<String, Object>> selectAllUserDeptId(String meetingId,String name) throws Exception {
		return this.meetingService.selectAllUserDeptId(meetingId,name);
	}

	@RequestMapping(value = "/selectUserByMeetingId.do")
	@ResponseBody
	public Map<String, Object> selectUserByMeetingId(String meetingId) throws Exception{
		return this.meetingService.selectUserByMeetingId(meetingId);
	}

	@RequestMapping(value = "/insertMeetingUser.do")
	@ResponseBody
	MessageResponse insertMeetingUser(String jsons, String meetingId,String name) throws Exception {
		List<MeetingUser> list = JSON.parseArray(jsons, MeetingUser.class);
		boolean del=true;
		if(CommonUtils.isNotBlank(name)){
			del=false;
		}
		return this.meetingService.insertMeetingUser(list, meetingId,del, this.getCurUserProp());
	}
	@RequestMapping(value = "/updateMandatory.do")
	@ResponseBody
	public  MessageResponse updateMandatory(String userId,String meetingId,String mandatory) throws Exception{
		return this.meetingService.updateMandatory(userId,meetingId,mandatory,this.getCurUserProp());
	}
	@RequestMapping(value = "/deleteByMeetingIdAndUserId.do")
	@ResponseBody
	public  MessageResponse deleteByMeetingIdAndUserId(String meetingId,String userId) throws Exception{
		return this.meetingService.deleteByMeetingIdAndUserId(meetingId,userId,this.getCurUserProp());
	}

}

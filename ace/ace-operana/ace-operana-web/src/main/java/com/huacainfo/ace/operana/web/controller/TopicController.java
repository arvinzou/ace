package com.huacainfo.ace.operana.web.controller;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.operana.model.TopicNorm;
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
import com.huacainfo.ace.operana.model.Topic;
import com.huacainfo.ace.operana.service.TopicService;
import com.huacainfo.ace.operana.vo.TopicVo;
import com.huacainfo.ace.operana.vo.TopicQVo;

import java.util.List;

@Controller
@RequestMapping("/topic")
public class TopicController extends OperanaBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TopicService topicService;

	@RequestMapping(value = "/findTopicList.do")
	@ResponseBody
	public PageResult<TopicVo> findTopicList(TopicQVo condition, PageParamNoChangeSord page) throws Exception {
		PageResult<TopicVo> rst = this.topicService.findTopicList(condition, page.getStart(), page.getLimit(),
				page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}

		return rst;
	}

	@RequestMapping(value = "/insertTopic.do")
	@ResponseBody
	public MessageResponse insertTopic(String jsons) throws Exception {
		Topic obj = JSON.parseObject(jsons, Topic.class);
		return this.topicService.insertTopic(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateTopic.do")
	@ResponseBody
	public MessageResponse updateTopic(String jsons) throws Exception {
		Topic obj = JSON.parseObject(jsons, Topic.class);
		return this.topicService.updateTopic(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectTopicByPrimaryKey.do")
	@ResponseBody
	public SingleResult<Topic> selectTopicByPrimaryKey(String id) throws Exception {
		return this.topicService.selectTopicByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteTopicByTopicId.do")
	@ResponseBody
	public MessageResponse deleteTopicByTopicId(String jsons) throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.topicService.deleteTopicByTopicId(id, this.getCurUserProp());
	}
	@RequestMapping(value = "/insertTopicNorm.do")
	@ResponseBody
	MessageResponse insertTopicNorm(String jsons, String topicId,String name) throws Exception {
		List<TopicNorm> list = JSON.parseArray(jsons, TopicNorm.class);
		boolean del=true;
		if(CommonUtils.isNotBlank(name)){
			del=false;
		}
		return this.topicService.insertTopicNorm(list, topicId,del, this.getCurUserProp());
	}
}

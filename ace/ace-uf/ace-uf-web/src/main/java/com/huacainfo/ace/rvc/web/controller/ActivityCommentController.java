package com.huacainfo.ace.rvc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.uf.model.ActivityComment;
import com.huacainfo.ace.uf.service.ActivityCommentService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/activityComment")
public class ActivityCommentController extends UfBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActivityCommentService activityCommentService;

	@RequestMapping(value = "/www/selectListByActivityId.do")
	@ResponseBody
	public List<Map<String,Object>> selectListByActivityId(String id) throws Exception {
		return this.activityCommentService.selectListByActivityId(id);
	}

	@RequestMapping(value = "/www/insertActivityComment.do")
	@ResponseBody
	public MessageResponse insertActivityComment(ActivityComment obj) throws Exception {
		return this.activityCommentService.insertActivityComment(obj, this.getCurWxUser());
	}

	@RequestMapping(value = "/deleteActivityCommentByActivityCommentId.do")
	@ResponseBody
	public MessageResponse deleteActivityCommentByActivityCommentId(String jsons) throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.activityCommentService.deleteActivityCommentByActivityCommentId(id, this.getCurUserProp());
	}
}

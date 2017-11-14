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
import com.huacainfo.ace.uf.model.ActivityUser;
import com.huacainfo.ace.uf.service.ActivityUserService;


import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/activityUser")
public class ActivityUserController extends UfBaseController {
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActivityUserService activityUserService;

	@RequestMapping(value = "/selectListByActivityId.do")
	@ResponseBody
	public List<Map<String,Object>> selectListByActivityId(String id) throws Exception {
		return this.activityUserService.selectListByActivityId(id);
	}
	@RequestMapping(value = "/insertActivityUser.do")
	@ResponseBody
	public MessageResponse insertActivityUser(String text) throws Exception {
		List<ActivityUser> list=JSON.parseArray(text,ActivityUser.class);
		return this.activityUserService.insertActivityUser(list, this.getCurUserProp());
	}

	@RequestMapping(value = "/deleteActivityUserByActivityUserId.do")
	@ResponseBody
	public MessageResponse deleteActivityUserByActivityUserId(String jsons) throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.activityUserService.deleteActivityUserByActivityUserId(id, this.getCurUserProp());
	}
}

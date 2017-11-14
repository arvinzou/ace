package com.huacainfo.ace.uf.web.controller;

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
import com.huacainfo.ace.uf.model.Activity;
import com.huacainfo.ace.uf.service.ActivityService;
import com.huacainfo.ace.uf.vo.ActivityVo;
import com.huacainfo.ace.uf.vo.ActivityQVo;

@Controller
@RequestMapping("/activity")
public class ActivityController extends UfBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActivityService activityService;

	@RequestMapping(value = "/findActivityList.do")
	@ResponseBody
	public PageResult<ActivityVo> findActivityList(ActivityQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<ActivityVo> rst = this.activityService
				.findActivityList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertActivity.do")
	@ResponseBody
	public MessageResponse insertActivity(String jsons) throws Exception {
		Activity obj = JSON.parseObject(jsons, Activity.class);
		return this.activityService
				.insertActivity(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateActivity.do")
	@ResponseBody
	public MessageResponse updateActivity(String jsons) throws Exception {
		Activity obj = JSON.parseObject(jsons, Activity.class);
		return this.activityService
				.updateActivity(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectActivityByPrimaryKey.do")
	@ResponseBody
	public SingleResult<ActivityVo> selectActivityByPrimaryKey(String id)
			throws Exception {
		return this.activityService.selectActivityByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteActivityByActivityId.do")
	@ResponseBody
	public MessageResponse deleteActivityByActivityId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.activityService.deleteActivityByActivityId(id,
				this.getCurUserProp());
	}
}

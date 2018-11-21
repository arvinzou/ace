package com.huacainfo.ace.portal.web.controller;

import com.huacainfo.ace.common.result.ResultResponse;
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
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.portal.service.WxUserService;
import com.huacainfo.ace.portal.vo.WxUserVo;
import com.huacainfo.ace.portal.vo.WxUserQVo;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wxUser")
public class WxUserController extends PortalBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private WxUserService wxUserService;

	@RequestMapping(value = "/findWxUserList.do")
	@ResponseBody
	public PageResult<WxUserVo> findWxUserList(WxUserQVo condition, PageParamNoChangeSord page) throws Exception {
		PageResult<WxUserVo> rst = this.wxUserService.findWxUserList(condition, page.getStart(), page.getLimit(),
				page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}

		return rst;
	}

	@RequestMapping(value = "/insertWxUser.do")
	@ResponseBody
	public MessageResponse insertWxUser(String jsons) throws Exception {
		WxUser obj = JSON.parseObject(jsons, WxUser.class);
		return this.wxUserService.insertWxUser(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateWxUser.do")
	@ResponseBody
	public MessageResponse updateWxUser(String jsons) throws Exception {
		WxUser obj = JSON.parseObject(jsons, WxUser.class);
		return this.wxUserService.updateWxUser(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectWxUserByPrimaryKey.do")
	@ResponseBody
	public SingleResult<WxUser> selectWxUserByPrimaryKey(String id) throws Exception {
		return this.wxUserService.selectWxUserByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteWxUserByWxUserId.do")
	@ResponseBody
	public MessageResponse deleteWxUserByWxUserId(String jsons) throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.wxUserService.deleteWxUserByWxUserId(id, this.getCurUserProp());
	}
	@RequestMapping(value = "/updateRoleById.do")
	@ResponseBody
	public MessageResponse updateRoleById(String id, String role) throws Exception {
		return this.wxUserService.updateRoleById(id, role, this.getCurUserProp());
	}
	@RequestMapping(value = "/deleteRoleById.do")
	@ResponseBody
	public MessageResponse deleteRoleById(String id) throws Exception {
		return this.wxUserService.deleteRoleById(id, this.getCurUserProp());
	}
	@RequestMapping(value = "/selectWxUser.do")
	@ResponseBody
	public List<Map<String,Object>> selectWxUser()throws Exception{
		return this.wxUserService.selectWxUser(this.getParams());
	}

	@RequestMapping(value = "/getSysWxUsers.do")
	@ResponseBody
	public ResultResponse getSysWxUsers()throws Exception{
		return this.wxUserService.getSysWxUsers(this.getCurUserProp().getActiveSyId());
	}


}

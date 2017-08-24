package com.huacainfo.ace.portal.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.huacainfo.ace.common.model.PageParam;

import org.apache.log4j.Logger;
import com.huacainfo.ace.portal.model.Group;
import com.huacainfo.ace.portal.service.GroupService;
import com.huacainfo.ace.portal.vo.GroupQVo;
import com.huacainfo.ace.portal.vo.GroupVo;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.model.view.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.PageResult;
@Controller
@RequestMapping("/group")
public class GroupController extends PortalBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private GroupService groupService;
	@RequestMapping(value = "/findGroupList.do")
	@ResponseBody
	public PageResult<GroupVo> findGroupList(GroupQVo condition, PageParam page) throws Exception {
		condition.setSyid(this.getCurUserProp().getActiveSyId());
		PageResult<GroupVo> rst = this.groupService.findGroupList(condition, page.getStart(), page.getLimit(),
				page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}
	@RequestMapping(value = "/insertGroup.do")
	@ResponseBody
	public MessageResponse insertGroup(String jsons) throws Exception {
		return this.groupService.insertGroup(JSON.parseObject(jsons, Group.class), this.getCurUserProp());
	}

	@RequestMapping(value = "/updateGroup.do")
	@ResponseBody
	public MessageResponse updateGroup(String jsons) throws Exception {
		return this.groupService.updateGroup(JSON.parseObject(jsons, Group.class), this.getCurUserProp());
	}

	@RequestMapping(value = "/deleteGroupByGroupId.do")
	@ResponseBody
	public MessageResponse deleteGroupByGroupId(String jsons) throws Exception {
		return this.groupService.deleteGroupByGroupId(JSON.parseObject("jsons").getString("id"), this.getCurUserProp());
	}

	@RequestMapping(value = "/selectGroupDepTreeByPid.do")
	@ResponseBody
	public List<Tree> selectGroupDepTreeByPid(String pid) throws Exception {
		return this.groupService.selectGroupDepTreeByPid(pid);
	}
	@RequestMapping(value = "/selectGroupGradeTreeByPid.do")
	@ResponseBody
	public List<Tree> selectGroupGradeTreeByPid(String pid) throws Exception {
		return this.groupService.selectGroupGradeTreeByPid(pid);
	}
	@RequestMapping(value = "/selectGroupDiscriblineTreeByPid.do")
	@ResponseBody
	public List<Tree> selectGroupDiscriblineTreeByPid(String pid) throws Exception {
		return this.groupService.selectGroupDiscriblineTreeByPid(pid);
	}

	@RequestMapping(value = "/selectFreeGroupTreeRoot.do")
	@ResponseBody
	public List<Tree> selectFreeGroupTreeRoot() throws Exception {
		return this.groupService.selectFreeGroupTreeRoot(this.getCurUserProp().getActiveSyId());
	}
	@RequestMapping(value = "/selectFreeGroupTreeByPid.do")
	@ResponseBody
	public List<Tree> selectFreeGroupTreeByPid(String pid) throws Exception {
		return this.groupService.selectFreeGroupTreeByPid(pid);
	}
	@RequestMapping(value = "/selectFreeGroupUsersListByGorupId.do")
	@ResponseBody
	public MessageResponse selectFreeGroupUsersListByGorupId(String groupId) throws Exception {
		return this.groupService.selectFreeGroupUsersListByGorupId(groupId);
	}
	@RequestMapping(value = "/batchSaveGroupUsersByUserIds.do")
	@ResponseBody
	public MessageResponse batchSaveGroupUsersByUserIds(String jsons) throws Exception {
		JSONObject json=JSON.parseObject(jsons);
		Map<String, Object> o = new HashMap<String, Object>();
		o.put("groupId",json.getString("groupId"));
		List<String> user=new ArrayList<String>();
		JSONArray list=json.getJSONArray("list");
		for(Object e:list){
			user.add(String.valueOf(e));
		}
		return this.groupService.batchSaveGroupUsersByUserIds(o, this.getCurUserProp());
	}
}

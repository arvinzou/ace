package com.huacainfo.ace.iop.web.controller;

import org.apache.log4j.Logger;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.iop.model.EvTaskUsers;
import com.huacainfo.ace.iop.vo.EvTaskUsersQVo;
import com.huacainfo.ace.iop.service.EvTaskUsersService;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.iop.vo.EvTaskUsersVo;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/evTaskUsers")
public class EvTaskUsersController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private EvTaskUsersService evTaskUsersService;


	@RequestMapping(value = "/findEvTaskUsersList")
	@ResponseBody
	public PageResult<EvTaskUsersVo> findEvTaskUsersList(EvTaskUsersQVo condition, PageParamNoChangeSord page) throws Exception {
		PageResult<EvTaskUsersVo> rst = this.evTaskUsersService.findEvTaskUsersList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}

	@RequestMapping(value = "/insertEvTaskUsers")
	@ResponseBody
	public MessageResponse insertEvTaskUsers(String jsons) throws Exception{
		EvTaskUsers obj = JSON.parseObject(jsons, EvTaskUsers.class);
		return this.evTaskUsersService.insertEvTaskUsers(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateEvTaskUsers")
	@ResponseBody
	public MessageResponse updateEvTaskUsers(String jsons) throws Exception{
		EvTaskUsers obj = JSON.parseObject(jsons, EvTaskUsers.class);
		return this.evTaskUsersService.updateEvTaskUsers(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectEvTaskUsersByPrimaryKey")
	@ResponseBody
	public SingleResult<EvTaskUsersVo> selectEvTaskUsersByPrimaryKey(String id) throws Exception{
		return  this.evTaskUsersService.selectEvTaskUsersByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteEvTaskUsersByEvTaskUsersId")
	@ResponseBody
	public MessageResponse deleteEvTaskUsersByEvTaskUsersId(String jsons) throws Exception{
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.evTaskUsersService.deleteEvTaskUsersByEvTaskUsersId(id, this.getCurUserProp());
	}

	@RequestMapping(value = "/saveOrUpdateEvTaskUsers")
	@ResponseBody
	public MessageResponse saveOrUpdateTeamPrepare(String jsons) throws Exception{
		EvTaskUsers obj = JSON.parseObject(jsons, EvTaskUsers.class);
		return this.evTaskUsersService.saveOrUpdateEvTaskUsers(obj, this.getCurUserProp());
	}
	@RequestMapping(value = "/selectUserListByDeptId")
	@ResponseBody
	 public List<Map<String, Object>> selectUserListByDeptId(String evTaskId, int limit) throws Exception{
		return this.evTaskUsersService.selectUserListByDeptId(evTaskId,limit);
	 }
	@RequestMapping(value = "/updateForReset")
	@ResponseBody
	public  MessageResponse updateForReset(String id) throws Exception{
		return this.evTaskUsersService.updateForReset(id,this.getCurUserProp());
	}
	@RequestMapping(value = "/selectPrintUserListByDeptId")
	@ResponseBody
	 public List<Map<String, Object>> selectPrintUserListByDeptId(String evTaskId, int limit) throws Exception{
		return this.evTaskUsersService.selectPrintUserListByDeptId(evTaskId,limit);
	 }
}

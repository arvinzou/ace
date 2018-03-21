package com.huacainfo.ace.iop.web.controller;

import java.util.List;
import org.apache.log4j.Logger;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.iop.model.EvTask;
import com.huacainfo.ace.iop.vo.EvTaskQVo;
import com.huacainfo.ace.iop.service.EvTaskService;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.iop.vo.EvTaskVo;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.common.model.view.CheckTree;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
@Controller
@RequestMapping("/evTask")
public class EvTaskController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private EvTaskService evTaskService;

	@RequestMapping(value = "/findEvTaskList")
	@ResponseBody
	public PageResult<EvTaskVo> findEvTaskList(EvTaskQVo condition, PageParamNoChangeSord page) throws Exception {
		PageResult<EvTaskVo> rst = this.evTaskService.findEvTaskList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}

	@RequestMapping(value = "/insertEvTask")
	@ResponseBody
	public MessageResponse insertEvTask(String jsons) throws Exception{
		EvTask obj = JSON.parseObject(jsons, EvTask.class);
		return this.evTaskService.insertEvTask(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateEvTask")
	@ResponseBody
	public MessageResponse updateEvTask(String jsons) throws Exception{
		EvTask obj = JSON.parseObject(jsons, EvTask.class);
		return this.evTaskService.updateEvTask(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectEvTaskByPrimaryKey")
	@ResponseBody
	public SingleResult<EvTaskVo> selectEvTaskByPrimaryKey(String id) throws Exception{
		return  this.evTaskService.selectEvTaskByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteEvTaskByEvTaskId")
	@ResponseBody
	public MessageResponse deleteEvTaskByEvTaskId(String jsons) throws Exception{
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.evTaskService.deleteEvTaskByEvTaskId(id, this.getCurUserProp());
	}

	@RequestMapping(value = "/saveOrUpdateEvTask")
	@ResponseBody
	public MessageResponse saveOrUpdateTeamPrepare(String jsons) throws Exception{
		EvTask obj = JSON.parseObject(jsons, EvTask.class);
		return this.evTaskService.saveOrUpdateEvTask(obj, this.getCurUserProp());
	}
	
	@RequestMapping(value = "/updateByPrimaryKeySelective")
	@ResponseBody
	public  MessageResponse updateByPrimaryKeySelective(String jsons) throws Exception{
		EvTask obj = JSON.parseObject(jsons, EvTask.class);
		return this.evTaskService.updateByPrimaryKeySelective(obj, this.getCurUserProp());
	}
	@RequestMapping(value = "/findMyEvTaskList")
	@ResponseBody
	public  PageResult<EvTaskVo> findMyEvTaskList(EvTaskQVo condition) throws Exception{
		return  this.evTaskService.findMyEvTaskList(condition);
	}
	
	@RequestMapping(value = "/selectDepAndUsersTreeList")
	@ResponseBody
	public List<CheckTree> selectDepAndUsersTreeList(String id,String taskId)throws Exception {
		List<CheckTree>	list=this.evTaskService.selectDepAndUsersTreeList(id,taskId);
		return list;
	}

	@RequestMapping(value = "/selectVoteClassesListByDeptId")
	@ResponseBody
	public  List<Map<String, Object>> selectVoteClassesListByDeptId() throws Exception{
		return this.evTaskService.selectVoteClassesListByDeptId(this.getParams());
	}
}

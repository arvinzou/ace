package com.huacainfo.ace.gesp.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.huacainfo.ace.gesp.service.BsSmsTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.model.PageParam;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.StringUtils;
import com.huacainfo.ace.gesp.model.BsSmsTask;
import com.huacainfo.ace.gesp.model.Sms;
import com.huacainfo.ace.gesp.service.SmsService;

@Controller
@RequestMapping("/smsTask")
public class SmsTaskController extends KernelBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BsSmsTaskService mainService;
	
	@Autowired
	private SmsService smsService;
	
	 
	 
	 
	@RequestMapping(value = "/findList.do")
	@ResponseBody
	public PageResult<Map<String, Object>> findList(PageParam page,HttpServletRequest request) throws Exception {
		
		Map<String, Object> condition=  getParamMap(request);
		try {
			 
			condition.put("departmentId",this.getCurUserProp().getCorpId());
			PageResult<Map<String, Object>> rst = this.mainService.findList(
					condition, page.getStart(), page.getLimit(), "sendTime desc");
			if (rst.getTotal() == 0) {
				rst.setTotal(page.getTotalRecord());
			}
			return rst;
			 
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		 
	}
	
	
	@RequestMapping(value = "/findListForSendSMS.do")
	@ResponseBody
	public PageResult<Map<String, Object>> findListForSendSMS(PageParam page,HttpServletRequest request) throws Exception {
		
		Map<String, Object> condition=  getParamMap(request);
		try {
			 
			//condition.put("departmentId",this.getCurUserProp().getCorpId());
			PageResult<Map<String, Object>> rst = this.mainService.findListForSendSMS(
					condition, page.getStart(), page.getLimit(), "");
			if (rst.getTotal() == 0) {
				rst.setTotal(page.getTotalRecord());
			}
			return rst;
			 
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		 
	}
	
	@RequestMapping(value = "/findListByMember.do")
	@ResponseBody
	public ListResult<Map<String, Object>> findListByMember(HttpServletRequest request)
			throws Exception {
		Map<String, Object> condition = this.getParamMap(request);
		condition.put("parent_department_id", this.getCurUserProp().getCorpId());
		return this.mainService.findListByMember(condition ,  "");
	}
	
	
	 
	@RequestMapping(value = "/insert.do")
	@ResponseBody
	public MessageResponse insert(BsSmsTask obj,HttpServletRequest request) throws Exception {
		//Sms obj = JSON.parseObject(jsons, Sms.class);
		String taskId=UUID.randomUUID().toString();
		obj.setId(taskId);
		obj.setCreateTime(new Date());
		obj.setCreateUserId(this.getCurUserProp().getUserId());
		obj.setDepartmentId(this.getCurUserProp().getCorpId());
		obj.setSendTime(new Date());
		obj.setIsEnable("1");
		
		String templateId=request.getParameter("template");
		
		String tels=request.getParameter("tels");
		if(!StringUtils.isNullOrWhiteSpace(tels))
		{
			String[] telArray = tels.split(",");
			List<Sms> list=new ArrayList<Sms>();
			for (String mobile : telArray) {
				Sms sms=new Sms();
				sms.setId(UUID.randomUUID().toString());
				sms.setBussId(taskId);
				sms.setSendStatus("0");
				sms.setTemplateCode(templateId);
				sms.setMobile(mobile);
				sms.setContent(obj.getContent());
				sms.setSendTime(new Date());
				sms.setCreateDate(new Date());
				sms.setCreateUserId(this.getCurUserProp().getUserId());
				list.add(sms);
				//.insert(obj, this.getCurUserProp()); 
			}
			smsService.insertBatch( list, this.getCurUserProp());
		}
		MessageResponse insert = this.mainService.insert(obj, this.getCurUserProp());
		return insert;
	}
	 
	@RequestMapping(value = "/update.do")
	@ResponseBody
	public MessageResponse update(String jsons,HttpServletRequest request) throws Exception {
		//BsSmsTask obj = JSON.parseObject(jsons, BsSmsTask.class);
		String id = request.getParameter("id");
		SingleResult<Map<String, Object>> selectByPrimaryKey = this.mainService.selectByPrimaryKey(id);
		Map<String, Object> value = selectByPrimaryKey.getValue();
		if(value!=null&&value.size()>0)
		{
			BsSmsTask m=new BsSmsTask();
			String taskId=UUID.randomUUID().toString();
			m.setId(taskId);
			String name_org=value.get("name").toString();
			 
			m.setName(value.get("name").toString()+"-"+CommonUtils.formatDate(new Date()));
			m.setContent(value.get("content").toString());
			m.setCreateTime(new Date());
			m.setCreateUserId(this.getCurUserProp().getUserId());
			m.setSendTime(new Date());
			m.setIsEnable("1");
			m.setDepartmentId(this.getCurUserProp().getCorpId());
			this.mainService.insert(m, this.getCurUserProp());
			 
			smsService.insertSelectiveByBussId(id,taskId);
		}
		
		//return this.mainService.update(obj, this.getCurUserProp());
		return new MessageResponse(0,"重发成功!");
	}
	 
	 
	 
	@RequestMapping(value = "/selectByPrimaryKey.do")
	@ResponseBody
	public SingleResult<Map<String, Object>> selectByPrimaryKey(String id,HttpServletRequest request)
			throws Exception {
		return this.mainService.selectByPrimaryKey(id);
	}
	 
	@RequestMapping(value = "/deleteById.do")
	@ResponseBody
	public MessageResponse deleteById(String jsons,String KeyValue,HttpServletRequest request)
			throws Exception {
		
		String[] array = KeyValue.split(",");
		for (String id : array) {
			 BsSmsTask o=new BsSmsTask();
			 o.setId(id);
			 o.setIsEnable("0");
			this.mainService.updateSelective(o, this.getCurUserProp()); 
		}
		return new MessageResponse(0,"删除成功!");
		 
	}
	 
	@RequestMapping(value = "/saveOrUpdate.do")
	@ResponseBody
	public MessageResponse saveOrUpdate(String jsons,HttpServletRequest request)
			throws Exception {
		BsSmsTask obj = JSON.parseObject(jsons, BsSmsTask.class);
		if(obj.getId()==null||obj.getId().trim().length()==0)
		{
			return this.mainService.insert(obj, this.getCurUserProp());
		}else
		{
			return this.mainService.update(obj, this.getCurUserProp());
		}
	}
	 
	@RequestMapping(value = "/findListTop.do")
	@ResponseBody
	public ListResult<Map<String, Object>> findListTop(HttpServletRequest request)
			throws Exception {
		Map<String, Object> condition = this.getParamMap(request);
		return this.mainService.findListTop(condition ,  "");
	}
	
	
	 
}

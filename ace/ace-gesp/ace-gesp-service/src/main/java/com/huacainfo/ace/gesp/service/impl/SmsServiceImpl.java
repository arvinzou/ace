package com.huacainfo.ace.gesp.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.gesp.dao.SmsMapper;
import com.huacainfo.ace.gesp.model.Sms;
import com.huacainfo.ace.gesp.service.SmsService;
import com.huacainfo.ace.portal.service.DataBaseLogService;

@Service ("smsService")
public class SmsServiceImpl   implements SmsService{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private SmsMapper daoMapper;
	 
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	
	 
	
	@Override
	public PageResult<Map<String, Object>> findList(Map<String, Object> condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<Map<String, Object>> rst = new PageResult<Map<String, Object>>();
		
		List<Map<String, Object>> list =this.daoMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.daoMapper.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	@Override
	public MessageResponse insert(Sms o, UserProp userProp) throws Exception {
		int effectCount=this.daoMapper.insert(o);
		return new MessageResponse(0, "添加完成！");
	}

	@Override
	public MessageResponse update(Sms o, UserProp userProp) throws Exception {
		this.daoMapper.update(o);
		return new MessageResponse(0, "更新完成！");
	}
	
	@Override
	public MessageResponse insertSelective(Sms o, UserProp userProp) throws Exception {
		int effectCount=this.daoMapper.insertSelective(o);
		return new MessageResponse(0, "添加完成！");
	}

	@Override
	public MessageResponse updateSelective(Sms o, UserProp userProp) throws Exception {
		int effectCount=this.daoMapper.updateSelective(o);
		return new MessageResponse(0, "更新完成！");
	}

	@Override
	public SingleResult<Map<String, Object>> selectByPrimaryKey(String id) throws Exception {
		SingleResult<Map<String, Object>> rst = new SingleResult<Map<String, Object>>();
		rst.setValue(this.daoMapper.selectByPrimaryKey(id));
		return rst;
	}

	@Override
	public MessageResponse deleteById(String id, UserProp userProp) throws Exception {
		MessageResponse rst = new MessageResponse(0,"删除成功！");
		this.daoMapper.deleteByPrimaryKey(id);
 		return rst;
	}

	@Override
	public ListResult<Map<String, Object>> findListTop(Map<String, Object> condition, String orderBy) throws Exception {
		ListResult<Map<String, Object>> rst = new ListResult<Map<String, Object>>();
		List<Map<String, Object>> list = this.daoMapper.findListTop(condition,orderBy);
		rst.setValue(list);
		return rst;
	}

	@Override
	public MessageResponse insertBatch(List<Sms> list, UserProp curUserProp) {
		 for (Sms sms : list) {
			this.daoMapper.insert(sms);
		}
		return new  MessageResponse(0,"添加成功!");
	}

	@Override
	public MessageResponse insertSelectiveByBussId(String oldTaskId, String newTaskId) {
		this.daoMapper.insertSelectiveByBussId(oldTaskId,newTaskId);
		return new  MessageResponse(0,"添加成功!");
	}
	 
	 
	
}

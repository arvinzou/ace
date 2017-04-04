package com.huacainfo.ace.workflow.service.impl.field;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.workflow.dao.field.ActFormDateboxDao;
import com.huacainfo.ace.workflow.model.field.ActFormDatebox;
import com.huacainfo.ace.workflow.service.ActFormFieldService;

@Service("datebox")
public class DateboxServiceImpl implements ActFormFieldService {
	@Autowired
	private ActFormDateboxDao actFormDateboxDao;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public MessageResponse saveOrUpdateActFormField(String jsons,String formId, UserProp userProp)
			throws Exception {
		JSONObject obj = JSON.parseObject(jsons);
		ActFormDatebox o = JSON.parseObject(obj.get("cfg").toString(), ActFormDatebox.class);
		o.setFormId(formId);
		o.setId(obj.getString("id"));
		int t=this.actFormDateboxDao.isExit(o);
		if(t>0){
			return this.updateActFormField(o, userProp);
		}else{
			return this.insertActFormField(o, userProp);
		}
	}
	private MessageResponse insertActFormField(ActFormDatebox o, UserProp userProp)
			throws Exception {
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getCorpName());
		o.setCreateUserId(userProp.getUserId());
		this.actFormDateboxDao.insert(o);
		return new MessageResponse(0, "添加组件类型完成！");
	}

	private MessageResponse updateActFormField(ActFormDatebox o, UserProp userProp)
			throws Exception {
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		o.setStatus("1");
		this.actFormDateboxDao.updateByPrimaryKey(o);
		return new MessageResponse(0, "更新组件类型完成！");
	}

	public Map<String,Object> selectActFormFieldByPrimaryKey(String id)
			throws Exception {
		return this.actFormDateboxDao.selectByPrimaryKey(id);
	}

	public MessageResponse deleteActFormFieldByActFormFieldId(String id,
			UserProp userProp) throws Exception {
		this.actFormDateboxDao.deleteByPrimaryKey(id);
		return new MessageResponse(0, "组件类型删除完成！");
	}

}

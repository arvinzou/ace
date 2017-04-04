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
import com.huacainfo.ace.workflow.dao.field.ActFormFilesboxDao;
import com.huacainfo.ace.workflow.model.field.ActFormFilesbox;
import com.huacainfo.ace.workflow.service.ActFormFieldService;

@Service("filesbox")
public class FilesboxServiceImpl implements ActFormFieldService {
	@Autowired
	private ActFormFilesboxDao actFormFilesboxDao;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public MessageResponse saveOrUpdateActFormField(String jsons,String formId, UserProp userProp)
			throws Exception {
		JSONObject obj = JSON.parseObject(jsons);
		ActFormFilesbox o = JSON.parseObject(obj.get("cfg").toString(), ActFormFilesbox.class);
		o.setFormId(formId);
		o.setId(obj.getString("id"));
		int t=this.actFormFilesboxDao.isExit(o);
		if(t>0){
			return this.updateActFormField(o, userProp);
		}else{
			return this.insertActFormField(o, userProp);
		}
	}
	private MessageResponse insertActFormField(ActFormFilesbox o, UserProp userProp)
			throws Exception {
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getCorpName());
		o.setCreateUserId(userProp.getUserId());
		this.actFormFilesboxDao.insert(o);
		return new MessageResponse(0, "添加组件类型完成！");
	}

	private MessageResponse updateActFormField(ActFormFilesbox o, UserProp userProp)
			throws Exception {
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		o.setStatus("1");
		this.actFormFilesboxDao.updateByPrimaryKey(o);
		return new MessageResponse(0, "更新组件类型完成！");
	}

	public Map<String,Object> selectActFormFieldByPrimaryKey(String id)
			throws Exception {
		return this.actFormFilesboxDao.selectByPrimaryKey(id);
	}

	public MessageResponse deleteActFormFieldByActFormFieldId(String id,
			UserProp userProp) throws Exception {
		this.actFormFilesboxDao.deleteByPrimaryKey(id);
		return new MessageResponse(0, "组件类型删除完成！");
	}

}

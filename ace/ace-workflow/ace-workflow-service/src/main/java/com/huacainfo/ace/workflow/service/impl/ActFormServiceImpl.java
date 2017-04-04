package com.huacainfo.ace.workflow.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.workflow.dao.ActFormDataDao;
import com.huacainfo.ace.workflow.dao.ActFormItemsDao;
import com.huacainfo.ace.workflow.dao.field.ActFormComboboxDao;
import com.huacainfo.ace.workflow.model.ActFormData;
import com.huacainfo.ace.workflow.model.ActFormItems;
import com.huacainfo.ace.workflow.service.ActFormFieldService;
import com.huacainfo.ace.workflow.service.ActFormService;

@Service("actFormService")
public class ActFormServiceImpl implements ActFormService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActFormItemsDao actFormItemsDao;

	@Autowired
	private ActFormDataDao actFormDataDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	private ActFormFieldService actFormFieldService;
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Autowired
	private ActFormComboboxDao actFormComboboxDao;

	public MessageResponse saveOrUpdateActForm(String jsons, UserProp userProp)
			throws Exception {
		this.logger.info(jsons);
		JSONObject o = JSON.parseObject(jsons);
		JSONArray list = o.getJSONArray("list");
		for (Object obj : list) {
			JSONObject field = (JSONObject) obj;
			actFormFieldService = (ActFormFieldService) SpringUtils
					.getBean(field.getString("fieldType"));
			actFormFieldService.saveOrUpdateActFormField(field.toJSONString(),
					o.getString("formId"), userProp);

			ActFormItems afi = JSON.parseObject(field.toJSONString(),
					ActFormItems.class);
			afi.setFieldId(field.getString("id"));
			afi.setFormId(o.getString("formId"));
			afi.setLabel(field.getJSONObject("cfg").getString("label"));
			afi.setDataType(field.getJSONObject("cfg").getString("dataType"));
			afi.setRequired(field.getJSONObject("cfg").getString("required"));
			afi.setDictId(field.getJSONObject("cfg").getString("dictId"));
			afi.setCreateDate(new Date());
			afi.setStatus("1");
			afi.setCreateUserName(userProp.getCorpName());
			afi.setCreateUserId(userProp.getUserId());
			afi.setLastModifyDate(new Date());
			afi.setLastModifyUserName(userProp.getName());
			afi.setLastModifyUserId(userProp.getUserId());
			int t = this.actFormItemsDao.isExit(afi);
			if (t > 0) {
				this.actFormItemsDao.updateByPrimaryKey(afi);
			} else {
				this.actFormItemsDao.insert(afi);
			}

		}
		return new MessageResponse(0, "保存完成！");
	}

	public MessageResponse deleteActFormFieldByActFormFieldId(String id,
			String fieldType, UserProp userProp) throws Exception {
		actFormFieldService = (ActFormFieldService) SpringUtils
				.getBean(fieldType);
		this.actFormItemsDao.deleteByPrimaryKey(id);
		return actFormFieldService.deleteActFormFieldByActFormFieldId(id,
				userProp);
	}

	public ListResult<Map<String, Object>> selectDefFormByFormId(String formId,
			UserProp userProp) throws Exception {
		ListResult<Map<String, Object>> rst = new ListResult<Map<String, Object>>();
		List<Map<String, Object>> list = this.actFormItemsDao
				.selectListByFormId(formId);
		for (Map<String, Object> o : list) {
			actFormFieldService = (ActFormFieldService) SpringUtils
					.getBean((String) o.get("fieldType"));
			Map<String, Object> cfg = actFormFieldService
					.selectActFormFieldByPrimaryKey((String) o.get("id"));
			cfg.put("dataType", o.get("dataType"));
			o.put("cfg", cfg);
			rst.getValue().add(o);
		}
		return rst;
	}

	public MessageResponse saveOrUpdateFormData(Map<String, Object> params,
			String appId, String instId, String formId, String taskId,
			UserProp userProp) throws Exception {

		for (String fieldId : params.keySet()) {
			ActFormData o = new ActFormData();
			o.setCreateDate(new Date());
			o.setStatus("1");
			o.setCreateUserName(userProp.getCorpName());
			o.setCreateUserId(userProp.getUserId());
			o.setAppId(appId);
			o.setInstId(instId);
			o.setFormId(formId);
			o.setTaskId(taskId);
			o.setFieldId(fieldId);
			o.setId(UUID.randomUUID().toString());
			if (!fieldId.equals("processDefinitionId")) {
				ActFormItems obj = actFormItemsDao.selectByPrimaryKey(fieldId);
				if (obj != null&&params.get(fieldId)!=null) {
					if (obj.getDataType().equals("string")||obj.getDataType().equals("file")) {
						o.setTextValue((String) params.get(fieldId));
					}
					if (obj.getDataType().equals("long")) {
						o.setLongValue(Long.valueOf((String) params.get(fieldId)));
					}
					if (obj.getDataType().equals("double")) {
						o.setDoubleValue(Double.valueOf((String)params.get(fieldId)));
					}
					if (obj.getDataType().equals("datetime")) {
						String formatStr = "yyyy-MM-dd";
						if (((String) params.get(fieldId)).length() > 10) {
							formatStr = "yyyy-MM-dd HH:mm";
						}
						o.setDateValue(CommonUtils.parseString2Date(
								(String) params.get(fieldId), formatStr));
					}
					actFormDataDao.insert(o);
				}

			}
			
		}
		return new MessageResponse(0, "保存完成！");
	}
	
	public ListResult<Map<String, Object>> selectListByInstId(String instId,
			UserProp userProp) throws Exception {
		ListResult<Map<String, Object>> rst = new ListResult<Map<String, Object>>();
		List<Map<String, Object>> list = this.actFormDataDao.selectListByInstId(instId);
		rst.setValue(list);
		return rst;
	}
	public ListResult<Map<String, Object>> selectModelByAppId(String appId,
			UserProp userProp) throws Exception {
		ListResult<Map<String, Object>> rst = new ListResult<Map<String, Object>>();
		List<Map<String, Object>> list = this.actFormDataDao.selectModelByAppId(appId);
		rst.setValue(list);
		return rst;
	}
}

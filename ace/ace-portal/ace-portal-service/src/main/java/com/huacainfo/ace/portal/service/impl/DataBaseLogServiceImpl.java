package com.huacainfo.ace.portal.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.portal.dao.DataBaseLogDao;
import com.huacainfo.ace.portal.model.Logs;
import com.huacainfo.ace.portal.service.DataBaseLogService;

@Service("dataBaseLogService")
public class DataBaseLogServiceImpl implements DataBaseLogService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DataBaseLogDao dataBaseLogDao;


	@Override
	public void log(String log, String name, String old, String news,
			String objectValue, UserProp userProp) {
		logger.info(userProp.getName() + "  " + log + " "+ objectValue);
		Logs logs = new Logs();
		logs.setCreateTime(new Date());
		logs.setLog(log);
		logs.setName(name);
		logs.setNews(news);
		logs.setOld(old);
		logs.setObjectValue(objectValue);
		logs.setUserId(userProp.getUserId());
		logs.setUserName(userProp.getName());
		logs.setIp(userProp.getIp());
		logs.setDepartmentId(userProp.getCorpId());
		this.dataBaseLogDao.insert(logs);
	}

	@Override
	public PageResult<Map<String, Object>> findList(Map<String, Object> condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<Map<String, Object>> rst = new PageResult<Map<String, Object>>();
		List<Map<String, Object>> list = this.dataBaseLogDao.findList(
				condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.dataBaseLogDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
}

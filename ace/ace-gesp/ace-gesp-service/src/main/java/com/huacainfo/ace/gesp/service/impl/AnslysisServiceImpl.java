package com.huacainfo.ace.gesp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.service.AnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.gesp.dao.report.ReportDao;

@Service("analysisService")
public class AnslysisServiceImpl implements AnalysisService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public ListResult<Map<String, Object>> query(Map<String, Object> condition,
			String reportId) throws Exception {
		ListResult<Map<String, Object>> rst = new ListResult<Map<String, Object>>();
		List<Map<String, Object>> p=new ArrayList<Map<String, Object>>();
		ReportDao dao = (ReportDao) SpringUtils.getBean(reportId);
		List<Map<String, Object>> list=dao.query(condition);
		for(Map<String, Object> e:list){
			p.add(CommonUtils.sortMapByKey(e));
		}
		rst.setValue(p);
		return rst;
	}
}

package com.huacainfo.ace.fundtown.service.impl;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.fundtown.service.AnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("analysisService")
public class AnslysisServiceImpl implements AnalysisService {
    private static final Logger logger = LoggerFactory.getLogger(AnslysisServiceImpl.class);

    @Override
    public ListResult<Map<String, Object>> query(Map<String, Object> condition,
                                                 String reportId, int start, int limit) throws Exception {
//		ListResult<Map<String, Object>> rst = new ListResult<Map<String, Object>>();
//		List<Map<String, Object>> p=new ArrayList<Map<String, Object>>();
//		ReportDao dao = (ReportDao) SpringUtils.getBean(reportId);
//		condition.put("start",start);
//		condition.put("limit",limit);
//		List<Map<String, Object>> list=dao.query(condition);
//		for(Map<String, Object> e:list){
//			p.add(CommonUtils.sortMapByKey(e));
//		}
//		rst.setValue(p);
//		return rst;
        return null;
    }



}

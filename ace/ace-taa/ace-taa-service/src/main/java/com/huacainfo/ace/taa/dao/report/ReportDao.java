package com.huacainfo.ace.taa.dao.report;

import java.util.List;
import java.util.Map;

public interface ReportDao {

    List<Map<String, Object>> query(Map<String, Object> condition);
}

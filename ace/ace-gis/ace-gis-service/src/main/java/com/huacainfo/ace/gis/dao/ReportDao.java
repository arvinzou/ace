package com.huacainfo.ace.gis.dao;
import java.util.Map;
import java.util.List;

/**
 * Created by chenxiaoke on 2017/5/4.
 */
public interface ReportDao {
    List<Map<String,Object>> query(Map<String, Object> condition,int start,int limit);
}

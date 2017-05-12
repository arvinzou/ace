package com.huacainfo.ace.operana.service;

import java.util.List;
import java.util.Map;

public interface ChartService {

	public Map<String,Object> chart1(String meetingId,String topicId,String normId) throws Exception;

	public Map<String,Object> chart2(String meetingId,String topicId,String normId) throws Exception;

	public Map<String,Object> chart3(String meetingId,String topicId,String normId) throws Exception;

	public Map<String,Object> chart4(String meetingId,String topicId,String normId) throws Exception;


}

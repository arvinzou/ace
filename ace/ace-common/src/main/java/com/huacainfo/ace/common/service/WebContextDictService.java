package com.huacainfo.ace.common.service;

import java.util.List;
import java.util.Map;

public interface WebContextDictService {
	
	public Map<String, List<Map<String,Object>>> flushJavaScriptFile(String syid) ;
	public List<Map<String,String>> selectSyidBydc() throws Exception;
}

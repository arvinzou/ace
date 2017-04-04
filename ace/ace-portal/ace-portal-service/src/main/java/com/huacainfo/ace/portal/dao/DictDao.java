package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;

public interface DictDao {
	List<Map<String,String>> selectAllDictList(String syid);
	List<Map<String,String>> selectSyidBydc();
}

package com.huacainfo.ace.uf.service;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.model.WxUser;

public interface DeptService {

	public  List<Map<String,Object>> selectDeptList(String q, WxUser user,String areaCode) throws Exception;

	public  Map<String,Object> selectDept(String id, WxUser user) throws Exception;

	public  List<Map<String,Object>> selectDeptListMap(WxUser user) throws Exception;

	public  List<Map<String,Object>> selectAreaCodeList(String areaCode, WxUser user) throws Exception;

	public  List<Map<String,Object>> selectDeptCategoryList(WxUser user,String areaCode) throws Exception;

	public  List<Map<String,Object>> selectDeptByCategory(String category, WxUser user) throws Exception;
}

package com.huacainfo.ace.uf.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.uf.dao.DeptDao;
import com.huacainfo.ace.uf.service.DeptService;


@Service("deptService")
public class DeptServiceImpl implements DeptService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DeptDao deptDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;


	public  List<Map<String,Object>> selectDeptList(String q,WxUser user,String areaCode) throws Exception{

		List<Map<String,Object>> category=this.deptDao.selectDeptCategoryList(areaCode);
		List<Map<String,Object>> list=this.deptDao.selectDeptList(q);
		int i=0;
		if(CommonUtils.isNotBlank(q)){
			category=new ArrayList<Map<String,Object>>();
			Map<String,Object> e=new HashedMap();
			e.put("id","01");
			e.put("name","搜索");
			e.put("icon","search.png");
			e.put("open",true);
			e.put("list",list);
			category.add(e);
			return category;
		}
		for(Map<String,Object> e:category){

			if(i==0){
				e.put("open",true);
			}else{
				e.put("open",false);
			}
			e.put("list",this.getGroupDept((String) e.get("id"),list));
			i++;
		}
		return category;
	}
	public  List<Map<String,Object>> selectDeptListMap(WxUser user) throws Exception{
		return this.deptDao.selectDeptList("");
	}
	private List<Map<String,Object>> getGroupDept(String category,List<Map<String,Object>> list){
		List<Map<String,Object>> rst=new ArrayList<Map<String,Object>>();
		for(Map<String,Object> e:list){
			if(((String)e.get("category")).equals(category)){
				rst.add(e);
			}
		}
		return  rst;
	}

	public  Map<String,Object> selectDept(String id,WxUser user) throws Exception{
		Map<String,Object> rst=this.deptDao.selectDeptById(id);
		List<Map<String,String>> list=this.deptDao.selectDeptImagesById(id);
		rst.put("list",list);
		return rst;
	}
	public  List<Map<String,Object>> selectAreaCodeList(String areaCode,WxUser user) throws Exception{
		List<Map<String,Object>> list=this.deptDao.selectAreaCodeList(areaCode);

		return list;
	}

	public  List<Map<String,Object>> selectDeptCategoryList(WxUser user,String areaCode) throws Exception{
		List<Map<String,Object>> list=this.deptDao.selectDeptCategoryList(areaCode);
		return list;
	}
	public  List<Map<String,Object>> selectDeptByCategory(String category,WxUser user) throws Exception{
		List<Map<String,Object>> list=this.deptDao.selectDeptByCategory(category);
		return list;
	}
}

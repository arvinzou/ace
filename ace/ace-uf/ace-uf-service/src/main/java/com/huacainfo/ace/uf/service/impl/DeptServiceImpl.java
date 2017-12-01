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
/*
 * @author 陈晓克
 * @date 2011/10/31
 */
public class DeptServiceImpl implements DeptService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DeptDao deptDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	/**
	 * 获取单位列表
	 * @param q
	 * @param user
	 * @param areaCode
	 * @return
	 * @throws Exception
	 */

	@Override
	public  List<Map<String,Object>> selectDeptList(String q,WxUser user,String areaCode) throws Exception{

		List<Map<String,Object>> category=this.deptDao.selectDeptCategoryList(areaCode);
		/*
		 *category
		 * id:
		 * name:
		 * icon
		 */
		List<Map<String,Object>> list=this.deptDao.selectDeptListByText(q);
		for(Map<String,Object> p:list){
			/*
			 *把category==4的单位 电话设置为“”；
			 */
			if(String.valueOf(p.get("category")).equals("4")){
				p.put("tel","");
			}
			/**
			 * 如果手机号码则，则也将手机号码设置为“”；
			 */
			if(p.get("tel")!=null){
				if(String.valueOf(p.get("tel")).length()==11){
					p.put("tel","");
				}
			}
		}
		int i=0;
		//如果参数q不为空（如果是搜索数据。）
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
		//如果不是搜索数据
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


	@Override
	public  List<Map<String,Object>> selectDeptListMap(WxUser user,String longitude,String latitude,String q) throws Exception{
		return this.deptDao.selectDeptList(q,longitude,latitude);
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
	@Override
	public  Map<String,Object> selectDept(String id,WxUser user) throws Exception{
		Map<String,Object> rst=this.deptDao.selectDeptById(id);
		List<Map<String,String>> list=this.deptDao.selectDeptImagesById(id);
		rst.put("list",list);
		return rst;
	}
	@Override
	public  List<Map<String,Object>> selectAreaCodeList(String areaCode,WxUser user) throws Exception{
		List<Map<String,Object>> list=this.deptDao.selectAreaCodeList(areaCode);

		return list;
	}
	@Override
	public  List<Map<String,Object>> selectDeptCategoryList(WxUser user,String areaCode) throws Exception{
		List<Map<String,Object>> list=this.deptDao.selectDeptCategoryList(areaCode);
		return list;
	}
	@Override
	public  List<Map<String,Object>> selectDeptByCategory(String category,WxUser user) throws Exception{
		List<Map<String,Object>> list=this.deptDao.selectDeptByCategory(category);
		return list;
	}
}

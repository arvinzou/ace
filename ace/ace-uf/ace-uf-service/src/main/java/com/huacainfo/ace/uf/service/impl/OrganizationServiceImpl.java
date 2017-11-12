package com.huacainfo.ace.uf.service.impl;

import java.util.*;

import com.huacainfo.ace.common.model.WxUser;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.uf.dao.OrganizationDao;
import com.huacainfo.ace.uf.model.Organization;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.uf.service.OrganizationService;
import com.huacainfo.ace.uf.vo.OrganizationVo;
import com.huacainfo.ace.uf.vo.OrganizationQVo;
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;



	public PageResult<OrganizationVo> findOrganizationList(OrganizationQVo condition, int start, int limit,
			String orderBy) throws Exception {
		PageResult<OrganizationVo> rst = new PageResult<OrganizationVo>();
		List<OrganizationVo> list = this.organizationDao.findList(condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.organizationDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertOrganization(Organization o, UserProp userProp) throws Exception {
		// o.setId(UUID.randomUUID().toString());
		o.setId(String.valueOf(new Date().getTime()));
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getCategory())) {
			return new MessageResponse(1, "资源类别不能为空！");
		}
		if (CommonUtils.isBlank(o.getAreaCode())) {
			return new MessageResponse(1, "所属辖区不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getAddr())) {
			return new MessageResponse(1, "地址不能为空！");
		}
		if (CommonUtils.isBlank(o.getTel())) {
			return new MessageResponse(1, "联系电话不能为空！");
		}
		if (CommonUtils.isBlank(o.getServiceWay())) {
			return new MessageResponse(1, "服务途径不能为空！");
		}
		int temp = this.organizationDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "社会资源名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.organizationDao.insert(o);
		this.dataBaseLogService.log("添加社会资源", "社会资源", "", o.getName(), o.getName(), userProp);
		return new MessageResponse(0, "添加社会资源完成！");
	}

	public MessageResponse updateOrganization(Organization o, UserProp userProp) throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getCategory())) {
			return new MessageResponse(1, "资源类别不能为空！");
		}
		if (CommonUtils.isBlank(o.getAreaCode())) {
			return new MessageResponse(1, "所属辖区不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getAddr())) {
			return new MessageResponse(1, "地址不能为空！");
		}
		if (CommonUtils.isBlank(o.getTel())) {
			return new MessageResponse(1, "联系电话不能为空！");
		}
		if (CommonUtils.isBlank(o.getServiceWay())) {
			return new MessageResponse(1, "服务途径不能为空！");
		}
		if (CommonUtils.isBlank(o.getStatus())) {
			return new MessageResponse(1, "状态不能为空！");
		}
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.organizationDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更社会资源", "社会资源", "", o.getName(), o.getName(), userProp);
		return new MessageResponse(0, "变更社会资源完成！");
	}

	public SingleResult<OrganizationVo> selectOrganizationByPrimaryKey(String id) throws Exception {
		SingleResult<OrganizationVo> rst = new SingleResult<OrganizationVo>();
		rst.setValue(this.organizationDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteOrganizationByOrganizationId(String id, UserProp userProp) throws Exception {
		this.organizationDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除社会资源", "社会资源", String.valueOf(id), String.valueOf(id), "社会资源", userProp);
		return new MessageResponse(0, "社会资源删除完成！");
	}

	public  List<Map<String,Object>> selectOrganizationList(String q,WxUser user) throws Exception{

		List<Map<String,Object>> category=this.organizationDao.selectOrganizationCategoryList();
		List<Map<String,Object>> list=this.organizationDao.selectOrganizationListByText(q);
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
			e.put("list",this.getGroupOrganization((String) e.get("id"),list));
			i++;
		}
		return category;
	}
	public  List<Map<String,Object>> selectOrganizationListMap(WxUser user,String longitude,String latitude) throws Exception{
		return this.organizationDao.selectOrganizationList("",longitude,latitude);
	}

	private List<Map<String,Object>> getGroupOrganization(String category,List<Map<String,Object>> list){
		List<Map<String,Object>> rst=new ArrayList<Map<String,Object>>();
		for(Map<String,Object> e:list){
			if(((String)e.get("category")).equals(category)){
				rst.add(e);
			}
		}
		return  rst;
	}

	public  Map<String,Object> selectOrganization(String id,WxUser user) throws Exception{
		Map<String,Object> rst=this.organizationDao.selectOrganizationById(id);
		List<Map<String,String>> list=this.organizationDao.selectOrganizationImagesById(id);
		rst.put("list",list);
		return rst;
	}
	public  List<Map<String,Object>> selectAreaCodeList(String areaCode,WxUser user) throws Exception{
		List<Map<String,Object>> list=this.organizationDao.selectAreaCodeList(areaCode);

		return list;
	}

	public  List<Map<String,Object>> selectOrganizationCategoryList(WxUser user) throws Exception{
		List<Map<String,Object>> list=this.organizationDao.selectOrganizationCategoryList();
		return list;
	}
	public  List<Map<String,Object>> selectOrganizationByCategory(String category,WxUser user) throws Exception{
		List<Map<String,Object>> list=this.organizationDao.selectOrganizationByCategory(category);
		return list;
	}
}

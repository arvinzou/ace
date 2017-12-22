package com.huacainfo.ace.operana.service.impl;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.operana.dao.NormDao;
import com.huacainfo.ace.operana.model.Norm;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.operana.service.NormService;
import com.huacainfo.ace.operana.vo.NormVo;
import com.huacainfo.ace.operana.vo.NormQVo;
@Service("normService")
public class NormServiceImpl implements NormService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NormDao normDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Override
	public PageResult<NormVo> findNormList(NormQVo condition, int start, int limit, String orderBy) throws Exception {
		PageResult<NormVo> rst = new PageResult<NormVo>();
		List<NormVo> list = this.normDao.findList(condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.normDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
	@Override
	public MessageResponse insertNorm(Norm o, UserProp userProp) throws Exception {
		o.setId(String.valueOf(System.currentTimeMillis()));
		if(CommonUtils.isBlank(o.getDeptId())){
			o.setDeptId(userProp.getCorpId());
		}
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "指标编码不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "指标名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getCategory())) {
			return new MessageResponse(1, "指标分类不能为空！");
		}
		if (CommonUtils.isBlank(o.getCalType())) {
			return new MessageResponse(1, "指标计算类型不能为空！");
		}
		if (CommonUtils.isBlank(o.getStatus())) {
			return new MessageResponse(1, "状态不能为空！");
		}

		int temp = this.normDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "指标名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		o.setPcode(CommonUtils.getPinYinHeadChar(o.getName()));
		this.normDao.insert(o);
		this.dataBaseLogService.log("添加指标", "指标", "", o.getName(), o.getName(), userProp);
		return new MessageResponse(0, "添加指标完成！");
	}
	@Override
	public MessageResponse updateNorm(Norm o, UserProp userProp) throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "指标编码不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "指标名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getCategory())) {
			return new MessageResponse(1, "指标分类不能为空！");
		}
		if (CommonUtils.isBlank(o.getCalType())) {
			return new MessageResponse(1, "指标计算类型不能为空！");
		}
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		o.setPcode(CommonUtils.getPinYinHeadChar(o.getName()));
		this.normDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更指标", "指标", "", o.getName(), o.getName(), userProp);
		return new MessageResponse(0, "变更指标完成！");
	}
	@Override
	public SingleResult<NormVo> selectNormByPrimaryKey(String id) throws Exception {
		SingleResult<NormVo> rst = new SingleResult<NormVo>();
		rst.setValue(this.normDao.selectByPrimaryKey(id));
		return rst;
	}
	@Override
	public MessageResponse deleteNormByNormId(String id, UserProp userProp) throws Exception {
		this.normDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除指标", "指标", String.valueOf(id), String.valueOf(id), "指标", userProp);
		return new MessageResponse(0, "指标删除完成！");
	}
	@Override
	public  List<Map<String,Object>> selectAllNorm(String topicId,String name){
		List<Map<String,Object>> rst=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> categorys=this.normDao.selectAllNormCategory();
		if(CommonUtils.isBlank(name)){
			for(Map<String,Object> o:categorys){
				Map<String,Object> e=new HashMap<String,Object>();
				e.put("category",o);
				List<Map<String,Object>> items=this.normDao.selectNormByCategory((String)o.get("code"),topicId);
				e.put("items",items);
				rst.add(e);
			}
		}else{
			Map<String,Object> e=new HashMap<String,Object>();
			Map<String,Object> o=new HashMap<String,Object>();
			o.put("code","01");
			o.put("name","默认");
			e.put("category",o);
			List<Map<String,Object>> items=this.normDao.selectNormByName(name,topicId);
			e.put("items",items);
			rst.add(e);
		}

		return rst;
	}
	@Override
	public Map<String, Object> selectNormByTopicId(String topicId) throws Exception{
		Map<String, Object> rst=new HashMap<String, Object>();
		List<Map<String, Object>> data=this.normDao.selectNormByTopicId(topicId);
		rst.put("data",data);
		return rst;
	}
	@Override
	public MessageResponse updateSort(List<Map<String, Object>> list, UserProp userProp)
			throws Exception{
		for(Map<String, Object> o:list){
			this.normDao.updateSort((String)o.get("id"),(int)o.get("sort"));
		}
		this.dataBaseLogService.log("排序", "KPI", "", "",
				"", userProp);
		return new MessageResponse(0, "完成！");
	}
}

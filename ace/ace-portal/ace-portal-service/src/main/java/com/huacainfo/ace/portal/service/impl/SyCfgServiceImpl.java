package com.huacainfo.ace.portal.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.dao.ConfigMapper;
import com.huacainfo.ace.portal.dao.SyCfgDao;
import com.huacainfo.ace.portal.model.Config;
import com.huacainfo.ace.portal.model.SyCfg;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.SyCfgService;
import com.huacainfo.ace.portal.vo.SyCfgQVo;
import com.huacainfo.ace.portal.vo.SyCfgVo;

@Service("syCfgService")
public class SyCfgServiceImpl implements SyCfgService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SyCfgDao syCfgDao;
	@Autowired
	private ConfigMapper configMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<SyCfgVo> findSyCfgList(SyCfgQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<SyCfgVo> rst = new PageResult<SyCfgVo>();
		List<SyCfgVo> list = this.syCfgDao.findList(condition, start, start
				+ limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.syCfgDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertSyCfg(SyCfg o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "系统编号不能为空！");
		}
		int temp = this.syCfgDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "系统名称重复！");
		}
		o.setCreateUserId(userProp.getUserId());
		o.setCreateDate(new Date());
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.syCfgDao.insert(o);
		this.initConfig(o.getId(),userProp.getCorpId(),o.getName());
		this.dataBaseLogService.log("添加系统", "系统", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加系统完成！");
	}
	private void initConfig(String syid,String deptId,String syName){
		Config cfg1=new Config();
		cfg1.setCreateTime(new Date());
		cfg1.setId(UUID.randomUUID().toString());
		cfg1.setDeptId(deptId);
		cfg1.setConfigKey("sys_login_bg_img");
		cfg1.setCategory("02");
		cfg1.setSyid(syid);
		cfg1.setConfigName("登录背景图片");
		cfg1.setConfigValue("/content/portal/images/LOGIN2880-760.png");
		this.configMapper.insert(cfg1);
		
		Config cfg2=new Config();
		cfg2.setCreateTime(new Date());
		cfg2.setId(UUID.randomUUID().toString());
		cfg2.setDeptId(deptId);
		cfg2.setConfigKey("sys_name");
		cfg2.setCategory("02");
		cfg2.setSyid(syid);
		cfg2.setConfigName("系统名称");
		cfg2.setConfigValue(syName);
		this.configMapper.insert(cfg2);
		
		
		Config cfg3=new Config();
		cfg3.setCreateTime(new Date());
		cfg3.setId(UUID.randomUUID().toString());
		cfg3.setDeptId(deptId);
		cfg3.setConfigKey("sys_unit");
		cfg3.setCategory("02");
		cfg3.setSyid(syid);
		cfg3.setConfigName("使用单位名称");
		cfg3.setConfigValue("XX单位");
		this.configMapper.insert(cfg3);
		
		
		Config cfg4=new Config();
		cfg4.setCreateTime(new Date());
		cfg4.setId(UUID.randomUUID().toString());
		cfg4.setDeptId(deptId);
		cfg4.setConfigKey("version");
		cfg4.setCategory("02");
		cfg4.setSyid(syid);
		cfg4.setConfigName("系统版本");
		cfg4.setConfigValue("V1.0");
		this.configMapper.insert(cfg4);
		
		
	}
	public MessageResponse updateSyCfg(SyCfg o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "系统编号不能为空！");
		}
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.syCfgDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更系统", "系统", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更系统完成！");
	}

	public SingleResult<SyCfg> selectSyCfgByPrimaryKey(String id)
			throws Exception {
		SingleResult<SyCfg> rst = new SingleResult<SyCfg>();
		rst.setValue(this.syCfgDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteSyCfgBySyCfgId(String id, UserProp userProp)
			throws Exception {
		this.syCfgDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除系统", "系统", String.valueOf(id),
				String.valueOf(id), "系统", userProp);
		return new MessageResponse(0, "系统删除完成！");
	}
	public  ListResult<Map<String,Object>> selectSyCfgByUser(UserProp userProp) throws Exception{
		ListResult<Map<String,Object>> rst = new ListResult<Map<String,Object>>();
		List<Map<String,Object>> value=this.syCfgDao.selectSyCfgByUser(userProp.getSyid());
		rst.setValue(value);
		return rst;
	}

}

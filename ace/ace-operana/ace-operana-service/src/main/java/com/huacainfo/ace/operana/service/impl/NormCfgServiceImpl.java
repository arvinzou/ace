package com.huacainfo.ace.operana.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.operana.dao.NormCfgDao;
import com.huacainfo.ace.operana.model.NormCfg;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.operana.service.NormCfgService;
import com.huacainfo.ace.operana.vo.NormCfgVo;
import com.huacainfo.ace.operana.vo.NormCfgQVo;
@Service("normCfgService")
public class NormCfgServiceImpl implements NormCfgService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NormCfgDao normCfgDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<NormCfgVo> findNormCfgList(NormCfgQVo condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<NormCfgVo> rst = new PageResult<NormCfgVo>();
		List<NormCfgVo> list = this.normCfgDao.findList(condition, start, start + limit, orderBy);
		for(NormCfgVo o:list){
			o.setId(UUID.randomUUID().toString());
		}
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.normCfgDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertNormCfg(NormCfg o, UserProp userProp) throws Exception {
		// o.setId(UUID.randomUUID().toString());
		o.setId(String.valueOf(new Date().getTime()));
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "编码不能为空！");
		}
		if (CommonUtils.isBlank(o.getNormId())) {
			return new MessageResponse(1, "指标编码不能为空！");
		}
		if (CommonUtils.isBlank(o.getYear())) {
			return new MessageResponse(1, "指标年度不能为空！");
		}
		if (CommonUtils.isBlank(o.getIndexValue())) {
			return new MessageResponse(1, "指标值不能为空！");
		}
		int temp = this.normCfgDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "指标名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.normCfgDao.insert(o);
		this.dataBaseLogService.log("添加指标", "指标", "", o.getNormId(), o.getNormId(), userProp);
		return new MessageResponse(0, "添加指标完成！");
	}

	public MessageResponse updateNormCfg(NormCfg o, UserProp userProp) throws Exception {

		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "指标编码不能为空！");
		}
		if (CommonUtils.isBlank(o.getYear())) {
			return new MessageResponse(1, "指标年度不能为空！");
		}
		if (CommonUtils.isBlank(o.getIndexValue())) {
			return new MessageResponse(1, "指标值不能为空！");
		}
		int temp = this.normCfgDao.isExit(o);
		o.setStatus("1");
		if (temp > 0) {
			o.setLastModifyDate(new Date());
			o.setLastModifyUserName(userProp.getName());
			o.setLastModifyUserId(userProp.getUserId());
			this.normCfgDao.updateByPrimaryKey(o);
		}else{
			o.setId(UUID.randomUUID().toString());
			o.setCreateDate(new Date());
			o.setCreateUserName(userProp.getName());
			o.setCreateUserId(userProp.getUserId());
			this.normCfgDao.insert(o);
		}

		this.dataBaseLogService.log("配置指标", "指标", "", o.getNormId(), o.getNormId(), userProp);
		return new MessageResponse(0, "配置指标完成！");
	}

	public SingleResult<NormCfg> selectNormCfgByPrimaryKey(String id) throws Exception {
		SingleResult<NormCfg> rst = new SingleResult<NormCfg>();
		rst.setValue(this.normCfgDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteNormCfgByNormCfgId(String id, UserProp userProp) throws Exception {
		this.normCfgDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除指标", "指标", String.valueOf(id), String.valueOf(id), "指标", userProp);
		return new MessageResponse(0, "指标删除完成！");
	}
}

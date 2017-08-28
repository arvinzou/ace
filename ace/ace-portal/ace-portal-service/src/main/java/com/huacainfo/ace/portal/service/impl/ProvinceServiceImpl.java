package com.huacainfo.ace.portal.service.impl;


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
import com.huacainfo.ace.portal.dao.ProvinceDao;
import com.huacainfo.ace.portal.model.Province;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.ProvinceService;
import com.huacainfo.ace.portal.vo.ProvinceVo;
import com.huacainfo.ace.portal.vo.ProvinceQVo;
@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProvinceDao provinceDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<ProvinceVo> findProvinceList(ProvinceQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<ProvinceVo> rst = new PageResult<ProvinceVo>();
		List<ProvinceVo> list = this.provinceDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.provinceDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertProvince(Province o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getParent_code())) {
			return new MessageResponse(1, "上级辖区编号不能为空！");
		}
		int temp = this.provinceDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "辖区信息管理名称重复！");
		}
		this.provinceDao.insert(o);
		this.dataBaseLogService.log("添加辖区信息管理", "辖区信息管理", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加辖区信息管理完成！");
	}

	public MessageResponse updateProvince(Province o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getCode())) {
			return new MessageResponse(1, "辖区编号不能为空！");
		}
		this.provinceDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更辖区信息管理", "辖区信息管理", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更辖区信息管理完成！");
	}

	public SingleResult<Province> selectProvinceByPrimaryKey(String id) throws Exception {
		SingleResult<Province> rst = new SingleResult<Province>();
		rst.setValue(this.provinceDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteProvinceByProvinceId(String id,
			UserProp userProp) throws Exception {
		this.provinceDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除辖区信息管理", "辖区信息管理", String.valueOf(id),
				String.valueOf(id), "辖区信息管理", userProp);
		return new MessageResponse(0, "辖区信息管理删除完成！");
	}
}

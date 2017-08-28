package com.huacainfo.ace.gesp.service.impl;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.dao.ManagerCarDao;
import com.huacainfo.ace.gesp.service.ManagerCarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;

@Service("managerCarService")
public class ManagerCarServiceImpl implements ManagerCarService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ManagerCarDao managerCarDao;

	/**
	 * 查询车辆的基本信息
	 * 
	 * @param condition
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @return PageResult<Map<String, Object>>
	 */
	public PageResult<Map<String, Object>> findCarInfoList(Map<String, Object> condition, int start, int limit,
			String orderBy) throws Exception{
		PageResult<Map<String, Object>> rst = new PageResult<Map<String, Object>>();
		List<Map<String, Object>> list = this.managerCarDao.findCarInfoList(
				condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.managerCarDao.findCarInfoCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	/**
	 * 根据条件查询车辆的违规违章信息
	 * 
	 * @param condition
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @return PageResult<Map<String, Object>>
	 */
	public PageResult<Map<String, Object>> findCarIllegalList(Map<String, Object> condition, int start, int limit,
			String orderBy) {
		PageResult<Map<String, Object>> rst = new PageResult<Map<String, Object>>();
		List<Map<String, Object>> list = this.managerCarDao.findCarIllegalList(
				condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.managerCarDao.findCarIllegalCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}


	/**
	 * 根据条件查询车辆的综合性能检测信息
	 * 
	 * @param condition
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @return PageResult<Map<String, Object>>
	 */
	public PageResult<Map<String, Object>> findCarPerTestList(Map<String, Object> condition, int start, int limit,
			String orderBy) throws Exception {
		PageResult<Map<String, Object>> rst = new PageResult<Map<String, Object>>();
		List<Map<String, Object>> list = this.managerCarDao.findCarPerTestList(
				condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.managerCarDao.findCarPerTestCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	/**
	 * 根据条件查询车辆的二级维护信息
	 * 
	 * @param condition
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @return PageResult<Map<String, Object>>
	 */
	public PageResult<Map<String, Object>> findCarTwoLeMainList(Map<String, Object> condition, int start, int limit,
			String orderBy) {
		PageResult<Map<String, Object>> rst = new PageResult<Map<String, Object>>();
		List<Map<String, Object>> list = this.managerCarDao.findCarTwoLeMainList(
				condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.managerCarDao.findCarTwoLeMainCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	/**
	 * 根据编号查询车辆的基本信息
	 * 
	 * @param plateNo
	 * @param color
	 * @return
	 */
	public SingleResult<Map<String, Object>> selectByCarInfo(String id) {
		SingleResult<Map<String, Object>> rst = new SingleResult<Map<String, Object>>();
		rst.setValue(this.managerCarDao.selectByCarInfo(id));
		return rst;
	}

	/**
	 * 根据车牌号码和颜色查询车辆的综合性能检测信息
	 * 
	 * @param plateNo
	 * @param color
	 * @return
	 */
	public List<Map<String, Object>> selectByCarPerTest(String plateNo, String color) {
		return this.managerCarDao.selectByCarPerTest(plateNo, color);
	}

	/**
	 * 根据车牌号码和颜色查询车辆的二级维护信息
	 * 
	 * @param plateNo
	 * @param color
	 * @return
	 */
	public List<Map<String, Object>> selectByCarTwoLeMain(String plateNo, String color) {
		return this.managerCarDao.selectByCarTwoLeMain(plateNo, color);
	}

	/**
	 * 根据车牌号码和颜色查询车辆的违规违章信息
	 * 
	 * @param plateNo
	 * @param color
	 * @return
	 */
	public List<Map<String, Object>> selectByCarIllegal(String plateNo, String color) {
		return this.managerCarDao.selectByCarIllegal(plateNo, color);
	}

}


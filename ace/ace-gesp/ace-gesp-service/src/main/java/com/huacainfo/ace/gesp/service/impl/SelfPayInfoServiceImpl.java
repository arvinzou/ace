package com.huacainfo.ace.gesp.service.impl;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.dao.SelfPayInfoDao;
import com.huacainfo.ace.gesp.service.SelfPayInfoService;
import com.huacainfo.ace.gesp.vo.MemberPayInfoQVo;
import com.huacainfo.ace.gesp.vo.MemberPayInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.portal.service.DataBaseLogService;

@Service("selfPayInfoService")
public class SelfPayInfoServiceImpl implements SelfPayInfoService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SelfPayInfoDao selfPayInfoDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<MemberPayInfoVo> findSelfPayInfoList(
            MemberPayInfoQVo condition, int start, int limit, String orderBy)
			throws Exception {
		
		PageResult<MemberPayInfoVo> rst = new PageResult<MemberPayInfoVo>();
		List<MemberPayInfoVo> list = this.selfPayInfoDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.selfPayInfoDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	
	public  List<Map<String,Object>> selectAnaysePayMentByMonth( String year,String deptId,String deptIdchargingItemId) throws Exception{
		return this.selfPayInfoDao.selectAnaysePayMentByMonth(year, deptId, deptIdchargingItemId);
	}
	
}

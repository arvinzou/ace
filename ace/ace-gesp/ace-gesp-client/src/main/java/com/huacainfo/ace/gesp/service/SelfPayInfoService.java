package com.huacainfo.ace.gesp.service;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.vo.MemberPayInfoQVo;
import com.huacainfo.ace.gesp.vo.MemberPayInfoVo;
import com.huacainfo.ace.common.result.PageResult;

public interface SelfPayInfoService {

	public abstract PageResult<MemberPayInfoVo> findSelfPayInfoList(
            MemberPayInfoQVo condition, int start, int limit, String orderBy)
			throws Exception;

	public abstract List<Map<String, Object>> selectAnaysePayMentByMonth(
			String year, String deptId, String deptIdchargingItemId)
			throws Exception;

}

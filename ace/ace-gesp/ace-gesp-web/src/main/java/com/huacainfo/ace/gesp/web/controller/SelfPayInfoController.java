package com.huacainfo.ace.gesp.web.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.gesp.service.SelfPayInfoService;
import com.huacainfo.ace.gesp.vo.MemberPayInfoQVo;
import com.huacainfo.ace.gesp.vo.MemberPayInfoVo;

@Controller
@RequestMapping("/selfPayInfo")
public class SelfPayInfoController extends KernelBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SelfPayInfoService selfPayInfoService;

	@RequestMapping(value = "/findSelfPayInfoList.do")
	@ResponseBody
	public PageResult<MemberPayInfoVo> findSelfPayInfoList(
			MemberPayInfoQVo condition, PageParamNoChangeSord page)
			throws Exception {
		if (CommonUtils.isBlank(condition.getMemberCode())) {
			condition.setMemberCode(this.getCurUserProp().getCorpId());
		}
		PageResult<MemberPayInfoVo> rst = this.selfPayInfoService
				.findSelfPayInfoList(condition, page.getStart(),
						page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}

		return rst;
	}

	@RequestMapping(value = "/selectAnaysePayMentByMonth.do")
	@ResponseBody
	List<Map<String, Object>> selectAnaysePayMentByMonth(String year,
			String deptId, String deptIdchargingItemId) throws Exception {
		return this.selfPayInfoService.selectAnaysePayMentByMonth(year, deptId,
				deptIdchargingItemId);
	}
}

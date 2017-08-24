package com.huacainfo.ace.portal.web.controller;

import com.huacainfo.ace.common.model.PageParam;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.portal.vo.QueueCmccHisVo;
import org.apache.log4j.Logger;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.portal.service.QueueCmccHisService;
import com.huacainfo.ace.portal.vo.QueueCmccHisQVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/queueCmccHis")
public class QueueCmccHisController extends PortalBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private QueueCmccHisService queueCmccHisService;
	@RequestMapping(value = "/findQueueCmccHisList.do")
	@ResponseBody
	public PageResult<QueueCmccHisVo> findQueueCmccHisList(QueueCmccHisQVo condition, PageParam page) throws Exception {
		PageResult<QueueCmccHisVo> rst = this.queueCmccHisService.findQueueCmccHisList(condition, page.getStart(),
				page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}

	@RequestMapping(value = "/deleteQueueCmccHisByQueueCmccHisId.do")
	@ResponseBody
	public MessageResponse deleteQueueCmccHisByQueueCmccHisId(String id) throws Exception {
		UserProp userProp = this.getCurUserProp();
		return this.queueCmccHisService.deleteQueueCmccHisByQueueCmccHisId(id, userProp);
	}
}

package com.huacainfo.ace.portal.web.controller;

import com.huacainfo.ace.common.model.PageParam;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.portal.vo.QueueCmccWaitVo;
import org.apache.log4j.Logger;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.portal.service.QueueCmccWaitService;
import com.huacainfo.ace.portal.vo.QueueCmccWaitQVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/queueCmccWait")
public class QueueCmccWaitController extends PortalBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private QueueCmccWaitService queueCmccWaitService;

	@RequestMapping(value = "/findQueueCmccWaitList.do")
	@ResponseBody
	public PageResult<QueueCmccWaitVo> findQueueCmccWaitList(QueueCmccWaitQVo condition, PageParam page)
			throws Exception {
		PageResult<QueueCmccWaitVo> rst = this.queueCmccWaitService.findQueueCmccWaitList(condition, page.getStart(),
				page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}

	@RequestMapping(value = "/deleteQueueCmccWaitByQueueCmccWaitId.do")
	@ResponseBody
	public MessageResponse deleteQueueCmccWaitByQueueCmccWaitId(String id) throws Exception {
		return this.queueCmccWaitService.deleteQueueCmccWaitByQueueCmccWaitId(id, this.getCurUserProp());
	}
}

package com.huacainfo.ace.woc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.CaseAudit;
import com.huacainfo.ace.woc.service.CaseAuditService;
import com.huacainfo.ace.woc.vo.CaseAuditVo;
import com.huacainfo.ace.woc.vo.CaseAuditQVo;

@Controller
@RequestMapping("/caseAudit")
/**
 * @author: 王恩
 * @version: 2018-03-21
 * @Description:  TODO(案件审核记录)
 */
public class CaseAuditController extends WocBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CaseAuditService caseAuditService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(案件审核记录分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<CaseAuditVo>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/findCaseAuditList")
	@ResponseBody
	public PageResult<CaseAuditVo> findCaseAuditList(CaseAuditQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<CaseAuditVo> rst = this.caseAuditService
				.findCaseAuditList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertCaseAudit
	    * @Description:  TODO(添加案件审核记录)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/insertCaseAudit")
	@ResponseBody
	public MessageResponse insertCaseAudit(String jsons) throws Exception {
		CaseAudit obj = JSON.parseObject(jsons, CaseAudit.class);
		return this.caseAuditService
				.insertCaseAudit(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateCaseAudit
	    * @Description:  TODO(更新案件审核记录)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/updateCaseAudit")
	@ResponseBody
	public MessageResponse updateCaseAudit(String jsons) throws Exception {
		CaseAudit obj = JSON.parseObject(jsons, CaseAudit.class);
		return this.caseAuditService
				.updateCaseAudit(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectCaseAuditByPrimaryKey
	    * @Description:  TODO(获取案件审核记录)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<CaseAudit>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/selectCaseAuditByPrimaryKey")
	@ResponseBody
	public SingleResult<CaseAuditVo> selectCaseAuditByPrimaryKey(String id)
			throws Exception {
		return this.caseAuditService.selectCaseAuditByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteCaseAuditByCaseAuditId
	    * @Description:  TODO(删除案件审核记录)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/deleteCaseAuditByCaseAuditId")
	@ResponseBody
	public MessageResponse deleteCaseAuditByCaseAuditId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.caseAuditService.deleteCaseAuditByCaseAuditId(id,
				this.getCurUserProp());
	}
}

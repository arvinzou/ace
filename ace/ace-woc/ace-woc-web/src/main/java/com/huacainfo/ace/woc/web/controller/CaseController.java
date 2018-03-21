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
import com.huacainfo.ace.woc.model.Case;
import com.huacainfo.ace.woc.service.CaseService;
import com.huacainfo.ace.woc.vo.CaseVo;
import com.huacainfo.ace.woc.vo.CaseQVo;

@Controller
@RequestMapping("/case")
/**
 * @author: 王恩
 * @version: 2018-03-21
 * @Description:  TODO(案件)
 */
public class CaseController extends WocBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CaseService caseService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(案件分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<CaseVo>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/findCaseList")
	@ResponseBody
	public PageResult<CaseVo> findCaseList(CaseQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<CaseVo> rst = this.caseService
				.findCaseList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertCase
	    * @Description:  TODO(添加案件)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/insertCase")
	@ResponseBody
	public MessageResponse insertCase(String jsons) throws Exception {
		Case obj = JSON.parseObject(jsons, Case.class);
		return this.caseService
				.insertCase(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateCase
	    * @Description:  TODO(更新案件)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/updateCase")
	@ResponseBody
	public MessageResponse updateCase(String jsons) throws Exception {
		Case obj = JSON.parseObject(jsons, Case.class);
		return this.caseService
				.updateCase(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectCaseByPrimaryKey
	    * @Description:  TODO(获取案件)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Case>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/selectCaseByPrimaryKey")
	@ResponseBody
	public SingleResult<CaseVo> selectCaseByPrimaryKey(String id)
			throws Exception {
		return this.caseService.selectCaseByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteCaseByCaseId
	    * @Description:  TODO(删除案件)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	@RequestMapping(value = "/deleteCaseByCaseId")
	@ResponseBody
	public MessageResponse deleteCaseByCaseId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.caseService.deleteCaseByCaseId(id,
				this.getCurUserProp());
	}
}

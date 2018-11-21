package com.huacainfo.ace.portal.web.controller;
import java.util.List;
import java.util.Map;
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
import com.huacainfo.ace.portal.model.EvaluatCaseSub;
import com.huacainfo.ace.portal.service.EvaluatCaseSubService;
import com.huacainfo.ace.portal.vo.EvaluatCaseSubVo;
import com.huacainfo.ace.portal.vo.EvaluatCaseSubQVo;

@Controller
@RequestMapping("/evaluatCaseSub")
/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description:  TODO(题目选项)
 */
public class EvaluatCaseSubController extends PortalBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EvaluatCaseSubService evaluatCaseSubService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(题目选项分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<EvaluatCaseSubVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/findEvaluatCaseSubList.do")
	@ResponseBody
	public PageResult<EvaluatCaseSubVo> findEvaluatCaseSubList(EvaluatCaseSubQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<EvaluatCaseSubVo> rst = this.evaluatCaseSubService
				.findEvaluatCaseSubList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertEvaluatCaseSub
	    * @Description:  TODO(添加题目选项)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/insertEvaluatCaseSub.do")
	@ResponseBody
	public MessageResponse insertEvaluatCaseSub(String jsons) throws Exception {
		EvaluatCaseSub obj = JSON.parseObject(jsons, EvaluatCaseSub.class);
		return this.evaluatCaseSubService
				.insertEvaluatCaseSub(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateEvaluatCaseSub
	    * @Description:  TODO(更新题目选项)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/updateEvaluatCaseSub.do")
	@ResponseBody
	public MessageResponse updateEvaluatCaseSub(String jsons) throws Exception {
		EvaluatCaseSub obj = JSON.parseObject(jsons, EvaluatCaseSub.class);
		return this.evaluatCaseSubService
				.updateEvaluatCaseSub(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectEvaluatCaseSubByPrimaryKey
	    * @Description:  TODO(获取题目选项)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<EvaluatCaseSub>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/selectEvaluatCaseSubByPrimaryKey.do")
	@ResponseBody
	public SingleResult<EvaluatCaseSubVo> selectEvaluatCaseSubByPrimaryKey(String id)
			throws Exception {
		return this.evaluatCaseSubService.selectEvaluatCaseSubByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteEvaluatCaseSubByEvaluatCaseSubId
	    * @Description:  TODO(删除题目选项)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/deleteEvaluatCaseSubByEvaluatCaseSubId.do")
	@ResponseBody
	public MessageResponse deleteEvaluatCaseSubByEvaluatCaseSubId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.evaluatCaseSubService.deleteEvaluatCaseSubByEvaluatCaseSubId(id,
				this.getCurUserProp());
	}

	/**
     *
     * @Title:getList
     * @Description:  TODO(获取题目选项列表)
     * @param:        @throws Exception
     * @return:       Map<String,Object>
     * @throws
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @RequestMapping(value = "/getList.do")
    @ResponseBody
    public Map<String,Object> getList() throws Exception{
        Map<String,Object> params=this.getParams();
        params.put("userId",this.getCurUserProp().getUserId());
        return this.evaluatCaseSubService.getList(params);
    }

    /**
     *
     * @Title:getById
     * @Description:  TODO(获取题目选项列表)
     * @param:        @throws Exception
     * @return:       Map<String,Object>
     * @throws
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @RequestMapping(value = "/getById.do")
    @ResponseBody
    public Map<String,Object> getById(String id) throws Exception{
        return this.evaluatCaseSubService.getById(id);
    }
}

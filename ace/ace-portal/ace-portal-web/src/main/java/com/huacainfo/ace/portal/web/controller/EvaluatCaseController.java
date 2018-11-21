package com.huacainfo.ace.portal.web.controller;
import java.util.List;
import java.util.Map;

import com.huacainfo.ace.portal.model.EvaluatCaseSub;
import com.huacainfo.ace.portal.model.EvaluatGauge;
import com.huacainfo.ace.portal.model.EvaluatTpl;
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
import com.huacainfo.ace.portal.model.EvaluatCase;
import com.huacainfo.ace.portal.service.EvaluatCaseService;
import com.huacainfo.ace.portal.vo.EvaluatCaseVo;
import com.huacainfo.ace.portal.vo.EvaluatCaseQVo;

@Controller
@RequestMapping("/evaluatCase")
/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description:  TODO(题目)
 */
public class EvaluatCaseController extends PortalBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EvaluatCaseService evaluatCaseService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(题目分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<EvaluatCaseVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/findEvaluatCaseList.do")
	@ResponseBody
	public PageResult<EvaluatCaseVo> findEvaluatCaseList(EvaluatCaseQVo condition, PageParamNoChangeSord page) throws Exception {
		PageResult<EvaluatCaseVo> rst = this.evaluatCaseService.findEvaluatCaseList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}


	/**
	 * @throws
	 * @Title:find!{bean.name}List
	 * @Description: TODO(题目分页查询)
	 * @param: @param condition
	 * @param: @param page
	 * @param: @return
	 * @param: @throws Exception
	 * @return: PageResult<EvaluatCaseVo>
	 * @author: 陈晓克
	 * @version: 2018-06-09
	 */
	@RequestMapping(value = "/findEvaluatCaseListSecond.do")
	@ResponseBody
	public PageResult<EvaluatCaseVo> findEvaluatCaseListSecond(EvaluatCaseQVo condition, PageParamNoChangeSord page) throws Exception {
		PageResult<EvaluatCaseVo> rst = this.evaluatCaseService.findEvaluatCaseListSecond(condition, page.getPage(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}


    /**
	 *
	    * @Title:insertEvaluatCase
	    * @Description:  TODO(添加题目)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/insertEvaluatCase.do")
	@ResponseBody
	public MessageResponse insertEvaluatCase(String jsons) throws Exception {
		return this.evaluatCaseService.insertEvaluatCase(jsons, this.getCurUserProp());
	}


	/**
	 * @throws
	 * @Title:insertEvaluatCase
	 * @Description: TODO(添加题目)
	 * @param: @param jsons
	 * @param: @throws Exception
	 * @return: MessageResponse
	 * @author: 陈晓克
	 * @version: 2018-06-09
	 */
	@RequestMapping(value = "/insertEvaluatCaseVo.do")
	@ResponseBody
	public MessageResponse insertEvaluatCaseVo(String jsons) throws Exception {
		JSONObject jsonObj = JSON.parseObject(jsons);
		EvaluatCase obj = JSON.parseObject(jsonObj.getString("evaluatCase"), EvaluatCase.class);
		List<EvaluatCaseSub> lists = JSON.parseArray(jsonObj.getString("evaluatCaseSub"), EvaluatCaseSub.class);
		return this.evaluatCaseService.insertEvaluatCaseVo(obj, lists, this.getCurUserProp());
	}

	/**
	 *
	    * @Title:updateEvaluatCase
	    * @Description:  TODO(更新题目)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/updateEvaluatCase.do")
	@ResponseBody
    public MessageResponse updateEvaluatCase(String jsons) throws Exception {
        return this.evaluatCaseService.updateEvaluatCase(jsons, this.getCurUserProp());
    }


    @RequestMapping(value = "/updateEvaluatCaseVo.do")
    @ResponseBody
    public MessageResponse updateEvaluatCaseVo(String jsons) throws Exception {
        JSONObject jsonObj = JSON.parseObject(jsons);
        EvaluatCase obj = JSON.parseObject(jsonObj.getString("evaluatCase"), EvaluatCase.class);
        List<EvaluatCaseSub> lists = JSON.parseArray(jsonObj.getString("evaluatCaseSub"), EvaluatCaseSub.class);
        return this.evaluatCaseService.updateEvaluatCaseVo(obj, lists, this.getCurUserProp());
    }
    /**
	 *
	    * @Title:selectEvaluatCaseByPrimaryKey
	    * @Description:  TODO(获取题目)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<EvaluatCase>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/selectEvaluatCaseByPrimaryKey.do")
	@ResponseBody
	public SingleResult<EvaluatCaseVo> selectEvaluatCaseByPrimaryKey(String id)
			throws Exception {
		return this.evaluatCaseService.selectEvaluatCaseByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteEvaluatCaseByEvaluatCaseId
	    * @Description:  TODO(删除题目)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/deleteEvaluatCaseByEvaluatCaseId.do")
	@ResponseBody
	public MessageResponse deleteEvaluatCaseByEvaluatCaseId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.evaluatCaseService.deleteEvaluatCaseByEvaluatCaseId(id,
				this.getCurUserProp());
	}

	/**
     *
     * @Title:getList
     * @Description:  TODO(获取题目列表)
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
        return this.evaluatCaseService.getList(params);
    }

    /**
     *
     * @Title:getById
     * @Description:  TODO(获取题目列表)
     * @param:        @throws Exception
     * @return:       Map<String,Object>
     * @throws
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @RequestMapping(value = "/getById.do")
    @ResponseBody
    public Map<String,Object> getById(String id) throws Exception{
        return this.evaluatCaseService.getById(id);
    }
}

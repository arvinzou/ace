package com.huacainfo.ace.portal.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.tools.CommonUtils;
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
import com.huacainfo.ace.portal.model.EvaluatType;
import com.huacainfo.ace.portal.service.EvaluatTypeService;
import com.huacainfo.ace.portal.vo.EvaluatTypeVo;
import com.huacainfo.ace.portal.vo.EvaluatTypeQVo;

@Controller
@RequestMapping("/evaluatType")
/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description:  TODO(评测类型)
 */
public class EvaluatTypeController extends PortalBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EvaluatTypeService evaluatTypeService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(评测类型分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<EvaluatTypeVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/findEvaluatTypeList.do")
	@ResponseBody
	public PageResult<EvaluatTypeVo> findEvaluatTypeList(EvaluatTypeQVo condition,
			PageParamNoChangeSord page) throws Exception {
        condition.setSyid(this.getCurUserProp().getActiveSyId());
        PageResult<EvaluatTypeVo> rst = this.evaluatTypeService.findEvaluatTypeList(condition, page.getStart(), page.getLimit(),
                page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertEvaluatType
	    * @Description:  TODO(添加评测类型)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/insertEvaluatType.do")
	@ResponseBody
	public MessageResponse insertEvaluatType(String jsons) throws Exception {
		EvaluatType obj = JSON.parseObject(jsons, EvaluatType.class);
		return this.evaluatTypeService
				.insertEvaluatType(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateEvaluatType
	    * @Description:  TODO(更新评测类型)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/updateEvaluatType.do")
	@ResponseBody
	public MessageResponse updateEvaluatType(String jsons) throws Exception {
		EvaluatType obj = JSON.parseObject(jsons, EvaluatType.class);
		return this.evaluatTypeService
				.updateEvaluatType(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectEvaluatTypeByPrimaryKey
	    * @Description:  TODO(获取评测类型)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<EvaluatType>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/selectEvaluatTypeByPrimaryKey.do")
	@ResponseBody
	public SingleResult<EvaluatTypeVo> selectEvaluatTypeByPrimaryKey(String id)
			throws Exception {
		return this.evaluatTypeService.selectEvaluatTypeByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteEvaluatTypeByEvaluatTypeId
	    * @Description:  TODO(删除评测类型)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/deleteEvaluatTypeByEvaluatTypeId.do")
	@ResponseBody
	public MessageResponse deleteEvaluatTypeByEvaluatTypeId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.evaluatTypeService.deleteEvaluatTypeByEvaluatTypeId(id,
				this.getCurUserProp());
	}

	/**
     *
     * @Title:getList
     * @Description:  TODO(获取评测类型列表)
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
        return this.evaluatTypeService.getList(params);
    }

    /**
     *
     * @Title:getById
     * @Description:  TODO(获取评测类型列表)
     * @param:        @throws Exception
     * @return:       Map<String,Object>
     * @throws
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @RequestMapping(value = "/getById.do")
    @ResponseBody
    public Map<String,Object> getById(String id) throws Exception{
        return this.evaluatTypeService.getById(id);
    }

	@RequestMapping(value = "/selectType.do")
	@ResponseBody
	public Map<String, Object> selectAuthor(String q, String id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("q", id);
		params.put("syid", this.getCurUserProp().getSyid());
		if (!CommonUtils.isBlank(q)) {
			params.put("q", q);
		}
		this.logger.info("", params);
		return this.evaluatTypeService.selectType(params);
	}
}

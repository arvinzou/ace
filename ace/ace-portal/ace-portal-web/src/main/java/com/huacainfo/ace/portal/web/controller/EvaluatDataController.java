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
import com.huacainfo.ace.portal.model.EvaluatData;
import com.huacainfo.ace.portal.service.EvaluatDataService;
import com.huacainfo.ace.portal.vo.EvaluatDataVo;
import com.huacainfo.ace.portal.vo.EvaluatDataQVo;

@Controller
@RequestMapping("/evaluatData")
/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description:  TODO(测评结果)
 */
public class EvaluatDataController extends PortalBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EvaluatDataService evaluatDataService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(测评结果分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<EvaluatDataVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/findEvaluatDataList.do")
	@ResponseBody
	public PageResult<EvaluatDataVo> findEvaluatDataList(EvaluatDataQVo condition,
			PageParamNoChangeSord page) throws Exception {
		condition.setSyid(this.getCurUserProp().getActiveSyId());
		PageResult<EvaluatDataVo> rst = this.evaluatDataService.findEvaluatDataList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertEvaluatData
	    * @Description:  TODO(添加测评结果)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/insertEvaluatData.do")
	@ResponseBody
	public MessageResponse insertEvaluatData(String jsons) throws Exception {
		EvaluatData obj = JSON.parseObject(jsons, EvaluatData.class);
		return this.evaluatDataService
				.insertEvaluatData(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateEvaluatData
	    * @Description:  TODO(更新测评结果)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/updateEvaluatData.do")
	@ResponseBody
	public MessageResponse updateEvaluatData(String jsons) throws Exception {
		EvaluatData obj = JSON.parseObject(jsons, EvaluatData.class);
		return this.evaluatDataService
				.updateEvaluatData(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectEvaluatDataByPrimaryKey
	    * @Description:  TODO(获取测评结果)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<EvaluatData>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/selectEvaluatDataByPrimaryKey.do")
	@ResponseBody
	public SingleResult<EvaluatDataVo> selectEvaluatDataByPrimaryKey(String id)
			throws Exception {
		return this.evaluatDataService.selectEvaluatDataByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteEvaluatDataByEvaluatDataId
	    * @Description:  TODO(删除测评结果)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/deleteEvaluatDataByEvaluatDataId.do")
	@ResponseBody
	public MessageResponse deleteEvaluatDataByEvaluatDataId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.evaluatDataService.deleteEvaluatDataByEvaluatDataId(id,
				this.getCurUserProp());
	}

	/**
     *
     * @Title:getList
     * @Description:  TODO(获取测评结果列表)
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
        return this.evaluatDataService.getList(params);
    }

    /**
     *
     * @Title:getById
     * @Description:  TODO(获取测评结果列表)
     * @param:        @throws Exception
     * @return:       Map<String,Object>
     * @throws
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @RequestMapping(value = "/getById.do")
    @ResponseBody
    public Map<String,Object> getById(String id) throws Exception{
        return this.evaluatDataService.getById(id);
    }
}

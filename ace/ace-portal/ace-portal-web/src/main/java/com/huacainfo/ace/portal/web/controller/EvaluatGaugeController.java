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
import com.huacainfo.ace.portal.model.EvaluatGauge;
import com.huacainfo.ace.portal.service.EvaluatGaugeService;
import com.huacainfo.ace.portal.vo.EvaluatGaugeVo;
import com.huacainfo.ace.portal.vo.EvaluatGaugeQVo;

@Controller
@RequestMapping("/evaluatGauge")
/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description:  TODO(评测量表)
 */
public class EvaluatGaugeController extends PortalBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EvaluatGaugeService evaluatGaugeService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(评测量表分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<EvaluatGaugeVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/findEvaluatGaugeList.do")
	@ResponseBody
	public PageResult<EvaluatGaugeVo> findEvaluatGaugeList(EvaluatGaugeQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<EvaluatGaugeVo> rst = this.evaluatGaugeService
				.findEvaluatGaugeList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertEvaluatGauge
	    * @Description:  TODO(添加评测量表)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/insertEvaluatGauge.do")
	@ResponseBody
	public MessageResponse insertEvaluatGauge(String jsons) throws Exception {
		EvaluatGauge obj = JSON.parseObject(jsons, EvaluatGauge.class);
		return this.evaluatGaugeService
				.insertEvaluatGauge(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateEvaluatGauge
	    * @Description:  TODO(更新评测量表)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/updateEvaluatGauge.do")
	@ResponseBody
	public MessageResponse updateEvaluatGauge(String jsons) throws Exception {
		EvaluatGauge obj = JSON.parseObject(jsons, EvaluatGauge.class);
		return this.evaluatGaugeService
				.updateEvaluatGauge(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectEvaluatGaugeByPrimaryKey
	    * @Description:  TODO(获取评测量表)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<EvaluatGauge>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/selectEvaluatGaugeByPrimaryKey.do")
	@ResponseBody
	public SingleResult<EvaluatGaugeVo> selectEvaluatGaugeByPrimaryKey(String id)
			throws Exception {
		return this.evaluatGaugeService.selectEvaluatGaugeByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteEvaluatGaugeByEvaluatGaugeId
	    * @Description:  TODO(删除评测量表)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/deleteEvaluatGaugeByEvaluatGaugeId.do")
	@ResponseBody
	public MessageResponse deleteEvaluatGaugeByEvaluatGaugeId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.evaluatGaugeService.deleteEvaluatGaugeByEvaluatGaugeId(id,
				this.getCurUserProp());
	}

	/**
     *
     * @Title:getList
     * @Description:  TODO(获取评测量表列表)
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
        return this.evaluatGaugeService.getList(params);
    }

    /**
     *
     * @Title:getById
     * @Description:  TODO(获取评测量表列表)
     * @param:        @throws Exception
     * @return:       Map<String,Object>
     * @throws
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @RequestMapping(value = "/getById.do")
    @ResponseBody
    public Map<String,Object> getById(String id) throws Exception{
        return this.evaluatGaugeService.getById(id);
    }
}

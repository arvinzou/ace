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
import com.huacainfo.ace.portal.model.EvaluatCmt;
import com.huacainfo.ace.portal.service.EvaluatCmtService;
import com.huacainfo.ace.portal.vo.EvaluatCmtVo;
import com.huacainfo.ace.portal.vo.EvaluatCmtQVo;

@Controller
@RequestMapping("/evaluatCmt")
/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description:  TODO(评测评论)
 */
public class EvaluatCmtController extends PortalBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EvaluatCmtService evaluatCmtService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(评测评论分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<EvaluatCmtVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/findEvaluatCmtList.do")
	@ResponseBody
	public PageResult<EvaluatCmtVo> findEvaluatCmtList(EvaluatCmtQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<EvaluatCmtVo> rst = this.evaluatCmtService
				.findEvaluatCmtList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertEvaluatCmt
	    * @Description:  TODO(添加评测评论)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/insertEvaluatCmt.do")
	@ResponseBody
	public MessageResponse insertEvaluatCmt(String jsons) throws Exception {
		EvaluatCmt obj = JSON.parseObject(jsons, EvaluatCmt.class);
		return this.evaluatCmtService
				.insertEvaluatCmt(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateEvaluatCmt
	    * @Description:  TODO(更新评测评论)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/updateEvaluatCmt.do")
	@ResponseBody
	public MessageResponse updateEvaluatCmt(String jsons) throws Exception {
		EvaluatCmt obj = JSON.parseObject(jsons, EvaluatCmt.class);
		return this.evaluatCmtService
				.updateEvaluatCmt(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectEvaluatCmtByPrimaryKey
	    * @Description:  TODO(获取评测评论)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<EvaluatCmt>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/selectEvaluatCmtByPrimaryKey.do")
	@ResponseBody
	public SingleResult<EvaluatCmtVo> selectEvaluatCmtByPrimaryKey(String id)
			throws Exception {
		return this.evaluatCmtService.selectEvaluatCmtByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteEvaluatCmtByEvaluatCmtId
	    * @Description:  TODO(删除评测评论)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	@RequestMapping(value = "/deleteEvaluatCmtByEvaluatCmtId.do")
	@ResponseBody
	public MessageResponse deleteEvaluatCmtByEvaluatCmtId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.evaluatCmtService.deleteEvaluatCmtByEvaluatCmtId(id,
				this.getCurUserProp());
	}

	/**
     *
     * @Title:getList
     * @Description:  TODO(获取评测评论列表)
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
        return this.evaluatCmtService.getList(params);
    }

    /**
     *
     * @Title:getById
     * @Description:  TODO(获取评测评论列表)
     * @param:        @throws Exception
     * @return:       Map<String,Object>
     * @throws
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @RequestMapping(value = "/getById.do")
    @ResponseBody
    public Map<String,Object> getById(String id) throws Exception{
        return this.evaluatCmtService.getById(id);
    }
}

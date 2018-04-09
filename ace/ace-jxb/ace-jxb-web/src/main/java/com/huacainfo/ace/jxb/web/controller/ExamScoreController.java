package com.huacainfo.ace.jxb.web.controller;

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
import com.huacainfo.ace.jxb.model.ExamScore;
import com.huacainfo.ace.jxb.service.ExamScoreService;
import com.huacainfo.ace.jxb.vo.ExamScoreVo;
import com.huacainfo.ace.jxb.vo.ExamScoreQVo;

@Controller
@RequestMapping("/examScore")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(评测)
 */
public class ExamScoreController extends LiveBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExamScoreService examScoreService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(评测分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<ExamScoreVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/findExamScoreList")
	@ResponseBody
	public PageResult<ExamScoreVo> findExamScoreList(ExamScoreQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<ExamScoreVo> rst = this.examScoreService
				.findExamScoreList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertExamScore
	    * @Description:  TODO(添加评测)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/insertExamScore")
	@ResponseBody
	public MessageResponse insertExamScore(String jsons) throws Exception {
		ExamScore obj = JSON.parseObject(jsons, ExamScore.class);
		return this.examScoreService
				.insertExamScore(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateExamScore
	    * @Description:  TODO(更新评测)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/updateExamScore")
	@ResponseBody
	public MessageResponse updateExamScore(String jsons) throws Exception {
		ExamScore obj = JSON.parseObject(jsons, ExamScore.class);
		return this.examScoreService
				.updateExamScore(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectExamScoreByPrimaryKey
	    * @Description:  TODO(获取评测)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<ExamScore>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/selectExamScoreByPrimaryKey")
	@ResponseBody
	public SingleResult<ExamScoreVo> selectExamScoreByPrimaryKey(String id)
			throws Exception {
		return this.examScoreService.selectExamScoreByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteExamScoreByExamScoreId
	    * @Description:  TODO(删除评测)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/deleteExamScoreByExamScoreId")
	@ResponseBody
	public MessageResponse deleteExamScoreByExamScoreId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.examScoreService.deleteExamScoreByExamScoreId(id,
				this.getCurUserProp());
	}
}

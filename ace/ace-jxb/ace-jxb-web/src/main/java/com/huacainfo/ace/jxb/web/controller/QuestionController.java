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
import com.huacainfo.ace.jxb.model.Question;
import com.huacainfo.ace.jxb.service.QuestionService;
import com.huacainfo.ace.jxb.vo.QuestionVo;
import com.huacainfo.ace.jxb.vo.QuestionQVo;

@Controller
@RequestMapping("/question")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(考题)
 */
public class QuestionController extends LiveBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private QuestionService questionService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(考题分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<QuestionVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/findQuestionList")
	@ResponseBody
	public PageResult<QuestionVo> findQuestionList(QuestionQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<QuestionVo> rst = this.questionService
				.findQuestionList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertQuestion
	    * @Description:  TODO(添加考题)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/insertQuestion")
	@ResponseBody
	public MessageResponse insertQuestion(String jsons) throws Exception {
		Question obj = JSON.parseObject(jsons, Question.class);
		return this.questionService
				.insertQuestion(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateQuestion
	    * @Description:  TODO(更新考题)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/updateQuestion")
	@ResponseBody
	public MessageResponse updateQuestion(String jsons) throws Exception {
		Question obj = JSON.parseObject(jsons, Question.class);
		return this.questionService
				.updateQuestion(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectQuestionByPrimaryKey
	    * @Description:  TODO(获取考题)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Question>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/selectQuestionByPrimaryKey")
	@ResponseBody
	public SingleResult<QuestionVo> selectQuestionByPrimaryKey(String id)
			throws Exception {
		return this.questionService.selectQuestionByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteQuestionByQuestionId
	    * @Description:  TODO(删除考题)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/deleteQuestionByQuestionId")
	@ResponseBody
	public MessageResponse deleteQuestionByQuestionId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.questionService.deleteQuestionByQuestionId(id,
				this.getCurUserProp());
	}
}

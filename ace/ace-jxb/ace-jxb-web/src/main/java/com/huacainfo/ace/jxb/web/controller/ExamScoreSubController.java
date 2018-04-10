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
import com.huacainfo.ace.jxb.model.ExamScoreSub;
import com.huacainfo.ace.jxb.service.ExamScoreSubService;
import com.huacainfo.ace.jxb.vo.ExamScoreSubVo;
import com.huacainfo.ace.jxb.vo.ExamScoreSubQVo;

@Controller
@RequestMapping("/examScoreSub")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(评测结果)
 */
public class ExamScoreSubController extends LiveBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExamScoreSubService examScoreSubService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(评测结果分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<ExamScoreSubVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/findExamScoreSubList")
	@ResponseBody
	public PageResult<ExamScoreSubVo> findExamScoreSubList(ExamScoreSubQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<ExamScoreSubVo> rst = this.examScoreSubService
				.findExamScoreSubList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertExamScoreSub
	    * @Description:  TODO(添加评测结果)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/insertExamScoreSub")
	@ResponseBody
	public MessageResponse insertExamScoreSub(String jsons) throws Exception {
		ExamScoreSub obj = JSON.parseObject(jsons, ExamScoreSub.class);
		return this.examScoreSubService
				.insertExamScoreSub(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateExamScoreSub
	    * @Description:  TODO(更新评测结果)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/updateExamScoreSub")
	@ResponseBody
	public MessageResponse updateExamScoreSub(String jsons) throws Exception {
		ExamScoreSub obj = JSON.parseObject(jsons, ExamScoreSub.class);
		return this.examScoreSubService
				.updateExamScoreSub(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectExamScoreSubByPrimaryKey
	    * @Description:  TODO(获取评测结果)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<ExamScoreSub>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/selectExamScoreSubByPrimaryKey")
	@ResponseBody
	public SingleResult<ExamScoreSubVo> selectExamScoreSubByPrimaryKey(String id)
			throws Exception {
		return this.examScoreSubService.selectExamScoreSubByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteExamScoreSubByExamScoreSubId
	    * @Description:  TODO(删除评测结果)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/deleteExamScoreSubByExamScoreSubId")
	@ResponseBody
	public MessageResponse deleteExamScoreSubByExamScoreSubId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.examScoreSubService.deleteExamScoreSubByExamScoreSubId(id,
				this.getCurUserProp());
	}
}

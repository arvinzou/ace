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
import com.huacainfo.ace.jxb.model.TestPaper;
import com.huacainfo.ace.jxb.service.TestPaperService;
import com.huacainfo.ace.jxb.vo.TestPaperVo;
import com.huacainfo.ace.jxb.vo.TestPaperQVo;

@Controller
@RequestMapping("/testPaper")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(试卷)
 */
public class TestPaperController extends LiveBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TestPaperService testPaperService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(试卷分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<TestPaperVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/findTestPaperList")
	@ResponseBody
	public PageResult<TestPaperVo> findTestPaperList(TestPaperQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<TestPaperVo> rst = this.testPaperService
				.findTestPaperList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertTestPaper
	    * @Description:  TODO(添加试卷)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/insertTestPaper")
	@ResponseBody
	public MessageResponse insertTestPaper(String jsons) throws Exception {
		TestPaper obj = JSON.parseObject(jsons, TestPaper.class);
		return this.testPaperService
				.insertTestPaper(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateTestPaper
	    * @Description:  TODO(更新试卷)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/updateTestPaper")
	@ResponseBody
	public MessageResponse updateTestPaper(String jsons) throws Exception {
		TestPaper obj = JSON.parseObject(jsons, TestPaper.class);
		return this.testPaperService
				.updateTestPaper(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectTestPaperByPrimaryKey
	    * @Description:  TODO(获取试卷)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<TestPaper>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/selectTestPaperByPrimaryKey")
	@ResponseBody
	public SingleResult<TestPaperVo> selectTestPaperByPrimaryKey(String id)
			throws Exception {
		return this.testPaperService.selectTestPaperByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteTestPaperByTestPaperId
	    * @Description:  TODO(删除试卷)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/deleteTestPaperByTestPaperId")
	@ResponseBody
	public MessageResponse deleteTestPaperByTestPaperId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.testPaperService.deleteTestPaperByTestPaperId(id,
				this.getCurUserProp());
	}
}

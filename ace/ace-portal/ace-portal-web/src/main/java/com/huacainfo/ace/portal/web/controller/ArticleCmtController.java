package com.huacainfo.ace.portal.web.controller;

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
import com.huacainfo.ace.portal.model.ArticleCmt;
import com.huacainfo.ace.portal.service.ArticleCmtService;
import com.huacainfo.ace.portal.vo.ArticleCmtVo;
import com.huacainfo.ace.portal.vo.ArticleCmtQVo;

import java.util.Map;

@Controller
@RequestMapping("/articleCmt")
/**
 * @author: 陈晓克
 * @version: 2018-05-04
 * @Description:  TODO(模板)
 */
public class ArticleCmtController extends PortalBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ArticleCmtService articleCmtService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(模板分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<ArticleCmtVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/findArticleCmtList.do")
	@ResponseBody
	public PageResult<ArticleCmtVo> findArticleCmtList(ArticleCmtQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<ArticleCmtVo> rst = this.articleCmtService
				.findArticleCmtList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertArticleCmt
	    * @Description:  TODO(添加模板)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/insertArticleCmt.do")
	@ResponseBody
	public MessageResponse insertArticleCmt(String jsons) throws Exception {
		ArticleCmt obj = JSON.parseObject(jsons, ArticleCmt.class);
		return this.articleCmtService.insertArticleCmt(obj);
	}

    /**
	 *
	    * @Title:deleteArticleCmtByArticleCmtId
	    * @Description:  TODO(删除模板)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/deleteArticleCmtByArticleCmtId.do")
	@ResponseBody
	public MessageResponse deleteArticleCmtByArticleCmtId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.articleCmtService.deleteArticleCmtByArticleCmtId(id,
				this.getCurUserProp());
	}

	/**
	 *
	 * @Title:getList
	 * @Description:  TODO(获取页面文章评论列表)
	 * @param:        @throws Exception
	 * @return:       Map<String,Object>
	 * @throws
	 * @author: 陈晓克
	 * @version: 2018-05-04
	 */
	@RequestMapping(value = "/getList.do")
	@ResponseBody
	public Map<String,Object> getList() throws Exception{
		return this.articleCmtService.getList(this.getParams());
	}
}

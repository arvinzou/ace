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
import com.huacainfo.ace.portal.model.Article;
import com.huacainfo.ace.portal.service.ArticleService;
import com.huacainfo.ace.portal.vo.ArticleVo;
import com.huacainfo.ace.portal.vo.ArticleQVo;

import java.util.Map;

@Controller
@RequestMapping("/article")
/**
 * @author: 陈晓克
 * @version: 2018-05-04
 * @Description:  TODO(模板)
 */
public class ArticleController extends PortalBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ArticleService articleService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(模板分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<ArticleVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/findArticleList.do")
	@ResponseBody
	public PageResult<ArticleVo> findArticleList(ArticleQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<ArticleVo> rst = this.articleService
				.findArticleList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertArticle
	    * @Description:  TODO(添加模板)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/insertArticle.do")
	@ResponseBody
	public MessageResponse insertArticle(String jsons) throws Exception {
		Article obj = JSON.parseObject(jsons, Article.class);
		return this.articleService
				.insertArticle(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateArticle
	    * @Description:  TODO(更新模板)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/updateArticle.do")
	@ResponseBody
	public MessageResponse updateArticle(String jsons) throws Exception {
		Article obj = JSON.parseObject(jsons, Article.class);
		return this.articleService
				.updateArticle(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "deleteArticleById.do")
	@ResponseBody
	public MessageResponse deleteArticle(String id)throws Exception{
		return this.articleService.deleteArticleByArticleId(id, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectArticleByPrimaryKey
	    * @Description:  TODO(获取模板)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Article>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/selectArticleByPrimaryKey.do")
	@ResponseBody
	public SingleResult<ArticleVo> selectArticleByPrimaryKey(String id)
			throws Exception {
		return this.articleService.selectArticleByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteArticleByArticleId
	    * @Description:  TODO(删除模板)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/deleteArticleByArticleId.do")
	@ResponseBody
	public MessageResponse deleteArticleByArticleId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.articleService.deleteArticleByArticleId(id,
				this.getCurUserProp());
	}

	/**
	 *
	 * @Title:getList
	 * @Description:  TODO(获取页面文章列表)
	 * @param:        @throws Exception
	 * @return:       Map<String,Object>
	 * @throws
	 * @author: 陈晓克
	 * @version: 2018-05-04
	 */
	@RequestMapping(value = "/getList.do")
	@ResponseBody
	public Map<String,Object> getList() throws Exception{
		return this.articleService.getList(this.getParams());
	}
}

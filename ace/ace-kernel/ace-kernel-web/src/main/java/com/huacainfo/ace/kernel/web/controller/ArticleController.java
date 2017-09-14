package com.huacainfo.ace.kernel.web.controller;

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
import com.huacainfo.ace.kernel.model.Article;
import com.huacainfo.ace.kernel.service.ArticleService;
import com.huacainfo.ace.kernel.vo.ArticleVo;
import com.huacainfo.ace.kernel.vo.ArticleQVo;

@Controller
@RequestMapping("/article")
public class ArticleController extends KernelBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ArticleService articleService;

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

	@RequestMapping(value = "/insertArticle.do")
	@ResponseBody
	public MessageResponse insertArticle(String jsons) throws Exception {
		Article obj = JSON.parseObject(jsons, Article.class);
		return this.articleService
				.insertArticle(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateArticle.do")
	@ResponseBody
	public MessageResponse updateArticle(String jsons) throws Exception {
		Article obj = JSON.parseObject(jsons, Article.class);
		return this.articleService
				.updateArticle(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectArticleByPrimaryKey.do")
	@ResponseBody
	public SingleResult<Article> selectArticleByPrimaryKey(String id)
			throws Exception {
		return this.articleService.selectArticleByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteArticleByArticleId.do")
	@ResponseBody
	public MessageResponse deleteArticleByArticleId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.articleService.deleteArticleByArticleId(id,
				this.getCurUserProp());
	}
}

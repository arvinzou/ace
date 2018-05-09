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
import com.huacainfo.ace.portal.model.ArticleCategory;
import com.huacainfo.ace.portal.service.ArticleCategoryService;
import com.huacainfo.ace.portal.vo.ArticleCategoryVo;
import com.huacainfo.ace.portal.vo.ArticleCategoryQVo;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/articleCategory")
/**
 * @author: 陈晓克
 * @version: 2018-05-04
 * @Description:  TODO(模板)
 */
public class ArticleCategoryController extends PortalBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ArticleCategoryService articleCategoryService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(模板分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<ArticleCategoryVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/findArticleCategoryList.do")
	@ResponseBody
	public PageResult<ArticleCategoryVo> findArticleCategoryList(ArticleCategoryQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<ArticleCategoryVo> rst = this.articleCategoryService
				.findArticleCategoryList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertArticleCategory
	    * @Description:  TODO(添加模板)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/insertArticleCategory.do")
	@ResponseBody
	public MessageResponse insertArticleCategory(String jsons) throws Exception {
		ArticleCategory obj = JSON.parseObject(jsons, ArticleCategory.class);
		return this.articleCategoryService
				.insertArticleCategory(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateArticleCategory
	    * @Description:  TODO(更新模板)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/updateArticleCategory.do")
	@ResponseBody
	public MessageResponse updateArticleCategory(String jsons) throws Exception {
		ArticleCategory obj = JSON.parseObject(jsons, ArticleCategory.class);
		return this.articleCategoryService
				.updateArticleCategory(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectArticleCategoryByPrimaryKey
	    * @Description:  TODO(获取模板)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<ArticleCategory>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/selectArticleCategoryByPrimaryKey.do")
	@ResponseBody
	public SingleResult<ArticleCategoryVo> selectArticleCategoryByPrimaryKey(String id)
			throws Exception {
		return this.articleCategoryService.selectArticleCategoryByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteArticleCategoryByArticleCategoryId
	    * @Description:  TODO(删除模板)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	@RequestMapping(value = "/deleteArticleCategoryByArticleCategoryId.do")
	@ResponseBody
	public MessageResponse deleteArticleCategoryByArticleCategoryId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.articleCategoryService.deleteArticleCategoryByArticleCategoryId(id,
				this.getCurUserProp());
	}

	/**
	 *
	 * @Title:getList
	 * @Description:  TODO(获取页面文章栏目列表)
	 * @param:        @throws Exception
	 * @return:       Map<String,Object>
	 * @throws
	 * @author: 陈晓克
	 * @version: 2018-05-04
	 */
	@RequestMapping(value = "/getList.do")
	@ResponseBody
	public Map<String,Object> getList() throws Exception{
		return this.articleCategoryService.getList(this.getParams());
	}
	/**
	 *
	 * @Title:updateSort
	 * @Description:  TODO(排序)
	 * @param:        ids
	 * @return:       MessageResponse
	 * @throws
	 * @author: 陈晓克
	 * @version: 2018-05-09
	 */
	@RequestMapping(value = "/updateSort.do")
	@ResponseBody
	public MessageResponse updateSort(String ids) throws Exception{
		return this.articleCategoryService.updateSort(ids.split(","));
	}
}

package com.huacainfo.ace.portal.service.impl;


import java.util.*;

import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.dao.ArticleCategoryDao;
import com.huacainfo.ace.portal.model.ArticleCategory;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.ArticleCategoryService;
import com.huacainfo.ace.portal.vo.ArticleCategoryVo;
import com.huacainfo.ace.portal.vo.ArticleCategoryQVo;
@Service("articleCategoryService")
/**
 * @author: 陈晓克
 * @version: 2018-05-04
 * @Description:  TODO(模板)
 */
public class ArticleCategoryServiceImpl implements ArticleCategoryService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ArticleCategoryDao articleCategoryDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(模板分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<ArticleCategoryVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
    @Override
	public PageResult<ArticleCategoryVo> findArticleCategoryList(ArticleCategoryQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<ArticleCategoryVo> rst = new PageResult<ArticleCategoryVo>();
		List<ArticleCategoryVo> list = this.articleCategoryDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.articleCategoryDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertArticleCategory
	    * @Description:  TODO(添加模板)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
    @Override
	public MessageResponse insertArticleCategory(ArticleCategory o, UserProp userProp)
			throws Exception {
		o.setId(GUIDUtil.getGUID());
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getTplPageId())) {
			return new MessageResponse(1, "所属页面不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "页面名称不能为空！");
		}
		int temp = this.articleCategoryDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "模板名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.articleCategoryDao.insert(o);
		this.dataBaseLogService.log("添加模板", "模板", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加模板完成！");
	}
    /**
	 *
	    * @Title:updateArticleCategory
	    * @Description:  TODO(更新模板)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
    @Override
	public MessageResponse updateArticleCategory(ArticleCategory o, UserProp userProp)
			throws Exception {

		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getTplPageId())) {
			return new MessageResponse(1, "所属页面不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "页面名称不能为空！");
		}
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.articleCategoryDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更模板", "模板", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更模板完成！");
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
    @Override
	public SingleResult<ArticleCategoryVo> selectArticleCategoryByPrimaryKey(String id) throws Exception {
		SingleResult<ArticleCategoryVo> rst = new SingleResult<ArticleCategoryVo>();
		rst.setValue(this.articleCategoryDao.selectByPrimaryKey(id));
		return rst;
	}
    /**
	 *
	    * @Title:deleteArticleCategoryByArticleCategoryId
	    * @Description:  TODO(删除模板)
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
    @Override
	public MessageResponse deleteArticleCategoryByArticleCategoryId(String id,
			UserProp userProp) throws Exception {
		this.articleCategoryDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除模板", "模板", String.valueOf(id),
				String.valueOf(id), "模板", userProp);
		return new MessageResponse(0, "模板删除完成！");
	}

	/**
	 *
	 * @Title:getList
	 * @Description:  TODO(获取页面文章分类列表)
	 * @param:        @throws Exception
	 * @return:       Map<String,Object>
	 * @throws
	 * @author: 陈晓克
	 * @version: 2018-05-04
	 */
	@Override
	public Map<String,Object> getList(Map<String,Object> params) throws Exception{
		Map<String,Object> rst=new HashMap<>();
		rst.put("status",0);
		rst.put("data",this.articleCategoryDao.getList(params));
		return rst;
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
	@Override
	public MessageResponse updateSort(String []ids) throws Exception{
		int i=0;
		for(String id:ids){
			this.articleCategoryDao.updateSort(id,i);
			i++;
		}
		return new MessageResponse(0, "OK！");
	}
}

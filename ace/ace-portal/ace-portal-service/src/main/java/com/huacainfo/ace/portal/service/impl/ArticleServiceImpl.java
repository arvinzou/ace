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
import com.huacainfo.ace.portal.dao.ArticleDao;
import com.huacainfo.ace.portal.model.Article;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.ArticleService;
import com.huacainfo.ace.portal.vo.ArticleVo;
import com.huacainfo.ace.portal.vo.ArticleQVo;
@Service("articleService")
/**
 * @author: 陈晓克
 * @version: 2018-05-04
 * @Description:  TODO(文章)
 */
public class ArticleServiceImpl implements ArticleService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(文章分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<ArticleVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
    @Override
	public PageResult<ArticleVo> findArticleList(ArticleQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<ArticleVo> rst = new PageResult<ArticleVo>();
		List<ArticleVo> list = this.articleDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.articleDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertArticle
	    * @Description:  TODO(添加文章)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
    @Override
	public MessageResponse insertArticle(Article o, UserProp userProp)
			throws Exception {
		o.setId(GUIDUtil.getGUID());
		o.setHitNum(Long.valueOf(0));
		o.setLikeNum(Long.valueOf(0));
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getTplPageId())) {
			return new MessageResponse(1, "所属页面不能为空！");
		}
		if (CommonUtils.isBlank(o.getArticleCategory())) {
			return new MessageResponse(1, "所属栏目不能为空！");
		}
		if (CommonUtils.isBlank(o.getTitle())) {
			return new MessageResponse(1, "标题不能为空！");
		}
		if (CommonUtils.isBlank(o.getCover())) {
			return new MessageResponse(1, "封面不能为空！");
		}
		if (CommonUtils.isBlank(o.getMediType())) {
			return new MessageResponse(1, "媒体类型不能为空！");
		}
		if (CommonUtils.isBlank(o.getLikeNum())) {
			return new MessageResponse(1, "点赞次数不能为空！");
		}
		if (CommonUtils.isBlank(o.getHitNum())) {
			return new MessageResponse(1, "浏览次数不能为空！");
		}
		int temp = this.articleDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "文章名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.articleDao.insert(o);
		this.dataBaseLogService.log("添加文章", "文章", "", o.getTitle(),
				o.getTitle(), userProp);
		return new MessageResponse(0, "添加文章完成！");
	}
    /**
	 *
	    * @Title:updateArticle
	    * @Description:  TODO(更新文章)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
    @Override
	public MessageResponse updateArticle(Article o, UserProp userProp)
			throws Exception {

		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getTplPageId())) {
			return new MessageResponse(1, "所属页面不能为空！");
		}
		if (CommonUtils.isBlank(o.getArticleCategory())) {
			return new MessageResponse(1, "所属栏目不能为空！");
		}
		if (CommonUtils.isBlank(o.getTitle())) {
			return new MessageResponse(1, "标题不能为空！");
		}
		if (CommonUtils.isBlank(o.getCover())) {
			return new MessageResponse(1, "封面不能为空！");
		}
		if (CommonUtils.isBlank(o.getMediType())) {
			return new MessageResponse(1, "媒体类型不能为空！");
		}
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.articleDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更文章", "文章", "", o.getTitle(),
				o.getTitle(), userProp);
		return new MessageResponse(0, "变更文章完成！");
	}

    /**
	 *
	    * @Title:selectArticleByPrimaryKey
	    * @Description:  TODO(获取文章)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Article>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
    @Override
	public SingleResult<ArticleVo> selectArticleByPrimaryKey(String id) throws Exception {
		SingleResult<ArticleVo> rst = new SingleResult<ArticleVo>();
		rst.setValue(this.articleDao.selectByPrimaryKey(id));
		return rst;
	}
    /**
	 *
	    * @Title:deleteArticleByArticleId
	    * @Description:  TODO(删除文章)
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
    @Override
	public MessageResponse deleteArticleByArticleId(String id,
			UserProp userProp) throws Exception {
		this.articleDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除文章", "文章", String.valueOf(id),
				String.valueOf(id), "文章", userProp);
		return new MessageResponse(0, "文章删除完成！");
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
	@Override
	public Map<String,Object> getList(Map<String,Object> params) throws Exception{
		Map<String,Object> rst=new HashMap<>();
		rst.put("status",0);
		rst.put("data",this.articleDao.getList(params));
		return rst;
	}
}

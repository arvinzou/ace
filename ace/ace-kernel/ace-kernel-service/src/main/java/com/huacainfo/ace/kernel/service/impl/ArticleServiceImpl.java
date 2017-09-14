package com.huacainfo.ace.kernel.service.impl;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.kernel.dao.ArticleDao;
import com.huacainfo.ace.kernel.model.Article;
import com.huacainfo.ace.kernel.service.ArticleService;
import com.huacainfo.ace.kernel.vo.ArticleQVo;
import com.huacainfo.ace.kernel.vo.ArticleVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

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

	public MessageResponse insertArticle(Article o, UserProp userProp)
			throws Exception {
		o.setId(UUID.randomUUID().toString());
		
		int temp = this.articleDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "推文名称重复！");
		}
		//o.setCreateDate(new Date());

		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.articleDao.insert(o);
		this.dataBaseLogService.log("添加推文", "推文", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加推文完成！");
	}

	public MessageResponse updateArticle(Article o, UserProp userProp)
			throws Exception {
		
		
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.articleDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更推文", "推文", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更推文完成！");
	}

	public SingleResult<Article> selectArticleByPrimaryKey(String id) throws Exception {
		SingleResult<Article> rst = new SingleResult<Article>();
		rst.setValue(this.articleDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteArticleByArticleId(String id,
			UserProp userProp) throws Exception {
		this.articleDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除推文", "推文", String.valueOf(id),
				String.valueOf(id), "推文", userProp);
		return new MessageResponse(0, "推文删除完成！");
	}
}

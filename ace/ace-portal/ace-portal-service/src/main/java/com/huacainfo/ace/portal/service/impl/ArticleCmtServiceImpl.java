package com.huacainfo.ace.portal.service.impl;


import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.dao.ArticleCmtDao;
import com.huacainfo.ace.portal.model.ArticleCmt;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.ArticleCmtService;
import com.huacainfo.ace.portal.vo.ArticleCmtVo;
import com.huacainfo.ace.portal.vo.ArticleCmtQVo;
@Service("articleCmtService")
/**
 * @author: 陈晓克
 * @version: 2018-05-04
 * @Description:  TODO(模板)
 */
public class ArticleCmtServiceImpl implements ArticleCmtService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ArticleCmtDao articleCmtDao;
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
	 		* @return:       PageResult<ArticleCmtVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
    @Override
	public PageResult<ArticleCmtVo> findArticleCmtList(ArticleCmtQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<ArticleCmtVo> rst = new PageResult<ArticleCmtVo>();
		List<ArticleCmtVo> list = this.articleCmtDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.articleCmtDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertArticleCmt
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
	public MessageResponse insertArticleCmt(ArticleCmt o, UserProp userProp)
			throws Exception {
		o.setId(UUID.randomUUID().toString());
		//o.setId(String.valueOf(new Date().getTime()));
		
		int temp = this.articleCmtDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "模板名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");

		this.articleCmtDao.insert(o);

		return new MessageResponse(0, "添加模板完成！");
	}


    /**
	 *
	    * @Title:selectArticleCmtByPrimaryKey
	    * @Description:  TODO(获取模板)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<ArticleCmt>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
    @Override
	public SingleResult<ArticleCmtVo> selectArticleCmtByPrimaryKey(String id) throws Exception {
		SingleResult<ArticleCmtVo> rst = new SingleResult<ArticleCmtVo>();
		rst.setValue(this.articleCmtDao.selectByPrimaryKey(id));
		return rst;
	}
    /**
	 *
	    * @Title:deleteArticleCmtByArticleCmtId
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
	public MessageResponse deleteArticleCmtByArticleCmtId(String id,
			UserProp userProp) throws Exception {
		this.articleCmtDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除模板", "模板", String.valueOf(id),
				String.valueOf(id), "模板", userProp);
		return new MessageResponse(0, "模板删除完成！");
	}

	/**
	 *
	 * @Title:getList
	 * @Description:  TODO(获取文章留言列表)
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
		rst.put("data",this.articleCmtDao.getList(params));
		return rst;
	}
}

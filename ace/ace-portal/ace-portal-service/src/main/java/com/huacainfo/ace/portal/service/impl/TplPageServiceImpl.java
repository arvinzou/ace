package com.huacainfo.ace.portal.service.impl;


import java.util.*;

import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.dao.ArticleCategoryDao;
import com.huacainfo.ace.portal.dao.ArticleDao;
import com.huacainfo.ace.portal.model.Article;
import com.huacainfo.ace.portal.model.ArticleCategory;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.apache.ibatis.session.Configuration;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.dao.TplPageDao;
import com.huacainfo.ace.portal.model.TplPage;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.TplPageService;
import com.huacainfo.ace.portal.vo.TplPageVo;
import com.huacainfo.ace.portal.vo.TplPageQVo;
@Service("tplPageService")
/**
 * @author: 陈晓克
 * @version: 2018-05-04
 * @Description:  TODO(页面)
 */
public class TplPageServiceImpl implements TplPageService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TplPageDao tplPageDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Autowired
	private ArticleCategoryDao articleCategoryDao;
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private SqlSessionTemplate sqlSession;

    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(页面分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<TplPageVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
    @Override
	public PageResult<TplPageVo> findTplPageList(TplPageQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<TplPageVo> rst = new PageResult<TplPageVo>();
		List<TplPageVo> list = this.tplPageDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.tplPageDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertTplPage
	    * @Description:  TODO(添加页面)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
    @Override
	public MessageResponse insertTplPage(TplPage o, UserProp userProp)
			throws Exception {
		o.setId(GUIDUtil.getGUID());
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getTplId())) {
			return new MessageResponse(1, "所属模板不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "页面名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getLastModifyDate())) {
			return new MessageResponse(1, "最后更新时间不能为空！");
		}
		int temp = this.tplPageDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "页面名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.tplPageDao.insert(o);
		this.dataBaseLogService.log("添加页面", "页面", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加页面完成！");
	}
    /**
	 *
	    * @Title:updateTplPage
	    * @Description:  TODO(更新页面)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
    @Override
	public MessageResponse updateTplPage(TplPage o, UserProp userProp)
			throws Exception {

		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getTplId())) {
			return new MessageResponse(1, "所属模板不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "页面名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getLastModifyDate())) {
			return new MessageResponse(1, "最后更新时间不能为空！");
		}
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.tplPageDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更页面", "页面", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更页面完成！");
	}

    /**
	 *
	    * @Title:selectTplPageByPrimaryKey
	    * @Description:  TODO(获取页面)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<TplPage>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
    @Override
	public SingleResult<TplPageVo> selectTplPageByPrimaryKey(String id) throws Exception {
		SingleResult<TplPageVo> rst = new SingleResult<TplPageVo>();
		rst.setValue(this.tplPageDao.selectByPrimaryKey(id));
		return rst;
	}
    /**
	 *
	    * @Title:deleteTplPageByTplPageId
	    * @Description:  TODO(删除页面)
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
    @Override
	public MessageResponse deleteTplPageByTplPageId(String id,
			UserProp userProp) throws Exception {
		this.tplPageDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除页面", "页面", String.valueOf(id),
				String.valueOf(id), "页面", userProp);
		return new MessageResponse(0, "页面删除完成！");
	}

	/**
	 *
	 * @Title:getList
	 * @Description:  TODO(获取个人页面列表)
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
		rst.put("data",this.tplPageDao.getList(params));
		return rst;
	}

	/**
	 *
	 * @Title:insertTplPageByTplId
	 * @Description:  TODO(根据模板ID创建默认页面元素)
	 * @param:        @param tplId
	 * @param:        @param userProp
	 * @param:        @throws Exception
	 * @return:       MessageResponse
	 * @throws
	 * @author: 陈晓克
	 * @version: 2018-05-04
	 */
	@Override
	public  MessageResponse insertTplPageByTplId(String tplId,UserProp userProp) throws Exception{
		String id=GUIDUtil.getGUID();
		TplPage o=new TplPage();
		o.setId(id);
		o.setTplId(tplId);
		o.setName("页面标题请修改");
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.tplPageDao.insert(o);
		String [] catgorys={"分类1","分类2"};
		String [] ids={GUIDUtil.getGUID(),GUIDUtil.getGUID()};
		int i=0;
		for(String name:catgorys){
			ArticleCategory ac=new ArticleCategory();
			ac.setId(ids[i]);
			ac.setTplPageId(id);
			ac.setName(name);
			ac.setCreateDate(new Date());
			ac.setStatus("1");
			ac.setCreateUserName(userProp.getName());
			ac.setCreateUserId(userProp.getUserId());
			this.articleCategoryDao.insert(ac);
			String [] titles={"标题","标题"};
			for(String title:titles){
				Article at=new Article();
				at.setId(GUIDUtil.getGUID());
				at.setTplPageId(id);
				at.setArticleCategory(ids[i]);
				at.setTitle(title);
				at.setRemark("摘要文字");
				at.setCover("http://zx.huacainfo.com/portal/www/img/640.jpg");
				at.setHitNum(Long.valueOf(0));
				at.setLikeNum(Long.valueOf(0));
				at.setMediType("1");
				at.setHrefUrl("");
				at.setCreateDate(new Date());
				at.setStatus("1");
				at.setType("1");
				at.setCreateUserName(userProp.getName());
				at.setCreateUserId(userProp.getUserId());
				this.articleDao.insert(at);
			}
			i++;
		}

		return new MessageResponse(0, "OK！");
	}

	/**
	 *
	 * @Title:getTplPageById
	 * @Description:  TODO(根据页面ID获取页面所有数据)
	 * @param:        @param id
	 * @param:        @throws Exception
	 * @return:       Map<String,Object>
	 * @throws
	 * @author: 陈晓克
	 * @version: 2018-05-07
	 */
	@Override
	public  Map<String, java.lang.Object>  getTplPageById(String id,String type) throws Exception{

		SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
		Configuration configuration = session.getConfiguration();
		configuration.setSafeResultHandlerEnabled(false);
		TplPageDao dao=session.getMapper(TplPageDao.class);
		Map<String,java.lang.Object> rst=new HashedMap();
		rst.put("status","0");
		Map<String,java.lang.Object> data=new HashedMap();
		try {
			data.put("page", dao.getById(id));
			data.put("categorys", dao.getPageData(id,type));
			data.put("covers", dao.getArticleTopListByPageId(id));
			rst.put("data", data);
		}catch (Exception e){
			if(session!=null){
				session.close();
			}
		}finally {
			if(session!=null){
				session.close();
			}
		}
		return rst;
	}

	/**
	 *
	 * @Title:updateNameById
	 * @Description:  TODO(根据页面ID更新标题)
	 * @param:        @param id
	 * @param:        @param name
	 * @param:        @throws Exception
	 * @return:       MessageResponse
	 * @throws
	 * @author: 陈晓克
	 * @version: 2018-05-11
	 */
	@Override
	public  MessageResponse updateNameById(String id,String name) throws Exception{
		this.tplPageDao.updateNameById(id,name);
		return new MessageResponse(0, "OK！");
	}

	/**
	 *
	 * @Title:updateNameById
	 * @Description:  TODO(根据页面ID更新封面)
	 * @param:        @param id
	 * @param:        @param cover
	 * @param:        @throws Exception
	 * @return:       MessageResponse
	 * @throws
	 * @author: 陈晓克
	 * @version: 2018-07-02
	 */
	@Override
	public  MessageResponse updateCoverById(String id,String cover) throws Exception{
		this.tplPageDao.updateCoverById(id,cover);
		return new MessageResponse(0, "OK！");
	}
	/**
	 *
	 * @Title:getById
	 * @Description:  TODO(根据页面ID获取页面所有数据)
	 * @param:        @param id
	 * @param:        @throws Exception
	 * @return:       Map<String,Object>
	 * @throws
	 * @author: 陈晓克
	 * @version: 2018-07-02
	 */
	@Override
	public  Map<String,Object> getById(String id) throws Exception{

		Map<String,Object> rst=new HashMap<>();
		rst.put("status",0);
		rst.put("data",this.tplPageDao.getById(id));
		return rst;
	}
}

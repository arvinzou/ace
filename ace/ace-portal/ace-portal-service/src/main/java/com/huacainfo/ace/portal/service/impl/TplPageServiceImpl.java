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
		o.setId(UUID.randomUUID().toString());
		//o.setId(String.valueOf(new Date().getTime()));
		
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
}

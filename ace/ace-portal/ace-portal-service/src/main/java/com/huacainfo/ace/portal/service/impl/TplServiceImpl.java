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
import com.huacainfo.ace.portal.dao.TplDao;
import com.huacainfo.ace.portal.model.Tpl;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.TplService;
import com.huacainfo.ace.portal.vo.TplVo;
import com.huacainfo.ace.portal.vo.TplQVo;
@Service("tplService")
/**
 * @author: 陈晓克
 * @version: 2018-05-04
 * @Description:  TODO(模板)
 */
public class TplServiceImpl implements TplService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TplDao tplDao;
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
	 		* @return:       PageResult<TplVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
    @Override
	public PageResult<TplVo> findTplList(TplQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<TplVo> rst = new PageResult<TplVo>();
		List<TplVo> list = this.tplDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.tplDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertTpl
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
	public MessageResponse insertTpl(Tpl o, UserProp userProp)
			throws Exception {
		o.setId(UUID.randomUUID().toString());
		//o.setId(String.valueOf(new Date().getTime()));
		
		int temp = this.tplDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "模板名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.tplDao.insert(o);
		this.dataBaseLogService.log("添加模板", "模板", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加模板完成！");
	}
    /**
	 *
	    * @Title:updateTpl
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
	public MessageResponse updateTpl(Tpl o, UserProp userProp)
			throws Exception {
		
		
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.tplDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更模板", "模板", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更模板完成！");
	}

    /**
	 *
	    * @Title:selectTplByPrimaryKey
	    * @Description:  TODO(获取模板)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Tpl>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
    @Override
	public SingleResult<TplVo> selectTplByPrimaryKey(String id) throws Exception {
		SingleResult<TplVo> rst = new SingleResult<TplVo>();
		rst.setValue(this.tplDao.selectByPrimaryKey(id));
		return rst;
	}
    /**
	 *
	    * @Title:deleteTplByTplId
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
	public MessageResponse deleteTplByTplId(String id,
			UserProp userProp) throws Exception {
		this.tplDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除模板", "模板", String.valueOf(id),
				String.valueOf(id), "模板", userProp);
		return new MessageResponse(0, "模板删除完成！");
	}

	/**
	 *
	 * @Title:getList
	 * @Description:  TODO(获取模板列表)
	 * @param:        @throws Exception
	 * @return:       Map<String,Object>
	 * @throws
	 * @author: 陈晓克
	 * @version: 2018-05-04
	 */
	@Override
	public Map<String,Object> getList() throws Exception{
		Map<String,Object> rst=new HashMap<>();
		rst.put("status",0);
		rst.put("data",this.tplDao.getList());
		return rst;
	}
}

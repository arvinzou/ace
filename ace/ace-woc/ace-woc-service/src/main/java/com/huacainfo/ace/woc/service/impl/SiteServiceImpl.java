package com.huacainfo.ace.woc.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.woc.dao.SiteDao;
import com.huacainfo.ace.woc.model.Site;
import com.huacainfo.ace.woc.service.SiteService;
import com.huacainfo.ace.woc.vo.SiteQVo;
import com.huacainfo.ace.woc.vo.SiteVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service("siteService")
/**
 * @author: 王恩
 * @version: 2018-03-09
 * @Description:  TODO(卡点档案)
 */
public class SiteServiceImpl implements SiteService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SiteDao siteDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(卡点档案分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<SiteVo>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
    @Override
	public PageResult<SiteVo> findSiteList(SiteQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<SiteVo> rst = new PageResult<SiteVo>();
		List<SiteVo> list = this.siteDao.findListInfo(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.siteDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertSite
	    * @Description:  TODO(添加卡点档案)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
    @Override
	public MessageResponse insertSite(Site o, UserProp userProp)
			throws Exception {
		o.setId(UUID.randomUUID().toString());
		int temp = this.siteDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "卡点档案名称重复！");
		}
		o.setCreateDate(new Date());
		o.setLastModifyDate(new Date());
		if (CommonUtils.isBlank(o.getStatus())) {
			return new MessageResponse(1, "站点类型不能为空！");
		}
		o.setCheckpointsNum(0);
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.siteDao.insertSelective(o);
		this.dataBaseLogService.log("添加卡点档案", "卡点档案", "", o.getSiteName(), o.getSiteName(), userProp);
		return new MessageResponse(0, "添加卡点档案完成！");
	}
    /**
	 *
	    * @Title:updateSite
	    * @Description:  TODO(更新卡点档案)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
    @Override
	public MessageResponse updateSite(Site o, UserProp userProp)
			throws Exception {
		
		
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.siteDao.updateByPrimaryKeySelective(o);
		this.dataBaseLogService.log("变更卡点档案", "卡点档案", "", o.getSiteName(),
				o.getSiteName(), userProp);
		return new MessageResponse(0, "变更卡点档案完成！");
	}

    /**
	 *
	    * @Title:selectSiteByPrimaryKey
	    * @Description:  TODO(获取卡点档案卡点档案)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Site>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
    @Override
	public SingleResult<SiteVo> selectSiteByPrimaryKey(String id) throws Exception {
		SingleResult<SiteVo> rst = new SingleResult<SiteVo>();
		rst.setValue(this.siteDao.selectByPrimaryKeyInfo(id));
		return rst;
	}
    /**
	 *
	    * @Title:deleteSiteBySiteId
	    * @Description:  TODO(删除卡点档案)
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
    @Override
	public MessageResponse deleteSiteBySiteId(String id,
			UserProp userProp) throws Exception {
		this.siteDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除卡点档案", "卡点档案", String.valueOf(id),
				String.valueOf(id), "卡点档案", userProp);
		return new MessageResponse(0, "卡点档案删除完成！");
	}

	@Override
	public Map<String, Object> selectSite(Map<String, Object> params) throws Exception{
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Map<String, String>> list = this.siteDao.selectSite(params);
		rst.put("total", list.size());
		rst.put("rows", list);
		return rst;
	}
}

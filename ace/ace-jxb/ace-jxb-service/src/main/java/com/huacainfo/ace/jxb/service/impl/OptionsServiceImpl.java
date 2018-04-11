package com.huacainfo.ace.jxb.service.impl;


import java.util.Date;
import java.util.List;
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
import com.huacainfo.ace.jxb.dao.OptionsDao;
import com.huacainfo.ace.jxb.model.Options;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.jxb.service.OptionsService;
import com.huacainfo.ace.jxb.vo.OptionsVo;
import com.huacainfo.ace.jxb.vo.OptionsQVo;
@Service("optionsService")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(选项)
 */
public class OptionsServiceImpl implements OptionsService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OptionsDao optionsDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(选项分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<OptionsVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public PageResult<OptionsVo> findOptionsList(OptionsQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<OptionsVo> rst = new PageResult<OptionsVo>();
		List<OptionsVo> list = this.optionsDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.optionsDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertOptions
	    * @Description:  TODO(添加选项)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse insertOptions(Options o, UserProp userProp)
			throws Exception {
		o.setId(GUIDUtil.getGUID());
		//o.setId(String.valueOf(new Date().getTime()));
		if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键不能为空！");
}
if (CommonUtils.isBlank(o.getQuestionId())) {
return new MessageResponse(1, "所属考题不能为空！");
}
if (CommonUtils.isBlank(o.getKeyName())) {
return new MessageResponse(1, "选项名称不能为空！");
}
if (CommonUtils.isBlank(o.getName())) {
return new MessageResponse(1, "名称不能为空！");
}
if (CommonUtils.isBlank(o.getSort())) {
return new MessageResponse(1, "顺序不能为空！");
}
if (CommonUtils.isBlank(o.getScore())) {
return new MessageResponse(1, "分值不能为空！");
}
if (CommonUtils.isBlank(o.getStatus())) {
return new MessageResponse(1, "状态不能为空！");
}
if (CommonUtils.isBlank(o.getLastModifyDate())) {
return new MessageResponse(1, "最后更新时间不能为空！");
}
		int temp = this.optionsDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "选项名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.optionsDao.insert(o);
		this.dataBaseLogService.log("添加选项", "选项", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加选项完成！");
	}
    /**
	 *
	    * @Title:updateOptions
	    * @Description:  TODO(更新选项)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse updateOptions(Options o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键不能为空！");
}
if (CommonUtils.isBlank(o.getQuestionId())) {
return new MessageResponse(1, "所属考题不能为空！");
}
if (CommonUtils.isBlank(o.getKeyName())) {
return new MessageResponse(1, "选项名称不能为空！");
}
if (CommonUtils.isBlank(o.getName())) {
return new MessageResponse(1, "名称不能为空！");
}
if (CommonUtils.isBlank(o.getSort())) {
return new MessageResponse(1, "顺序不能为空！");
}
if (CommonUtils.isBlank(o.getScore())) {
return new MessageResponse(1, "分值不能为空！");
}
if (CommonUtils.isBlank(o.getStatus())) {
return new MessageResponse(1, "状态不能为空！");
}
if (CommonUtils.isBlank(o.getLastModifyDate())) {
return new MessageResponse(1, "最后更新时间不能为空！");
}
		
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.optionsDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更选项", "选项", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更选项完成！");
	}

    /**
	 *
	    * @Title:selectOptionsByPrimaryKey
	    * @Description:  TODO(获取选项)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Options>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public SingleResult<OptionsVo> selectOptionsByPrimaryKey(String id) throws Exception {
		SingleResult<OptionsVo> rst = new SingleResult<OptionsVo>();
		rst.setValue(this.optionsDao.selectByPrimaryKey(id));
		return rst;
	}
    /**
	 *
	    * @Title:deleteOptionsByOptionsId
	    * @Description:  TODO(删除选项)
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse deleteOptionsByOptionsId(String id,
			UserProp userProp) throws Exception {
		this.optionsDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除选项", "选项", String.valueOf(id),
				String.valueOf(id), "选项", userProp);
		return new MessageResponse(0, "选项删除完成！");
	}
}

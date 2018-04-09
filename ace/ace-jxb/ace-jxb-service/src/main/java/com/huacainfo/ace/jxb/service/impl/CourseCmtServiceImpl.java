package com.huacainfo.ace.woc.service.impl;


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
import com.huacainfo.ace.jxb.dao.CourseCmtDao;
import com.huacainfo.ace.jxb.model.CourseCmt;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.jxb.service.CourseCmtService;
import com.huacainfo.ace.jxb.vo.CourseCmtVo;
import com.huacainfo.ace.jxb.vo.CourseCmtQVo;
@Service("courseCmtService")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(课程评论)
 */
public class CourseCmtServiceImpl implements CourseCmtService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CourseCmtDao courseCmtDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(课程评论分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<CourseCmtVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public PageResult<CourseCmtVo> findCourseCmtList(CourseCmtQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<CourseCmtVo> rst = new PageResult<CourseCmtVo>();
		List<CourseCmtVo> list = this.courseCmtDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.courseCmtDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertCourseCmt
	    * @Description:  TODO(添加课程评论)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse insertCourseCmt(CourseCmt o, UserProp userProp)
			throws Exception {
		o.setId(GUIDUtil.getGUID());
		//o.setId(String.valueOf(new Date().getTime()));
		if (CommonUtils.isBlank(o.getId())) {return new MessageResponse(1, "主键不能为空！");}if (CommonUtils.isBlank(o.getCourseId())) {return new MessageResponse(1, "课程主键不能为空！");}if (CommonUtils.isBlank(o.getUserId())) {return new MessageResponse(1, "评论人不能为空！");}if (CommonUtils.isBlank(o.getContent())) {return new MessageResponse(1, "评论内容不能为空！");}
		int temp = this.courseCmtDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "课程评论名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.courseCmtDao.insert(o);
		this.dataBaseLogService.log("添加课程评论", "课程评论", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加课程评论完成！");
	}
    /**
	 *
	    * @Title:updateCourseCmt
	    * @Description:  TODO(更新课程评论)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse updateCourseCmt(CourseCmt o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {return new MessageResponse(1, "主键不能为空！");}if (CommonUtils.isBlank(o.getCourseId())) {return new MessageResponse(1, "课程主键不能为空！");}if (CommonUtils.isBlank(o.getUserId())) {return new MessageResponse(1, "评论人不能为空！");}if (CommonUtils.isBlank(o.getContent())) {return new MessageResponse(1, "评论内容不能为空！");}
		
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.courseCmtDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更课程评论", "课程评论", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更课程评论完成！");
	}

    /**
	 *
	    * @Title:selectCourseCmtByPrimaryKey
	    * @Description:  TODO(获取课程评论)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<CourseCmt>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public SingleResult<CourseCmtVo> selectCourseCmtByPrimaryKey(String id) throws Exception {
		SingleResult<CourseCmtVo> rst = new SingleResult<CourseCmtVo>();
		rst.setValue(this.courseCmtDao.selectByPrimaryKey(id));
		return rst;
	}
    /**
	 *
	    * @Title:deleteCourseCmtByCourseCmtId
	    * @Description:  TODO(删除课程评论)
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse deleteCourseCmtByCourseCmtId(String id,
			UserProp userProp) throws Exception {
		this.courseCmtDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除课程评论", "课程评论", String.valueOf(id),
				String.valueOf(id), "课程评论", userProp);
		return new MessageResponse(0, "课程评论删除完成！");
	}
}

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
import com.huacainfo.ace.jxb.dao.TeacherAuditDao;
import com.huacainfo.ace.jxb.model.TeacherAudit;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.jxb.service.TeacherAuditService;
import com.huacainfo.ace.jxb.vo.TeacherAuditVo;
import com.huacainfo.ace.jxb.vo.TeacherAuditQVo;
@Service("teacherAuditService")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(老师审核记录)
 */
public class TeacherAuditServiceImpl implements TeacherAuditService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TeacherAuditDao teacherAuditDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(老师审核记录分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<TeacherAuditVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public PageResult<TeacherAuditVo> findTeacherAuditList(TeacherAuditQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<TeacherAuditVo> rst = new PageResult<TeacherAuditVo>();
		List<TeacherAuditVo> list = this.teacherAuditDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.teacherAuditDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertTeacherAudit
	    * @Description:  TODO(添加老师审核记录)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse insertTeacherAudit(TeacherAudit o, UserProp userProp)
			throws Exception {
		o.setId(GUIDUtil.getGUID());
		//o.setId(String.valueOf(new Date().getTime()));
		if (CommonUtils.isBlank(o.getId())) {return new MessageResponse(1, "主键不能为空！");}if (CommonUtils.isBlank(o.getTeacherId())) {return new MessageResponse(1, "老师主键不能为空！");}if (CommonUtils.isBlank(o.getAuditor())) {return new MessageResponse(1, "审核人不能为空！");}if (CommonUtils.isBlank(o.getStatement())) {return new MessageResponse(1, "审核说明不能为空！");}if (CommonUtils.isBlank(o.getRst())) {return new MessageResponse(1, "审核结果不能为空！");}
		int temp = this.teacherAuditDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "老师审核记录名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.teacherAuditDao.insert(o);
		this.dataBaseLogService.log("添加老师审核记录", "老师审核记录", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加老师审核记录完成！");
	}
    /**
	 *
	    * @Title:updateTeacherAudit
	    * @Description:  TODO(更新老师审核记录)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse updateTeacherAudit(TeacherAudit o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {return new MessageResponse(1, "主键不能为空！");}if (CommonUtils.isBlank(o.getTeacherId())) {return new MessageResponse(1, "老师主键不能为空！");}if (CommonUtils.isBlank(o.getAuditor())) {return new MessageResponse(1, "审核人不能为空！");}if (CommonUtils.isBlank(o.getStatement())) {return new MessageResponse(1, "审核说明不能为空！");}if (CommonUtils.isBlank(o.getRst())) {return new MessageResponse(1, "审核结果不能为空！");}
		
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.teacherAuditDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更老师审核记录", "老师审核记录", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更老师审核记录完成！");
	}

    /**
	 *
	    * @Title:selectTeacherAuditByPrimaryKey
	    * @Description:  TODO(获取老师审核记录)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<TeacherAudit>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public SingleResult<TeacherAuditVo> selectTeacherAuditByPrimaryKey(String id) throws Exception {
		SingleResult<TeacherAuditVo> rst = new SingleResult<TeacherAuditVo>();
		rst.setValue(this.teacherAuditDao.selectByPrimaryKey(id));
		return rst;
	}
    /**
	 *
	    * @Title:deleteTeacherAuditByTeacherAuditId
	    * @Description:  TODO(删除老师审核记录)
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse deleteTeacherAuditByTeacherAuditId(String id,
			UserProp userProp) throws Exception {
		this.teacherAuditDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除老师审核记录", "老师审核记录", String.valueOf(id),
				String.valueOf(id), "老师审核记录", userProp);
		return new MessageResponse(0, "老师审核记录删除完成！");
	}
}

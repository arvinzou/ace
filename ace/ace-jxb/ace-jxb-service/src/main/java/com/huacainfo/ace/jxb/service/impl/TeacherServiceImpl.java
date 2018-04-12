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
import com.huacainfo.ace.jxb.dao.TeacherDao;
import com.huacainfo.ace.jxb.model.Teacher;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.jxb.service.TeacherService;
import com.huacainfo.ace.jxb.vo.TeacherVo;
import com.huacainfo.ace.jxb.vo.TeacherQVo;
@Service("teacherService")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(老师)
 */
public class TeacherServiceImpl implements TeacherService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(老师分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<TeacherVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public PageResult<TeacherVo> findTeacherList(TeacherQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<TeacherVo> rst = new PageResult<TeacherVo>();
		List<TeacherVo> list = this.teacherDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.teacherDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertTeacher
	    * @Description:  TODO(添加老师)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse insertTeacher(Teacher o, UserProp userProp)
			throws Exception {
		o.setId(GUIDUtil.getGUID());
		//o.setId(String.valueOf(new Date().getTime()));
		if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键不能为空！");
}
if (CommonUtils.isBlank(o.getName())) {
return new MessageResponse(1, "姓名不能为空！");
}
if (CommonUtils.isBlank(o.getMobile())) {
return new MessageResponse(1, "手机号不能为空！");
}
if (CommonUtils.isBlank(o.getImagePhotoUrl())) {
return new MessageResponse(1, "形象照不能为空！");
}
if (CommonUtils.isBlank(o.getIdCard())) {
return new MessageResponse(1, "身份证号不能为空！");
}
if (CommonUtils.isBlank(o.getIdCardImgUrl())) {
return new MessageResponse(1, "身份证件电子档不能为空！");
}
if (CommonUtils.isBlank(o.getCertificateNo())) {
return new MessageResponse(1, "资格从业证书号不能为空！");
}
if (CommonUtils.isBlank(o.getCertificateImgUrl())) {
return new MessageResponse(1, "资格从业证书电子档不能为空！");
}
if (CommonUtils.isBlank(o.getEvidenceImgUrl())) {
return new MessageResponse(1, "身份证持胸前自拍照不能为空！");
}
if (CommonUtils.isBlank(o.getLevel())) {
return new MessageResponse(1, "级别不能为空！");
}
		int temp = this.teacherDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "老师名称重复！");
		}
		o.setCreateDate(new Date());
		//o.setStatus("1");
		//o.setCreateUserName(userProp.getName());
		//o.setCreateUserId(userProp.getUserId());
		this.teacherDao.insert(o);
		this.dataBaseLogService.log("添加老师", "老师", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加老师完成！");
	}
    /**
	 *
	    * @Title:updateTeacher
	    * @Description:  TODO(更新老师)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse updateTeacher(Teacher o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键不能为空！");
}
if (CommonUtils.isBlank(o.getName())) {
return new MessageResponse(1, "姓名不能为空！");
}
if (CommonUtils.isBlank(o.getMobile())) {
return new MessageResponse(1, "手机号不能为空！");
}
if (CommonUtils.isBlank(o.getImagePhotoUrl())) {
return new MessageResponse(1, "形象照不能为空！");
}
if (CommonUtils.isBlank(o.getIdCard())) {
return new MessageResponse(1, "身份证号不能为空！");
}
if (CommonUtils.isBlank(o.getIdCardImgUrl())) {
return new MessageResponse(1, "身份证件电子档不能为空！");
}
if (CommonUtils.isBlank(o.getCertificateNo())) {
return new MessageResponse(1, "资格从业证书号不能为空！");
}
if (CommonUtils.isBlank(o.getCertificateImgUrl())) {
return new MessageResponse(1, "资格从业证书电子档不能为空！");
}
if (CommonUtils.isBlank(o.getEvidenceImgUrl())) {
return new MessageResponse(1, "身份证持胸前自拍照不能为空！");
}
if (CommonUtils.isBlank(o.getLevel())) {
return new MessageResponse(1, "级别不能为空！");
}
		
		o.setCreateDate(new Date());
		//o.setLastModifyUserName(userProp.getName());
		//o.setLastModifyUserId(userProp.getUserId());
		this.teacherDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更老师", "老师", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更老师完成！");
	}

    /**
	 *
	    * @Title:selectTeacherByPrimaryKey
	    * @Description:  TODO(获取老师)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Teacher>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public SingleResult<TeacherVo> selectTeacherByPrimaryKey(String id) throws Exception {
		SingleResult<TeacherVo> rst = new SingleResult<TeacherVo>();
		rst.setValue(this.teacherDao.selectByPrimaryKey(id));
		return rst;
	}
    /**
	 *
	    * @Title:deleteTeacherByTeacherId
	    * @Description:  TODO(删除老师)
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse deleteTeacherByTeacherId(String id,
			UserProp userProp) throws Exception {
		this.teacherDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除老师", "老师", String.valueOf(id),
				String.valueOf(id), "老师", userProp);
		return new MessageResponse(0, "老师删除完成！");
	}
}

package com.huacainfo.ace.jxb.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.Teacher;
import com.huacainfo.ace.jxb.service.TeacherService;
import com.huacainfo.ace.jxb.vo.TeacherVo;
import com.huacainfo.ace.jxb.vo.TeacherQVo;

@Controller
@RequestMapping("/teacher")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(老师)
 */
public class TeacherController extends LiveBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TeacherService teacherService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(老师分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<TeacherVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/findTeacherList")
	@ResponseBody
	public PageResult<TeacherVo> findTeacherList(TeacherQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<TeacherVo> rst = this.teacherService
				.findTeacherList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertTeacher
	    * @Description:  TODO(添加老师)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/insertTeacher")
	@ResponseBody
	public MessageResponse insertTeacher(String jsons) throws Exception {
		Teacher obj = JSON.parseObject(jsons, Teacher.class);
		return this.teacherService
				.insertTeacher(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateTeacher
	    * @Description:  TODO(更新老师)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/updateTeacher")
	@ResponseBody
	public MessageResponse updateTeacher(String jsons) throws Exception {
		Teacher obj = JSON.parseObject(jsons, Teacher.class);
		return this.teacherService
				.updateTeacher(obj, this.getCurUserProp());
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
	@RequestMapping(value = "/selectTeacherByPrimaryKey")
	@ResponseBody
	public SingleResult<TeacherVo> selectTeacherByPrimaryKey(String id)
			throws Exception {
		return this.teacherService.selectTeacherByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteTeacherByTeacherId
	    * @Description:  TODO(删除老师)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/deleteTeacherByTeacherId")
	@ResponseBody
	public MessageResponse deleteTeacherByTeacherId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.teacherService.deleteTeacherByTeacherId(id,
				this.getCurUserProp());
	}
}

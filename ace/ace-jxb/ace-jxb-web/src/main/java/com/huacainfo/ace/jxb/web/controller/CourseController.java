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
import com.huacainfo.ace.jxb.model.Course;
import com.huacainfo.ace.jxb.service.CourseService;
import com.huacainfo.ace.jxb.vo.CourseVo;
import com.huacainfo.ace.jxb.vo.CourseQVo;

@Controller
@RequestMapping("/course")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(课程)
 */
public class CourseController extends LiveBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CourseService courseService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(课程分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<CourseVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/findCourseList")
	@ResponseBody
	public PageResult<CourseVo> findCourseList(CourseQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<CourseVo> rst = this.courseService
				.findCourseList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertCourse
	    * @Description:  TODO(添加课程)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/insertCourse")
	@ResponseBody
	public MessageResponse insertCourse(String jsons) throws Exception {
		Course obj = JSON.parseObject(jsons, Course.class);
		return this.courseService
				.insertCourse(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateCourse
	    * @Description:  TODO(更新课程)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/updateCourse")
	@ResponseBody
	public MessageResponse updateCourse(String jsons) throws Exception {
		Course obj = JSON.parseObject(jsons, Course.class);
		return this.courseService
				.updateCourse(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectCourseByPrimaryKey
	    * @Description:  TODO(获取课程)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Course>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/selectCourseByPrimaryKey")
	@ResponseBody
	public SingleResult<CourseVo> selectCourseByPrimaryKey(String id)
			throws Exception {
		return this.courseService.selectCourseByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteCourseByCourseId
	    * @Description:  TODO(删除课程)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/deleteCourseByCourseId")
	@ResponseBody
	public MessageResponse deleteCourseByCourseId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.courseService.deleteCourseByCourseId(id,
				this.getCurUserProp());
	}
}

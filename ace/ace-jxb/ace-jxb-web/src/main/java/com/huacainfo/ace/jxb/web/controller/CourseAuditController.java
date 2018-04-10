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
import com.huacainfo.ace.jxb.model.CourseAudit;
import com.huacainfo.ace.jxb.service.CourseAuditService;
import com.huacainfo.ace.jxb.vo.CourseAuditVo;
import com.huacainfo.ace.jxb.vo.CourseAuditQVo;

@Controller
@RequestMapping("/courseAudit")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(课程审核记录)
 */
public class CourseAuditController extends LiveBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CourseAuditService courseAuditService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(课程审核记录分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<CourseAuditVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/findCourseAuditList")
	@ResponseBody
	public PageResult<CourseAuditVo> findCourseAuditList(CourseAuditQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<CourseAuditVo> rst = this.courseAuditService
				.findCourseAuditList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertCourseAudit
	    * @Description:  TODO(添加课程审核记录)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/insertCourseAudit")
	@ResponseBody
	public MessageResponse insertCourseAudit(String jsons) throws Exception {
		CourseAudit obj = JSON.parseObject(jsons, CourseAudit.class);
		return this.courseAuditService
				.insertCourseAudit(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateCourseAudit
	    * @Description:  TODO(更新课程审核记录)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/updateCourseAudit")
	@ResponseBody
	public MessageResponse updateCourseAudit(String jsons) throws Exception {
		CourseAudit obj = JSON.parseObject(jsons, CourseAudit.class);
		return this.courseAuditService
				.updateCourseAudit(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectCourseAuditByPrimaryKey
	    * @Description:  TODO(获取课程审核记录)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<CourseAudit>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/selectCourseAuditByPrimaryKey")
	@ResponseBody
	public SingleResult<CourseAuditVo> selectCourseAuditByPrimaryKey(String id)
			throws Exception {
		return this.courseAuditService.selectCourseAuditByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteCourseAuditByCourseAuditId
	    * @Description:  TODO(删除课程审核记录)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/deleteCourseAuditByCourseAuditId")
	@ResponseBody
	public MessageResponse deleteCourseAuditByCourseAuditId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.courseAuditService.deleteCourseAuditByCourseAuditId(id,
				this.getCurUserProp());
	}
}

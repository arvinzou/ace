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
import com.huacainfo.ace.jxb.model.TeacherAudit;
import com.huacainfo.ace.jxb.service.TeacherAuditService;
import com.huacainfo.ace.jxb.vo.TeacherAuditVo;
import com.huacainfo.ace.jxb.vo.TeacherAuditQVo;

@Controller
@RequestMapping("/teacherAudit")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(老师审核记录)
 */
public class TeacherAuditController extends LiveBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TeacherAuditService teacherAuditService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(老师审核记录分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<TeacherAuditVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/findTeacherAuditList")
	@ResponseBody
	public PageResult<TeacherAuditVo> findTeacherAuditList(TeacherAuditQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<TeacherAuditVo> rst = this.teacherAuditService
				.findTeacherAuditList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertTeacherAudit
	    * @Description:  TODO(添加老师审核记录)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/insertTeacherAudit")
	@ResponseBody
	public MessageResponse insertTeacherAudit(String jsons) throws Exception {
		TeacherAudit obj = JSON.parseObject(jsons, TeacherAudit.class);
		return this.teacherAuditService
				.insertTeacherAudit(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateTeacherAudit
	    * @Description:  TODO(更新老师审核记录)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/updateTeacherAudit")
	@ResponseBody
	public MessageResponse updateTeacherAudit(String jsons) throws Exception {
		TeacherAudit obj = JSON.parseObject(jsons, TeacherAudit.class);
		return this.teacherAuditService
				.updateTeacherAudit(obj, this.getCurUserProp());
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
	@RequestMapping(value = "/selectTeacherAuditByPrimaryKey")
	@ResponseBody
	public SingleResult<TeacherAuditVo> selectTeacherAuditByPrimaryKey(String id)
			throws Exception {
		return this.teacherAuditService.selectTeacherAuditByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteTeacherAuditByTeacherAuditId
	    * @Description:  TODO(删除老师审核记录)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/deleteTeacherAuditByTeacherAuditId")
	@ResponseBody
	public MessageResponse deleteTeacherAuditByTeacherAuditId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.teacherAuditService.deleteTeacherAuditByTeacherAuditId(id,
				this.getCurUserProp());
	}
}

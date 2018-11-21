package com.huacainfo.ace.jxb.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.CourseAudit;
import com.huacainfo.ace.jxb.service.CourseAuditService;
import com.huacainfo.ace.jxb.vo.CourseAuditQVo;
import com.huacainfo.ace.jxb.vo.CourseAuditVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/courseAudit")
/**
 * @author: Arvin
 * @version: 2018-08-06
 * @Description: TODO(课程审核)
 */
public class CourseAuditController extends JxbBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CourseAuditService courseAuditService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(课程审核分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <CourseAuditVo>
     * @author: Arvin
     * @version: 2018-08-06
     */
    @RequestMapping(value = "/findCourseAuditList")
    @ResponseBody
    public PageResult<CourseAuditVo> findCourseAuditList(CourseAuditQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<CourseAuditVo> rst = this.courseAuditService
                .findCourseAuditList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertCourseAudit
     * @Description: TODO(添加课程审核)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @RequestMapping(value = "/insertCourseAudit")
    @ResponseBody
    public MessageResponse insertCourseAudit(String jsons) throws Exception {
        CourseAudit obj = JSON.parseObject(jsons, CourseAudit.class);
        return this.courseAuditService.insertCourseAudit(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateCourseAudit
     * @Description: TODO(更新课程审核)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @RequestMapping(value = "/updateCourseAudit")
    @ResponseBody
    public MessageResponse updateCourseAudit(String jsons) throws Exception {
        CourseAudit obj = JSON.parseObject(jsons, CourseAudit.class);
        return this.courseAuditService.updateCourseAudit(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectCourseAuditByPrimaryKey
     * @Description: TODO(获取课程审核)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CourseAudit>
     * @author: Arvin
     * @version: 2018-08-06
     */
    @RequestMapping(value = "/selectCourseAuditByPrimaryKey")
    @ResponseBody
    public SingleResult<CourseAuditVo> selectCourseAuditByPrimaryKey(String id) throws Exception {
        return this.courseAuditService.selectCourseAuditByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteCourseAuditByCourseAuditId
     * @Description: TODO(删除课程审核)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @RequestMapping(value = "/deleteCourseAuditByCourseAuditId")
    @ResponseBody
    public MessageResponse deleteCourseAuditByCourseAuditId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.courseAuditService.deleteCourseAuditByCourseAuditId(id, this.getCurUserProp());
    }
}

package com.huacainfo.ace.partyschool.web.controller;

import com.huacainfo.ace.common.result.ResultResponse;
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
import com.huacainfo.ace.partyschool.model.Course;
import com.huacainfo.ace.partyschool.service.CourseService;
import com.huacainfo.ace.partyschool.vo.CourseVo;
import com.huacainfo.ace.partyschool.vo.CourseQVo;

@Controller
@RequestMapping("/course")
/**
 * @author: 王恩
 * @version: 2019-01-02
 * @Description: TODO(课程管理)
 */
public class CourseController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CourseService courseService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(课程管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <CourseVo>
     * @author: 王恩
     * @version: 2019-01-02
     */
    @RequestMapping(value = "/findCourseList")
    @ResponseBody
    public PageResult<CourseVo> findCourseList(CourseQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<CourseVo> rst = this.courseService.findCourseList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    @RequestMapping(value = "/findListClassifiedName")
    @ResponseBody
    public ResultResponse findListClassifiedName(CourseQVo condition, PageParamNoChangeSord page) throws Exception {
        return this.courseService.findListClassifiedName(condition, 0, 1000,"");
    }

    /**
     * @throws
     * @Title:insertCourse
     * @Description: TODO(添加课程管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-02
     */
    @RequestMapping(value = "/insertCourse")
    @ResponseBody
    public MessageResponse insertCourse(String jsons) throws Exception {
        CourseQVo obj = JSON.parseObject(jsons, CourseQVo.class);
        return this.courseService.insertCourse(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateCourse
     * @Description: TODO(更新课程管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-02
     */
    @RequestMapping(value = "/updateCourse")
    @ResponseBody
    public MessageResponse updateCourse(String jsons) throws Exception {
        CourseQVo obj = JSON.parseObject(jsons, CourseQVo.class);
        return this.courseService.updateCourse(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectCourseByPrimaryKey
     * @Description: TODO(获取课程管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Course>
     * @author: 王恩
     * @version: 2019-01-02
     */
    @RequestMapping(value = "/selectCourseByPrimaryKey")
    @ResponseBody
    public SingleResult<CourseVo> selectCourseByPrimaryKey(String id) throws Exception {
        return this.courseService.selectCourseByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteCourseByCourseId
     * @Description: TODO(删除课程管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-02
     */
    @RequestMapping(value = "/deleteCourseByCourseId")
    @ResponseBody
    public MessageResponse deleteCourseByCourseId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.courseService.deleteCourseByCourseId(id, this.getCurUserProp());
    }

//    /**
//     * @throws
//     * @Title:audit
//     * @Description: TODO(审核课程管理)
//     * @param: @param id bean.id
//     * @param: @param rst 审核结果 3-通过 4-拒绝
//     * @param: @param message 审核说明
//     * @param: @throws Exception
//     * @return: MessageResponse
//     * @author: 王恩
//     * @version: 2019-01-02
//     */
//    @RequestMapping(value = "/audit")
//    @ResponseBody
//    public MessageResponse audit(String id, String rst, String message) throws Exception {
//
//        return this.courseService.audit(id, rst, message, this.getCurUserProp());
//    }
//
   @RequestMapping(value = "/softdel")
    @ResponseBody
    public MessageResponse softdel(String id) throws Exception {
        return this.courseService.softdel(id, this.getCurUserProp());
    }
}

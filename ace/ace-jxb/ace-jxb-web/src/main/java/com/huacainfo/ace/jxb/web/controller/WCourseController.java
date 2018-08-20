package com.huacainfo.ace.jxb.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.jxb.service.CourseCmtService;
import com.huacainfo.ace.jxb.service.CourseService;
import com.huacainfo.ace.jxb.service.CourseSourceService;
import com.huacainfo.ace.jxb.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: Arvin
 * @Date: 2018/8/6 17:10
 * @Description:
 */
@RestController
@RequestMapping("/www/course")
public class WCourseController extends JxbBaseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseSourceService courseSourceService;
    @Autowired
    private CourseCmtService courseCmtService;

    /**
     * 查询获取课程列表
     */
    @RequestMapping("/findList")
    public ResultResponse findList(CourseQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<CourseVo> rst = this.courseService
                .findCourseList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rst);
    }

    /**
     * 查询获取 -- 课程介绍
     */
    @RequestMapping("/findCourseDetail")
    public ResultResponse findCourseDetail(String courseId) throws Exception {
        if (StringUtil.isEmpty(courseId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        CourseVo courseVo = courseService.selectCourseByPrimaryKey(courseId).getValue();
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", courseVo);
    }

    /**
     * 查询获取 -- 课程详情
     */
    @RequestMapping("/findCoursePartInfo")
    public ResultResponse findCoursePartInfo(String courseId) throws Exception {
        if (StringUtil.isEmpty(courseId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        List<CoursePartVo> coursePartInfo = courseService.findCoursePartInfo(courseId);

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", coursePartInfo);
    }

    /**
     * 查询获取 -- 课程资源详情
     */
    @RequestMapping("/findCourseSource")
    public ResultResponse findCourseSource(String srcId) throws Exception {
        if (StringUtil.isEmpty(srcId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        CourseSourceVo csVo = courseSourceService.selectCourseSourceByPrimaryKey(srcId).getValue();
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", csVo);
    }


    /**
     * 功能描述: 查询课程评论 列表
     *
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/8/15 11:08
     */
    @RequestMapping("/cmt/findCmtList")
    public ResultResponse findCmtList(CourseCmtQVo condition, PageParamNoChangeSord page) throws Exception {
        if (StringUtil.isEmpty(condition.getCourseId())) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        PageResult<CourseCmtVo> rst = this.courseCmtService
                .findCourseCmtList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rst);
    }

    /**
     * 功能描述: 新增课程评论
     *
     * @param courseId 课程id
     * @param userId   评论人id
     * @param content  评论内容
     * @param grade    评分
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/8/15 11:08
     */
    @RequestMapping("/cmt/add")
    public ResultResponse addCourseCmt(String courseId, String userId, String content, String grade) throws Exception {
        if (StringUtil.isEmpty(courseId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        if (StringUtil.isEmpty(userId)) {
            Userinfo userinfo = getCurUserinfo();
            userId = userinfo.getUnionid();
        }
        if (StringUtil.isEmpty(userId)) {
            return new ResultResponse(ResultCode.FAIL, "用户授权失败");
        }

        courseCmtService.addCourseCmt(courseId, userId, content, grade);
        return new ResultResponse(ResultCode.SUCCESS, "添加评论成功");
    }


}

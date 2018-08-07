package com.huacainfo.ace.jxb.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.jxb.service.CourseService;
import com.huacainfo.ace.jxb.vo.CourseQVo;
import com.huacainfo.ace.jxb.vo.CourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 查询获取课程详情
     */
    @RequestMapping("/findCourseDetail")
    public ResultResponse findCourseDetail(String courseId) throws Exception {
        if (StringUtil.isEmpty(courseId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        CourseVo courseVo = courseService.selectCourseByPrimaryKey(courseId).getValue();
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", courseVo);
    }

}

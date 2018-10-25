package com.huacainfo.ace.jxb.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.jxb.service.CourseService;
import com.huacainfo.ace.jxb.service.MemberQrcodeService;
import com.huacainfo.ace.jxb.service.StudioService;
import com.huacainfo.ace.jxb.vo.CourseQVo;
import com.huacainfo.ace.jxb.vo.CourseVo;
import com.huacainfo.ace.jxb.vo.StudioVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/7/26 08:55
 * @Description:
 */
@RestController
@RequestMapping("/www/studio")
public class WStudioController extends JxbBaseController {
    @Autowired
    private MemberQrcodeService memberQrcodeService;
    @Autowired
    private StudioService studioService;
    @Autowired
    private CourseService courseService;


    /**
     * 获取工作室推广二维码 -- 直接获取二维码图方式
     *
     * @param studioId 工作室ID
     * @param type     二维码时效类型 0-临时 1-永久
     * @param refresh  强制刷新条件 0-正常获取 1 - 强制刷新
     * @return ResultResponse
     */
    @RequestMapping("/getQRCode")
    public ResultResponse getQRCode(String studioId, String type, String refresh) throws Exception {
        if (StringUtil.isEmpty(studioId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        type = StringUtil.isEmpty(type) ? "1" : type;
        refresh = StringUtil.isEmpty(refresh) ? "0" : refresh;

        try {
            return memberQrcodeService.getQRCode(studioId, type, refresh);
        } catch (CustomException e) {
            return new ResultResponse(ResultCode.FAIL, e.getMsg());
        }
    }

    /**
     * 获取工作室推广二维码 -- 绘制方式
     *
     * @param studioId 工作室ID
     * @param type     二维码时效类型 0-临时 1-永久
     * @param refresh  强制刷新条件 0-正常获取 1 - 强制刷新
     * @return ResultResponse
     */
    @RequestMapping("/drawQRCode")
    public ResultResponse drawQRCode(String studioId, String type, String refresh) throws Exception {
        if (StringUtil.isEmpty(studioId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        type = StringUtil.isEmpty(type) ? "1" : type;
        refresh = StringUtil.isEmpty(refresh) ? "0" : refresh;

        try {
            return memberQrcodeService.drawQRCode(studioId, type, refresh);
        } catch (CustomException e) {
            return new ResultResponse(ResultCode.FAIL, e.getMsg());
        }
    }


    /**
     * 获取我的工作室列表
     */
    @RequestMapping("/getStudioList")
    public ResultResponse getStudioList(String counselorId) throws Exception {
        if (StringUtil.isEmpty(counselorId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        Map<String, Object> voList = null;
        try {
            voList = studioService.getStudioList(counselorId);
        } catch (CustomException e) {
            return new ResultResponse(ResultCode.FAIL, e.getMsg());
        }

        return new ResultResponse(ResultCode.SUCCESS, "获取成功", voList);
    }


    /**
     * 获取我的工作室详情
     */
    @RequestMapping("/getStudioDetail")
    public ResultResponse getStudioDetail(String studioId) throws Exception {
        if (StringUtil.isEmpty(studioId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        StudioVo studioVo = studioService.getStudioDetail(studioId);
        return new ResultResponse(ResultCode.SUCCESS, "获取成功", studioVo);
    }

    /**
     * 获取我的工作室数据报表
     * studioId    --  工作室ID
     * memberCount --  成员数量
     * courseCount --  课程数量
     * consultCount    --  咨询数量
     * liveCount    --  直播数量
     */
    @RequestMapping("/studioReport")
    public ResultResponse studioReport(String studioId) throws Exception {
        if (StringUtil.isEmpty(studioId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        Map<String, Object> rst = studioService.studioReport(studioId);
        return new ResultResponse(ResultCode.SUCCESS, "获取成功", rst);
    }

    /**
     * 查询工作室课程列表
     *
     * @return
     */
    @RequestMapping("/findCourseList")
    public ResultResponse findCourseList(String studioId, PageParamNoChangeSord page) throws Exception {
        if (StringUtil.isEmpty(studioId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少工作室ID");
        }

        CourseQVo condition = new CourseQVo();
        condition.setStudioId(studioId);
        PageResult<CourseVo> rst =
                courseService.findCourseList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rst);
    }


    /**
     * 查询工作室 -- 精品课程
     *
     * @return
     */
    @RequestMapping("/findExcellentCourse")
    public ResultResponse findExcellentCourse(String studioId, PageParamNoChangeSord page) throws Exception {
        if (StringUtil.isEmpty(studioId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少工作室ID");
        }

        CourseQVo condition = new CourseQVo();
        condition.setStudioId(studioId);
        condition.setFine("1");

        PageResult<CourseVo> rst =
                courseService.findCourseList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rst);
    }

}

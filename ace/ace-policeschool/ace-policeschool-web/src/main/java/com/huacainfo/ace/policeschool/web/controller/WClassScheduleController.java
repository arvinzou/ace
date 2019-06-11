package com.huacainfo.ace.policeschool.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.policeschool.service.ClassScheduleService;
import com.huacainfo.ace.policeschool.vo.ClassScheduleQVo;
import com.huacainfo.ace.policeschool.vo.ClassScheduleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("www/classSchedule")
/**
 * @author: 王恩
 * @version: 2019-01-06
 * @Description: TODO(课程表管理)
 */
public class WClassScheduleController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ClassScheduleService classScheduleService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(课程表管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <ClassScheduleVo>
     * @author: 王恩
     * @version: 2019-01-06
     */
    @GetMapping(value = "/findMyClassSchedule")
    public ResultResponse findClassScheduleList(ClassScheduleQVo condition, PageParamNoChangeSord page) throws Exception {
        return this.classScheduleService.MyClassSchedule(condition, page.getStart(), page.getLimit(), page.getOrderBy(), this.getCurUserProp());
    }


    @GetMapping(value = "/notDoneTestList")
    public ResultResponse notDoneTestList(ClassScheduleQVo condition, PageParamNoChangeSord page) throws Exception {
        ResultResponse rst = this.classScheduleService.notDoneTestList(condition, page.getStart(), page.getLimit(), page.getOrderBy(), this.getCurUserProp());
        return rst;
    }


    @GetMapping(value = "/DoneTestList")
    public ResultResponse doneTestList(ClassScheduleQVo condition, PageParamNoChangeSord page) throws Exception {
        ResultResponse rst = this.classScheduleService.doneTestList(condition, page.getStart(), page.getLimit(), page.getOrderBy(), this.getCurUserProp());
        return rst;
    }

    /**
     * @throws
     * @Title:selectClassScheduleByPrimaryKey
     * @Description: TODO(获取课程表管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ClassSchedule>
     * @author: 王恩
     * @version: 2019-01-06
     */
    @GetMapping(value = "/selectClassScheduleByPrimaryKey")
    public SingleResult<ClassScheduleVo> selectClassScheduleByPrimaryKey(String id) throws Exception {
        return this.classScheduleService.selectClassScheduleByPrimaryKey(id);
    }

    /**
     * 获取教师已测评列表 & 待结课列表
     *
     * @param status 1-未结课；2-已结课（待测评）
     * @return ResultResponse
     */
    @GetMapping(value = "/findList")
    public ResultResponse findList(String status, PageParamNoChangeSord page) throws Exception {
        //登录教师
        UserProp teacher = this.getCurUserProp();
        if (teacher == null) {
            return new ResultResponse(ResultCode.FAIL, "获取登录信息失败");
        }
        //查询条件
        ClassScheduleQVo condition = new ClassScheduleQVo();
        condition.setStatus(status);
        condition.setTeacherId(teacher.getUserId());
        //结果查询
        List<ClassScheduleVo> rst = classScheduleService.findList(condition, page.getStart(), page.getLimit(), page.getOrderBy());

        return new ResultResponse(ResultCode.SUCCESS, "ok", rst);
    }

    /**
     * 教官结课处理
     *
     * @param clsScheduleId 课程ID
     */
    @PostMapping(value = "/closeClass")
    public ResultResponse closeClass(String clsScheduleId) {
        //登录教官
        UserProp teacher = this.getCurUserProp();
        if (teacher == null) {
            return new ResultResponse(ResultCode.FAIL, "获取登录信息失败");
        }
        if (CommonUtils.isBlank(clsScheduleId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少课程ID");
        }

        return classScheduleService.closeClass(teacher.getUserId(), clsScheduleId);

    }

    /**
     * 教官课时统计报表
     *
     * @param teacherId 教官用户ID
     * @return ResultResponse
     */
    @GetMapping("/classReport")
    public ResultResponse classReport(String teacherId) {
        if (CommonUtils.isBlank(teacherId)) {
            return new ResultResponse(ResultCode.FAIL, "教官用户ID");
        }

        return classScheduleService.classReport(teacherId);
    }
}

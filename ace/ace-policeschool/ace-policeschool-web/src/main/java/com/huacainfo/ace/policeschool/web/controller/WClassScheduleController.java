package com.huacainfo.ace.policeschool.web.controller;

import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.policeschool.service.ClassScheduleService;
import com.huacainfo.ace.policeschool.vo.ClassScheduleQVo;
import com.huacainfo.ace.policeschool.vo.ClassScheduleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
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
    @RequestMapping(value = "/findMyClassSchedule")
    @ResponseBody
    public ResultResponse findClassScheduleList(ClassScheduleQVo condition, PageParamNoChangeSord page) throws Exception {
        return this.classScheduleService
                .MyClassSchedule(condition, page.getStart(), page.getLimit(), page.getOrderBy(), this.getCurUserProp());
    }


    @RequestMapping(value = "/notDoneTestList")
    @ResponseBody
    public ResultResponse notDoneTestList(ClassScheduleQVo condition, PageParamNoChangeSord page) throws Exception {
        ResultResponse rst = this.classScheduleService.notDoneTestList(condition, page.getStart(), page.getLimit(), page.getOrderBy(), this.getCurUserProp());
        return rst;
    }


    @RequestMapping(value = "/DoneTestList")
    @ResponseBody
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
    @RequestMapping(value = "/selectClassScheduleByPrimaryKey")
    @ResponseBody
    public SingleResult<ClassScheduleVo> selectClassScheduleByPrimaryKey(String id) throws Exception {
        return this.classScheduleService.selectClassScheduleByPrimaryKey(id);
    }
}

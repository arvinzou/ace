package com.huacainfo.ace.partyschool.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.tools.CommonUtils;
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
import com.huacainfo.ace.partyschool.model.ClassSchedule;
import com.huacainfo.ace.partyschool.service.ClassScheduleService;
import com.huacainfo.ace.partyschool.vo.ClassScheduleVo;
import com.huacainfo.ace.partyschool.vo.ClassScheduleQVo;

@Controller
@RequestMapping("/classSchedule")
/**
 * @author: 王恩
 * @version: 2019-01-06
 * @Description: TODO(课程表管理)
 */
public class ClassScheduleController extends BisBaseController {


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
    @RequestMapping(value = "/findClassScheduleList")
    @ResponseBody
    public PageResult<ClassScheduleVo> findClassScheduleList(ClassScheduleQVo condition,
                                                             PageParamNoChangeSord page) throws Exception {
        PageResult<ClassScheduleVo> rst = this.classScheduleService
                .findClassScheduleList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertClassSchedule
     * @Description: TODO(添加课程表管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-06
     */
    @RequestMapping(value = "/insertClassSchedule")
    @ResponseBody
    public MessageResponse insertClassSchedule(String jsons) throws Exception {
        ClassSchedule obj = JSON.parseObject(jsons, ClassSchedule.class);
        return this.classScheduleService.insertClassSchedule(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateClassSchedule
     * @Description: TODO(更新课程表管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-06
     */
    @RequestMapping(value = "/updateClassSchedule")
    @ResponseBody
    public MessageResponse updateClassSchedule(String jsons) throws Exception {
        ClassSchedule obj = JSON.parseObject(jsons, ClassSchedule.class);
        return this.classScheduleService.updateClassSchedule(obj, this.getCurUserProp());
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

    /**
     * @throws
     * @Title:deleteClassScheduleByClassScheduleId
     * @Description: TODO(删除课程表管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-06
     */
    @RequestMapping(value = "/deleteClassScheduleByClassScheduleId")
    @ResponseBody
    public MessageResponse deleteClassScheduleByClassScheduleId(String id) throws Exception {
        return this.classScheduleService.deleteClassScheduleByClassScheduleId(id, this.getCurUserProp());
    }


    @RequestMapping(value = "/LearnedCourses")
    @ResponseBody
    public MessageResponse LearnedCourses(ClassScheduleQVo condition, PageParamNoChangeSord page) throws Exception {
        return this.classScheduleService.LearnedCourses(condition, page.getStart(), page.getLimit(), page.getOrderBy());
    }


    /**
     * @throws
     * @Title:updateClassSchedule
     * @Description: TODO(更新课程表管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-06
     */
    @RequestMapping(value = "/updateIfTest")
    @ResponseBody
    public MessageResponse updateIfTest(String id) throws Exception {
        ClassSchedule obj = this.classScheduleService.selectClassScheduleByPrimaryKey(id).getValue();
        if(CommonUtils.isBlank(obj)){
            new MessageResponse(ResultCode.FAIL,"数据丢失");
        }
        String i=obj.getIfTest();
        obj.setIfTest(i.equals("1")?"0":"1");
        return this.classScheduleService.updateClassSchedule(obj, this.getCurUserProp());
    }
}

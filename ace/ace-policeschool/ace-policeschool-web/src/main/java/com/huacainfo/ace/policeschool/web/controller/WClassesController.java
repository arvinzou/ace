package com.huacainfo.ace.policeschool.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.policeschool.service.ClassesService;
import com.huacainfo.ace.policeschool.service.StudentService;
import com.huacainfo.ace.policeschool.vo.ClassesQVo;
import com.huacainfo.ace.policeschool.vo.ClassesVo;
import com.huacainfo.ace.policeschool.vo.StudentQVo;
import com.huacainfo.ace.policeschool.vo.StudentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/www/classes")
/**
 * @author: Arvin
 * @version: 2019-01-03
 * @Description: TODO(班级管理)
 */
public class WClassesController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ClassesService classesService;
    @Autowired
    private StudentService studentService;

    /**
     * @throws
     * @Title:selectClassesByPrimaryKey
     * @Description: TODO(获取班级须知)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResultResponse
     * @author: Arvin
     * @version: 2019-01-03
     */
    @RequestMapping(value = "/getClassesInfo")
    @ResponseBody
    public ResultResponse selectClassesByPrimaryKey(String classId) throws Exception {
        return this.classesService.selectClassesByPrimaryKeyVo(this.getCurUserProp(), classId);
    }

    @RequestMapping(value = "/findClassList")
    @ResponseBody
    public PageResult<ClassesVo> findFilesList(ClassesQVo condition, PageParamNoChangeSord page) throws Exception {

        return this.classesService.findClassesList(condition, page.getStart(), page.getLimit(), page.getOrderBy());

    }

    @RequestMapping(value = "/getMyClasses")
    @ResponseBody
    public ResultResponse getMyClasses() throws Exception {
        UserProp userProp = this.getCurUserProp();
        if (userProp == null) {
            return new ResultResponse(ResultCode.FAIL, "请先跳转登录");
        }
        return this.classesService.getMyClasses(this.getCurUserProp());
    }

    @RequestMapping(value = "/getAllClasses")
    @ResponseBody
    public ResultResponse getAllClasses() throws Exception {
        UserProp userProp = this.getCurUserProp();
        if (userProp == null) {
            return new ResultResponse(ResultCode.FAIL, "请先跳转登录");
        }
        return this.classesService.getAllClasses(this.getCurUserProp());
    }

    @RequestMapping(value = "/findStudentList")
    @ResponseBody
    public PageResult<StudentVo> findStudentList(StudentQVo condition,
                                                 PageParamNoChangeSord page) throws Exception {
        PageResult<StudentVo> rst =
                this.studentService.findStudentList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        return rst;
    }
}

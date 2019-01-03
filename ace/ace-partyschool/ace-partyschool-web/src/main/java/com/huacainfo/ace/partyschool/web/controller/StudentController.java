package com.huacainfo.ace.partyschool.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.partyschool.model.Student;
import com.huacainfo.ace.partyschool.service.StudentService;
import com.huacainfo.ace.partyschool.vo.StudentQVo;
import com.huacainfo.ace.partyschool.vo.StudentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/student")
/**
 * @author: Arvin
 * @version: 2018-12-29
 * @Description: TODO(学员管理)
 */
public class StudentController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StudentService studentService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(学员管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <StudentVo>
     * @author: Arvin
     * @version: 2018-12-29
     */
    @RequestMapping(value = "/findStudentList")
    @ResponseBody
    public PageResult<StudentVo> findStudentList(StudentQVo condition,
                                                 PageParamNoChangeSord page) throws Exception {

        PageResult<StudentVo> rst =
                this.studentService.findStudentList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertStudent
     * @Description: TODO(添加学员管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-12-29
     */
    @RequestMapping(value = "/insertStudent")
    @ResponseBody
    public MessageResponse insertStudent(String jsons) throws Exception {
        Student obj = JSON.parseObject(jsons, Student.class);
        return this.studentService.insertStudent(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStudent
     * @Description: TODO(更新学员管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-12-29
     */
    @RequestMapping(value = "/updateStudent")
    @ResponseBody
    public MessageResponse updateStudent(String jsons) throws Exception {
        Student obj = JSON.parseObject(jsons, Student.class);
        return this.studentService.updateStudent(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectStudentByPrimaryKey
     * @Description: TODO(获取学员管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Student>
     * @author: Arvin
     * @version: 2018-12-29
     */
    @RequestMapping(value = "/selectStudentByPrimaryKey")
    @ResponseBody
    public SingleResult<StudentVo> selectStudentByPrimaryKey(String id) throws Exception {
        return this.studentService.selectStudentByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteStudentByStudentId
     * @Description: TODO(删除学员管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-12-29
     */
    @RequestMapping(value = "/deleteStudentByStudentId")
    @ResponseBody
    public MessageResponse deleteStudentByStudentId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.studentService.deleteStudentByStudentId(id, this.getCurUserProp());
    }

}

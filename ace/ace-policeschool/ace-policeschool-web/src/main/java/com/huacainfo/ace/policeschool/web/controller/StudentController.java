package com.huacainfo.ace.policeschool.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.policeschool.model.Student;
import com.huacainfo.ace.policeschool.service.StudentService;
import com.huacainfo.ace.policeschool.vo.StudentQVo;
import com.huacainfo.ace.policeschool.vo.StudentVo;
import com.huacainfo.ace.portal.vo.MongoFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
                                                 PageParamNoChangeSord page, String q) throws Exception {
        condition.setName(CommonUtils.isBlank(q) ? condition.getName() : q);
        PageResult<StudentVo> rst =
                this.studentService.findStudentList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }


    @RequestMapping(value = "/selectStudentList")
    @ResponseBody
    public PageResult<StudentVo> selectStudentList(StudentQVo condition,
                                                 PageParamNoChangeSord page, String q) throws Exception {
        condition.setName(CommonUtils.isBlank(q) ? condition.getName() : q);
        condition.setStatus("1");
        PageResult<StudentVo> rst = this.studentService.selectStudentList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
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
        try {
            return studentService.addStudent(obj, this.getCurUserProp());
        } catch (CustomException e) {
            return new MessageResponse(ResultCode.FAIL, e.getMsg());
        }
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


    /**
     * 批量导入学员
     *
     * @param file  上载文件
     * @param clsId 班级ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/importXls")
    @ResponseBody
    public MessageResponse importXls(@RequestParam MultipartFile[] file, String clsId) throws Exception {

        if (StringUtil.isEmpty(clsId)) {
            return new MessageResponse(ResultCode.FAIL, "请选择班级信息");
        }

        List<Map<String, Object>> list = new ArrayList<>();
        MongoFile[] files = new MongoFile[file.length];
        ExcelUtils utils = new ExcelUtils();
        int i = 0;
        for (MultipartFile o : file) {
            MongoFile obj = new MongoFile();
            obj.setLength(o.getSize());
            obj.setInputStream(o.getInputStream());
            obj.setFilename(o.getOriginalFilename());
            files[i] = obj;
            i++;
            String ext = obj.getFilename().toLowerCase().substring(obj.getFilename().toLowerCase().lastIndexOf("."));
            this.logger.info(ext);
            if (ext.equals(".xls")) {
                list = utils.readExcelByJXL(obj.getInputStream(), 2);
            }
            if (ext.equals(".xlsx")) {
                list = utils.readExcelByPOI(obj.getInputStream(), 2);
            }
        }

        return studentService.insertImportXls(list, this.getCurUserProp(), clsId);
    }

    /**
     * 账户恢复
     *
     * @param id did
     * @return MessageResponse
     */
    @RequestMapping(value = "/recover")
    @ResponseBody
    public MessageResponse recover(String id) throws Exception {

        return studentService.recover(id, this.getCurUserProp());
    }
}

package com.huacainfo.ace.policeschool.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.policeschool.model.Teacher;
import com.huacainfo.ace.policeschool.service.TeacherService;
import com.huacainfo.ace.policeschool.vo.TeacherQVo;
import com.huacainfo.ace.policeschool.vo.TeacherVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/teacher")
/**
 * @author: Arvin
 * @version: 2019-01-02
 * @Description: TODO(教职工管理)
 */
public class TeacherController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TeacherService teacherService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(教职工管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <TeacherVo>
     * @author: Arvin
     * @version: 2019-01-02
     */
    @RequestMapping(value = "/findTeacherList")
    @ResponseBody
    public PageResult<TeacherVo> findTeacherList(TeacherQVo condition,
                                                 PageParamNoChangeSord page, String q) throws Exception {
        condition.setName(CommonUtils.isBlank(q) ? condition.getName() : q);
        PageResult<TeacherVo> rst = this.teacherService
                .findTeacherList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(班主任查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <TeacherVo>
     * @author: Arvin
     * @version: 2019-01-02
     */
    @RequestMapping(value = "/findHeadmasterList")
    @ResponseBody
    public PageResult<TeacherVo> findHeadmasterList(TeacherQVo condition,
                                                    PageParamNoChangeSord page, String q) throws Exception {
        if (!CommonUtils.isBlank(q)) {
            condition.setId("");
            condition.setName(CommonUtils.isBlank(q) ? condition.getName() : q);
        }
        PageResult<TeacherVo> rst = this.teacherService
                .findHeadmasterList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertTeacher
     * @Description: TODO(添加教职工管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-02
     */
    @RequestMapping(value = "/insertTeacher")
    @ResponseBody
    public MessageResponse insertTeacher(String jsons) throws Exception {
        Teacher obj = JSON.parseObject(jsons, Teacher.class);

        try {
            return teacherService.addTeacher(obj, this.getCurUserProp());
        } catch (CustomException e) {
            return new MessageResponse(ResultCode.FAIL, e.getMsg());
        }
    }

    /**
     * @throws
     * @Title:updateTeacher
     * @Description: TODO(更新教职工管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-02
     */
    @RequestMapping(value = "/updateTeacher")
    @ResponseBody
    public MessageResponse updateTeacher(String jsons) throws Exception {
        Teacher obj = JSON.parseObject(jsons, Teacher.class);
        return this.teacherService.updateTeacher(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectTeacherByPrimaryKey
     * @Description: TODO(获取教职工管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Teacher>
     * @author: Arvin
     * @version: 2019-01-02
     */
    @RequestMapping(value = "/selectTeacherByPrimaryKey")
    @ResponseBody
    public SingleResult<TeacherVo> selectTeacherByPrimaryKey(String id) throws Exception {
        return this.teacherService.selectTeacherByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteTeacherByTeacherId
     * @Description: TODO(删除教职工管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-02
     */
    @RequestMapping(value = "/deleteTeacherByTeacherId")
    @ResponseBody
    public MessageResponse deleteTeacherByTeacherId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.teacherService.deleteTeacherByTeacherId(id, this.getCurUserProp());
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

        return this.teacherService.recover(id, this.getCurUserProp());
    }

}

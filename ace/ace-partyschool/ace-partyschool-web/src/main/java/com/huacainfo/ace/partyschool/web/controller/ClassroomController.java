package com.huacainfo.ace.partyschool.web.controller;

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
import com.huacainfo.ace.partyschool.model.Classroom;
import com.huacainfo.ace.partyschool.service.ClassroomService;
import com.huacainfo.ace.partyschool.vo.ClassroomVo;
import com.huacainfo.ace.partyschool.vo.ClassroomQVo;

@Controller
@RequestMapping("/classroom")
/**
 * @author: Arvin
 * @version: 2019-01-03
 * @Description: TODO(教室管理)
 */
public class ClassroomController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ClassroomService classroomService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(教室管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <ClassroomVo>
     * @author: Arvin
     * @version: 2019-01-03
     */
    @RequestMapping(value = "/findClassroomList")
    @ResponseBody
    public PageResult<ClassroomVo> findClassroomList(ClassroomQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<ClassroomVo> rst = this.classroomService
                .findClassroomList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    @RequestMapping(value = "/selectClassroomList")
    @ResponseBody
    public PageResult<ClassroomVo> selectClassroomList(ClassroomQVo condition, PageParamNoChangeSord page,String q) throws Exception {
        if(!CommonUtils.isBlank(q)){
            condition.setName(q);
        }
        PageResult<ClassroomVo> rst = this.classroomService.selectClassroomList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }


    /**
     * @throws
     * @Title:insertClassroom
     * @Description: TODO(添加教室管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-03
     */
    @RequestMapping(value = "/insertClassroom")
    @ResponseBody
    public MessageResponse insertClassroom(String jsons) throws Exception {
        Classroom obj = JSON.parseObject(jsons, Classroom.class);
        return this.classroomService.insertClassroom(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateClassroom
     * @Description: TODO(更新教室管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-03
     */
    @RequestMapping(value = "/updateClassroom")
    @ResponseBody
    public MessageResponse updateClassroom(String jsons) throws Exception {
        Classroom obj = JSON.parseObject(jsons, Classroom.class);
        return this.classroomService.updateClassroom(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectClassroomByPrimaryKey
     * @Description: TODO(获取教室管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Classroom>
     * @author: Arvin
     * @version: 2019-01-03
     */
    @RequestMapping(value = "/selectClassroomByPrimaryKey")
    @ResponseBody
    public SingleResult<ClassroomVo> selectClassroomByPrimaryKey(String id) throws Exception {
        return this.classroomService.selectClassroomByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteClassroomByClassroomId
     * @Description: TODO(删除教室管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-03
     */
    @RequestMapping(value = "/deleteClassroomByClassroomId")
    @ResponseBody
    public MessageResponse deleteClassroomByClassroomId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.classroomService.deleteClassroomByClassroomId(id, this.getCurUserProp());
    }

    /**
     * 恢复班级状态
     *
     * @param id did
     * @return MessageResponse
     */
    @RequestMapping(value = "/recover")
    @ResponseBody
    public MessageResponse recover(String id) throws Exception {

        return classroomService.recover(id, this.getCurUserProp());
    }

}

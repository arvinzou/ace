package com.huacainfo.ace.partyschool.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.partyschool.model.Classes;
import com.huacainfo.ace.partyschool.service.ClassesService;
import com.huacainfo.ace.partyschool.vo.ClassesQVo;
import com.huacainfo.ace.partyschool.vo.ClassesVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/classes")
/**
 * @author: Arvin
 * @version: 2019-01-03
 * @Description: TODO(班级管理)
 */
public class ClassesController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ClassesService classesService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(班级管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <ClassesVo>
     * @author: Arvin
     * @version: 2019-01-03
     */
    @RequestMapping(value = "/findClassesList")
    @ResponseBody
    public PageResult
            <ClassesVo> findClassesList(ClassesQVo condition,
                                        PageParamNoChangeSord page) throws Exception {

        PageResult<ClassesVo> rst = this.classesService.findClassesList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertClasses
     * @Description: TODO(添加班级管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-03
     */
    @RequestMapping(value = "/insertClasses")
    @ResponseBody
    public MessageResponse insertClasses(String jsons) throws Exception {
        Classes obj = JSON.parseObject(jsons, Classes.class);
        return this.classesService.insertClasses(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateClasses
     * @Description: TODO(更新班级管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-03
     */
    @RequestMapping(value = "/updateClasses")
    @ResponseBody
    public MessageResponse updateClasses(String jsons) throws Exception {
        Classes obj = JSON.parseObject(jsons, Classes.class);
        return this.classesService.updateClasses(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectClassesByPrimaryKey
     * @Description: TODO(获取班级管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Classes>
     * @author: Arvin
     * @version: 2019-01-03
     */
    @RequestMapping(value = "/selectClassesByPrimaryKey")
    @ResponseBody
    public SingleResult
            <ClassesVo> selectClassesByPrimaryKey(String id) throws Exception {
        return this.classesService.selectClassesByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteClassesByClassesId
     * @Description: TODO(删除班级管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-03
     */
    @RequestMapping(value = "/deleteClassesByClassesId")
    @ResponseBody
    public MessageResponse deleteClassesByClassesId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.classesService.deleteClassesByClassesId(id, this.getCurUserProp());
    }


    @ResponseBody
    @RequestMapping(value = "/findByQ")
    public Map<String, Object> findByQ(String q, String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("q", id);
        if (!CommonUtils.isBlank(q)) {
            params.put("q", q);
        }

        return classesService.findByQ(params);
    }
}

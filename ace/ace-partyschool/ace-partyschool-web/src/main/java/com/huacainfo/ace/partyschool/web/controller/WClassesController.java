package com.huacainfo.ace.partyschool.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
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
        return this.classesService.selectClassesByPrimaryKeyVo(this.getCurUserProp(),classId);
    }

    @RequestMapping(value = "/findClassList")
    @ResponseBody
    public PageResult<ClassesVo> findFilesList(ClassesQVo condition,PageParamNoChangeSord page) throws Exception {

        return this.classesService.findClassesList(condition, page.getStart(), page.getLimit(), page.getOrderBy());

    }
}

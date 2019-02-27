package com.huacainfo.ace.policeschool.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.policeschool.model.Files;
import com.huacainfo.ace.policeschool.service.ClsFilesService;
import com.huacainfo.ace.policeschool.vo.FilesQVo;
import com.huacainfo.ace.policeschool.vo.FilesVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/www/files")
/**
 * @author: Arvin
 * @version: 2019-01-04
 * @Description: TODO(班级文件)
 */
public class WFilesController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ClsFilesService clsFilesService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(班级文件分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <FilesVo>
     * @author: Arvin
     * @version: 2019-01-04
     */
    @RequestMapping(value = "/findFilesListVo")
    @ResponseBody
    public PageResult<FilesVo> findFilesList(FilesQVo condition, PageParamNoChangeSord page) throws Exception {
        return this.clsFilesService.findFilesList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
    }


    @RequestMapping(value = "/findFilesList")
    @ResponseBody
    public ResultResponse findFilesListVo(FilesQVo condition, PageParamNoChangeSord page) throws Exception {
        UserProp userProp = this.getCurUserProp();
        if (userProp == null) {
            return new ResultResponse(ResultCode.FAIL, "请先跳转登录");
        }
        return this.clsFilesService.findFilesListVo(condition, page.getStart(), page.getLimit(), page.getOrderBy(), userProp);
    }


    /**
     * @throws
     * @Title:insertFiles
     * @Description: TODO(添加班级文件)
     * @param: @throws Exception
     * @return: ResultResponse
     * @author: Arvin
     * @version: 2019-01-04
     * Student:url
     * teacher:url: & classesId: eg:classId1,classId2
     */
    @RequestMapping(value = "/insertFiles")
    @ResponseBody
    public ResultResponse insertFiles(Files obj) throws Exception {
        return this.clsFilesService.insertFilesVo(obj, this.getCurUserProp());
    }

}

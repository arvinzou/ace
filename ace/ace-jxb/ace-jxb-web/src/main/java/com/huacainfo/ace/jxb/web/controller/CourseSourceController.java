package com.huacainfo.ace.jxb.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.CourseSource;
import com.huacainfo.ace.jxb.service.CourseSourceService;
import com.huacainfo.ace.jxb.vo.CourseSourceQVo;
import com.huacainfo.ace.jxb.vo.CourseSourceVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/courseSource")
/**
 * @author: Arvin
 * @version: 2018-08-06
 * @Description: TODO(课程资源)
 */
public class CourseSourceController extends JxbBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CourseSourceService courseSourceService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(课程资源分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <CourseSourceVo>
     * @author: Arvin
     * @version: 2018-08-06
     */
    @RequestMapping(value = "/findCourseSourceList")
    @ResponseBody
    public PageResult<CourseSourceVo> findCourseSourceList(CourseSourceQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<CourseSourceVo> rst = this.courseSourceService
                .findCourseSourceList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertCourseSource
     * @Description: TODO(添加课程资源)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @RequestMapping(value = "/insertCourseSource")
    @ResponseBody
    public MessageResponse insertCourseSource(String jsons) throws Exception {
        CourseSource obj = JSON.parseObject(jsons, CourseSource.class);
        return this.courseSourceService.insertCourseSource(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateCourseSource
     * @Description: TODO(更新课程资源)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @RequestMapping(value = "/updateCourseSource")
    @ResponseBody
    public MessageResponse updateCourseSource(String jsons) throws Exception {
        CourseSource obj = JSON.parseObject(jsons, CourseSource.class);
        return this.courseSourceService.updateCourseSource(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectCourseSourceByPrimaryKey
     * @Description: TODO(获取课程资源)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CourseSource>
     * @author: Arvin
     * @version: 2018-08-06
     */
    @RequestMapping(value = "/selectCourseSourceByPrimaryKey")
    @ResponseBody
    public SingleResult<CourseSourceVo> selectCourseSourceByPrimaryKey(String id) throws Exception {
        return this.courseSourceService.selectCourseSourceByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteCourseSourceByCourseSourceId
     * @Description: TODO(删除课程资源)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @RequestMapping(value = "/deleteCourseSourceByCourseSourceId")
    @ResponseBody
    public MessageResponse deleteCourseSourceByCourseSourceId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.courseSourceService.deleteCourseSourceByCourseSourceId(id, this.getCurUserProp());
    }
}

package com.huacainfo.ace.jxb.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.CoursePart;
import com.huacainfo.ace.jxb.service.CoursePartService;
import com.huacainfo.ace.jxb.vo.CoursePartQVo;
import com.huacainfo.ace.jxb.vo.CoursePartVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/coursePart")
/**
 * @author: Arvin
 * @version: 2018-08-06
 * @Description: TODO(课程章节)
 */
public class CoursePartController extends JxbBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CoursePartService coursePartService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(课程章节分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <CoursePartVo>
     * @author: Arvin
     * @version: 2018-08-06
     */
    @RequestMapping(value = "/findCoursePartList")
    @ResponseBody
    public PageResult<CoursePartVo> findCoursePartList(CoursePartQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<CoursePartVo> rst = this.coursePartService
                .findCoursePartList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertCoursePart
     * @Description: TODO(添加课程章节)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @RequestMapping(value = "/insertCoursePart")
    @ResponseBody
    public MessageResponse insertCoursePart(String jsons) throws Exception {
        CoursePart obj = JSON.parseObject(jsons, CoursePart.class);
        return this.coursePartService.insertCoursePart(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateCoursePart
     * @Description: TODO(更新课程章节)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @RequestMapping(value = "/updateCoursePart")
    @ResponseBody
    public MessageResponse updateCoursePart(String jsons) throws Exception {
        CoursePart obj = JSON.parseObject(jsons, CoursePart.class);
        return this.coursePartService.updateCoursePart(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectCoursePartByPrimaryKey
     * @Description: TODO(获取课程章节)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CoursePart>
     * @author: Arvin
     * @version: 2018-08-06
     */
    @RequestMapping(value = "/selectCoursePartByPrimaryKey")
    @ResponseBody
    public SingleResult<CoursePartVo> selectCoursePartByPrimaryKey(String id) throws Exception {
        return this.coursePartService.selectCoursePartByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteCoursePartByCoursePartId
     * @Description: TODO(删除课程章节)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @RequestMapping(value = "/deleteCoursePartByCoursePartId")
    @ResponseBody
    public MessageResponse deleteCoursePartByCoursePartId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.coursePartService.deleteCoursePartByCoursePartId(id, this.getCurUserProp());
    }
}

package com.huacainfo.ace.jxb.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.CourseCmt;
import com.huacainfo.ace.jxb.service.CourseCmtService;
import com.huacainfo.ace.jxb.vo.CourseCmtQVo;
import com.huacainfo.ace.jxb.vo.CourseCmtVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/courseCmt")
/**
 * @author: Arvin
 * @version: 2018-08-06
 * @Description: TODO(课程评论)
 */
public class CourseCmtController extends JxbBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CourseCmtService courseCmtService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(课程评论分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <CourseCmtVo>
     * @author: Arvin
     * @version: 2018-08-06
     */
    @RequestMapping(value = "/findCourseCmtList")
    @ResponseBody
    public PageResult<CourseCmtVo> findCourseCmtList(CourseCmtQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<CourseCmtVo> rst = this.courseCmtService
                .findCourseCmtList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertCourseCmt
     * @Description: TODO(添加课程评论)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @RequestMapping(value = "/insertCourseCmt")
    @ResponseBody
    public MessageResponse insertCourseCmt(String jsons) throws Exception {
        CourseCmt obj = JSON.parseObject(jsons, CourseCmt.class);
        return this.courseCmtService.insertCourseCmt(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateCourseCmt
     * @Description: TODO(更新课程评论)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @RequestMapping(value = "/updateCourseCmt")
    @ResponseBody
    public MessageResponse updateCourseCmt(String jsons) throws Exception {
        CourseCmt obj = JSON.parseObject(jsons, CourseCmt.class);
        return this.courseCmtService.updateCourseCmt(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectCourseCmtByPrimaryKey
     * @Description: TODO(获取课程评论)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CourseCmt>
     * @author: Arvin
     * @version: 2018-08-06
     */
    @RequestMapping(value = "/selectCourseCmtByPrimaryKey")
    @ResponseBody
    public SingleResult<CourseCmtVo> selectCourseCmtByPrimaryKey(String id) throws Exception {
        return this.courseCmtService.selectCourseCmtByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteCourseCmtByCourseCmtId
     * @Description: TODO(删除课程评论)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @RequestMapping(value = "/deleteCourseCmtByCourseCmtId")
    @ResponseBody
    public MessageResponse deleteCourseCmtByCourseCmtId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.courseCmtService.deleteCourseCmtByCourseCmtId(id, this.getCurUserProp());
    }

    @RequestMapping(value = "/findMyCourseCmtList")
    @ResponseBody
    public PageResult<CourseCmtVo> findMyCourseCmtList(CourseCmtQVo condition, PageParamNoChangeSord page) throws Exception {
        condition.setCounselorId(getCurUserProp().getUserId());

        PageResult<CourseCmtVo> rst = this.courseCmtService
                .findCourseCmtList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }
}

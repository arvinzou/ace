package com.huacainfo.ace.jxb.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.jxb.model.PostLevel;
import com.huacainfo.ace.jxb.service.PostLevelService;
import com.huacainfo.ace.jxb.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/postLevel")
/**
 * @author: Arvin
 * @version: 2018-08-08
 * @Description: TODO(咨询师岗位级别配置)
 */
public class PostLevelController extends JxbBaseController {

    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PostLevelService postLevelService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(咨询师岗位级别配置分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <PostLevelVo>
     * @author: Arvin
     * @version: 2018-08-08
     */
    @RequestMapping(value = "/findPostLevelList")
    @ResponseBody
    public PageResult<PostLevelVo> findPostLevelList(PostLevelQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<PostLevelVo> rst = this.postLevelService.findPostLevelList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertPostLevel
     * @Description: TODO(添加咨询师岗位级别配置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-08
     */
    @RequestMapping(value = "/insertPostLevel")
    @ResponseBody
    public MessageResponse insertPostLevel(String jsons) throws Exception {
        PostLevel obj = JSON.parseObject(jsons, PostLevel.class);
        return this.postLevelService.insertPostLevel(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updatePostLevel
     * @Description: TODO(更新咨询师岗位级别配置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-08
     */
    @RequestMapping(value = "/updatePostLevel")
    @ResponseBody
    public MessageResponse updatePostLevel(String jsons) throws Exception {
        PostLevel obj = JSON.parseObject(jsons, PostLevel.class);
        return this.postLevelService.updatePostLevel(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectPostLevelByPrimaryKey
     * @Description: TODO(获取咨询师岗位级别配置)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<PostLevel>
     * @author: Arvin
     * @version: 2018-08-08
     */
    @RequestMapping(value = "/selectPostLevelByPrimaryKey")
    @ResponseBody
    public SingleResult<PostLevelVo> selectPostLevelByPrimaryKey(String id) throws Exception {
        return this.postLevelService.selectPostLevelByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deletePostLevelByPostLevelId
     * @Description: TODO(删除咨询师岗位级别配置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-08
     */
    @RequestMapping(value = "/deletePostLevelByPostLevelId")
    @ResponseBody
    public MessageResponse deletePostLevelByPostLevelId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.postLevelService.deletePostLevelByPostLevelId(id, this.getCurUserProp());
    }


    /**
     * 查询咨询师岗位列表
     *
     * @param condition
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findCounselorLevelList")
    @ResponseBody
    public PageResult<CounselorPostLevelVo> findCounselorLevelList(CounselorPostLevelQVo condition,
                                                                   PageParamNoChangeSord page) throws Exception {
        String nowDateTime = DateUtil.getNow();
        String year = nowDateTime.substring(0, 4);
        String quarter = getQuarter(DateUtil.getNowDate());

        if (StringUtil.isEmpty(condition.getYear())) {
            condition.setYear(year);
        }
        if (StringUtil.isEmpty(condition.getQuarter())) {
            condition.setQuarter(quarter);
        }

        PageResult<CounselorPostLevelVo> rst =
                postLevelService.findCounselorLevelList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    private String getQuarter(Date dt) {
        int myMonth = dt.getMonth() + 1;
        System.out.println(myMonth);
        if (myMonth >= 1 && myMonth <= 3) {
            return "第一季度";//return 1;
        }
        if (myMonth >= 4 && myMonth <= 6) {
            return "第二季度";// return 2;
        }
        if (myMonth >= 7 && myMonth <= 9) {
            return "第三季度";//return 3;
        }
        if (myMonth >= 10 && myMonth <= 12) {
            return "第四季度";//return 4;
        }
        return "第一季度";
    }


    /**
     * 指定咨询师岗位级别
     *
     * @param counselorId 咨询师id
     * @param postId      post_level.id
     * @return
     */
    @RequestMapping(value = "/modifyCounselorLevel")
    @ResponseBody
    public MessageResponse cfgCounselorPostLevel(String counselorId, String postId) {
        return postLevelService.cfgCounselorPostLevel(counselorId, postId);
    }


    /**
     * 收入管理报表
     */
    @RequestMapping(value = "/incomeReport")
    @ResponseBody
    public PageResult<IncomeReportVo> incomeReport(IncomeReportQVo condition, PageParamNoChangeSord page) {
        if (StringUtil.isEmpty(condition.getDateTime())) {
            condition.setDateTime(DateUtil.getNow());
        }

        PageResult<IncomeReportVo> rst =
                postLevelService.incomeReport(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }
}

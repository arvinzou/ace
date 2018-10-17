package com.huacainfo.ace.society.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.Activity;
import com.huacainfo.ace.society.model.ActivityReport;
import com.huacainfo.ace.society.service.ActivityDetailService;
import com.huacainfo.ace.society.service.ActivityReportService;
import com.huacainfo.ace.society.service.ActivityService;
import com.huacainfo.ace.society.service.SocietyOrgInfoService;
import com.huacainfo.ace.society.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("www/activity")
/**
 * @author: huacai003
 * @version: 2018-09-11
 * @Description: TODO(线下活动)
 */
public class WActivityController extends SocietyBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityDetailService activityDetailService;

    @Autowired
    private SocietyOrgInfoService societyOrgInfoService;

    @Autowired
    private ActivityReportService activityReportService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(线下活动分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <ActivityVo>
     * @author: huacai003
     * @version: 2018-09-11
     */
    /*获取活动列表*/
    @RequestMapping(value = "/findActivityList")
    @ResponseBody
    public ResultResponse findActivityList(ActivityQVo condition, PageParamNoChangeSord page) throws Exception {
        List<ActivityVo> rst = this.activityService.findActivityList(condition, page.getStart(), page.getLimit(), page.getOrderBy()).getRows();
        return new ResultResponse(ResultCode.SUCCESS,"获取成功",rst);
    }


    /**
     * @throws
     * @Title:insertActivity
     * @Description: TODO(添加线下活动)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-11
     */
    @RequestMapping(value = "/insertActivity")
    @ResponseBody
    public MessageResponse insertActivity(String jsons) throws Exception {
        Activity obj = JSON.parseObject(jsons, Activity.class);
        return this.activityService.insertActivity(obj);
    }

    /**
     * @throws
     * @Title:selectActivityByPrimaryKey
     * @Description: TODO(获取线下活动)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Activity>
     * @author: huacai003
     * @version: 2018-09-11
     */
    @RequestMapping(value = "/selectActivityByPrimaryKey")
    @ResponseBody
    public ResultResponse selectActivityByPrimaryKey(String id) throws Exception {
        ActivityVo activityVo =this.activityService.selectActivityByPrimaryKey(id).getValue();
        return new ResultResponse(ResultCode.SUCCESS,"获取成功",activityVo);
    }


    /**
     * @throws
     * @Title:selectActivityByPrimaryKey
     * @Description: TODO(获取参与人列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Activity>
     * @author: huacai003
     * @version: 2018-09-11
     */
    @RequestMapping(value = "/findActivityParticipants")
    @ResponseBody
    public ResultResponse selectActivityDByPrimaryKey(String activityId) throws Exception {
        ActivityDetailQVo activityDetailQVo=new ActivityDetailQVo();
        activityDetailQVo.setActivityId(activityId);
        List<ActivityDetailVo> activityDetailVos =this.activityDetailService.findActivityDetailList(activityDetailQVo,0,100,null).getRows();
        return new ResultResponse(ResultCode.SUCCESS,"获取成功",activityDetailVos);
    }

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(社会组织信息分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <SocietyOrgInfoVo>
     * @author: Arvin
     * @version: 2018-09-12
     */
    @RequestMapping(value = "/findSocietyOrgInfoList")
    @ResponseBody
    public PageResult<SocietyOrgInfoVo> findSocietyOrgInfoList(SocietyOrgInfoQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<SocietyOrgInfoVo> rst = this.societyOrgInfoService.findSocietyOrgInfoList(condition, page.getStart(), page.getLimit(), page.getOrderBy());

        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:selectActivityReportByPrimaryKey
     * @Description: TODO(获取活动报道)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ActivityReport>
     * @author: huacai003
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/selectActivityReportByActivityId")
    @ResponseBody
    public ResultResponse selectActivityReportByActivityId(String activityId) throws Exception {
        WxUser wxUser = getCurWxUser();
        ActivityReport activityReport=this.activityReportService.selectActivityReportByActivityId(activityId,wxUser);
        return  new ResultResponse(ResultCode.SUCCESS,"获取成功",activityReport);
    }

    /**
     * @throws
     * @Title:updateActivityReport
     * @Description: TODO(更新活动报道)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/updateActivityReport")
    @ResponseBody
    public ResultResponse updateActivityReport(String jsons) throws Exception {
        ActivityReport obj = JSON.parseObject(jsons, ActivityReport.class);
        WxUser wxUser = getCurWxUser();
        return this.activityReportService.WxUpdateActivityReport(obj,wxUser);
    }

    @RequestMapping(value = "/getUserType")
    @ResponseBody
    public ResultResponse getUserType() throws Exception {
//        WxUser wxUser = getCurWxUser();
        return  this.societyOrgInfoService.getUserType("o6qFn1J84GDX6dMs9jivNpfuu4rA");
    }
}

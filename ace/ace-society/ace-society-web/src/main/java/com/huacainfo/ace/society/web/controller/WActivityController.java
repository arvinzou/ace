package com.huacainfo.ace.society.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.society.model.Activity;
import com.huacainfo.ace.society.model.ActivityDetail;
import com.huacainfo.ace.society.model.ActivityReport;
import com.huacainfo.ace.society.service.*;
import com.huacainfo.ace.society.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private RegService regService;

    /**
     * @Description: TODO(公开的活动列表)
     */
    /*获取活动列表*/
    @RequestMapping(value = "/findPublicActivityList")
    @ResponseBody
    public ResultResponse findPublicActivityList(ActivityQVo condition, PageParamNoChangeSord page) throws Exception {
        condition.setStatus("3");
        List<ActivityVo> rst = this.activityService.findActivityList(condition, page.getStart(), page.getLimit(), page.getOrderBy()).getRows();
        return new ResultResponse(ResultCode.SUCCESS, "获取成功", rst);
    }

    /**
     * @Description: TODO(查找活动组织的活动列表)
     */
    /*获取活动列表*/
    @RequestMapping(value = "/findAdminSelfActivityList")
    @ResponseBody
    public ResultResponse findAdminSelfActivityList(ActivityQVo condition, PageParamNoChangeSord page) throws Exception {
        WxUser wxUser = getCurWxUser();
        condition.setInitiatorId(wxUser.getUnionId());
        if(wxUser == null){
            return  new ResultResponse(ResultCode.FAIL,"没有获取到用户信息");
        }
        List<ActivityVo> rst = this.activityService.findActivityList(condition, page.getStart(), page.getLimit(), page.getOrderBy()).getRows();
        return new ResultResponse(ResultCode.SUCCESS, "获取成功", rst);
    }


    /**
     * @Description: TODO(查找用户报名的活动列表)
     */
    @RequestMapping(value = "/findUserSelfActivityList")
    @ResponseBody
    public ResultResponse findUserSelfActivityList(ActivityQVo condition,PageParamNoChangeSord page) throws Exception {
        WxUser wxUser = getCurWxUser();
        if(wxUser == null){
            return  new ResultResponse(ResultCode.FAIL,"没有获取到用户信息");
        }
        condition.setUserId(wxUser.getUnionId());
        List<ActivityVo> rst = this.activityService.findUserActivityList(condition, page.getStart(), page.getLimit(), page.getOrderBy()).getRows();
        return new ResultResponse(ResultCode.SUCCESS, "获取成功", rst);
    }

    /**
     * @Description: TODO(查找正在进行的活动)
     */
    @RequestMapping(value = "/findActivitying")
    @ResponseBody
    public ResultResponse findActivitying(ActivityQVo condition,PageParamNoChangeSord page) throws Exception {
        condition.setStatus("3");
        condition.setIngId("ing");
        List<ActivityVo> rst = this.activityService.findActivityList(condition, page.getStart(), page.getLimit(), page.getOrderBy()).getRows();
        return new ResultResponse(ResultCode.SUCCESS, "获取成功", rst);
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
        WxUser wxUser = getCurWxUser();
        if(wxUser == null){
            return  new MessageResponse(ResultCode.FAIL,"系统繁忙，稍后重试");
        }
        return this.activityService.insertActivity(obj ,  wxUser);
    }


    /**
     * @Description: TODO(活动签到)
     */
    @RequestMapping(value = "/activitySign")
    @ResponseBody
    public MessageResponse activitySign(String filePath,String type,String id) throws Exception {
        WxUser wxUser = getCurWxUser();
        if(wxUser == null){
            return  new MessageResponse(ResultCode.FAIL,"没有获取到用户信息");
        }
        return this.activityService.activitySign(filePath, type, id ,  wxUser);
    }


    /**
     * @Description: TODO(根据id查找活动)
     */
    @RequestMapping(value = "/selectActivityByPrimaryKey")
    @ResponseBody
    public ResultResponse selectActivityByPrimaryKey(String id) throws Exception {
        ActivityVo activityVo = this.activityService.selectActivityByPrimaryKey(id).getValue();
        return new ResultResponse(ResultCode.SUCCESS, "获取成功", activityVo);
    }


    /**
     * @Description: TODO(获取参与人列表)
     */
    @RequestMapping(value = "/findActivityParticipants")
    @ResponseBody
    public ResultResponse selectActivityDByPrimaryKey(String activityId) throws Exception {
        ActivityDetailQVo activityDetailQVo = new ActivityDetailQVo();
        activityDetailQVo.setActivityId(activityId);
        List<ActivityDetailVo> activityDetailVos = this.activityDetailService.findActivityDetailList(activityDetailQVo, 0, 100, null).getRows();
        return new ResultResponse(ResultCode.SUCCESS, "获取成功", activityDetailVos);
    }

    /**
     * @Description: TODO(社会组织信息分页查询)
     * 主要是获取党组织
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
     * @Description: TODO(根据活动ID获取活动报道)
     */
    @RequestMapping(value = "/selectActivityReportByActivityId")
    @ResponseBody
    public ResultResponse selectActivityReportByActivityId(String activityId) throws Exception {
        WxUser wxUser = getCurWxUser();
        ActivityReport activityReport = this.activityReportService.selectActivityReportByActivityId(activityId, wxUser);
        return new ResultResponse(ResultCode.SUCCESS, "获取成功", activityReport);
    }

    /**
     * @Description: TODO(更新活动报道)
     */
    @RequestMapping(value = "/updateActivityReport")
    @ResponseBody
    public ResultResponse updateActivityReport(String jsons) throws Exception {
        ActivityReport obj = JSON.parseObject(jsons, ActivityReport.class);
        WxUser wxUser = getCurWxUser();
        return this.activityReportService.WxUpdateActivityReport(obj, wxUser);
    }


    /**
     * @Description: TODO(查找审核通过活动报道分页查询)
     */
    @RequestMapping(value = "/findPublicActivityReportList")
    @ResponseBody
    public ResultResponse findPublicActivityReportList( ActivityReportQVo activityReportQVo, PageParamNoChangeSord page) throws Exception {
        activityReportQVo.setStatus("3");
        List<ActivityReportVo> rst = this.activityReportService.findActivityReportList(activityReportQVo, page.getStart(), page.getLimit(), page.getOrderBy()).getRows();
        return new ResultResponse(ResultCode.SUCCESS, "获取数据", rst);
    }

    /**
     * @Description: TODO(获取活动报道)
     */
    @RequestMapping(value = "/selectActivityReportByPrimaryKey")
    @ResponseBody
    public ResultResponse selectActivityReportByPrimaryKey(String id) throws Exception {
        ActivityReportVo activityReportVo = this.activityReportService.selectActivityReportByPrimaryKey(id).getValue();
        return new ResultResponse(ResultCode.SUCCESS, "获取成功", activityReportVo);
    }


    /**
     * @Description: TODO(获取活动报道)
     */
    @RequestMapping(value = "/personalActivitydetails")
    @ResponseBody
    public ResultResponse personalActivitydetails(String activityId) throws Exception {
        WxUser wxUser = getCurWxUser();
        return this.activityDetailService.personalActivitydetails(activityId, wxUser.getUnionId());
    }


    /**
     * @Description: TODO(获取活动报名情况)
     */
    @RequestMapping(value = "/getApplyStatus")
    @ResponseBody
    public ResultResponse getApplyStatus(String activityId) throws Exception {
        Map<String,String> map=new HashMap<String ,String>();
        map.put("code","1");
//        code:1、未注册和鉴权2、账户类型为组织，3、未注册，4、已注册
        WxUser wxUser = getCurWxUser();
        if (wxUser == null) {
            return new ResultResponse(ResultCode.FAIL, "微信鉴权失败",map);
        }
        CustomerVo vo = regService.findByUserId(wxUser.getUnionId());
        if (null == vo) {
            return new ResultResponse(ResultCode.FAIL, "用户尚未注册",map);
        }
        if(vo.getPerson()==null){
            return new ResultResponse(ResultCode.FAIL, "组织用户",map.put("code","2"));
        }
        Object object=this.activityDetailService.personalActivitydetails(activityId, wxUser.getUnionId()).getData();
        if(object==null){
            map.put("code","3");
            return new ResultResponse(ResultCode.FAIL, "未报名",map);
        }
        map.put("code","4");
        return new ResultResponse(ResultCode.FAIL, "已报名",map);
    }


    /**
     * @Description: TODO(删除线下活动)
     */
    @RequestMapping(value = "/delActivity")
    @ResponseBody
    public MessageResponse deleteActivityByActivityId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.activityService.deleteActivityByActivityId(id, this.getCurUserProp());
    }

    /**
     * @Description: TODO(活动报名)
     */
    @RequestMapping(value = "/insertActivityDetail")
    @ResponseBody
    public MessageResponse insertActivityDetail(String jsons) throws Exception {
        WxUser wxUser = getCurWxUser();
        if(wxUser == null){
            return  new MessageResponse(ResultCode.FAIL,"没有获取到用户信息");
        }
        ActivityDetail obj = JSON.parseObject(jsons, ActivityDetail.class);
        return this.activityDetailService.insertActivityDetailW(obj, wxUser);
    }


}

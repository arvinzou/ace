package com.huacainfo.ace.society.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.HttpUtils;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.portal.service.WeChatApiService;
import com.huacainfo.ace.society.service.*;
import com.huacainfo.ace.society.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/10/19 10:26
 * @Description:
 */
@RestController
@RequestMapping("/www/user")
public class WUserController extends SocietyBaseController {
    @Autowired
    private AnalysisService analysisService;
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private PointsRecordService pointsRecordService;
    @Autowired
    private WeChatApiService weChatApiService;
    @Autowired
    private SocietyOrgInfoService societyOrgInfoService;
    @Autowired
    private FeedBackService feedBackService;

    /**
     * 提交意见反馈
     */
    @RequestMapping("/feedBack")
    public ResultResponse feedBack(String unionId, FeedBackVo params) throws Exception {
        //微信用户信息
        WxUser wxUser = getCurWxUser();
        unionId = StringUtil.isNotEmpty(unionId) ? unionId : (null == wxUser ? "" : wxUser.getUnionId());
        params.setUserId(unionId);
        try {
            return feedBackService.submit(params);
        } catch (CustomException e) {
            return new ResultResponse(ResultCode.FAIL, e.getMsg());
        }
    }

    /**
     * 新组织信息创建
     */
    @RequestMapping("/newOrgInfo")
    public ResultResponse newOrgInfo(String unionId, String jsonData) throws Exception {
        //微信用户信息
        WxUser wxUser = getCurWxUser();
        unionId = StringUtil.isNotEmpty(unionId) ? unionId : (null == wxUser ? "" : wxUser.getUnionId());
        //申请表单
        SocietyOrgInfoVo params = JsonUtil.toObject(jsonData, SocietyOrgInfoVo.class);

        try {
            return societyOrgInfoService.newOrgInfo(unionId, params);
        } catch (CustomException e) {
            return new ResultResponse(ResultCode.FAIL, e.getMsg());
        }
    }

    /**
     * 爱心币排行
     *
     * @param userType 1-个人 2-组织
     * @param rankType year-年度排行，month-月度排行 season-季度排行
     * @return List<Map<String, Object>>
     */
    @RequestMapping("/pointsRank")
    public ResultResponse pointsRank(String rankType, String userType, String unionId,
                                     String start, String limit) throws Exception {
        //微信用户信息
        WxUser wxUser = getCurWxUser();
        unionId = StringUtil.isNotEmpty(unionId) ? unionId : (null == wxUser ? "" : wxUser.getUnionId());
        //查询条件
        Map<String, Object> condition = new HashMap<>();
        if (StringUtil.isEmpty(start)) {
            condition.put("start", 0);
        }
        if (StringUtil.isEmpty(limit)) {
            condition.put("limit", 50);
        }
        userType = StringUtil.isEmpty(userType) ? "1" : userType;
        condition.put("userType", userType);

        Map<String, Object> rtn = analysisService.pointsRank(unionId, rankType, condition);

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rtn);
    }

    /***
     * 查询订单列表
     * @param condition 查询条件
     * @param page 分页工具
     * @return
     * @throws Exception
     */
    @RequestMapping("/findOrderList")
    public ResultResponse findOrderList(OrderInfoQVo condition, PageParamNoChangeSord page) throws Exception {
        WxUser wxUser = getCurWxUser();//小程序用户
        if (null == wxUser && StringUtil.isEmpty(condition.getUserId())) {
            return new ResultResponse(ResultCode.FAIL, "微信鉴权失败");
        }

        PageResult<OrderInfoVo> rst =
                orderInfoService.findOrderInfoList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rst);
    }

    /***
     * 查询 积分变动流水
     * @param condition 查询条件
     * @param page 分页工具
     * @return
     * @throws Exception
     */
    @RequestMapping("/findPointsRecord")
    public ResultResponse findPointsRecord(PointsRecordQVo condition, PageParamNoChangeSord page) throws Exception {
        WxUser wxUser = getCurWxUser();//小程序用户
        if (null == wxUser && StringUtil.isEmpty(condition.getUserId())) {
            return new ResultResponse(ResultCode.FAIL, "微信鉴权失败");
        }

        PageResult<PointsRecordVo> rst = pointsRecordService.findPointsRecordList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rst);
    }

    /***
     * 获取当前服务器时间
     */
    @RequestMapping("/getNowDateTime")
    public String getNowDateTime() throws Exception {
        return DateUtil.getNow();
    }

    @RequestMapping(value = "/wxSearch.do")
    @ResponseBody
    public ResultResponse search(String access_tocken, String json) throws Exception {
        String url = "https://api.weixin.qq.com/datacube/getweanalysisappidmonthlyvisittrend?access_token=" + access_tocken;
        String body = HttpUtils.httpPost(url, json);
        return new ResultResponse(ResultCode.SUCCESS, "成功", body);
    }

    @RequestMapping(value = "/synUserList")
    @ResponseBody
    public ResultResponse synUserList() throws Exception {
        ResultResponse rs = weChatApiService.synUserList("society");

        return rs;
    }

}

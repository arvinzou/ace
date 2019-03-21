package com.huacainfo.ace.taa.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.portal.service.AuthorityService;
import com.huacainfo.ace.taa.service.RoadDangerReportService;
import com.huacainfo.ace.taa.vo.CustomerVo;
import com.huacainfo.ace.taa.vo.RoadDangerReportQVo;
import com.huacainfo.ace.taa.vo.RoadDangerReportVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/www/roadDangerReport")
/**
 * @author: 何双
 * @version: 2019-03-15
 * @Description: TODO(路况上报)
 */
public class WRoadDangerReportController extends TaaBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoadDangerReportService roadDangerReportService;
    @Autowired
    private AuthorityService authorityService;

    /**
     * 获取路患列里 --小程序端展示
     *
     * @param condition 条件查询
     * @param page      分页条件
     * @return PageResult<RoadDangerReportQVo>
     * @throws Exception
     */
    @RequestMapping(value = "/findReportList")
    @ResponseBody
    public ResultResponse findReportList(String uid,
                                         RoadDangerReportQVo condition,
                                         PageParamNoChangeSord page) throws Exception {
        //微信鉴权信息 --小程序
        WxUser user = getCurWxUser();
        if (StringUtil.isNotEmpty(uid)) {
            user = new WxUser();
            user.setUnionId(uid);
            user.setNickName("test");
        }
        if (user == null) {
            return new ResultResponse(ResultCode.FAIL, "微信授权失败");
        }
        //同部门筛选条件
        UserProp u = parseUser(user);
        if (u == null) {
            return new ResultResponse(ResultCode.FAIL, "账户信息有误");
        }

        return roadDangerReportService.findViewList(parseUser(user), condition, page);
    }

    /**
     * 功能描述: 路患上报
     *
     * @param uid  用户id
     * @param data
     * @return
     * @throws Exception
     */
    @RequestMapping("/roadReport")
    @ResponseBody
    public MessageResponse roadReport(String uid, String data) throws Exception {

        if (StringUtil.isEmpty(data)) {
            return new MessageResponse(ResultCode.FAIL, "缺少必要参数");
        }
        RoadDangerReportQVo params = JsonUtil.toObject(data, RoadDangerReportQVo.class);

        //微信鉴权信息 --小程序
        WxUser user = getCurWxUser();
        if (StringUtil.isNotEmpty(uid)) {
            user = new WxUser();
            user.setUnionId(uid);
            user.setNickName("test");
        }
        if (user == null) {
            return new MessageResponse(ResultCode.FAIL, "微信授权失败");
        }
        return this.roadDangerReportService.insertRordDangerReport(params, parseUser(user));
    }


    /**
     * @throws
     * @Title:selectRordDangerReportByPrimaryKey
     * @Description: TODO(获取路况上报)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<RordDangerReport>
     * @author: 何双
     * @version: 2019-03-15
     */
    @RequestMapping(value = "/selectRoadDangerReportByPrimaryKey")
    @ResponseBody
    public SingleResult<RoadDangerReportVo> selectRordDangerReportByPrimaryKey(String uid, String reportId) throws Exception {

        //微信鉴权信息 --小程序
        WxUser user = getCurWxUser();
        if (StringUtil.isNotEmpty(uid)) {
            user = new WxUser();
            user.setUnionId(uid);
            user.setNickName("test");
        }
        if (user == null) {
            return new SingleResult(ResultCode.FAIL, "微信授权失败");
        }
        return this.roadDangerReportService.selectRordDangerReportByPrimaryKey(reportId);
    }


    /**
     * 功能描述: 路患撤销
     *
     * @param reportId
     * @return:
     * @auther:
     * @date: 2019/3/20
     */
    @RequestMapping("/repealReport")
    @ResponseBody
    public MessageResponse repealReport(String uid, String reportId) throws Exception {

        if (CommonUtils.isBlank(reportId)) {
            return new MessageResponse(ResultCode.FAIL, "路患主键" + "不能为空！");
        }
        //微信鉴权信息 --小程序
        WxUser user = getCurWxUser();
        if (StringUtil.isNotEmpty(uid)) {
            user = new WxUser();
            user.setUnionId(uid);
            user.setNickName("test");
        }
        if (user == null) {
            return new MessageResponse(ResultCode.FAIL, "微信授权失败");
        }

        return this.roadDangerReportService.deleteByPrimaryKey(reportId, parseUser(user));

    }

    /**
     * 功能描述: 获取用户权限
     *
     * @param uid 用户id
     * @return:
     * @auther:
     * @date: 2019/3/20
     */
    @RequestMapping("/findUserRole")
    @ResponseBody
    public List<Map<String, Object>> findUserRole(String uid) throws Exception {
        //微信鉴权信息 --小程序
        WxUser user = getCurWxUser();
        if (StringUtil.isNotEmpty(uid)) {
            user = new WxUser();
            user.setUnionId(uid);
            user.setNickName("test");
        }
        if (user == null) {
            //     return new MessageResponse(ResultCode.FAIL, "微信授权失败");
        }

        List<Map<String, Object>> roleList = roadDangerReportService.selectUserRole(uid);

        return roleList;
    }
}

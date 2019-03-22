package com.huacainfo.ace.taa.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.portal.service.AuthorityService;
import com.huacainfo.ace.taa.service.TraAccService;
import com.huacainfo.ace.taa.vo.TraAccQVo;
import com.huacainfo.ace.taa.vo.TraAccVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Arvin
 * @Date: 2019/1/12 10:51
 * @Description:
 */
@RestController
@RequestMapping("/www/traAcc")
public class WTraAccController extends TaaBaseController {

    @Autowired
    private TraAccService traAccService;

    @Autowired
    private AuthorityService authorityService;

    /**
     * 获取事故报表 --小程序端展示
     *
     * @param condition 条件查询
     * @param page      分页条件
     * @return PageResult<TraAccVo>
     * @throws Exception
     */
    @RequestMapping("/findList")
    public ResultResponse findList(String uid,
                                   TraAccQVo condition,
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

        return traAccService.findViewList(u, condition, page);
    }

    /**
     * 功能描述: 事故快报
     *
     * @param: json 上报参数
     * @return: ResultResponse
     * @auther: Arvin Zou
     * @date: 2019/1/12 10:57
     */
    @RequestMapping("/flashReport")
    public ResultResponse flashReport(String uid, String data) throws Exception {
        if (StringUtil.isEmpty(data)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        TraAccVo params = JsonUtil.toObject(data, TraAccVo.class);
        if (CommonUtils.isBlank(params.getLatitude())) {
            return new ResultResponse(ResultCode.FAIL, "纬度不能为空！");
        }
        if (CommonUtils.isBlank(params.getLongitude())) {
            return new ResultResponse(ResultCode.FAIL, "经度不能为空！");
        }
        if (CommonUtils.isBlank(params.getWeather())) {
            return new ResultResponse(ResultCode.FAIL, "天气不能为空！");
        }
        if (CommonUtils.isBlank(params.getAddress())) {
            return new ResultResponse(ResultCode.FAIL, "事故发生地点不能为空！");
        }
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

        return traAccService.flashReport(user, params);
    }

    /**
     * 功能描述: 事故续报
     *
     * @param uid  用户ID， 可选
     * @param data 续报参数
     * @return: ResultResponse
     * @auther: Arvin Zou
     * @date: 2019/1/12 11:15
     */
    @RequestMapping("/report")
    public ResultResponse report(String uid, String data) throws Exception {

        if (StringUtil.isEmpty(data)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        TraAccVo params = JsonUtil.toObject(data, TraAccVo.class);

        if (CommonUtils.isBlank(params.getId())) {
            return new ResultResponse(ResultCode.FAIL, "事故主键" + "不能为空！");
        }
        if (CommonUtils.isBlank(params.getAccidentTime())) {
            return new ResultResponse(ResultCode.FAIL, "事故发生时间" + "不能为空！");
        }
        if (CommonUtils.isBlank(params.getRoadSectionId())) {
            return new ResultResponse(ResultCode.FAIL, "归属路段" + "不能为空！");
        }


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

        return traAccService.report(user, params);
    }

    /**
     * 功能描述: 事故撤销
     *
     * @param traAccId
     * @return:
     * @auther: Arvin Zou
     * @date: 2019/1/12 11:32
     */
    @RequestMapping("/repealReport")
    public ResultResponse repealReport(String uid, String traAccId) throws Exception {

        SingleResult<UserProp> rst = this.authorityService.getCurUserPropByOpenId(this.getCurUserinfo().getUnionid());

        if (rst.getStatus() == 0) {
            UserProp userProp = rst.getValue();

        } else {
            return null;
        }
        if (CommonUtils.isBlank(traAccId)) {
            return new ResultResponse(ResultCode.FAIL, "事故主键" + "不能为空！");
        }
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

        MessageResponse ms = traAccService.updateStatus(traAccId, "0", parseUser(user));
        return new ResultResponse(ms);

    }

}

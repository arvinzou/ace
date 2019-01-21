package com.huacainfo.ace.taa.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.taa.service.TraAccService;
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

    /**
     * 功能描述: 事故快报
     *
     * @param: data 上报参数
     * @return: ResultResponse
     * @auther: Arvin Zou
     * @date: 2019/1/12 10:57
     */
    @RequestMapping("/flashReport")
    public ResultResponse flashReport(String uid, TraAccVo params) throws Exception {
        if (CommonUtils.isBlank(params.getLatitude())) {
            return new ResultResponse(ResultCode.FAIL, "纬度不能为空！");
        }
        if (CommonUtils.isBlank(params.getLongitude())) {
            return new ResultResponse(ResultCode.FAIL, "经度不能为空！");
        }
        if (CommonUtils.isBlank(params.getWeather())) {
            return new ResultResponse(ResultCode.FAIL, "天气不能为空！");
        }
        if (CommonUtils.isBlank(params.getVehicleType())) {
            return new ResultResponse(ResultCode.FAIL, "车型不能为空！");
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
     * @param uid    用户ID， 可选
     * @param params 续报参数
     * @return: ResultResponse
     * @auther: Arvin Zou
     * @date: 2019/1/12 11:15
     */
    @RequestMapping("/report")
    public ResultResponse report(String uid, TraAccVo params) throws Exception {
        if (CommonUtils.isBlank(params.getId())) {
            return new ResultResponse(ResultCode.FAIL, "事故主键" + "不能为空！");
        }
        if (CommonUtils.isBlank(params.getAccidentTime())) {
            return new ResultResponse(ResultCode.FAIL, "事故发生时间" + "不能为空！");
        }
        if (CommonUtils.isBlank(params.getRoadSectionId())) {
            return new ResultResponse(ResultCode.FAIL, "归属路段" + "不能为空！");
        }
        if (CommonUtils.isBlank(params.getCause())) {
            return new ResultResponse(ResultCode.FAIL, "事故原因" + "不能为空！");
        }
//        if (CommonUtils.isBlank(params.getDeadthToll())) {
//            return new ResultResponse(ResultCode.FAIL, "死亡人数" + "不能为空！");
//        }
//        if (CommonUtils.isBlank(params.getInjuries())) {
//            return new ResultResponse(ResultCode.FAIL, "受伤人数" + "不能为空！");
//        }
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

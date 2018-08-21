package com.huacainfo.ace.jxb.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.constant.ApiURL;
import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import com.huacainfo.ace.common.plugins.wechat.util.RandomValidateCode;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.plugins.wechat.util.WeChatPayUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.*;
import com.huacainfo.ace.jxb.constant.OrderPayType;
import com.huacainfo.ace.jxb.model.JxbCallBackLog;
import com.huacainfo.ace.jxb.service.BaseOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by HuaCai008 on 2018/6/15.
 */
@Controller
@RequestMapping("/www/wxpay")
public class WWxPayController extends JxbBaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseOrderService baseOrderService;

    /**
     * 统一下单
     * <p>
     * 官方接口地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
     *
     * @param openid 微信openid
     * @param fee    支付金额
     * @param body   商品描述
     * @param attach 附加数据 --  此处存放 cu_donate_order.id
     * @return
     */
    @ResponseBody
    @RequestMapping("/unifiedorder")
    public ResultResponse unifiedorder(String openid, String fee, String body, String attach) {
        if (CommonUtils.isEmpty(openid)) {
            //公众号用户信息
            Userinfo userinfo = getCurUserinfo();
            if (null == userinfo) {
                return new ResultResponse(ResultCode.FAIL, "微信授权失败");
            }
            openid = userinfo.getOpenid();
        }
        fee = fee.trim();
        ResultResponse checkRs = baseOrderService.checkAmount(attach, fee);//todo orderCheck(attach, fee);
        if (ResultCode.FAIL == checkRs.getStatus()) {
            return checkRs;
        }
        //取值
        String callBackUri = PropertyUtil.getProperty("pay_call_back_uri");
        String appid = PropertyUtil.getProperty("appid");
        String mchId = PropertyUtil.getProperty("mch_id");
        String apiKey = PropertyUtil.getProperty("api_key");
        String deviceInfo = "WEB";

        String nonceStr = RandomValidateCode.CreateRadom(32, 2);
        String outTradeNo = GUIDUtil.getGUID();
        String notifyUrl = callBackUri;
        String sign = WeChatPayUtil.getSign(appid, deviceInfo, mchId, apiKey,
                openid, fee, body, attach, nonceStr, outTradeNo, notifyUrl);
        String XML = WeChatPayUtil.initXML(appid, deviceInfo, mchId,
                openid, fee, sign, body, attach, nonceStr, outTradeNo, notifyUrl);
        String prepay_id = "";
        prepay_id = WeChatPayUtil.getPayNo(ApiURL.WX_PAY_PATH_API_URL, XML);
        if (CommonUtils.isEmpty(prepay_id)) {
            return new ResultResponse(ResultCode.FAIL, "统一下单请求失败");
        }
        //进行获取订单再次签名
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonceStr2 = RandomValidateCode.CreateRadom(32, 2);
        SortedMap<String, Object> parameters = new TreeMap<>();
        parameters.put("appId", appid);
        parameters.put("timeStamp", timeStamp);
        parameters.put("nonceStr", nonceStr2);
        parameters.put("package", "prepay_id=" + prepay_id);
        parameters.put("signType", "MD5");
        String paySign = WeChatPayUtil.createSign("MD5", "UTF-8", parameters, apiKey);
        parameters.put("paySign", paySign);

        return new ResultResponse(ResultCode.SUCCESS, "统一下单成功", parameters);
    }

    /**
     * 微信支付回调方法
     *
     * @param req
     * @param resp
     * @author bovine
     * Date  2016-04-18
     * @since v1.0
     */
    @RequestMapping(value = "/callback", produces = "text/xml;charset=UTF-8")
    public void weChatPayCallBack(HttpServletRequest req, HttpServletResponse resp) {
        logger.debug("jxb.WWWWxPayController.weChatPayCallBack.start");
        resp.setContentType("text/xml");
        resp.addHeader("Accept", "text/xml");
        String xml = HttpKit.readIncommingRequestData(req);
        logger.debug("jxb.WWWWxPayController.weChatPayCallBack.reqXml={}", xml);
        String rs = "<xml><return_code>SUCCESS</return_code><return_msg>OK</return_msg></xml>";
        if (StringUtil.isNotEmpty(xml)) {
            String json = XmlUtil.xmltoJson(xml);
            logger.debug("jxb.WWWWxPayController.weChatPayCallBack.json={}", json);
            JxbCallBackLog callBackLog = JsonUtil.toObject(json, JxbCallBackLog.class);
            try {
                int iCount = baseOrderService.insertCallBackLog(callBackLog);
                if (iCount <= 0) {
                    return;
                }
            } catch (Exception e) {
                logger.error("jxb.WWWWxPayController.wx_pay_log.error:{}", e);
            }
            try {
                //订单支付逻辑
                ResultResponse resultResponse = baseOrderService.pay(callBackLog, OrderPayType.WX_PAY);
                logger.info("jxb.Xml: {},\n  DealResult:{}", xml, resultResponse);
                resp.getWriter().write(rs);
            } catch (IOException e) {
                logger.error("jxb.WWWWxPayController.weChatPayCallBack.run error:{}", e);
            } catch (Exception e) {
                logger.error("jxb.WWWWxPayController.weChatPayCallBack/www/wxpay/callback.error => {}", e);
            }
        }
    }


    @RequestMapping(value = "/payBackTest")
    @ResponseBody
    public ResultResponse payBackTest(String xml) {

        return null;

//        String json = XmlUtil.xmltoJson(xml);
//        logger.debug("WWWWxPayController.weChatPayCallBack.json={}", json);
//        WxPayLog wxPayLog = JsonUtil.toObject(json, WxPayLog.class);
//        //订单支付逻辑
//        ResultResponse resultResponse = cuDonateOrderService.pay(wxPayLog, OrderConstant.PAY_TYPE_WX);
//        logger.info("Xml: {},\n  DealResult:{}", xml, resultResponse);
//
//
//        return resultResponse;
    }
}

package com.huacainfo.ace.cu.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.ccb.CCBApi;
import com.huacainfo.ace.common.plugins.ccb.pojo.CCBConfig;
import com.huacainfo.ace.common.plugins.wechat.constant.ApiURL;
import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import com.huacainfo.ace.common.plugins.wechat.util.RandomValidateCode;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.plugins.wechat.util.WeChatPayUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.*;
import com.huacainfo.ace.cu.common.constant.OrderConstant;
import com.huacainfo.ace.cu.model.CcbCallbackLog;
import com.huacainfo.ace.cu.model.WxPayLog;
import com.huacainfo.ace.cu.service.CuDonateOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by HuaCai008 on 2018/6/15.
 */
@RestController
@RequestMapping("/www/wxpay")
public class WWWWxPayController extends CuBaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private CuDonateOrderService cuDonateOrderService;

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
        ResultResponse checkRs = cuDonateOrderService.orderCheck(attach, fee);
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

        return new ResultResponse(ResultCode.SUCCESS, "统一下单成功", parameters);//JsonUtils.toJson(ResultUtil.getResult(parameters));
    }

    /**
     * 微信回调方法 -- 支付结果通知
     *
     * @param req
     * @param resp
     * @author bovine
     * Date  2016-04-18
     * @since v1.0
     */
    @RequestMapping(value = "/callback", produces = "text/xml;charset=UTF-8")
    public void weChatPayCallBack(HttpServletRequest req, HttpServletResponse resp) {
        logger.debug("WWWWxPayController.weChatPayCallBack.start");
        resp.setContentType("text/xml");
        resp.addHeader("Accept", "text/xml");
        String xml = HttpKit.readIncommingRequestData(req);
        logger.debug("WWWWxPayController.weChatPayCallBack.reqXml={}", xml);
        String rs = "<xml><return_code>SUCCESS</return_code><return_msg>OK</return_msg></xml>";
        if (StringUtil.isNotEmpty(xml)) {
            String json = XmlUtil.xmltoJson(xml);
            logger.debug("WWWWxPayController.weChatPayCallBack.json={}", json);
            WxPayLog wxPayLog = JsonUtil.toObject(json, WxPayLog.class);
            try {
                cuDonateOrderService.insertWxPayLog(wxPayLog);
            } catch (Exception e) {
                logger.error("WWWWxPayController.wx_pay_log.error:{}", e);
            }
            try {
                //通知支付成功
                if ("SUCCESS".equals(wxPayLog.getResult_code())) {
                    //订单支付逻辑
                    ResultResponse resultResponse =
                            cuDonateOrderService.pay(wxPayLog.getAttach(), OrderConstant.PAY_TYPE_WX, wxPayLog.getTotal_fee());
                    logger.info("Xml: {},\n  DealResult:{}", xml, resultResponse);
                } else {
                    logger.info("Xml: {},\n  DealResult:{}", xml, "微信回调结果[微信通知支付失败]");
                }

                resp.getWriter().write(rs);
            } catch (IOException e) {
                logger.error("WWWWxPayController.weChatPayCallBack.run error:{}", e);
            } catch (Exception e) {
                logger.error("WWWWxPayController.weChatPayCallBack/wechat/pay/callback.pay.error => {}", e);
            }
        }
    }

    /**
     * 建行聚合支付 -- 返回支付地址
     * 客户号:     105000083980372
     * 手机号码：   18670619000（张姣）
     * 证书号：MC1093460
     * 登录密码：7251211
     * 支付密码：20180905
     *
     * @return String
     */
    @RequestMapping(value = "/ccbPay")
    public ResultResponse ccbPay(String payAmount, String orderId) {
        if (!StringUtil.areNotEmpty(payAmount, orderId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        ResultResponse checkRs = cuDonateOrderService.orderCheck(orderId, payAmount);
        if (ResultCode.FAIL == checkRs.getStatus()) {
            return checkRs;
        }
        //返回支付链接
        CCBConfig config = new CCBConfig("105000083980372", "023328365",
                "430000000", "d477951e6c46c575320042ad020111");
        String payURL = CCBApi.pay(payAmount, orderId, config);

        return new ResultResponse(ResultCode.SUCCESS, "获取支付链接成功", payURL);
    }

    /**
     * 建行支付结果通知
     *
     * @return String
     */
    @RequestMapping(value = "/ccbCallBack")
    public ResultResponse ccbCallBack(CcbCallbackLog params) {
        logger.debug("[慈善总会]ccbCallBack.info:{}", params.toString());

        return cuDonateOrderService.ccbCallBack(params);
    }

}

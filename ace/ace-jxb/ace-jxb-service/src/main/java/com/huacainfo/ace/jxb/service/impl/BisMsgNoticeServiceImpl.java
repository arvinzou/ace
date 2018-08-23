package com.huacainfo.ace.jxb.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.jxb.constant.OrderCategory;
import com.huacainfo.ace.jxb.model.TeacherAudit;
import com.huacainfo.ace.jxb.service.BaseOrderService;
import com.huacainfo.ace.jxb.service.BisMsgNoticeService;
import com.huacainfo.ace.jxb.service.StudioService;
import com.huacainfo.ace.jxb.vo.BaseOrderVo;
import com.huacainfo.ace.jxb.vo.StudioVo;
import com.huacainfo.ace.portal.service.MessageTemplateService;
import com.huacainfo.ace.portal.service.UserinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/8/21 11:44
 * @Description:
 */
@Service("bisMsgNoticeService")
public class BisMsgNoticeServiceImpl implements BisMsgNoticeService {
    //咨询订单付款成功通知 - 咨询师
    public static final String CONSULT_ORDER_PAY_SUCCESS_COUNSELOR = "CONSULT-ORDER-PAY-SUCCESS-COUNSELOR";
    //咨询订单付款成功通知 - 微信用户
    public static final String CONSULT_ORDER_PAY_SUCCESS_USER = "CONSULT-ORDER-PAY-SUCCESS-USER";
    //咨询师入驻审核通知
    public static final String COUNSELOR_REGISTER_AUDIT = "COUNSELOR-REGISTER-AUDIT";
    //工作室审核通知
    public static final String STUDIO_AUDIT = "STUDIO-AUDIT";
    //工作室新增成员通知
    public static final String STUDIO_NEW_MEMBER = "STUDIO-NEW-MEMBER";

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MessageTemplateService messageTemplateService;
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private BaseOrderService baseOrderService;
    @Autowired
    private StudioService studioService;

    /**
     * 付款成功消息
     *
     * @param orderId 订单id
     */
    @Override
    public ResultResponse paySuccess(String orderId) throws Exception {
        BaseOrderVo orderVo = baseOrderService.selectBaseOrderByPrimaryKey(orderId).getValue();
        if (null == orderVo) {
            return new ResultResponse(ResultCode.FAIL, "付款成功消息-推送失败-订单数据不存在 [{}]", orderId);
        }
        //咨询订单付款完成消息
        if (OrderCategory.CATEGORY_CONSULT.equals(orderVo.getCategory())) {
            return paySuccessConsultOrder(orderVo);
        }

        return new ResultResponse(ResultCode.FAIL, "付款成功消息-推送失败-订单类型不支持 [{}]", orderId);
    }

    /**
     * 咨询师审核消息
     *
     * @param record 审核结果
     * @return ResultResponse
     */
    @Override
    public ResultResponse counselorAuditMsg(TeacherAudit record) throws Exception {
        Userinfo userinfo = userinfoService.selectUserinfoByPrimaryKey(record.getCounselorId()).getValue();
        if (null == userinfo) {
            return new ResultResponse(ResultCode.FAIL, "咨询师微信资料丢失");
        }
        boolean isPass = "1".equals(record.getRst());
        Map<String, Object> params = new HashMap<>();
        params.put("openid", userinfo.getOpenid());
        params.put("auditResult", isPass ? "您的加入申请已通过！" : "您的加入申请未通过！");
        params.put("auditDate", DateUtil.getNow());
        params.put("description", StringUtil.isEmpty(record.getStatement()) ? "无" : record.getStatement());
        params.put("remark", isPass ? "您已经可以开始使用平台！" : "可向邮箱jinxinbang123@163.com 发送邮件提出异议！");

        return messageTemplateService.send("jxb", COUNSELOR_REGISTER_AUDIT, params);
    }

    /**
     * 工作室审核消息发放
     */
    @Override
    public ResultResponse studioAuditMsg(StudioVo studioVo, String auditRs) throws Exception {
        Userinfo userinfo = userinfoService.selectUserinfoByPrimaryKey(studioVo.getCounselorId()).getValue();
        if (null == userinfo) {
            return new ResultResponse(ResultCode.FAIL, "咨询师微信资料丢失");
        }
        boolean isPass = "1".equals(auditRs);
        Map<String, Object> params = new HashMap<>();
        params.put("openid", userinfo.getOpenid());
        params.put("auditResult", isPass ? "您的加入申请已通过！" : "您的加入申请未通过！");
        params.put("auditDate", DateUtil.getNow());
        params.put("description", "无");
        params.put("remark", isPass ? "您已经可以开始发展工作室！" : "可向邮箱jinxinbang123@163.com 发送邮件提出异议！");

        return messageTemplateService.send("jxb", STUDIO_NEW_MEMBER, params);
    }

    /**
     * 工作室新成员加入提醒
     *
     * @param newOne   新成员信息
     * @param studioId 工作室
     * @return ResultResponse
     */
    @Override
    public ResultResponse memberJoinMsg(Userinfo newOne, String studioId) throws Exception {
        Map<String, Object> referee = studioService.findUserInfoByStudioId(studioId);
        if (null == referee || null == referee.get("openid")) {
            return new ResultResponse(ResultCode.FAIL, "工作室责任人微信信息缺失");
        }
        String openId = String.valueOf(referee.get("openid"));

        Map<String, Object> params = new HashMap<>();
        params.put("openid", openId);
        params.put("nickName", StringUtil.isEmpty(newOne.getNickname()) ? "未知姓名" : newOne.getNickname());
        params.put("joinDate", DateUtil.getNow());

        return messageTemplateService.send("jxb", STUDIO_AUDIT, params);
    }

    /**
     * 咨询订单-付款成功消息
     */
    private ResultResponse paySuccessConsultOrder(BaseOrderVo orderVo) throws Exception {
        String tmplCode = "";
        Map<String, Object> params = new HashMap<>();
        //发送给购买客户
        Userinfo consumer = userinfoService.selectUserinfoByKey(orderVo.getConsumerId());
        if (null != consumer) {
            tmplCode = CONSULT_ORDER_PAY_SUCCESS_USER;
            params.put("openid", consumer.getOpenid());
            params.put("counselorName", orderVo.getBusinessName());
            params.put("consultType", formatConsultType(orderVo.getConsultProduct().getType()));
            params.put("payMoney", orderVo.getPayMoney());
            params.put("consultContent", orderVo.getConsultOrder().getInfo());
            params.put("url", "www.baidu.com");//点击查看详情
            ResultResponse rs1 = messageTemplateService.send("jxb", tmplCode, params);
            logger.debug("付款成功消息-消息推送结果：{}", rs1.toString());
        }
        //发送给受理咨询师
        Userinfo counselor = userinfoService.selectUserinfoByKey(orderVo.getBusinessId());
        if (null != counselor) {
            tmplCode = CONSULT_ORDER_PAY_SUCCESS_COUNSELOR;
            params.put("openid", counselor.getOpenid());
            params.put("reserveDate", orderVo.getConsultOrder().getReserveDate());
            params.put("userName", orderVo.getConsumerName());
            params.put("payMoney", orderVo.getPayMoney());
            params.put("url", "www.baidu.com");//点击查看详情
            ResultResponse rs2 = messageTemplateService.send("jxb", tmplCode, params);
            logger.debug("付款成功消息-消息推送结果：{}", rs2.toString());
        }

        return new ResultResponse(ResultCode.SUCCESS, "消息发送完成");
    }

    private String formatConsultType(String type) {
        switch (type) {
            case "1":
                return "语音咨询";
            case "2":
                return "视频咨询";
            case "3":
                return "面对面咨询";
            default:
                return "";
        }
    }
}

package com.huacainfo.ace.jxb.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.jxb.constant.OrderCategory;
import com.huacainfo.ace.jxb.model.ConsultOrder;
import com.huacainfo.ace.jxb.model.Counselor;
import com.huacainfo.ace.jxb.model.OrderComplaint;
import com.huacainfo.ace.jxb.model.TeacherAudit;
import com.huacainfo.ace.jxb.service.BaseOrderService;
import com.huacainfo.ace.jxb.service.BisMsgNoticeService;
import com.huacainfo.ace.jxb.service.StudioService;
import com.huacainfo.ace.jxb.vo.BaseOrderVo;
import com.huacainfo.ace.jxb.vo.StudioVo;
import com.huacainfo.ace.portal.service.MessageTemplateService;
import com.huacainfo.ace.portal.service.UserinfoService;
import com.huacainfo.ace.portal.vo.UserinfoVo;
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
    //审核结果通知
    public static final String AUDIT_RESULT = "AUDIT-RESULT";

    //订单投诉-通知自身
    public static final String ORDER_COMPLAINT_ONESELF = "ORDER-COMPLAINT-ONESELF";
    //订单投诉-通知咨询师
    public static final String ORDER_COMPLAINT_COUNSELOR = "ORDER-COMPLAINT-COUNSELOR";
    //订单投诉-通知管理员
    public static final String ORDER_COMPLAINT_ADMIN = "ORDER-COMPLAINT-ADMIN";
    //咨询订单再次提醒
    public static final String CONSULT_ORDER_REMIND = "CONSULT-ORDER-REMIND";


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
        String tmplCode;
        Map<String, Object> params = new HashMap<>();
        if (isPass) {
            tmplCode = COUNSELOR_REGISTER_AUDIT;
            params.put("auditResult", "已通过");
            params.put("auditDate", DateUtil.getNow());
            params.put("description", StringUtil.isEmpty(record.getStatement()) ? "无" : record.getStatement());
        } else {
            tmplCode = AUDIT_RESULT;
            params.put("auditContent", "咨询师入驻");
            params.put("auditResult", "已拒绝");
        }
        params.put("openid", userinfo.getOpenid());
        params.put("remark", isPass ? "您已经可以开始使用平台！" : "可向邮箱jinxinbang123@163.com 发送邮件提出异议！");
        params.put("first", isPass ? "您的加入申请已通过！" : "您的加入申请未通过！");

        return messageTemplateService.send("jxb", tmplCode, params);
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
        String tmplCode;
        Map<String, Object> params = new HashMap<>();
//        if (isPass) {
//            tmplCode = STUDIO_AUDIT;
//            params.put("auditResult", "已通过");
//            params.put("auditDate", DateUtil.getNow());
//            params.put("description", "无");
//        } else {
        tmplCode = AUDIT_RESULT;
        params.put("auditContent", "工作室申请");
        params.put("auditResult", isPass ? "已通过" : "未通过");
//        }
        params.put("openid", userinfo.getOpenid());
        params.put("remark", isPass ? "您已经可以开始发展工作室！" : "可向邮箱jinxinbang123@163.com 发送邮件提出异议！");
        params.put("first", isPass ? "您的加入申请已通过！" : "您的加入申请未通过！");


        return messageTemplateService.send("jxb", tmplCode, params);
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

        return messageTemplateService.send("jxb", STUDIO_NEW_MEMBER, params);
    }

    /**
     * * 提交投诉-消息通知
     */
    @Override
    public ResultResponse sendOrderComplaintMsg(OrderComplaint complaint) throws Exception {
        BaseOrderVo orderVo = baseOrderService.selectBaseOrderByPrimaryKey(complaint.getOrderId()).getValue();
        if (null == orderVo) {
            return new ResultResponse(ResultCode.FAIL, "订单信息丢失");
        }
        String submitDate = DateUtil.toStr(complaint.getCreateDate(), DateUtil.DEFAULT_DATE_TIME_REGEX);
        Map<String, Object> params = new HashMap<>();
        ResultResponse msg;
        //1、推送至本人 =========================================
        UserinfoVo oneself = userinfoService.selectUserinfoByPrimaryKey(complaint.getUserId()).getValue();
        if (oneself == null) {
            return new ResultResponse(ResultCode.FAIL, "本人微信资料丢失");
        }
        params.put("openid", oneself.getOpenid());
        params.put("submitDate", submitDate);
        params.put("content", complaint.getContent());
        msg = messageTemplateService.send("jxb", ORDER_COMPLAINT_ONESELF, params);
        logger.debug("[" + orderVo.getId() + "]提交投诉-消息通知结果[本人]: {} ", msg.toString());
        //2、推送给咨询师 =========================================
        params.clear();
        Counselor c = orderVo.getCounselor();
        ConsultOrder co = orderVo.getConsultOrder();
        UserinfoVo counselor = userinfoService.selectUserinfoByPrimaryKey(c.getId()).getValue();
        if (counselor == null) {
            return new ResultResponse(ResultCode.FAIL, "咨询师微信资料丢失");
        }
        params.put("openid", counselor.getOpenid());
        params.put("submitDate", submitDate);
        params.put("parentName", co.getName());
        params.put("orderId", orderVo.getId());
        params.put("parentMobile", co.getTel());
        params.put("content", complaint.getContent());
        msg = messageTemplateService.send("jxb", ORDER_COMPLAINT_COUNSELOR, params);
        logger.debug("[" + orderVo.getId() + "]提交投诉-消息通知结果[咨询师]: {}", msg.toString());
        //3、推送给平台管理者 =========================================
        params.clear();
        params.put("openid", "ogxN71uKkUJu-JOIhWcMyKKE3Mo4");//默认管理员 - 哩大人
        params.put("parentName", co.getName());
        params.put("parentMobile", co.getTel());
        params.put("counselor", c.getName());
        params.put("orderId", orderVo.getId());
        params.put("content", complaint.getContent());
        msg = messageTemplateService.send("jxb", ORDER_COMPLAINT_ADMIN, params);
        logger.debug("[" + orderVo.getId() + "]提交投诉-消息通知结果[平台管理者]: {}", msg.toString());

        //=========================================
        return new ResultResponse(ResultCode.SUCCESS, "消息推送完成");
    }

    /**
     * 咨询订单再次提醒消息
     *
     * @param orderVo 订单vo
     * @return 发送结果
     */
    @Override
    public ResultResponse consultOrderRemind(BaseOrderVo orderVo) throws Exception {
        String detailURL = "http://zx.huacainfo.com/jxb/www/view/consultantDetail/index.jsp?id=" + orderVo.getId();
        Map<String, Object> params = new HashMap<>();
        //发送给受理咨询师
        Userinfo counselor = userinfoService.selectUserinfoByKey(orderVo.getBusinessId());
        if (null != counselor) {
            params.put("openid", counselor.getOpenid());
            params.put("reserveDate",
                    DateUtil.toStr(orderVo.getConsultOrder().getReserveDate(), DateUtil.DEFAULT_DATE_TIME_REGEX));
            params.put("userName", orderVo.getConsultOrder().getName());
            params.put("payMoney", orderVo.getPayMoney());
            params.put("url", detailURL);//点击查看详情
            ResultResponse rs2 = messageTemplateService.send("jxb", CONSULT_ORDER_REMIND, params);
            logger.debug("再次提醒消息-[咨询师]消息推送结果：{}", rs2.toString());
            return rs2;
        }

        return new ResultResponse(ResultCode.FAIL, "咨询师微信资料不全");
    }

    /**
     * 咨询订单-付款成功消息
     */
    private ResultResponse paySuccessConsultOrder(BaseOrderVo orderVo) throws Exception {
        String tmplCode = "";
        Map<String, Object> params = new HashMap<>();
        String detailURL = "http://zx.huacainfo.com/jxb/www/view/consultantDetail/index.jsp?id=" + orderVo.getId();
        //发送给购买客户
        Userinfo consumer = userinfoService.selectUserinfoByKey(orderVo.getConsumerId());
        if (null != consumer) {
            tmplCode = CONSULT_ORDER_PAY_SUCCESS_USER;
            params.put("openid", consumer.getOpenid());
            params.put("counselorName", orderVo.getBusinessName());
            params.put("consultType", formatConsultType(orderVo.getConsultProduct().getType()));
            params.put("payMoney", orderVo.getPayMoney());
            params.put("consultContent", orderVo.getConsultOrder().getInfo());
            params.put("url", detailURL);//点击查看详情
            ResultResponse rs1 = messageTemplateService.send("jxb", tmplCode, params);
            logger.debug("付款成功消息-[客户]消息推送结果：{}", rs1.toString());
        }
        //发送给受理咨询师
        Userinfo counselor = userinfoService.selectUserinfoByKey(orderVo.getBusinessId());
        if (null != counselor) {
            tmplCode = CONSULT_ORDER_PAY_SUCCESS_COUNSELOR;
            params.put("openid", counselor.getOpenid());
            params.put("reserveDate", DateUtil.toStr(orderVo.getConsultOrder().getReserveDate(), DateUtil.DEFAULT_DATE_TIME_REGEX));
            params.put("userName", orderVo.getConsultOrder().getName());
            params.put("payMoney", orderVo.getPayMoney());
            params.put("url", detailURL);//点击查看详情
            ResultResponse rs2 = messageTemplateService.send("jxb", tmplCode, params);
            logger.debug("付款成功消息-[咨询师]消息推送结果：{}", rs2.toString());
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


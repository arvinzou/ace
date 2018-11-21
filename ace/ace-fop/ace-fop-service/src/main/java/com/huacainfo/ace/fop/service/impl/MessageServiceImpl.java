package com.huacainfo.ace.fop.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.fop.common.constant.AuditResult;
import com.huacainfo.ace.fop.common.constant.FopConstant;
import com.huacainfo.ace.fop.common.constant.KafkaConstant;
import com.huacainfo.ace.fop.common.constant.MsgTmplCode;
import com.huacainfo.ace.fop.model.*;
import com.huacainfo.ace.fop.service.MessageService;
import com.huacainfo.ace.fop.service.SysAccountService;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.MessageTemplateService;
import com.huacainfo.ace.portal.service.TaskCmccService;
import com.huacainfo.ace.portal.service.UserinfoService;
import com.huacainfo.ace.portal.service.UsersService;
import com.huacainfo.ace.portal.vo.UserinfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/7/2 10:24
 * @Description:
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskCmccService taskCmccService;
    @Autowired
    private SysAccountService sysAccountService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private MessageTemplateService messageTemplateService;

    /**
     * 注册结果通知
     *
     * @param result true - 注册成功，false-注册失败
     * @param mobile
     * @param params
     * @return
     */
    @Override
    public ResultResponse registerMessage(boolean result, String mobile, Map<String, Object> params)
            throws Exception {
        if (result) {

            Map<String, Object> msg = new HashMap<>();
            msg.put("taskName", "注册成功通知：" + mobile);
            msg.put("msg", params.get("name") + "，您已成功注册工商联服务平台，" +
                    "登录帐号：" + mobile + "，" +
                    "初始密码：" + params.get("password") + "，" +
                    "您在可登录后修改密码。进入个人中心，完善资料通过审核后，成为工商联会员。【常德市工商联】");
            msg.put("tel", mobile + "," + mobile);
            TaskCmcc o = new TaskCmcc();
            CommonBeanUtils.copyMap2Bean(o, msg);

            return new ResultResponse(taskCmccService.insertTaskCmcc(o));
        }
        return new ResultResponse(ResultCode.SUCCESS, "发送完成");
    }

    /**
     * 政企服务消息反馈
     *
     * @param fopGeHelp
     * @param auditResult
     * @param auditOpinion
     * @return
     */
    @Override
    public ResultResponse geHelpAuditMessage(FopGeHelp fopGeHelp, String auditResult, String auditOpinion) throws Exception {
        Map<String, Object> accountInfo =
                sysAccountService.getAccountInfo(fopGeHelp.getRequestId(), fopGeHelp.getRequestType());
        Users users = usersService.selectByAccount((String) accountInfo.get("account"));
        if (null == users) {
            return new ResultResponse(ResultCode.FAIL, "未找到相关绑定关系");
        }
        //此处逻辑绑定为unionid
        UserinfoVo uVo = userinfoService.selectUserinfoByPrimaryKey(users.getOpenId()).getValue();
        if (null == uVo) {
            return new ResultResponse(ResultCode.FAIL, "微信用户信息不存在");
        }

        String openid = uVo.getOpenid();
        String tmplCode = MsgTmplCode.BIS_CONFIRM_NOTICE;
        Map<String, Object> params = new HashMap<>();
        //kafka所需内容
        params.put("service", "messageTemplateService");
        params.put("sysId", "fop");
        params.put("tmplCode", tmplCode);
        //发送消息内容
        params.put("openid", openid);
//        params.put("url", "www.baidu.com");
//        params.put("first", "哈哈哈哈哈");
        //data
        params.put("name", accountInfo.get("name"));
        params.put("title", fopGeHelp.getTitle());// "政企服务审核");
        String rs = AuditResult.PASS.equals(auditResult) ? "已通过" : "被驳回";
        params.put("content", "政企服务审核\n" +
                "审核结果：" + rs + "\n" +
                "审核意见：" + (CommonUtils.isEmpty(auditOpinion) ? "" : auditOpinion)
        );
        //kafka消息推送
        kafkaProducerService.sendMsg(KafkaConstant.TOPIC_NAME, params);
//        ResultResponse rs1 = messageTemplateService.send("fop", tmplCode, params);
//        logger.debug("*****************geHelpAuditMessage:\n{}", rs1);

        return new ResultResponse(ResultCode.SUCCESS, "消息发送成功");
    }

    /**
     * 银企服务消息反馈
     */
    @Override
    public ResultResponse financeHelpAuditMessage(FopFinanceProject obj, String auditResult, String auditOpinion)
            throws Exception {
        Map<String, Object> accountInfo = sysAccountService.getAccountInfo(obj.getCompanyId(), FopConstant.COMPANY);
        Users users = usersService.selectByAccount((String) accountInfo.get("account"));
        if (null == users) {
            return new ResultResponse(ResultCode.FAIL, "未找到相关绑定关系");
        }
        //此处逻辑绑定为unionid
        UserinfoVo uVo = userinfoService.selectUserinfoByPrimaryKey(users.getOpenId()).getValue();
        if (null == uVo) {
            return new ResultResponse(ResultCode.FAIL, "微信用户信息不存在");
        }

        String openid = uVo.getOpenid();
        String tmplCode = MsgTmplCode.BIS_CONFIRM_NOTICE;
        Map<String, Object> params = new HashMap<>();
        //kafka所需内容
        params.put("service", "messageTemplateService");
        params.put("sysId", "fop");
        params.put("tmplCode", tmplCode);
        //发送消息内容
        params.put("openid", openid);
//        params.put("url", "www.baidu.com");
//        params.put("first", "哈哈哈哈哈");
        //data
        params.put("name", accountInfo.get("name"));
        params.put("title", obj.getFinanceTitle());
        String rs = AuditResult.PASS.equals(auditResult) ? "已通过" : "被驳回";
        params.put("content", "银企服务 - 审核\n" +
                "审核结果：" + rs + "\n" +
                "审核意见：" + (CommonUtils.isEmpty(auditOpinion) ? "" : auditOpinion));
        //kafka消息推送
        kafkaProducerService.sendMsg(KafkaConstant.TOPIC_NAME, params);

        return new ResultResponse(ResultCode.SUCCESS, "消息发送成功");
    }

    /**
     * 法律诉求消息反馈
     */
    @Override
    public ResultResponse lawQuestionAuditMessage(FopQuestion obj,
                                                  String auditResult, String auditOpinion) throws Exception {
        Map<String, Object> accountInfo = sysAccountService.getAccountInfo(obj.getRelationId(), obj.getRelationType());
        Users users = usersService.selectByAccount((String) accountInfo.get("account"));
        if (null == users) {
            return new ResultResponse(ResultCode.FAIL, "未找到相关绑定关系");
        }
        //此处逻辑绑定为unionid
        UserinfoVo uVo = userinfoService.selectUserinfoByPrimaryKey(users.getOpenId()).getValue();
        if (null == uVo) {
            return new ResultResponse(ResultCode.FAIL, "微信用户信息不存在");
        }

        String openid = uVo.getOpenid();
        String tmplCode = MsgTmplCode.BIS_CONFIRM_NOTICE;
        Map<String, Object> params = new HashMap<>();
        //kafka所需内容
        params.put("service", "messageTemplateService");
        params.put("sysId", "fop");
        params.put("tmplCode", tmplCode);
        //发送消息内容
        params.put("openid", openid);
//        params.put("url", "www.baidu.com");
//        params.put("first", "哈哈哈哈哈");
        //data
        params.put("name", accountInfo.get("name"));
        params.put("title", obj.getTitle());
        String rs = AuditResult.PASS.equals(auditResult) ? "已通过" : "被驳回";
        params.put("content", "法律诉求审核\n" +
                "审核结果：" + rs + "\n" +
                "审核意见：" + (CommonUtils.isEmpty(auditOpinion) ? "" : auditOpinion));

        //kafka消息推送
        kafkaProducerService.sendMsg(KafkaConstant.TOPIC_NAME, params);

        return new ResultResponse(ResultCode.SUCCESS, "消息发送成功");
    }

    /**
     * 项目信息审核消息反馈
     */
    @Override
    public ResultResponse projectAuditMessage(FopProject fopProject,
                                              String auditResult, String auditOpinion) throws Exception {
        Map<String, Object> accountInfo =
                sysAccountService.getAccountInfo(fopProject.getRelationId(), fopProject.getRelationType());
        Users users = usersService.selectByAccount((String) accountInfo.get("account"));
        if (null == users) {
            return new ResultResponse(ResultCode.FAIL, "未找到相关绑定关系");
        }
        //此处逻辑绑定为unionid
        UserinfoVo uVo = userinfoService.selectUserinfoByPrimaryKey(users.getOpenId()).getValue();
        if (null == uVo) {
            return new ResultResponse(ResultCode.FAIL, "微信用户信息不存在");
        }

        String openid = uVo.getOpenid();
        String tmplCode = MsgTmplCode.BIS_CONFIRM_NOTICE;
        Map<String, Object> params = new HashMap<>();
        //kafka所需内容
        params.put("service", "messageTemplateService");
        params.put("sysId", "fop");
        params.put("tmplCode", tmplCode);
        //发送消息内容
        params.put("openid", openid);
//        params.put("url", "www.baidu.com");
//        params.put("first", "哈哈哈哈哈");
        //data
        params.put("name", accountInfo.get("name"));
        params.put("title", fopProject.getProjectName());
        String rs = AuditResult.PASS.equals(auditResult) ? "已通过" : "被驳回";
        params.put("content", "项目信息审核\n" +
                "审核结果：" + rs + "\n" +
                "审核意见：" + (CommonUtils.isEmpty(auditOpinion) ? "" : auditOpinion));
        //kafka消息推送
        kafkaProducerService.sendMsg(KafkaConstant.TOPIC_NAME, params);


        return new ResultResponse(ResultCode.SUCCESS, "消息发送成功");
    }

    /**
     * 信息服务审核消息反馈
     */
    @Override
    public ResultResponse informationAuditMessage(InformationService info, String moduleName,
                                                  String auditResult, String auditOpinion) throws Exception {
        Map<String, Object> accountInfo =
                sysAccountService.getAccountInfo(info.getRelationId(), info.getRelationType());
        Users users = usersService.selectByAccount((String) accountInfo.get("account"));
        if (null == users) {
            return new ResultResponse(ResultCode.FAIL, "未找到相关绑定关系");
        }
        //此处逻辑绑定为unionid
        UserinfoVo uVo = userinfoService.selectUserinfoByPrimaryKey(users.getOpenId()).getValue();
        if (null == uVo) {
            return new ResultResponse(ResultCode.FAIL, "微信用户信息不存在");
        }

        String openid = uVo.getOpenid();
        String tmplCode = MsgTmplCode.BIS_CONFIRM_NOTICE;
        Map<String, Object> params = new HashMap<>();
        //kafka所需内容
        params.put("service", "messageTemplateService");
        params.put("sysId", "fop");
        params.put("tmplCode", tmplCode);
        //发送消息内容
        params.put("openid", openid);
//        params.put("url", "www.baidu.com");
//        params.put("first", "哈哈哈哈哈");
        //data
        params.put("name", accountInfo.get("name"));
        params.put("title", info.getTitle());
        String rs = AuditResult.PASS.equals(auditResult) ? "已通过" : "被驳回";
        params.put("content", moduleName + "\n" +
                "审核结果：" + rs + "\n" +
                "审核意见：" + (CommonUtils.isEmpty(auditOpinion) ? "" : auditOpinion));
        //kafka消息推送
        kafkaProducerService.sendMsg(KafkaConstant.TOPIC_NAME, params);


        return new ResultResponse(ResultCode.SUCCESS, "消息发送成功");
    }
}

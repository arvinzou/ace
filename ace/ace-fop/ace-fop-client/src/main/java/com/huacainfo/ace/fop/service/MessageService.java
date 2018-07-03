package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.fop.model.*;

import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/7/2 10:21
 * @Description:
 */
public interface MessageService {
    /**
     * 注册结果通知
     *
     * @return
     */
    ResultResponse registerMessage(boolean result, String mobile, Map<String, Object> params) throws Exception;

    /**
     * 政企服务消息反馈
     */
    ResultResponse geHelpAuditMessage(FopGeHelp fopGeHelp, String auditResult, String auditOpinion) throws Exception;

    /**
     * 银企服务消息反馈
     */
    ResultResponse financeHelpAuditMessage(FopFinanceProject obj, String auditResult, String auditOpinion) throws Exception;

    /**
     * 法律诉求消息反馈
     */
    ResultResponse lawQuestionAuditMessage(FopQuestion obj, String auditResult, String auditOpinion) throws Exception;

    /**
     * 项目信息审核消息反馈
     */
    ResultResponse projectAuditMessage(FopProject fopProject, String auditResult, String auditOpinion) throws Exception;

    /**
     * 信息服务审核消息反馈
     */
    ResultResponse informationAuditMessage(InformationService info, String moduleName, String auditResult, String auditOpinion) throws Exception;
}

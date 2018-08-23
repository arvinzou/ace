package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.jxb.model.TeacherAudit;
import com.huacainfo.ace.jxb.vo.StudioVo;

/**
 * @Auther: Arvin
 * @Date: 2018/8/21 11:32
 * @Description:
 */
public interface BisMsgNoticeService {
    /**
     * 付款成功消息
     */
    ResultResponse paySuccess(String orderId) throws Exception;

    /**
     * 咨询师审核消息
     *
     * @param record 审核结果
     * @return ResultResponse
     */
    ResultResponse counselorAuditMsg(TeacherAudit record) throws Exception;

    /**
     * 工作室审核消息发放
     */
    ResultResponse studioAuditMsg(StudioVo studioVo, String auditRs) throws Exception;

    /**
     * 工作室新成员加入提醒
     *
     * @param newOne   新成员信息
     * @param studioId 工作室
     * @return ResultResponse
     */
    ResultResponse memberJoinMsg(Userinfo newOne, String studioId) throws Exception;
}

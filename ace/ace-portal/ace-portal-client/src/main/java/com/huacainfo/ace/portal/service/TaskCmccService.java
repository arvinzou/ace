package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.vo.TaskCmccQVo;
import com.huacainfo.ace.portal.vo.TaskCmccVo;

public interface TaskCmccService {
    public abstract PageResult<TaskCmccVo> findTaskCmccList(TaskCmccQVo condition, int start, int limit, String orderBy) throws Exception;

    public abstract MessageResponse insertTaskCmcc(TaskCmcc o, UserProp userProp) throws Exception;

    public abstract MessageResponse insertTaskCmcc(TaskCmcc o) throws Exception;

    public abstract MessageResponse updateTaskCmcc(TaskCmcc o, UserProp userProp) throws Exception;

    public abstract MessageResponse deleteTaskCmccByTaskCmccId(String id, UserProp userProp) throws Exception;

    public abstract void queueTask() throws Exception;

    public abstract void queueTaskDetail() throws Exception;

    public abstract MessageResponse updateTaskStatusCmccByTaskCmccId(String id,
                                                                     UserProp userProp) throws Exception;

    public abstract void workFlowMsgTask() throws Exception;

    public abstract SingleResult<TaskCmcc> selectBYId(String id) throws Exception;

    /**
     * 添加短信发送任务
     *
     * @param taskName  任务名称
     * @param message   发送给内容
     * @param mobileStr 目标号码 ：nickName1,mobile1;nickName2,mobile2;
     * @return 发送结果
     */
    MessageResponse addTaskCmcc(String taskName, String message, String mobileStr) throws Exception;
}

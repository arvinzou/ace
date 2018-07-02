package com.huacainfo.ace.fop.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.fop.service.MessageService;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.service.TaskCmccService;
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

    @Autowired
    private TaskCmccService taskCmccService;

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
            msg.put("msg", "[" + params.get("name") + "]您已成功注册工商联服务平台，" +
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
}

package com.huacainfo.ace.portal.service.impl;

import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.common.plugins.wechat.entity.msg.in.InTextMsg;
import com.huacainfo.ace.common.plugins.wechat.entity.msg.out.OutTextMsg;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.WechatService;
import com.huacainfo.ace.portal.service.WxCfgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by HuaCai008 on 2018/5/15.
 */
@Service("wechatService")
public class WechatServiceImpl implements WechatService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxCfgService wxCfgService;
    @Autowired
    private KafkaProducerService kafkaProducerService;

    /**
     * 获取回复客服消息
     *
     * @param inTextMsg
     * @return
     */
    @Override
    public OutTextMsg getCustomerResponse(InTextMsg inTextMsg) {
        OutTextMsg outTextMsg = new OutTextMsg(inTextMsg);
        String orgId = inTextMsg.getToUserName();//公众号原始ID
        String openid = inTextMsg.getFromUserName();//用户openid
        String userInput = inTextMsg.getContent();//用户键入消息
        //TODO 以后考虑异步回复问题 ,根据用户输入查询AI问题结果
        List<Map<String, Object>> questionList = wxCfgService.selectQuestion(userInput);
        if (!CollectionUtils.isEmpty(questionList)) {
            Random r = new Random();
            int index = r.nextInt(questionList.size());
            Map<String, Object> question = questionList.get(index);
            outTextMsg.setContent((String) question.get("answer"));

            return outTextMsg;
        }

        //题库中无相关答案,则记录问题，并通知相关人员
        try {
//        WxCfg wxCfg = wxCfgService.findByOriginalId(orgId);
//        if (null == wxCfg) {
//            outTextMsg.setContent("公众号信息未配置");
//        }
            //记录问题
            String recordId = insertQuestion(userInput);
            //推送模板消息
            //1.推送给诉求方
            sendToUser(openid, userInput);
            //2.推送给处理方
            sendToAdmin(userInput);

        } catch (Exception e) {
            logger.error("wechatService.getCustomerResponse.error: {}", e);
        }

        return null;
    }

    private void sendToAdmin(String userInput) {
        //todo 获取管理者openid
        String openid = "oFvIjw7bU8IN-GYgxYCwwf_fOKZY";//武琼
        String tmplCode = "APPEAL_RESULT_NOTICE";
        Map<String, Object> params = new HashMap<>();
        //kafka所需内容
        params.put("service", "messageTemplateService");
        params.put("sysId", "fop");
        params.put("tmplCode", tmplCode);
        //发送消息内容
        params.put("openid", openid);
        params.put("url", "www.baidu.com");
//        params.put("first", "哈哈哈哈哈");
        //data
        params.put("appealContent", userInput);
        params.put("dealResult", "待处理");
        params.put("dealDate", "待处理");
        params.put("consultingTel", "400-12345678");

        kafkaProducerService.sendMsg("topic.sys.msg", params);
    }

    private void sendToUser(String openid, String userInput) {
        String tmplCode = "APPEAL_RESULT_NOTICE";
        Map<String, Object> params = new HashMap<>();
        //kafka所需内容
        params.put("service", "messageTemplateService");
        params.put("sysId", "fop");
        params.put("tmplCode", tmplCode);
        //发送消息内容
        params.put("openid", openid);
        params.put("url", "www.baidu.com");
//        params.put("first", "哈哈哈哈哈");
        //data
        params.put("appealContent", userInput);
        params.put("dealResult", "待处理");
        params.put("dealDate", "待处理");
        params.put("consultingTel", "400-12345678");

        kafkaProducerService.sendMsg("topic.sys.msg", params);
    }

    /**
     * 功能描述:插入问题记录
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/16 14:33
     */
    private String insertQuestion(String userInput) {
        String recordId = GUIDUtil.getGUID();

        Map<String, Object> params = new HashMap<>();
        params.put("id", recordId);
        params.put("question", userInput + "?");
        params.put("createDate", DateUtil.getNowDate());
        params.put("status", "1");
        params.put("createUserName", "system");
        params.put("createUserId", "0000-0000");
        params.put("lastModifyDate", DateUtil.getNowDate());
        params.put("accCount", 1);
        wxCfgService.insertQuestion(params);

        return recordId;
    }


}

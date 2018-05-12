package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.XmlConverUtil;
import com.huacainfo.ace.portal.dao.MessageTemplateDao;
import com.huacainfo.ace.portal.model.MessageSendRecord;
import com.huacainfo.ace.portal.model.MessageTemplate;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.MessageTemplateService;
import com.huacainfo.ace.portal.service.TaskCmccService;
import com.huacainfo.ace.portal.vo.MessageTemplateQVo;
import com.huacainfo.ace.portal.vo.MessageTemplateVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("messageTemplateService")
/**
 * @author: Arvin
 * @version: 2018-05-11
 * @Description: (消息模板)
 */
public class MessageTemplateServiceImpl implements MessageTemplateService {
    public static final String TEXT = "0";
    public static final String XML = "1";
    private static final String SEND_FLAG = "1";
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MessageTemplateDao messageTemplateDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private TaskCmccService taskCmccService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: (消息模板分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<MessageTemplateVo>
     * @author: Arvin
     * @version: 2018-05-11
     */
    @Override
    public PageResult<MessageTemplateVo> findMessageTemplateList(MessageTemplateQVo condition, int start,
                                                                 int limit, String orderBy) throws Exception {
        PageResult<MessageTemplateVo> rst = new PageResult<MessageTemplateVo>();
        List<MessageTemplateVo> list = this.messageTemplateDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.messageTemplateDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertMessageTemplate
     * @Description: (添加消息模板)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    @Override
    public MessageResponse insertMessageTemplate(MessageTemplate o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());
        o.setSysId(userProp.getActiveSyId());
        if (CommonUtils.isBlank(o.getTmplCode())) {
            return new MessageResponse(1, "模板编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getTmplBody())) {
            return new MessageResponse(1, "模板内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getTmplType())) {
            return new MessageResponse(1, "消息类型不能为空！");
        }
        if (CommonUtils.isNotEmpty(o.getSendWechat())
                && "1".equals(o.getSendWechat())
                && CommonUtils.isBlank(o.getWechatTmplId())) {
            return new MessageResponse(1, "微信模板ID不能为空！");
        }

        int temp = this.messageTemplateDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "消息模板名称重复！");
        }

        o.setLastModifyDate(DateUtil.getNowDate());
        o.setCreateDate(DateUtil.getNowDate());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        messageTemplateDao.insertSelective(o);
        dataBaseLogService.log("添加消息模板", "消息模板", "", o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "添加消息模板完成！");
    }

    /**
     * @throws
     * @Title:updateMessageTemplate
     * @Description: (更新消息模板)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    @Override
    public MessageResponse updateMessageTemplate(MessageTemplate o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTmplCode())) {
            return new MessageResponse(1, "模板编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getTmplBody())) {
            return new MessageResponse(1, "模板内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getTmplType())) {
            return new MessageResponse(1, "消息类型不能为空！");
        }
        if (CommonUtils.isNotEmpty(o.getSendWechat())
                && "1".equals(o.getSendWechat())
                && CommonUtils.isBlank(o.getWechatTmplId())) {
            return new MessageResponse(1, "微信模板ID不能为空！");
        }


        o.setLastModifyDate(DateUtil.getNowDate());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        messageTemplateDao.updateByPrimaryKeySelective(o);
        dataBaseLogService.log("变更消息模板", "消息模板", "", o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "变更消息模板完成！");
    }

    /**
     * @throws
     * @Title:selectMessageTemplateByPrimaryKey
     * @Description: (获取消息模板)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<MessageTemplate>
     * @author: Arvin
     * @version: 2018-05-11
     */
    @Override
    public SingleResult<MessageTemplateVo> selectVoByPrimaryKey(String id) throws Exception {
        SingleResult<MessageTemplateVo> rst = new SingleResult<>();
        rst.setValue(this.messageTemplateDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteMessageTemplateByMessageTemplateId
     * @Description: (删除消息模板)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    @Override
    public MessageResponse deleteByPrimaryKey(String id, UserProp userProp) throws Exception {
        messageTemplateDao.deleteByPrimaryKey(id);
        dataBaseLogService.log("删除消息模板", "消息模板", id, id, "消息模板", userProp);
        return new MessageResponse(0, "消息模板删除完成！");
    }

    /**
     * 功能描述: 发送模板消息
     *
     * @param sysId    系统ID
     * @param tmplCode 模板代码
     * @param params   附加参数
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/12 10:49
     */
    @Override
    public ResultResponse send(String sysId, String tmplCode, Map<String, Object> params) throws Exception {
        MessageTemplate template = messageTemplateDao.findByTmplCode(sysId, tmplCode);
        if (CommonUtils.isBlank(template)) {
            return new ResultResponse(ResultCode.FAIL, "发送失败-消息模板不存在", template);
        }
        if (CommonUtils.isBlank(template.getTmplBody())) {
            return new ResultResponse(ResultCode.FAIL, "发送失败-消息内容为空", template);
        }
        //String convert to map
        Map<String, Object> bodyMap = getBodyMap(template);
        if (null == bodyMap) {
            return new ResultResponse(ResultCode.FAIL, "消息类型设置有误", template);
        }
        //
        ResultResponse sms = new ResultResponse(ResultCode.SUCCESS,
                SEND_FLAG.equals(template.getSendSms()) ? "短信发送成功" : "短信推送未开启");
        ResultResponse wechat = new ResultResponse(ResultCode.SUCCESS,
                SEND_FLAG.equals(template.getSendWechat()) ? "微信消息发送成功" : "微信推送未开启");
        //短信推送
        if (SEND_FLAG.equals(template.getSendSms())) {
            sms = sendSms(template, params, bodyMap);
        }
        //微信模板消息推送
        if (SEND_FLAG.equals(template.getSendWechat())) {
            wechat = sendWechat(template, params, bodyMap);
        }
        //一个发送失败，则返回提示消息
        if (ResultCode.FAIL == sms.getStatus() || ResultCode.FAIL == wechat.getStatus()) {
            return new ResultResponse(ResultCode.FAIL, sms.getInfo() + "|" + wechat.getInfo(), template);
        }

        return new ResultResponse(ResultCode.FAIL, "发送失败-消息模板未设置推送类型", template);
    }

    private Map<String, Object> getBodyMap(MessageTemplate template) {
        //tmplType:消息正文 xml/json/text格式
        Map<String, Object> map = new HashMap<>();
        if ("1".equals(template.getTmplType())) {//xml
            return XmlConverUtil.xmltoLinkMap(template.getTmplBody());

        } else if ("0".equals(template.getTmplType())) {//text格式
            map.put("wechat", template.getTmplBody());
            map.put("sms", template.getTmplBody());
//            map.put("innermsg", template.getTmplBody());
        }
        return null;
    }

    /**
     * 功能描述: 短信推送
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/12 11:08
     */
    private ResultResponse sendSms(MessageTemplate template,
                                   Map<String, Object> params, Map<String, Object> bodyMap) throws Exception {
        String mobile = (String) params.get("mobile");
        if (CommonUtils.isBlank(mobile)) {
            return new ResultResponse(ResultCode.FAIL, "");
        }
        String taskName = "业务通知:" + mobile;
        String message = template.getTmplBody();
        for (Map.Entry<String, Object> entry : bodyMap.entrySet()) {
            message.replace("#" + entry.getKey() + "#", String.valueOf(entry.getValue()));
        }

        String mobileStr = mobile + "," + mobile;

        return new ResultResponse(taskCmccService.addTaskCmcc(taskName, message, mobileStr));
    }


    /**
     * 功能描述:  微信模板消息推送
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/12 11:09
     */
    private ResultResponse sendWechat(MessageTemplate template,
                                      Map<String, Object> params, Map<String, Object> bodyMap) {
        //"0": "text", "1": "xml"
        if (!XML.equals(template.getTmplType())) {
            return new ResultResponse(ResultCode.FAIL, "发送失败-消息类型配置有误", template);
        }
        String openid = (String) params.get("openid");


        return null;
    }


    /**
     * 功能描述: 插入消息发送记录
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/12 10:57
     */
    @Override
    public ResultResponse insertSendRecord() {
        MessageSendRecord sendRecord = new MessageSendRecord();


        return null;
    }

}

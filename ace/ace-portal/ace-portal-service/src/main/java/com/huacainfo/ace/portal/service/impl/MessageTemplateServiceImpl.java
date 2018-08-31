package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.api.MessageSendApi;
import com.huacainfo.ace.common.plugins.wechat.entity.Miniprogram;
import com.huacainfo.ace.common.plugins.wechat.entity.TemplateData;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.XmlUtil;
import com.huacainfo.ace.portal.dao.MessageTemplateDao;
import com.huacainfo.ace.portal.model.MessageSendRecord;
import com.huacainfo.ace.portal.model.MessageTemplate;
import com.huacainfo.ace.portal.service.*;
import com.huacainfo.ace.portal.vo.MessageTemplateQVo;
import com.huacainfo.ace.portal.vo.MessageTemplateVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Service("messageTemplateService")
/**
 * @author: Arvin
 * @version: 2018-05-11
 * @Description: (消息模板)
 */
public class MessageTemplateServiceImpl implements MessageTemplateService, BackendService {
    private static final String TEXT = "0";
    private static final String XML = "1";
    private static final String SEND_FLAG = "1";
    private static final String PARAM_SPLIT = "#";
    private static final String TOP_COLOR = "#FF0000";
    private static final String TEMP_COLOR = "#173177";
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MessageTemplateDao messageTemplateDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private TaskCmccService taskCmccService;
    @Autowired
    private UserinfoService userinfoService;

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
        MessageTemplateVo vo = messageTemplateDao.selectVoByPrimaryKey(id);
        rst.setValue(vo);

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
     * 功能描述: 发送模板消息  -- 采用kafka完成此部分逻辑运行
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
        MessageTemplate msgTemplate = messageTemplateDao.findByTmplCode(sysId, tmplCode);
        if (CommonUtils.isBlank(msgTemplate)) {
            return new ResultResponse(ResultCode.FAIL, "发送失败-消息模板不存在", msgTemplate);
        }
        if (CommonUtils.isBlank(msgTemplate.getTmplBody())) {
            return new ResultResponse(ResultCode.FAIL, "发送失败-消息内容为空", msgTemplate);
        }
        ResultResponse sms = new ResultResponse(ResultCode.SUCCESS,
                SEND_FLAG.equals(msgTemplate.getSendSms()) ? "短信发送成功" : "短信推送未开启");
        ResultResponse wechat = new ResultResponse(ResultCode.SUCCESS,
                SEND_FLAG.equals(msgTemplate.getSendWechat()) ? "微信消息发送成功" : "微信推送未开启");
        //短信推送
        //String convert to map
        Map<String, Object> bodyMap = getBodyMap(msgTemplate);
        if (null == bodyMap) {
            return new ResultResponse(ResultCode.FAIL, "消息类型设置有误", msgTemplate);
        }
        if (SEND_FLAG.equals(msgTemplate.getSendSms())) {
            sms = sendSms(msgTemplate, params, bodyMap);
        }
        //微信模板消息推送
        if (SEND_FLAG.equals(msgTemplate.getSendWechat())) {
            wechat = sendWechat(msgTemplate, params, bodyMap);
        }
        //一个发送失败，则返回提示消息
        if (ResultCode.FAIL == sms.getStatus() || ResultCode.FAIL == wechat.getStatus()) {
            return new ResultResponse(ResultCode.FAIL, sms.getInfo() + "|" + wechat.getInfo(), msgTemplate);
        }
        if (!SEND_FLAG.equals(msgTemplate.getSendSms())
                && !SEND_FLAG.equals(msgTemplate.getSendWechat())) {
            return new ResultResponse(ResultCode.FAIL, "发送失败-消息模板未设置推送类型", msgTemplate);
        }

        return new ResultResponse(ResultCode.SUCCESS, "发送成功", msgTemplate);
    }

    private Map<String, Object> getBodyMap(MessageTemplate template) {
        //tmplType:消息正文 xml/json/text格式
        Map<String, Object> map = new HashMap<>();
        if ("1".equals(template.getTmplType())) {//xml
            return XmlUtil.xmltoLinkMap(template.getTmplBody());

        } else if ("0".equals(template.getTmplType())) {//text格式
            map.put("wechat", template.getTmplBody());
            map.put("sms", template.getTmplBody());
//            map.put("innermsg", template.getTmplBody());
            return map;
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
    private ResultResponse sendSms(MessageTemplate msgTemplate,
                                   Map<String, Object> params, Map<String, Object> bodyMap) throws Exception {
        String mobile = (String) params.get("mobile");
        if (CommonUtils.isBlank(mobile)) {
            return new ResultResponse(ResultCode.FAIL, "无目标手机号码");
        }
        String taskName = "业务通知:" + mobile;
        String smsMessage;
        if (bodyMap.containsKey("sms")) {
            smsMessage = (String) bodyMap.get("sms");
        } else {
            smsMessage = msgTemplate.getTmplBody();
        }
        smsMessage = dealValue(params, smsMessage);
        String mobileStr = mobile + "," + mobile;

        return new ResultResponse(taskCmccService.addTaskCmcc(taskName, smsMessage, mobileStr));
    }


    /**
     * 功能描述:  微信模板消息推送
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/12 11:09
     */
    private ResultResponse sendWechat(MessageTemplate msgTemplate,
                                      Map<String, Object> params, Map<String, Object> bodyMap) {
        //"0": "text", "1": "xml"
        if (!XML.equals(msgTemplate.getTmplType())) {
            return new ResultResponse(ResultCode.FAIL, "发送失败-消息类型配置有误", msgTemplate);
        }
        String openid = (String) params.get("openid");
        if (CommonUtils.isEmpty(openid)) {
            return new ResultResponse(ResultCode.FAIL, "发送失败-微信openid不存在", msgTemplate);
        }
        //构建发送数据
        TemplateData templateData = dealWechat(msgTemplate, params, bodyMap);
        if (CommonUtils.isBlank(templateData)) {
            return new ResultResponse(ResultCode.FAIL, "发送失败-微信推送模板数据生成失败", msgTemplate);
        }
        //调用接口，推送消息
        String accessToken = getAccessToken(openid);
        String wxResponse = MessageSendApi.sendTemplate(accessToken, templateData);
        logger.debug("requestParams: {}" + params + "," + "wxResponse: {}" + wxResponse);

        return new ResultResponse(ResultCode.SUCCESS, "发送失败-微信推送成功", msgTemplate);
    }

    /**
     * 获取openid对应下的token信息
     *
     * @param openid
     * @return
     */
    private String getAccessToken(String openid) {
        Map<String, Object> where = new HashMap<>();
        where.put("openid", openid);

        Map<String, Object> userInfoVo = userinfoService.selectUserInfoVo(where);
        if (null == userInfoVo) {
            return "";
        }

        return (String) userInfoVo.get("accessToken");
    }

    private TemplateData dealWechat(MessageTemplate msgTemplate, Map<String, Object> params, Map<String, Object> bodyMap) {
        if (bodyMap.containsKey("wechat")) {
            Object wechatObj = bodyMap.get("wechat");
            if (wechatObj instanceof Map) {
                Map<String, Object> wechatMap = (Map) wechatObj;
                if (!wechatMap.isEmpty()) {
                    //组装微信发送对象
                    String templateId = msgTemplate.getWechatTmplId();
                    //<openid></openid>
                    String openid = dealParams(wechatMap, params, "openid");
                    //<url></url>
                    String url = (String) params.get("url");
                    //<first></first>
                    String first = dealParams(wechatMap, params, "first");
                    //<data></data>
                    Map<String, String> dataMap = getDataMap(params, wechatMap);
                    //<remark></remark>
                    String remark = dealParams(wechatMap, params, "remark");
                    //xml信息
                    TemplateData templateData = getTemplateData(openid, templateId, url, first, remark, dataMap);
                    //处理小程序
                    Miniprogram miniprogram = (Miniprogram) params.get("miniprogram");
                    if (null != miniprogram
                            && StringUtil.isNotEmpty(miniprogram.getAppid())
                            && StringUtil.isNotEmpty(miniprogram.getPagepath())) {
                        templateData.setMiniAppid(miniprogram.getAppid());
                        templateData.setMiniPagePath(miniprogram.getPagepath());
                    }

                    logger.debug("templateData={}", templateData.toString());
                    return templateData;
                }
            }
        }
        return null;
    }

    private TemplateData getTemplateData(String openid, String tmpId, String url, String first, String remark,
                                         Map<String, String> dataMap) {
        TemplateData temp = new TemplateData();
        temp.setTouser(openid);
        temp.setTemplate_id(tmpId);
        temp.setUrl(null == url ? "" : url);//详情链接
        temp.setTopcolor(TOP_COLOR);
        if (CommonUtils.isNotEmpty(first)) {
            temp.add("first", first, TEMP_COLOR);
        }
        if (!CollectionUtils.isEmpty(dataMap)) {
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                temp.add(entry.getKey(), entry.getValue(), TEMP_COLOR);
            }
        }
        if (null != remark) {
            temp.add("remark", remark, TEMP_COLOR);
        }
        return temp;
    }

    /**
     * 处理data节点的内容
     *
     * @param params
     * @param wechatMap
     */
    private Map<String, String> getDataMap(Map<String, Object> params, Map<String, Object> wechatMap) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        if (wechatMap.containsKey("data")) {
            Object dataObj = wechatMap.get("data");
            if (dataObj instanceof Map) {
                Map<String, Object> dataMap = (Map) dataObj;
                if (!dataMap.isEmpty()) {
                    for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                        String value = String.valueOf(entry.getValue());
                        value = dealValue(params, value);
                        map.put(entry.getKey(), value);
                    }
                    return map;
                }
            }
        }

        return map;
    }

    private String dealParams(Map<String, Object> wechatMap, Map<String, Object> params, String key) {
        String value = null;
        if (wechatMap.containsKey(key)) {
            value = String.valueOf(wechatMap.get(key));
            value = dealValue(params, value);
        }

        return value;
    }

    private String dealValue(Map<String, Object> params, String value) {
        //获取所有的参数，参数格式为   正文#para#正文#para#正文#para#正文
        List<String> valueParamList = getArgsFromString(value);
        //如果存在参数，参数params包含这个参数，则替换
        if (!CollectionUtils.isEmpty(valueParamList)) {
            for (String str : valueParamList) {
                if (params.containsKey(str)) {
                    value = StringUtils.replace(value, PARAM_SPLIT + str + PARAM_SPLIT, String.valueOf(params.get(str)));
                }
            }
        }
        return value;
    }

    private List<String> getArgsFromString(String value) {
        List<String> paramList = new ArrayList<>();
        if (CommonUtils.isNotEmpty(value) && value.contains(PARAM_SPLIT)) {
            String[] paramArr = value.split(PARAM_SPLIT);
            for (int i = 1; i < paramArr.length; i++) {
                paramList.add(paramArr[i]);
                i++;
            }
        }
        return paramList;
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


    /***
     * Mq队列处理逻辑
     * @param data  sysId -- 系统识别ID
     *              tmplCode -- 模板编码
     *              ... -- 其他参数
     * @return
     * @throws Exception
     */
    @Override
    public MessageResponse service(Map<String, Object> data) throws Exception {
        String sysId = (String) data.get("sysId");
        String tmplCode = (String) data.get("tmplCode");

        ResultResponse rs = send(sysId, tmplCode, data);
        return new MessageResponse(rs.getStatus(), rs.getInfo());
    }

    /**
     * 功能描述: 调用参考代码
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/14 11:51
     */
    private void sendDemo() {
        //华彩伟业公众号
        String openid1 = "oFvIjw7bU8IN-GYgxYCwwf_fOKZY";//武琼
        String openid2 = "oFvIjw9bgtJmgvqVv0XIayPsh2QI";//聂宁
        String openid3 = "oFvIjw8x1--0lQkUhO1Ta3L59o3c";//陈总

        Map<String, Object> params = new HashMap<>();
        params.put("openid", openid1);
        params.put("url", "www.qq.com");
        params.put("first", "哈哈哈哈哈");
        //data
        params.put("enterpriseName", "华彩伟业");
        params.put("payAmount", "100万");
        params.put("feeType", "保护费");
        params.put("payTimeInterval", "2018-01-01" + "至" + "2019-01-01");
        params.put("payDate", DateUtil.getNow());

        //sms
        params.put("mobile", "18570629027");

//        ResultResponse rs = service.send(getMessageTemplate(), params);
//        System.out.println(rs.toString());
    }

    private MessageTemplate getMessageTemplate() {
        String body = "<!--\n" +
                "{{first.DATA}}\n" +
                "企业名：{{keyword1.DATA}}\n" +
                "缴费金额：{{keyword2.DATA}}\n" +
                "费用类型：{{keyword3.DATA}}\n" +
                "缴费时间段：{{keyword4.DATA}}\n" +
                "缴费时间：{{keyword5.DATA}}\n" +
                "{{remark.DATA}}\n" +
                "-->\n" +
                "<xml>\n" +
                "<wechat>\n" +
                "\t<openid>#openid#</openid>\n" +
                "\t<url></url>\n" +
                "\t<first><![CDATA[ hello 摩托 #first#]]></first>\n" +
                "\t<data>\n" +
                "\t\t<keyword1>#enterpriseName#</keyword1>\n" +
                "\t\t<keyword2>#payAmount#</keyword2>\n" +
                "\t\t<keyword3>#feeType#</keyword3>\n" +
                "\t\t<keyword4>#payTimeInterval#</keyword4>\n" +
                "\t\t<keyword5>#payDate#</keyword5>\n" +
                "\t</data>\n" +
                "\t<remark>this is a test,don't worry about it. everything is fine !</remark>\n" +
                "</wechat>\n" +
                "<sms></sms>\n" +
                "</xml>";

        String wxTmplId = "xfnk2KnQqx6R-NcRAZJ3A00sErVJWcjh8VqLCxc8EAY";


        MessageTemplate messageTemplate = new MessageTemplate();
        messageTemplate.setSysId("fop");
        messageTemplate.setTmplCode("MEMBER_REGISTER_PAY_SUCCESS");
        messageTemplate.setTmplType("1");
        messageTemplate.setTmplBody(body);
        messageTemplate.setSendSms("0");
        messageTemplate.setSendWechat("1");
        messageTemplate.setWechatTmplId(wxTmplId);

        return messageTemplate;
    }
}

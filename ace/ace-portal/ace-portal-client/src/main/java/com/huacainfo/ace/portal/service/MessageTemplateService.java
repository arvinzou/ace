package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.MessageTemplate;
import com.huacainfo.ace.portal.vo.MessageTemplateQVo;
import com.huacainfo.ace.portal.vo.MessageTemplateVo;

import java.util.Map;

/**
 * @author: Arvin
 * @version: 2018-05-11
 * @Description: (消息模板)
 */
public interface MessageTemplateService {
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
    PageResult<MessageTemplateVo> findMessageTemplateList(MessageTemplateQVo condition,
                                                          int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertMessageTemplate
     * @Description: (添加消息模板)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    MessageResponse insertMessageTemplate(MessageTemplate obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateMessageTemplate
     * @Description: (更新消息模板)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    MessageResponse updateMessageTemplate(MessageTemplate obj, UserProp userProp) throws Exception;

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
    SingleResult<MessageTemplateVo> selectVoByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteMessageTemplateByMessageTemplateId
     * @Description: (删除消息模板)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    MessageResponse deleteByPrimaryKey(String id, UserProp userProp) throws Exception;


    /**
     * 功能描述: 发送模板消息   -- 采用kafka完成此部分逻辑运行
     *
     * @param sysId    系统ID
     * @param tmplCode 模板代码
     * @param params   附加参数
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/12 10:49
     */
    ResultResponse send(String sysId, String tmplCode, Map<String, Object> params) throws Exception;


    /**
     * 功能描述: 插入消息发送记录
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/12 10:57
     */
    ResultResponse insertSendRecord();

}

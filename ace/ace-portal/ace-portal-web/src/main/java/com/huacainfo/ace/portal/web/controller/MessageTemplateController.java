package com.huacainfo.ace.portal.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.MessageTemplate;
import com.huacainfo.ace.portal.service.MessageTemplateService;
import com.huacainfo.ace.portal.vo.MessageTemplateQVo;
import com.huacainfo.ace.portal.vo.MessageTemplateVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/messageTemplate")
/**
 * @author: Arvin
 * @version: 2018-05-11
 * @Description: TODO(消息模板)
 */
public class MessageTemplateController extends PortalBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MessageTemplateService messageTemplateService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(消息模板分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<MessageTemplateVo>
     * @author: Arvin
     * @version: 2018-05-11
     */
    @RequestMapping(value = "/findMessageTemplateList.do")
    @ResponseBody
    public PageResult<MessageTemplateVo> findMessageTemplateList(MessageTemplateQVo condition,
                                                                 PageParamNoChangeSord page) throws Exception {
        PageResult<MessageTemplateVo> rst = this.messageTemplateService
                .findMessageTemplateList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertMessageTemplate
     * @Description: TODO(添加消息模板)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    @RequestMapping(value = "/insertMessageTemplate.do")
    @ResponseBody
    public MessageResponse insertMessageTemplate(String jsons) throws Exception {
        MessageTemplate obj = JSON.parseObject(jsons, MessageTemplate.class);
        return this.messageTemplateService
                .insertMessageTemplate(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateMessageTemplate
     * @Description: TODO(更新消息模板)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    @RequestMapping(value = "/updateMessageTemplate.do")
    @ResponseBody
    public MessageResponse updateMessageTemplate(String jsons) throws Exception {
        MessageTemplate obj = JSON.parseObject(jsons, MessageTemplate.class);
        return this.messageTemplateService
                .updateMessageTemplate(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectMessageTemplateByPrimaryKey
     * @Description: TODO(获取消息模板)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<MessageTemplate>
     * @author: Arvin
     * @version: 2018-05-11
     */
    @RequestMapping(value = "/selectVoByPrimaryKey.do")
    @ResponseBody
    public SingleResult<MessageTemplateVo> selectVoByPrimaryKey(String id) throws Exception {
        return this.messageTemplateService.selectVoByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteMessageTemplateByMessageTemplateId
     * @Description: TODO(删除消息模板)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    @RequestMapping(value = "/deleteByPrimaryKey.do")
    @ResponseBody
    public MessageResponse deleteByPrimaryKey(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.messageTemplateService.deleteByPrimaryKey(id, this.getCurUserProp());
    }
}

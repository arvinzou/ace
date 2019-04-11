package com.huacainfo.ace.glink.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.glink.model.SmsSchedulerMapped;
import com.huacainfo.ace.glink.service.SmsSchedulerMappedService;
import com.huacainfo.ace.glink.vo.SmsSchedulerMappedQVo;
import com.huacainfo.ace.glink.vo.SmsSchedulerMappedVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/smsSchedulerMapped")
/**
 * @author: Arvin
 * @version: 2019-04-11
 * @Description: TODO(故障报警 - 短信 - 调度映射关系)
 */
public class SmsSchedulerMappedController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SmsSchedulerMappedService smsSchedulerMappedService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(故障报警 - 短信 - 调度映射关系分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<SmsSchedulerMappedVo>
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/findSmsSchedulerMappedList")
    @ResponseBody
    public PageResult<SmsSchedulerMappedVo> findSmsSchedulerMappedList(SmsSchedulerMappedQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<SmsSchedulerMappedVo> rst = this.smsSchedulerMappedService.findSmsSchedulerMappedList(condition, page.getStart(),
                page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertSmsSchedulerMapped
     * @Description: TODO(添加故障报警 - 短信 - 调度映射关系)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/insertSmsSchedulerMapped")
    @ResponseBody
    public MessageResponse insertSmsSchedulerMapped(String jsons) throws Exception {
        SmsSchedulerMapped obj = JSON.parseObject(jsons, SmsSchedulerMapped.class);
        return this.smsSchedulerMappedService.insertSmsSchedulerMapped(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateSmsSchedulerMapped
     * @Description: TODO(更新故障报警 - 短信 - 调度映射关系)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/updateSmsSchedulerMapped")
    @ResponseBody
    public MessageResponse updateSmsSchedulerMapped(String jsons) throws Exception {
        SmsSchedulerMapped obj = JSON.parseObject(jsons, SmsSchedulerMapped.class);
        return this.smsSchedulerMappedService.updateSmsSchedulerMapped(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectSmsSchedulerMappedByPrimaryKey
     * @Description: TODO(获取故障报警 - 短信 - 调度映射关系)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SmsSchedulerMapped>
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/selectSmsSchedulerMappedByPrimaryKey")
    @ResponseBody
    public SingleResult<SmsSchedulerMappedVo> selectSmsSchedulerMappedByPrimaryKey(String id) throws Exception {
        return this.smsSchedulerMappedService.selectSmsSchedulerMappedByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteSmsSchedulerMappedBySmsSchedulerMappedId
     * @Description: TODO(删除故障报警 - 短信 - 调度映射关系)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/deleteSmsSchedulerMappedBySmsSchedulerMappedId")
    @ResponseBody
    public MessageResponse deleteSmsSchedulerMappedBySmsSchedulerMappedId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.smsSchedulerMappedService.deleteSmsSchedulerMappedBySmsSchedulerMappedId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version:2019-04-11
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.smsSchedulerMappedService.updateStatus(id, status, this.getCurUserProp());
    }
}

package com.huacainfo.ace.glink.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.glink.model.DynamicScheduler;
import com.huacainfo.ace.glink.service.DynamicSchedulerService;
import com.huacainfo.ace.glink.vo.DynamicSchedulerQVo;
import com.huacainfo.ace.glink.vo.DynamicSchedulerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dynamicScheduler")
/**
 * @author: Arvin
 * @version: 2019-04-11
 * @Description: TODO(故障报警 - 调度设置)
 */
public class DynamicSchedulerController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DynamicSchedulerService dynamicSchedulerService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(故障报警 - 调度设置分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<DynamicSchedulerVo>
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/findDynamicSchedulerList")
    @ResponseBody
    public PageResult<DynamicSchedulerVo> findDynamicSchedulerList(DynamicSchedulerQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<DynamicSchedulerVo> rst = this.dynamicSchedulerService.findDynamicSchedulerList(condition, page.getStart(),
                page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertDynamicScheduler
     * @Description: TODO(添加故障报警 - 调度设置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/insertDynamicScheduler")
    @ResponseBody
    public MessageResponse insertDynamicScheduler(String jsons) throws Exception {
        DynamicScheduler obj = JSON.parseObject(jsons, DynamicScheduler.class);
        return this.dynamicSchedulerService.insertDynamicScheduler(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateDynamicScheduler
     * @Description: TODO(更新故障报警 - 调度设置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/updateDynamicScheduler")
    @ResponseBody
    public MessageResponse updateDynamicScheduler(String jsons) throws Exception {
        DynamicScheduler obj = JSON.parseObject(jsons, DynamicScheduler.class);
        return this.dynamicSchedulerService.updateDynamicScheduler(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectDynamicSchedulerByPrimaryKey
     * @Description: TODO(获取故障报警 - 调度设置)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<DynamicScheduler>
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/selectDynamicSchedulerByPrimaryKey")
    @ResponseBody
    public SingleResult<DynamicSchedulerVo> selectDynamicSchedulerByPrimaryKey(String id) throws Exception {
        return this.dynamicSchedulerService.selectDynamicSchedulerByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteDynamicSchedulerByDynamicSchedulerId
     * @Description: TODO(删除故障报警 - 调度设置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/deleteDynamicSchedulerByDynamicSchedulerId")
    @ResponseBody
    public MessageResponse deleteDynamicSchedulerByDynamicSchedulerId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.dynamicSchedulerService.deleteDynamicSchedulerByDynamicSchedulerId(id, this.getCurUserProp());
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
        return this.dynamicSchedulerService.updateStatus(id, status, this.getCurUserProp());
    }
}

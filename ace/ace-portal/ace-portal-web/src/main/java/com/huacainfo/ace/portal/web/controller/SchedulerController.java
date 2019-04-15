package com.huacainfo.ace.portal.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.Scheduler;
import com.huacainfo.ace.portal.vo.SchedulerQVo;
import com.huacainfo.ace.portal.vo.SchedulerVo;
import com.huacainfo.ace.portal.service.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/scheduler")
/**
 * @author: Arvin
 * @version: 2019-04-11
 * @Description: TODO(故障报警 - 调度设置)
 */
public class SchedulerController extends PortalBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SchedulerService schedulerService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(故障报警 - 调度设置分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<SchedulerVo>
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/findSchedulerList")
    @ResponseBody
    public PageResult<SchedulerVo> findSchedulerList(SchedulerQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<SchedulerVo> rst = this.schedulerService.findSchedulerList(condition, page.getStart(),
                page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertscheduler
     * @Description: TODO(添加故障报警 - 调度设置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/insertScheduler")
    @ResponseBody
    public MessageResponse insertScheduler(String jsons) throws Exception {
        Scheduler obj = JSON.parseObject(jsons, Scheduler.class);
        return this.schedulerService.insertScheduler(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updatescheduler
     * @Description: TODO(更新故障报警 - 调度设置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/updateScheduler")
    @ResponseBody
    public MessageResponse updateScheduler(String jsons) throws Exception {
        Scheduler obj = JSON.parseObject(jsons, Scheduler.class);
        return this.schedulerService.updateScheduler(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectschedulerByPrimaryKey
     * @Description: TODO(获取故障报警 - 调度设置)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Scheduler>
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/selectSchedulerByPrimaryKey")
    @ResponseBody
    public SingleResult<SchedulerVo> selectSchedulerByPrimaryKey(String id) throws Exception {
        return this.schedulerService.selectSchedulerByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteschedulerByschedulerId
     * @Description: TODO(删除故障报警 - 调度设置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/deleteSchedulerBySchedulerId")
    @ResponseBody
    public MessageResponse deleteSchedulerBySchedulerId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.schedulerService.deleteSchedulerBySchedulerId(id, this.getCurUserProp());
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
        return this.schedulerService.updateStatus(id, status, this.getCurUserProp());
    }

    /**
     * 更新调度规则有效状态
     *
     * @param id    主键
     * @param state 0-失效，1-生效
     * @return MessageResponse
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/updateValidState")
    public MessageResponse updateValidStatus(String id, String state) throws Exception {
        return this.schedulerService.updateValidState(id, state, this.getCurUserProp());
    }
}

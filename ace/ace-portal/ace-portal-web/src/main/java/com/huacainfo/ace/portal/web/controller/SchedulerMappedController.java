package com.huacainfo.ace.portal.web.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.portal.model.SchedulerMapped;
import com.huacainfo.ace.portal.service.SchedulerMappedService;
import com.huacainfo.ace.portal.vo.SchedulerMappedQVo;
import com.huacainfo.ace.portal.vo.SchedulerMappedVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/schedulerMapped")
/**
 * @author: ArvinZou
 * @version: 2019-04-12
 * @Description: TODO(调度配置)
 */
public class SchedulerMappedController extends PortalBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SchedulerMappedService schedulerMappedService;

    /**
     * @Title: findVoList
     * @Description: TODO(调度配置分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<SchedulerMappedVo>
     * @author: ArvinZou
     * @version: 2019-04-12
     */
    @RequestMapping(value = "/findVoList")
    @ResponseBody
    public PageResult<SchedulerMappedVo> findSchedulerMappedList(SchedulerMappedQVo condition,
                                                                 PageParamNoChangeSord page) throws Exception {

        PageResult<SchedulerMappedVo> rst = this.schedulerMappedService.findVoList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertSchedulerMapped
     * @Description: TODO(添加调度配置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-04-12
     */
    @RequestMapping(value = "/insertSchedulerMapped")
    @ResponseBody
    public MessageResponse insertSchedulerMapped(String jsons) throws Exception {
        SchedulerMapped obj = JSON.parseObject(jsons, SchedulerMapped.class);
        return this.schedulerMappedService.insertSchedulerMapped(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateSchedulerMapped
     * @Description: TODO(更新调度配置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-04-12
     */
    @RequestMapping(value = "/updateSchedulerMapped")
    @ResponseBody
    public MessageResponse updateSchedulerMapped(String jsons) throws Exception {
        SchedulerMapped obj = JSON.parseObject(jsons, SchedulerMapped.class);
        return this.schedulerMappedService.updateSchedulerMapped(obj, this.getCurUserProp());
    }

    /**
     * @Title:selectByPrimaryKey
     * @Description: TODO(获取调度配置)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SchedulerMapped>
     * @author: ArvinZou
     * @version: 2019-04-12
     */
    @RequestMapping(value = "/selectByPrimaryKey")
    @ResponseBody
    public SchedulerMapped selectByPrimaryKey(String id) throws Exception {
        return this.schedulerMappedService.selectByPrimaryKey(id);
    }

    /**
     * @Title:deleteById
     * @Description: TODO(删除调度配置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-04-12
     */
    @ResponseBody
    @RequestMapping(value = "/deleteByPrimaryKey")
    public MessageResponse deleteByPrimaryKey(String id) throws Exception {
        return this.schedulerMappedService.deleteByPrimaryKey(id, this.getCurUserProp());
    }
}

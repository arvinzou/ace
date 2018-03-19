package com.huacainfo.ace.woc.web.controller;

import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.woc.model.MonitorSiteDetail;
import com.huacainfo.ace.woc.service.MonitorSiteDetailService;
import com.huacainfo.ace.woc.vo.MonitorSiteDetailQVo;
import com.huacainfo.ace.woc.vo.MonitorSiteDetailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.MonitorSite;
import com.huacainfo.ace.woc.service.MonitorSiteService;
import com.huacainfo.ace.woc.vo.MonitorSiteVo;
import com.huacainfo.ace.woc.vo.MonitorSiteQVo;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/monitorSite")
/**
 * @author: Arvin
 * @version: 2018-03-12
 * @Description: TODO(监控点档案)
 */
public class MonitorSiteController extends WocBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MonitorSiteService monitorSiteService;

    @Autowired
    private MonitorSiteDetailService monitorSiteDetailService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(监控点档案分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<MonitorSiteVo>
     * @author: Arvin
     * @version: 2018-03-12
     */
    @RequestMapping(value = "/findMonitorSiteList")
    @ResponseBody
    public PageResult<MonitorSiteVo> findMonitorSiteList(MonitorSiteQVo condition,
                                                         PageParamNoChangeSord page) throws Exception {
        PageResult<MonitorSiteVo> rst = this.monitorSiteService
                .findMonitorSiteList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertMonitorSite
     * @Description: TODO(添加监控点档案)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-12
     */
    @RequestMapping(value = "/insertMonitorSite")
    @ResponseBody
    public MessageResponse insertMonitorSite(String jsons) throws Exception {
        MonitorSite obj = JSON.parseObject(jsons, MonitorSite.class);
        return this.monitorSiteService
                .insertMonitorSite(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateMonitorSite
     * @Description: TODO(更新监控点档案)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-12
     */
    @RequestMapping(value = "/updateMonitorSite")
    @ResponseBody
    public MessageResponse updateMonitorSite(String jsons) throws Exception {
        MonitorSite obj = JSON.parseObject(jsons, MonitorSite.class);
        return this.monitorSiteService
                .updateMonitorSite(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectMonitorSiteByPrimaryKey
     * @Description: TODO(获取监控点档案)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<MonitorSite>
     * @author: Arvin
     * @version: 2018-03-12
     */
    @RequestMapping(value = "/selectMonitorSiteByPrimaryKey")
    @ResponseBody
    public SingleResult<MonitorSiteVo> selectMonitorSiteByPrimaryKey(String id)
            throws Exception {
        return this.monitorSiteService.selectMonitorSiteByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteMonitorSiteByMonitorSiteId
     * @Description: TODO(删除监控点档案)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-12
     */
    @RequestMapping(value = "/deleteMonitorSiteByMonitorSiteId")
    @ResponseBody
    public MessageResponse deleteMonitorSiteByMonitorSiteId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.monitorSiteService.deleteMonitorSiteByMonitorSiteId(id,
                this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:insertMonitorSite
     * @Description: TODO(添加监控点明细)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-12
     */
    @RequestMapping(value = "/bindMonitorSiteDevice")
    @ResponseBody
    public MessageResponse bindMonitorSiteDevice(MonitorSiteDetailVo[] dataList) throws Exception {

        if (null == dataList || dataList.length == 0) {
            return new MessageResponse(1, "传入数据不能为空！");
        }

        return monitorSiteDetailService.bindMonitorSiteDevice(dataList, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(监控点明显档案分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<MonitorSiteDetailVo>
     * @author: Arvin
     * @version: 2018-03-12
     */
    @RequestMapping(value = "/findMonitorSiteDetailList")
    @ResponseBody
    public PageResult<MonitorSiteDetailVo> findMonitorSiteDetailList(MonitorSiteDetailQVo condition,
                                                                     PageParamNoChangeSord page) throws Exception {
        PageResult<MonitorSiteDetailVo> rst = this.monitorSiteDetailService
                .findMonitorSiteDetailList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }
}

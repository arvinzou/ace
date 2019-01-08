package com.huacainfo.ace.taa.web.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.taa.model.RoadGps;
import com.huacainfo.ace.taa.service.RoadGpsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/roadGps")
/**
 * @author: 陈晓克
 * @version: 2019-01-08
 * @Description: TODO(GPS)
 */
public class RoadGpsController extends TaaBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoadGpsService roadGpsService;


    /**
     * @throws
     * @Title:insertRoadGps
     * @Description: TODO(添加GPS)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/insertRoadGps")
    @ResponseBody
    public MessageResponse insertRoadGps(String jsons) throws Exception {
        RoadGps obj = JSON.parseObject(jsons, RoadGps.class);
        return this.roadGpsService.insertRoadGps(obj, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: 陈晓克
     * @version:2019-01-08
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.roadGpsService.getList(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteRoadGpsByRoadGpsIds
     * @Description: TODO(批量删除GPS)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version:2019-01-08
     */
    @RequestMapping(value = "/deleteRoadGpsByRoadGpsIds")
    @ResponseBody
    public MessageResponse deleteRoadGpsByRoadGpsIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.roadGpsService.deleteRoadGpsByRoadGpsIds(id, this.getCurUserProp());
    }
}

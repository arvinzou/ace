package com.huacainfo.ace.woc.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.TrafficIllegal;
import com.huacainfo.ace.woc.service.TrafficIllegalService;
import com.huacainfo.ace.woc.vo.TrafficIllegalQVo;
import com.huacainfo.ace.woc.vo.TrafficIllegalVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/trafficIllegal")
/**
 * @author: 王恩
 * @version: 2018-03-21
 * @Description: TODO(通行违章记录)
 */
public class TrafficIllegalController extends WocBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TrafficIllegalService trafficIllegalService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(通行违章记录分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<TrafficIllegalVo>
     * @author: 王恩
     * @version: 2018-03-21
     */
    @RequestMapping(value = "/findTrafficIllegalList")
    @ResponseBody
    public PageResult<TrafficIllegalVo> findTrafficIllegalList(TrafficIllegalQVo condition,
                                                               PageParamNoChangeSord page) throws Exception {
        PageResult<TrafficIllegalVo> rst = this.trafficIllegalService
                .findTrafficIllegalList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertTrafficIllegal
     * @Description: TODO(添加通行违章记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    @RequestMapping(value = "/insertTrafficIllegal")
    @ResponseBody
    public MessageResponse insertTrafficIllegal(String jsons) throws Exception {
        TrafficIllegal obj = JSON.parseObject(jsons, TrafficIllegal.class);
        return this.trafficIllegalService
                .insertTrafficIllegal(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateTrafficIllegal
     * @Description: TODO(更新通行违章记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    @RequestMapping(value = "/updateTrafficIllegal")
    @ResponseBody
    public MessageResponse updateTrafficIllegal(String jsons) throws Exception {
        TrafficIllegal obj = JSON.parseObject(jsons, TrafficIllegal.class);
        return this.trafficIllegalService
                .updateTrafficIllegal(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectTrafficIllegalByPrimaryKey
     * @Description: TODO(获取通行违章记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TrafficIllegal>
     * @author: 王恩
     * @version: 2018-03-21
     */
    @RequestMapping(value = "/selectTrafficIllegalByPrimaryKey")
    @ResponseBody
    public SingleResult<TrafficIllegalVo> selectTrafficIllegalByPrimaryKey(String id)
            throws Exception {
        return this.trafficIllegalService.selectTrafficIllegalByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteTrafficIllegalByTrafficIllegalId
     * @Description: TODO(删除通行违章记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    @RequestMapping(value = "/deleteTrafficIllegalByTrafficIllegalId")
    @ResponseBody
    public MessageResponse deleteTrafficIllegalByTrafficIllegalId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.trafficIllegalService.deleteTrafficIllegalByTrafficIllegalId(id,
                this.getCurUserProp());
    }


    /**
     * 违章记录转为案件
     *
     * @param ids  woc.traffic_illegal.id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/convertToCases")
    @ResponseBody
    public MessageResponse convertToCases(String ids) throws Exception {
        return this.trafficIllegalService.convertToCases(ids, this.getCurUserProp());
    }
}

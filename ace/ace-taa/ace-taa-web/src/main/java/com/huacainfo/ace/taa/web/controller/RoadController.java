package com.huacainfo.ace.taa.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.taa.model.Road;
import com.huacainfo.ace.taa.service.RoadService;
import com.huacainfo.ace.taa.vo.RoadVo;
import com.huacainfo.ace.taa.vo.RoadQVo;

import java.util.Map;

@Controller
@RequestMapping("/road")
/**
 * @author: 陈晓克
 * @version: 2019-01-03
 * @Description: TODO(道路)
 */
public class RoadController extends TaaBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoadService roadService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(道路分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <RoadVo>
     * @author: 陈晓克
     * @version: 2019-01-03
     */
    @RequestMapping(value = "/findRoadList")
    @ResponseBody
    public PageResult<RoadVo> findRoadList(RoadQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<RoadVo> rst = this.roadService.findRoadList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertRoad
     * @Description: TODO(添加道路)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-03
     */
    @RequestMapping(value = "/insertRoad")
    @ResponseBody
    public MessageResponse insertRoad(String jsons) throws Exception {
        Road obj = JSON.parseObject(jsons, Road.class);
        return this.roadService.insertRoad(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateRoad
     * @Description: TODO(更新道路)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-03
     */
    @RequestMapping(value = "/updateRoad")
    @ResponseBody
    public MessageResponse updateRoad(String jsons) throws Exception {
        Road obj = JSON.parseObject(jsons, Road.class);
        return this.roadService.updateRoad(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectRoadByPrimaryKey
     * @Description: TODO(获取道路)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Road>
     * @author: 陈晓克
     * @version: 2019-01-03
     */
    @RequestMapping(value = "/selectRoadByPrimaryKey")
    @ResponseBody
    public SingleResult<RoadVo> selectRoadByPrimaryKey(String id) throws Exception {
        return this.roadService.selectRoadByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteRoadByRoadId
     * @Description: TODO(删除道路)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-03
     */
    @RequestMapping(value = "/deleteRoadByRoadId")
    @ResponseBody
    public MessageResponse deleteRoadByRoadId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.roadService.deleteRoadByRoadId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(路长查询，用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: chenxiaoke
     * @version: 2019年1月04日 下午1:24:14
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.roadService.getListByCondition(this.getParams());
    }

}

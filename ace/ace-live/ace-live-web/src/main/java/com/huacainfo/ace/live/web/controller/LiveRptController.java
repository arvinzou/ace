package com.huacainfo.ace.live.web.controller;

import com.huacainfo.ace.live.model.LiveImg;
import com.huacainfo.ace.live.web.websocket.MyWebSocket;
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
import com.huacainfo.ace.live.model.LiveRpt;
import com.huacainfo.ace.live.service.LiveRptService;
import com.huacainfo.ace.live.vo.LiveRptVo;
import com.huacainfo.ace.live.vo.LiveRptQVo;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/liveRpt")
/**
 * @author: 陈晓克
 * @version: 2018-01-03
 * @Description: TODO(图文直播)
 */
public class LiveRptController extends LiveBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LiveRptService liveRptService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(图文直播分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<LiveRptVo>
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @RequestMapping(value = "/findLiveRptList.do")
    @ResponseBody
    public PageResult<LiveRptVo> findLiveRptList(LiveRptQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<LiveRptVo> rst = this.liveRptService.findLiveRptList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertLiveRpt
     * @Description: TODO(添加图文直播)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @RequestMapping(value = "/insertLiveRpt.do")
    @ResponseBody
    public MessageResponse insertLiveRpt(String jsons) throws Exception {
        logger.debug(jsons);
        JSONObject json = JSON.parseObject(jsons);
        LiveRpt obj = JSON.parseObject(((JSONObject) json.get("rpt")).toJSONString(), LiveRpt.class);
        List<LiveImg> imgs = JSON.parseArray(((JSONObject) json.get("imgs")).toJSONString(), LiveImg.class);


        return this.liveRptService
                .insertLiveRpt(obj, imgs);
    }

    /**
     * @throws
     * @Title:updateLiveRpt
     * @Description: TODO(更新图文直播)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @RequestMapping(value = "/updateLiveRpt.do")
    @ResponseBody
    public MessageResponse updateLiveRpt(String jsons) throws Exception {
        LiveRpt obj = JSON.parseObject(jsons, LiveRpt.class);
        return this.liveRptService
                .updateLiveRpt(obj);
    }

    /**
     * @throws
     * @Title:updateLiveRpt
     * @Description: TODO(更新图文直播)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @RequestMapping(value = "/updateLiveRptStatus.do")
    @ResponseBody
    public MessageResponse updateLiveRptStatus(String id, String status, String message, String rid) throws Exception {
        if (status != null && "2".equals(status)) {
            for (MyWebSocket item : MyWebSocket.rooms.get(rid)) {
                try {
                    item.sendMessage(message);
                } catch (IOException e) {
                    logger.error(e.getMessage());
                    continue;
                }
            }
        }

        return this.liveRptService.updateLiveRptStatus(id, status);
    }

    /**
     * @throws
     * @Title:selectLiveRptByPrimaryKey
     * @Description: TODO(获取图文直播)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LiveRpt>
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @RequestMapping(value = "/selectLiveRptByPrimaryKey.do")
    @ResponseBody
    public SingleResult<LiveRptVo> selectLiveRptByPrimaryKey(String id)
            throws Exception {
        return this.liveRptService.selectLiveRptByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteLiveRptByLiveRptId
     * @Description: TODO(删除图文直播)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @RequestMapping(value = "/deleteLiveRptByLiveRptId.do")
    @ResponseBody
    public MessageResponse deleteLiveRptByLiveRptId(String id) throws Exception {
        return this.liveRptService.deleteLiveRptByLiveRptId(id,
                this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:sendLiveRptContentBySortAct
     * @Description: TODO(图文直播排序)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @RequestMapping(value = "/sendLiveRptContentBySortAct.do")
    @ResponseBody
    public MessageResponse sendLiveRptContentBySortAct(String message, String rid) throws Exception {
        for (MyWebSocket item : MyWebSocket.rooms.get(rid)) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                logger.error(e.getMessage());
                continue;
            }
        }
        return new MessageResponse(0, "排序完成！");
    }

    /**
     *
     * @param data [{"id":"id1","index":0},{"id":"id2","index":1}]
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateSequence.do")
    @ResponseBody
    public MessageResponse updateSequence(String data) throws Exception {
        return liveRptService.updateSequence(data);
    }

}

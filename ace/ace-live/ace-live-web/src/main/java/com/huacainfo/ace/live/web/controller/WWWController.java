package com.huacainfo.ace.live.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.live.model.Live;
import com.huacainfo.ace.live.model.LiveMsg;
import com.huacainfo.ace.live.model.LiveSub;
import com.huacainfo.ace.live.service.LiveMsgService;
import com.huacainfo.ace.live.service.LiveSubService;
import com.huacainfo.ace.live.service.WWWService;
import com.huacainfo.ace.live.vo.LiveQVo;
import com.huacainfo.ace.live.vo.LiveVo;
import com.huacainfo.ace.live.web.websocket.MyWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.Session;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/www/live/")
/**
 * @author: 陈晓克
 * @version: 2017-12-27
 * @Description: TODO(直播)
 */
public class WWWController extends LiveBaseController {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WWWService wwwService;

    @Autowired
    private LiveMsgService liveMsgService;

    @Autowired
    private LiveSubService liveSubService;

    /**
     * @throws
     * @Title:getLiveList
     * @Description: TODO(微网页获取直播列表)
     * @param: @param p
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-01-01
     */
    @RequestMapping(value = "/getLiveList.do")
    @ResponseBody
    public List<Map<String, Object>> getLiveList() throws Exception {

        return this.wwwService.getLiveList(this.getParams());
    }

    /**
     * @throws
     * @Title:getLive
     * @Description: TODO(微网页根据直播获取ID，直播信息信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-01-01
     */
    @RequestMapping(value = "/getLive.do")
    @ResponseBody
    public Map<String, Object> getLive(String id) throws Exception {
        return this.wwwService.getLive(id);
    }

    /**
     * @throws
     * @Title:insertLiveMsg
     * @Description: TODO(添加图文直播)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @RequestMapping(value = "/insertLiveMsg.do")
    @ResponseBody
    public MessageResponse insertLiveMsg(String jsons) throws Exception {
        LiveMsg obj = JSON.parseObject(jsons, LiveMsg.class);
        return this.liveMsgService
                .insertLiveMsg(obj);
    }

    /**
     * @throws
     * @Title:insertLiveSub
     * @Description: TODO(添加图文直播)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @RequestMapping(value = "/insertLiveSub.do")
    @ResponseBody
    public MessageResponse insertLiveSub(String jsons) throws Exception {
        LiveSub obj = JSON.parseObject(jsons, LiveSub.class);
        return this.liveSubService
                .insertLiveSub(obj);
    }

    @RequestMapping(value = "/sendMsg.do")
    @ResponseBody
    public MessageResponse sendMsg(String message, String rid) throws Exception {
        logger.debug("{} {}", rid, message);

        //群发消息
        for (MyWebSocket item : MyWebSocket.rooms.get(rid)) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                logger.error(e.getMessage());
                continue;
            }
        }
        return new MessageResponse(0, "OK");
    }
}

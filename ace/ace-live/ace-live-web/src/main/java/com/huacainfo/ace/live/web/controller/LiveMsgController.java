package com.huacainfo.ace.live.web.controller;

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
import com.huacainfo.ace.live.model.LiveMsg;
import com.huacainfo.ace.live.service.LiveMsgService;
import com.huacainfo.ace.live.vo.LiveMsgVo;
import com.huacainfo.ace.live.vo.LiveMsgQVo;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Controller
@RequestMapping("/liveMsg")
/**
 * @author: 陈晓克
 * @version: 2018-01-03
 * @Description: TODO(图文直播)
 */
public class LiveMsgController extends LiveBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LiveMsgService liveMsgService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(图文直播分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<LiveMsgVo>
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @RequestMapping(value = "/findLiveMsgLists")
    @ResponseBody
    public PageResult<LiveMsgVo> findLiveMsgLists(LiveMsgQVo condition,
                                                 PageParamNoChangeSord page) throws Exception {
        condition.setDeptId(this.getCurUserProp().getCorpId());
        PageResult<LiveMsgVo> rst = this.liveMsgService
                .findLiveMsgList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
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
    @RequestMapping(value = "/insertLiveMsg")
    @ResponseBody
    public MessageResponse insertLiveMsg(String jsons) throws Exception {
        LiveMsg obj = JSON.parseObject(jsons, LiveMsg.class);
        return this.liveMsgService
                .insertLiveMsg(obj);
    }

    /**
     * @throws
     * @Title:updateLiveMsg
     * @Description: TODO(更新图文直播)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @RequestMapping(value = "/updateLiveMsg")
    @ResponseBody
    public MessageResponse updateLiveMsg(String id, String status, String message, String rid) throws Exception {
        if (status != null && status.equals("2")) {
            SingleResult<LiveMsgVo> rst=this.liveMsgService.selectLiveMsgByPrimaryKey(id);

            if (MyWebSocket.rooms.get(rid) == null) {
                CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();
                MyWebSocket.rooms.put(rid, webSocketSet);
                logger.debug("create new room rid:{}", rid);
            }
            for (MyWebSocket item : MyWebSocket.rooms.get(rid)) {
                try {
                    item.sendMessage(rst.getValue().getContent());
                } catch (IOException e) {
                    logger.error(e.getMessage());
                    continue;
                }
            }
        }
        return this.liveMsgService
                .updateLiveMsg(id, status);
    }

    /**
     * @throws
     * @Title:selectLiveMsgByPrimaryKey
     * @Description: TODO(获取图文直播)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LiveMsg>
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @RequestMapping(value = "/selectLiveMsgByPrimaryKey")
    @ResponseBody
    public SingleResult<LiveMsgVo> selectLiveMsgByPrimaryKey(String id)
            throws Exception {
        return this.liveMsgService.selectLiveMsgByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteLiveMsgByLiveMsgId
     * @Description: TODO(删除图文直播)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @RequestMapping(value = "/deleteLiveMsgByLiveMsgId")
    @ResponseBody
    public MessageResponse deleteLiveMsgByLiveMsgId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.liveMsgService.deleteLiveMsgByLiveMsgId(id,
                this.getCurUserProp());
    }
    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(审核)
     * @param: @param id
     * @param status
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-18
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id,String status) throws Exception {
        MessageResponse rst =this.liveMsgService.updateStatus(id,status);
        LiveMsgVo o=this.liveMsgService.selectLiveMsgByPrimaryKey(id).getValue();
        this.cls(this.createMessage("reload.msg"),o.getRid());
        return rst;
    }
    private  String createMessage(String cmd){
       return  "{\"header\":{\"cmd\":\""+cmd+"\"},\"message\":{}}";
    }

    @RequestMapping(value = "/cls")
    @ResponseBody
    public MessageResponse cls(String message, String rid) throws Exception {
        logger.debug("{} {}", rid, message);
        //群发指令
        if (MyWebSocket.rooms.get(rid) == null) {
            CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();
            MyWebSocket.rooms.put(rid, webSocketSet);
            logger.debug("create new room rid:{}", rid);
        }
        for (MyWebSocket  item : MyWebSocket.rooms.get(rid)) {
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

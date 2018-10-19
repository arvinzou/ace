package com.huacainfo.ace.live.web.controller;

import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.live.service.LiveRptService;
import com.huacainfo.ace.live.service.LiveService;
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
import com.huacainfo.ace.live.model.LiveCmt;
import com.huacainfo.ace.live.service.LiveCmtService;
import com.huacainfo.ace.live.vo.LiveCmtVo;
import com.huacainfo.ace.live.vo.LiveCmtQVo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@Controller
@RequestMapping("/liveCmt")
/**
 * @author: 陈晓克
 * @version: 2018-01-13
 * @Description: TODO(评论)
 */
public class LiveCmtController extends LiveBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LiveCmtService liveCmtService;

    @Autowired
    private LiveRptService liveRptService;
    @Autowired
    private KafkaProducerService kafkaProducerService;




    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评论分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<LiveCmtVo>
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    @RequestMapping(value = "/findLiveCmtList")
    @ResponseBody
    public PageResult<LiveCmtVo> findLiveCmtList(LiveCmtQVo condition,
                                                 PageParamNoChangeSord page) throws Exception {
        condition.setDeptId(this.getCurUserProp().getCorpId());
        PageResult<LiveCmtVo> rst = this.liveCmtService
                .findLiveCmtList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertLiveCmt
     * @Description: TODO(添加评论)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    @RequestMapping(value = "/insertLiveCmt")
    @ResponseBody
    public MessageResponse insertLiveCmt(String jsons) throws Exception {
        LiveCmt obj = JSON.parseObject(jsons, LiveCmt.class);
        return this.liveCmtService.insertLiveCmt(obj);
    }

    /**
     * @throws
     * @Title:updateLiveCmt
     * @Description: TODO(更新评论)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    @RequestMapping(value = "/updateLiveCmt")
    @ResponseBody
    public MessageResponse updateLiveCmt(String id, String status, String message, String rptId) throws Exception {
        return this.liveCmtService.updateLiveCmt(id, status);
    }

    /**
     * @throws
     * @Title:selectLiveCmtByPrimaryKey
     * @Description: TODO(获取评论)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LiveCmt>
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    @RequestMapping(value = "/selectLiveCmtByPrimaryKey")
    @ResponseBody
    public SingleResult<LiveCmtVo> selectLiveCmtByPrimaryKey(String id)
            throws Exception {
        return this.liveCmtService.selectLiveCmtByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteLiveCmtByLiveCmtId
     * @Description: TODO(删除评论)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    @RequestMapping(value = "/deleteLiveCmtByLiveCmtId")
    @ResponseBody
    public MessageResponse deleteLiveCmtByLiveCmtId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.liveCmtService.deleteLiveCmtByLiveCmtId(id, this.getCurUserProp());
    }

    /**
     * @param status
     * @throws
     * @Title:updateStatus
     * @Description: TODO(审核)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-18
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        MessageResponse rst = this.liveCmtService.updateStatus(id, status);
        String rptId = this.liveCmtService.selectLiveCmtByPrimaryKey(id).getValue().getRptId();
        String rid = liveRptService.selectLiveRptByPrimaryKey(rptId).getValue().getRid();
        this.cls(this.createMessage("reload.rpt"), rid);
        return rst;
    }

    /**
     * @throws
     * @Title:insertLiveCmt
     * @Description: TODO(添加评论)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    @RequestMapping(value = "/www/insertLiveCmt")
    @ResponseBody
    public MessageResponse insertLiveCmtWww(String jsons) throws Exception {

        Map<String, String> data = new HashMap<String, String>();
        data.put("jsons", jsons);
        data.put("service","cmt");
        this.logger.info("{}", data);
        this.kafkaProducerService.sendMsg("topic.sys.msg.live", data);
        return  new MessageResponse(0,"OK");
    }

    private String createMessage(String cmd) {
        return "{\"header\":{\"cmd\":\"" + cmd + "\"},\"message\":{}}";
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

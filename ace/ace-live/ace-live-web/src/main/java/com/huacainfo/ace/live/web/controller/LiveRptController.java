package com.huacainfo.ace.live.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.live.model.Live;
import com.huacainfo.ace.live.model.LiveImg;
import com.huacainfo.ace.live.web.websocket.MyWebSocket;
import com.huacainfo.ace.portal.service.AuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
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
import java.util.concurrent.CopyOnWriteArraySet;

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


    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private RedisOperations<String, Object> redisTemplate;

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
    @RequestMapping(value = "/findLiveRptList")
    @ResponseBody
    public PageResult<LiveRptVo> findLiveRptList(LiveRptQVo condition, PageParamNoChangeSord page) throws Exception {
        condition.setDeptId(this.getCurUserProp().getCorpId());
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
    @RequestMapping(value = "/insertLiveRpt")
    @ResponseBody
    public MessageResponse insertLiveRpt(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        LiveRpt obj = JSON.parseObject(((JSONObject) json.get("rpt")).toJSONString(), LiveRpt.class);
        List<LiveImg> imgs = JSON.parseArray(((JSONArray) json.get("imgs")).toJSONString(), LiveImg.class);
        return this.liveRptService.insertLiveRpt(obj, imgs);
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
    @RequestMapping(value = "/updateLiveRpt")
    @ResponseBody
    public MessageResponse updateLiveRpt(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        LiveRpt obj = JSON.parseObject(((JSONObject) json.get("rpt")).toJSONString(), LiveRpt.class);
        List<LiveImg> imgs = JSON.parseArray(((JSONArray) json.get("imgs")).toJSONString(), LiveImg.class);
        return this.liveRptService.updateLiveRpt(obj,imgs);
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
    @RequestMapping(value = "/updateLiveRptStatus")
    @ResponseBody
    public MessageResponse updateLiveRptStatus(String id, String status) throws Exception {
        MessageResponse rst= this.liveRptService.updateLiveRptStatus(id, status);
        if (0==rst.getStatus()) {
            LiveRptVo o=this.liveRptService.selectLiveRptByPrimaryKey(id).getValue();
            this.cls(this.createMessage("reload.rpt"),o.getRid());
        }
        return rst;
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
    @RequestMapping(value = "/selectLiveRptByPrimaryKey")
    @ResponseBody
    public SingleResult<LiveRptVo> selectLiveRptByPrimaryKey(String id) throws Exception {
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
    @RequestMapping(value = "/deleteLiveRptByLiveRptId")
    @ResponseBody
    public MessageResponse deleteLiveRptByLiveRptId(String id) throws Exception {
        return this.liveRptService.deleteLiveRptByLiveRptId(id,
                this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:deleteLiveRptByLiveRptId
     * @Description: TODO(删除图和文直播)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @RequestMapping(value = "/deleteLiveRptAndImgLiveByRptId")
    @ResponseBody
    public MessageResponse deleteLiveRptAndImgLiveByRptId(String id) throws Exception {
        return this.liveRptService.deleteLiveRptAndImgLiveByRptId(id,this.getCurUserProp());
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
    @RequestMapping(value = "/sendLiveRptContentBySortAct")
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
    @RequestMapping(value = "/updateSequence")
    @ResponseBody
    public MessageResponse updateSequence(String data,String rid,String message) throws Exception {
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
        return liveRptService.updateSequence(data);
    }


    /**
     * @throws
     * @Title:updateAudit
     * @Description: TODO(审核)
     * @param: @param id
     * @param rst
     * @param text
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-18
     */
    @RequestMapping(value = "/updateAudit")
    @ResponseBody
    public MessageResponse updateAudit(String id,String rst,String text) throws Exception {
        LiveRptVo o=this.liveRptService.selectLiveRptByPrimaryKey(id).getValue();

        MessageResponse response=this.liveRptService.updateAudit(id,rst,text,this.getCurUserProp());

        this.cls(this.createMessage("reload.rpt"),o.getRid());
        return  response;

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
    @RequestMapping(value = "/www/insertLiveRpt")
    @ResponseBody
    public MessageResponse insertLiveRptWww(String jsons) throws Exception {
        JSONObject o=JSON.parseObject(jsons);
        String captcha=o.getString("captcha");
        String _3rd_session=this.getRequest().getHeader("WX-SESSION-ID");
        String j_captcha_weui=(String) this.redisTemplate.opsForValue().get(_3rd_session + "j_captcha_weui");
        this.logger.info("captcha->{}",captcha);
        this.logger.info("j_captcha_weui->{}",j_captcha_weui);
        if(CommonUtils.isBlank(captcha)){
            return new MessageResponse(1,"验证码不能为空！");
        }
        if(!captcha.equals(j_captcha_weui)){
            return new MessageResponse(1,"验证码错误！");
        }
        SingleResult<UserProp> rst=authorityService.getCurUserPropByOpenId(this.getCurWxUser().getUnionId());
        if(rst.getStatus()==0){
            JSONObject json = JSON.parseObject(jsons);
            json.put("uid",this.getCurWxUser().getUnionId());
            LiveRpt obj = JSON.parseObject(((JSONObject) json.get("rpt")).toJSONString(), LiveRpt.class);
            List<LiveImg> imgs = JSON.parseArray(((JSONArray) json.get("imgs")).toJSONString(), LiveImg.class);
            return this.liveRptService.insertLiveRpt(obj, imgs);
        }
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

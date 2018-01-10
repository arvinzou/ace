package com.huacainfo.ace.live.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonKeys;
import com.huacainfo.ace.common.tools.CommonTreeUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.live.model.Live;
import com.huacainfo.ace.live.model.LiveMsg;
import com.huacainfo.ace.live.model.LiveSub;
import com.huacainfo.ace.live.service.LiveMsgService;
import com.huacainfo.ace.live.service.LiveSubService;
import com.huacainfo.ace.live.service.WWWService;
import com.huacainfo.ace.live.vo.LiveQVo;
import com.huacainfo.ace.live.vo.LiveVo;
import com.huacainfo.ace.live.web.websocket.WebSocketSub;
import com.huacainfo.ace.portal.service.DepartmentService;
import com.huacainfo.ace.portal.vo.DepartmentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.Session;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.HashMap;
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

    @Autowired
    private RedisOperations<String, Object> redisTemplate;


    @Autowired
    private DepartmentService departmentService;

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
    @RequestMapping(value = "/getListByCompany.do")
    @ResponseBody
    public Map<String, Object> getListByCompany(int page, String deptId) throws Exception {
        Map<String, Object> p = this.getPageParam(page, this.getParams());
        Map<String, Object> rst = this.wwwService.getTotalNumAndOrgInfo(deptId);
        Long totalNum = (Long) rst.get("totalNum");
        Long totalpage = new Long(1);
        if (totalNum % this.defaultPageSize == 0) {
            totalpage = totalNum / this.defaultPageSize;
        } else {
            totalpage = (totalNum / this.defaultPageSize) + 1;
        }
        rst.put("data", this.wwwService.getLiveList(p));
        rst.put("currentpage", page);
        rst.put("pagecount", totalNum);
        rst.put("status", 0);
        rst.put("totalcount", totalNum);
        rst.put("totalpage", totalpage);
        return rst;
    }

    @RequestMapping(value = "/getTotalNumAndOrgInfo.do")
    @ResponseBody
    public Map<String, Object> getTotalNumAndOrgInfo(String deptId) throws Exception {
        Map<String, Object> rst = new HashMap<>();
        rst.put("data", this.wwwService.getTotalNumAndOrgInfo(deptId));
        rst.put("errCode", "get_info_succeed");
        rst.put("errMsg", "获取现场总数和组织信息成功");
        rst.put("status", 0);
        return rst;
    }

    @RequestMapping(value = "/getShareContent.do")
    @ResponseBody
    public Map<String, Object> getShareContent(String deptId) throws Exception {
        Map<String, Object> rst = new HashMap<>();
        rst.put("data", this.wwwService.getShareContent(deptId));
        rst.put("status", 0);
        return rst;
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
        Map<String, Object> rst = new HashMap<>();
        rst.put("data", this.wwwService.getLive(id));
        rst.put("status", "0");
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
        for (WebSocketSub item : WebSocketSub.rooms.get(rid)) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                logger.error(e.getMessage());
                continue;
            }
        }
        return new MessageResponse(0, "OK");
    }

    /**
     * @throws
     * @Title:pop
     * @Description: TODO(微网页直播点赞)
     * @param:
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-01-07
     */
    @RequestMapping(value = "/pop.do")
    @ResponseBody
    public SingleResult<Long> pop(String rid) throws Exception {
        SingleResult<Long> rst = new SingleResult(0, "OK");
        logger.debug("{}", rid);
        String keypop = rid + ".pop";
        Long pop = (Long) this.redisTemplate.opsForValue().get(keypop);
        if (pop == null) {
            pop = new Long(0);
            this.redisTemplate.opsForValue().set(keypop, pop);
        }
        this.redisTemplate.opsForValue().set(keypop, new Long(pop + 1));
        rst.setValue(pop);
        return rst;
    }

    /**
     * @throws
     * @Title:getLiveSubList
     * @Description: TODO(微网页根据直播间RID获取图文直播内容)
     * @param:
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-01-07
     */
    @RequestMapping(value = "/getLiveSubList.do")
    @ResponseBody
    public List<Map<String, Object>> getLiveSubList() {
        return this.wwwService.getLiveSubList(this.getParams());
    }

    /**
     * @throws
     * @Title:getLiveMsgList
     * @Description: TODO(微网页根据直播间RID获取互动内容)
     * @param:
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-01-07
     */
    @RequestMapping(value = "/getLiveMsgList.do")
    @ResponseBody
    public List<Map<String, Object>> getLiveMsgList() {
        return this.wwwService.getLiveMsgList(this.getParams());
    }
}

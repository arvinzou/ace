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
import com.huacainfo.ace.live.model.LiveSub;
import com.huacainfo.ace.live.service.LiveSubService;
import com.huacainfo.ace.live.vo.LiveSubVo;
import com.huacainfo.ace.live.vo.LiveSubQVo;

import java.io.IOException;

@Controller
@RequestMapping("/liveSub")
/**
 * @author: 陈晓克
 * @version: 2018-01-03
 * @Description: TODO(图文直播)
 */
public class LiveSubController extends LiveBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LiveSubService liveSubService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(图文直播分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<LiveSubVo>
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @RequestMapping(value = "/findLiveSubList.do")
    @ResponseBody
    public PageResult<LiveSubVo> findLiveSubList(LiveSubQVo condition,
                                                 PageParamNoChangeSord page) throws Exception {
        PageResult<LiveSubVo> rst = this.liveSubService
                .findLiveSubList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
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

    /**
     * @throws
     * @Title:updateLiveSub
     * @Description: TODO(更新图文直播)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @RequestMapping(value = "/updateLiveSub.do")
    @ResponseBody
    public MessageResponse updateLiveSub(String id, String status, String message, String rid) throws Exception {
        if (status != null && status.equals("2")) {
            for (MyWebSocket item : MyWebSocket.rooms.get(rid)) {
                try {
                    item.sendMessage(message);
                } catch (IOException e) {
                    logger.error(e.getMessage());
                    continue;
                }
            }
        }

        return this.liveSubService.updateLiveSub(id, status);
    }

    /**
     * @throws
     * @Title:selectLiveSubByPrimaryKey
     * @Description: TODO(获取图文直播)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LiveSub>
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @RequestMapping(value = "/selectLiveSubByPrimaryKey.do")
    @ResponseBody
    public SingleResult<LiveSubVo> selectLiveSubByPrimaryKey(String id)
            throws Exception {
        return this.liveSubService.selectLiveSubByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteLiveSubByLiveSubId
     * @Description: TODO(删除图文直播)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @RequestMapping(value = "/deleteLiveSubByLiveSubId.do")
    @ResponseBody
    public MessageResponse deleteLiveSubByLiveSubId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.liveSubService.deleteLiveSubByLiveSubId(id,
                this.getCurUserProp());
    }
}

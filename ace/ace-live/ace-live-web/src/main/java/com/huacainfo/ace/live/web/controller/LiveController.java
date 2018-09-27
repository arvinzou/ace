package com.huacainfo.ace.live.web.controller;

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
import com.huacainfo.ace.live.model.Live;
import com.huacainfo.ace.live.service.LiveService;
import com.huacainfo.ace.live.vo.LiveVo;
import com.huacainfo.ace.live.vo.LiveQVo;

@Controller
@RequestMapping("/live")
/**
 * @author: 陈晓克
 * @version: 2017-12-27
 * @Description: TODO(直播)
 */
public class LiveController extends LiveBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LiveService liveService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(直播分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<LiveVo>
     * @author: 陈晓克
     * @version: 2017-12-27
     */
    @RequestMapping(value = "/findLiveList")
    @ResponseBody
    public PageResult<LiveVo> findLiveList(LiveQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<LiveVo> rst = this.liveService.findLiveList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        condition.setDeptId(this.getCurUserProp().getCorpId());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertLive
     * @Description: TODO(添加直播)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2017-12-27
     */
    @RequestMapping(value = "/insertLive")
    @ResponseBody
    public MessageResponse insertLive(String jsons) throws Exception {
        Live obj = JSON.parseObject(jsons, Live.class);

        return this.liveService
                .insertLive(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateLive
     * @Description: TODO(更新直播)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2017-12-27
     */
    @RequestMapping(value = "/updateLive")
    @ResponseBody
    public MessageResponse updateLive(String jsons) throws Exception {
        Live obj = JSON.parseObject(jsons, Live.class);
        return this.liveService.updateLive(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectLiveByPrimaryKey
     * @Description: TODO(获取直播)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Live>
     * @author: 陈晓克
     * @version: 2017-12-27
     */
    @RequestMapping(value = "/selectLiveByPrimaryKey")
    @ResponseBody
    public SingleResult<LiveVo> selectLiveByPrimaryKey(String id)
            throws Exception {
        return this.liveService.selectLiveByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteLiveByLiveId
     * @Description: TODO(删除直播)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2017-12-27
     */
    @RequestMapping(value = "/deleteLiveByLiveId")
    @ResponseBody
    public MessageResponse deleteLiveByLiveId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.liveService.deleteLiveByLiveId(id,
                this.getCurUserProp());
    }



    /**
     * @throws
     * @Title:updateLiveAuditByLiveId
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
    @RequestMapping(value = "/updateLiveAuditByLiveId")
    @ResponseBody
    public MessageResponse updateLiveAuditByLiveId(String id,String rst,String text) throws Exception {

        return this.liveService.updateLiveAuditByLiveId(id,rst,text,this.getCurUserProp());
    }
    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(设置直播状态1预告2直播中3结束)
     * @param: @param id
     * @param status
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-18
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id,String status) throws Exception {
        return this.liveService.updateStatus(id,status,this.getCurUserProp());
    }
}

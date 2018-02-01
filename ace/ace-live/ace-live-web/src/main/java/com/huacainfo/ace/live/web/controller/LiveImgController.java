package com.huacainfo.ace.live.web.controller;

import com.huacainfo.ace.live.vo.LiveQVo;
import com.huacainfo.ace.live.vo.LiveVo;
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
import com.huacainfo.ace.live.model.LiveImg;
import com.huacainfo.ace.live.service.LiveImgService;
import com.huacainfo.ace.live.vo.LiveImgVo;
import com.huacainfo.ace.live.vo.LiveImgQVo;

@Controller
@RequestMapping("/liveImg")
/**
 * @author: 陈晓克
 * @version: 2018-01-13
 * @Description: TODO(图片)
 */
public class LiveImgController extends LiveBaseController {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LiveImgService liveImgService;

    /**
     * @throws
     * @Title:insertLiveImg
     * @Description: TODO(添加图片)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    @RequestMapping(value = "/insertLiveImg.do")
    @ResponseBody
    public MessageResponse insertLiveImg(String jsons) throws Exception {
        LiveImg obj = JSON.parseObject(jsons, LiveImg.class);
        return this.liveImgService.insertLiveImg(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:deleteLiveImgByLiveImgId
     * @Description: TODO(删除图片)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    @RequestMapping(value = "/deleteLiveImgByLiveImgId.do")
    @ResponseBody
    public MessageResponse deleteLiveImgByLiveImgId(String id)
            throws Exception {
        return this.liveImgService.deleteLiveImgByLiveImgId(id,
                this.getCurUserProp());
    }

    /**
     * TODO（获取图片）
     * @param id
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/selectLiveImgByPrimaryKey.do")
    @ResponseBody
    public MessageResponse selectLiveImgByPrimaryKey(String id)
            throws Exception {
        return this.liveImgService.selectLiveImgByPrimaryKey(id);
    }

    @RequestMapping(value = "/findLiveImgList.do")
    @ResponseBody
    public PageResult<LiveImgVo> findLiveImgList(LiveImgQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<LiveImgVo> rst = this.liveImgService.findLiveImgList(condition, page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

}

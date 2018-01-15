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
import com.huacainfo.ace.live.model.LiveCmt;
import com.huacainfo.ace.live.service.LiveCmtService;
import com.huacainfo.ace.live.vo.LiveCmtVo;
import com.huacainfo.ace.live.vo.LiveCmtQVo;

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
    @RequestMapping(value = "/findLiveCmtList.do")
    @ResponseBody
    public PageResult<LiveCmtVo> findLiveCmtList(LiveCmtQVo condition,
                                                 PageParamNoChangeSord page) throws Exception {
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
    @RequestMapping(value = "/insertLiveCmt.do")
    @ResponseBody
    public MessageResponse insertLiveCmt(String jsons) throws Exception {
        LiveCmt obj = JSON.parseObject(jsons, LiveCmt.class);
        return this.liveCmtService
                .insertLiveCmt(obj);
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
    @RequestMapping(value = "/updateLiveCmt.do")
    @ResponseBody
    public MessageResponse updateLiveCmt(String jsons) throws Exception {
        LiveCmt obj = JSON.parseObject(jsons, LiveCmt.class);
        return this.liveCmtService
                .updateLiveCmt(obj, this.getCurUserProp());
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
    @RequestMapping(value = "/selectLiveCmtByPrimaryKey.do")
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
    @RequestMapping(value = "/deleteLiveCmtByLiveCmtId.do")
    @ResponseBody
    public MessageResponse deleteLiveCmtByLiveCmtId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.liveCmtService.deleteLiveCmtByLiveCmtId(id,
                this.getCurUserProp());
    }
}

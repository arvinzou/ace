package com.huacainfo.ace.society.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.FeedBack;
import com.huacainfo.ace.society.service.FeedBackService;
import com.huacainfo.ace.society.vo.FeedBackQVo;
import com.huacainfo.ace.society.vo.FeedBackVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/feedBack")
/**
 * @author: Arvin
 * @version: 2018-11-27
 * @Description: TODO(问题反馈)
 */
public class FeedBackController extends SocietyBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FeedBackService feedBackService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(问题反馈分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <FeedBackVo>
     * @author: Arvin
     * @version: 2018-11-27
     */
    @RequestMapping(value = "/findFeedBackList")
    @ResponseBody
    public PageResult<FeedBackVo> findFeedBackList(FeedBackQVo condition,
                                                   PageParamNoChangeSord page) throws Exception {

        PageResult<FeedBackVo> rst = this.feedBackService
                .findFeedBackList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertFeedBack
     * @Description: TODO(添加问题反馈)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-27
     */
    @RequestMapping(value = "/insertFeedBack")
    @ResponseBody
    public MessageResponse insertFeedBack(String jsons) throws Exception {
        FeedBack obj = JSON.parseObject(jsons, FeedBack.class);
        return this.feedBackService.insertFeedBack(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateFeedBack
     * @Description: TODO(更新问题反馈)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-27
     */
    @RequestMapping(value = "/updateFeedBack")
    @ResponseBody
    public MessageResponse updateFeedBack(String jsons) throws Exception {
        FeedBack obj = JSON.parseObject(jsons, FeedBack.class);
        return this.feedBackService.updateFeedBack(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFeedBackByPrimaryKey
     * @Description: TODO(获取问题反馈)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FeedBack>
     * @author: Arvin
     * @version: 2018-11-27
     */
    @RequestMapping(value = "/selectFeedBackByPrimaryKey")
    @ResponseBody
    public SingleResult<FeedBackVo> selectFeedBackByPrimaryKey(String id) throws Exception {
        return this.feedBackService.selectFeedBackByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteFeedBackByFeedBackId
     * @Description: TODO(删除问题反馈)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-27
     */
    @RequestMapping(value = "/deleteFeedBackByFeedBackId")
    @ResponseBody
    public MessageResponse deleteFeedBackByFeedBackId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.feedBackService.deleteFeedBackByFeedBackId(id, this.getCurUserProp());
    }


}

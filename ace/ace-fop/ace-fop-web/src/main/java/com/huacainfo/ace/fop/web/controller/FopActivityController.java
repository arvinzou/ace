package com.huacainfo.ace.fop.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.fop.model.FopActivity;
import com.huacainfo.ace.fop.service.FopActivityService;
import com.huacainfo.ace.fop.vo.FopActivityQVo;
import com.huacainfo.ace.fop.vo.FopActivityVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fopActivity")
/**
 * @author: Arvin
 * @version: 2018-05-09
 * @Description: TODO(企业/协会活动)
 */
public class FopActivityController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopActivityService fopActivityService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业/协会活动分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <FopActivityVo>
     * @author: Arvin
     * @version: 2018-05-09
     */
    @RequestMapping(value = "/findFopActivityList")
    @ResponseBody
    public PageResult<FopActivityVo> findFopActivityList(FopActivityQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<FopActivityVo> rst = this.fopActivityService
                .findFopActivityList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertFopActivity
     * @Description: TODO(添加企业/协会活动)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-09
     */
    @RequestMapping(value = "/insertFopActivity")
    @ResponseBody
    public MessageResponse insertFopActivity(String jsons) throws Exception {
        FopActivity obj = JSON.parseObject(jsons, FopActivity.class);
        return this.fopActivityService.insertFopActivity(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateFopActivity
     * @Description: TODO(更新企业/协会活动)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-09
     */
    @RequestMapping(value = "/updateFopActivity")
    @ResponseBody
    public MessageResponse updateFopActivity(String jsons) throws Exception {
        FopActivity obj = JSON.parseObject(jsons, FopActivity.class);
        return this.fopActivityService.updateFopActivity(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFopActivityByPrimaryKey
     * @Description: TODO(获取企业/协会活动)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopActivity>
     * @author: Arvin
     * @version: 2018-05-09
     */
    @RequestMapping(value = "/selectFopActivityByPrimaryKey")
    @ResponseBody
    public SingleResult<FopActivityVo> selectFopActivityByPrimaryKey(String id) throws Exception {
        return this.fopActivityService.selectFopActivityByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteFopActivityByFopActivityId
     * @Description: TODO(删除企业/协会活动)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-09
     */
    @RequestMapping(value = "/deleteFopActivityByFopActivityId")
    @ResponseBody
    public MessageResponse deleteFopActivityByFopActivityId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.fopActivityService.deleteFopActivityByFopActivityId(id, this.getCurUserProp());
    }


    /**
     * 功能描述:  发布审核
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/9 15:12
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id) throws Exception {
        if (CommonUtils.isEmpty(id)) {
            return new MessageResponse(ResultCode.FAIL, "缺少必备参数");
        }

        return this.fopActivityService.audit(id, getCurUserProp());
    }
}

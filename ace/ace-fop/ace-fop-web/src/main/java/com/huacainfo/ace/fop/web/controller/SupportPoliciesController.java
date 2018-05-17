package com.huacainfo.ace.fop.web.controller;

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
import com.huacainfo.ace.fop.model.SupportPolicies;
import com.huacainfo.ace.fop.service.SupportPoliciesService;
import com.huacainfo.ace.fop.vo.SupportPoliciesVo;
import com.huacainfo.ace.fop.vo.SupportPoliciesQVo;

@Controller
@RequestMapping("/supportPolicies")
/**
 * @author: huacai003
 * @version: 2018-05-17
 * @Description: TODO(扶持政策)
 */
public class SupportPoliciesController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SupportPoliciesService supportPoliciesService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(扶持政策分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <SupportPoliciesVo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/findSupportPoliciesList")
    @ResponseBody
    public PageResult
            <SupportPoliciesVo> findSupportPoliciesList(SupportPoliciesQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult
                <SupportPoliciesVo> rst = this.supportPoliciesService
                .findSupportPoliciesList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertSupportPolicies
     * @Description: TODO(添加扶持政策)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/insertSupportPolicies")
    @ResponseBody
    public MessageResponse insertSupportPolicies(String jsons) throws Exception {
        SupportPolicies obj = JSON.parseObject(jsons, SupportPolicies.class);
        return this.supportPoliciesService.insertSupportPolicies(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateSupportPolicies
     * @Description: TODO(更新扶持政策)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/updateSupportPolicies")
    @ResponseBody
    public MessageResponse updateSupportPolicies(String jsons) throws Exception {
        SupportPolicies obj = JSON.parseObject(jsons, SupportPolicies.class);
        return this.supportPoliciesService.updateSupportPolicies(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectSupportPoliciesByPrimaryKey
     * @Description: TODO(获取扶持政策)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SupportPolicies>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/selectSupportPoliciesByPrimaryKey")
    @ResponseBody
    public SingleResult
            <SupportPoliciesVo> selectSupportPoliciesByPrimaryKey(String id) throws Exception {
        return this.supportPoliciesService.selectSupportPoliciesByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteSupportPoliciesBySupportPoliciesId
     * @Description: TODO(删除扶持政策)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/deleteSupportPoliciesBySupportPoliciesId")
    @ResponseBody
    public MessageResponse deleteSupportPoliciesBySupportPoliciesId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.supportPoliciesService.deleteSupportPoliciesBySupportPoliciesId(id, this.getCurUserProp());
    }
}

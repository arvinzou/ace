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
import com.huacainfo.ace.fop.model.FopGeHelp;
import com.huacainfo.ace.fop.service.FopGeHelpService;
import com.huacainfo.ace.fop.vo.FopGeHelpVo;
import com.huacainfo.ace.fop.vo.FopGeHelpQVo;

@Controller
@RequestMapping("/fopGeHelp")
/**
 * @author: Arvin
 * @version: 2018-05-09
 * @Description: TODO(企业/协会活动)
 */
public class FopGeHelpController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopGeHelpService fopGeHelpService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业/协会活动分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <FopGeHelpVo>
     * @author: Arvin
     * @version: 2018-05-09
     */
    @RequestMapping(value = "/findFopGeHelpList")
    @ResponseBody
    public PageResult
            <FopGeHelpVo> findFopGeHelpList(FopGeHelpQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult
                <FopGeHelpVo> rst = this.fopGeHelpService
                .findFopGeHelpList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertFopGeHelp
     * @Description: TODO(添加企业/协会活动)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-09
     */
    @RequestMapping(value = "/insertFopGeHelp")
    @ResponseBody
    public MessageResponse insertFopGeHelp(String jsons) throws Exception {
        FopGeHelp obj = JSON.parseObject(jsons, FopGeHelp.class);
        return this.fopGeHelpService.insertFopGeHelp(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateFopGeHelp
     * @Description: TODO(更新企业/协会活动)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-09
     */
    @RequestMapping(value = "/updateFopGeHelp")
    @ResponseBody
    public MessageResponse updateFopGeHelp(String jsons) throws Exception {
        FopGeHelp obj = JSON.parseObject(jsons, FopGeHelp.class);
        return this.fopGeHelpService.updateFopGeHelp(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFopGeHelpByPrimaryKey
     * @Description: TODO(获取企业/协会活动)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopGeHelp>
     * @author: Arvin
     * @version: 2018-05-09
     */
    @RequestMapping(value = "/selectFopGeHelpByPrimaryKey")
    @ResponseBody
    public SingleResult
            <FopGeHelpVo> selectFopGeHelpByPrimaryKey(String id) throws Exception {
        return this.fopGeHelpService.selectFopGeHelpByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteFopGeHelpByFopGeHelpId
     * @Description: TODO(删除企业/协会活动)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-09
     */
    @RequestMapping(value = "/deleteFopGeHelpByFopGeHelpId")
    @ResponseBody
    public MessageResponse deleteFopGeHelpByFopGeHelpId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.fopGeHelpService.deleteFopGeHelpByFopGeHelpId(id, this.getCurUserProp());
    }
}

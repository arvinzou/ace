package com.huacainfo.ace.iop.web.controller;

import org.apache.log4j.Logger;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.iop.model.EvCategory;
import com.huacainfo.ace.iop.vo.EvCategoryQVo;
import com.huacainfo.ace.iop.service.EvCategoryService;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.iop.vo.EvCategoryVo;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/evCategory")
public class EvCategoryController extends BaseController {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private EvCategoryService evCategoryService;

    @RequestMapping(value = "/findEvCategoryList")
    @ResponseBody
    public PageResult<EvCategoryVo> findEvCategoryList(EvCategoryQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<EvCategoryVo> rst = this.evCategoryService.findEvCategoryList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    @RequestMapping(value = "/insertEvCategory")
    @ResponseBody
    public MessageResponse insertEvCategory(String jsons) throws Exception{
        EvCategory obj = JSON.parseObject(jsons, EvCategory.class);
        return this.evCategoryService.insertEvCategory(obj, this.getCurUserProp());
    }

    @RequestMapping(value = "/updateEvCategory")
    @ResponseBody
    public MessageResponse updateEvCategory(String jsons) throws Exception{
        EvCategory obj = JSON.parseObject(jsons, EvCategory.class);
        return this.evCategoryService.updateEvCategory(obj, this.getCurUserProp());
    }

    @RequestMapping(value = "/selectEvCategoryByPrimaryKey")
    @ResponseBody
    public SingleResult<EvCategoryVo> selectEvCategoryByPrimaryKey(String id) throws Exception{
        return  this.evCategoryService.selectEvCategoryByPrimaryKey(id);
    }

    @RequestMapping(value = "/deleteEvCategoryByEvCategoryId")
    @ResponseBody
    public MessageResponse deleteEvCategoryByEvCategoryId(String jsons) throws Exception{
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.evCategoryService.deleteEvCategoryByEvCategoryId(id, this.getCurUserProp());
    }

    @RequestMapping(value = "/saveOrUpdateEvCategory")
    @ResponseBody
    public MessageResponse saveOrUpdateTeamPrepare(String jsons) throws Exception{
        EvCategory obj = JSON.parseObject(jsons, EvCategory.class);
        return this.evCategoryService.saveOrUpdateEvCategory(obj, this.getCurUserProp());
    }
}

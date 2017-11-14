package com.huacainfo.ace.uf.web.controller;

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
import com.huacainfo.ace.uf.model.TongXin;
import com.huacainfo.ace.uf.service.TongXinService;
import com.huacainfo.ace.uf.vo.TongXinVo;
import com.huacainfo.ace.uf.vo.TongXinQVo;

@Controller
@RequestMapping("/tongXin")
public class TongXinController extends UfBaseController {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TongXinService tongXinService;

    @RequestMapping(value = "/findTongXinList.do")
    @ResponseBody
    public PageResult<TongXinVo> findTongXinList(TongXinQVo condition,
                                                 PageParamNoChangeSord page) throws Exception {
        PageResult<TongXinVo> rst = this.tongXinService
                .findTongXinList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    @RequestMapping(value = "/insertTongXin.do")
    @ResponseBody
    public MessageResponse insertTongXin(String jsons) throws Exception {
        TongXin obj = JSON.parseObject(jsons, TongXin.class);
        return this.tongXinService
                .insertTongXin(obj, this.getCurUserProp());
    }

    @RequestMapping(value = "/updateTongXin.do")
    @ResponseBody
    public MessageResponse updateTongXin(String jsons) throws Exception {
        TongXin obj = JSON.parseObject(jsons, TongXin.class);
        return this.tongXinService
                .updateTongXin(obj, this.getCurUserProp());
    }

    @RequestMapping(value = "/selectTongXinByPrimaryKey.do")
    @ResponseBody
    public SingleResult<TongXinVo> selectTongXinByPrimaryKey(String id)
            throws Exception {
        return this.tongXinService.selectTongXinByPrimaryKey(id);
    }

    @RequestMapping(value = "/deleteTongXinByTongXinId.do")
    @ResponseBody
    public MessageResponse deleteTongXinByTongXinId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.tongXinService.deleteTongXinByTongXinId(id,
                this.getCurUserProp());
    }
}

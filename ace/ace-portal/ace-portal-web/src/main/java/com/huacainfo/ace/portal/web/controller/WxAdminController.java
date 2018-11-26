package com.huacainfo.ace.portal.web.controller;

import com.huacainfo.ace.common.web.controller.BaseController;
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
import com.huacainfo.ace.portal.model.WxAdmin;
import com.huacainfo.ace.portal.service.WxAdminService;
import com.huacainfo.ace.portal.vo.WxAdminVo;
import com.huacainfo.ace.portal.vo.WxAdminQVo;

@Controller
@RequestMapping("/wxAdmin")
/**
 * @author: 陈晓克
 * @version: 2018-11-26
 * @Description: TODO(管理员列表)
 */
public class WxAdminController extends PortalBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WxAdminService wxAdminService;

    /**
     * Portal
     *
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(管理员列表分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <WxAdminVo>
     * @author: 陈晓克
     * @version: 2018-11-26
     */
    @RequestMapping(value = "/findWxAdminList")
    @ResponseBody
    public PageResult<WxAdminVo> findWxAdminList(WxAdminQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<WxAdminVo> rst = this.wxAdminService.findWxAdminList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertWxAdmin
     * @Description: TODO(添加管理员列表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-26
     */
    @RequestMapping(value = "/insertWxAdmin")
    @ResponseBody
    public MessageResponse insertWxAdmin(String jsons) throws Exception {
        WxAdmin obj = JSON.parseObject(jsons, WxAdmin.class);
        return this.wxAdminService.insertWxAdmin(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateWxAdmin
     * @Description: TODO(更新管理员列表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-26
     */
    @RequestMapping(value = "/updateWxAdmin")
    @ResponseBody
    public MessageResponse updateWxAdmin(String jsons) throws Exception {
        WxAdmin obj = JSON.parseObject(jsons, WxAdmin.class);
        return this.wxAdminService.updateWxAdmin(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectWxAdminByPrimaryKey
     * @Description: TODO(获取管理员列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<WxAdmin>
     * @author: 陈晓克
     * @version: 2018-11-26
     */
    @RequestMapping(value = "/selectWxAdminByPrimaryKey")
    @ResponseBody
    public SingleResult<WxAdminVo> selectWxAdminByPrimaryKey(String id) throws Exception {
        return this.wxAdminService.selectWxAdminByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteWxAdminByWxAdminId
     * @Description: TODO(删除管理员列表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-26
     */
    @RequestMapping(value = "/deleteWxAdminByWxAdminId")
    @ResponseBody
    public MessageResponse deleteWxAdminByWxAdminId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.wxAdminService.deleteWxAdminByWxAdminId(id, this.getCurUserProp());
    }
}

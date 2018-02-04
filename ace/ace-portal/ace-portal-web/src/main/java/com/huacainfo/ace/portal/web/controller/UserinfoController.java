package com.huacainfo.ace.portal.web.controller;

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
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.portal.service.UserinfoService;
import com.huacainfo.ace.portal.vo.UserinfoVo;
import com.huacainfo.ace.portal.vo.UserinfoQVo;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userinfo")
/**
 * @author: 陈晓克
 * @version: 2017-12-29
 * @Description: TODO(微信用户)
 */
public class UserinfoController extends PortalBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserinfoService userinfoService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(微信用户分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<UserinfoVo>
     * @author: 陈晓克
     * @version: 2017-12-29
     */
    @RequestMapping(value = "/findUserinfoList.do")
    @ResponseBody
    public PageResult<UserinfoVo> findUserinfoList(UserinfoQVo condition,
                                                   PageParamNoChangeSord page) throws Exception {
        PageResult<UserinfoVo> rst = this.userinfoService
                .findUserinfoList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertUserinfo
     * @Description: TODO(添加微信用户)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2017-12-29
     */
    @RequestMapping(value = "/insertUserinfo.do")
    @ResponseBody
    public MessageResponse insertUserinfo(String jsons) throws Exception {
        Userinfo obj = JSON.parseObject(jsons, Userinfo.class);
        return this.userinfoService
                .insertUserinfo(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateUserinfo
     * @Description: TODO(更新微信用户)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2017-12-29
     */
    @RequestMapping(value = "/updateUserinfo.do")
    @ResponseBody
    public MessageResponse updateUserinfo(String jsons) throws Exception {
        Userinfo obj = JSON.parseObject(jsons, Userinfo.class);
        return this.userinfoService
                .updateUserinfo(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectUserinfoByPrimaryKey
     * @Description: TODO(获取微信用户)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Userinfo>
     * @author: 陈晓克
     * @version: 2017-12-29
     */
    @RequestMapping(value = "/selectUserinfoByPrimaryKey.do")
    @ResponseBody
    public SingleResult<UserinfoVo> selectUserinfoByPrimaryKey(String id)
            throws Exception {
        return this.userinfoService.selectUserinfoByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteUserinfoByUserinfoId
     * @Description: TODO(删除微信用户)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2017-12-29
     */
    @RequestMapping(value = "/deleteUserinfoByUserinfoId.do")
    @ResponseBody
    public MessageResponse deleteUserinfoByUserinfoId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.userinfoService.deleteUserinfoByUserinfoId(id,
                this.getCurUserProp());
    }

    @RequestMapping(value = "/updateRoleById.do")
    @ResponseBody
    public MessageResponse updateRoleById(String id, String role) throws Exception {
        return this.userinfoService.updateRoleById(id, role, this.getCurUserProp());
    }
    @RequestMapping(value = "/deleteRoleById.do")
    @ResponseBody
    public MessageResponse deleteRoleById(String id) throws Exception {
        return this.userinfoService.deleteRoleById(id, this.getCurUserProp());
    }
    @RequestMapping(value = "/selectWxUser.do")
    @ResponseBody
    public List<Map<String,Object>> selectWxUser()throws Exception{
        return this.userinfoService.selectWxUser(this.getParams());
    }
}

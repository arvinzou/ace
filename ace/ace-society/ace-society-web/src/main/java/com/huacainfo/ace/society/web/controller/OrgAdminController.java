package com.huacainfo.ace.society.web.controller;

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
import com.huacainfo.ace.society.model.OrgAdmin;
import com.huacainfo.ace.society.service.OrgAdminService;
import com.huacainfo.ace.society.vo.OrgAdminVo;
import com.huacainfo.ace.society.vo.OrgAdminQVo;

@Controller
@RequestMapping("/orgAdmin")
/**
 * @author: Arvin
 * @version: 2018-11-19
 * @Description: TODO(组织管理者列表)
 */
public class OrgAdminController extends SocietyBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrgAdminService orgAdminService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(组织管理者列表分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <OrgAdminVo>
     * @author: Arvin
     * @version: 2018-11-19
     */
    @RequestMapping(value = "/findOrgAdminList")
    @ResponseBody
    public PageResult
            <OrgAdminVo> findOrgAdminList(OrgAdminQVo condition,
                                          PageParamNoChangeSord page) throws Exception {

        PageResult
                <OrgAdminVo> rst = this.orgAdminService
                .findOrgAdminList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertOrgAdmin
     * @Description: TODO(添加组织管理者列表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-19
     */
    @RequestMapping(value = "/insertOrgAdmin")
    @ResponseBody
    public MessageResponse insertOrgAdmin(String jsons) throws Exception {
        OrgAdmin obj = JSON.parseObject(jsons, OrgAdmin.class);
        return this.orgAdminService.insertOrgAdmin(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateOrgAdmin
     * @Description: TODO(更新组织管理者列表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-19
     */
    @RequestMapping(value = "/updateOrgAdmin")
    @ResponseBody
    public MessageResponse updateOrgAdmin(String jsons) throws Exception {
        OrgAdmin obj = JSON.parseObject(jsons, OrgAdmin.class);
        return this.orgAdminService.updateOrgAdmin(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectOrgAdminByPrimaryKey
     * @Description: TODO(获取组织管理者列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<OrgAdmin>
     * @author: Arvin
     * @version: 2018-11-19
     */
    @RequestMapping(value = "/selectOrgAdminByPrimaryKey")
    @ResponseBody
    public SingleResult
            <OrgAdminVo> selectOrgAdminByPrimaryKey(String id) throws Exception {
        return this.orgAdminService.selectOrgAdminByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteOrgAdminByOrgAdminId
     * @Description: TODO(删除组织管理者列表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-19
     */
    @RequestMapping(value = "/deleteOrgAdminByOrgAdminId")
    @ResponseBody
    public MessageResponse deleteOrgAdminByOrgAdminId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.orgAdminService.deleteOrgAdminByOrgAdminId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核组织管理者列表)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-19
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.orgAdminService.audit(id, rst, message, this.getCurUserProp());
    }
}

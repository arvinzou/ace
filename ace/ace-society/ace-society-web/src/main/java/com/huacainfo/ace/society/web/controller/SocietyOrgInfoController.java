package com.huacainfo.ace.society.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.SocietyOrgInfo;
import com.huacainfo.ace.society.service.SocietyOrgInfoService;
import com.huacainfo.ace.society.vo.SocietyOrgInfoQVo;
import com.huacainfo.ace.society.vo.SocietyOrgInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/societyOrgInfo")
/**
 * @author: Arvin
 * @version: 2018-09-12
 * @Description: TODO(社会组织信息)
 */
public class SocietyOrgInfoController extends SocietyBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SocietyOrgInfoService societyOrgInfoService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(社会组织信息分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <SocietyOrgInfoVo>
     * @author: Arvin
     * @version: 2018-09-12
     */
    @RequestMapping(value = "/findSocietyOrgInfoList")
    @ResponseBody
    public PageResult
            <SocietyOrgInfoVo> findSocietyOrgInfoList(SocietyOrgInfoQVo condition,
                                                      PageParamNoChangeSord page) throws Exception {

        PageResult
                <SocietyOrgInfoVo> rst = this.societyOrgInfoService
                .findSocietyOrgInfoList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertSocietyOrgInfo
     * @Description: TODO(添加社会组织信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-12
     */
    @RequestMapping(value = "/insertSocietyOrgInfo")
    @ResponseBody
    public MessageResponse insertSocietyOrgInfo(String jsons) throws Exception {
        SocietyOrgInfo obj = JSON.parseObject(jsons, SocietyOrgInfo.class);
        return this.societyOrgInfoService.insertSocietyOrgInfo(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateSocietyOrgInfo
     * @Description: TODO(更新社会组织信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-12
     */
    @RequestMapping(value = "/updateSocietyOrgInfo")
    @ResponseBody
    public MessageResponse updateSocietyOrgInfo(String jsons) throws Exception {
        SocietyOrgInfo obj = JSON.parseObject(jsons, SocietyOrgInfo.class);
        return this.societyOrgInfoService.updateSocietyOrgInfo(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectSocietyOrgInfoByPrimaryKey
     * @Description: TODO(获取社会组织信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SocietyOrgInfo>
     * @author: Arvin
     * @version: 2018-09-12
     */
    @RequestMapping(value = "/selectSocietyOrgInfoByPrimaryKey")
    @ResponseBody
    public SingleResult
            <SocietyOrgInfoVo> selectSocietyOrgInfoByPrimaryKey(String id) throws Exception {
        return this.societyOrgInfoService.selectSocietyOrgInfoByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteSocietyOrgInfoBySocietyOrgInfoId
     * @Description: TODO(删除社会组织信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-12
     */
    @RequestMapping(value = "/deleteSocietyOrgInfoBySocietyOrgInfoId")
    @ResponseBody
    public MessageResponse deleteSocietyOrgInfoBySocietyOrgInfoId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.societyOrgInfoService.deleteSocietyOrgInfoBySocietyOrgInfoId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核社会组织信息)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-12
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.societyOrgInfoService.audit(id, rst, message, this.getCurUserProp());
    }
}

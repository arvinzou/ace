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
import com.huacainfo.ace.fop.model.TalentInfo;
import com.huacainfo.ace.fop.service.TalentInfoService;
import com.huacainfo.ace.fop.vo.TalentInfoVo;
import com.huacainfo.ace.fop.vo.TalentInfoQVo;

@Controller
@RequestMapping("/talentInfo")
/**
 * @author: huacai003
 * @version: 2018-05-17
 * @Description: TODO(人才信息)
 */
public class TalentInfoController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TalentInfoService talentInfoService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(人才信息分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <TalentInfoVo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/findTalentInfoList")
    @ResponseBody
    public PageResult
            <TalentInfoVo> findTalentInfoList(TalentInfoQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult
                <TalentInfoVo> rst = this.talentInfoService
                .findTalentInfoList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertTalentInfo
     * @Description: TODO(添加人才信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/insertTalentInfo")
    @ResponseBody
    public MessageResponse insertTalentInfo(String jsons) throws Exception {
        TalentInfo obj = JSON.parseObject(jsons, TalentInfo.class);
        return this.talentInfoService.insertTalentInfo(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateTalentInfo
     * @Description: TODO(更新人才信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/updateTalentInfo")
    @ResponseBody
    public MessageResponse updateTalentInfo(String jsons) throws Exception {
        TalentInfo obj = JSON.parseObject(jsons, TalentInfo.class);
        return this.talentInfoService.updateTalentInfo(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectTalentInfoByPrimaryKey
     * @Description: TODO(获取人才信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TalentInfo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/selectTalentInfoByPrimaryKey")
    @ResponseBody
    public SingleResult
            <TalentInfoVo> selectTalentInfoByPrimaryKey(String id) throws Exception {
        return this.talentInfoService.selectTalentInfoByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteTalentInfoByTalentInfoId
     * @Description: TODO(删除人才信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/deleteTalentInfoByTalentInfoId")
    @ResponseBody
    public MessageResponse deleteTalentInfoByTalentInfoId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.talentInfoService.deleteTalentInfoByTalentInfoId(id, this.getCurUserProp());
    }
}

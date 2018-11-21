package com.huacainfo.ace.fop.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopAssociation;
import com.huacainfo.ace.fop.service.FopAssociationService;
import com.huacainfo.ace.fop.vo.FopAssociationQVo;
import com.huacainfo.ace.fop.vo.FopAssociationVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fopAssociation")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: 商协会管理
 */
public class FopAssociationController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopAssociationService fopAssociationService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<FopAssociationVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/findFopAssociationList")
    @ResponseBody
    public PageResult<FopAssociationVo> findFopAssociationList(FopAssociationQVo condition,
                                                               PageParamNoChangeSord page) throws Exception {
        PageResult<FopAssociationVo> rst = this.fopAssociationService
                .findFopAssociationList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertFopAssociation
     * @Description: TODO(添加企业管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/insertFopAssociation")
    @ResponseBody
    public MessageResponse insertFopAssociation(String jsons) throws Exception {
        FopAssociation obj = JSON.parseObject(jsons, FopAssociation.class);
        return this.fopAssociationService
                .insertFopAssociation(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateFopAssociation
     * @Description: TODO(更新企业管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/updateFopAssociation")
    @ResponseBody
    public MessageResponse updateFopAssociation(String jsons) throws Exception {
        FopAssociation obj = JSON.parseObject(jsons, FopAssociation.class);
        return this.fopAssociationService
                .updateFopAssociation(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFopAssociationByPrimaryKey
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopAssociation>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/selectFopAssociationByPrimaryKey")
    @ResponseBody
    public SingleResult<FopAssociationVo> selectFopAssociationByPrimaryKey(String id)
            throws Exception {
        return this.fopAssociationService.selectFopAssociationByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteFopAssociationByFopAssociationId
     * @Description: TODO(删除企业管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/deleteFopAssociationByFopAssociationId")
    @ResponseBody
    public MessageResponse deleteFopAssociationByFopAssociationId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.fopAssociationService.deleteFopAssociationByFopAssociationId(id,
                this.getCurUserProp());
    }
}

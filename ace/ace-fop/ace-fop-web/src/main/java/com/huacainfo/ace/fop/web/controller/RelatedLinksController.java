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
import com.huacainfo.ace.fop.model.RelatedLinks;
import com.huacainfo.ace.fop.service.RelatedLinksService;
import com.huacainfo.ace.fop.vo.RelatedLinksVo;
import com.huacainfo.ace.fop.vo.RelatedLinksQVo;

@Controller
@RequestMapping("/relatedLinks")
/**
 * @author: huacai003
 * @version: 2018-05-23
 * @Description: TODO(相关链接)
 */
public class RelatedLinksController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RelatedLinksService relatedLinksService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(相关链接分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <RelatedLinksVo>
     * @author: huacai003
     * @version: 2018-05-23
     */
    @RequestMapping(value = "/findRelatedLinksList")
    @ResponseBody
    public PageResult
            <RelatedLinksVo> findRelatedLinksList(RelatedLinksQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult
                <RelatedLinksVo> rst = this.relatedLinksService
                .findRelatedLinksList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertRelatedLinks
     * @Description: TODO(添加相关链接)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-23
     */
    @RequestMapping(value = "/insertRelatedLinks")
    @ResponseBody
    public MessageResponse insertRelatedLinks(String jsons) throws Exception {
        RelatedLinks obj = JSON.parseObject(jsons, RelatedLinks.class);
        return this.relatedLinksService.insertRelatedLinks(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateRelatedLinks
     * @Description: TODO(更新相关链接)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-23
     */
    @RequestMapping(value = "/updateRelatedLinks")
    @ResponseBody
    public MessageResponse updateRelatedLinks(String jsons) throws Exception {
        RelatedLinks obj = JSON.parseObject(jsons, RelatedLinks.class);
        return this.relatedLinksService.updateRelatedLinks(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectRelatedLinksByPrimaryKey
     * @Description: TODO(获取相关链接)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<RelatedLinks>
     * @author: huacai003
     * @version: 2018-05-23
     */
    @RequestMapping(value = "/selectRelatedLinksByPrimaryKey")
    @ResponseBody
    public SingleResult
            <RelatedLinksVo> selectRelatedLinksByPrimaryKey(String id) throws Exception {
        return this.relatedLinksService.selectRelatedLinksByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteRelatedLinksByRelatedLinksId
     * @Description: TODO(删除相关链接)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-23
     */
    @RequestMapping(value = "/deleteRelatedLinksByRelatedLinksId")
    @ResponseBody
    public MessageResponse deleteRelatedLinksByRelatedLinksId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.relatedLinksService.deleteRelatedLinksByRelatedLinksId(id, this.getCurUserProp());
    }
}

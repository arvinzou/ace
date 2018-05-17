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
import com.huacainfo.ace.fop.model.CorporateStyle;
import com.huacainfo.ace.fop.service.CorporateStyleService;
import com.huacainfo.ace.fop.vo.CorporateStyleVo;
import com.huacainfo.ace.fop.vo.CorporateStyleQVo;

@Controller
@RequestMapping("/corporateStyle")
/**
 * @author: huacai003
 * @version: 2018-05-17
 * @Description: TODO(企业风采)
 */
public class CorporateStyleController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CorporateStyleService corporateStyleService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业风采分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <CorporateStyleVo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/findCorporateStyleList")
    @ResponseBody
    public PageResult
            <CorporateStyleVo> findCorporateStyleList(CorporateStyleQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult
                <CorporateStyleVo> rst = this.corporateStyleService
                .findCorporateStyleList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertCorporateStyle
     * @Description: TODO(添加企业风采)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/insertCorporateStyle")
    @ResponseBody
    public MessageResponse insertCorporateStyle(String jsons) throws Exception {
        CorporateStyle obj = JSON.parseObject(jsons, CorporateStyle.class);
        return this.corporateStyleService.insertCorporateStyle(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateCorporateStyle
     * @Description: TODO(更新企业风采)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/updateCorporateStyle")
    @ResponseBody
    public MessageResponse updateCorporateStyle(String jsons) throws Exception {
        CorporateStyle obj = JSON.parseObject(jsons, CorporateStyle.class);
        return this.corporateStyleService.updateCorporateStyle(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectCorporateStyleByPrimaryKey
     * @Description: TODO(获取企业风采)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CorporateStyle>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/selectCorporateStyleByPrimaryKey")
    @ResponseBody
    public SingleResult
            <CorporateStyleVo> selectCorporateStyleByPrimaryKey(String id) throws Exception {
        return this.corporateStyleService.selectCorporateStyleByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteCorporateStyleByCorporateStyleId
     * @Description: TODO(删除企业风采)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/deleteCorporateStyleByCorporateStyleId")
    @ResponseBody
    public MessageResponse deleteCorporateStyleByCorporateStyleId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.corporateStyleService.deleteCorporateStyleByCorporateStyleId(id, this.getCurUserProp());
    }
}

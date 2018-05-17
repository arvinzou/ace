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
import com.huacainfo.ace.fop.model.EnterpriseProducts;
import com.huacainfo.ace.fop.service.EnterpriseProductsService;
import com.huacainfo.ace.fop.vo.EnterpriseProductsVo;
import com.huacainfo.ace.fop.vo.EnterpriseProductsQVo;

@Controller
@RequestMapping("/enterpriseProducts")
/**
 * @author: huacai003
 * @version: 2018-05-17
 * @Description: TODO(企业风采)
 */
public class EnterpriseProductsController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EnterpriseProductsService enterpriseProductsService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业风采分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <EnterpriseProductsVo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/findEnterpriseProductsList")
    @ResponseBody
    public PageResult
            <EnterpriseProductsVo> findEnterpriseProductsList(EnterpriseProductsQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult
                <EnterpriseProductsVo> rst = this.enterpriseProductsService
                .findEnterpriseProductsList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertEnterpriseProducts
     * @Description: TODO(添加企业风采)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/insertEnterpriseProducts")
    @ResponseBody
    public MessageResponse insertEnterpriseProducts(String jsons) throws Exception {
        EnterpriseProducts obj = JSON.parseObject(jsons, EnterpriseProducts.class);
        return this.enterpriseProductsService.insertEnterpriseProducts(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateEnterpriseProducts
     * @Description: TODO(更新企业风采)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/updateEnterpriseProducts")
    @ResponseBody
    public MessageResponse updateEnterpriseProducts(String jsons) throws Exception {
        EnterpriseProducts obj = JSON.parseObject(jsons, EnterpriseProducts.class);
        return this.enterpriseProductsService.updateEnterpriseProducts(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectEnterpriseProductsByPrimaryKey
     * @Description: TODO(获取企业风采)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EnterpriseProducts>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/selectEnterpriseProductsByPrimaryKey")
    @ResponseBody
    public SingleResult
            <EnterpriseProductsVo> selectEnterpriseProductsByPrimaryKey(String id) throws Exception {
        return this.enterpriseProductsService.selectEnterpriseProductsByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteEnterpriseProductsByEnterpriseProductsId
     * @Description: TODO(删除企业风采)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/deleteEnterpriseProductsByEnterpriseProductsId")
    @ResponseBody
    public MessageResponse deleteEnterpriseProductsByEnterpriseProductsId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.enterpriseProductsService.deleteEnterpriseProductsByEnterpriseProductsId(id, this.getCurUserProp());
    }
}

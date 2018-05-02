package com.huacainfo.ace.fop.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopLoanProduct;
import com.huacainfo.ace.fop.service.FopLoanProductService;
import com.huacainfo.ace.fop.vo.FopLoanProductQVo;
import com.huacainfo.ace.fop.vo.FopLoanProductVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fopLoanProduct")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: 通知公告
 */
public class FopLoanProductController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopLoanProductService fopLoanProductService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(通知公告分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<FopLoanProductVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/findFopLoanProductList")
    @ResponseBody
    public PageResult<FopLoanProductVo> findFopLoanProductList(FopLoanProductQVo condition,
                                                               PageParamNoChangeSord page) throws Exception {
        PageResult<FopLoanProductVo> rst = this.fopLoanProductService
                .findFopLoanProductList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertFopLoanProduct
     * @Description: TODO(添加通知公告)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/insertFopLoanProduct")
    @ResponseBody
    public MessageResponse insertFopLoanProduct(String jsons) throws Exception {
        FopLoanProduct obj = JSON.parseObject(jsons, FopLoanProduct.class);
        return this.fopLoanProductService
                .insertFopLoanProduct(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateFopLoanProduct
     * @Description: TODO(更新通知公告)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/updateFopLoanProduct")
    @ResponseBody
    public MessageResponse updateFopLoanProduct(String jsons) throws Exception {
        FopLoanProduct obj = JSON.parseObject(jsons, FopLoanProduct.class);
        return this.fopLoanProductService
                .updateFopLoanProduct(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFopLoanProductByPrimaryKey
     * @Description: TODO(获取通知公告)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopLoanProduct>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/selectFopLoanProductByPrimaryKey")
    @ResponseBody
    public SingleResult<FopLoanProductVo> selectFopLoanProductByPrimaryKey(String id)
            throws Exception {
        return this.fopLoanProductService.selectFopLoanProductByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteFopLoanProductByFopLoanProductId
     * @Description: TODO(删除通知公告)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/deleteFopLoanProductByFopLoanProductId")
    @ResponseBody
    public MessageResponse deleteFopLoanProductByFopLoanProductId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.fopLoanProductService.deleteFopLoanProductByFopLoanProductId(id,
                this.getCurUserProp());
    }
}

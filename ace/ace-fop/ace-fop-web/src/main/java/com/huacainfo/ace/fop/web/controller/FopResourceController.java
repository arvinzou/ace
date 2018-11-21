package com.huacainfo.ace.fop.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopResource;
import com.huacainfo.ace.fop.service.FopResourceService;
import com.huacainfo.ace.fop.vo.FopResourceQVo;
import com.huacainfo.ace.fop.vo.FopResourceVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fopResource")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: 资源管理
 */
public class FopResourceController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopResourceService fopResourceService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<FopResourceVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/findFopResourceList")
    @ResponseBody
    public PageResult<FopResourceVo> findFopResourceList(FopResourceQVo condition,
                                                         PageParamNoChangeSord page) throws Exception {
        PageResult<FopResourceVo> rst = this.fopResourceService.findFopResourceList(condition,
                page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopResource
     * @Description: TODO(添加企业管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/insertFopResource")
    @ResponseBody
    public MessageResponse insertFopResource(String jsons) throws Exception {
        FopResource obj = JSON.parseObject(jsons, FopResource.class);
        return this.fopResourceService.insertFopResource(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateFopResource
     * @Description: TODO(更新企业管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/updateFopResource")
    @ResponseBody
    public MessageResponse updateFopResource(String jsons) throws Exception {
        FopResource obj = JSON.parseObject(jsons, FopResource.class);
        return this.fopResourceService.updateFopResource(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFopResourceByPrimaryKey
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopResource>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/selectFopResourceByPrimaryKey")
    @ResponseBody
    public SingleResult<FopResourceVo> selectFopResourceByPrimaryKey(String id)
            throws Exception {
        return this.fopResourceService.selectFopResourceByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteFopResourceByFopResourceId
     * @Description: TODO(删除企业管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/deleteFopResourceByFopResourceId")
    @ResponseBody
    public MessageResponse deleteFopResourceByFopResourceId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.fopResourceService.deleteFopResourceByFopResourceId(id,
                this.getCurUserProp());
    }
}

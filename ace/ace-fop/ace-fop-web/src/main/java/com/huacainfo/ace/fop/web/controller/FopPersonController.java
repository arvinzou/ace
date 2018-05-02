package com.huacainfo.ace.fop.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopPerson;
import com.huacainfo.ace.fop.service.FopPersonService;
import com.huacainfo.ace.fop.vo.FopPersonQVo;
import com.huacainfo.ace.fop.vo.FopPersonVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fopPerson")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: 个人档案
 */
public class FopPersonController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopPersonService fopPersonService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<FopPersonVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/findFopPersonList")
    @ResponseBody
    public PageResult<FopPersonVo> findFopPersonList(FopPersonQVo condition,
                                                     PageParamNoChangeSord page) throws Exception {
        PageResult<FopPersonVo> rst = this.fopPersonService
                .findFopPersonList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertFopPerson
     * @Description: TODO(添加企业管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/insertFopPerson")
    @ResponseBody
    public MessageResponse insertFopPerson(String jsons) throws Exception {
        FopPerson obj = JSON.parseObject(jsons, FopPerson.class);
        return this.fopPersonService
                .insertFopPerson(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateFopPerson
     * @Description: TODO(更新企业管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/updateFopPerson")
    @ResponseBody
    public MessageResponse updateFopPerson(String jsons) throws Exception {
        FopPerson obj = JSON.parseObject(jsons, FopPerson.class);
        return this.fopPersonService
                .updateFopPerson(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFopPersonByPrimaryKey
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopPerson>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/selectFopPersonByPrimaryKey")
    @ResponseBody
    public SingleResult<FopPersonVo> selectFopPersonByPrimaryKey(String id)
            throws Exception {
        return this.fopPersonService.selectFopPersonByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteFopPersonByFopPersonId
     * @Description: TODO(删除企业管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/deleteFopPersonByFopPersonId")
    @ResponseBody
    public MessageResponse deleteFopPersonByFopPersonId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.fopPersonService.deleteFopPersonByFopPersonId(id,
                this.getCurUserProp());
    }
}

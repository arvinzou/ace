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
import com.huacainfo.ace.fop.model.IntegrityPublicity;
import com.huacainfo.ace.fop.service.IntegrityPublicityService;
import com.huacainfo.ace.fop.vo.IntegrityPublicityVo;
import com.huacainfo.ace.fop.vo.IntegrityPublicityQVo;

@Controller
@RequestMapping("/integrityPublicity")
/**
 * @author: huacai003
 * @version: 2018-05-28
 * @Description: TODO(诚信公示)
 */
public class IntegrityPublicityController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IntegrityPublicityService integrityPublicityService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(诚信公示分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <IntegrityPublicityVo>
     * @author: huacai003
     * @version: 2018-05-28
     */
    @RequestMapping(value = "/findIntegrityPublicityList")
    @ResponseBody
    public PageResult
            <IntegrityPublicityVo> findIntegrityPublicityList(IntegrityPublicityQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult
                <IntegrityPublicityVo> rst = this.integrityPublicityService
                .findIntegrityPublicityList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertIntegrityPublicity
     * @Description: TODO(添加诚信公示)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-28
     */
    @RequestMapping(value = "/insertIntegrityPublicity")
    @ResponseBody
    public MessageResponse insertIntegrityPublicity(String jsons) throws Exception {
        IntegrityPublicity obj = JSON.parseObject(jsons, IntegrityPublicity.class);
        return this.integrityPublicityService.insertIntegrityPublicity(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateIntegrityPublicity
     * @Description: TODO(更新诚信公示)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-28
     */
    @RequestMapping(value = "/updateIntegrityPublicity")
    @ResponseBody
    public MessageResponse updateIntegrityPublicity(String jsons) throws Exception {
        IntegrityPublicity obj = JSON.parseObject(jsons, IntegrityPublicity.class);
        return this.integrityPublicityService.updateIntegrityPublicity(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectIntegrityPublicityByPrimaryKey
     * @Description: TODO(获取诚信公示)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<IntegrityPublicity>
     * @author: huacai003
     * @version: 2018-05-28
     */
    @RequestMapping(value = "/selectIntegrityPublicityByPrimaryKey")
    @ResponseBody
    public SingleResult
            <IntegrityPublicityVo> selectIntegrityPublicityByPrimaryKey(String id) throws Exception {
        return this.integrityPublicityService.selectIntegrityPublicityByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteIntegrityPublicityByIntegrityPublicityId
     * @Description: TODO(删除诚信公示)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-28
     */
    @RequestMapping(value = "/deleteIntegrityPublicityByIntegrityPublicityId")
    @ResponseBody
    public MessageResponse deleteIntegrityPublicityByIntegrityPublicityId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.integrityPublicityService.deleteIntegrityPublicityByIntegrityPublicityId(id, this.getCurUserProp());
    }
}

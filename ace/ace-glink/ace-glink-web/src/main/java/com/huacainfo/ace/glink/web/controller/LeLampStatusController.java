package com.huacainfo.ace.glink.web.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.glink.model.LeLampStatus;
import com.huacainfo.ace.glink.service.LeLampStatusService;
import com.huacainfo.ace.glink.vo.LeLampStatusQVo;
import com.huacainfo.ace.glink.vo.LeLampStatusVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/leLampStatus")
/**
 * @author: Arvin
 * @version: 2019-04-25
 * @Description: TODO(弱电 - 设备状态)
 */
public class LeLampStatusController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LeLampStatusService leLampStatusService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(弱电 - 设备状态分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<LeLampStatusVo>
     * @author: Arvin
     * @version: 2019-04-25
     */
    @RequestMapping(value = "/findLeLampStatusList")
    @ResponseBody
    public PageResult<LeLampStatusVo> findLeLampStatusList(LeLampStatusQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<LeLampStatusVo> rst = this.leLampStatusService.findLeLampStatusList(condition, page.getStart(),
                page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertLeLampStatus
     * @Description: TODO(添加弱电 - 设备状态)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-25
     */
    @RequestMapping(value = "/insertLeLampStatus")
    @ResponseBody
    public MessageResponse insertLeLampStatus(String jsons) throws Exception {
        LeLampStatus obj = JSON.parseObject(jsons, LeLampStatus.class);
        return this.leLampStatusService.insertLeLampStatus(obj, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:selectLeLampStatusByPrimaryKey
     * @Description: TODO(获取弱电 - 设备状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LeLampStatus>
     * @author: Arvin
     * @version: 2019-04-25
     */
    @RequestMapping(value = "/selectLeLampStatusByPrimaryKey")
    @ResponseBody
    public SingleResult<LeLampStatusVo> selectLeLampStatusByPrimaryKey(String id) throws Exception {
        return this.leLampStatusService.selectLeLampStatusByPrimaryKey(id);
    }


}

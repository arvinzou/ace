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
import com.huacainfo.ace.fop.model.Pm;
import com.huacainfo.ace.fop.service.PmService;
import com.huacainfo.ace.fop.vo.PmVo;
import com.huacainfo.ace.fop.vo.PmQVo;

@Controller
@RequestMapping("/pm")
/**
 * @author: 陈晓克
 * @version: 2018-11-05
 * @Description: TODO(党员信息)
 */
public class PmController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PmService pmService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(党员信息分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <PmVo>
     * @author: 陈晓克
     * @version: 2018-11-05
     */
    @RequestMapping(value = "/findPmList")
    @ResponseBody
    public PageResult<PmVo> findPmList(PmQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<PmVo> rst = this.pmService.findPmList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertPm
     * @Description: TODO(添加党员信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-05
     */
    @RequestMapping(value = "/insertPm")
    @ResponseBody
    public MessageResponse insertPm(String jsons) throws Exception {
        Pm obj = JSON.parseObject(jsons, Pm.class);
        return this.pmService.insertPm(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updatePm
     * @Description: TODO(更新党员信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-05
     */
    @RequestMapping(value = "/updatePm")
    @ResponseBody
    public MessageResponse updatePm(String jsons) throws Exception {
        Pm obj = JSON.parseObject(jsons, Pm.class);
        return this.pmService.updatePm(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectPmByPrimaryKey
     * @Description: TODO(获取党员信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Pm>
     * @author: 陈晓克
     * @version: 2018-11-05
     */
    @RequestMapping(value = "/selectPmByPrimaryKey")
    @ResponseBody
    public SingleResult<PmVo> selectPmByPrimaryKey(String id) throws Exception {
        return this.pmService.selectPmByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deletePmByPmId
     * @Description: TODO(删除党员信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-05
     */
    @RequestMapping(value = "/deletePmByPmId")
    @ResponseBody
    public MessageResponse deletePmByPmId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.pmService.deletePmByPmId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核党员信息)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-05
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.pmService.audit(id, rst, message, this.getCurUserProp());
    }
}

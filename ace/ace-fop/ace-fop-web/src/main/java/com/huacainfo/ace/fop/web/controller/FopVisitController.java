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
import com.huacainfo.ace.fop.model.FopVisit;
import com.huacainfo.ace.fop.service.FopVisitService;
import com.huacainfo.ace.fop.vo.FopVisitVo;
import com.huacainfo.ace.fop.vo.FopVisitQVo;

@Controller
@RequestMapping("/fopVisit")
/**
 * @author: 陈晓克
 * @version: 2018-11-02
 * @Description: TODO(企业走访)
 */
public class FopVisitController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopVisitService fopVisitService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业走访分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <FopVisitVo>
     * @author: 陈晓克
     * @version: 2018-11-02
     */
    @RequestMapping(value = "/findFopVisitList")
    @ResponseBody
    public PageResult
            <FopVisitVo> findFopVisitList(FopVisitQVo condition,
                                          PageParamNoChangeSord page) throws Exception {

        PageResult
                <FopVisitVo> rst = this.fopVisitService
                .findFopVisitList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertFopVisit
     * @Description: TODO(添加企业走访)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-02
     */
    @RequestMapping(value = "/insertFopVisit")
    @ResponseBody
    public MessageResponse insertFopVisit(String jsons) throws Exception {
        FopVisit obj = JSON.parseObject(jsons, FopVisit.class);
        return this.fopVisitService.insertFopVisit(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateFopVisit
     * @Description: TODO(更新企业走访)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-02
     */
    @RequestMapping(value = "/updateFopVisit")
    @ResponseBody
    public MessageResponse updateFopVisit(String jsons) throws Exception {
        FopVisit obj = JSON.parseObject(jsons, FopVisit.class);
        return this.fopVisitService.updateFopVisit(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFopVisitByPrimaryKey
     * @Description: TODO(获取企业走访)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopVisit>
     * @author: 陈晓克
     * @version: 2018-11-02
     */
    @RequestMapping(value = "/selectFopVisitByPrimaryKey")
    @ResponseBody
    public SingleResult
            <FopVisitVo> selectFopVisitByPrimaryKey(String id) throws Exception {
        return this.fopVisitService.selectFopVisitByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteFopVisitByFopVisitId
     * @Description: TODO(删除企业走访)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-02
     */
    @RequestMapping(value = "/deleteFopVisitByFopVisitId")
    @ResponseBody
    public MessageResponse deleteFopVisitByFopVisitId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.fopVisitService.deleteFopVisitByFopVisitId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核企业走访)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-02
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.fopVisitService.audit(id, rst, message, this.getCurUserProp());
    }
}

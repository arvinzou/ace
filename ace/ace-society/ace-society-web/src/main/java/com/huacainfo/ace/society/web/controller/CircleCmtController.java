package com.huacainfo.ace.society.web.controller;

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
import com.huacainfo.ace.society.model.CircleCmt;
import com.huacainfo.ace.society.service.CircleCmtService;
import com.huacainfo.ace.society.vo.CircleCmtVo;
import com.huacainfo.ace.society.vo.CircleCmtQVo;

@Controller
@RequestMapping("/circleCmt")
/**
 * @author: 陈晓克
 * @version: 2018-09-20
 * @Description: TODO(评论)
 */
public class CircleCmtController extends SocietyBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CircleCmtService circleCmtService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评论分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <CircleCmtVo>
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @RequestMapping(value = "/findCircleCmtList")
    @ResponseBody
    public PageResult
            <CircleCmtVo> findCircleCmtList(CircleCmtQVo condition,
                                            PageParamNoChangeSord page) throws Exception {
        //condition.setCorpId(this.getCurUserProp().getCorpId());
        PageResult
                <CircleCmtVo> rst = this.circleCmtService
                .findCircleCmtList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertCircleCmt
     * @Description: TODO(添加评论)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @RequestMapping(value = "/www/insertCircleCmt")
    @ResponseBody
    public MessageResponse insertCircleCmt(String jsons) throws Exception {
        CircleCmt obj = JSON.parseObject(jsons, CircleCmt.class);
        return this.circleCmtService.insertCircleCmt(obj);
    }

    /**
     * @throws
     * @Title:updateCircleCmt
     * @Description: TODO(更新评论)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @RequestMapping(value = "/updateCircleCmt")
    @ResponseBody
    public MessageResponse updateCircleCmt(String jsons) throws Exception {
        CircleCmt obj = JSON.parseObject(jsons, CircleCmt.class);
        return this.circleCmtService.updateCircleCmt(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectCircleCmtByPrimaryKey
     * @Description: TODO(获取评论)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CircleCmt>
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @RequestMapping(value = "/selectCircleCmtByPrimaryKey")
    @ResponseBody
    public SingleResult
            <CircleCmtVo> selectCircleCmtByPrimaryKey(String id) throws Exception {
        return this.circleCmtService.selectCircleCmtByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteCircleCmtByCircleCmtId
     * @Description: TODO(删除评论)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @RequestMapping(value = "/deleteCircleCmtByCircleCmtId")
    @ResponseBody
    public MessageResponse deleteCircleCmtByCircleCmtId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.circleCmtService.deleteCircleCmtByCircleCmtId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核评论)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.circleCmtService.audit(id, rst, message, this.getCurUserProp());
    }
}

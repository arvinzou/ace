package com.huacainfo.ace.fop.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopFlowRecord;
import com.huacainfo.ace.fop.service.FopFlowRecordService;
import com.huacainfo.ace.fop.vo.FopFlowRecordQVo;
import com.huacainfo.ace.fop.vo.FopFlowRecordVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fopFlowRecord")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: 流程记录
 */
public class FopFlowRecordController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopFlowRecordService fopFlowRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(流程记录分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<FopFlowRecordVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/findFopFlowRecordList")
    @ResponseBody
    public PageResult<FopFlowRecordVo> findFopFlowRecordList(FopFlowRecordQVo condition,
                                                             PageParamNoChangeSord page) throws Exception {
        PageResult<FopFlowRecordVo> rst =
                fopFlowRecordService.findFopFlowRecordList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertFopFlowRecord
     * @Description: TODO(添加流程记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/insertFopFlowRecord")
    @ResponseBody
    public MessageResponse insertFopFlowRecord(String jsons) throws Exception {
        FopFlowRecord obj = JSON.parseObject(jsons, FopFlowRecord.class);
        return this.fopFlowRecordService
                .insertFopFlowRecord(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateFopFlowRecord
     * @Description: TODO(更新流程记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/updateFopFlowRecord")
    @ResponseBody
    public MessageResponse updateFopFlowRecord(String jsons) throws Exception {
        FopFlowRecord obj = JSON.parseObject(jsons, FopFlowRecord.class);
        return this.fopFlowRecordService
                .updateFopFlowRecord(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFopFlowRecordByPrimaryKey
     * @Description: TODO(获取流程记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopFlowRecord>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/selectFopFlowRecordByPrimaryKey")
    @ResponseBody
    public SingleResult<FopFlowRecordVo> selectFopFlowRecordByPrimaryKey(String id) throws Exception {
        return this.fopFlowRecordService.selectFopFlowRecordByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteFopFlowRecordByFopFlowRecordId
     * @Description: TODO(删除流程记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/deleteFopFlowRecordByFopFlowRecordId")
    @ResponseBody
    public MessageResponse deleteFopFlowRecordByFopFlowRecordId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.fopFlowRecordService.deleteFopFlowRecordByFopFlowRecordId(id,
                this.getCurUserProp());
    }


    /**
     * 功能描述: 流程审核
     *
     * @param: jsons 传入参数
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/7 11:45
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String jsons)
            throws Exception {
        FopFlowRecord record = JSON.parseObject(jsons, FopFlowRecord.class);
        return fopFlowRecordService.audit(record, this.getCurUserProp());

    }

}

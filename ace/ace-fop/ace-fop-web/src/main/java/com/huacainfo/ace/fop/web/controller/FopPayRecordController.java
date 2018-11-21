package com.huacainfo.ace.fop.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.fop.model.FopPayRecord;
import com.huacainfo.ace.fop.service.FopPayRecordService;
import com.huacainfo.ace.fop.vo.FopPayRecordQVo;
import com.huacainfo.ace.fop.vo.FopPayRecordVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fopPayRecord")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: 支付记录
 */
public class FopPayRecordController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopPayRecordService fopPayRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(支付记录分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<FopPayRecordVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/findFopPayRecordList")
    @ResponseBody
    public PageResult<FopPayRecordVo> findFopPayRecordList(FopPayRecordQVo condition,
                                                           PageParamNoChangeSord page) throws Exception {
        PageResult<FopPayRecordVo> rst = this.fopPayRecordService
                .findFopPayRecordList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertFopPayRecord
     * @Description: TODO(添加支付记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/insertFopPayRecord")
    @ResponseBody
    public MessageResponse insertFopPayRecord(String jsons) throws Exception {
        FopPayRecord obj = JSON.parseObject(jsons, FopPayRecord.class);
        return this.fopPayRecordService
                .insertFopPayRecord(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateFopPayRecord
     * @Description: TODO(更新支付记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/updateFopPayRecord")
    @ResponseBody
    public MessageResponse updateFopPayRecord(String jsons) throws Exception {
        FopPayRecord obj = JSON.parseObject(jsons, FopPayRecord.class);
        return this.fopPayRecordService
                .updateFopPayRecord(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFopPayRecordByPrimaryKey
     * @Description: TODO(获取支付记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopPayRecord>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/selectFopPayRecordByPrimaryKey")
    @ResponseBody
    public SingleResult<FopPayRecordVo> selectFopPayRecordByPrimaryKey(String id)
            throws Exception {
        return this.fopPayRecordService.selectFopPayRecordByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteFopPayRecordByFopPayRecordId
     * @Description: TODO(删除支付记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/deleteFopPayRecordByFopPayRecordId")
    @ResponseBody
    public MessageResponse deleteFopPayRecordByFopPayRecordId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.fopPayRecordService.deleteFopPayRecordByFopPayRecordId(id,
                this.getCurUserProp());
    }


    /**
     * 功能描述: 确认缴费审核
     *
     * @param: +
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/8 13:56
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id) throws Exception {
        if (CommonUtils.isEmpty(id)) {
            return new MessageResponse(ResultCode.FAIL, "传入参数不能为空");
        }

        return fopPayRecordService.audit(id, getCurUserProp());
    }

    /**
     * 功能描述: 推送催缴通知
     *
     * @param: id  fop_pay_record.id
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/8 13:56
     */
    @RequestMapping(value = "/sendNotice")
    @ResponseBody
    public MessageResponse sendNotice(String id) throws Exception {
        if (CommonUtils.isEmpty(id)) {
            return new MessageResponse(ResultCode.FAIL, "传入参数不能为空");
        }

        return fopPayRecordService.sendNotice(id, getCurUserProp());
    }
}

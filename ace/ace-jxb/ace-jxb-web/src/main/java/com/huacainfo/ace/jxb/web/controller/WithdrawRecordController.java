package com.huacainfo.ace.jxb.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.WithdrawRecord;
import com.huacainfo.ace.jxb.service.WithdrawRecordService;
import com.huacainfo.ace.jxb.vo.WithdrawRecordQVo;
import com.huacainfo.ace.jxb.vo.WithdrawRecordVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/withdrawRecord")
/**
 * @author: Arvin
 * @version: 2018-11-14
 * @Description: TODO(提现申请记录)
 */
public class WithdrawRecordController extends JxbBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WithdrawRecordService withdrawRecordService;

    /**
     * @Description: TODO(提现申请记录分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <WithdrawRecordVo>
     * @author: Arvin
     * @version: 2018-11-14
     */
    @RequestMapping(value = "/findWithdrawRecordList")
    @ResponseBody
    public PageResult<WithdrawRecordVo> findWithdrawRecordList(WithdrawRecordQVo condition,
                                                               PageParamNoChangeSord page) throws Exception {

        PageResult<WithdrawRecordVo> rst = this.withdrawRecordService
                .findWithdrawRecordList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @Description: TODO(添加提现申请记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-14
     */
    @RequestMapping(value = "/insertWithdrawRecord")
    @ResponseBody
    public MessageResponse insertWithdrawRecord(String jsons) throws Exception {
        WithdrawRecord obj = JSON.parseObject(jsons, WithdrawRecord.class);
        return this.withdrawRecordService.insertWithdrawRecord(obj, this.getCurUserProp());
    }

    /**
     * @Description: TODO(更新提现申请记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-14
     */
    @RequestMapping(value = "/updateWithdrawRecord")
    @ResponseBody
    public MessageResponse updateWithdrawRecord(String jsons) throws Exception {
        WithdrawRecord obj = JSON.parseObject(jsons, WithdrawRecord.class);
        return this.withdrawRecordService.updateWithdrawRecord(obj, this.getCurUserProp());
    }

    /**
     * @Description: TODO(获取提现申请记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<WithdrawRecord>
     * @author: Arvin
     * @version: 2018-11-14
     */
    @RequestMapping(value = "/selectWithdrawRecordByPrimaryKey")
    @ResponseBody
    public SingleResult<WithdrawRecordVo> selectWithdrawRecordByPrimaryKey(String id) throws Exception {
        return this.withdrawRecordService.selectWithdrawRecordByPrimaryKey(id);
    }

    /**
     * @Description: TODO(删除提现申请记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-14
     */
    @RequestMapping(value = "/deleteWithdrawRecordByWithdrawRecordId")
    @ResponseBody
    public MessageResponse deleteWithdrawRecordByWithdrawRecordId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.withdrawRecordService.deleteWithdrawRecordByWithdrawRecordId(id, this.getCurUserProp());
    }

    /**
     * @Description: TODO(审核提现申请记录)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-14
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {
        if (StringUtil.areNotEmpty(id, rst)) {

        }
        try {
            return withdrawRecordService.updateAudit(id, rst, message, this.getCurUserProp());
        } catch (CustomException e) {
            return new MessageResponse(ResultCode.FAIL, e.getMsg());
        }
    }
}

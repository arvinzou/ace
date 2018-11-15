package com.huacainfo.ace.jxb.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.WithdrawWxLog;
import com.huacainfo.ace.jxb.service.WithdrawWxLogService;
import com.huacainfo.ace.jxb.vo.WithdrawWxLogQVo;
import com.huacainfo.ace.jxb.vo.WithdrawWxLogVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/withdrawWxLog")
/**
 * @author: Arvin
 * @version: 2018-11-14
 * @Description: TODO(企业支付接口日志)
 */
public class WithdrawWxLogController extends JxbBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WithdrawWxLogService withdrawWxLogService;

    /**
     * @Description: TODO(企业支付接口日志分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <WithdrawWxLogVo>
     * @author: Arvin
     * @version: 2018-11-14
     */
    @RequestMapping(value = "/findWithdrawWxLogList")
    @ResponseBody
    public PageResult<WithdrawWxLogVo> findWithdrawWxLogList(WithdrawWxLogQVo condition,
                                                             PageParamNoChangeSord page) throws Exception {

        PageResult<WithdrawWxLogVo> rst = this.withdrawWxLogService
                .findWithdrawWxLogList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @Description: TODO(添加企业支付接口日志)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-14
     */
    @RequestMapping(value = "/insertWithdrawWxLog")
    @ResponseBody
    public MessageResponse insertWithdrawWxLog(String jsons) throws Exception {
        WithdrawWxLog obj = JSON.parseObject(jsons, WithdrawWxLog.class);
        return this.withdrawWxLogService.insertWithdrawWxLog(obj, this.getCurUserProp());
    }

    /**
     * @Description: TODO(更新企业支付接口日志)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-14
     */
    @RequestMapping(value = "/updateWithdrawWxLog")
    @ResponseBody
    public MessageResponse updateWithdrawWxLog(String jsons) throws Exception {
        WithdrawWxLog obj = JSON.parseObject(jsons, WithdrawWxLog.class);
        return this.withdrawWxLogService.updateWithdrawWxLog(obj, this.getCurUserProp());
    }

    /**
     * @Description: TODO(获取企业支付接口日志)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<WithdrawWxLog>
     * @author: Arvin
     * @version: 2018-11-14
     */
    @RequestMapping(value = "/selectWithdrawWxLogByPrimaryKey")
    @ResponseBody
    public SingleResult<WithdrawWxLogVo> selectWithdrawWxLogByPrimaryKey(String id) throws Exception {
        return this.withdrawWxLogService.selectWithdrawWxLogByPrimaryKey(id);
    }

    /**
     * @Description: TODO(删除企业支付接口日志)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-14
     */
    @RequestMapping(value = "/deleteWithdrawWxLogByWithdrawWxLogId")
    @ResponseBody
    public MessageResponse deleteWithdrawWxLogByWithdrawWxLogId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.withdrawWxLogService.deleteWithdrawWxLogByWithdrawWxLogId(id, this.getCurUserProp());
    }

}

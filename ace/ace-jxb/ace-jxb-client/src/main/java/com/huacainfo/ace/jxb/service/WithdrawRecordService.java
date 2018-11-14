package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.WithdrawRecord;
import com.huacainfo.ace.jxb.vo.WithdrawRecordQVo;
import com.huacainfo.ace.jxb.vo.WithdrawRecordVo;

/**
 * @author: Arvin
 * @version: 2018-11-14
 * @Description: TODO(提现申请记录)
 */
public interface WithdrawRecordService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(提现申请记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <WithdrawRecordVo>
     * @author: Arvin
     * @version: 2018-11-14
     */
    PageResult<WithdrawRecordVo> findWithdrawRecordList(WithdrawRecordQVo condition,
                                                        int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertWithdrawRecord
     * @Description: TODO(添加提现申请记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-14
     */
    MessageResponse insertWithdrawRecord(WithdrawRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateWithdrawRecord
     * @Description: TODO(更新提现申请记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-14
     */
    MessageResponse updateWithdrawRecord(WithdrawRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectWithdrawRecordByPrimaryKey
     * @Description: TODO(获取提现申请记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<WithdrawRecord>
     * @author: Arvin
     * @version: 2018-11-14
     */
    SingleResult
            <WithdrawRecordVo> selectWithdrawRecordByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteWithdrawRecordByWithdrawRecordId
     * @Description: TODO(删除提现申请记录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-14
     */
    MessageResponse deleteWithdrawRecordByWithdrawRecordId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核提现申请记录)
     * @param: @param id bean.id
     * @param: @param rst 审核结果
     * String TEMP = "temp";//提交审核
     * String PASS = "pass";//审核通过 -- 提现成功
     * String REJECT = "reject";//驳回审核 -- 提现失败
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-14
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;

    /**
     * 余额提现申请
     *
     * @param params 必须
     * @return ResultResponse
     */
    ResultResponse withdraw(WithdrawRecordVo params) throws Exception;


}

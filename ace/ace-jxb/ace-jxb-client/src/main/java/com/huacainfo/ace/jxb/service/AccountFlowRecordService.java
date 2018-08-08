package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.jxb.vo.AccountFlowRecordQVo;
import com.huacainfo.ace.jxb.vo.AccountFlowRecordVo;

import java.math.BigDecimal;

/**
 * @author: Arvin
 * @version: 2018-08-07
 * @Description: TODO(账户资金流水)
 */
public interface AccountFlowRecordService {
    /**
     * @Description: 账户资金流水分页查询
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<AccountFlowRecordVo>
     * @author: Arvin
     * @version: 2018-08-07
     */
    PageResult<AccountFlowRecordVo> findList(AccountFlowRecordQVo condition,
                                             int start, int limit, String orderBy) throws Exception;

    /**
     * 添加账户资金流水
     *
     * @param userId   用户ID
     * @param userType 用户类型 1-咨询师，2-家长
     * @param bisType  业务类型 1-订单入账
     * @param amount   金额数值
     * @param srcId    源头单据id
     * @param srcType  源头单据类型 1-订单id（base_order.id）
     * @return int
     * @throws Exception
     */
    int insertRecord(String userId, String userType, String bisType,
                     BigDecimal amount, String srcId, String srcType) throws Exception;

}

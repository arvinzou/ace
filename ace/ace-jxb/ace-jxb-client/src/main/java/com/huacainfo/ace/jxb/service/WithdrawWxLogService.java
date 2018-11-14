package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.WithdrawWxLog;
import com.huacainfo.ace.jxb.vo.WithdrawWxLogQVo;
import com.huacainfo.ace.jxb.vo.WithdrawWxLogVo;

/**
 * @author: Arvin
 * @version: 2018-11-14
 * @Description: TODO(企业支付接口日志)
 */
public interface WithdrawWxLogService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业支付接口日志分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<WithdrawWxLogVo>
     * @author: Arvin
     * @version: 2018-11-14
     */
    PageResult<WithdrawWxLogVo> findWithdrawWxLogList(WithdrawWxLogQVo condition,
                                                      int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertWithdrawWxLog
     * @Description: TODO(添加企业支付接口日志)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-14
     */
    MessageResponse insertWithdrawWxLog(WithdrawWxLog obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateWithdrawWxLog
     * @Description: TODO(更新企业支付接口日志)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-14
     */
    MessageResponse updateWithdrawWxLog(WithdrawWxLog obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectWithdrawWxLogByPrimaryKey
     * @Description: TODO(获取企业支付接口日志)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<WithdrawWxLog>
     * @author: Arvin
     * @version: 2018-11-14
     */
    SingleResult
            <WithdrawWxLogVo> selectWithdrawWxLogByPrimaryKey(String id) throws Exception;

    /**
     * @Description: TODO(删除企业支付接口日志)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-14
     */
    MessageResponse deleteWithdrawWxLogByWithdrawWxLogId(String id, UserProp userProp) throws Exception;

}

package com.huacainfo.ace.cu.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.cu.model.WxPayLog;
import com.huacainfo.ace.cu.vo.WxPayLogQVo;
import com.huacainfo.ace.cu.vo.WxPayLogVo;

/**
 * @author: Arvin
 * @version: 2018-06-14
 * @Description: TODO(捐款支付订单)
 */
public interface WxPayLogService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(捐款支付订单分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <WxPayLogVo>
     * @author: Arvin
     * @version: 2018-06-14
     */
    PageResult<WxPayLogVo> findWxPayLogList(WxPayLogQVo condition,
                                            int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertWxPayLog
     * @Description: TODO(添加捐款支付订单)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    MessageResponse insertWxPayLog(WxPayLog obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateWxPayLog
     * @Description: TODO(更新捐款支付订单)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    MessageResponse updateWxPayLog(WxPayLog obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectWxPayLogByPrimaryKey
     * @Description: TODO(获取捐款支付订单)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<WxPayLog>
     * @author: Arvin
     * @version: 2018-06-14
     */
    SingleResult<WxPayLogVo> selectWxPayLogByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteWxPayLogByWxPayLogId
     * @Description: TODO(删除捐款支付订单)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    MessageResponse deleteWxPayLogByWxPayLogId(String id, UserProp userProp) throws Exception;

}

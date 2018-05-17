package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.InvestmentInfo;
import com.huacainfo.ace.fop.vo.InvestmentInfoVo;
import com.huacainfo.ace.fop.vo.InvestmentInfoQVo;

/**
 * @author: huacai003
 * @version: 2018-05-17
 * @Description: TODO(招商信息)
 */
public interface InvestmentInfoService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(招商信息分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <InvestmentInfoVo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    PageResult
            <InvestmentInfoVo> findInvestmentInfoList(InvestmentInfoQVo condition, int start, int limit, String orderBy) throws
            Exception;

    /**
     * @throws
     * @Title:insertInvestmentInfo
     * @Description: TODO(添加招商信息)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    MessageResponse insertInvestmentInfo(InvestmentInfo obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateInvestmentInfo
     * @Description: TODO(更新招商信息)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    MessageResponse updateInvestmentInfo(InvestmentInfo obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectInvestmentInfoByPrimaryKey
     * @Description: TODO(获取招商信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<InvestmentInfo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    SingleResult
            <InvestmentInfoVo> selectInvestmentInfoByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteInvestmentInfoByInvestmentInfoId
     * @Description: TODO(删除招商信息)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    MessageResponse deleteInvestmentInfoByInvestmentInfoId(String id, UserProp userProp) throws Exception;

}

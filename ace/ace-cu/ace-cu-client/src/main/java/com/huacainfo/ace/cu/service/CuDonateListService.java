package com.huacainfo.ace.cu.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.cu.model.CuDonateList;
import com.huacainfo.ace.cu.model.CuDonateOrder;
import com.huacainfo.ace.cu.vo.CuDonateListQVo;
import com.huacainfo.ace.cu.vo.CuDonateListVo;

import java.math.BigDecimal;

/**
 * @author: Arvin
 * @version: 2018-06-14
 * @Description: TODO(慈善项目-捐献列表)
 */
public interface CuDonateListService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(慈善项目-捐献列表分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CuDonateListVo>
     * @author: Arvin
     * @version: 2018-06-14
     */
    PageResult<CuDonateListVo> findCuDonateListList(CuDonateListQVo condition,
                                                    int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCuDonateList
     * @Description: TODO(添加慈善项目-捐献列表)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    MessageResponse insertCuDonateList(CuDonateList obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCuDonateList
     * @Description: TODO(更新慈善项目-捐献列表)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    MessageResponse updateCuDonateList(CuDonateList obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCuDonateListByPrimaryKey
     * @Description: TODO(获取慈善项目-捐献列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CuDonateList>
     * @author: Arvin
     * @version: 2018-06-14
     */
    SingleResult<CuDonateListVo> selectCuDonateListByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCuDonateListByCuDonateListId
     * @Description: TODO(删除慈善项目-捐献列表)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    MessageResponse deleteCuDonateListByCuDonateListId(String id, UserProp userProp) throws Exception;

    /**
     * 增加捐献记录
     *
     * @param order
     * @return
     */
    ResultResponse addDonateList(CuDonateOrder order);

    /**
     * 获取某人的累计捐款金额
     *
     * @param openId
     * @return
     */
    BigDecimal getAccDonateAmount(String openId);

    /**
     * 获取某人的累计捐款次数
     *
     * @param openId
     * @return
     */
    int getAccDonateCount(String openId);


}

package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.ConsultProduct;
import com.huacainfo.ace.jxb.vo.ConsultProductQVo;
import com.huacainfo.ace.jxb.vo.ConsultProductVo;

/**
 * @author: Arvin
 * @version: 2018-07-25
 * @Description: TODO(咨询预约产品)
 */
public interface ConsultProductService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(咨询预约产品分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ConsultProductVo>
     * @author: Arvin
     * @version: 2018-07-25
     */
    PageResult
            <ConsultProductVo> findConsultProductList(ConsultProductQVo condition,
                                                      int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertConsultProduct
     * @Description: TODO(添加咨询预约产品)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    MessageResponse insertConsultProduct(ConsultProduct obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateConsultProduct
     * @Description: TODO(更新咨询预约产品)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    MessageResponse updateConsultProduct(ConsultProduct obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectConsultProductByPrimaryKey
     * @Description: TODO(获取咨询预约产品)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ConsultProduct>
     * @author: Arvin
     * @version: 2018-07-25
     */
    SingleResult
            <ConsultProductVo> selectConsultProductByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteConsultProductByConsultProductId
     * @Description: TODO(删除咨询预约产品)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    MessageResponse deleteConsultProductByConsultProductId(String id, UserProp userProp) throws Exception;

}

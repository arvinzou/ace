package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopLoanProduct;
import com.huacainfo.ace.fop.vo.FopLoanProductQVo;
import com.huacainfo.ace.fop.vo.FopLoanProductVo;

/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(通知公告)
 */
public interface FopLoanProductService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(通知公告分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopLoanProductVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract PageResult<FopLoanProductVo> findFopLoanProductList(FopLoanProductQVo condition, int start, int limit, String orderBy) throws Exception;

    public abstract PageResult<FopLoanProductVo> findLoanProductList(FopLoanProductQVo condition, int page, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertFopLoanProduct
     * @Description: TODO(添加通知公告)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse insertFopLoanProduct(FopLoanProduct obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopLoanProduct
     * @Description: TODO(更新通知公告)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse updateFopLoanProduct(FopLoanProduct obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopLoanProductByPrimaryKey
     * @Description: TODO(获取通知公告)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopLoanProduct>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract SingleResult<FopLoanProductVo> selectFopLoanProductByPrimaryKey(String id) throws Exception;

    public abstract ResultResponse selectLoanProductByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopLoanProductByFopLoanProductId
     * @Description: TODO(删除通知公告)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse deleteFopLoanProductByFopLoanProductId(String id, UserProp userProp) throws Exception;


}

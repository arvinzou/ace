package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.EnterpriseProducts;
import com.huacainfo.ace.fop.vo.EnterpriseProductsVo;
import com.huacainfo.ace.fop.vo.EnterpriseProductsQVo;

/**
 * @author: huacai003
 * @version: 2018-05-17
 * @Description: TODO(企业风采)
 */
public interface EnterpriseProductsService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业风采分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <EnterpriseProductsVo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    PageResult
            <EnterpriseProductsVo> findEnterpriseProductsList(EnterpriseProductsQVo condition, int start, int limit, String orderBy) throws
            Exception;

    /**
     * @throws
     * @Title:insertEnterpriseProducts
     * @Description: TODO(添加企业风采)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    MessageResponse insertEnterpriseProducts(EnterpriseProducts obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateEnterpriseProducts
     * @Description: TODO(更新企业风采)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    MessageResponse updateEnterpriseProducts(EnterpriseProducts obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectEnterpriseProductsByPrimaryKey
     * @Description: TODO(获取企业风采)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EnterpriseProducts>
     * @author: huacai003
     * @version: 2018-05-17
     */
    SingleResult
            <EnterpriseProductsVo> selectEnterpriseProductsByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteEnterpriseProductsByEnterpriseProductsId
     * @Description: TODO(删除企业风采)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    MessageResponse deleteEnterpriseProductsByEnterpriseProductsId(String id, UserProp userProp) throws Exception;

}

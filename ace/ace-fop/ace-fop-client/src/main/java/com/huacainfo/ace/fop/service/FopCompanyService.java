package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.vo.FopCompanyQVo;
import com.huacainfo.ace.fop.vo.FopCompanyVo;

/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(企业管理)
 */
public interface FopCompanyService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopCompanyVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    PageResult<FopCompanyVo> findFopCompanyList(FopCompanyQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertFopCompany
     * @Description: TODO(添加企业管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    MessageResponse insertFopCompany(FopCompanyVo obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopCompany
     * @Description: TODO(更新企业管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    MessageResponse updateFopCompany(FopCompanyVo obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopCompanyByPrimaryKey
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopCompany>
     * @author: Arvin
     * @version: 2018-05-02
     */
    SingleResult<FopCompanyVo> selectFopCompanyByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopCompanyByFopCompanyId
     * @Description: TODO(删除企业管理)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    MessageResponse deleteFopCompanyByFopCompanyId(String id, UserProp userProp) throws Exception;

}

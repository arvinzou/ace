package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopCompanyOrg;
import com.huacainfo.ace.fop.vo.FopCompanyOrgQVo;
import com.huacainfo.ace.fop.vo.FopCompanyOrgVo;

/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(企业管理)
 */
public interface FopCompanyOrgService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopCompanyOrgVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract PageResult<FopCompanyOrgVo> findFopCompanyOrgList(FopCompanyOrgQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertFopCompanyOrg
     * @Description: TODO(添加企业管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse insertFopCompanyOrg(FopCompanyOrg obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopCompanyOrg
     * @Description: TODO(更新企业管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse updateFopCompanyOrg(FopCompanyOrg obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopCompanyOrgByPrimaryKey
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopCompanyOrg>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract SingleResult<FopCompanyOrgVo> selectFopCompanyOrgByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopCompanyOrgByFopCompanyOrgId
     * @Description: TODO(删除企业管理)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse deleteFopCompanyOrgByFopCompanyOrgId(String id, UserProp userProp) throws Exception;


}

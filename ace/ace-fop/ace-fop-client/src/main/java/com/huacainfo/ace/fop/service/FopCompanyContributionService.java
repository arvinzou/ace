package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopCompanyContribution;
import com.huacainfo.ace.fop.vo.FopCompanyContributionQVo;
import com.huacainfo.ace.fop.vo.FopCompanyContributionVo;

import java.util.List;

/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(企业管理)
 */
public interface FopCompanyContributionService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopCompanyContributionVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract PageResult<FopCompanyContributionVo> findFopCompanyContributionList(FopCompanyContributionQVo condition, int start, int limit, String orderBy) throws Exception;


    public abstract List<FopCompanyContributionVo> findFopCompanyContributionListByCID(String companyId) throws Exception;

    /**
     * @throws
     * @Title:insertFopCompanyContribution
     * @Description: TODO(添加企业管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse insertFopCompanyContribution(FopCompanyContribution obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopCompanyContribution
     * @Description: TODO(更新企业管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse updateFopCompanyContribution(FopCompanyContribution obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopCompanyContributionByPrimaryKey
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopCompanyContribution>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract SingleResult<FopCompanyContributionVo> selectFopCompanyContributionByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopCompanyContributionByFopCompanyContributionId
     * @Description: TODO(删除企业管理)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse deleteFopCompanyContributionByFopCompanyContributionId(String id, UserProp userProp) throws Exception;

    MessageResponse deleteFopCompanyContributionByCID(String cid, UserProp userProp) throws Exception;


}

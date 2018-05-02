package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopAssociation;
import com.huacainfo.ace.fop.vo.FopAssociationQVo;
import com.huacainfo.ace.fop.vo.FopAssociationVo;

/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(企业管理)
 */
public interface FopAssociationService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopAssociationVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract PageResult<FopAssociationVo> findFopAssociationList(FopAssociationQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertFopAssociation
     * @Description: TODO(添加企业管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse insertFopAssociation(FopAssociation obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopAssociation
     * @Description: TODO(更新企业管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse updateFopAssociation(FopAssociation obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopAssociationByPrimaryKey
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopAssociation>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract SingleResult<FopAssociationVo> selectFopAssociationByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopAssociationByFopAssociationId
     * @Description: TODO(删除企业管理)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse deleteFopAssociationByFopAssociationId(String id, UserProp userProp) throws Exception;


}

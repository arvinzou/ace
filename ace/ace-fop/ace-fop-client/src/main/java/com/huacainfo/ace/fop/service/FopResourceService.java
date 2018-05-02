package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopResource;
import com.huacainfo.ace.fop.vo.FopResourceQVo;
import com.huacainfo.ace.fop.vo.FopResourceVo;

/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(企业管理)
 */
public interface FopResourceService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopResourceVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract PageResult<FopResourceVo> findFopResourceList(FopResourceQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertFopResource
     * @Description: TODO(添加企业管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse insertFopResource(FopResource obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopResource
     * @Description: TODO(更新企业管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse updateFopResource(FopResource obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopResourceByPrimaryKey
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopResource>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract SingleResult<FopResourceVo> selectFopResourceByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopResourceByFopResourceId
     * @Description: TODO(删除企业管理)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse deleteFopResourceByFopResourceId(String id, UserProp userProp) throws Exception;


}

package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopCompanyActivity;
import com.huacainfo.ace.fop.vo.FopCompanyActivityQVo;
import com.huacainfo.ace.fop.vo.FopCompanyActivityVo;

/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(企业管理)
 */
public interface FopCompanyActivityService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopCompanyActivityVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract PageResult<FopCompanyActivityVo> findFopCompanyActivityList(FopCompanyActivityQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertFopCompanyActivity
     * @Description: TODO(添加企业管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse insertFopCompanyActivity(FopCompanyActivity obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopCompanyActivity
     * @Description: TODO(更新企业管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse updateFopCompanyActivity(FopCompanyActivity obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopCompanyActivityByPrimaryKey
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopCompanyActivity>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract SingleResult<FopCompanyActivityVo> selectFopCompanyActivityByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopCompanyActivityByFopCompanyActivityId
     * @Description: TODO(删除企业管理)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse deleteFopCompanyActivityByFopCompanyActivityId(String id, UserProp userProp) throws Exception;


}

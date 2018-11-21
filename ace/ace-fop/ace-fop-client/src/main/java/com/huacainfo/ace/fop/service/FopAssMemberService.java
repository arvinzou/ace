package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopAssMember;
import com.huacainfo.ace.fop.model.FopAssociation;
import com.huacainfo.ace.fop.vo.FopAssMemberQVo;
import com.huacainfo.ace.fop.vo.FopAssMemberVo;

/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(企业管理)
 */
public interface FopAssMemberService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopAssMemberVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract PageResult<FopAssMemberVo> findFopAssMemberList(FopAssMemberQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertFopAssMember
     * @Description: TODO(添加企业管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse insertFopAssMember(FopAssMember obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopAssMember
     * @Description: TODO(更新企业管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse updateFopAssMember(FopAssMember obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopAssMemberByPrimaryKey
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopAssMember>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract SingleResult<FopAssMemberVo> selectFopAssMemberByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopAssMemberByFopAssMemberId
     * @Description: TODO(删除企业管理)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse deleteFopAssMemberByFopAssMemberId(String id, UserProp userProp) throws Exception;

    public abstract MessageResponse deleteFopAssMemberByFopAssId(String assId, UserProp userProp) throws Exception;


}

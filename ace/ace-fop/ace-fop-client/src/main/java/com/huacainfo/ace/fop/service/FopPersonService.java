package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopPerson;
import com.huacainfo.ace.fop.vo.FopCompanyVo;
import com.huacainfo.ace.fop.vo.FopPersonQVo;
import com.huacainfo.ace.fop.vo.FopPersonVo;

/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(企业管理)
 */
public interface FopPersonService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopPersonVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract PageResult<FopPersonVo> findFopPersonList(FopPersonQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertFopPerson
     * @Description: TODO(添加企业管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse insertFopPerson(FopPerson obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopPerson
     * @Description: TODO(更新企业管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse updateFopPerson(FopPerson obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopPersonByPrimaryKey
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopPerson>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract SingleResult<FopPersonVo> selectFopPersonByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopPersonByFopPersonId
     * @Description: TODO(删除企业管理)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse deleteFopPersonByFopPersonId(String id, UserProp userProp) throws Exception;


    ResultResponse insertPerson(FopCompanyVo o, UserProp userProp) throws Exception;

    FopPerson selectByMobile(String mobileNumber);
}

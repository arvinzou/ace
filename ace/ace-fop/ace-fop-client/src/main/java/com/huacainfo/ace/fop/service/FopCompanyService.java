package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopCompany;
import com.huacainfo.ace.fop.vo.FopCompanyQVo;
import com.huacainfo.ace.fop.vo.FopCompanyVo;

import java.util.List;
import java.util.Map;

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
    public abstract PageResult<FopCompanyVo> findFopCompanyList(FopCompanyQVo condition, int start, int limit, String orderBy) throws Exception;

    public abstract ResultResponse findCompanyList(FopCompanyQVo condition, int page, int limit, String orderBy) throws Exception;

    public abstract ResultResponse findCompanyGisList() throws Exception;

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
    public abstract MessageResponse insertFopCompany(FopCompanyVo obj, UserProp userProp) throws Exception;

    public abstract MessageResponse insertCompany(String fullName, String lpMobile) throws Exception;

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
    public abstract MessageResponse updateFopCompany(FopCompanyVo obj, UserProp userProp) throws Exception;

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
    public abstract SingleResult<FopCompanyVo> selectFopCompanyByPrimaryKey(String id) throws Exception;

    public abstract ResultResponse selectCompanyByPrimaryKey(String id) throws Exception;

    public abstract ResultResponse selectCompanyInfo(UserProp userProp) throws Exception;

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
    public abstract MessageResponse deleteFopCompanyByFopCompanyId(String id, UserProp userProp) throws Exception;


    public Map<String, Object> selectCompany(Map<String, Object> params) throws Exception;

    FopCompany selectByDepartmentId(String departmentId) throws Exception;


}

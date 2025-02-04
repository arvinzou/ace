package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopCompany;
import com.huacainfo.ace.fop.vo.FopCompanyQVo;
import com.huacainfo.ace.fop.vo.FopCompanyVo;

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
    PageResult<FopCompanyVo> findFopCompanyList(FopCompanyQVo condition, int start, int limit, String orderBy) throws Exception;

    ResultResponse findCompanyList(FopCompanyQVo condition, int page, int limit, String orderBy) throws Exception;

    ResultResponse findCompanyGisList() throws Exception;

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

    MessageResponse insertCompany(String fullName, String isCompany, String lpMobile) throws Exception;

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

    MessageResponse updateCompany(FopCompanyVo obj, UserProp userProp) throws Exception;

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

    ResultResponse selectCompanyByPrimaryKey(String id) throws Exception;

    ResultResponse selectCompanyInfo(UserProp userProp) throws Exception;

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


    public Map<String, Object> selectCompany(Map<String, Object> params) throws Exception;

    FopCompany selectByDepartmentId(String departmentId) throws Exception;

    /**
     * 账号恢复
     *
     * @param id   唯一主键
     * @param type 会员类型 0-企业/个人/银行 1-团体
     * @return
     * @throws Exception
     */
    MessageResponse recoverData(String id, String type, UserProp curUserProp) throws Exception;

    /**
     * 恢复会员身份
     *
     * @param id 唯一主键
     */
    MessageResponse reJoin(String id, UserProp curUserProp) throws Exception;

    /**
     * 上传企业档案附件
     *
     * @param url       附件地址
     * @param category  分类
     * @param companyId 公司ID
     * @return ResultResponse
     */
    ResultResponse uploadAnnex(String url, String category, String companyId);


    SingleResult<FopCompanyVo> findByPK(String id);

    /**
     * 删除电子附件
     *
     * @param id 主键ID
     * @return MessageResponse
     */
    MessageResponse delAnnex(String id);
}

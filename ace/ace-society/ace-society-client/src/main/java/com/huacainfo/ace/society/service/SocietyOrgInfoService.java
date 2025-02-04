package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.SocietyOrgInfo;
import com.huacainfo.ace.society.vo.OrgAdminVo;
import com.huacainfo.ace.society.vo.SocietyOrgInfoQVo;
import com.huacainfo.ace.society.vo.SocietyOrgInfoVo;

import java.util.List;
import java.util.Map;

/**
 * @author: Arvin
 * @version: 2018-09-12
 * @Description: TODO(社会组织信息)
 */
public interface SocietyOrgInfoService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(社会组织信息分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SocietyOrgInfoVo>
     * @author: Arvin
     * @version: 2018-09-12
     */
    PageResult<SocietyOrgInfoVo> findSocietyOrgInfoList(SocietyOrgInfoQVo condition,
                                                        int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertSocietyOrgInfo
     * @Description: TODO(添加社会组织信息)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-12
     */
    MessageResponse insertSocietyOrgInfo(SocietyOrgInfo obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateSocietyOrgInfo
     * @Description: TODO(更新社会组织信息)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-12
     */
    MessageResponse updateSocietyOrgInfo(SocietyOrgInfo obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectSocietyOrgInfoByPrimaryKey
     * @Description: TODO(获取社会组织信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SocietyOrgInfo>
     * @author: Arvin
     * @version: 2018-09-12
     */
    SingleResult<SocietyOrgInfoVo> selectSocietyOrgInfoByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteSocietyOrgInfoBySocietyOrgInfoId
     * @Description: TODO(删除社会组织信息)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-12
     */
    MessageResponse deleteSocietyOrgInfoBySocietyOrgInfoId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核社会组织信息)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-12
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;

    List<SocietyOrgInfoVo> findList(SocietyOrgInfoQVo condition, int start, int limit, String orderBy);

    /**
     * 新组织信息创建
     *
     * @param params 表单参数
     * @return ResultResponse
     */
    ResultResponse newOrgInfo(String crtUserId, SocietyOrgInfoVo params) throws Exception;

    /**
     * 查询组织负责人
     *
     * @param orgId 组织ID
     * @return SingleResult<OrgAdminVo>
     * @throws Exception
     */
    SingleResult<OrgAdminVo> findOrgAdmin(String orgId) throws Exception;

    /**
     * 移除组织负责人
     *
     * @param orgId 组织ID
     * @return SingleResult<OrgAdminVo>
     * @throws Exception
     */
    MessageResponse removeAdmin(String orgId);

    /**
     * 添加/更换 组织负责人
     *
     * @param orgId 组织ID
     * @return SingleResult<OrgAdminVo>
     * @throws Exception
     */
    MessageResponse addAdmin(String orgId, String userId) throws Exception;


    /**
     * 查询 待选负责人列表
     *
     * @param keyword 查询关键字
     * @return SingleResult<OrgAdminVo>
     * @throws Exception
     */
    Map<String, Object> findAdminList(String keyword);
}

package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.OrgAdmin;
import com.huacainfo.ace.society.vo.OrgAdminQVo;
import com.huacainfo.ace.society.vo.OrgAdminVo;

/**
 * @author: Arvin
 * @version: 2018-11-19
 * @Description: TODO(组织管理者列表)
 */
public interface OrgAdminService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(组织管理者列表分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <OrgAdminVo>
     * @author: Arvin
     * @version: 2018-11-19
     */
    PageResult<OrgAdminVo> findOrgAdminList(OrgAdminQVo condition,
                                            int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertOrgAdmin
     * @Description: TODO(添加组织管理者列表)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-19
     */
    MessageResponse insertOrgAdmin(OrgAdmin obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateOrgAdmin
     * @Description: TODO(更新组织管理者列表)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-19
     */
    MessageResponse updateOrgAdmin(OrgAdmin obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectOrgAdminByPrimaryKey
     * @Description: TODO(获取组织管理者列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<OrgAdmin>
     * @author: Arvin
     * @version: 2018-11-19
     */
    SingleResult<OrgAdminVo> selectOrgAdminByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteOrgAdminByOrgAdminId
     * @Description: TODO(删除组织管理者列表)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-19
     */
    MessageResponse deleteOrgAdminByOrgAdminId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核组织管理者列表)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-19
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;
}

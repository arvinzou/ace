package com.huacainfo.ace.taa.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.taa.model.OfficeAdmin;
import com.huacainfo.ace.taa.vo.OfficeAdminQVo;
import com.huacainfo.ace.taa.vo.OfficeAdminVo;

import java.util.List;
import java.util.Map;

/**
 * @author: Arvin
 * @version: 2019-02-28
 * @Description: TODO(内勤人员)
 */
public interface OfficeAdminService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(内勤人员分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<OfficeAdminVo>
     * @author: Arvin
     * @version: 2019-02-28
     */
    PageResult<OfficeAdminVo> findOfficeAdminList(OfficeAdminQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertOfficeAdmin
     * @Description: TODO(添加内勤人员)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-28
     */
    MessageResponse insertOfficeAdmin(OfficeAdmin obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateOfficeAdmin
     * @Description: TODO(更新内勤人员)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-28
     */
    MessageResponse updateOfficeAdmin(OfficeAdmin obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectOfficeAdminByPrimaryKey
     * @Description: TODO(获取内勤人员)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<OfficeAdmin>
     * @author: Arvin
     * @version: 2019-02-28
     */
    SingleResult<OfficeAdminVo> selectOfficeAdminByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteOfficeAdminByOfficeAdminId
     * @Description: TODO(删除内勤人员)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-28
     */
    MessageResponse deleteByPrimaryKey(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核内勤人员)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-28
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:importXls
     * @Description: TODO(Excel导入资源数据)
     * @param: @param list
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-28
     */
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult<Map<String,Object>>
     * @author: Arvin
     * @version: 2019-02-28
     */
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception;


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: Arvin
     * @version: 2019-02-28
     */
    public Map<String, Object> getListByCondition(Map<String, Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除内勤人员）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-28
     */
    public MessageResponse deleteOfficeAdminByOfficeAdminIds(String[] id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-28
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;

    /**
     * @Description: TODO(根据昵称查询微信用户)
     */
    List<Map<String,Object>> selectList(String nickname);
}

package com.huacainfo.ace.partyschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.partyschool.model.EnrollRoster;
import com.huacainfo.ace.partyschool.vo.EnrollRosterQVo;
import com.huacainfo.ace.partyschool.vo.EnrollRosterVo;

import java.util.List;
import java.util.Map;

/**
 * @author: Arvin
 * @version: 2019-01-16
 * @Description: TODO(报名花名册管理)
 */
public interface EnrollRosterService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(报名花名册管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <EnrollRosterVo>
     * @author: Arvin
     * @version: 2019-01-16
     */
    PageResult<EnrollRosterVo> findEnrollRosterList(EnrollRosterQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertEnrollRoster
     * @Description: TODO(添加报名花名册管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-16
     */
    MessageResponse insertEnrollRoster(EnrollRoster obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateEnrollRoster
     * @Description: TODO(更新报名花名册管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-16
     */
    MessageResponse updateEnrollRoster(EnrollRoster obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectEnrollRosterByPrimaryKey
     * @Description: TODO(获取报名花名册管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EnrollRoster>
     * @author: Arvin
     * @version: 2019-01-16
     */
    SingleResult<EnrollRosterVo> selectEnrollRosterByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteEnrollRosterByEnrollRosterId
     * @Description: TODO(删除报名花名册管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-16
     */
    MessageResponse deleteEnrollRosterByEnrollRosterId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核报名花名册管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-16
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
     * @version: 2019-01-16
     */
    public MessageResponse importXls(String clsId, List<Map<String, Object>> list, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult<Map<String,Object>>
     * @author: Arvin
     * @version: 2019-01-16
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
     * @version: 2019-01-16
     */
    public Map<String, Object> getListByCondition(Map<String, Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除报名花名册管理）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-16
     */
    public MessageResponse deleteEnrollRosterByEnrollRosterIds(String[] id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-16
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;

    /**
     * 是否准许报名
     *
     * @param name 学员姓名
     * @return t/f
     */
    boolean isAllowed(String name);

    /**
     * 批量开启/关闭报名
     *
     * @param clsId  班级ID
     * @param status 开启/关闭  1-开，0-关
     * @return MessageResponse
     * @throws Exception
     */
    MessageResponse updateStatusByClsId(String clsId, String status, UserProp curUserProp);

    /**
     * 批量删除报名数据
     *
     * @param clsId 班级ID
     * @return MessageResponse
     */
    MessageResponse batchDel(String clsId);
}

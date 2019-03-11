package com.huacainfo.ace.policeschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.policeschool.model.ZkAttData;
import com.huacainfo.ace.policeschool.vo.ZkAttDataQVo;
import com.huacainfo.ace.policeschool.vo.ZkAttDataVo;

import java.util.List;
import java.util.Map;

/**
 * @author: Arvin
 * @version: 2019-02-20
 * @Description: TODO(中控考勤数据源)
 */
public interface ZkAttDataService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(中控考勤数据源分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ZkAttDataVo>
     * @author: Arvin
     * @version: 2019-02-20
     */
    PageResult<ZkAttDataVo> findZkAttDataList(ZkAttDataQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertZkAttData
     * @Description: TODO(添加中控考勤数据源)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    MessageResponse insertZkAttData(ZkAttData obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateZkAttData
     * @Description: TODO(更新中控考勤数据源)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    MessageResponse updateZkAttData(ZkAttData obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectZkAttDataByPrimaryKey
     * @Description: TODO(获取中控考勤数据源)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ZkAttData>
     * @author: Arvin
     * @version: 2019-02-20
     */
    SingleResult<ZkAttDataVo> selectZkAttDataByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteZkAttDataByZkAttDataId
     * @Description: TODO(删除中控考勤数据源)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    MessageResponse deleteZkAttDataByZkAttDataId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核中控考勤数据源)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
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
     * @version: 2019-02-20
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
     * @version: 2019-02-20
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
     * @version: 2019-02-20
     */
    public Map<String, Object> getListByCondition(Map<String, Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除中控考勤数据源）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    public MessageResponse deleteZkAttDataByZkAttDataIds(String[] id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;
}

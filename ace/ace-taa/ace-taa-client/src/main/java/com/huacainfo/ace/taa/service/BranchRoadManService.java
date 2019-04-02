package com.huacainfo.ace.taa.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.taa.model.BranchRoadMan;
import com.huacainfo.ace.taa.vo.BranchRoadManVo;
import com.huacainfo.ace.taa.vo.BranchRoadManQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: heshaung
 * @version: 2019-03-29
 * @Description: TODO(BranchRoadMan)
 */
public interface BranchRoadManService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(BranchRoadMan分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <BranchRoadManVo>
     * @author: heshaung
     * @version: 2019-03-29
     */
    PageResult<BranchRoadManVo> findBranchRoadManList(BranchRoadManQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertBranchRoadMan
     * @Description: TODO(添加BranchRoadMan)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshaung
     * @version: 2019-03-29
     */
    MessageResponse insertBranchRoadMan(BranchRoadMan obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateBranchRoadMan
     * @Description: TODO(更新BranchRoadMan)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshaung
     * @version: 2019-03-29
     */
    MessageResponse updateBranchRoadMan(BranchRoadMan obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectBranchRoadManByPrimaryKey
     * @Description: TODO(获取BranchRoadMan)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<BranchRoadMan>
     * @author: heshaung
     * @version: 2019-03-29
     */
    SingleResult<BranchRoadMan> selectBranchRoadManByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteBranchRoadManByBranchRoadManId
     * @Description: TODO(删除BranchRoadMan)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshaung
     * @version: 2019-03-29
     */
    MessageResponse deleteBranchRoadManByBranchRoadManId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核BranchRoadMan)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshaung
     * @version: 2019-03-29
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
     * @author: heshaung
     * @version: 2019-03-29
     */
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult<Map < String, Object>>
     * @author: heshaung
     * @version: 2019-03-29
     */
    public ListResult<Map<String, Object>> getList(String roadSectionId) throws Exception;


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String, Object>
     * @author: heshaung
     * @version: 2019-03-29
     */
    public Map<String, Object> getListByCondition(Map<String, Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除BranchRoadMan ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshaung
     * @version: 2019-03-29
     */
    public MessageResponse deleteBranchRoadManByBranchRoadManIds(String[] id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshaung
     * @version: 2019-03-29
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;


    SingleResult<BranchRoadManVo> selectVoBranchRoadManByPrimaryKey(String id) throws Exception;
}

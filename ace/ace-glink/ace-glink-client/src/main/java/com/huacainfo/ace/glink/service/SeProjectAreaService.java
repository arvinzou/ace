package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.glink.model.SeProjectArea;
import com.huacainfo.ace.glink.vo.SeProjectAreaVo;
import com.huacainfo.ace.glink.vo.SeProjectAreaQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: heshuang
 * @version: 2019-04-18
 * @Description: TODO(项目区域数据)
 */
public interface SeProjectAreaService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(项目区域数据分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SeProjectAreaVo>
     * @author: heshuang
     * @version: 2019-04-18
     */
    PageResult
            <SeProjectAreaVo> findSeProjectAreaList(SeProjectAreaQVo condition,
                                                    int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertSeProjectArea
     * @Description: TODO(添加项目区域数据)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    MessageResponse insertSeProjectArea(SeProjectArea o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateSeProjectArea
     * @Description: TODO(更新项目区域数据)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    MessageResponse updateSeProjectArea(SeProjectArea obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectSeProjectAreaByPrimaryKey
     * @Description: TODO(获取项目区域)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeProjectArea>
     * @author: heshuang
     * @version: 2019-04-18
     */
    SingleResult
            <SeProjectAreaVo> selectSeProjectAreaByPrimaryKey(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteSeProjectAreaBySeProjectAreaId
     * @Description: TODO(删除项目区域数据)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    MessageResponse deleteSeProjectAreaBySeProjectAreaId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核项目区域数据)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
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
     * @author: heshuang
     * @version: 2019-04-18
     */
    MessageResponse importXls(List
                                      <Map
                                              <String
                                                      , Object>> list, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult
     * <Map
     * <String
     * ,Object>>
     * @author: heshuang
     * @version: 2019-04-18
     */
    ListResult
            <Map
                    <String
                            , Object>> getList(Map
                                                       <String
                                                               , Object> p) throws Exception;


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map
     * <String
     * ,Object>
     * @author: heshuang
     * @version: 2019-04-18
     */
    Map
            <String
                    , Object> getListByCondition(Map
                                                         <String
                                                                 , Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除项目区域数据 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    MessageResponse deleteSeProjectAreaBySeProjectAreaIds(String[] id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;

    /**
     * 同步数据
     *
     * @param userProp
     * @return
     */
    MessageResponse syncProjectData(UserProp userProp);

    /**
     * 加载树
     *
     * @return
     * @throws Exception
     */
    public abstract List<Tree> selectTreeList() throws Exception;
}

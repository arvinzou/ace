package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.glink.model.SceneConfig;
import com.huacainfo.ace.glink.vo.SceneConfigVo;
import com.huacainfo.ace.glink.vo.SceneConfigQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: huacai003
 * @version: 2019-04-15
 * @Description: TODO(场景设置)
 */
public interface SceneConfigService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(场景设置分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SceneConfigVo>
     * @author: huacai003
     * @version: 2019-04-15
     */
    PageResult<SceneConfigVo> findSceneConfigList(SceneConfigQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertSceneConfig
     * @Description: TODO(添加场景设置)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-15
     */
    MessageResponse insertSceneConfig(SceneConfig obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateSceneConfig
     * @Description: TODO(更新场景设置)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-15
     */
    MessageResponse updateSceneConfig(SceneConfig obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectSceneConfigByPrimaryKey
     * @Description: TODO(获取场景设置)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SceneConfig>
     * @author: huacai003
     * @version: 2019-04-15
     */
    SingleResult<SceneConfigVo> selectSceneConfigByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteSceneConfigBySceneConfigId
     * @Description: TODO(删除场景设置)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-15
     */
    MessageResponse deleteSceneConfigBySceneConfigId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核场景设置)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-15
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
     * @author: huacai003
     * @version: 2019-04-15
     */
    MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception;


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
     * @author: huacai003
     * @version: 2019-04-15
     */
    ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception;


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map
     * <String
     * ,Object>
     * @author: huacai003
     * @version: 2019-04-15
     */
    Map<String, Object> getListByCondition(Map<String, Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除场景设置）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-15
     */
    MessageResponse deleteSceneConfigBySceneConfigIds(String[] id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-15
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;
}

package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.glink.model.LeScene;
import com.huacainfo.ace.glink.vo.LeSceneVo;
import com.huacainfo.ace.glink.vo.LeSceneQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: Arvin
 * @version: 2019-05-13
 * @Description: TODO(弱电场景管理)
 */
public interface LeSceneService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(弱电场景管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<LeSceneVo>
     * @author: Arvin
     * @version: 2019-05-13
     */
    PageResult<LeSceneVo> findLeSceneList(LeSceneQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertLeScene
     * @Description: TODO(添加弱电场景管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-05-13
     */
    MessageResponse insertLeScene(LeScene obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateLeScene
     * @Description: TODO(更新弱电场景管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-05-13
     */
    MessageResponse updateLeScene(LeScene obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectLeSceneByPrimaryKey
     * @Description: TODO(获取弱电场景管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LeScene>
     * @author: Arvin
     * @version: 2019-05-13
     */
    SingleResult<LeSceneVo> selectLeSceneByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteLeSceneByLeSceneId
     * @Description: TODO(删除弱电场景管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-05-13
     */
    MessageResponse deleteLeSceneByLeSceneId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核弱电场景管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-05-13
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
     * @version: 2019-05-13
     */
    MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult<Map < String, Object>>
     * @author: Arvin
     * @version: 2019-05-13
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
     * @author: Arvin
     * @version: 2019-05-13
     */
    Map<String, Object> getListByCondition(Map<String, Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除弱电场景管理 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-05-13
     */
    MessageResponse deleteLeSceneByLeSceneIds(String[] id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-05-13
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;

    /**
     * 同步中控机数据
     *
     * @param curUserProp 当前登录用户信息
     * @return MessageResponse 处理结果
     * @throws Exception
     */
    MessageResponse syncData(UserProp curUserProp);

    LeScene findBySceneNum(String sceneNum);
}

package com.huacainfo.ace.taa.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.taa.model.RoadMan;
import com.huacainfo.ace.taa.vo.RoadManVo;
import com.huacainfo.ace.taa.vo.RoadManQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: 陈晓克
 * @version: 2019-01-04
 * @Description: TODO(路长)
 */
public interface RoadManService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(路长分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <RoadManVo>
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    PageResult<RoadManVo> findRoadManList(RoadManQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertRoadMan
     * @Description: TODO(添加路长)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    MessageResponse insertRoadMan(RoadMan obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateRoadMan
     * @Description: TODO(更新路长)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    MessageResponse updateRoadMan(RoadMan obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectRoadManByPrimaryKey
     * @Description: TODO(获取路长)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<RoadMan>
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    SingleResult<RoadManVo> selectRoadManByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteRoadManByRoadManId
     * @Description: TODO(删除路长)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    MessageResponse deleteRoadManByRoadManId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核路长)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
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
     * @author: 陈晓克
     * @version: 2019-01-04
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
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception;


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(路长查询，用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: chenxiaoke
     * @version: 2019年1月04日 下午1:24:14
     */
    public Map<String, Object> getListByCondition(Map<String, Object> params);

    /**
     * 获取所有路长花名册 - 通讯录形式
     *
     * @return ResultResponse
     * @throws Exception
     */
    ResultResponse findRoster();
}

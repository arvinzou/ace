package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.glink.model.TopDevice;
import com.huacainfo.ace.glink.vo.TopDeviceVo;
import com.huacainfo.ace.glink.vo.TopDeviceQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: heshuang
 * @version: 2019-04-10
 * @Description: TODO(设备管理)
 */
public interface TopDeviceService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(设备管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TopDeviceVo>
     * @author: heshuang
     * @version: 2019-04-10
     */
    PageResult
            <TopDeviceVo> findTopDeviceList(TopDeviceQVo condition,
                                            int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertTopDevice
     * @Description: TODO(添加设备管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-10
     */
    MessageResponse insertTopDevice(TopDevice obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateTopDevice
     * @Description: TODO(更新设备管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-10
     */
    MessageResponse updateTopDevice(TopDevice obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectTopDeviceByPrimaryKey
     * @Description: TODO(获取设备管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TopDevice>
     * @author: heshuang
     * @version: 2019-04-10
     */
    SingleResult
            <TopDeviceVo> selectTopDeviceByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteTopDeviceByTopDeviceId
     * @Description: TODO(删除设备管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-10
     */
    MessageResponse deleteTopDeviceByTopDeviceId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核设备管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-10
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
     * @version: 2019-04-10
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
     * @version: 2019-04-10
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
     * @version: 2019-04-10
     */
    Map
            <String
                    , Object> getListByCondition(Map
                                                         <String
                                                                 , Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除设备管理 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-10
     */
    MessageResponse deleteTopDeviceByTopDeviceIds(String[] id, UserProp userProp) throws
            Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-10
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;
}

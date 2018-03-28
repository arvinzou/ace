package com.huacainfo.ace.woc.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.Vehicle;
import com.huacainfo.ace.woc.vo.VehicleQVo;
import com.huacainfo.ace.woc.vo.VehicleVo;

import java.util.Map;

/**
 * @author: 王恩
 * @version: 2018-03-09
 * @Description: TODO(人员信息)
 */
public interface VehicleService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(人员信息分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<VehicleVo>
     * @author: 王恩
     * @version: 2018-03-09
     */
    public abstract PageResult<VehicleVo> findVehicleList(VehicleQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertVehicle
     * @Description: TODO(添加人员信息)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-09
     */
    public abstract MessageResponse insertVehicle(Vehicle obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateVehicle
     * @Description: TODO(更新人员信息)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-09
     */
    public abstract MessageResponse updateVehicle(Vehicle obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectVehicleByPrimaryKey
     * @Description: TODO(获取人员信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Vehicle>
     * @author: 王恩
     * @version: 2018-03-09
     */
    public abstract SingleResult<VehicleVo> selectVehicleByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteVehicleByVehicleId
     * @Description: TODO(删除人员信息)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-09
     */
    public abstract MessageResponse deleteVehicleByVehicleId(String id, UserProp userProp) throws Exception;

    /**
     * 根据关键词，查询车辆信息
     *
     * @param keyWord
     * @return
     */
    Map<String, Object> selectListByKeyWord(String keyWord);
}

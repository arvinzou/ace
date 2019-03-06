package com.huacainfo.ace.taa.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.taa.model.RoadGps;

import java.util.Map;
import java.util.List;

/**
 * @author: 陈晓克
 * @version: 2019-01-08
 * @Description: TODO(GPS)
 */
public interface RoadGpsService {


    /**
     * @throws
     * @Title:insertRoadGps
     * @Description: TODO(添加GPS)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-08
     */
    MessageResponse insertRoadGps(List<RoadGps> obj, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult<Map < String, Object>>
     * @author: 陈晓克
     * @version: 2019-01-08
     */
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception;


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除GPS
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-08
     */
    public MessageResponse deleteRoadGpsByRoadGpsIds(String[] id, UserProp userProp) throws Exception;

    /**
     * 获取最近路段信息
     *
     * @param lat    纬度坐标
     * @param lon    经度坐标
     * @param radius 扫描半径距离，单位：米
     * @return ResultResponse
     * @throws Exception
     */
    ResultResponse getCloseRoadSection(double lat, double lon, int radius);

    /**
     * 重置路段采集数据
     *
     * @param sectionId 路段ID
     * @param userProp  操作员
     * @return ResultResponse
     * @throws Exception
     */
    MessageResponse resetSectionGPS(String sectionId, UserProp userProp);
}

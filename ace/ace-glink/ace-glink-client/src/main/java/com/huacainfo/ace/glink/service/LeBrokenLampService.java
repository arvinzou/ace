package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.glink.model.LeBrokenLamp;
import com.huacainfo.ace.glink.vo.LeBrokenLampQVo;
import com.huacainfo.ace.glink.vo.LeBrokenLampVo;

/**
 * @author: Arvin
 * @version: 2019-04-22
 * @Description: TODO(弱电 - 故障设备情况)
 */
public interface LeBrokenLampService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(弱电 - 故障设备情况分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<LeBrokenLampVo>
     * @author: Arvin
     * @version: 2019-04-22
     */
    PageResult<LeBrokenLampVo> findLeBrokenLampList(LeBrokenLampQVo condition,
                                                    int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertLeBrokenLamp
     * @Description: TODO(添加弱电 - 故障设备情况)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-22
     */
    MessageResponse insertLeBrokenLamp(LeBrokenLamp obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateLeBrokenLamp
     * @Description: TODO(更新弱电 - 故障设备情况)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-22
     */
    MessageResponse updateLeBrokenLamp(LeBrokenLamp obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectLeBrokenLampByPrimaryKey
     * @Description: TODO(获取弱电 - 故障设备情况)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LeBrokenLamp>
     * @author: Arvin
     * @version: 2019-04-22
     */
    SingleResult<LeBrokenLampVo> selectLeBrokenLampByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteLeBrokenLampByLeBrokenLampId
     * @Description: TODO(删除弱电 - 故障设备情况)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-22
     */
    MessageResponse deleteLeBrokenLampByLeBrokenLampId(String id, UserProp userProp) throws Exception;

    /**
     * 按指定日期,调用一次弱电接口：   获取 武汉设备故障情况
     * @param date 20190416
     */
    MessageResponse getBrokenLampDetail(String date);
}

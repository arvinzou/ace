package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.glink.model.SeAlarmData;
import com.huacainfo.ace.glink.vo.SeAlarmDataVo;
import com.huacainfo.ace.glink.vo.SeAlarmDataQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: luocan
 * @version: 2019-04-18
 * @Description: TODO(强电 - 报警数据)
 */
public interface SeAlarmDataService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(强电 - 报警数据分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SeAlarmDataVo>
     * @author: luocan
     * @version: 2019-04-18
     */
    PageResult<SeAlarmDataVo> findSeAlarmDataList(SeAlarmDataQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertSeAlarmData
     * @Description: TODO(添加强电 - 报警数据)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    MessageResponse insertSeAlarmData(List<SeAlarmData> list, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateSeAlarmData
     * @Description: TODO(更新强电 - 报警数据)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    MessageResponse updateSeAlarmData(SeAlarmData obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectSeAlarmDataByPrimaryKey
     * @Description: TODO(获取强电 - 报警数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeAlarmData>
     * @author: luocan
     * @version: 2019-04-18
     */
    SingleResult<SeAlarmDataVo> selectSeAlarmDataByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteSeAlarmDataBySeAlarmDataId
     * @Description: TODO(删除强电 - 报警数据)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    MessageResponse deleteSeAlarmDataBySeAlarmDataId(String id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:importXls
     * @Description: TODO(Excel导入资源数据)
     * @param: @param list
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception;




    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除强电 - 报警数据 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    MessageResponse deleteSeAlarmDataBySeAlarmDataIds(List<String> list, UserProp userProp) throws Exception;
}

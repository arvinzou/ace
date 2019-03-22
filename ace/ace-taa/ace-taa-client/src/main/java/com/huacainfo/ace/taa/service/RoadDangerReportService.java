package com.huacainfo.ace.taa.service;

import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.taa.model.RoadDangerReport;
import com.huacainfo.ace.taa.vo.RoadDangerReportQVo;
import com.huacainfo.ace.taa.vo.RoadDangerReportVo;

import java.util.List;
import java.util.Map;

/**
 * @author: 何双
 * @version: 2019-03-15
 * @Description: TODO(路况上报)
 */
public interface RoadDangerReportService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(路况上报分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <RordDangerReportVo>
     * @author: 何双
     * @version: 2019-03-15
     */
    PageResult<RoadDangerReportVo> findRordDangerReportList(RoadDangerReportQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertRordDangerReport
     * @Description: TODO(添加路况上报)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    MessageResponse insertRordDangerReport(RoadDangerReportQVo obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateRordDangerReport
     * @Description: TODO(更新路况上报)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    MessageResponse updateRordDangerReport(RoadDangerReportQVo obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectRordDangerReportByPrimaryKey
     * @Description: TODO(获取路况上报)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<RordDangerReport>
     * @author: 何双
     * @version: 2019-03-15
     */
    SingleResult<RoadDangerReportVo> selectRordDangerReportByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteRordDangerReportByRordDangerReportId
     * @Description: TODO(删除路况上报)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */


    MessageResponse deleteByPrimaryKey(String id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:importXls
     * @Description: TODO(Excel导入资源数据)
     * @param: @param list
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;

    /**
     * 更新状态和撤回原因
     *
     * @param id
     * @param status
     * @param reason
     * @param userProp
     * @return
     * @throws Exception
     */
    public MessageResponse updateReason(String id, String status, String reason, UserProp userProp) throws Exception;

    /**
     * 获取路况列表 --小程序端展示
     *
     * @param user      查询用户
     * @param condition 条件查询
     * @param page      分页条件
     * @return
     * @throws Exception
     */
    ResultResponse findViewList(UserProp user, RoadDangerReportQVo condition, PageParamNoChangeSord page) throws Exception;


    /**
     * 获取用户所有权限
     *
     * @param userId
     * @return
     */
    List<Map<String, Object>> selectUserRole(String userId);

    /**
     * 获取用户是否有路况权限
     *
     * @param userId
     * @return
     */
    List<Map<String, Object>> selectUserRoadRole(String userId);
}

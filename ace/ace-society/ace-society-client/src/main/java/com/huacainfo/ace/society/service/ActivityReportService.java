package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.ActivityReport;
import com.huacainfo.ace.society.vo.ActivityReportVo;
import com.huacainfo.ace.society.vo.ActivityReportQVo;

/**
 * @author: huacai003
 * @version: 2018-09-13
 * @Description: TODO(活动报道)
 */
public interface ActivityReportService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(活动报道分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ActivityReportVo>
     * @author: huacai003
     * @version: 2018-09-13
     */
    PageResult<ActivityReportVo> findActivityReportList(ActivityReportQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertActivityReport
     * @Description: TODO(添加活动报道)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-13
     */
    MessageResponse insertActivityReport(ActivityReport obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateActivityReport
     * @Description: TODO(更新活动报道)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-13
     */
    MessageResponse updateActivityReport(ActivityReport obj, UserProp userProp) throws Exception;

    ResultResponse WxUpdateActivityReport(ActivityReport o, WxUser wxUser) throws Exception;

    /**
     * @throws
     * @Title:selectActivityReportByPrimaryKey
     * @Description: TODO(获取活动报道)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ActivityReport>
     * @author: huacai003
     * @version: 2018-09-13
     */
    SingleResult<ActivityReportVo> selectActivityReportByPrimaryKey(String id) throws Exception;

    ActivityReport selectActivityReportByActivityId(String id, WxUser wxUser) throws Exception;

    /**
     * @throws
     * @Title:deleteActivityReportByActivityReportId
     * @Description: TODO(删除活动报道)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-13
     */
    MessageResponse deleteActivityReportByActivityReportId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核活动报道)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-13
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;
}

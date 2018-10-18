package com.huacainfo.ace.society.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.ActivityDetail;
import com.huacainfo.ace.society.vo.ActivityDetailVo;
import com.huacainfo.ace.society.vo.ActivityDetailQVo;
/**
* @author: huacai003
* @version: 2018-09-13
* @Description:  TODO(活动报道)
*/
public interface ActivityDetailService {
/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(活动报道分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<ActivityDetailVo>
    * @throws
    * @author: huacai003
    * @version: 2018-09-13
    */
    PageResult
    <ActivityDetailVo> findActivityDetailList(ActivityDetailQVo condition,
        int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insertActivityDetail
        * @Description: TODO(添加活动报道)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: huacai003
        * @version: 2018-09-13
        */
        MessageResponse insertActivityDetail(ActivityDetail obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:updateActivityDetail
        * @Description: TODO(更新活动报道)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: huacai003
        * @version: 2018-09-13
        */
        MessageResponse updateActivityDetail(ActivityDetail obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:selectActivityDetailByPrimaryKey
        * @Description: TODO(获取活动报道)
        * @param: @param id
        * @param: @throws Exception
        * @return: SingleResult<ActivityDetail>
        * @throws
        * @author: huacai003
        * @version: 2018-09-13
        */
        SingleResult<ActivityDetailVo> selectActivityDetailByPrimaryKey(String id) throws Exception;
        ResultResponse personalActivitydetails(String activityId, String unionId) throws Exception;

            /**
            *
            * @Title:deleteActivityDetailByActivityDetailId
            * @Description: TODO(删除活动报道)
            * @param: @param id
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: huacai003
            * @version: 2018-09-13
            */
            MessageResponse deleteActivityDetailByActivityDetailId(String id,UserProp userProp) throws Exception;

            /**
            *
            * @Title:audit
            * @Description: TODO(审核活动报道)
            * @param: @param id bean.id
            * @param: @param rst 审核结果 3-通过 4-拒绝
            * @param: @param remark 审核备注
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: huacai003
            * @version: 2018-09-13
            */
            MessageResponse audit(String id,String rst, String remark, UserProp userProp) throws Exception;
            }

package com.huacainfo.ace.society.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.ActivityDetail;
import com.huacainfo.ace.society.service.ActivityDetailService;
import com.huacainfo.ace.society.vo.ActivityDetailVo;
import com.huacainfo.ace.society.vo.ActivityDetailQVo;

@Controller
@RequestMapping("/activityDetail")
/**
* @author: huacai003
* @version: 2018-09-13
* @Description:  TODO(活动报道)
*/
public class ActivityDetailController extends SocietyBaseController {


private static final long serialVersionUID = 1L;
Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
private ActivityDetailService activityDetailService;

/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(活动报道分页查询)
* @param:        @param condition
* @param:        @param page
* @param:        @return
* @param:        @throws Exception
* @return:       PageResult
<ActivityDetailVo>
    * @throws
    * @author: huacai003
    * @version: 2018-09-13
    */
    @RequestMapping(value = "/findActivityDetailList")
    @ResponseBody
    public PageResult
    <ActivityDetailVo> findActivityDetailList(ActivityDetailQVo condition,
        PageParamNoChangeSord page) throws Exception {

        PageResult
        <ActivityDetailVo> rst = this.activityDetailService
            .findActivityDetailList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
            if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
            }

            return rst;
            }

            /**
            *
            * @Title:insertActivityDetail
            * @Description: TODO(添加活动报道)
            * @param: @param jsons
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: huacai003
            * @version: 2018-09-13
            */
            @RequestMapping(value = "/insertActivityDetail")
            @ResponseBody
            public MessageResponse insertActivityDetail(String jsons) throws Exception {
            ActivityDetail obj = JSON.parseObject(jsons, ActivityDetail.class);
            return this.activityDetailService.insertActivityDetail(obj, this.getCurUserProp());
            }

            /**
            *
            * @Title:updateActivityDetail
            * @Description: TODO(更新活动报道)
            * @param: @param jsons
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: huacai003
            * @version: 2018-09-13
            */
            @RequestMapping(value = "/updateActivityDetail")
            @ResponseBody
            public MessageResponse updateActivityDetail(String jsons) throws Exception {
            ActivityDetail obj = JSON.parseObject(jsons, ActivityDetail.class);
            return this.activityDetailService.updateActivityDetail(obj, this.getCurUserProp());
            }

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
            @RequestMapping(value = "/selectActivityDetailByPrimaryKey")
            @ResponseBody
            public SingleResult
            <ActivityDetailVo> selectActivityDetailByPrimaryKey(String id)throws Exception {
                return this.activityDetailService.selectActivityDetailByPrimaryKey(id);
                }

                /**
                *
                * @Title:deleteActivityDetailByActivityDetailId
                * @Description: TODO(删除活动报道)
                * @param: @param jsons
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
                * @author: huacai003
                * @version: 2018-09-13
                */
                @RequestMapping(value = "/deleteActivityDetailByActivityDetailId")
                @ResponseBody
                public MessageResponse deleteActivityDetailByActivityDetailId(String jsons) throws Exception {
                JSONObject json = JSON.parseObject(jsons);
                String id = json.getString("id");
                return this.activityDetailService.deleteActivityDetailByActivityDetailId(id, this.getCurUserProp());
                }

                /**
                *
                * @Title:audit
                * @Description: TODO(审核活动报道)
                * @param: @param id bean.id
                * @param: @param rst 审核结果 3-通过 4-拒绝
                * @param: @param message 审核说明
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
                * @author: huacai003
                * @version: 2018-09-13
                */
                @RequestMapping(value = "/audit")
                @ResponseBody
                public MessageResponse audit(String id,String rst, String message) throws Exception {

                return this.activityDetailService.audit(id, rst, message, this.getCurUserProp());
                }
                }

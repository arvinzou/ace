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
import com.huacainfo.ace.society.model.Behavior;
import com.huacainfo.ace.society.service.BehaviorService;
import com.huacainfo.ace.society.vo.BehaviorVo;
import com.huacainfo.ace.society.vo.BehaviorQVo;

@Controller
@RequestMapping("/behavior")
/**
* @author: lcan
* @version: 2018-09-11
* @Description:  TODO(市民行为详情)
*/
public class BehaviorController extends SocietyBaseController {


private static final long serialVersionUID = 1L;
Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
private BehaviorService behaviorService;

/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(市民行为详情分页查询)
* @param:        @param condition
* @param:        @param page
* @param:        @return
* @param:        @throws Exception
* @return:       PageResult
<BehaviorVo>
    * @throws
    * @author: lcan
    * @version: 2018-09-11
    */
    @RequestMapping(value = "/findBehaviorList")
    @ResponseBody
    public PageResult
    <BehaviorVo> findBehaviorList(BehaviorQVo condition,
        PageParamNoChangeSord page) throws Exception {

        PageResult
        <BehaviorVo> rst = this.behaviorService
            .findBehaviorList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
            if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
            }

            return rst;
            }

            /**
            *
            * @Title:insertBehavior
            * @Description: TODO(添加市民行为详情)
            * @param: @param jsons
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: lcan
            * @version: 2018-09-11
            */
            @RequestMapping(value = "/insertBehavior")
            @ResponseBody
            public MessageResponse insertBehavior(String jsons) throws Exception {
            Behavior obj = JSON.parseObject(jsons, Behavior.class);
            return this.behaviorService.insertBehavior(obj, this.getCurUserProp());
            }

            /**
            *
            * @Title:updateBehavior
            * @Description: TODO(更新市民行为详情)
            * @param: @param jsons
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: lcan
            * @version: 2018-09-11
            */
            @RequestMapping(value = "/updateBehavior")
            @ResponseBody
            public MessageResponse updateBehavior(String jsons) throws Exception {
            Behavior obj = JSON.parseObject(jsons, Behavior.class);
            return this.behaviorService.updateBehavior(obj, this.getCurUserProp());
            }

            /**
            *
            * @Title:selectBehaviorByPrimaryKey
            * @Description: TODO(获取市民行为详情)
            * @param: @param id
            * @param: @throws Exception
            * @return: SingleResult<Behavior>
            * @throws
            * @author: lcan
            * @version: 2018-09-11
            */
            @RequestMapping(value = "/selectBehaviorByPrimaryKey")
            @ResponseBody
            public SingleResult
            <BehaviorVo> selectBehaviorByPrimaryKey(String id)throws Exception {
                return this.behaviorService.selectBehaviorByPrimaryKey(id);
                }

                /**
                *
                * @Title:deleteBehaviorByBehaviorId
                * @Description: TODO(删除市民行为详情)
                * @param: @param jsons
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
                * @author: lcan
                * @version: 2018-09-11
                */
                @RequestMapping(value = "/deleteBehaviorByBehaviorId")
                @ResponseBody
                public MessageResponse deleteBehaviorByBehaviorId(String jsons) throws Exception {
                JSONObject json = JSON.parseObject(jsons);
                String id = json.getString("id");
                return this.behaviorService.deleteBehaviorByBehaviorId(id, this.getCurUserProp());
                }

                /**
                *
                * @Title:audit
                * @Description: TODO(审核市民行为详情)
                * @param: @param id bean.id
                * @param: @param rst 审核结果 3-通过 4-拒绝
                * @param: @param remark 审核备注
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
                * @author: lcan
                * @version: 2018-09-11
                */
                @RequestMapping(value = "/audit")
                @ResponseBody
                public MessageResponse audit(String id,String rst, String remark) throws Exception {

                return this.behaviorService.audit(id, rst, remark, this.getCurUserProp());
                }
                }

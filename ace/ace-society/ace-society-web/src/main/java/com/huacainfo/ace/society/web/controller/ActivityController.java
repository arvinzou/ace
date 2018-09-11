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
import com.huacainfo.ace.society.model.Activity;
import com.huacainfo.ace.society.service.ActivityService;
import com.huacainfo.ace.society.vo.ActivityVo;
import com.huacainfo.ace.society.vo.ActivityQVo;

@Controller
@RequestMapping("/activity")
/**
 * @author: huacai003
 * @version: 2018-09-11
 * @Description: TODO(线下活动)
 */
public class ActivityController extends SocietyBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ActivityService activityService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(线下活动分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <ActivityVo>
     * @author: huacai003
     * @version: 2018-09-11
     */
    @RequestMapping(value = "/findActivityList")
    @ResponseBody
    public PageResult<ActivityVo> findActivityList(ActivityQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<ActivityVo> rst = this.activityService.findActivityList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertActivity
     * @Description: TODO(添加线下活动)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-11
     */
    @RequestMapping(value = "/insertActivity")
    @ResponseBody
    public MessageResponse insertActivity(String jsons) throws Exception {
        Activity obj = JSON.parseObject(jsons, Activity.class);
        return this.activityService.insertActivity(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateActivity
     * @Description: TODO(更新线下活动)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-11
     */
    @RequestMapping(value = "/updateActivity")
    @ResponseBody
    public MessageResponse updateActivity(String jsons) throws Exception {
        Activity obj = JSON.parseObject(jsons, Activity.class);
        return this.activityService.updateActivity(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectActivityByPrimaryKey
     * @Description: TODO(获取线下活动)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Activity>
     * @author: huacai003
     * @version: 2018-09-11
     */
    @RequestMapping(value = "/selectActivityByPrimaryKey")
    @ResponseBody
    public SingleResult
            <ActivityVo> selectActivityByPrimaryKey(String id) throws Exception {
        return this.activityService.selectActivityByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteActivityByActivityId
     * @Description: TODO(删除线下活动)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-11
     */
    @RequestMapping(value = "/deleteActivityByActivityId")
    @ResponseBody
    public MessageResponse deleteActivityByActivityId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.activityService.deleteActivityByActivityId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核线下活动)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-11
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String remark) throws Exception {

        return this.activityService.audit(id, rst, remark, this.getCurUserProp());
    }
}

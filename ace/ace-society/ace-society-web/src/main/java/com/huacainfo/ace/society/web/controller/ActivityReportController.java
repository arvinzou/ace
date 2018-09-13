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
import com.huacainfo.ace.society.model.ActivityReport;
import com.huacainfo.ace.society.service.ActivityReportService;
import com.huacainfo.ace.society.vo.ActivityReportVo;
import com.huacainfo.ace.society.vo.ActivityReportQVo;

@Controller
@RequestMapping("/activityReport")
/**
 * @author: huacai003
 * @version: 2018-09-13
 * @Description: TODO(活动报道)
 */
public class ActivityReportController extends SocietyBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ActivityReportService activityReportService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(活动报道分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <ActivityReportVo>
     * @author: huacai003
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/findActivityReportList")
    @ResponseBody
    public PageResult<ActivityReportVo> findActivityReportList(ActivityReportQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<ActivityReportVo> rst = this.activityReportService.findActivityReportList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertActivityReport
     * @Description: TODO(添加活动报道)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/insertActivityReport")
    @ResponseBody
    public MessageResponse insertActivityReport(String jsons) throws Exception {
        ActivityReport obj = JSON.parseObject(jsons, ActivityReport.class);
        return this.activityReportService.insertActivityReport(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateActivityReport
     * @Description: TODO(更新活动报道)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/updateActivityReport")
    @ResponseBody
    public MessageResponse updateActivityReport(String jsons) throws Exception {
        ActivityReport obj = JSON.parseObject(jsons, ActivityReport.class);
        return this.activityReportService.updateActivityReport(obj, this.getCurUserProp());
    }

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
    @RequestMapping(value = "/selectActivityReportByPrimaryKey")
    @ResponseBody
    public SingleResult<ActivityReportVo> selectActivityReportByPrimaryKey(String id) throws Exception {
        return this.activityReportService.selectActivityReportByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteActivityReportByActivityReportId
     * @Description: TODO(删除活动报道)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/deleteActivityReportByActivityReportId")
    @ResponseBody
    public MessageResponse deleteActivityReportByActivityReportId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.activityReportService.deleteActivityReportByActivityReportId(id, this.getCurUserProp());
    }

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
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String remark) throws Exception {
        return this.activityReportService.audit(id, rst, remark, this.getCurUserProp());
    }
}

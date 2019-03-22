package com.huacainfo.ace.taa.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;

import com.huacainfo.ace.taa.service.RoadDangerReportService;
import com.huacainfo.ace.taa.vo.RoadDangerReportQVo;
import com.huacainfo.ace.taa.vo.RoadDangerReportVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/rordDangerReport")
/**
 * @author: 何双
 * @version: 2019-03-15
 * @Description: TODO(路况上报)
 */
public class RordDangerReportController extends TaaBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoadDangerReportService rordDangerReportService;


    //  @Autowired
    //  private RoadDangerReportPicService rordDangerReportPicService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(路况上报分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <RordDangerReportVo>
     * @author: 何双
     * @version: 2019-03-15
     */
    @RequestMapping(value = "/findRordDangerReportList")
    @ResponseBody
    public PageResult<RoadDangerReportVo> findRordDangerReportList(RoadDangerReportQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<RoadDangerReportVo> rst = this.rordDangerReportService.findRordDangerReportList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }


        return rst;
    }

    /**
     * @throws
     * @Title:insertRordDangerReport
     * @Description: TODO(添加路况上报)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    @RequestMapping(value = "/insertRordDangerReport")
    @ResponseBody
    public MessageResponse insertRordDangerReport(String jsons) throws Exception {
        RoadDangerReportQVo obj = JSON.parseObject(jsons, RoadDangerReportQVo.class);
        return this.rordDangerReportService.insertRordDangerReport(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateRordDangerReport
     * @Description: TODO(更新路况上报)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    @RequestMapping(value = "/updateRordDangerReport")
    @ResponseBody
    public MessageResponse updateRordDangerReport(String jsons) throws Exception {
        RoadDangerReportQVo obj = JSON.parseObject(jsons, RoadDangerReportQVo.class);
        return this.rordDangerReportService.updateRordDangerReport(obj, this.getCurUserProp());
    }

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
    @RequestMapping(value = "/selectRordDangerReportByPrimaryKey")
    @ResponseBody
    public SingleResult<RoadDangerReportVo> selectRordDangerReportByPrimaryKey(String id) throws Exception {

        return this.rordDangerReportService.selectRordDangerReportByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteRordDangerReportByRordDangerReportId
     * @Description: TODO(删除路况上报)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    @RequestMapping(value = "/deleteRordDangerReportByRordDangerReportId")
    @ResponseBody
    public MessageResponse deleteRordDangerReportByRordDangerReportId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.rordDangerReportService.deleteByPrimaryKey(id, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version:2019-03-15
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.rordDangerReportService.updateStatus(id, status, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version:2019-03-15
     */
    @RequestMapping(value = "/withdrawReason")
    @ResponseBody
    public MessageResponse withdrawReason(String id, String status, String reason) throws Exception {
        return this.rordDangerReportService.updateReason(id, status, reason, this.getCurUserProp());
    }


}

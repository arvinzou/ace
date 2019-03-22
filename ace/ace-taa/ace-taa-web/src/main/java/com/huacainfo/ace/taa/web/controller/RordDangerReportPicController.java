package com.huacainfo.ace.taa.web.controller;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.taa.model.RoadDangerReportPic;
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
import com.huacainfo.ace.taa.service.RoadDangerReportPicService;
import com.huacainfo.ace.taa.vo.RoadDangerReportPicVo;
import com.huacainfo.ace.taa.vo.RoadDangerReportPicQVo;

import java.util.Map;

@Controller
@RequestMapping("/roadDangerReportPic")
/**
 * @author: 何双
 * @version: 2019-03-15
 * @Description: TODO(路况上报)
 */
public class RordDangerReportPicController extends TaaBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoadDangerReportPicService rordDangerReportPicService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(路况上报分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <RordDangerReportPicVo>
     * @author: 何双
     * @version: 2019-03-15
     */
    @RequestMapping(value = "/findRordDangerReportPicList")
    @ResponseBody
    public PageResult<RoadDangerReportPicVo> findRordDangerReportPicList(RoadDangerReportPicQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<RoadDangerReportPicVo> rst = this.rordDangerReportPicService.findRordDangerReportPicList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertRordDangerReportPic
     * @Description: TODO(添加路况上报)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    @RequestMapping(value = "/insertRordDangerReportPic")
    @ResponseBody
    public MessageResponse insertRordDangerReportPic(String jsons) throws Exception {
        RoadDangerReportPic obj = JSON.parseObject(jsons, RoadDangerReportPic.class);
        return this.rordDangerReportPicService.insertRordDangerReportPic(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateRordDangerReportPic
     * @Description: TODO(更新路况上报)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    @RequestMapping(value = "/updateRordDangerReportPic")
    @ResponseBody
    public MessageResponse updateRordDangerReportPic(String jsons) throws Exception {
        RoadDangerReportPic obj = JSON.parseObject(jsons, RoadDangerReportPic.class);
        return this.rordDangerReportPicService.updateRordDangerReportPic(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectRordDangerReportPicByPrimaryKey
     * @Description: TODO(获取路况上报)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<RordDangerReportPic>
     * @author: 何双
     * @version: 2019-03-15
     */
    @RequestMapping(value = "/selectRordDangerReportPicByPrimaryKey")
    @ResponseBody
    public SingleResult<RoadDangerReportPicVo> selectRordDangerReportPicByPrimaryKey(String id) throws Exception {
        return this.rordDangerReportPicService.selectRordDangerReportPicByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteRordDangerReportPicByRordDangerReportPicId
     * @Description: TODO(删除路况上报)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    @RequestMapping(value = "/deleteRordDangerReportPicByRordDangerReportPicId")
    @ResponseBody
    public MessageResponse deleteRordDangerReportPicByRordDangerReportPicId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.rordDangerReportPicService.deleteRordDangerReportPicByRordDangerReportPicId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核路况上报)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.rordDangerReportPicService.audit(id, rst, message, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: 何双
     * @version:2019-03-15
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.rordDangerReportPicService.getList(this.getParams());
    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String, Object>
     * @author: 何双
     * @version:2019-03-15
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.rordDangerReportPicService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteRordDangerReportPicByRordDangerReportPicIds
     * @Description: TODO(批量删除路况上报)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version:2019-03-15
     */
    @RequestMapping(value = "/deleteRordDangerReportPicByRordDangerReportPicIds")
    @ResponseBody
    public MessageResponse deleteRordDangerReportPicByRordDangerReportPicIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.rordDangerReportPicService.deleteRordDangerReportPicByRordDangerReportPicIds(id, this.getCurUserProp());
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
        return this.rordDangerReportPicService.updateStatus(id, status, this.getCurUserProp());
    }
}

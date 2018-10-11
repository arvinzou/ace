package com.huacainfo.ace.society.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.PointsRecord;
import com.huacainfo.ace.society.service.PointsRecordService;
import com.huacainfo.ace.society.vo.PointsRecordQVo;
import com.huacainfo.ace.society.vo.PointsRecordVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pointsRecord")
/**
 * @author: arvin
 * @version: 2018-09-28
 * @Description: TODO(积分流水)
 */
public class PointsRecordController extends SocietyBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PointsRecordService pointsRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(积分流水分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <PointsRecordVo>
     * @author: arvin
     * @version: 2018-09-28
     */
    @RequestMapping(value = "/findPointsRecordList")
    @ResponseBody
    public PageResult
            <PointsRecordVo> findPointsRecordList(PointsRecordQVo condition,
                                                  PageParamNoChangeSord page) throws Exception {

        PageResult
                <PointsRecordVo> rst = this.pointsRecordService
                .findPointsRecordList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertPointsRecord
     * @Description: TODO(添加积分流水)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: arvin
     * @version: 2018-09-28
     */
    @RequestMapping(value = "/insertPointsRecord")
    @ResponseBody
    public MessageResponse insertPointsRecord(String jsons) throws Exception {
        PointsRecord obj = JSON.parseObject(jsons, PointsRecord.class);
        return this.pointsRecordService.insertPointsRecord(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updatePointsRecord
     * @Description: TODO(更新积分流水)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: arvin
     * @version: 2018-09-28
     */
    @RequestMapping(value = "/updatePointsRecord")
    @ResponseBody
    public MessageResponse updatePointsRecord(String jsons) throws Exception {
        PointsRecord obj = JSON.parseObject(jsons, PointsRecord.class);
        return this.pointsRecordService.updatePointsRecord(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectPointsRecordByPrimaryKey
     * @Description: TODO(获取积分流水)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<PointsRecord>
     * @author: arvin
     * @version: 2018-09-28
     */
    @RequestMapping(value = "/selectPointsRecordByPrimaryKey")
    @ResponseBody
    public SingleResult
            <PointsRecordVo> selectPointsRecordByPrimaryKey(String id) throws Exception {
        return this.pointsRecordService.selectPointsRecordByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deletePointsRecordByPointsRecordId
     * @Description: TODO(删除积分流水)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: arvin
     * @version: 2018-09-28
     */
    @RequestMapping(value = "/deletePointsRecordByPointsRecordId")
    @ResponseBody
    public MessageResponse deletePointsRecordByPointsRecordId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.pointsRecordService.deletePointsRecordByPointsRecordId(id, this.getCurUserProp());
    }
}

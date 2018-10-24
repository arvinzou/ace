package com.huacainfo.ace.society.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.AdmireRecord;
import com.huacainfo.ace.society.service.AdmireRecordService;
import com.huacainfo.ace.society.vo.AdmireRecordQVo;
import com.huacainfo.ace.society.vo.AdmireRecordVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admireRecord")
/**
 * @author: ArvinZou
 * @version: 2018-10-24
 * @Description: TODO(点赞管理)
 */
public class AdmireRecordController extends SocietyBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AdmireRecordService admireRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(点赞管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <AdmireRecordVo>
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @RequestMapping(value = "/findAdmireRecordList")
    @ResponseBody
    public PageResult<AdmireRecordVo> findAdmireRecordList(AdmireRecordQVo condition,
                                                           PageParamNoChangeSord page) throws Exception {

        PageResult<AdmireRecordVo> rst = this.admireRecordService
                .findAdmireRecordList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertAdmireRecord
     * @Description: TODO(添加点赞管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @RequestMapping(value = "/insertAdmireRecord")
    @ResponseBody
    public MessageResponse insertAdmireRecord(String jsons) throws Exception {
        AdmireRecord obj = JSON.parseObject(jsons, AdmireRecord.class);
        return this.admireRecordService.insertAdmireRecord(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateAdmireRecord
     * @Description: TODO(更新点赞管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @RequestMapping(value = "/updateAdmireRecord")
    @ResponseBody
    public MessageResponse updateAdmireRecord(String jsons) throws Exception {
        AdmireRecord obj = JSON.parseObject(jsons, AdmireRecord.class);
        return this.admireRecordService.updateAdmireRecord(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectAdmireRecordByPrimaryKey
     * @Description: TODO(获取点赞管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<AdmireRecord>
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @RequestMapping(value = "/selectAdmireRecordByPrimaryKey")
    @ResponseBody
    public SingleResult<AdmireRecordVo> selectAdmireRecordByPrimaryKey(String id) throws Exception {
        return this.admireRecordService.selectAdmireRecordByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteAdmireRecordByAdmireRecordId
     * @Description: TODO(删除点赞管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @RequestMapping(value = "/deleteAdmireRecordByAdmireRecordId")
    @ResponseBody
    public MessageResponse deleteAdmireRecordByAdmireRecordId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.admireRecordService.deleteAdmireRecordByAdmireRecordId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核点赞管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.admireRecordService.audit(id, rst, message, this.getCurUserProp());
    }
}

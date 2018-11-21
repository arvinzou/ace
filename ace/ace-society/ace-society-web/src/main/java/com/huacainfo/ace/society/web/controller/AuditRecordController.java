package com.huacainfo.ace.society.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.AuditRecord;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.vo.AuditRecordQVo;
import com.huacainfo.ace.society.vo.AuditRecordVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/auditRecord")
/**
 * @author: Arvin
 * @version: 2018-09-11
 * @Description: TODO(审核记录)
 */
public class AuditRecordController extends SocietyBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AuditRecordService auditRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(审核记录分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <AuditRecordVo>
     * @author: Arvin
     * @version: 2018-09-11
     */
    @RequestMapping(value = "/findAuditRecordList")
    @ResponseBody
    public PageResult<AuditRecordVo> findAuditRecordList(AuditRecordQVo condition,
                                                         PageParamNoChangeSord page) throws Exception {

        PageResult<AuditRecordVo> rst = this.auditRecordService
                .findAuditRecordList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertAuditRecord
     * @Description: TODO(添加审核记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    @RequestMapping(value = "/insertAuditRecord")
    @ResponseBody
    public MessageResponse insertAuditRecord(String jsons) throws Exception {
        AuditRecord obj = JSON.parseObject(jsons, AuditRecord.class);
        return this.auditRecordService.insertAuditRecord(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateAuditRecord
     * @Description: TODO(更新审核记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    @RequestMapping(value = "/updateAuditRecord")
    @ResponseBody
    public MessageResponse updateAuditRecord(String jsons) throws Exception {
        AuditRecord obj = JSON.parseObject(jsons, AuditRecord.class);
        return this.auditRecordService.updateAuditRecord(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectAuditRecordByPrimaryKey
     * @Description: TODO(获取审核记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<AuditRecord>
     * @author: Arvin
     * @version: 2018-09-11
     */
    @RequestMapping(value = "/selectAuditRecordByPrimaryKey")
    @ResponseBody
    public SingleResult<AuditRecordVo> selectAuditRecordByPrimaryKey(String id) throws Exception {
        return this.auditRecordService.selectAuditRecordByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteAuditRecordByAuditRecordId
     * @Description: TODO(删除审核记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    @RequestMapping(value = "/deleteAuditRecordByAuditRecordId")
    @ResponseBody
    public MessageResponse deleteAuditRecordByAuditRecordId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.auditRecordService.deleteAuditRecordByAuditRecordId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核审核记录)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String remark) throws Exception {

        return null;// this.auditRecordService.audit(id, rst, remark, this.getCurUserProp());
    }
}

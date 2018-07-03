package com.huacainfo.ace.fundtown.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.fundtown.model.VipProcessRecord;
import com.huacainfo.ace.fundtown.service.VipProcessRecordService;
import com.huacainfo.ace.fundtown.vo.VipProcessRecordQVo;
import com.huacainfo.ace.fundtown.vo.VipProcessRecordVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/vipProcessRecord")
/**
 * @author: Arvin
 * @version: 2018-07-03
 * @Description: TODO(入驻成员-流程节点记录)
 */
public class VipProcessRecordController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private VipProcessRecordService vipProcessRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(入驻成员-流程节点记录分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <VipProcessRecordVo>
     * @author: Arvin
     * @version: 2018-07-03
     */
    @RequestMapping(value = "/findVipProcessRecordList")
    @ResponseBody
    public PageResult<VipProcessRecordVo>
    findVipProcessRecordList(VipProcessRecordQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<VipProcessRecordVo> rst =
                vipProcessRecordService.findVipProcessRecordList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertVipProcessRecord
     * @Description: TODO(添加入驻成员-流程节点记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    @RequestMapping(value = "/insertVipProcessRecord")
    @ResponseBody
    public MessageResponse insertVipProcessRecord(String jsons) throws Exception {
        VipProcessRecord obj = JSON.parseObject(jsons, VipProcessRecord.class);
        return vipProcessRecordService.insertVipProcessRecord(obj, getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateVipProcessRecord
     * @Description: TODO(更新入驻成员-流程节点记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    @RequestMapping(value = "/updateVipProcessRecord")
    @ResponseBody
    public MessageResponse updateVipProcessRecord(String jsons) throws Exception {
        VipProcessRecord obj = JSON.parseObject(jsons, VipProcessRecord.class);
        return vipProcessRecordService.updateVipProcessRecord(obj, getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectVipProcessRecordByPrimaryKey
     * @Description: TODO(获取入驻成员-流程节点记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<VipProcessRecord>
     * @author: Arvin
     * @version: 2018-07-03
     */
    @RequestMapping(value = "/selectVipProcessRecordByPrimaryKey")
    @ResponseBody
    public SingleResult<VipProcessRecordVo> selectVipProcessRecordByPrimaryKey(String id) throws Exception {
        return vipProcessRecordService.selectVipProcessRecordByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteVipProcessRecordByVipProcessRecordId
     * @Description: TODO(删除入驻成员-流程节点记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    @RequestMapping(value = "/deleteVipProcessRecordByVipProcessRecordId")
    @ResponseBody
    public MessageResponse deleteVipProcessRecordByVipProcessRecordId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return vipProcessRecordService.deleteVipProcessRecordByVipProcessRecordId(id, getCurUserProp());
    }
}

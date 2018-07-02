package com.huacainfo.ace.fop.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.fop.model.FopCallRecord;
import com.huacainfo.ace.fop.model.FopCallRecordDetail;
import com.huacainfo.ace.fop.service.FopCallRecordService;
import com.huacainfo.ace.fop.vo.FopCallRecordQVo;
import com.huacainfo.ace.fop.vo.FopCallRecordVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/fopCallRecord")
/**
 * @author: Arvin
 * @version: 2018-05-17
 * @Description: TODO(催缴通知)
 */
public class FopCallRecordController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private FopCallRecordService fopCallRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(催缴通知分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <FopCallRecordVo>
     * @author: Arvin
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/findFopCallRecordList")
    @ResponseBody
    public PageResult<FopCallRecordVo> findFopCallRecordList(FopCallRecordQVo condition,
                                                             PageParamNoChangeSord page) throws Exception {
        PageResult<FopCallRecordVo> rst =
                fopCallRecordService.findFopCallRecordList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertFopCallRecord
     * @Description: TODO(催缴记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/insertFopCallRecord")
    @ResponseBody
    public MessageResponse insertFopCallRecord(String jsons) throws Exception {
        FopCallRecord obj = JSON.parseObject(jsons, FopCallRecord.class);
        return fopCallRecordService.insertFopCallRecord(obj, getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateFopCallRecord
     * @Description: TODO(更新催缴通知)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/updateFopCallRecord")
    @ResponseBody
    public MessageResponse updateFopCallRecord(String jsons) throws Exception {
        FopCallRecord obj = JSON.parseObject(jsons, FopCallRecord.class);
        return fopCallRecordService.updateFopCallRecord(obj, getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFopCallRecordByPrimaryKey
     * @Description: TODO(获取催缴通知)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopCallRecord>
     * @author: Arvin
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/selectFopCallRecordByPrimaryKey")
    @ResponseBody
    public SingleResult<FopCallRecordVo> selectFopCallRecordByPrimaryKey(String id) throws Exception {
        return fopCallRecordService.selectFopCallRecordByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteFopCallRecordByFopCallRecordId
     * @Description: TODO(删除催缴通知)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/deleteFopCallRecordByFopCallRecordId")
    @ResponseBody
    public MessageResponse deleteFopCallRecordByFopCallRecordId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return fopCallRecordService.deleteFopCallRecordByFopCallRecordId(id, getCurUserProp());
    }

    /**
     * 功能描述: 统一催缴
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/18 9:39
     */
    @RequestMapping(value = "/sendCallNotice")
    @ResponseBody
    public MessageResponse sendCallNotice(String recordId) throws Exception {
        if (CommonUtils.isEmpty(recordId)) {
            return new MessageResponse(ResultCode.FAIL, "缺少参数");
        }
        return fopCallRecordService.sendCallNotice(recordId, getCurUserProp());
    }


    /**
     * 功能描述: 添加催缴对象
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/18 9:39
     */
    @RequestMapping(value = "/insertCallRecordDetail")
    @ResponseBody
    public MessageResponse insertCallRecordDetail(String recordId, String data) {
        if (CommonUtils.isEmpty(recordId) || CommonUtils.isEmpty(data)) {
            return new MessageResponse(ResultCode.FAIL, "缺少参数");
        }

        List<Map<String, String>> detailList = JsonUtil.toObject(data, List.class);

        return fopCallRecordService.insertCallRecordDetail(recordId, detailList, getCurUserProp());
    }

    /**
     * 功能描述: 查询催缴人员列表
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/18 14:52
     */
    public ResultResponse findCallRecordDetail(String recordId) {
        if (CommonUtils.isEmpty(recordId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少参数");
        }
        List<FopCallRecordDetail> list = fopCallRecordService.findCallRecordDetail(recordId);

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", list);
    }

}

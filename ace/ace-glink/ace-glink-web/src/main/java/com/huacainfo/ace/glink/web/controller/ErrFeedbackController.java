package com.huacainfo.ace.glink.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.glink.model.ErrFeedback;
import com.huacainfo.ace.glink.service.ErrFeedbackService;
import com.huacainfo.ace.glink.vo.ErrFeedbackQVo;
import com.huacainfo.ace.glink.vo.ErrFeedbackVo;
import com.huacainfo.ace.portal.vo.MongoFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/errFeedback")
/**
 * @author: Arvin
 * @version: 2019-04-10
 * @Description: TODO(故障报警)
 */
public class ErrFeedbackController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ErrFeedbackService errFeedbackService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(故障报警分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <ErrFeedbackVo>
     * @author: Arvin
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/findErrFeedbackList")
    @ResponseBody
    public PageResult<ErrFeedbackVo> findErrFeedbackList(ErrFeedbackQVo condition,
                                                         PageParamNoChangeSord page) throws Exception {

        PageResult<ErrFeedbackVo> rst =
                errFeedbackService.findErrFeedbackList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertErrFeedback
     * @Description: TODO(添加故障报警)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/insertErrFeedback")
    @ResponseBody
    public MessageResponse insertErrFeedback(String jsons) throws Exception {
        ErrFeedback obj = JSON.parseObject(jsons, ErrFeedback.class);
        return this.errFeedbackService.insertErrFeedback(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateErrFeedback
     * @Description: TODO(更新故障报警)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/updateErrFeedback")
    @ResponseBody
    public MessageResponse updateErrFeedback(String jsons) throws Exception {
        ErrFeedback obj = JSON.parseObject(jsons, ErrFeedback.class);
        return this.errFeedbackService.updateErrFeedback(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectErrFeedbackByPrimaryKey
     * @Description: TODO(获取故障报警)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ErrFeedback>
     * @author: Arvin
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/selectErrFeedbackByPrimaryKey")
    @ResponseBody
    public SingleResult<ErrFeedbackVo> selectErrFeedbackByPrimaryKey(String id) throws Exception {
        return this.errFeedbackService.selectErrFeedbackByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteErrFeedbackByErrFeedbackId
     * @Description: TODO(删除故障报警)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/deleteErrFeedbackByErrFeedbackId")
    @ResponseBody
    public MessageResponse deleteErrFeedbackByErrFeedbackId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.errFeedbackService.deleteErrFeedbackByErrFeedbackId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核故障报警)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.errFeedbackService.audit(id, rst, message, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:importXls
     * @Description: TODO(导入 ! { bean.tableChineseName })
     * @param: @param file
     * @param: @param jsons
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version:2019-04-10
     */
    @RequestMapping(value = "/importXls")
    @ResponseBody
    public MessageResponse importXls(@RequestParam MultipartFile[] file, String jsons) throws Exception {
        ExcelUtils utils = new ExcelUtils();
        List<Map<String, Object>> list = new ArrayList<>();
        MongoFile[] files = new MongoFile[file.length];
        int i = 0;
        for (MultipartFile o : file) {
            MongoFile obj = new MongoFile();
            obj.setInputStream(o.getInputStream());
            obj.setFilename(o.getOriginalFilename());
            obj.setLength(o.getSize());
            files[i] = obj;
            i++;
            String ext = obj.getFilename().toLowerCase();
            ext = ext.substring(obj.getFilename().toLowerCase().lastIndexOf("."));
            this.logger.info(ext);
            if (ext.equals(".xls")) {
                list = utils.readExcelByJXL(obj.getInputStream(), 2);
            }
            if (ext.equals(".xlsx")) {
                list = utils.readExcelByPOI(obj.getInputStream(), 2);
            }
        }
        return this.errFeedbackService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: Arvin
     * @version:2019-04-10
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.errFeedbackService.getList(this.getParams());
    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map
     * <String
     * ,Object>
     * @author: Arvin
     * @version:2019-04-10
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.errFeedbackService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteErrFeedbackByErrFeedbackIds
     * @Description: TODO(批量删除故障报警)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version:2019-04-10
     */
    @RequestMapping(value = "/deleteErrFeedbackByErrFeedbackIds")
    @ResponseBody
    public MessageResponse deleteErrFeedbackByErrFeedbackIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.errFeedbackService.deleteErrFeedbackByErrFeedbackIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version:2019-04-10
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.errFeedbackService.updateStatus(id, status, this.getCurUserProp());
    }

    /**
     * 根据年，月。获取月份下面的每天故障统计情况
     * @param year
     * @param month
     * @return
     */
    @RequestMapping(value = "/getDayErrCountList")
    @ResponseBody
    public List<Map<String, Object>> getDayErrCountList(String year, String month) throws Exception{
        return this.errFeedbackService.getDayErrCount(year, month);
    }
}

package com.huacainfo.ace.glink.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.glink.model.ErrWarnSms;
import com.huacainfo.ace.glink.service.ErrWarnSmsService;
import com.huacainfo.ace.glink.vo.ErrWarnSmsQVo;
import com.huacainfo.ace.glink.vo.ErrWarnSmsVo;
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
@RequestMapping("/errWarnSms")
/**
 * @author: Arvin
 * @version: 2019-04-11
 * @Description: TODO(故障报警)
 */
public class ErrWarnSmsController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ErrWarnSmsService errWarnSmsService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(故障报警分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<ErrWarnSmsVo>
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/findErrWarnSmsList")
    @ResponseBody
    public PageResult<ErrWarnSmsVo> findErrWarnSmsList(ErrWarnSmsQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<ErrWarnSmsVo> rst = this.errWarnSmsService.findErrWarnSmsList(condition, page.getStart(),
                page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertErrWarnSms
     * @Description: TODO(添加故障报警)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/insertErrWarnSms")
    @ResponseBody
    public MessageResponse insertErrWarnSms(String jsons) throws Exception {
        ErrWarnSms obj = JSON.parseObject(jsons, ErrWarnSms.class);
        return this.errWarnSmsService.insertErrWarnSms(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateErrWarnSms
     * @Description: TODO(更新故障报警)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/updateErrWarnSms")
    @ResponseBody
    public MessageResponse updateErrWarnSms(String jsons) throws Exception {
        ErrWarnSms obj = JSON.parseObject(jsons, ErrWarnSms.class);
        return this.errWarnSmsService.updateErrWarnSms(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectErrWarnSmsByPrimaryKey
     * @Description: TODO(获取故障报警)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ErrWarnSms>
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/selectErrWarnSmsByPrimaryKey")
    @ResponseBody
    public SingleResult<ErrWarnSmsVo> selectErrWarnSmsByPrimaryKey(String id) throws Exception {
        return this.errWarnSmsService.selectErrWarnSmsByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteErrWarnSmsByErrWarnSmsId
     * @Description: TODO(删除故障报警)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/deleteErrWarnSmsByErrWarnSmsId")
    @ResponseBody
    public MessageResponse deleteErrWarnSmsByErrWarnSmsId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.errWarnSmsService.deleteErrWarnSmsByErrWarnSmsId(id, this.getCurUserProp());
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
     * @version: 2019-04-11
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.errWarnSmsService.audit(id, rst, message, this.getCurUserProp());
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
     * @version:2019-04-11
     */
    @RequestMapping(value = "/importXls")
    @ResponseBody
    public MessageResponse importXls(@RequestParam MultipartFile[] file,
                                     String jsons) throws Exception {
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
        return this.errWarnSmsService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: Arvin
     * @version:2019-04-11
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.errWarnSmsService.getList(this.getParams());
    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String, Object>
     * @author: Arvin
     * @version:2019-04-11
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.errWarnSmsService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteErrWarnSmsByErrWarnSmsIds
     * @Description: TODO(批量删除故障报警)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version:2019-04-11
     */
    @RequestMapping(value = "/deleteErrWarnSmsByErrWarnSmsIds")
    @ResponseBody
    public MessageResponse deleteErrWarnSmsByErrWarnSmsIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.errWarnSmsService.deleteErrWarnSmsByErrWarnSmsIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version:2019-04-11
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.errWarnSmsService.updateStatus(id, status, this.getCurUserProp());
    }
}

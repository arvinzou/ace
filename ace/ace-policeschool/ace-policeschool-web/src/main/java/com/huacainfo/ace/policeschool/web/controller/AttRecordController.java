package com.huacainfo.ace.policeschool.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.plugins.excel.ExportExcelKit;
import com.huacainfo.ace.common.plugins.excel.ExportExcelWrapper;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.common.tools.FileUtil;
import com.huacainfo.ace.common.tools.TimestampKit;
import com.huacainfo.ace.policeschool.model.AttRecord;
import com.huacainfo.ace.policeschool.model.AttRecordExcel;
import com.huacainfo.ace.policeschool.service.AttRecordService;
import com.huacainfo.ace.policeschool.vo.AttRecordQVo;
import com.huacainfo.ace.policeschool.vo.AttRecordVo;
import com.huacainfo.ace.portal.vo.MongoFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/attRecord")
/**
 * @author: Arvin
 * @version: 2019-02-20
 * @Description: TODO(学员考勤)
 */
public class AttRecordController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AttRecordService attRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(学员考勤分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <AttRecordVo>
     * @author: Arvin
     * @version: 2019-02-20
     */
    @RequestMapping(value = "/findAttRecordList")
    @ResponseBody
    public PageResult<AttRecordVo> findAttRecordList(AttRecordQVo condition, PageParamNoChangeSord page) throws Exception {
        if (StringUtil.isNotEmpty(condition.getStartDate())) {
            condition.setStartDate(condition.getStartDate() + " 00:00:00");
        }
        if (StringUtil.isNotEmpty(condition.getEndDate())) {
            condition.setEndDate(condition.getEndDate() + " 23:59:59");
        }
        PageResult<AttRecordVo> rst = this.attRecordService.findAttRecordList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }


    /**
     * @throws
     * @Title:selectAttRecordByPrimaryKey
     * @Description: TODO(获取学员考勤)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<AttRecord>
     * @author: Arvin
     * @version: 2019-02-20
     */
    @RequestMapping(value = "/selectAttRecordByPrimaryKey")
    @ResponseBody
    public SingleResult<AttRecordVo> selectAttRecordByPrimaryKey(String id) throws Exception {
        return this.attRecordService.selectAttRecordByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteAttRecordByAttRecordId
     * @Description: TODO(删除学员考勤)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    @RequestMapping(value = "/deleteAttRecordByAttRecordId")
    @ResponseBody
    public MessageResponse deleteAttRecordByAttRecordId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.attRecordService.deleteAttRecordByAttRecordId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核学员考勤)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.attRecordService.audit(id, rst, message, this.getCurUserProp());
    }





    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: Arvin
     * @version:2019-02-20
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.attRecordService.getList(this.getParams());
    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: Arvin
     * @version:2019-02-20
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.attRecordService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteAttRecordByAttRecordIds
     * @Description: TODO(批量删除学员考勤)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version:2019-02-20
     */
    @RequestMapping(value = "/deleteAttRecordByAttRecordIds")
    @ResponseBody
    public MessageResponse deleteAttRecordByAttRecordIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.attRecordService.deleteAttRecordByAttRecordIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version:2019-02-20
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.attRecordService.updateStatus(id, status, this.getCurUserProp());
    }

    /**
     * 上传中控考勤数据
     *
     * @param file 上传文件
     * @return MessageResponse
     */
    @ResponseBody
    @RequestMapping(value = "/uploadData")
    public MessageResponse uploadData(@RequestParam MultipartFile[] file) throws IOException {
        String nowTime = DateUtil.getNow()
                .replace(" ", "")
                .replace("-", "")
                .replace(":", "");
        //MultipartFile to InputStream
        InputStream inputStream = file[0].getInputStream();
        //存储文件件
        String storePath = System.getProperties().getProperty("user.dir");
        //文件完整路径
        String fileName = storePath + File.separator + nowTime + ".mdb";
        logger.info("access file store path: " + fileName);
        File f = new File(fileName);
        FileUtil.inputStreamToFile(inputStream, f);

        return attRecordService.saveZkData(fileName, nowTime);
    }

    /**
     * 获取导出考勤数据
     *
     * @param condition 查询条件
     * @param response  httpResponse
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/exportAttRecord")
    public ResultResponse getExcel(AttRecordQVo condition, HttpServletResponse response) {
        if (StringUtil.isNotEmpty(condition.getStartDate())) {
            condition.setStartDate(condition.getStartDate() + " 00:00:00");
        }
        if (StringUtil.isNotEmpty(condition.getEndDate())) {
            condition.setEndDate(condition.getEndDate() + " 23:59:59");
        }
        //excel data
        List<AttRecordExcel> list;
        try {
            list = attRecordService.exportAttRecord(condition);
        } catch (CustomException e) {
            return new ResultResponse(ResultCode.FAIL, e.getMsg());
        }

        if (CollectionUtils.isEmpty(list)) {
            return new ResultResponse(ResultCode.FAIL, "查无数据");
        }

        // 准备数据
        String[] columnNames = {"姓名", "考勤时间", "考勤日期"};
        String fileName = TimestampKit.getTimestamp10() + "";
        ExportExcelWrapper<AttRecordExcel> util = new ExportExcelWrapper<>();
        util.exportExcel(fileName, fileName, columnNames, list, response, ExportExcelKit.EXCEL_FILE_2003);

        return new ResultResponse(ResultCode.SUCCESS, "导出成功");
    }
}

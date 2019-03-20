package com.huacainfo.ace.policeschool.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.policeschool.model.QyAttRecord;
import com.huacainfo.ace.policeschool.service.QyAttRecordService;
import com.huacainfo.ace.policeschool.vo.QyAttRecordQVo;
import com.huacainfo.ace.policeschool.vo.QyAttRecordVo;
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
@RequestMapping("/qyAttRecord")
/**
 * @author: ArvinZou
 * @version: 2019-03-19
 * @Description: TODO(群英考勤数据)
 */
public class QyAttRecordController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private QyAttRecordService qyAttRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(群英考勤数据分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <QyAttRecordVo>
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @RequestMapping(value = "/findQyAttRecordList")
    @ResponseBody
    public PageResult<QyAttRecordVo> findQyAttRecordList(QyAttRecordQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<QyAttRecordVo> rst = this.qyAttRecordService.findQyAttRecordList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertQyAttRecord
     * @Description: TODO(添加群英考勤数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @RequestMapping(value = "/insertQyAttRecord")
    @ResponseBody
    public MessageResponse insertQyAttRecord(String jsons) throws Exception {
        QyAttRecord obj = JSON.parseObject(jsons, QyAttRecord.class);
        return this.qyAttRecordService.insertQyAttRecord(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateQyAttRecord
     * @Description: TODO(更新群英考勤数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @RequestMapping(value = "/updateQyAttRecord")
    @ResponseBody
    public MessageResponse updateQyAttRecord(String jsons) throws Exception {
        QyAttRecord obj = JSON.parseObject(jsons, QyAttRecord.class);
        return this.qyAttRecordService.updateQyAttRecord(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectQyAttRecordByPrimaryKey
     * @Description: TODO(获取群英考勤数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<QyAttRecord>
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @RequestMapping(value = "/selectQyAttRecordByPrimaryKey")
    @ResponseBody
    public SingleResult<QyAttRecordVo> selectQyAttRecordByPrimaryKey(String id) throws Exception {
        return this.qyAttRecordService.selectQyAttRecordByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteQyAttRecordByQyAttRecordId
     * @Description: TODO(删除群英考勤数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @RequestMapping(value = "/deleteQyAttRecordByQyAttRecordId")
    @ResponseBody
    public MessageResponse deleteQyAttRecordByQyAttRecordId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.qyAttRecordService.deleteQyAttRecordByQyAttRecordId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核群英考勤数据)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.qyAttRecordService.audit(id, rst, message, this.getCurUserProp());
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
     * @author: ArvinZou
     * @version:2019-03-19
     */
    @RequestMapping(value = "/importXls")
    @ResponseBody
    public MessageResponse importXls(@RequestParam MultipartFile[] file,
                                     String jsons) throws Exception {
        ExcelUtils utils = new ExcelUtils();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        MongoFile[] files = new MongoFile[file.length];
        int i = 0;
        for (MultipartFile o : file) {
            MongoFile obj = new MongoFile();
            obj.setInputStream(o.getInputStream());
            obj.setFilename(o.getOriginalFilename());
            obj.setLength(o.getSize());
            files[i] = obj;
            i++;
            String ext = obj
                    .getFilename()
                    .toLowerCase()
                    .substring(
                            obj.getFilename().toLowerCase()
                                    .lastIndexOf("."));
            this.logger.info(ext);
            if (ext.equals(".xls")) {
                list = utils.readExcelByJXL(obj.getInputStream(), 2);
            }
            if (ext.equals(".xlsx")) {
                list = utils.readExcelByPOI(obj.getInputStream(), 2);
            }
        }
        return this.qyAttRecordService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: ArvinZou
     * @version:2019-03-19
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.qyAttRecordService.getList(this.getParams());
    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String, Object>
     * @author: ArvinZou
     * @version:2019-03-19
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.qyAttRecordService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteQyAttRecordByQyAttRecordIds
     * @Description: TODO(批量删除群英考勤数据)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version:2019-03-19
     */
    @RequestMapping(value = "/deleteQyAttRecordByQyAttRecordIds")
    @ResponseBody
    public MessageResponse deleteQyAttRecordByQyAttRecordIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.qyAttRecordService.deleteQyAttRecordByQyAttRecordIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version:2019-03-19
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.qyAttRecordService.updateStatus(id, status, this.getCurUserProp());
    }
}

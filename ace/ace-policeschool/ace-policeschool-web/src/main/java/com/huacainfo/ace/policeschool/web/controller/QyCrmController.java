package com.huacainfo.ace.policeschool.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.plugins.qyplugin.pojo.DeviceRst;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.policeschool.model.QyCrm;
import com.huacainfo.ace.policeschool.service.QyCrmService;
import com.huacainfo.ace.policeschool.vo.QyCrmQVo;
import com.huacainfo.ace.policeschool.vo.QyCrmVo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/qyCrm")
/**
 * @author: ArvinZou
 * @version: 2019-03-19
 * @Description: TODO(数据上传)
 */
public class QyCrmController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private QyCrmService qyCrmService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(数据上传分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <QyCrmVo>
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @RequestMapping(value = "/findQyCrmList")
    @ResponseBody
    public PageResult<QyCrmVo> findQyCrmList(QyCrmQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<QyCrmVo> rst = this.qyCrmService.findQyCrmList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertQyCrm
     * @Description: TODO(添加数据上传)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @RequestMapping(value = "/insertQyCrm")
    @ResponseBody
    public MessageResponse insertQyCrm(String jsons) throws Exception {
        QyCrm obj = JSON.parseObject(jsons, QyCrm.class);
        return this.qyCrmService.insertQyCrm(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateQyCrm
     * @Description: TODO(更新数据上传)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @RequestMapping(value = "/updateQyCrm")
    @ResponseBody
    public MessageResponse updateQyCrm(String jsons) throws Exception {
        QyCrm obj = JSON.parseObject(jsons, QyCrm.class);
        return this.qyCrmService.updateQyCrm(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectQyCrmByPrimaryKey
     * @Description: TODO(获取数据上传)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<QyCrm>
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @RequestMapping(value = "/selectQyCrmByPrimaryKey")
    @ResponseBody
    public SingleResult<QyCrmVo> selectQyCrmByPrimaryKey(String id) throws Exception {
        return this.qyCrmService.selectQyCrmByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteQyCrmByQyCrmId
     * @Description: TODO(删除数据上传)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    @RequestMapping(value = "/deleteQyCrmByQyCrmId")
    @ResponseBody
    public MessageResponse deleteQyCrmByQyCrmId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.qyCrmService.deleteQyCrmByQyCrmId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核数据上传)
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

        return this.qyCrmService.audit(id, rst, message, this.getCurUserProp());
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
        return this.qyCrmService.importXls(list, this.getCurUserProp());
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
        return this.qyCrmService.getList(this.getParams());
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
        return this.qyCrmService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteQyCrmByQyCrmIds
     * @Description: TODO(批量删除数据上传)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version:2019-03-19
     */
    @RequestMapping(value = "/deleteQyCrmByQyCrmIds")
    @ResponseBody
    public MessageResponse deleteQyCrmByQyCrmIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.qyCrmService.deleteQyCrmByQyCrmIds(id, this.getCurUserProp());
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
        return this.qyCrmService.updateStatus(id, status, this.getCurUserProp());
    }

    /**
     * 获取设备列表
     *
     * @param userId 用户ID
     * @return Map
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/getDevice")
    public Map<String, Object> getDevice(String userId) throws Exception {
        //个人数据
        SingleResult<QyCrmVo> vo = qyCrmService.selectQyCrmByPrimaryKey(userId);
        //设备列表
        DeviceRst rstObj = qyCrmService.getDevice("");
        //数据返回
        Map<String, Object> rst = new HashMap<>();
        rst.put("vo", vo.getValue());
        rst.put("deviceList", rstObj);
        return rst;
    }


    /**
     * 调用群英接口
     * 上传员工数据&同步数据到设备
     *
     * @param userId 用户ID
     * @param idStr  设备SN串
     * @return Map
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/syncData")
    public MessageResponse syncData(String userId, String idStr) throws Exception {
        if (!StringUtil.areNotEmpty(userId, idStr)) {
            return new MessageResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return qyCrmService.syncData(userId, idStr);
    }
}

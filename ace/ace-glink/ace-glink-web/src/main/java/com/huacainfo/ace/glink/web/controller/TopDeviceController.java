package com.huacainfo.ace.glink.web.controller;

import com.huacainfo.ace.common.result.ListResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.glink.model.TopDevice;
import com.huacainfo.ace.glink.service.TopDeviceService;
import com.huacainfo.ace.glink.vo.TopDeviceVo;
import com.huacainfo.ace.glink.vo.TopDeviceQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/topDevice")
/**
 * @author: heshuang
 * @version: 2019-04-10
 * @Description: TODO(设备管理)
 */
public class TopDeviceController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopDeviceService topDeviceService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(设备管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <TopDeviceVo>
     * @author: heshuang
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/findTopDeviceList")
    @ResponseBody
    public PageResult
            <TopDeviceVo>
    findTopDeviceList(TopDeviceQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult
                <TopDeviceVo> rst =
                this.topDeviceService.findTopDeviceList(condition, page.getStart(),
                        page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertTopDevice
     * @Description: TODO(添加设备管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/insertTopDevice")
    @ResponseBody
    public MessageResponse insertTopDevice(String jsons) throws Exception {
        TopDevice obj = JSON.parseObject(jsons, TopDevice.class);
        return this.topDeviceService.insertTopDevice(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateTopDevice
     * @Description: TODO(更新设备管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/updateTopDevice")
    @ResponseBody
    public MessageResponse updateTopDevice(String jsons) throws Exception {
        TopDevice obj = JSON.parseObject(jsons, TopDevice.class);
        return this.topDeviceService.updateTopDevice(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectTopDeviceByPrimaryKey
     * @Description: TODO(获取设备管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TopDevice>
     * @author: heshuang
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/selectTopDeviceByPrimaryKey")
    @ResponseBody
    public SingleResult
            <TopDeviceVo> selectTopDeviceByPrimaryKey(String id) throws Exception {
        return this.topDeviceService.selectTopDeviceByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteTopDeviceByTopDeviceId
     * @Description: TODO(删除设备管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/deleteTopDeviceByTopDeviceId")
    @ResponseBody
    public MessageResponse deleteTopDeviceByTopDeviceId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.topDeviceService.deleteTopDeviceByTopDeviceId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核设备管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.topDeviceService.audit(id, rst, message, this.getCurUserProp());
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
     * @author: heshuang
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
        return this.topDeviceService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: heshuang
     * @version:2019-04-10
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.topDeviceService.getList(this.getParams());
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
     * @author: heshuang
     * @version:2019-04-10
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.topDeviceService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteTopDeviceByTopDeviceIds
     * @Description: TODO(批量删除设备管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version:2019-04-10
     */
    @RequestMapping(value = "/deleteTopDeviceByTopDeviceIds")
    @ResponseBody
    public MessageResponse deleteTopDeviceByTopDeviceIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.topDeviceService.deleteTopDeviceByTopDeviceIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version:2019-04-10
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.topDeviceService.updateStatus(id, status, this.getCurUserProp());
    }
}

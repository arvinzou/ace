package com.huacainfo.ace.policeschool.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.policeschool.model.ZkAttData;
import com.huacainfo.ace.policeschool.service.ZkAttDataService;
import com.huacainfo.ace.policeschool.vo.ZkAttDataQVo;
import com.huacainfo.ace.policeschool.vo.ZkAttDataVo;
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
@RequestMapping("/zkAttData")
/**
 * @author: Arvin
 * @version: 2019-02-20
 * @Description: TODO(中控考勤数据源)
 */
public class ZkAttDataController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ZkAttDataService zkAttDataService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(中控考勤数据源分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <ZkAttDataVo>
     * @author: Arvin
     * @version: 2019-02-20
     */
    @RequestMapping(value = "/findZkAttDataList")
    @ResponseBody
    public PageResult<ZkAttDataVo> findZkAttDataList(ZkAttDataQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<ZkAttDataVo> rst = this.zkAttDataService.findZkAttDataList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertZkAttData
     * @Description: TODO(添加中控考勤数据源)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    @RequestMapping(value = "/insertZkAttData")
    @ResponseBody
    public MessageResponse insertZkAttData(String jsons) throws Exception {
        ZkAttData obj = JSON.parseObject(jsons, ZkAttData.class);
        return this.zkAttDataService.insertZkAttData(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateZkAttData
     * @Description: TODO(更新中控考勤数据源)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    @RequestMapping(value = "/updateZkAttData")
    @ResponseBody
    public MessageResponse updateZkAttData(String jsons) throws Exception {
        ZkAttData obj = JSON.parseObject(jsons, ZkAttData.class);
        return this.zkAttDataService.updateZkAttData(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectZkAttDataByPrimaryKey
     * @Description: TODO(获取中控考勤数据源)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ZkAttData>
     * @author: Arvin
     * @version: 2019-02-20
     */
    @RequestMapping(value = "/selectZkAttDataByPrimaryKey")
    @ResponseBody
    public SingleResult<ZkAttDataVo> selectZkAttDataByPrimaryKey(String id) throws Exception {
        return this.zkAttDataService.selectZkAttDataByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteZkAttDataByZkAttDataId
     * @Description: TODO(删除中控考勤数据源)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    @RequestMapping(value = "/deleteZkAttDataByZkAttDataId")
    @ResponseBody
    public MessageResponse deleteZkAttDataByZkAttDataId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.zkAttDataService.deleteZkAttDataByZkAttDataId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核中控考勤数据源)
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

        return this.zkAttDataService.audit(id, rst, message, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:importXls
     * @Description: TODO(导入!{bean.tableChineseName})
     * @param: @param file
     * @param: @param jsons
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version:2019-02-20
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
        return this.zkAttDataService.importXls(list, this.getCurUserProp());
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
        return this.zkAttDataService.getList(this.getParams());
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
        return this.zkAttDataService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteZkAttDataByZkAttDataIds
     * @Description: TODO(批量删除中控考勤数据源)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version:2019-02-20
     */
    @RequestMapping(value = "/deleteZkAttDataByZkAttDataIds")
    @ResponseBody
    public MessageResponse deleteZkAttDataByZkAttDataIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.zkAttDataService.deleteZkAttDataByZkAttDataIds(id, this.getCurUserProp());
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
        return this.zkAttDataService.updateStatus(id, status, this.getCurUserProp());
    }
}

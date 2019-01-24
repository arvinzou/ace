package com.huacainfo.ace.taa.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.portal.vo.MongoFile;
import com.huacainfo.ace.taa.model.TraAcc;
import com.huacainfo.ace.taa.service.TraAccService;
import com.huacainfo.ace.taa.vo.TraAccQVo;
import com.huacainfo.ace.taa.vo.TraAccVo;
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
@RequestMapping("/traAcc")
/**
 * @author: 陈晓克
 * @version: 2019-01-10
 * @Description: TODO(事故)
 */
public class TraAccController extends TaaBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TraAccService traAccService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(事故分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <TraAccVo>
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @RequestMapping(value = "/findTraAccList")
    @ResponseBody
    public PageResult<TraAccVo> findTraAccList(TraAccQVo condition, PageParamNoChangeSord page) throws Exception {
        if (CommonUtils.isBlank(condition.getAreaCode())) {
            condition.setAreaCode(this.getCurUserProp().getAreaCode());
        }
        PageResult<TraAccVo> rst = this.traAccService.findTraAccList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertTraAcc
     * @Description: TODO(添加事故)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @RequestMapping(value = "/insertTraAcc")
    @ResponseBody
    public MessageResponse insertTraAcc(String jsons) throws Exception {
        TraAcc obj = JSON.parseObject(jsons, TraAcc.class);
        return this.traAccService.insertTraAcc(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateTraAcc
     * @Description: TODO(更新事故)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @RequestMapping(value = "/updateTraAcc")
    @ResponseBody
    public MessageResponse updateTraAcc(String jsons) throws Exception {
        TraAcc obj = JSON.parseObject(jsons, TraAcc.class);
        return this.traAccService.updateTraAcc(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectTraAccByPrimaryKey
     * @Description: TODO(获取事故)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TraAcc>
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @RequestMapping(value = "/selectTraAccByPrimaryKey")
    @ResponseBody
    public SingleResult<TraAccVo> selectTraAccByPrimaryKey(String id) throws Exception {
        return this.traAccService.selectTraAccByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteTraAccByTraAccId
     * @Description: TODO(删除事故)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @RequestMapping(value = "/deleteTraAccByTraAccId")
    @ResponseBody
    public MessageResponse deleteTraAccByTraAccId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.traAccService.deleteTraAccByTraAccId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核事故)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.traAccService.audit(id, rst, message, this.getCurUserProp());
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
     * @author: 陈晓克
     * @version:2019-01-10
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
        return this.traAccService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: 陈晓克
     * @version:2019-01-10
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.traAccService.getList(this.getParams());
    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version:2019-01-10
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.traAccService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteTraAccByTraAccIds
     * @Description: TODO(批量删除事故)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version:2019-01-10
     */
    @RequestMapping(value = "/deleteTraAccByTraAccIds")
    @ResponseBody
    public MessageResponse deleteTraAccByTraAccIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.traAccService.deleteTraAccByTraAccIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.traAccService.updateStatus(id, status, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getLatLongByAreaCode
     * @Description: TODO(获取行政区划中心坐标)
     * @param: @param areaCode 行政区划编码
     * @param: @throws Exception
     * @return: SingleResult
     * @author: 陈晓克
     * @version: 2019-01-19
     */
    @RequestMapping(value = "/getLatLongByAreaCode")
    @ResponseBody
    public SingleResult<Map<String, Object>> getLatLongByAreaCode(String areaCode) throws Exception {
        return this.traAccService.getLatLongByAreaCode(areaCode);
    }

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(事故分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <TraAccVo>
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    @RequestMapping(value = "/getTraAccList")
    @ResponseBody
    public JSONArray getTraAccList(TraAccQVo condition, PageParamNoChangeSord page) throws Exception {
        JSONArray rst = new JSONArray();
        JSONArray items = new JSONArray();
        if (CommonUtils.isBlank(condition.getAreaCode())) {
            condition.setAreaCode(this.getCurUserProp().getAreaCode());
        }
        List<Map<String, Object>> list = this.traAccService.getTraAccList(condition);
        for (Map<String, Object> o : list) {
            JSONObject e = new JSONObject();
            e.put("coord", new double[]{(Double) o.get("longitude"),(Double) o.get("latitude")});
            e.put("elevation", o.get("deadthToll"));
            items.add(e);
        }
        rst.add(items);
        return rst;
    }


}

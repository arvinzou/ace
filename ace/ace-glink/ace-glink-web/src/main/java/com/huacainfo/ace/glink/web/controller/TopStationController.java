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
import com.huacainfo.ace.glink.model.TopStation;
import com.huacainfo.ace.glink.service.TopStationService;
import com.huacainfo.ace.glink.vo.TopStationVo;
import com.huacainfo.ace.glink.vo.TopStationQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/topStation")
/**
 * @author: heshuang
 * @version: 2019-04-09
 * @Description: TODO(站点管理)
 */
public class TopStationController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopStationService topStationService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(站点管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <TopStationVo>
     * @author: heshuang
     * @version: 2019-04-09
     */
    @RequestMapping(value = "/findTopStationList")
    @ResponseBody
    public PageResult
            <TopStationVo> findTopStationList(TopStationQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult
                <TopStationVo> rst = this.topStationService.findTopStationList(condition, page.getStart(),
                page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertTopStation
     * @Description: TODO(添加站点管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-09
     */
    @RequestMapping(value = "/insertTopStation")
    @ResponseBody
    public MessageResponse insertTopStation(String jsons) throws Exception {
        TopStation obj = JSON.parseObject(jsons, TopStation.class);
        return this.topStationService.insertTopStation(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateTopStation
     * @Description: TODO(更新站点管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-09
     */
    @RequestMapping(value = "/updateTopStation")
    @ResponseBody
    public MessageResponse updateTopStation(String jsons) throws Exception {
        TopStation obj = JSON.parseObject(jsons, TopStation.class);
        return this.topStationService.updateTopStation(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectTopStationByPrimaryKey
     * @Description: TODO(获取站点管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TopStation>
     * @author: heshuang
     * @version: 2019-04-09
     */
    @RequestMapping(value = "/selectTopStationByPrimaryKey")
    @ResponseBody
    public SingleResult
            <TopStationVo> selectTopStationByPrimaryKey(String id) throws Exception {
        return this.topStationService.selectTopStationByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteTopStationByTopStationId
     * @Description: TODO(删除站点管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-09
     */
    @RequestMapping(value = "/deleteTopStationByTopStationId")
    @ResponseBody
    public MessageResponse deleteTopStationByTopStationId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.topStationService.deleteTopStationByTopStationId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核站点管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-09
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.topStationService.audit(id, rst, message, this.getCurUserProp());
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
     * @version:2019-04-09
     */
    @RequestMapping(value = "/importXls")
    @ResponseBody
    public MessageResponse importXls(@RequestParam MultipartFile[] file,
                                     String jsons) throws Exception {
        ExcelUtils utils = new ExcelUtils();
        List
                <Map
                        <String
                                , Object>> list = new ArrayList
                <Map
                        <String
                                , Object>>();
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
        return this.topStationService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: heshuang
     * @version:2019-04-09
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult
            <Map
                        <String
                                , Object>> getList() throws Exception {
        return this.topStationService.getList(this.getParams());
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
     * @version:2019-04-09
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map
            <String
                    , Object> getListByCondition() {
        return this.topStationService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteTopStationByTopStationIds
     * @Description: TODO(批量删除站点管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version:2019-04-09
     */
    @RequestMapping(value = "/deleteTopStationByTopStationIds")
    @ResponseBody
    public MessageResponse deleteTopStationByTopStationIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.topStationService.deleteTopStationByTopStationIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version:2019-04-09
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.topStationService.updateStatus(id, status, this.getCurUserProp());
    }
}

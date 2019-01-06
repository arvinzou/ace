package com.huacainfo.ace.taa.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.portal.vo.MongoFile;
import com.huacainfo.ace.taa.model.RoadSection;
import com.huacainfo.ace.taa.service.RoadSectionService;
import com.huacainfo.ace.taa.vo.RoadSectionQVo;
import com.huacainfo.ace.taa.vo.RoadSectionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
@Controller
@RequestMapping("/roadSection")
/**
 * @author: 陈晓克
 * @version: 2019-01-04
 * @Description: TODO(路段)
 */
public class RoadSectionController extends TaaBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoadSectionService roadSectionService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(路段分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <RoadSectionVo>
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @RequestMapping(value = "/findRoadSectionList")
    @ResponseBody
    public PageResult<RoadSectionVo> findRoadSectionList(RoadSectionQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<RoadSectionVo> rst = this.roadSectionService.findRoadSectionList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertRoadSection
     * @Description: TODO(添加路段)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @RequestMapping(value = "/insertRoadSection")
    @ResponseBody
    public MessageResponse insertRoadSection(String jsons) throws Exception {
        RoadSection obj = JSON.parseObject(jsons, RoadSection.class);
        return this.roadSectionService.insertRoadSection(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateRoadSection
     * @Description: TODO(更新路段)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @RequestMapping(value = "/updateRoadSection")
    @ResponseBody
    public MessageResponse updateRoadSection(String jsons) throws Exception {
        RoadSection obj = JSON.parseObject(jsons, RoadSection.class);
        return this.roadSectionService.updateRoadSection(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectRoadSectionByPrimaryKey
     * @Description: TODO(获取路段)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<RoadSection>
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @RequestMapping(value = "/selectRoadSectionByPrimaryKey")
    @ResponseBody
    public SingleResult<RoadSectionVo> selectRoadSectionByPrimaryKey(String id) throws Exception {
        return this.roadSectionService.selectRoadSectionByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionId
     * @Description: TODO(删除路段)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @RequestMapping(value = "/deleteRoadSectionByRoadSectionId")
    @ResponseBody
    public MessageResponse deleteRoadSectionByRoadSectionId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.roadSectionService.deleteRoadSectionByRoadSectionId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核路段)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.roadSectionService.audit(id, rst, message, this.getCurUserProp());
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
     * @version:2019-01-04
     */
    @RequestMapping(value = "/importXls")
    @ResponseBody
    public MessageResponse importXls(@RequestParam MultipartFile[] file,
                                     String roadId) throws Exception {
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
        return this.roadSectionService.importXls(list, this.getCurUserProp(),roadId);
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String,Object>> getList() throws Exception {
        return this.roadSectionService.getList(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionId
     * @Description: TODO(删除路段)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-04
     */
    @RequestMapping(value = "/deleteRoadSectionByRoadSectionIds")
    @ResponseBody
    public MessageResponse deleteRoadSectionByRoadSectionIds(String ids) throws Exception {
        String [] id=ids.split(",");
        return this.roadSectionService.deleteRoadSectionByRoadSectionIds(id, this.getCurUserProp());
    }
}

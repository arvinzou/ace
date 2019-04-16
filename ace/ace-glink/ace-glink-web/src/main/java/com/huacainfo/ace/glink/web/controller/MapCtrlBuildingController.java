package com.huacainfo.ace.glink.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.glink.model.MapCtrlBuilding;
import com.huacainfo.ace.glink.service.MapCtrlBuildingService;
import com.huacainfo.ace.glink.vo.MapCtrlBuildingQVo;
import com.huacainfo.ace.glink.vo.MapCtrlBuildingVo;
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
@RequestMapping("/mapCtrlBuilding")
/**
 * @author: Arvin
 * @version: 2019-04-16
 * @Description: TODO(控制器映射关系)
 */
public class MapCtrlBuildingController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MapCtrlBuildingService mapCtrlBuildingService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(控制器映射关系分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<MapCtrlBuildingVo>
     * @author: Arvin
     * @version: 2019-04-16
     */
    @RequestMapping(value = "/findMapCtrlBuildingList")
    @ResponseBody
    public PageResult<MapCtrlBuildingVo> findMapCtrlBuildingList(MapCtrlBuildingQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<MapCtrlBuildingVo> rst = this.mapCtrlBuildingService.findMapCtrlBuildingList(condition, page.getStart(),
                page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertMapCtrlBuilding
     * @Description: TODO(添加控制器映射关系)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-16
     */
    @RequestMapping(value = "/insertMapCtrlBuilding")
    @ResponseBody
    public MessageResponse insertMapCtrlBuilding(String jsons) throws Exception {
        MapCtrlBuilding obj = JSON.parseObject(jsons, MapCtrlBuilding.class);
        return this.mapCtrlBuildingService.insertMapCtrlBuilding(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateMapCtrlBuilding
     * @Description: TODO(更新控制器映射关系)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-16
     */
    @RequestMapping(value = "/updateMapCtrlBuilding")
    @ResponseBody
    public MessageResponse updateMapCtrlBuilding(String jsons) throws Exception {
        MapCtrlBuilding obj = JSON.parseObject(jsons, MapCtrlBuilding.class);
        return this.mapCtrlBuildingService.updateMapCtrlBuilding(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectMapCtrlBuildingByPrimaryKey
     * @Description: TODO(获取控制器映射关系)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<MapCtrlBuilding>
     * @author: Arvin
     * @version: 2019-04-16
     */
    @RequestMapping(value = "/selectMapCtrlBuildingByPrimaryKey")
    @ResponseBody
    public SingleResult<MapCtrlBuildingVo> selectMapCtrlBuildingByPrimaryKey(String id) throws Exception {
        return this.mapCtrlBuildingService.selectMapCtrlBuildingByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteMapCtrlBuildingByMapCtrlBuildingId
     * @Description: TODO(删除控制器映射关系)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-16
     */
    @RequestMapping(value = "/deleteMapCtrlBuildingByMapCtrlBuildingId")
    @ResponseBody
    public MessageResponse deleteMapCtrlBuildingByMapCtrlBuildingId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.mapCtrlBuildingService.deleteMapCtrlBuildingByMapCtrlBuildingId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核控制器映射关系)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-16
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.mapCtrlBuildingService.audit(id, rst, message, this.getCurUserProp());
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
     * @version:2019-04-16
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
        return this.mapCtrlBuildingService.importXls(list, this.getCurUserProp());
    }
}

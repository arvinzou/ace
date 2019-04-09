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
import com.huacainfo.ace.glink.model.TopBuilding;
import com.huacainfo.ace.glink.service.TopBuildingService;
import com.huacainfo.ace.glink.vo.TopBuildingVo;
import com.huacainfo.ace.glink.vo.TopBuildingQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
@Controller
@RequestMapping("/topBuilding")
/**
* @author: luocan
* @version: 2019-04-09
* @Description:  TODO(建筑物管理)
*/
public class TopBuildingController extends GLinkBaseController {


private static final long serialVersionUID = 1L;
Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
private TopBuildingService topBuildingService;

/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(建筑物管理分页查询)
* @param:        @param condition
* @param:        @param page
* @param:        @return
* @param:        @throws Exception
* @return:       PageResult
<TopBuildingVo>
    * @throws
    * @author: luocan
    * @version: 2019-04-09
    */
    @RequestMapping(value = "/findTopBuildingList")
    @ResponseBody
    public PageResult
    <TopBuildingVo> findTopBuildingList(TopBuildingQVo condition,PageParamNoChangeSord page) throws Exception {

        PageResult
        <TopBuildingVo> rst = this.topBuildingService.findTopBuildingList(condition, page.getStart(),
            page.getLimit(), page.getOrderBy());
            if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
            }

            return rst;
            }

            /**
            *
            * @Title:insertTopBuilding
            * @Description: TODO(添加建筑物管理)
            * @param: @param jsons
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: luocan
            * @version: 2019-04-09
            */
            @RequestMapping(value = "/insertTopBuilding")
            @ResponseBody
            public MessageResponse insertTopBuilding(String jsons) throws Exception {
            TopBuilding obj = JSON.parseObject(jsons, TopBuilding.class);
            return this.topBuildingService.insertTopBuilding(obj, this.getCurUserProp());
            }

            /**
            *
            * @Title:updateTopBuilding
            * @Description: TODO(更新建筑物管理)
            * @param: @param jsons
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: luocan
            * @version: 2019-04-09
            */
            @RequestMapping(value = "/updateTopBuilding")
            @ResponseBody
            public MessageResponse updateTopBuilding(String jsons) throws Exception {
            TopBuilding obj = JSON.parseObject(jsons, TopBuilding.class);
            return this.topBuildingService.updateTopBuilding(obj, this.getCurUserProp());
            }

            /**
            *
            * @Title:selectTopBuildingByPrimaryKey
            * @Description: TODO(获取建筑物管理)
            * @param: @param id
            * @param: @throws Exception
            * @return: SingleResult<TopBuilding>
            * @throws
            * @author: luocan
            * @version: 2019-04-09
            */
            @RequestMapping(value = "/selectTopBuildingByPrimaryKey")
            @ResponseBody
            public SingleResult
            <TopBuildingVo> selectTopBuildingByPrimaryKey(String id)throws Exception {
                return this.topBuildingService.selectTopBuildingByPrimaryKey(id);
                }

                /**
                *
                * @Title:deleteTopBuildingByTopBuildingId
                * @Description: TODO(删除建筑物管理)
                * @param: @param jsons
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
                * @author: luocan
                * @version: 2019-04-09
                */
                @RequestMapping(value = "/deleteTopBuildingByTopBuildingId")
                @ResponseBody
                public MessageResponse deleteTopBuildingByTopBuildingId(String jsons) throws Exception {
                JSONObject json = JSON.parseObject(jsons);
                String id = json.getString("id");
                return this.topBuildingService.deleteTopBuildingByTopBuildingId(id, this.getCurUserProp());
                }

                /**
                *
                * @Title:audit
                * @Description: TODO(审核建筑物管理)
                * @param: @param id bean.id
                * @param: @param rst 审核结果 3-通过 4-拒绝
                * @param: @param message 审核说明
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
                * @author: luocan
                * @version: 2019-04-09
                */
                @RequestMapping(value = "/audit")
                @ResponseBody
                public MessageResponse audit(String id,String rst, String message) throws Exception {

                return this.topBuildingService.audit(id, rst, message, this.getCurUserProp());
                }


                /**
                *
                * @Title:importXls
                * @Description: TODO(导入!{bean.tableChineseName})
                * @param: @param file
                * @param: @param jsons
                * @param: @return
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
                * @author: luocan
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
                , Object>> list=new ArrayList
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
                return this.topBuildingService.importXls(list, this.getCurUserProp());
                }
                /**
                * @throws
                * @Title:updateStatus
                * @Description: TODO(更新状态)
                * @param: @param id
                * @param: @throws Exception
                * @return: MessageResponse
                * @author: luocan
                * @version:2019-04-09
                */
                @RequestMapping(value = "/updateStatus")
                @ResponseBody
                public MessageResponse updateStatus(String id,String status) throws Exception {
                return this.topBuildingService.updateStatus(id,status,this.getCurUserProp());
                }
                }

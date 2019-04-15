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
import com.huacainfo.ace.glink.model.LoopCtrlArea;
import com.huacainfo.ace.glink.service.LoopCtrlAreaService;
import com.huacainfo.ace.glink.vo.LoopCtrlAreaVo;
import com.huacainfo.ace.glink.vo.LoopCtrlAreaQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/loopCtrlArea")
/**
 * @author: heshuang
 * @version: 2019-04-15
 * @Description: TODO(区级整体控制)
 */
public class LoopCtrlAreaController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LoopCtrlAreaService loopCtrlAreaService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(区级整体控制分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <LoopCtrlAreaVo>
     * @author: heshuang
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/findLoopCtrlAreaList")
    @ResponseBody
    public PageResult
            <LoopCtrlAreaVo> findLoopCtrlAreaList(LoopCtrlAreaQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult
                <LoopCtrlAreaVo> rst = this.loopCtrlAreaService.findLoopCtrlAreaList(condition, page.getStart(),
                page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertLoopCtrlArea
     * @Description: TODO(添加区级整体控制)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/insertLoopCtrlArea")
    @ResponseBody
    public MessageResponse insertLoopCtrlArea(String jsons) throws Exception {
        LoopCtrlArea obj = JSON.parseObject(jsons, LoopCtrlArea.class);
        return this.loopCtrlAreaService.insertLoopCtrlArea(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateLoopCtrlArea
     * @Description: TODO(更新区级整体控制)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/updateLoopCtrlArea")
    @ResponseBody
    public MessageResponse updateLoopCtrlArea(String jsons) throws Exception {
        LoopCtrlArea obj = JSON.parseObject(jsons, LoopCtrlArea.class);
        return this.loopCtrlAreaService.updateLoopCtrlArea(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectLoopCtrlAreaByPrimaryKey
     * @Description: TODO(获取区级整体控制)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LoopCtrlArea>
     * @author: heshuang
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/selectLoopCtrlAreaByPrimaryKey")
    @ResponseBody
    public SingleResult
            <LoopCtrlAreaVo> selectLoopCtrlAreaByPrimaryKey(String id) throws Exception {
        return this.loopCtrlAreaService.selectLoopCtrlAreaByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteLoopCtrlAreaByLoopCtrlAreaId
     * @Description: TODO(删除区级整体控制)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/deleteLoopCtrlAreaByLoopCtrlAreaId")
    @ResponseBody
    public MessageResponse deleteLoopCtrlAreaByLoopCtrlAreaId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.loopCtrlAreaService.deleteLoopCtrlAreaByLoopCtrlAreaId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核区级整体控制)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.loopCtrlAreaService.audit(id, rst, message, this.getCurUserProp());
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
     * @version:2019-04-15
     */
    @RequestMapping(value = "/importXls")
    @ResponseBody
    public MessageResponse importXls(@RequestParam MultipartFile[] file,
                                     String jsons) throws Exception {
        ExcelUtils utils = new ExcelUtils();
        List
                <Map
                        <String
                                , Object>> list = new ArrayList<>();
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
        return this.loopCtrlAreaService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: heshuang
     * @version:2019-04-15
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.loopCtrlAreaService.getList(this.getParams());
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
     * @version:2019-04-15
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.loopCtrlAreaService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteLoopCtrlAreaByLoopCtrlAreaIds
     * @Description: TODO(批量删除区级整体控制)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version:2019-04-15
     */
    @RequestMapping(value = "/deleteLoopCtrlAreaByLoopCtrlAreaIds")
    @ResponseBody
    public MessageResponse deleteLoopCtrlAreaByLoopCtrlAreaIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.loopCtrlAreaService.deleteLoopCtrlAreaByLoopCtrlAreaIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version:2019-04-15
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.loopCtrlAreaService.updateStatus(id, status, this.getCurUserProp());
    }
}

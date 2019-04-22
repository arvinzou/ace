package com.huacainfo.ace.glink.web.controller;

import com.huacainfo.ace.common.model.view.Tree;
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
import com.huacainfo.ace.glink.model.SeProjectArea;
import com.huacainfo.ace.glink.service.SeProjectAreaService;
import com.huacainfo.ace.glink.vo.SeProjectAreaVo;
import com.huacainfo.ace.glink.vo.SeProjectAreaQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/seProjectArea")
/**
 * @author: heshuang
 * @version: 2019-04-18
 * @Description: TODO(项目区域数据)
 */
public class SeProjectAreaController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeProjectAreaService seProjectAreaService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(项目区域数据分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <SeProjectAreaVo>
     * @author: heshuang
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/findSeProjectAreaList")
    @ResponseBody
    public PageResult
            <SeProjectAreaVo> findSeProjectAreaList(SeProjectAreaQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult
                <SeProjectAreaVo> rst = this.seProjectAreaService.findSeProjectAreaList(condition, page.getStart(),
                page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertSeProjectArea
     * @Description: TODO(添加项目区域数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/insertSeProjectArea")
    @ResponseBody
    public MessageResponse insertSeProjectArea(String jsons) throws Exception {
        SeProjectArea obj = JSON.parseObject(jsons, SeProjectArea.class);
        //  List<SeProjectArea> obj=JSON.parseArray(jsons, SeProjectArea.class);
        return this.seProjectAreaService.insertSeProjectArea(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateSeProjectArea
     * @Description: TODO(更新项目区域数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/updateSeProjectArea")
    @ResponseBody
    public MessageResponse updateSeProjectArea(String jsons) throws Exception {
        SeProjectArea obj = JSON.parseObject(jsons, SeProjectArea.class);
        return this.seProjectAreaService.updateSeProjectArea(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectSeProjectAreaByPrimaryKey
     * @Description: TODO(获取项目区域数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeProjectArea>
     * @author: heshuang
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/selectSeProjectAreaByPrimaryKey")
    @ResponseBody
    public SingleResult
            <SeProjectAreaVo> selectSeProjectAreaByPrimaryKey(String id) throws Exception {
        return this.seProjectAreaService.selectSeProjectAreaByPrimaryKey(id);
    }


    /**
     * @throws
     * @Title:deleteSeProjectAreaBySeProjectAreaId
     * @Description: TODO(删除项目区域数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/deleteSeProjectAreaBySeProjectAreaId")
    @ResponseBody
    public MessageResponse deleteSeProjectAreaBySeProjectAreaId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.seProjectAreaService.deleteSeProjectAreaBySeProjectAreaId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核项目区域数据)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.seProjectAreaService.audit(id, rst, message, this.getCurUserProp());
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
     * @version:2019-04-18
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
        return this.seProjectAreaService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: heshuang
     * @version:2019-04-18
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult
            <Map
                    <String
                            , Object>> getList() throws Exception {
        return this.seProjectAreaService.getList(this.getParams());
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
     * @version:2019-04-18
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map
            <String
                    , Object> getListByCondition() {
        return this.seProjectAreaService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteSeProjectAreaBySeProjectAreaIds
     * @Description: TODO(批量删除项目区域数据)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version:2019-04-18
     */
    @RequestMapping(value = "/deleteSeProjectAreaBySeProjectAreaIds")
    @ResponseBody
    public MessageResponse deleteSeProjectAreaBySeProjectAreaIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.seProjectAreaService.deleteSeProjectAreaBySeProjectAreaIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version:2019-04-18
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.seProjectAreaService.updateStatus(id, status, this.getCurUserProp());
    }

    @RequestMapping(value = "/selectTreeList")
    @ResponseBody
    public List<Tree> selectResourcesTreeList() throws Exception {
        return this.seProjectAreaService.selectTreeList();
    }

    /**
     * 同步数据
     *
     * @throws Exception
     */
    @RequestMapping(value = "/syncProjectData")
    @ResponseBody
    public MessageResponse syncProjectData() throws Exception {

        return this.seProjectAreaService.syncProjectData(this.getCurUserProp());
    }
}

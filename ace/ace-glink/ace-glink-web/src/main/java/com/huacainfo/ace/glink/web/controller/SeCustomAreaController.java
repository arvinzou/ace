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
import com.huacainfo.ace.glink.model.SeCustomArea;
import com.huacainfo.ace.glink.service.SeCustomAreaService;
import com.huacainfo.ace.glink.vo.SeCustomAreaVo;
import com.huacainfo.ace.glink.vo.SeCustomAreaQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/seCustomArea")
/**
 * @author: heshuang
 * @version: 2019-04-18
 * @Description: TODO(逻辑区数据)
 */
public class SeCustomAreaController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeCustomAreaService seCustomAreaService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(逻辑区数据分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <SeCustomAreaVo>
     * @author: heshuang
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/findSeCustomAreaList")
    @ResponseBody
    public PageResult
            <SeCustomAreaVo> findSeCustomAreaList(SeCustomAreaQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult
                <SeCustomAreaVo> rst = this.seCustomAreaService.findSeCustomAreaList(condition, page.getStart(),
                page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertSeCustomArea
     * @Description: TODO(添加逻辑区数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/insertSeCustomArea")
    @ResponseBody
    public MessageResponse insertSeCustomArea(String jsons) throws Exception {
        /*   List<SeCustomArea> obj = JSON.parseArray(jsons, SeCustomArea.class);*/

        SeCustomArea obj = JSON.parseObject(jsons, SeCustomArea.class);
        return this.seCustomAreaService.insertSeCustomArea(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateSeCustomArea
     * @Description: TODO(更新逻辑区数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/updateSeCustomArea")
    @ResponseBody
    public MessageResponse updateSeCustomArea(String jsons) throws Exception {
        SeCustomArea obj = JSON.parseObject(jsons, SeCustomArea.class);
        return this.seCustomAreaService.updateSeCustomArea(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectSeCustomAreaByPrimaryKey
     * @Description: TODO(获取逻辑区数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeCustomArea>
     * @author: heshuang
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/selectSeCustomAreaByPrimaryKey")
    @ResponseBody
    public SingleResult
            <SeCustomAreaVo> selectSeCustomAreaByPrimaryKey(String id) throws Exception {
        return this.seCustomAreaService.selectSeCustomAreaByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteSeCustomAreaBySeCustomAreaId
     * @Description: TODO(删除逻辑区数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/deleteSeCustomAreaBySeCustomAreaId")
    @ResponseBody
    public MessageResponse deleteSeCustomAreaBySeCustomAreaId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.seCustomAreaService.deleteSeCustomAreaBySeCustomAreaId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核逻辑区数据)
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

        return this.seCustomAreaService.audit(id, rst, message, this.getCurUserProp());
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
        return this.seCustomAreaService.importXls(list, this.getCurUserProp());
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
        return this.seCustomAreaService.getList(this.getParams());
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
        return this.seCustomAreaService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteSeCustomAreaBySeCustomAreaIds
     * @Description: TODO(批量删除逻辑区数据)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version:2019-04-18
     */
    @RequestMapping(value = "/deleteSeCustomAreaBySeCustomAreaIds")
    @ResponseBody
    public MessageResponse deleteSeCustomAreaBySeCustomAreaIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.seCustomAreaService.deleteSeCustomAreaBySeCustomAreaIds(id, this.getCurUserProp());
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
        return this.seCustomAreaService.updateStatus(id, status, this.getCurUserProp());
    }

    /**
     * 同步数据
     *
     * @throws Exception
     */
    @RequestMapping(value = "/syncCustomData")
    @ResponseBody
    public MessageResponse syncCustomData(String jsons) throws Exception {
        List<SeCustomArea> obj = JSON.parseArray(jsons, SeCustomArea.class);
        return this.seCustomAreaService.syncCustomData(obj, this.getCurUserProp());
    }
}

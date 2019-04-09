package com.huacainfo.ace.glink.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.glink.model.TopSubarea;
import com.huacainfo.ace.glink.service.TopSubareaService;
import com.huacainfo.ace.glink.vo.TopSubareaQVo;
import com.huacainfo.ace.glink.vo.TopSubareaVo;
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
@RequestMapping("/topSubarea")
/**
 * @author: Arvin
 * @version: 2019-04-09
 * @Description: TODO(分区管理)
 */
public class TopSubareaController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopSubareaService topSubareaService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(分区管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<TopSubareaVo>
     * @author: Arvin
     * @version: 2019-04-09
     */
    @RequestMapping(value = "/findTopSubareaList")
    @ResponseBody
    public PageResult<TopSubareaVo> findTopSubareaList(TopSubareaQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<TopSubareaVo> rst =
                topSubareaService.findTopSubareaList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertTopSubarea
     * @Description: TODO(添加分区管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-09
     */
    @RequestMapping(value = "/insertTopSubarea")
    @ResponseBody
    public MessageResponse insertTopSubarea(String jsons) throws Exception {
        TopSubarea obj = JSON.parseObject(jsons, TopSubarea.class);
        return this.topSubareaService.insertTopSubarea(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateTopSubarea
     * @Description: TODO(更新分区管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-09
     */
    @RequestMapping(value = "/updateTopSubarea")
    @ResponseBody
    public MessageResponse updateTopSubarea(String jsons) throws Exception {
        TopSubarea obj = JSON.parseObject(jsons, TopSubarea.class);
        return this.topSubareaService.updateTopSubarea(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectTopSubareaByPrimaryKey
     * @Description: TODO(获取分区管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TopSubarea>
     * @author: Arvin
     * @version: 2019-04-09
     */
    @RequestMapping(value = "/selectTopSubareaByPrimaryKey")
    @ResponseBody
    public SingleResult<TopSubareaVo> selectTopSubareaByPrimaryKey(String id) throws Exception {
        return this.topSubareaService.selectTopSubareaByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteTopSubareaByTopSubareaId
     * @Description: TODO(删除分区管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-09
     */
    @RequestMapping(value = "/deleteTopSubareaByTopSubareaId")
    @ResponseBody
    public MessageResponse deleteTopSubareaByTopSubareaId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.topSubareaService.deleteTopSubareaByTopSubareaId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核分区管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-09
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.topSubareaService.audit(id, rst, message, this.getCurUserProp());
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
     * @version:2019-04-09
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
        return this.topSubareaService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: Arvin
     * @version:2019-04-09
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.topSubareaService.getList(this.getParams());
    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String, Object>
     * @author: Arvin
     * @version:2019-04-09
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.topSubareaService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteTopSubareaByTopSubareaIds
     * @Description: TODO(批量删除分区管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version:2019-04-09
     */
    @RequestMapping(value = "/deleteTopSubareaByTopSubareaIds")
    @ResponseBody
    public MessageResponse deleteTopSubareaByTopSubareaIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.topSubareaService.deleteTopSubareaByTopSubareaIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version:2019-04-09
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.topSubareaService.updateStatus(id, status, this.getCurUserProp());
    }
}

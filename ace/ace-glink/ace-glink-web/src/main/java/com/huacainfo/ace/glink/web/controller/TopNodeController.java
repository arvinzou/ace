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
import com.huacainfo.ace.glink.model.TopNode;
import com.huacainfo.ace.glink.service.TopNodeService;
import com.huacainfo.ace.glink.vo.TopNodeVo;
import com.huacainfo.ace.glink.vo.TopNodeQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/topNode")
/**
 * @author: huacai003
 * @version: 2019-04-09
 * @Description: TODO(节点管理)
 */
public class TopNodeController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopNodeService topNodeService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(节点管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <TopNodeVo>
     * @author: huacai003
     * @version: 2019-04-09
     */
    @RequestMapping(value = "/findTopNodeList")
    @ResponseBody
    public PageResult<TopNodeVo> findTopNodeList(TopNodeQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<TopNodeVo> rst = this.topNodeService.findTopNodeList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertTopNode
     * @Description: TODO(添加节点管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-09
     */
    @RequestMapping(value = "/insertTopNode")
    @ResponseBody
    public MessageResponse insertTopNode(String jsons) throws Exception {
        TopNode obj = JSON.parseObject(jsons, TopNode.class);
        return this.topNodeService.insertTopNode(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateTopNode
     * @Description: TODO(更新节点管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-09
     */
    @RequestMapping(value = "/updateTopNode")
    @ResponseBody
    public MessageResponse updateTopNode(String jsons) throws Exception {
        TopNode obj = JSON.parseObject(jsons, TopNode.class);
        return this.topNodeService.updateTopNode(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectTopNodeByPrimaryKey
     * @Description: TODO(获取节点管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TopNode>
     * @author: huacai003
     * @version: 2019-04-09
     */
    @RequestMapping(value = "/selectTopNodeByPrimaryKey")
    @ResponseBody
    public SingleResult
            <TopNodeVo> selectTopNodeByPrimaryKey(String id) throws Exception {
        return this.topNodeService.selectTopNodeByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteTopNodeByTopNodeId
     * @Description: TODO(删除节点管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-09
     */
    @RequestMapping(value = "/deleteTopNodeByTopNodeId")
    @ResponseBody
    public MessageResponse deleteTopNodeByTopNodeId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.topNodeService.deleteTopNodeByTopNodeId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核节点管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-09
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.topNodeService.audit(id, rst, message, this.getCurUserProp());
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
     * @author: huacai003
     * @version:2019-04-09
     */
    @RequestMapping(value = "/importXls")
    @ResponseBody
    public MessageResponse importXls(@RequestParam MultipartFile[] file, String jsons) throws Exception {
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
            String ext = obj.getFilename().toLowerCase().substring(obj.getFilename().toLowerCase().lastIndexOf("."));
            this.logger.info(ext);
            if (ext.equals(".xls")) {
                list = utils.readExcelByJXL(obj.getInputStream(), 2);
            }
            if (ext.equals(".xlsx")) {
                list = utils.readExcelByPOI(obj.getInputStream(), 2);
            }
        }
        return this.topNodeService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: huacai003
     * @version:2019-04-09
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.topNodeService.getList(this.getParams());
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
     * @author: huacai003
     * @version:2019-04-09
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.topNodeService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteTopNodeByTopNodeIds
     * @Description: TODO(批量删除节点管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version:2019-04-09
     */
    @RequestMapping(value = "/deleteTopNodeByTopNodeIds")
    @ResponseBody
    public MessageResponse deleteTopNodeByTopNodeIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.topNodeService.deleteTopNodeByTopNodeIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version:2019-04-09
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.topNodeService.updateStatus(id, status, this.getCurUserProp());
    }
}

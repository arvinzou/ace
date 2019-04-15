package com.huacainfo.ace.glink.web.controller;

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
import com.huacainfo.ace.glink.model.AmmeterNode;
import com.huacainfo.ace.glink.service.AmmeterNodeService;
import com.huacainfo.ace.glink.vo.AmmeterNodeVo;
import com.huacainfo.ace.glink.vo.AmmeterNodeQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/ammeterNode")
/**
 * @author: luocan
 * @version: 2019-04-15
 * @Description: TODO(节点能耗信息)
 */
public class AmmeterNodeController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AmmeterNodeService ammeterNodeService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(节点能耗信息分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <AmmeterNodeVo>
     * @author: luocan
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/findAmmeterNodeList")
    @ResponseBody
    public PageResult
            <AmmeterNodeVo> findAmmeterNodeList(AmmeterNodeQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult
                <AmmeterNodeVo> rst = this.ammeterNodeService.findAmmeterNodeList(condition, page.getStart(),
                page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertAmmeterNode
     * @Description: TODO(添加节点能耗信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/insertAmmeterNode")
    @ResponseBody
    public MessageResponse insertAmmeterNode(String jsons) throws Exception {
        AmmeterNode obj = JSON.parseObject(jsons, AmmeterNode.class);
        return this.ammeterNodeService.insertAmmeterNode(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateAmmeterNode
     * @Description: TODO(更新节点能耗信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/updateAmmeterNode")
    @ResponseBody
    public MessageResponse updateAmmeterNode(String jsons) throws Exception {
        AmmeterNode obj = JSON.parseObject(jsons, AmmeterNode.class);
        return this.ammeterNodeService.updateAmmeterNode(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectAmmeterNodeByPrimaryKey
     * @Description: TODO(获取节点能耗信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<AmmeterNode>
     * @author: luocan
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/selectAmmeterNodeByPrimaryKey")
    @ResponseBody
    public SingleResult
            <AmmeterNodeVo> selectAmmeterNodeByPrimaryKey(String id) throws Exception {
        return this.ammeterNodeService.selectAmmeterNodeByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteAmmeterNodeByAmmeterNodeId
     * @Description: TODO(删除节点能耗信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/deleteAmmeterNodeByAmmeterNodeId")
    @ResponseBody
    public MessageResponse deleteAmmeterNodeByAmmeterNodeId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.ammeterNodeService.deleteAmmeterNodeByAmmeterNodeId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核节点能耗信息)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.ammeterNodeService.audit(id, rst, message, this.getCurUserProp());
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
     * @author: luocan
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
        return this.ammeterNodeService.importXls(list, this.getCurUserProp());
    }
}

package com.huacainfo.ace.fundtown.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.fundtown.model.ProcessNode;
import com.huacainfo.ace.fundtown.service.ProcessNodeService;
import com.huacainfo.ace.fundtown.vo.ProcessNodeQVo;
import com.huacainfo.ace.fundtown.vo.ProcessNodeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/processNode")
/**
 * @author: Arvin
 * @version: 2018-07-03
 * @Description: TODO(入驻流程节点)
 */
public class ProcessNodeController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProcessNodeService processNodeService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(入驻流程节点分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <ProcessNodeVo>
     * @author: Arvin
     * @version: 2018-07-03
     */
    @RequestMapping(value = "/findProcessNodeList")
    @ResponseBody
    public PageResult<ProcessNodeVo> findProcessNodeList(ProcessNodeQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<ProcessNodeVo> rst = this.processNodeService
                .findProcessNodeList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertProcessNode
     * @Description: TODO(添加入驻流程节点)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    @RequestMapping(value = "/insertProcessNode")
    @ResponseBody
    public MessageResponse insertProcessNode(String jsons) throws Exception {
        ProcessNode obj = JSON.parseObject(jsons, ProcessNode.class);
        return this.processNodeService.insertProcessNode(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateProcessNode
     * @Description: TODO(更新入驻流程节点)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    @RequestMapping(value = "/updateProcessNode")
    @ResponseBody
    public MessageResponse updateProcessNode(String jsons) throws Exception {
        ProcessNode obj = JSON.parseObject(jsons, ProcessNode.class);
        return this.processNodeService.updateProcessNode(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectProcessNodeByPrimaryKey
     * @Description: TODO(获取入驻流程节点)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ProcessNode>
     * @author: Arvin
     * @version: 2018-07-03
     */
    @RequestMapping(value = "/selectProcessNodeByPrimaryKey")
    @ResponseBody
    public SingleResult
            <ProcessNodeVo> selectProcessNodeByPrimaryKey(String id) throws Exception {
        return this.processNodeService.selectProcessNodeByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteProcessNodeByProcessNodeId
     * @Description: TODO(删除入驻流程节点)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    @RequestMapping(value = "/deleteProcessNodeByProcessNodeId")
    @ResponseBody
    public MessageResponse deleteProcessNodeByProcessNodeId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.processNodeService.deleteProcessNodeByProcessNodeId(id, this.getCurUserProp());
    }
}

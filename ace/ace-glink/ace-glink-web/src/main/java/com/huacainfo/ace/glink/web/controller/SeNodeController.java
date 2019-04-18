package com.huacainfo.ace.glink.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.glink.model.SeNode;
import com.huacainfo.ace.glink.service.SeNodeService;
import com.huacainfo.ace.glink.vo.SeNodeQVo;
import com.huacainfo.ace.glink.vo.SeNodeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/seNode")
/**
 * @author: Arvin
 * @version: 2019-04-18
 * @Description: TODO(配电箱数据)
 */
public class SeNodeController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeNodeService seNodeService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(配电箱数据分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<SeNodeVo>
     * @author: Arvin
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/findSeNodeList")
    @ResponseBody
    public PageResult<SeNodeVo> findSeNodeList(SeNodeQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<SeNodeVo> rst = this.seNodeService.findSeNodeList(condition, page.getStart(),
                page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertSeNode
     * @Description: TODO(添加配电箱数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/insertSeNode")
    @ResponseBody
    public MessageResponse insertSeNode(String jsons) throws Exception {
        SeNode obj = JSON.parseObject(jsons, SeNode.class);
        return this.seNodeService.insertSeNode(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateSeNode
     * @Description: TODO(更新配电箱数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/updateSeNode")
    @ResponseBody
    public MessageResponse updateSeNode(String jsons) throws Exception {
        SeNode obj = JSON.parseObject(jsons, SeNode.class);
        return this.seNodeService.updateSeNode(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectSeNodeByPrimaryKey
     * @Description: TODO(获取配电箱数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeNode>
     * @author: Arvin
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/selectSeNodeByPrimaryKey")
    @ResponseBody
    public SingleResult<SeNodeVo> selectSeNodeByPrimaryKey(String id) throws Exception {
        return this.seNodeService.selectSeNodeByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteSeNodeBySeNodeId
     * @Description: TODO(删除配电箱数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/deleteSeNodeBySeNodeId")
    @ResponseBody
    public MessageResponse deleteSeNodeBySeNodeId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.seNodeService.deleteSeNodeBySeNodeId(id, this.getCurUserProp());
    }

    /**
     * 同步配电箱基础数据
     *
     * @return MessageResponse
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/syncData")
    public MessageResponse syncData() throws Exception {
        return seNodeService.syncData(this.getCurUserProp());
    }

}

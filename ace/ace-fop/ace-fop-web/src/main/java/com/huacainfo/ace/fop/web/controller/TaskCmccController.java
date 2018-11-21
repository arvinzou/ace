package com.huacainfo.ace.fop.web.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.model.PageParam;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.service.TaskCmccService;
import com.huacainfo.ace.portal.vo.TaskCmccQVo;
import com.huacainfo.ace.portal.vo.TaskCmccVo;
import com.huacainfo.ace.portal.web.controller.PortalBaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/taskCmcc")
public class TaskCmccController extends PortalBaseController {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private TaskCmccService taskCmccService;

    @RequestMapping(value = "/findTaskCmccList.do")
    @ResponseBody
    public PageResult<TaskCmccVo> findTaskCmccList(TaskCmccQVo condition, PageParam page) throws Exception {
        PageResult<TaskCmccVo> rst = this.taskCmccService.findTaskCmccList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    @RequestMapping(value = "/insertTaskCmcc.do")
    @ResponseBody
    public MessageResponse insertTaskCmcc(String jsons) throws Exception {
        TaskCmcc o = JSON.parseObject(jsons, TaskCmcc.class);
        return this.taskCmccService.insertTaskCmcc(o, this.getCurUserProp());
    }

    @RequestMapping(value = "/updateTaskCmcc.do")
    @ResponseBody
    public MessageResponse updateTaskCmcc(String jsons) throws Exception {
        TaskCmcc o = JSON.parseObject(jsons, TaskCmcc.class);
        return this.taskCmccService.updateTaskCmcc(o, this.getCurUserProp());
    }

    @RequestMapping(value = "/deleteTaskCmccByTaskCmccId.do")
    @ResponseBody
    public MessageResponse deleteTaskCmccByTaskCmccId(String jsons) throws Exception {
        com.alibaba.fastjson.JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.taskCmccService.deleteTaskCmccByTaskCmccId(json.getString("id"), this.getCurUserProp());
    }

    @RequestMapping(value = "/updateTaskStatusCmccByTaskCmccId.do")
    @ResponseBody
    public MessageResponse updateTaskStatusCmccByTaskCmccId(String id) throws Exception {
        return this.taskCmccService.updateTaskStatusCmccByTaskCmccId(id, this.getCurUserProp());
    }

    @RequestMapping(value = "/selectById.do")
    @ResponseBody
    public MessageResponse selectById(String id) throws Exception {
        return this.taskCmccService.selectBYId(id);
    }
}

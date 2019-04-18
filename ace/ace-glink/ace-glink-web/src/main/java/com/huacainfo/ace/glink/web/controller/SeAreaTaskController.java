package com.huacainfo.ace.glink.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.glink.model.SeAreaTask;
import com.huacainfo.ace.glink.service.SeAreaTaskService;
import com.huacainfo.ace.glink.vo.SeAreaTaskQVo;
import com.huacainfo.ace.glink.vo.SeAreaTaskVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/seAreaTask")
/**
 * @author: Arvin
 * @version: 2019-04-18
 * @Description: TODO(区域任务数据)
 */
public class SeAreaTaskController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeAreaTaskService seAreaTaskService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(区域任务数据分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<SeAreaTaskVo>
     * @author: Arvin
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/findSeAreaTaskList")
    @ResponseBody
    public PageResult<SeAreaTaskVo> findSeAreaTaskList(SeAreaTaskQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<SeAreaTaskVo> rst = this.seAreaTaskService.findSeAreaTaskList(condition, page.getStart(),
                page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertSeAreaTask
     * @Description: TODO(添加区域任务数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/insertSeAreaTask")
    @ResponseBody
    public MessageResponse insertSeAreaTask(String jsons) throws Exception {
        SeAreaTask obj = JSON.parseObject(jsons, SeAreaTask.class);
        return this.seAreaTaskService.insertSeAreaTask(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateSeAreaTask
     * @Description: TODO(更新区域任务数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/updateSeAreaTask")
    @ResponseBody
    public MessageResponse updateSeAreaTask(String jsons) throws Exception {
        SeAreaTask obj = JSON.parseObject(jsons, SeAreaTask.class);
        return this.seAreaTaskService.updateSeAreaTask(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectSeAreaTaskByPrimaryKey
     * @Description: TODO(获取区域任务数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeAreaTask>
     * @author: Arvin
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/selectSeAreaTaskByPrimaryKey")
    @ResponseBody
    public SingleResult<SeAreaTaskVo> selectSeAreaTaskByPrimaryKey(String id) throws Exception {
        return this.seAreaTaskService.selectSeAreaTaskByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteSeAreaTaskBySeAreaTaskId
     * @Description: TODO(删除区域任务数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/deleteSeAreaTaskBySeAreaTaskId")
    @ResponseBody
    public MessageResponse deleteSeAreaTaskBySeAreaTaskId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.seAreaTaskService.deleteSeAreaTaskBySeAreaTaskId(id, this.getCurUserProp());
    }

}

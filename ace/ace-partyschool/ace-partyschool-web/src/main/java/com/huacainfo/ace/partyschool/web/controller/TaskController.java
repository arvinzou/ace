package com.huacainfo.ace.partyschool.web.controller;

import com.huacainfo.ace.common.model.UserProp;
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
import com.huacainfo.ace.partyschool.model.Task;
import com.huacainfo.ace.partyschool.service.TaskService;
import com.huacainfo.ace.partyschool.vo.TaskVo;
import com.huacainfo.ace.partyschool.vo.TaskQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/task")
/**
 * @author: 王恩
 * @version: 2019-03-08
 * @Description: TODO(任务管理)
 */
public class TaskController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TaskService taskService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(任务管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <TaskVo>
     * @author: 王恩
     * @version: 2019-03-08
     */
    @RequestMapping(value = "/findTaskList")
    @ResponseBody
    public PageResult<TaskVo> findTaskList(TaskQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<TaskVo> rst = this.taskService.findTaskList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertTask
     * @Description: TODO(添加任务管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-08
     */
    @RequestMapping(value = "/insertTask")
    @ResponseBody
    public MessageResponse insertTask(String jsons) throws Exception {
        Task obj = JSON.parseObject(jsons, Task.class);
        return this.taskService.insertTask(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateTask
     * @Description: TODO(更新任务管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-08
     */
    @RequestMapping(value = "/updateTask")
    @ResponseBody
    public MessageResponse updateTask(String jsons) throws Exception {
        Task obj = JSON.parseObject(jsons, Task.class);
        return this.taskService.updateTask(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectTaskByPrimaryKey
     * @Description: TODO(获取任务管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Task>
     * @author: 王恩
     * @version: 2019-03-08
     */
    @RequestMapping(value = "/selectTaskByPrimaryKey")
    @ResponseBody
    public SingleResult<TaskVo> selectTaskByPrimaryKey(String id) throws Exception {
        return this.taskService.selectTaskByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteTaskByTaskId
     * @Description: TODO(删除任务管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-08
     */
    @RequestMapping(value = "/deleteTaskByTaskId")
    @ResponseBody
    public MessageResponse deleteTaskByTaskId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.taskService.deleteTaskByTaskId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核任务管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-08
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.taskService.audit(id, rst, message, this.getCurUserProp());
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
     * @author: 王恩
     * @version:2019-03-08
     */
    @RequestMapping(value = "/importXls")
    @ResponseBody
    public MessageResponse importXls(@RequestParam MultipartFile[] file,
                                     String jsons) throws Exception {
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
            String ext = obj
                    .getFilename()
                    .toLowerCase()
                    .substring(
                            obj.getFilename().toLowerCase()
                                    .lastIndexOf("."));
            this.logger.info(ext);
            if (ext.equals(".xls")) {
                list = utils.readExcelByJXL(obj.getInputStream(), 2);
            }
            if (ext.equals(".xlsx")) {
                list = utils.readExcelByPOI(obj.getInputStream(), 2);
            }
        }
        return this.taskService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: 王恩
     * @version:2019-03-08
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.taskService.getList(this.getParams());
    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: 王恩
     * @version:2019-03-08
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.taskService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteTaskByTaskIds
     * @Description: TODO(批量删除任务管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version:2019-03-08
     */
    @RequestMapping(value = "/deleteTaskByTaskIds")
    @ResponseBody
    public MessageResponse deleteTaskByTaskIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.taskService.deleteTaskByTaskIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version:2019-03-08
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.taskService.updateStatus(id, status, this.getCurUserProp());
    }

    @RequestMapping(value = "/releaseTask")
    @ResponseBody
    public MessageResponse releaseTask(String id) throws Exception {
        return this.taskService.releaseTask(id,this.getCurUserProp());
    }
}

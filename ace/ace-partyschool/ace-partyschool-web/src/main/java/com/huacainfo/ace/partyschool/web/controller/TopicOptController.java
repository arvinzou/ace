package com.huacainfo.ace.partyschool.web.controller;

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
import com.huacainfo.ace.partyschool.model.TopicOpt;
import com.huacainfo.ace.partyschool.service.TopicOptService;
import com.huacainfo.ace.partyschool.vo.TopicOptVo;
import com.huacainfo.ace.partyschool.vo.TopicOptQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/topicOpt")
/**
 * @author: 王恩
 * @version: 2019-02-28
 * @Description: TODO(试题选项管理)
 */
public class TopicOptController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopicOptService topicOptService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(试题选项管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <TopicOptVo>
     * @author: 王恩
     * @version: 2019-02-28
     */
    @RequestMapping(value = "/findTopicOptList")
    @ResponseBody
    public PageResult<TopicOptVo> findTopicOptList(TopicOptQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<TopicOptVo> rst = this.topicOptService.findTopicOptList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertTopicOpt
     * @Description: TODO(添加试题选项管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-28
     */
    @RequestMapping(value = "/insertTopicOpt")
    @ResponseBody
    public MessageResponse insertTopicOpt(String jsons) throws Exception {
        TopicOpt obj = JSON.parseObject(jsons, TopicOpt.class);
        return this.topicOptService.insertTopicOpt(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateTopicOpt
     * @Description: TODO(更新试题选项管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-28
     */
    @RequestMapping(value = "/updateTopicOpt")
    @ResponseBody
    public MessageResponse updateTopicOpt(String jsons) throws Exception {
        TopicOpt obj = JSON.parseObject(jsons, TopicOpt.class);
        return this.topicOptService.updateTopicOpt(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectTopicOptByPrimaryKey
     * @Description: TODO(获取试题选项管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TopicOpt>
     * @author: 王恩
     * @version: 2019-02-28
     */
    @RequestMapping(value = "/selectTopicOptByPrimaryKey")
    @ResponseBody
    public SingleResult<TopicOptVo> selectTopicOptByPrimaryKey(String id) throws Exception {
        return this.topicOptService.selectTopicOptByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteTopicOptByTopicOptId
     * @Description: TODO(删除试题选项管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-28
     */
    @RequestMapping(value = "/deleteTopicOptByTopicOptId")
    @ResponseBody
    public MessageResponse deleteTopicOptByTopicOptId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.topicOptService.deleteTopicOptByTopicOptId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核试题选项管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-28
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.topicOptService.audit(id, rst, message, this.getCurUserProp());
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
     * @version:2019-02-28
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
        return this.topicOptService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: 王恩
     * @version:2019-02-28
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.topicOptService.getList(this.getParams());
    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: 王恩
     * @version:2019-02-28
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.topicOptService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteTopicOptByTopicOptIds
     * @Description: TODO(批量删除试题选项管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version:2019-02-28
     */
    @RequestMapping(value = "/deleteTopicOptByTopicOptIds")
    @ResponseBody
    public MessageResponse deleteTopicOptByTopicOptIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.topicOptService.deleteTopicOptByTopicOptIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version:2019-02-28
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.topicOptService.updateStatus(id, status, this.getCurUserProp());
    }
}

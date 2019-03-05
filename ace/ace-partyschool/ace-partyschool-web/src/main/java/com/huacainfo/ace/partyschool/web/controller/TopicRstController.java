package com.huacainfo.ace.partyschool.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.partyschool.model.TopicRst;
import com.huacainfo.ace.partyschool.service.TopicRstService;
import com.huacainfo.ace.partyschool.vo.TopicRstQVo;
import com.huacainfo.ace.partyschool.vo.TopicRstVo;
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
@RequestMapping("/topicRst")
/**
 * @author: 王恩
 * @version: 2019-03-05
 * @Description: TODO(测试答案管理)
 */
public class TopicRstController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopicRstService topicRstService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(测试答案管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <TopicRstVo>
     * @author: 王恩
     * @version: 2019-03-05
     */
    @RequestMapping(value = "/findTopicRstList")
    @ResponseBody
    public PageResult<TopicRstVo> findTopicRstList(TopicRstQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<TopicRstVo> rst = this.topicRstService.findTopicRstList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertTopicRst
     * @Description: TODO(添加测试答案管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-05
     */
    @RequestMapping(value = "/insertTopicRst")
    @ResponseBody
    public MessageResponse insertTopicRst(String jsons) throws Exception {
        TopicRst obj = JSON.parseObject(jsons, TopicRst.class);
        return this.topicRstService.insertTopicRst(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateTopicRst
     * @Description: TODO(更新测试答案管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-05
     */
    @RequestMapping(value = "/updateTopicRst")
    @ResponseBody
    public MessageResponse updateTopicRst(String jsons) throws Exception {
        TopicRst obj = JSON.parseObject(jsons, TopicRst.class);
        return this.topicRstService.updateTopicRst(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectTopicRstByPrimaryKey
     * @Description: TODO(获取测试答案管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TopicRst>
     * @author: 王恩
     * @version: 2019-03-05
     */
    @RequestMapping(value = "/selectTopicRstByPrimaryKey")
    @ResponseBody
    public SingleResult<TopicRstVo> selectTopicRstByPrimaryKey(String id) throws Exception {
        return this.topicRstService.selectTopicRstByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteTopicRstByTopicRstId
     * @Description: TODO(删除测试答案管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-05
     */
    @RequestMapping(value = "/deleteTopicRstByTopicRstId")
    @ResponseBody
    public MessageResponse deleteTopicRstByTopicRstId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.topicRstService.deleteTopicRstByTopicRstId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核测试答案管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-05
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.topicRstService.audit(id, rst, message, this.getCurUserProp());
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
     * @version:2019-03-05
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
        return this.topicRstService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: 王恩
     * @version:2019-03-05
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.topicRstService.getList(this.getParams());
    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: 王恩
     * @version:2019-03-05
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.topicRstService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteTopicRstByTopicRstIds
     * @Description: TODO(批量删除测试答案管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version:2019-03-05
     */
    @RequestMapping(value = "/deleteTopicRstByTopicRstIds")
    @ResponseBody
    public MessageResponse deleteTopicRstByTopicRstIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.topicRstService.deleteTopicRstByTopicRstIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version:2019-03-05
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.topicRstService.updateStatus(id, status, this.getCurUserProp());
    }
}

package com.huacainfo.ace.partyschool.web.controller;

import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.partyschool.model.TestTopic;
import com.huacainfo.ace.partyschool.vo.TestTopicQVo;
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
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.partyschool.model.Test;
import com.huacainfo.ace.partyschool.service.TestService;
import com.huacainfo.ace.partyschool.vo.TestVo;
import com.huacainfo.ace.partyschool.vo.TestQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/test")
/**
 * @author: 王恩
 * @version: 2019-02-27
 * @Description: TODO(测评结果管理)
 */
public class TestController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TestService testService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(测评结果管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <TestVo>
     * @author: 王恩
     * @version: 2019-02-27
     */
    @RequestMapping(value = "/findTestList")
    @ResponseBody
    public PageResult<TestVo> findTestList(TestQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<TestVo> rst = this.testService.findTestList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertTest
     * @Description: TODO(添加测评结果管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-27
     */
    @RequestMapping(value = "/insertTest")
    @ResponseBody
    public MessageResponse insertTest(String jsons) throws Exception {
        Test obj = JSON.parseObject(jsons, Test.class);
        return this.testService.insertTest(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateTest
     * @Description: TODO(更新测评结果管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-27
     */
    @RequestMapping(value = "/updateTest")
    @ResponseBody
    public MessageResponse updateTest(String jsons) throws Exception {
        Test obj = JSON.parseObject(jsons, Test.class);
        return this.testService.updateTest(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectTestByPrimaryKey
     * @Description: TODO(获取测评结果管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Test>
     * @author: 王恩
     * @version: 2019-02-27
     */
    @RequestMapping(value = "/selectTestByPrimaryKey")
    @ResponseBody
    public SingleResult<TestVo> selectTestByPrimaryKey(String id) throws Exception {
        return this.testService.selectTestByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:selectTestByPrimaryKey
     * @Description: TODO(获取测评结果管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Test>
     * @author: 王恩
     * @version: 2019-02-27
     */
    @RequestMapping(value = "/findTopicsByTestId")
    @ResponseBody
    public ResultResponse findTopicsByTestId(String id) throws Exception {
        return this.testService.findTopicsByTestId(id);
    }

    /**
     * @throws
     * @Title:deleteTestByTestId
     * @Description: TODO(删除测评结果管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-27
     */
    @RequestMapping(value = "/deleteTestByTestId")
    @ResponseBody
    public MessageResponse deleteTestByTestId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.testService.deleteTestByTestId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核测评结果管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-02-27
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.testService.audit(id, rst, message, this.getCurUserProp());
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
     * @version:2019-02-27
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
        return this.testService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: 王恩
     * @version:2019-02-27
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.testService.getList(this.getParams());
    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: 王恩
     * @version:2019-02-27
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.testService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteTestByTestIds
     * @Description: TODO(批量删除测评结果管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version:2019-02-27
     */
    @RequestMapping(value = "/deleteTestByTestIds")
    @ResponseBody
    public MessageResponse deleteTestByTestIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.testService.deleteTestByTestIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version:2019-02-27
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.testService.updateStatus(id, status, this.getCurUserProp());
    }


    @RequestMapping(value = "/inserTopics")
    @ResponseBody
    public MessageResponse inserTopics(String jsons) throws Exception {
        TestTopicQVo obj = JSON.parseObject(jsons, TestTopicQVo.class);
        return this.testService.inserTopics(obj, this.getCurUserProp());
    }

    @RequestMapping(value = "/changeTestTopicIndex")
    @ResponseBody
    public MessageResponse changeTestTopicIndex(String tid1, String tid2) throws Exception {
        return this.testService.changeTestTopicIndex(tid1, tid2);
    }

    @RequestMapping(value = "/delTestTopic")
    @ResponseBody
    public MessageResponse delTestTopic(String id) throws Exception {
        return this.testService.delTestTopic(id);
    }

    @RequestMapping(value = "/setScore")
    @ResponseBody
    public MessageResponse setScore(TestTopic obj) throws Exception {
        return this.testService.setScore(obj);
    }


}

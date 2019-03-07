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
import com.huacainfo.ace.partyschool.model.TestRst;
import com.huacainfo.ace.partyschool.service.TestRstService;
import com.huacainfo.ace.partyschool.vo.TestRstVo;
import com.huacainfo.ace.partyschool.vo.TestRstQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/testRst")
/**
 * @author: 王恩
 * @version: 2019-03-06
 * @Description: TODO(测试答案管理)
 */
public class TestRstController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TestRstService testRstService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(测试答案管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <TestRstVo>
     * @author: 王恩
     * @version: 2019-03-06
     */
    @RequestMapping(value = "/findTestRstList")
    @ResponseBody
    public PageResult<TestRstVo> findTestRstList(TestRstQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<TestRstVo> rst = this.testRstService.findTestRstList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertTestRst
     * @Description: TODO(添加测试答案管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-06
     */
    @RequestMapping(value = "/insertTestRst")
    @ResponseBody
    public MessageResponse insertTestRst(String jsons) throws Exception {
        TestRst obj = JSON.parseObject(jsons, TestRst.class);
        return this.testRstService.insertTestRst(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateTestRst
     * @Description: TODO(更新测试答案管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-06
     */
    @RequestMapping(value = "/updateTestRst")
    @ResponseBody
    public MessageResponse updateTestRst(String jsons) throws Exception {
        TestRst obj = JSON.parseObject(jsons, TestRst.class);
        return this.testRstService.updateTestRst(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectTestRstByPrimaryKey
     * @Description: TODO(获取测试答案管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TestRst>
     * @author: 王恩
     * @version: 2019-03-06
     */
    @RequestMapping(value = "/selectTestRstByPrimaryKey")
    @ResponseBody
    public SingleResult<TestRstVo> selectTestRstByPrimaryKey(String id) throws Exception {
        return this.testRstService.selectTestRstByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteTestRstByTestRstId
     * @Description: TODO(删除测试答案管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-06
     */
    @RequestMapping(value = "/deleteTestRstByTestRstId")
    @ResponseBody
    public MessageResponse deleteTestRstByTestRstId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.testRstService.deleteTestRstByTestRstId(id, this.getCurUserProp());
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
     * @version: 2019-03-06
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.testRstService.audit(id, rst, message, this.getCurUserProp());
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
     * @version:2019-03-06
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
        return this.testRstService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: 王恩
     * @version:2019-03-06
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.testRstService.getList(this.getParams());
    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: 王恩
     * @version:2019-03-06
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.testRstService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteTestRstByTestRstIds
     * @Description: TODO(批量删除测试答案管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version:2019-03-06
     */
    @RequestMapping(value = "/deleteTestRstByTestRstIds")
    @ResponseBody
    public MessageResponse deleteTestRstByTestRstIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.testRstService.deleteTestRstByTestRstIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version:2019-03-06
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.testRstService.updateStatus(id, status, this.getCurUserProp());
    }
}

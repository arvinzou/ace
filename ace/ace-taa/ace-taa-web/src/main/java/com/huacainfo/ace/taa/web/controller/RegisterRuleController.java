package com.huacainfo.ace.taa.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.portal.vo.MongoFile;
import com.huacainfo.ace.taa.model.RegisterRule;
import com.huacainfo.ace.taa.service.RegisterRuleService;
import com.huacainfo.ace.taa.vo.RegisterRuleQVo;
import com.huacainfo.ace.taa.vo.RegisterRuleVo;
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
@RequestMapping("/registerRule")
/**
 * @author: Arvin
 * @version: 2019-03-08
 * @Description: TODO(注册规则)
 */
public class RegisterRuleController extends TaaBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RegisterRuleService registerRuleService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(注册规则分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <RegisterRuleVo>
     * @author: Arvin
     * @version: 2019-03-08
     */
    @RequestMapping(value = "/findRegisterRuleList")
    @ResponseBody
    public PageResult<RegisterRuleVo> findRegisterRuleList(RegisterRuleQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<RegisterRuleVo> rst = this.registerRuleService.findRegisterRuleList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertRegisterRule
     * @Description: TODO(添加注册规则)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-03-08
     */
    @RequestMapping(value = "/insertRegisterRule")
    @ResponseBody
    public MessageResponse insertRegisterRule(String jsons) throws Exception {
        RegisterRule obj = JSON.parseObject(jsons, RegisterRule.class);
        return this.registerRuleService.insertRegisterRule(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateRegisterRule
     * @Description: TODO(更新注册规则)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-03-08
     */
    @RequestMapping(value = "/updateRegisterRule")
    @ResponseBody
    public MessageResponse updateRegisterRule(String jsons) {
        RegisterRule obj = JSON.parseObject(jsons, RegisterRule.class);
        return this.registerRuleService.updateRegisterRule(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectRegisterRuleByPrimaryKey
     * @Description: TODO(获取注册规则)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<RegisterRule>
     * @author: Arvin
     * @version: 2019-03-08
     */
    @RequestMapping(value = "/selectRegisterRuleByPrimaryKey")
    @ResponseBody
    public SingleResult<RegisterRuleVo> selectRegisterRuleByPrimaryKey(String id) throws Exception {
        return this.registerRuleService.selectRegisterRuleByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteRegisterRuleByRegisterRuleId
     * @Description: TODO(删除注册规则)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-03-08
     */
    @RequestMapping(value = "/deleteRegisterRuleByRegisterRuleId")
    @ResponseBody
    public MessageResponse deleteRegisterRuleByRegisterRuleId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.registerRuleService.deleteRegisterRuleByRegisterRuleId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核注册规则)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-03-08
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.registerRuleService.audit(id, rst, message, this.getCurUserProp());
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
     * @author: Arvin
     * @version:2019-03-08
     */
    @RequestMapping(value = "/importXls")
    @ResponseBody
    public MessageResponse importXls(@RequestParam MultipartFile[] file, String jsons) throws Exception {
        Map<String, Object> params = JsonUtil.toMap(jsons);
        if (CommonUtils.isBlank(params) || CommonUtils.isBlank(params.get("deptId"))) {
            return new MessageResponse(ResultCode.FAIL, "所属支队不能为空");
        }

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
            String ext = obj.getFilename().toLowerCase()
                    .substring(obj.getFilename().toLowerCase().lastIndexOf("."));
            this.logger.info(ext);
            if (ext.equals(".xls")) {
                list = utils.readExcelByJXL(obj.getInputStream(), 2);
            }
            if (ext.equals(".xlsx")) {
                list = utils.readExcelByPOI(obj.getInputStream(), 2);
            }
        }
        return this.registerRuleService.importXls(params, list, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version:2019-03-08
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.registerRuleService.updateStatus(id, status, this.getCurUserProp());
    }
}

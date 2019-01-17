package com.huacainfo.ace.partyschool.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.partyschool.model.EnrollRoster;
import com.huacainfo.ace.partyschool.service.EnrollRosterService;
import com.huacainfo.ace.partyschool.vo.EnrollRosterQVo;
import com.huacainfo.ace.partyschool.vo.EnrollRosterVo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/enrollRoster")
/**
 * @author: Arvin
 * @version: 2019-01-16
 * @Description: TODO(报名花名册管理)
 */
public class EnrollRosterController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EnrollRosterService enrollRosterService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(报名花名册管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <EnrollRosterVo>
     * @author: Arvin
     * @version: 2019-01-16
     */
    @RequestMapping(value = "/findEnrollRosterList")
    @ResponseBody
    public PageResult<EnrollRosterVo> findEnrollRosterList(EnrollRosterQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<EnrollRosterVo> rst = this.enrollRosterService.findEnrollRosterList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertEnrollRoster
     * @Description: TODO(添加报名花名册管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-16
     */
    @RequestMapping(value = "/insertEnrollRoster")
    @ResponseBody
    public MessageResponse insertEnrollRoster(String jsons) throws Exception {
        EnrollRoster obj = JSON.parseObject(jsons, EnrollRoster.class);
        return this.enrollRosterService.insertEnrollRoster(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateEnrollRoster
     * @Description: TODO(更新报名花名册管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-16
     */
    @RequestMapping(value = "/updateEnrollRoster")
    @ResponseBody
    public MessageResponse updateEnrollRoster(String jsons) throws Exception {
        EnrollRoster obj = JSON.parseObject(jsons, EnrollRoster.class);
        return this.enrollRosterService.updateEnrollRoster(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectEnrollRosterByPrimaryKey
     * @Description: TODO(获取报名花名册管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EnrollRoster>
     * @author: Arvin
     * @version: 2019-01-16
     */
    @RequestMapping(value = "/selectEnrollRosterByPrimaryKey")
    @ResponseBody
    public SingleResult<EnrollRosterVo> selectEnrollRosterByPrimaryKey(String id) throws Exception {
        return this.enrollRosterService.selectEnrollRosterByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteEnrollRosterByEnrollRosterId
     * @Description: TODO(删除报名花名册管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-16
     */
    @RequestMapping(value = "/deleteEnrollRosterByEnrollRosterId")
    @ResponseBody
    public MessageResponse deleteEnrollRosterByEnrollRosterId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.enrollRosterService.deleteEnrollRosterByEnrollRosterId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核报名花名册管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-16
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.enrollRosterService.audit(id, rst, message, this.getCurUserProp());
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
     * @author: Arvin
     * @version:2019-01-16
     */
    @RequestMapping(value = "/importXls")
    @ResponseBody
    public MessageResponse importXls(@RequestParam MultipartFile[] file,
                                     String jsons) throws Exception {
        Map<String, Object> params = JsonUtil.toMap(jsons);
        String clsId = String.valueOf(params.get("clsId"));
        if (CommonUtils.isBlank(clsId)) {
            return new MessageResponse(ResultCode.FAIL, "请选择班次信息");
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
            String ext = obj.getFilename()
                    .toLowerCase()
                    .substring(obj.getFilename().toLowerCase().lastIndexOf("."));
            this.logger.info(ext);
            if (ext.equals(".xls")) {
                list = utils.readExcelByJXL(obj.getInputStream(), 2);
            }
            if (ext.equals(".xlsx")) {
                list = utils.readExcelByPOI(obj.getInputStream(), 2);
            }
        }
        return this.enrollRosterService.importXls(clsId, list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: Arvin
     * @version:2019-01-16
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.enrollRosterService.getList(this.getParams());
    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: Arvin
     * @version:2019-01-16
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition(String q, String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("q", id);
        if (!CommonUtils.isBlank(q)) {
            params.put("q", q);
        }
        return this.enrollRosterService.getListByCondition(params);
    }


    /**
     * @throws
     * @Title:deleteEnrollRosterByEnrollRosterIds
     * @Description: TODO(批量删除报名花名册管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version:2019-01-16
     */
    @RequestMapping(value = "/deleteEnrollRosterByEnrollRosterIds")
    @ResponseBody
    public MessageResponse deleteEnrollRosterByEnrollRosterIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.enrollRosterService.deleteEnrollRosterByEnrollRosterIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version:2019-01-16
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.enrollRosterService.updateStatus(id, status, this.getCurUserProp());
    }

    /**
     * 批量开启/关闭报名
     *
     * @param clsId  班级ID
     * @param status 开启/关闭  1-开，0-关
     * @return MessageResponse
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/updateStatusByClsId")
    public MessageResponse updateStatusByClsId(String clsId, String status) throws Exception {
        if (!StringUtil.areNotEmpty(clsId, status)) {
            return new MessageResponse(ResultCode.FAIL, "缺少必要参数");
        }
        return this.enrollRosterService.updateStatusByClsId(clsId, status, this.getCurUserProp());
    }
}

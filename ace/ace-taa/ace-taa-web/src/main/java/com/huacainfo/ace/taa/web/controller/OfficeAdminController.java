package com.huacainfo.ace.taa.web.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.portal.vo.MongoFile;
import com.huacainfo.ace.taa.model.OfficeAdmin;
import com.huacainfo.ace.taa.service.OfficeAdminService;
import com.huacainfo.ace.taa.vo.OfficeAdminQVo;
import com.huacainfo.ace.taa.vo.OfficeAdminVo;
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
@RequestMapping("/officeAdmin")
/**
 * @author: Arvin
 * @version: 2019-02-28
 * @Description: TODO(内勤人员)
 */
public class OfficeAdminController extends TaaBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OfficeAdminService officeAdminService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(内勤人员分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <OfficeAdminVo>
     * @author: Arvin
     * @version: 2019-02-28
     */
    @RequestMapping(value = "/findOfficeAdminList")
    @ResponseBody
    public PageResult<OfficeAdminVo> findOfficeAdminList(OfficeAdminQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<OfficeAdminVo> rst = this.officeAdminService.findOfficeAdminList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertOfficeAdmin
     * @Description: TODO(添加内勤人员)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-28
     */
    @RequestMapping(value = "/insertOfficeAdmin")
    @ResponseBody
    public MessageResponse insertOfficeAdmin(String jsons) throws Exception {
        OfficeAdmin obj = JSON.parseObject(jsons, OfficeAdmin.class);
        return this.officeAdminService.insertOfficeAdmin(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateOfficeAdmin
     * @Description: TODO(更新内勤人员)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-28
     */
    @RequestMapping(value = "/updateOfficeAdmin")
    @ResponseBody
    public MessageResponse updateOfficeAdmin(String jsons) throws Exception {
        OfficeAdmin obj = JSON.parseObject(jsons, OfficeAdmin.class);
        return this.officeAdminService.updateOfficeAdmin(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectOfficeAdminByPrimaryKey
     * @Description: TODO(获取内勤人员)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<OfficeAdmin>
     * @author: Arvin
     * @version: 2019-02-28
     */
    @RequestMapping(value = "/selectOfficeAdminByPrimaryKey")
    @ResponseBody
    public SingleResult<OfficeAdminVo> selectOfficeAdminByPrimaryKey(String id) throws Exception {
        return this.officeAdminService.selectOfficeAdminByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteOfficeAdminByOfficeAdminId
     * @Description: TODO(删除内勤人员)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-28
     */
    @RequestMapping(value = "/deleteByPrimaryKey")
    @ResponseBody
    public MessageResponse deleteByPrimaryKey(String id) throws Exception {
        return this.officeAdminService.deleteByPrimaryKey(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核内勤人员)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-28
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.officeAdminService.audit(id, rst, message, this.getCurUserProp());
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
        return this.officeAdminService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: Arvin
     * @version:2019-02-28
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.officeAdminService.getList(this.getParams());
    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: Arvin
     * @version:2019-02-28
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.officeAdminService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteOfficeAdminByOfficeAdminIds
     * @Description: TODO(批量删除内勤人员)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version:2019-02-28
     */
    @RequestMapping(value = "/deleteOfficeAdminByOfficeAdminIds")
    @ResponseBody
    public MessageResponse deleteOfficeAdminByOfficeAdminIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.officeAdminService.deleteOfficeAdminByOfficeAdminIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version:2019-02-28
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.officeAdminService.updateStatus(id, status, this.getCurUserProp());
    }

    /**
     * @Description: TODO(根据昵称查询微信用户)
     */
    @ResponseBody
    @RequestMapping(value = "/selectList")
    public List<Map<String, Object>> selectList(String nickname) throws Exception {
        return officeAdminService.selectList(nickname);
    }
}

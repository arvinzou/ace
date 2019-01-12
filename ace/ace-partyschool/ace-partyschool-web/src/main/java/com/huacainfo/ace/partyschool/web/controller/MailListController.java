package com.huacainfo.ace.partyschool.web.controller;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.web.controller.BaseController;
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
import com.huacainfo.ace.partyschool.model.MailList;
import com.huacainfo.ace.partyschool.service.MailListService;
import com.huacainfo.ace.partyschool.vo.MailListVo;
import com.huacainfo.ace.partyschool.vo.MailListQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/mailList")
/**
 * @author: 陈晓克
 * @version: 2019-01-12
 * @Description: TODO(通讯录)
 */
public class MailListController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MailListService mailListService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(通讯录分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <MailListVo>
     * @author: 陈晓克
     * @version: 2019-01-12
     */
    @RequestMapping(value = "/findMailListList")
    @ResponseBody
    public PageResult<MailListVo> findMailListList(MailListQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<MailListVo> rst = this.mailListService.findMailListList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertMailList
     * @Description: TODO(添加通讯录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-12
     */
    @RequestMapping(value = "/insertMailList")
    @ResponseBody
    public MessageResponse insertMailList(String jsons) throws Exception {
        MailList obj = JSON.parseObject(jsons, MailList.class);
        return this.mailListService.insertMailList(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateMailList
     * @Description: TODO(更新通讯录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-12
     */
    @RequestMapping(value = "/updateMailList")
    @ResponseBody
    public MessageResponse updateMailList(String jsons) throws Exception {
        MailList obj = JSON.parseObject(jsons, MailList.class);
        return this.mailListService.updateMailList(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectMailListByPrimaryKey
     * @Description: TODO(获取通讯录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<MailList>
     * @author: 陈晓克
     * @version: 2019-01-12
     */
    @RequestMapping(value = "/selectMailListByPrimaryKey")
    @ResponseBody
    public SingleResult<MailListVo> selectMailListByPrimaryKey(String id) throws Exception {
        return this.mailListService.selectMailListByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteMailListByMailListId
     * @Description: TODO(删除通讯录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-12
     */
    @RequestMapping(value = "/deleteMailListByMailListId")
    @ResponseBody
    public MessageResponse deleteMailListByMailListId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.mailListService.deleteMailListByMailListId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核通讯录)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-12
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.mailListService.audit(id, rst, message, this.getCurUserProp());
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
     * @author: 陈晓克
     * @version:2019-01-12
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
        return this.mailListService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: 陈晓克
     * @version:2019-01-12
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.mailListService.getList(this.getParams());
    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version:2019-01-12
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.mailListService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteMailListByMailListIds
     * @Description: TODO(批量删除通讯录)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version:2019-01-12
     */
    @RequestMapping(value = "/deleteMailListByMailListIds")
    @ResponseBody
    public MessageResponse deleteMailListByMailListIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.mailListService.deleteMailListByMailListIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version:2019-01-12
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.mailListService.updateStatus(id, status, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getTreeList
     * @Description: TODO(加载通讯录)
     * @param: @return
     * @return: List<Tree>
     * @author: chenxiaoke
     * @version: 2019-01-12
     */
    @RequestMapping(value = "/www/getTreeList")
    @ResponseBody
    public  List<Tree> getTreeList(String name){
        return this.mailListService.getTreeList(name,this.getCurUserProp());
    }
}

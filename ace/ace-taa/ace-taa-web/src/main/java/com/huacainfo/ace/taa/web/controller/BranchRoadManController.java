package com.huacainfo.ace.taa.web.controller;

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
import com.huacainfo.ace.taa.model.BranchRoadMan;
import com.huacainfo.ace.taa.service.BranchRoadManService;
import com.huacainfo.ace.taa.vo.BranchRoadManVo;
import com.huacainfo.ace.taa.vo.BranchRoadManQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/branchRoadMan")
/**
 * @author: heshaung
 * @version: 2019-03-29
 * @Description: TODO(BranchRoadMan)
 */
public class BranchRoadManController extends TaaBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BranchRoadManService branchRoadManService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(BranchRoadMan分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <BranchRoadManVo>
     * @author: heshaung
     * @version: 2019-03-29
     */
    @RequestMapping(value = "/findBranchRoadManList")
    @ResponseBody
    public PageResult<BranchRoadManVo> findBranchRoadManList(BranchRoadManQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<BranchRoadManVo> rst = this.branchRoadManService.findBranchRoadManList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertBranchRoadMan
     * @Description: TODO(添加BranchRoadMan)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshaung
     * @version: 2019-03-29
     */
    @RequestMapping(value = "/insertBranchRoadMan")
    @ResponseBody
    public MessageResponse insertBranchRoadMan(BranchRoadMan obj) throws Exception {
        // BranchRoadMan obj = JSON.parseObject(jsons, BranchRoadMan.class);
        return this.branchRoadManService.insertBranchRoadMan(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateBranchRoadMan
     * @Description: TODO(更新BranchRoadMan)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshaung
     * @version: 2019-03-29
     */
    @RequestMapping(value = "/updateBranchRoadMan")
    @ResponseBody
    public MessageResponse updateBranchRoadMan(BranchRoadMan obj, String createDate1) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // BranchRoadMan obj = JSON.parseObject(jsons, BranchRoadMan.class);
        obj.setCreateDate(sdf.parse(createDate1));
        return this.branchRoadManService.updateBranchRoadMan(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectBranchRoadManByPrimaryKey
     * @Description: TODO(获取BranchRoadMan)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<BranchRoadMan>
     * @author: heshaung
     * @version: 2019-03-29
     */
    @RequestMapping(value = "/selectBranchRoadManByPrimaryKey")
    @ResponseBody
 /*   public SingleResult<BranchRoadMan> selectBranchRoadManByPrimaryKey(String id) throws Exception {
        return this.branchRoadManService.selectBranchRoadManByPrimaryKey(id);
    }*/
    public SingleResult<BranchRoadManVo> selectBranchRoadManByPrimaryKey(String id) throws Exception {
        return this.branchRoadManService.selectVoBranchRoadManByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteBranchRoadManByBranchRoadManId
     * @Description: TODO(删除BranchRoadMan)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshaung
     * @version: 2019-03-29
     */
    @RequestMapping(value = "/deleteBranchRoadManByBranchRoadManId")
    @ResponseBody
    public MessageResponse deleteBranchRoadManByBranchRoadManId(String id) throws Exception {
        //JSONObject json = JSON.parseObject(jsons);
        // String id = json.getString("id");
        return this.branchRoadManService.deleteBranchRoadManByBranchRoadManId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核BranchRoadMan)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshaung
     * @version: 2019-03-29
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.branchRoadManService.audit(id, rst, message, this.getCurUserProp());
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
     * @author: heshaung
     * @version:2019-03-29
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
        return this.branchRoadManService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: heshaung
     * @version:2019-03-29
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList(String roadSectionId) throws Exception {
        return this.branchRoadManService.getList(roadSectionId);
    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String, Object>
     * @author: heshaung
     * @version:2019-03-29
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.branchRoadManService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteBranchRoadManByBranchRoadManIds
     * @Description: TODO(批量删除BranchRoadMan)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshaung
     * @version:2019-03-29
     */
    @RequestMapping(value = "/deleteBranchRoadManByBranchRoadManIds")
    @ResponseBody
    public MessageResponse deleteBranchRoadManByBranchRoadManIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.branchRoadManService.deleteBranchRoadManByBranchRoadManIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshaung
     * @version:2019-03-29
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.branchRoadManService.updateStatus(id, status, this.getCurUserProp());
    }
}

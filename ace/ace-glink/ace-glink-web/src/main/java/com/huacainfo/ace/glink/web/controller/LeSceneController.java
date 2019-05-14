package com.huacainfo.ace.glink.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.glink.model.LeScene;
import com.huacainfo.ace.glink.service.LeSceneService;
import com.huacainfo.ace.glink.vo.LeSceneQVo;
import com.huacainfo.ace.glink.vo.LeSceneVo;
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
@RequestMapping("/leScene")
/**
 * @author: Arvin
 * @version: 2019-05-13
 * @Description: TODO(弱电场景管理)
 */
public class LeSceneController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LeSceneService leSceneService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(弱电场景管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <LeSceneVo>
     * @author: Arvin
     * @version: 2019-05-13
     */
    @RequestMapping(value = "/findLeSceneList")
    @ResponseBody
    public PageResult<LeSceneVo> findLeSceneList(LeSceneQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<LeSceneVo> rst = this.leSceneService.findLeSceneList(condition, page.getStart(),
                page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertLeScene
     * @Description: TODO(添加弱电场景管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-05-13
     */
    @RequestMapping(value = "/insertLeScene")
    @ResponseBody
    public MessageResponse insertLeScene(String jsons) throws Exception {
        LeScene obj = JSON.parseObject(jsons, LeScene.class);
        return this.leSceneService.insertLeScene(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateLeScene
     * @Description: TODO(更新弱电场景管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-05-13
     */
    @RequestMapping(value = "/updateLeScene")
    @ResponseBody
    public MessageResponse updateLeScene(String jsons) throws Exception {
        LeScene obj = JSON.parseObject(jsons, LeScene.class);
        return this.leSceneService.updateLeScene(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectLeSceneByPrimaryKey
     * @Description: TODO(获取弱电场景管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LeScene>
     * @author: Arvin
     * @version: 2019-05-13
     */
    @RequestMapping(value = "/selectLeSceneByPrimaryKey")
    @ResponseBody
    public SingleResult
            <LeSceneVo> selectLeSceneByPrimaryKey(String id) throws Exception {
        return this.leSceneService.selectLeSceneByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteLeSceneByLeSceneId
     * @Description: TODO(删除弱电场景管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-05-13
     */
    @RequestMapping(value = "/deleteLeSceneByLeSceneId")
    @ResponseBody
    public MessageResponse deleteLeSceneByLeSceneId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.leSceneService.deleteLeSceneByLeSceneId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核弱电场景管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-05-13
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.leSceneService.audit(id, rst, message, this.getCurUserProp());
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
     * @version:2019-05-13
     */
    @RequestMapping(value = "/importXls")
    @ResponseBody
    public MessageResponse importXls(@RequestParam MultipartFile[] file,
                                     String jsons) throws Exception {
        ExcelUtils utils = new ExcelUtils();
        List<Map<String, Object>> list = new ArrayList<>();
        MongoFile[] files = new MongoFile[file.length];
        int i = 0;
        for (MultipartFile o : file) {
            MongoFile obj = new MongoFile();
            obj.setInputStream(o.getInputStream());
            obj.setFilename(o.getOriginalFilename());
            obj.setLength(o.getSize());
            files[i] = obj;
            i++;
            String ext = obj.getFilename().toLowerCase();
            ext = ext.substring(obj.getFilename().toLowerCase().lastIndexOf("."));
            this.logger.info(ext);
            if (ext.equals(".xls")) {
                list = utils.readExcelByJXL(obj.getInputStream(), 2);
            }
            if (ext.equals(".xlsx")) {
                list = utils.readExcelByPOI(obj.getInputStream(), 2);
            }
        }
        return this.leSceneService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: Arvin
     * @version:2019-05-13
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.leSceneService.getList(this.getParams());
    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map
     * <String
     * ,Object>
     * @author: Arvin
     * @version:2019-05-13
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.leSceneService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteLeSceneByLeSceneIds
     * @Description: TODO(批量删除弱电场景管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version:2019-05-13
     */
    @RequestMapping(value = "/deleteLeSceneByLeSceneIds")
    @ResponseBody
    public MessageResponse deleteLeSceneByLeSceneIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.leSceneService.deleteLeSceneByLeSceneIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version:2019-05-13
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.leSceneService.updateStatus(id, status, this.getCurUserProp());
    }

    /**
     * 同步中控机数据
     *
     * @return MessageResponse 处理结果
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/syncData")
    public MessageResponse syncData() throws Exception {
        return this.leSceneService.syncData(this.getCurUserProp());
    }
}

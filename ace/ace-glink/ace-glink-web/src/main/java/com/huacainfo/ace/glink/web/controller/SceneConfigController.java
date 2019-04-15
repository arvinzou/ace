package com.huacainfo.ace.glink.web.controller;

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
import com.huacainfo.ace.glink.model.SceneConfig;
import com.huacainfo.ace.glink.service.SceneConfigService;
import com.huacainfo.ace.glink.vo.SceneConfigVo;
import com.huacainfo.ace.glink.vo.SceneConfigQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/sceneConfig")
/**
 * @author: huacai003
 * @version: 2019-04-15
 * @Description: TODO(场景设置)
 */
public class SceneConfigController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SceneConfigService sceneConfigService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(场景设置分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <SceneConfigVo>
     * @author: huacai003
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/findSceneConfigList")
    @ResponseBody
    public PageResult<SceneConfigVo> findSceneConfigList(SceneConfigQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<SceneConfigVo> rst = this.sceneConfigService.findSceneConfigList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertSceneConfig
     * @Description: TODO(添加场景设置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/insertSceneConfig")
    @ResponseBody
    public MessageResponse insertSceneConfig(String jsons) throws Exception {
        SceneConfig obj = JSON.parseObject(jsons, SceneConfig.class);
        return this.sceneConfigService.insertSceneConfig(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateSceneConfig
     * @Description: TODO(更新场景设置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/updateSceneConfig")
    @ResponseBody
    public MessageResponse updateSceneConfig(String jsons) throws Exception {
        SceneConfig obj = JSON.parseObject(jsons, SceneConfig.class);
        return this.sceneConfigService.updateSceneConfig(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectSceneConfigByPrimaryKey
     * @Description: TODO(获取场景设置)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SceneConfig>
     * @author: huacai003
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/selectSceneConfigByPrimaryKey")
    @ResponseBody
    public SingleResult
            <SceneConfigVo> selectSceneConfigByPrimaryKey(String id) throws Exception {
        return this.sceneConfigService.selectSceneConfigByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteSceneConfigBySceneConfigId
     * @Description: TODO(删除场景设置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/deleteSceneConfigBySceneConfigId")
    @ResponseBody
    public MessageResponse deleteSceneConfigBySceneConfigId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.sceneConfigService.deleteSceneConfigBySceneConfigId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核场景设置)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.sceneConfigService.audit(id, rst, message, this.getCurUserProp());
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
     * @author: huacai003
     * @version:2019-04-15
     */
    @RequestMapping(value = "/importXls")
    @ResponseBody
    public MessageResponse importXls(@RequestParam MultipartFile[] file, String jsons) throws Exception {
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
        return this.sceneConfigService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: huacai003
     * @version:2019-04-15
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.sceneConfigService.getList(this.getParams());
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
     * @author: huacai003
     * @version:2019-04-15
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.sceneConfigService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteSceneConfigBySceneConfigIds
     * @Description: TODO(批量删除场景设置)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version:2019-04-15
     */
    @RequestMapping(value = "/deleteSceneConfigBySceneConfigIds")
    @ResponseBody
    public MessageResponse deleteSceneConfigBySceneConfigIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.sceneConfigService.deleteSceneConfigBySceneConfigIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version:2019-04-15
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.sceneConfigService.updateStatus(id, status, this.getCurUserProp());
    }
}

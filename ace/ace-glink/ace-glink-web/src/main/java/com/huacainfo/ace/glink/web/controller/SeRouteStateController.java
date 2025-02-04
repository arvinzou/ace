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
import com.huacainfo.ace.glink.model.SeRouteState;
import com.huacainfo.ace.glink.service.SeRouteStateService;
import com.huacainfo.ace.glink.vo.SeRouteStateVo;
import com.huacainfo.ace.glink.vo.SeRouteStateQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/seRouteState")
/**
 * @author: huacai003
 * @version: 2019-04-18
 * @Description: TODO(路由器运行状态)
 */
public class SeRouteStateController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeRouteStateService seRouteStateService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(路由器运行状态分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <SeRouteStateVo>
     * @author: huacai003
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/findSeRouteStateList")
    @ResponseBody
    public PageResult<SeRouteStateVo> findSeRouteStateList(SeRouteStateQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<SeRouteStateVo> rst = this.seRouteStateService.findSeRouteStateList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertSeRouteState
     * @Description: TODO(添加路由器运行状态)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/insertSeRouteState")
    @ResponseBody
    public MessageResponse insertSeRouteState(String jsons) throws Exception {
        SeRouteState obj = JSON.parseObject(jsons, SeRouteState.class);
        return this.seRouteStateService.insertSeRouteState(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateSeRouteState
     * @Description: TODO(更新路由器运行状态)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/updateSeRouteState")
    @ResponseBody
    public MessageResponse updateSeRouteState(String jsons) throws Exception {
        SeRouteState obj = JSON.parseObject(jsons, SeRouteState.class);
        return this.seRouteStateService.updateSeRouteState(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectSeRouteStateByPrimaryKey
     * @Description: TODO(获取路由器运行状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeRouteState>
     * @author: huacai003
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/selectSeRouteStateByPrimaryKey")
    @ResponseBody
    public SingleResult<SeRouteStateVo> selectSeRouteStateByPrimaryKey(String id) throws Exception {
        return this.seRouteStateService.selectSeRouteStateByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteSeRouteStateBySeRouteStateId
     * @Description: TODO(删除路由器运行状态)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/deleteSeRouteStateBySeRouteStateId")
    @ResponseBody
    public MessageResponse deleteSeRouteStateBySeRouteStateId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.seRouteStateService.deleteSeRouteStateBySeRouteStateId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核路由器运行状态)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {
        return this.seRouteStateService.audit(id, rst, message, this.getCurUserProp());
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
     * @version:2019-04-18
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
        return this.seRouteStateService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: huacai003
     * @version:2019-04-18
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.seRouteStateService.getList(this.getParams());
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
     * @version:2019-04-18
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map
            <String
                    , Object> getListByCondition() {
        return this.seRouteStateService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteSeRouteStateBySeRouteStateIds
     * @Description: TODO(批量删除路由器运行状态)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version:2019-04-18
     */
    @RequestMapping(value = "/deleteSeRouteStateBySeRouteStateIds")
    @ResponseBody
    public MessageResponse deleteSeRouteStateBySeRouteStateIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.seRouteStateService.deleteSeRouteStateBySeRouteStateIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version:2019-04-18
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.seRouteStateService.updateStatus(id, status, this.getCurUserProp());
    }

    @RequestMapping(value = "/syncData")
    @ResponseBody
    public MessageResponse syncData() throws Exception {
        return this.seRouteStateService.syncData(this.getCurUserProp());
    }
}

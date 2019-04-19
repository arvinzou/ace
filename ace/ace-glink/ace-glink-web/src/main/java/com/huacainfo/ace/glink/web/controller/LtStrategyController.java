package com.huacainfo.ace.glink.web.controller;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.glink.model.LtLnkObject;
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
import com.huacainfo.ace.glink.model.LtStrategy;
import com.huacainfo.ace.glink.service.LtStrategyService;
import com.huacainfo.ace.glink.vo.LtStrategyVo;
import com.huacainfo.ace.glink.vo.LtStrategyQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/ltStrategy")
/**
 * @author: huacai003
 * @version: 2019-04-10
 * @Description: TODO(策略管理)
 */
public class LtStrategyController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LtStrategyService ltStrategyService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(策略管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <LtStrategyVo>
     * @author: huacai003
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/findLtStrategyList")
    @ResponseBody
    public PageResult
            <LtStrategyVo>
    findLtStrategyList(LtStrategyQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<LtStrategyVo> rst = this.ltStrategyService.findLtStrategyList(condition, page.getStart(),
                        page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertLtStrategy
     * @Description: TODO(添加策略管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/insertLtStrategy")
    @ResponseBody
    public MessageResponse insertLtStrategy(String jsons) throws Exception {
        LtStrategy obj = JSON.parseObject(jsons, LtStrategy.class);
        return this.ltStrategyService.insertLtStrategy(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateLtStrategy
     * @Description: TODO(更新策略管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/updateLtStrategy")
    @ResponseBody
    public MessageResponse updateLtStrategy(String jsons) throws Exception {
        LtStrategy obj = JSON.parseObject(jsons, LtStrategy.class);
        return this.ltStrategyService.updateLtStrategy(obj, this.getCurUserProp());
    }

    @RequestMapping(value = "/updateLtStrategyVo")
    @ResponseBody
    public MessageResponse updateLtStrategyVo(String jsons) throws Exception {
        LtStrategy obj = JSON.parseObject(jsons, LtStrategy.class);
        return this.ltStrategyService.updateLtStrategyVo(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectLtStrategyByPrimaryKey
     * @Description: TODO(获取策略管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LtStrategy>
     * @author: huacai003
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/selectLtStrategyByPrimaryKey")
    @ResponseBody
    public SingleResult
            <LtStrategyVo> selectLtStrategyByPrimaryKey(String id) throws Exception {
        return this.ltStrategyService.selectLtStrategyByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteLtStrategyByLtStrategyId
     * @Description: TODO(删除策略管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/deleteLtStrategyByLtStrategyId")
    @ResponseBody
    public MessageResponse deleteLtStrategyByLtStrategyId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.ltStrategyService.deleteLtStrategyByLtStrategyId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核策略管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.ltStrategyService.audit(id, rst, message, this.getCurUserProp());
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
     * @version:2019-04-10
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
        return this.ltStrategyService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: huacai003
     * @version:2019-04-10
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult<Map<String, Object>> getList() throws Exception {
        return this.ltStrategyService.getList(this.getParams());
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
     * @version:2019-04-10
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map<String, Object> getListByCondition() {
        return this.ltStrategyService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteLtStrategyByLtStrategyIds
     * @Description: TODO(批量删除策略管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version:2019-04-10
     */
    @RequestMapping(value = "/deleteLtStrategyByLtStrategyIds")
    @ResponseBody
    public MessageResponse deleteLtStrategyByLtStrategyIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.ltStrategyService.deleteLtStrategyByLtStrategyIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version:2019-04-10
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String lnkCode,String lnkType,String aiCode) throws Exception {
        LtLnkObject ltLnkObject =new LtLnkObject();
        ltLnkObject.setLnkCode(lnkCode);
        ltLnkObject.setLnkType(lnkType);
        ltLnkObject.setAiCode(aiCode);
        return this.ltStrategyService.updateStatus(id,  ltLnkObject, this.getCurUserProp());
    }
}

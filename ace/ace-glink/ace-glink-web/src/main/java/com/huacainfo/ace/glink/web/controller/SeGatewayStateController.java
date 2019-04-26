package com.huacainfo.ace.glink.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.glink.model.SeGatewayState;
import com.huacainfo.ace.glink.service.SeGatewayStateService;
import com.huacainfo.ace.glink.vo.SeGatewayStateQVo;
import com.huacainfo.ace.glink.vo.SeGatewayStateVo;
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
@RequestMapping("/seGatewayState")
/**
 * @author: luocan
 * @version: 2019-04-18
 * @Description: TODO(网关数据)
 */
public class SeGatewayStateController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeGatewayStateService seGatewayStateService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(网关数据分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <SeGatewayStateVo>
     * @author: luocan
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/findSeGatewayStateList")
    @ResponseBody
    public PageResult<SeGatewayStateVo> findSeGatewayStateList(SeGatewayStateQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<SeGatewayStateVo> rst = this.seGatewayStateService.findSeGatewayStateList(condition, page.getStart(),
                page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertSeGatewayState
     * @Description: TODO(添加网关数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/insertSeGatewayState")
    @ResponseBody
    public MessageResponse insertSeGatewayState(String jsons) throws Exception {

        List<SeGatewayState> list = JSON.parseArray(jsons, SeGatewayState.class);
        return this.seGatewayStateService.insertSeGatewayState(list, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateSeGatewayState
     * @Description: TODO(更新网关数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/updateSeGatewayState")
    @ResponseBody
    public MessageResponse updateSeGatewayState(String jsons) throws Exception {
        SeGatewayState obj = JSON.parseObject(jsons, SeGatewayState.class);
        return this.seGatewayStateService.updateSeGatewayState(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectSeGatewayStateByPrimaryKey
     * @Description: TODO(获取网关数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeGatewayState>
     * @author: luocan
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/selectSeGatewayStateByPrimaryKey")
    @ResponseBody
    public SingleResult<SeGatewayStateVo> selectSeGatewayStateByPrimaryKey(String id) throws Exception {
        return this.seGatewayStateService.selectSeGatewayStateByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteSeGatewayStateBySeGatewayStateId
     * @Description: TODO(删除网关数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/deleteSeGatewayStateBySeGatewayStateId")
    @ResponseBody
    public MessageResponse deleteSeGatewayStateBySeGatewayStateId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.seGatewayStateService.deleteSeGatewayStateBySeGatewayStateId(id, this.getCurUserProp());
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
     * @author: luocan
     * @version:2019-04-18
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
        return this.seGatewayStateService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:deleteSeGatewayStateBySeGatewayStateIds
     * @Description: TODO(批量删除网关数据)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version:2019-04-18
     */
    @RequestMapping(value = "/deleteSeGatewayStateBySeGatewayStateIds")
    @ResponseBody
    public MessageResponse deleteSeGatewayStateBySeGatewayStateIds(String jsons) throws Exception {

        List<String> list = JSON.parseArray(jsons, String.class);
        return this.seGatewayStateService.deleteSeGatewayStateBySeGatewayStateIds(list, this.getCurUserProp());
    }

    /**
     * 同步网关数据
     * @param parasm
     * @return String
     */
    @RequestMapping(value = "/syncData")
    @ResponseBody
    public MessageResponse syncData(String parasm){
        return  this.seGatewayStateService.syncData(this.getCurUserProp());
    }
}

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
import com.huacainfo.ace.glink.model.SeTimerWeek;
import com.huacainfo.ace.glink.service.SeTimerWeekService;
import com.huacainfo.ace.glink.vo.SeTimerWeekVo;
import com.huacainfo.ace.glink.vo.SeTimerWeekQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/seTimerWeek")
/**
 * @author: heshuang
 * @version: 2019-04-19
 * @Description: TODO(定时任务 - 星期数据)
 */
public class SeTimerWeekController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeTimerWeekService seTimerWeekService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(定时任务 - 星期数据分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <SeTimerWeekVo>
     * @author: heshuang
     * @version: 2019-04-19
     */
    @RequestMapping(value = "/findSeTimerWeekList")
    @ResponseBody
    public PageResult
            <SeTimerWeekVo> findSeTimerWeekList(SeTimerWeekQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult
                <SeTimerWeekVo> rst = this.seTimerWeekService.findSeTimerWeekList(condition, page.getStart(),
                page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertSeTimerWeek
     * @Description: TODO(添加定时任务 - 星期数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-19
     */
    @RequestMapping(value = "/insertSeTimerWeek")
    @ResponseBody
    public MessageResponse insertSeTimerWeek(String jsons) throws Exception {
        SeTimerWeek obj = JSON.parseObject(jsons, SeTimerWeek.class);
        return this.seTimerWeekService.insertSeTimerWeek(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateSeTimerWeek
     * @Description: TODO(更新定时任务 - 星期数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-19
     */
    @RequestMapping(value = "/updateSeTimerWeek")
    @ResponseBody
    public MessageResponse updateSeTimerWeek(String jsons) throws Exception {
        SeTimerWeek obj = JSON.parseObject(jsons, SeTimerWeek.class);
        return this.seTimerWeekService.updateSeTimerWeek(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectSeTimerWeekByPrimaryKey
     * @Description: TODO(获取定时任务 - 星期数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeTimerWeek>
     * @author: heshuang
     * @version: 2019-04-19
     */
    @RequestMapping(value = "/selectSeTimerWeekByPrimaryKey")
    @ResponseBody
    public SingleResult
            <SeTimerWeekVo> selectSeTimerWeekByPrimaryKey(String id) throws Exception {
        return this.seTimerWeekService.selectSeTimerWeekByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteSeTimerWeekBySeTimerWeekId
     * @Description: TODO(删除定时任务 - 星期数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-19
     */
    @RequestMapping(value = "/deleteSeTimerWeekBySeTimerWeekId")
    @ResponseBody
    public MessageResponse deleteSeTimerWeekBySeTimerWeekId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.seTimerWeekService.deleteSeTimerWeekBySeTimerWeekId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核定时任务 - 星期数据)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-19
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.seTimerWeekService.audit(id, rst, message, this.getCurUserProp());
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
     * @author: heshuang
     * @version:2019-04-19
     */
    @RequestMapping(value = "/importXls")
    @ResponseBody
    public MessageResponse importXls(@RequestParam MultipartFile[] file,
                                     String jsons) throws Exception {
        ExcelUtils utils = new ExcelUtils();
        List
                <Map
                        <String
                                , Object>> list = new ArrayList<>();
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
        return this.seTimerWeekService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(条件查询)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListResult
     * @author: heshuang
     * @version:2019-04-19
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public ListResult
            <Map
                    <String
                            , Object>> getList() throws Exception {
        return this.seTimerWeekService.getList(this.getParams());
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
     * @author: heshuang
     * @version:2019-04-19
     */
    @RequestMapping(value = "/getListByCondition")
    @ResponseBody
    public Map
            <String
                    , Object> getListByCondition() {
        return this.seTimerWeekService.getListByCondition(this.getParams());
    }


    /**
     * @throws
     * @Title:deleteSeTimerWeekBySeTimerWeekIds
     * @Description: TODO(批量删除定时任务 - 星期数据)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version:2019-04-19
     */
    @RequestMapping(value = "/deleteSeTimerWeekBySeTimerWeekIds")
    @ResponseBody
    public MessageResponse deleteSeTimerWeekBySeTimerWeekIds(String ids) throws Exception {
        String[] id = ids.split(",");
        return this.seTimerWeekService.deleteSeTimerWeekBySeTimerWeekIds(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version:2019-04-19
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.seTimerWeekService.updateStatus(id, status, this.getCurUserProp());
    }
}

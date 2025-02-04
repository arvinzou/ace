package com.huacainfo.ace.glink.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.glink.model.SeAlarmData;
import com.huacainfo.ace.glink.service.SeAlarmDataService;
import com.huacainfo.ace.glink.vo.SeAlarmDataQVo;
import com.huacainfo.ace.glink.vo.SeAlarmDataVo;
import com.huacainfo.ace.portal.vo.MongoFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seAlarmData")
/**
 * @author: luocan
 * @version: 2019-04-18
 * @Description: TODO(强电 - 报警数据)
 */
public class SeAlarmDataController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeAlarmDataService seAlarmDataService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(强电 - 报警数据分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <SeAlarmDataVo>
     * @author: luocan
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/findSeAlarmDataList")
    @ResponseBody
    public PageResult<SeAlarmDataVo> findSeAlarmDataList(SeAlarmDataQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<SeAlarmDataVo> rst = this.seAlarmDataService.findSeAlarmDataList(condition, page.getStart(),
                page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertSeAlarmData
     * @Description: TODO(添加强电 - 报警数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/insertSeAlarmData")
    @ResponseBody
    public MessageResponse insertSeAlarmData(String jsons) throws Exception {

        SeAlarmData vo = JSON.parseObject(jsons, SeAlarmData.class);
        return this.seAlarmDataService.insertSeAlarmData(vo, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateSeAlarmData
     * @Description: TODO(更新强电 - 报警数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/updateSeAlarmData")
    @ResponseBody
    public MessageResponse updateSeAlarmData(String jsons) throws Exception {
        SeAlarmData obj = JSON.parseObject(jsons, SeAlarmData.class);
        return this.seAlarmDataService.updateSeAlarmData(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectSeAlarmDataByPrimaryKey
     * @Description: TODO(获取强电 - 报警数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeAlarmData>
     * @author: luocan
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/selectSeAlarmDataByPrimaryKey")
    @ResponseBody
    public SingleResult<SeAlarmDataVo> selectSeAlarmDataByPrimaryKey(String id) throws Exception {
        return this.seAlarmDataService.selectSeAlarmDataByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteSeAlarmDataBySeAlarmDataId
     * @Description: TODO(删除强电 - 报警数据)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-18
     */
    @RequestMapping(value = "/deleteSeAlarmDataBySeAlarmDataId")
    @ResponseBody
    public MessageResponse deleteSeAlarmDataBySeAlarmDataId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.seAlarmDataService.deleteSeAlarmDataBySeAlarmDataId(id, this.getCurUserProp());
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
        return this.seAlarmDataService.importXls(list, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:deleteSeAlarmDataBySeAlarmDataIds
     * @Description: TODO(批量删除强电 - 报警数据)
     * @param: @param ids
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version:2019-04-18
     */
    @RequestMapping(value = "/deleteSeAlarmDataBySeAlarmDataIds")
    @ResponseBody
    public MessageResponse deleteSeAlarmDataBySeAlarmDataIds(String jsons) throws Exception {
        List<String> list = JSON.parseArray(jsons, String.class);
        return this.seAlarmDataService.deleteSeAlarmDataBySeAlarmDataIds(list, this.getCurUserProp());
    }


    /**
     * 强电报警信息推送
     *
     * @param json AreaNodeID    区域节点编号
     *             AlarmType     报警分类
     *             AlarmContent  报警类容描述
     *             AlarmDateTime 报警时间
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/www/accept")
    public MessageResponse accept(@RequestBody String json) throws Exception {

        Map<String, Object> params = JsonUtil.toMap(json);
        SeAlarmData data = new SeAlarmData();
        data.setAreaNodeID(String.valueOf(params.get("AreaNodeID")));
        data.setAlarmType(String.valueOf(params.get("AlarmType")));
        data.setAlarmContent(String.valueOf(params.get("AlarmContent")));
        data.setAlarmDateTime(String.valueOf(params.get("AlarmDateTime")));

        return this.seAlarmDataService.insertSeAlarmData(data, this.getCurUserProp());
    }
}

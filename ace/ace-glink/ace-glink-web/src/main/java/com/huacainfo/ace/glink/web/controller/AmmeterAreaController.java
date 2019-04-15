package com.huacainfo.ace.glink.web.controller;

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
import com.huacainfo.ace.glink.model.AmmeterArea;
import com.huacainfo.ace.glink.service.AmmeterAreaService;
import com.huacainfo.ace.glink.vo.AmmeterAreaVo;
import com.huacainfo.ace.glink.vo.AmmeterAreaQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/ammeterArea")
/**
 * @author: Arvin
 * @version: 2019-04-15
 * @Description: TODO(故障报警 - 短信 - 调度映射关系)
 */
public class AmmeterAreaController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AmmeterAreaService ammeterAreaService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(故障报警 - 短信 - 调度映射关系分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <AmmeterAreaVo>
     * @author: Arvin
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/findAmmeterAreaList")
    @ResponseBody
    public PageResult
            <AmmeterAreaVo> findAmmeterAreaList(AmmeterAreaQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult
                <AmmeterAreaVo> rst = this.ammeterAreaService.findAmmeterAreaList(condition, page.getStart(),
                page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertAmmeterArea
     * @Description: TODO(添加故障报警 - 短信 - 调度映射关系)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/insertAmmeterArea")
    @ResponseBody
    public MessageResponse insertAmmeterArea(String jsons) throws Exception {
        AmmeterArea obj = JSON.parseObject(jsons, AmmeterArea.class);
        return this.ammeterAreaService.insertAmmeterArea(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateAmmeterArea
     * @Description: TODO(更新故障报警 - 短信 - 调度映射关系)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/updateAmmeterArea")
    @ResponseBody
    public MessageResponse updateAmmeterArea(String jsons) throws Exception {
        AmmeterArea obj = JSON.parseObject(jsons, AmmeterArea.class);
        return this.ammeterAreaService.updateAmmeterArea(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectAmmeterAreaByPrimaryKey
     * @Description: TODO(获取故障报警 - 短信 - 调度映射关系)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<AmmeterArea>
     * @author: Arvin
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/selectAmmeterAreaByPrimaryKey")
    @ResponseBody
    public SingleResult
            <AmmeterAreaVo> selectAmmeterAreaByPrimaryKey(String id) throws Exception {
        return this.ammeterAreaService.selectAmmeterAreaByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteAmmeterAreaByAmmeterAreaId
     * @Description: TODO(删除故障报警 - 短信 - 调度映射关系)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/deleteAmmeterAreaByAmmeterAreaId")
    @ResponseBody
    public MessageResponse deleteAmmeterAreaByAmmeterAreaId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.ammeterAreaService.deleteAmmeterAreaByAmmeterAreaId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核故障报警 - 短信 - 调度映射关系)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-15
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.ammeterAreaService.audit(id, rst, message, this.getCurUserProp());
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
     * @version:2019-04-15
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
        return this.ammeterAreaService.importXls(list, this.getCurUserProp());
    }


}

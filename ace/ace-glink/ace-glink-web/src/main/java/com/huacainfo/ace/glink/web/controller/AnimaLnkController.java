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
import com.huacainfo.ace.glink.model.AnimaLnk;
import com.huacainfo.ace.glink.service.AnimaLnkService;
import com.huacainfo.ace.glink.vo.AnimaLnkVo;
import com.huacainfo.ace.glink.vo.AnimaLnkQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/animaLnk")
/**
 * @author: luocan
 * @version: 2019-04-10
 * @Description: TODO(节目上传)
 */
public class AnimaLnkController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AnimaLnkService animaLnkService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(节目上传分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <AnimaLnkVo>
     * @author: luocan
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/findAnimaLnkList")
    @ResponseBody
    public PageResult
            <AnimaLnkVo>
    findAnimaLnkList(AnimaLnkQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult
                <AnimaLnkVo> rst =
                this.animaLnkService.findAnimaLnkList(condition, page.getStart(),
                        page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertAnimaLnk
     * @Description: TODO(添加节目上传)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/insertAnimaLnk")
    @ResponseBody
    public MessageResponse insertAnimaLnk(String jsons) throws Exception {
        List<AnimaLnk> list = JSON.parseArray(jsons, AnimaLnk.class);
        return this.animaLnkService.insertAnimaLnk(list, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateAnimaLnk
     * @Description: TODO(更新节目上传)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/updateAnimaLnk")
    @ResponseBody
    public MessageResponse updateAnimaLnk(String jsons) throws Exception {
        AnimaLnk obj = JSON.parseObject(jsons, AnimaLnk.class);
        return this.animaLnkService.updateAnimaLnk(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectAnimaLnkByPrimaryKey
     * @Description: TODO(获取节目上传)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<AnimaLnk>
     * @author: luocan
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/selectAnimaLnkByPrimaryKey")
    @ResponseBody
    public SingleResult
            <AnimaLnkVo> selectAnimaLnkByPrimaryKey(String id) throws Exception {
        return this.animaLnkService.selectAnimaLnkByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteAnimaLnkByAnimaLnkId
     * @Description: TODO(删除节目上传)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/deleteAnimaLnkByAnimaLnkId")
    @ResponseBody
    public MessageResponse deleteAnimaLnkByAnimaLnkId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.animaLnkService.deleteAnimaLnkByAnimaLnkId(id, this.getCurUserProp());
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
     * @version:2019-04-10
     */
    @RequestMapping(value = "/importXls")
    @ResponseBody
    public MessageResponse importXls(@RequestParam MultipartFile[] file, String jsons) throws Exception {
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
        return this.animaLnkService.importXls(list, this.getCurUserProp());
    }


}

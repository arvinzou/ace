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
import com.huacainfo.ace.glink.model.AnimaRes;
import com.huacainfo.ace.glink.service.AnimaResService;
import com.huacainfo.ace.glink.vo.AnimaResVo;
import com.huacainfo.ace.glink.vo.AnimaResQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/animaRes")
/**
 * @author: luocan
 * @version: 2019-04-10
 * @Description: TODO(节目管理)
 */
public class AnimaResController extends GLinkBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AnimaResService animaResService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(节目管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <AnimaResVo>
     * @author: luocan
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/findAnimaResList")
    @ResponseBody
    public PageResult<AnimaResVo> findAnimaResList(AnimaResQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<AnimaResVo> rst = this.animaResService.findAnimaResList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertAnimaRes
     * @Description: TODO(添加节目管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/insertAnimaRes")
    @ResponseBody
    public MessageResponse insertAnimaRes(String jsons) throws Exception {
        AnimaRes obj = JSON.parseObject(jsons, AnimaRes.class);
        return this.animaResService.insertAnimaRes(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateAnimaRes
     * @Description: TODO(更新节目管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/updateAnimaRes")
    @ResponseBody
    public MessageResponse updateAnimaRes(String jsons) throws Exception {
        AnimaRes obj = JSON.parseObject(jsons, AnimaRes.class);
        return this.animaResService.updateAnimaRes(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectAnimaResByPrimaryKey
     * @Description: TODO(获取节目管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<AnimaRes>
     * @author: luocan
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/selectAnimaResByPrimaryKey")
    @ResponseBody
    public SingleResult
            <AnimaResVo> selectAnimaResByPrimaryKey(String id) throws Exception {
        return this.animaResService.selectAnimaResByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteAnimaResByAnimaResId
     * @Description: TODO(删除节目管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/deleteAnimaResByAnimaResId")
    @ResponseBody
    public MessageResponse deleteAnimaResByAnimaResId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.animaResService.deleteAnimaResByAnimaResId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核节目管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-10
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.animaResService.audit(id, rst, message, this.getCurUserProp());
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
        return this.animaResService.importXls(list, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version:2019-04-10
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public MessageResponse updateStatus(String id, String status) throws Exception {
        return this.animaResService.updateStatus(id, status, this.getCurUserProp());
    }
}

package com.huacainfo.ace.policeschool.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.policeschool.model.CardData;
import com.huacainfo.ace.policeschool.service.CardDataService;
import com.huacainfo.ace.policeschool.vo.CardDataQVo;
import com.huacainfo.ace.policeschool.vo.CardDataVo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cardData")
/**
 * @author: Arvin
 * @version: 2019-01-08
 * @Description: TODO(一卡通绑定)
 */
public class CardDataController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CardDataService cardDataService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(一卡通绑定分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <CardDataVo>
     * @author: Arvin
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/findCardDataList")
    @ResponseBody
    public PageResult<CardDataVo> findCardDataList(CardDataQVo condition,
                                                   PageParamNoChangeSord page) throws Exception {

        PageResult<CardDataVo> rst =
                cardDataService.findCardDataList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertCardData
     * @Description: TODO(添加一卡通绑定)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/insertCardData")
    @ResponseBody
    public MessageResponse insertCardData(String jsons) throws Exception {
        CardData obj = JSON.parseObject(jsons, CardData.class);
        return this.cardDataService.insertCardData(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateCardData
     * @Description: TODO(更新一卡通绑定)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/updateCardData")
    @ResponseBody
    public MessageResponse updateCardData(String jsons) throws Exception {
        CardData obj = JSON.parseObject(jsons, CardData.class);
        return this.cardDataService.updateCardData(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectCardDataByPrimaryKey
     * @Description: TODO(获取一卡通绑定)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CardData>
     * @author: Arvin
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/selectCardDataByPrimaryKey")
    @ResponseBody
    public SingleResult<CardDataVo> selectCardDataByPrimaryKey(String id) throws Exception {
        return this.cardDataService.selectCardDataByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteCardDataByCardDataId
     * @Description: TODO(删除一卡通绑定)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/deleteCardDataByCardDataId")
    @ResponseBody
    public MessageResponse deleteCardDataByCardDataId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.cardDataService.deleteCardDataByCardDataId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核一卡通绑定)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.cardDataService.audit(id, rst, message, this.getCurUserProp());
    }


    /**
     * 加载系统所有用户列表，切条件模糊查询
     *
     * @param id userId
     * @param q  索引KEY
     * @return MessageResponse
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/findUserList")
    public Map<String, Object> findUserList(String id, String q) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("q", id);
        if (!CommonUtils.isBlank(q)) {
            params.put("q", q);
        }

        return cardDataService.findUserList(params);
    }


    /**
     * 批量导入
     *
     * @param file 上载文件
     * @return MessageResponse
     * @throws Exception
     */
    @RequestMapping(value = "/importXls")
    @ResponseBody
    public MessageResponse importXls(@RequestParam MultipartFile[] file) throws Exception {

        List<Map<String, Object>> list = new ArrayList<>();
        MongoFile[] files = new MongoFile[file.length];
        ExcelUtils utils = new ExcelUtils();
        int i = 0;
        for (MultipartFile o : file) {
            MongoFile obj = new MongoFile();
            obj.setLength(o.getSize());
            obj.setInputStream(o.getInputStream());
            obj.setFilename(o.getOriginalFilename());
            files[i] = obj;
            String ext = obj.getFilename().toLowerCase().substring(obj.getFilename().toLowerCase().lastIndexOf("."));
            this.logger.info(ext);
            if (ext.equals(".xls")) {
                list = utils.readExcelByJXL(obj.getInputStream(), 2);
            }
            if (ext.equals(".xlsx")) {
                list = utils.readExcelByPOI(obj.getInputStream(), 2);
            }
            i++;
        }

        return cardDataService.importXls(list, this.getCurUserProp());
    }
}

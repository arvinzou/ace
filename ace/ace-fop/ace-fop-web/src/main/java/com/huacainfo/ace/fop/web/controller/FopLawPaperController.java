package com.huacainfo.ace.fop.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopLawPaper;
import com.huacainfo.ace.fop.service.FopLawPaperService;
import com.huacainfo.ace.fop.vo.FopLawPaperQVo;
import com.huacainfo.ace.fop.vo.FopLawPaperVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fopLawPaper")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: 法律文书
 */
public class FopLawPaperController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopLawPaperService fopLawPaperService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(法律文书分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<FopLawPaperVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/findFopLawPaperList")
    @ResponseBody
    public PageResult<FopLawPaperVo> findFopLawPaperList(FopLawPaperQVo condition,
                                                         PageParamNoChangeSord page) throws Exception {
        PageResult<FopLawPaperVo> rst = this.fopLawPaperService
                .findFopLawPaperList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertFopLawPaper
     * @Description: TODO(添加法律文书)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/insertFopLawPaper")
    @ResponseBody
    public MessageResponse insertFopLawPaper(String jsons) throws Exception {
        FopLawPaper obj = JSON.parseObject(jsons, FopLawPaper.class);
        return this.fopLawPaperService.insertFopLawPaper(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateFopLawPaper
     * @Description: TODO(更新法律文书)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/updateFopLawPaper")
    @ResponseBody
    public MessageResponse updateFopLawPaper(String jsons) throws Exception {
        FopLawPaper obj = JSON.parseObject(jsons, FopLawPaper.class);
        return this.fopLawPaperService
                .updateFopLawPaper(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFopLawPaperByPrimaryKey
     * @Description: TODO(获取法律文书)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopLawPaper>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/selectFopLawPaperByPrimaryKey")
    @ResponseBody
    public SingleResult<FopLawPaperVo> selectFopLawPaperByPrimaryKey(String id)
            throws Exception {
        return this.fopLawPaperService.selectFopLawPaperByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteFopLawPaperByFopLawPaperId
     * @Description: TODO(删除法律文书)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/deleteFopLawPaperByFopLawPaperId")
    @ResponseBody
    public MessageResponse deleteFopLawPaperByFopLawPaperId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.fopLawPaperService.deleteFopLawPaperByFopLawPaperId(id,
                this.getCurUserProp());
    }
}

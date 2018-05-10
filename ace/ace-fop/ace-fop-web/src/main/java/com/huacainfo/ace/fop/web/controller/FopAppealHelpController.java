package com.huacainfo.ace.fop.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.fop.model.FopAppealHelp;
import com.huacainfo.ace.fop.service.FopAppealHelpService;
import com.huacainfo.ace.fop.vo.FopAppealHelpQVo;
import com.huacainfo.ace.fop.vo.FopAppealHelpVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fopAppealHelp")
/**
 * @author: Arvin
 * @version: 2018-05-10
 * @Description: (诉求服务)
 */
public class FopAppealHelpController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopAppealHelpService fopAppealHelpService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: (诉求服务分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <FopAppealHelpVo>
     * @author: Arvin
     * @version: 2018-05-10
     */
    @RequestMapping(value = "/findFopAppealHelpList")
    @ResponseBody
    public PageResult<FopAppealHelpVo> findFopAppealHelpList(FopAppealHelpQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<FopAppealHelpVo> rst = this.fopAppealHelpService
                .findFopAppealHelpList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertFopAppealHelp
     * @Description: (添加诉求服务)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-10
     */
    @RequestMapping(value = "/insertFopAppealHelp")
    @ResponseBody
    public MessageResponse insertFopAppealHelp(String jsons) throws Exception {
        FopAppealHelp obj = JSON.parseObject(jsons, FopAppealHelp.class);
        return this.fopAppealHelpService.insertFopAppealHelp(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateFopAppealHelp
     * @Description: (更新诉求服务)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-10
     */
    @RequestMapping(value = "/updateFopAppealHelp")
    @ResponseBody
    public MessageResponse updateFopAppealHelp(String jsons) throws Exception {
        FopAppealHelp obj = JSON.parseObject(jsons, FopAppealHelp.class);
        return this.fopAppealHelpService.updateFopAppealHelp(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFopAppealHelpByPrimaryKey
     * @Description: (获取诉求服务)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopAppealHelp>
     * @author: Arvin
     * @version: 2018-05-10
     */
    @RequestMapping(value = "/selectFopAppealHelpByPrimaryKey")
    @ResponseBody
    public SingleResult<FopAppealHelpVo> selectFopAppealHelpByPrimaryKey(String id) throws Exception {
        return this.fopAppealHelpService.selectFopAppealHelpByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteFopAppealHelpByFopAppealHelpId
     * @Description: (删除诉求服务)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-10
     */
    @RequestMapping(value = "/deleteFopAppealHelpByFopAppealHelpId")
    @ResponseBody
    public MessageResponse deleteFopAppealHelpByFopAppealHelpId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.fopAppealHelpService.deleteFopAppealHelpByFopAppealHelpId(id, this.getCurUserProp());
    }

    /**
     * 功能描述: 确认回复
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/10 18:30
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id) throws Exception {
        if (CommonUtils.isBlank(id)) {
            return new MessageResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return fopAppealHelpService.audit(id, getCurUserProp());
    }
}

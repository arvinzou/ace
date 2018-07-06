package com.huacainfo.ace.fop.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.fop.model.FopGeHelp;
import com.huacainfo.ace.fop.service.FopGeHelpService;
import com.huacainfo.ace.fop.vo.FopGeHelpQVo;
import com.huacainfo.ace.fop.vo.FopGeHelpVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fopGeHelp")
/**
 * @author: Arvin
 * @version: 2018-05-09
 * @Description: TODO(政企服务)
 */
public class FopGeHelpController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    @Autowired
    private FopGeHelpService fopGeHelpService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(政企服务分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <FopGeHelpVo>
     * @author: Arvin
     * @version: 2018-05-09
     */
    @RequestMapping(value = "/findFopGeHelpList")
    @ResponseBody
    public PageResult
            <FopGeHelpVo> findFopGeHelpList(FopGeHelpQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<FopGeHelpVo> rst = this.fopGeHelpService.findFopGeHelpListVo(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertFopGeHelp
     * @Description: TODO(添加政企服务)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-09
     */
    @RequestMapping(value = "/insertFopGeHelp")
    @ResponseBody
    public MessageResponse insertFopGeHelp(String jsons) throws Exception {
        FopGeHelp obj = JSON.parseObject(jsons, FopGeHelp.class);
        return this.fopGeHelpService.insertFopGeHelp(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateFopGeHelp
     * @Description: TODO(更新政企服务)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-09
     */
    @RequestMapping(value = "/updateFopGeHelp")
    @ResponseBody
    public MessageResponse updateFopGeHelp(String jsons) throws Exception {
        FopGeHelp obj = JSON.parseObject(jsons, FopGeHelp.class);
        JSONObject json = JSONObject.parseObject(jsons);
        String addProcess = (String) json.get("addProcess");
        if (null != addProcess && (!"".equals(addProcess.trim()))) {
            obj.setProcessDetail(null);
            FopGeHelp fg = new FopGeHelp();
            fg.setParentId(obj.getId());
            fg.setProcessDetail(addProcess);
            MessageResponse rs = fopGeHelpService.insertProcess(fg, this.getCurUserProp());
            if (ResultCode.FAIL == rs.getStatus()) {
                return rs;
            }
        }
        return this.fopGeHelpService.updateFopGeHelp(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFopGeHelpByPrimaryKey
     * @Description: TODO(获取政企服务)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopGeHelp>
     * @author: Arvin
     * @version: 2018-05-09
     */
    @RequestMapping(value = "/selectFopGeHelpByPrimaryKey")
    @ResponseBody
    public SingleResult<FopGeHelpVo> selectFopGeHelpByPrimaryKey(String id) throws Exception {


        // return this.fopGeHelpService.selectFopGeHelpByPrimaryKey(id);
        return this.fopGeHelpService.selectFopGeHelpByPrimaryKeyVo(id);
    }


    /**
     * @throws
     * @Title:deleteFopGeHelpByFopGeHelpId
     * @Description: TODO(删除政企服务)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-09
     */
    @RequestMapping(value = "/deleteFopGeHelpByFopGeHelpId")
    @ResponseBody
    public MessageResponse deleteFopGeHelpByFopGeHelpId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.fopGeHelpService.deleteFopGeHelpByFopGeHelpId(id, this.getCurUserProp());
    }

    /**
     * 功能描述:  审核发布
     *
     * @param: id fop_ge_help.id
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/8 18:19
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String auditResult, String auditOpinion) throws Exception {
        if (CommonUtils.isEmpty(id)) {
            return new MessageResponse(ResultCode.FAIL, "缺少必备参数");
        }

        return fopGeHelpService.audit(id, auditResult, auditOpinion, getCurUserProp());
    }
}

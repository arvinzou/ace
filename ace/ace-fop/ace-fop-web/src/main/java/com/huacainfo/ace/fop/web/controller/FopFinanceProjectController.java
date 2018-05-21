package com.huacainfo.ace.fop.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.fop.model.FopFinanceProject;
import com.huacainfo.ace.fop.service.FopFinanceProjectService;
import com.huacainfo.ace.fop.vo.FopFinanceProjectQVo;
import com.huacainfo.ace.fop.vo.FopFinanceProjectVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fopFinanceProject")
/**
 * @author: Arvin
 * @version: 2018-05-05
 * @Description: TODO(流程记录)
 */
public class FopFinanceProjectController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopFinanceProjectService fopFinanceProjectService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(流程记录分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <FopFinanceProjectVo>
     * @author: Arvin
     * @version: 2018-05-05
     */
    @RequestMapping(value = "/findFopFinanceProjectList")
    @ResponseBody
    public PageResult
            <FopFinanceProjectVo> findFopFinanceProjectList(FopFinanceProjectQVo condition,
                                                            PageParamNoChangeSord page) throws Exception {
        PageResult
                <FopFinanceProjectVo> rst = this.fopFinanceProjectService
                .findFopFinanceProjectList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertFopFinanceProject
     * @Description: TODO(添加流程记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-05
     */
    @RequestMapping(value = "/insertFopFinanceProject")
    @ResponseBody
    public MessageResponse insertFopFinanceProject(String jsons) throws Exception {
        FopFinanceProject obj = JSON.parseObject(jsons, FopFinanceProject.class);
        return this.fopFinanceProjectService.insertFopFinanceProject(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateFopFinanceProject
     * @Description: TODO(更新流程记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-05
     */
    @RequestMapping(value = "/updateFopFinanceProject")
    @ResponseBody
    public MessageResponse updateFopFinanceProject(String jsons) throws Exception {
        FopFinanceProject obj = JSON.parseObject(jsons, FopFinanceProject.class);
        return this.fopFinanceProjectService
                .updateFopFinanceProject(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFopFinanceProjectByPrimaryKey
     * @Description: TODO(获取流程记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopFinanceProject>
     * @author: Arvin
     * @version: 2018-05-05
     */
    @RequestMapping(value = "/selectFopFinanceProjectByPrimaryKey")
    @ResponseBody
    public SingleResult
            <FopFinanceProjectVo> selectFopFinanceProjectByPrimaryKey(String id)
            throws Exception {
        return this.fopFinanceProjectService.selectFopFinanceProjectByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteFopFinanceProjectByFopFinanceProjectId
     * @Description: TODO(删除流程记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-05
     */
    @RequestMapping(value = "/deleteFopFinanceProjectByFopFinanceProjectId")
    @ResponseBody
    public MessageResponse deleteFopFinanceProjectByFopFinanceProjectId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.fopFinanceProjectService.deleteFopFinanceProjectByFopFinanceProjectId(id,
                this.getCurUserProp());
    }


    /**
     * 功能描述:  审核
     *
     * @param: id Fop_Finance_Project.id
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

        return fopFinanceProjectService.audit(id, auditResult, auditOpinion, getCurUserProp());
    }
}

package com.huacainfo.ace.fop.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.fop.model.FopProject;
import com.huacainfo.ace.fop.service.FopProjectService;
import com.huacainfo.ace.fop.vo.FopProjectQVo;
import com.huacainfo.ace.fop.vo.FopProjectVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fopProject")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: 合作项目
 */
public class FopProjectController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopProjectService fopProjectService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(合作项目分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<FopProjectVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/findFopProjectList")
    @ResponseBody
    public PageResult<FopProjectVo> findFopProjectList(FopProjectQVo condition,
                                                       PageParamNoChangeSord page) throws Exception {
        PageResult<FopProjectVo> rst = this.fopProjectService
                .findFopProjectList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertFopProject
     * @Description: TODO(添加合作项目)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/insertFopProject")
    @ResponseBody
    public MessageResponse insertFopProject(String jsons) throws Exception {
        FopProject obj = JSON.parseObject(jsons, FopProject.class);
        return this.fopProjectService
                .insertFopProject(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateFopProject
     * @Description: TODO(更新合作项目)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/updateFopProject")
    @ResponseBody
    public MessageResponse updateFopProject(String jsons) throws Exception {
        FopProject obj = JSON.parseObject(jsons, FopProject.class);
        return this.fopProjectService
                .updateFopProject(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFopProjectByPrimaryKey
     * @Description: TODO(获取合作项目)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopProject>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/selectFopProjectByPrimaryKey")
    @ResponseBody
    public SingleResult<FopProjectVo> selectFopProjectByPrimaryKey(String id)
            throws Exception {
        return this.fopProjectService.selectFopProjectByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteFopProjectByFopProjectId
     * @Description: TODO(删除合作项目)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/deleteFopProjectByFopProjectId")
    @ResponseBody
    public MessageResponse deleteFopProjectByFopProjectId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.fopProjectService.deleteFopProjectByFopProjectId(id,
                this.getCurUserProp());
    }


    /**
     * 功能描述: 审核发布项目
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/10 15:45
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id) throws Exception {
        if (CommonUtils.isBlank(id)) {
            return new MessageResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return fopProjectService.audit(id, getCurUserProp());
    }
}

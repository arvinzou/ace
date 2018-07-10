package com.huacainfo.ace.cu.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.cu.model.CuProject;
import com.huacainfo.ace.cu.service.CuProjectService;
import com.huacainfo.ace.cu.vo.CuProjectQVo;
import com.huacainfo.ace.cu.vo.CuProjectVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cuProject")
/**
 * @author: Arvin
 * @version: 2018-06-13
 * @Description: TODO(慈善项目)
 */
public class CuProjectController extends CuBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CuProjectService cuProjectService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(慈善项目分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <CuProjectVo>
     * @author: Arvin
     * @version: 2018-06-13
     */
    @RequestMapping(value = "/findCuProjectList")
    @ResponseBody
    public PageResult<CuProjectVo> findCuProjectList(CuProjectQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<CuProjectVo> rst = this.cuProjectService
                .findCuProjectList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertCuProject
     * @Description: TODO(添加慈善项目)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-13
     */
    @RequestMapping(value = "/insertCuProject")
    @ResponseBody
    public MessageResponse insertCuProject(String jsons) throws Exception {
        CuProject obj = JSON.parseObject(jsons, CuProject.class);
        return this.cuProjectService.insertCuProjectByType(obj, obj.getType(), this.getCurUserProp());
        //insertCuProject(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateCuProject
     * @Description: TODO(更新慈善项目)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-13
     */
    @RequestMapping(value = "/updateCuProject")
    @ResponseBody
    public MessageResponse updateCuProject(String jsons) throws Exception {
        CuProject obj = JSON.parseObject(jsons, CuProject.class);
        return this.cuProjectService.updateCuProject(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectCuProjectByPrimaryKey
     * @Description: TODO(获取慈善项目)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CuProject>
     * @author: Arvin
     * @version: 2018-06-13
     */
    @RequestMapping(value = "/selectCuProjectByPrimaryKey")
    @ResponseBody
    public SingleResult<CuProjectVo> selectCuProjectByPrimaryKey(String id) throws Exception {
        return this.cuProjectService.selectCuProjectByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteCuProjectByCuProjectId
     * @Description: TODO(删除慈善项目)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-13
     */
    @RequestMapping(value = "/deleteCuProjectByCuProjectId")
    @ResponseBody
    public MessageResponse deleteCuProjectByCuProjectId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.cuProjectService.deleteCuProjectByCuProjectId(id, this.getCurUserProp());
    }


    /**
     * 功能描述:  慈善项目审核审核
     *
     * @param: id cu_project.id
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

        return cuProjectService.audit(id, auditResult, auditOpinion, getCurUserProp());
    }

    /**
     * 功能描述:  确认上线
     *
     * @param: id cu_project.id
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/8 18:19
     */
    @RequestMapping(value = "/setup")
    @ResponseBody
    public MessageResponse setup(String id, String reason) throws Exception {
        if (CommonUtils.isEmpty(id)) {
            return new MessageResponse(ResultCode.FAIL, "缺少必备参数");
        }

        return cuProjectService.setup(id, getCurUserProp());
    }

    /**
     * 功能描述:  强制下线
     *
     * @param: id cu_project.id
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/8 18:19
     */
    @RequestMapping(value = "/shutDown")
    @ResponseBody
    public MessageResponse shutDown(String id, String reason) throws Exception {
        if (CommonUtils.isEmpty(id)) {
            return new MessageResponse(ResultCode.FAIL, "缺少必备参数");
        }

        return cuProjectService.shutDown(id, reason, getCurUserProp());
    }
}

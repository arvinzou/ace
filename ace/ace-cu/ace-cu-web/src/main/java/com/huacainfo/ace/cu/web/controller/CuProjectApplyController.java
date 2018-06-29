package com.huacainfo.ace.cu.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.cu.model.CuProjectApply;
import com.huacainfo.ace.cu.model.CuProjectApplyRes;
import com.huacainfo.ace.cu.service.CuProjectApplyService;
import com.huacainfo.ace.cu.vo.CuProjectApplyQVo;
import com.huacainfo.ace.cu.vo.CuProjectApplyVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/cuProjectApply")
/**
 * @author: Arvin
 * @version: 2018-06-14
 * @Description: TODO(救急难申请表)
 */
public class CuProjectApplyController extends CuBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CuProjectApplyService cuProjectApplyService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(救急难申请表分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <CuProjectApplyVo>
     * @author: Arvin
     * @version: 2018-06-14
     */
    @RequestMapping(value = "/findCuProjectApplyList")
    @ResponseBody
    public PageResult<CuProjectApplyVo> findCuProjectApplyList(CuProjectApplyQVo condition,
                                                               PageParamNoChangeSord page) throws Exception {
        PageResult<CuProjectApplyVo> rst = this.cuProjectApplyService
                .findCuProjectApplyList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertCuProjectApply
     * @Description: TODO(添加救急难申请表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    @RequestMapping(value = "/insertCuProjectApply")
    @ResponseBody
    public MessageResponse insertCuProjectApply(String jsons) throws Exception {
        CuProjectApply obj = JSON.parseObject(jsons, CuProjectApply.class);
        return this.cuProjectApplyService.insertCuProjectApply(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateCuProjectApply
     * @Description: TODO(更新救急难申请表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    @RequestMapping(value = "/updateCuProjectApply")
    @ResponseBody
    public MessageResponse updateCuProjectApply(String jsons) throws Exception {
        CuProjectApply obj = JSON.parseObject(jsons, CuProjectApply.class);
        return this.cuProjectApplyService.updateCuProjectApply(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectCuProjectApplyByPrimaryKey
     * @Description: TODO(获取救急难申请表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CuProjectApply>
     * @author: Arvin
     * @version: 2018-06-14
     */
    @RequestMapping(value = "/selectCuProjectApplyByPrimaryKey")
    @ResponseBody
    public SingleResult<CuProjectApplyVo> selectCuProjectApplyByPrimaryKey(String id) throws Exception {
        return this.cuProjectApplyService.selectCuProjectApplyByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteCuProjectApplyByCuProjectApplyId
     * @Description: TODO(删除救急难申请表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    @RequestMapping(value = "/deleteCuProjectApplyByCuProjectApplyId")
    @ResponseBody
    public MessageResponse deleteCuProjectApplyByCuProjectApplyId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.cuProjectApplyService.deleteCuProjectApplyByCuProjectApplyId(id, this.getCurUserProp());
    }


    /**
     * 功能描述:  "救急难"审核
     *
     * @param: id cu_project_apply.id
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

        return cuProjectApplyService.audit(id, auditResult, auditOpinion, getCurUserProp());
    }


    /**
     * 功能描述: 查询救助资料列表
     *
     * @param: resTypes ","分割多种类型 0-身份证正面 1-身份证反面 2-其他证明图片
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/6/27 17:16
     */
    @RequestMapping(value = "/findResList")
    @ResponseBody
    public ResultResponse findResList(String applyId, String resTypes) {
        if (CommonUtils.isEmpty(applyId) || CommonUtils.isEmpty(resTypes)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必备参数");
        }

        List<CuProjectApplyRes> resList = cuProjectApplyService.findResList(applyId, resTypes, getCurUserProp());
        return new ResultResponse(ResultCode.SUCCESS, "获取成功", resList);
    }
}

package com.huacainfo.ace.cu.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.cu.service.CuDonateOrderService;
import com.huacainfo.ace.cu.service.CuProjectApplyService;
import com.huacainfo.ace.cu.service.CuProjectService;
import com.huacainfo.ace.cu.vo.CuDonateOrderVo;
import com.huacainfo.ace.cu.vo.CuProjectApplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by HuaCai008 on 2018/6/14.
 */
@Controller
@RequestMapping("/www/project")
public class WWWProjectController extends CuBaseController {
    @Autowired
    private CuProjectService cuProjectService;

    @Autowired
    private CuProjectApplyService cuProjectApplyService;
    @Autowired
    private CuDonateOrderService cuDonateOrderService;

    /**
     * 查询项目列表
     *
     * @param type    项目类型 0-普通项目 1-专项项目 2-个人项目 3-支出项目
     * @param start   分页开始位置  --  必选
     * @param limit   页数  --  必选
     * @param orderBy 排序条件   --  可选，默认时间倒叙
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findList")
    @ResponseBody
    public ResultResponse findList(String type, int start, int limit, String orderBy) throws Exception {
        if (StringUtil.isEmpty(type)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return cuProjectService.findList(type, start, limit, orderBy);
    }

    /**
     * 查询项目详情
     *
     * @param projectId 慈善项目ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findDetail")
    @ResponseBody
    public ResultResponse findDetail(String projectId) throws Exception {
        if (StringUtil.isEmpty(projectId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return cuProjectService.findDetail(projectId);
    }


    /**
     * 查询项目 - 使用记录列表
     *
     * @param projectId 慈善项目ID
     * @param start     分页开始位置  --  必选
     * @param limit     页数  --  必选
     * @param orderBy   排序条件   --  可选，默认时间倒叙
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findUsedRecordList")
    @ResponseBody
    public ResultResponse findUsedRecordList(String projectId, int start, int limit, String orderBy) throws Exception {
        if (StringUtil.isEmpty(projectId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return cuProjectService.findUsedRecordList(projectId, start, limit, orderBy);
    }

    /**
     * 查询项目 - 捐赠列表
     *
     * @param projectId 慈善项目ID
     * @param start     分页开始位置  --  必选
     * @param limit     页数  --  必选
     * @param orderBy   排序条件   --  可选，默认时间倒叙
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findDonateList")
    @ResponseBody
    public ResultResponse findDonateList(String projectId, int start, int limit, String orderBy) throws Exception {
        if (StringUtil.isEmpty(projectId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return cuProjectService.findDonateList(projectId, start, limit, orderBy);
    }


    /**
     * 救急难 - 立项申请
     *
     * @param json 参考 CuProjectApplyVo.java对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/applyProject")
    @ResponseBody
    public ResultResponse applyProject(String json) throws Exception {
        //公众号用户信息
        Userinfo userinfo = getCurUserinfo();
        if (null == userinfo) {
            return new ResultResponse(ResultCode.FAIL, "微信授权失败");
        }
        if (StringUtil.isEmpty(json)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        CuProjectApplyVo vo = JsonUtil.toObject(json, CuProjectApplyVo.class);
        vo.setApplyOpenId(userinfo.getOpenid());

        return cuProjectApplyService.applyProject(vo);
    }

    /**
     * 创建捐款订单
     *
     * @param json 参考 CuDonateOrderVo.java对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/createDonateOrder")
    @ResponseBody
    public ResultResponse createDonateOrder(String json) throws Exception {
        //公众号用户信息
        Userinfo userinfo = getCurUserinfo();
        if (null == userinfo) {
            return new ResultResponse(ResultCode.FAIL, "微信授权失败");
        }
        if (StringUtil.isEmpty(json)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        CuDonateOrderVo data = JsonUtil.toObject(json, CuDonateOrderVo.class);
        data.setOpenId(userinfo.getOpenid());

        return cuDonateOrderService.createDonateOrder(data);
    }
}

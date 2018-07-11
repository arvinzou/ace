package com.huacainfo.ace.fundtown.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.fundtown.model.ProcessNode;
import com.huacainfo.ace.fundtown.model.ProcessNodeRes;
import com.huacainfo.ace.fundtown.service.ProcessNodeService;
import com.huacainfo.ace.fundtown.service.VipDepartmentService;
import com.huacainfo.ace.fundtown.vo.VipDepartmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/7/6 20:14
 * @Description:
 */
@RestController
@RequestMapping("/www/process")
public class WWWProcessController extends BisBaseController {

    @Autowired
    private ProcessNodeService processNodeService;
    @Autowired
    private VipDepartmentService vipDepartmentService;

    /**
     * 获取入驻流程节点
     *
     * @return
     */
    @RequestMapping("/getNodeList")
    public ResultResponse getNodeList() {

        List<ProcessNode> nodeList = processNodeService.findNodeList();

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", nodeList);
    }

    /**
     * 获取节点文件资源
     *
     * @param nodeId 节点ID  fundtown.process_node.id
     * @return
     */
    @RequestMapping("/getNodeResList")
    public ResultResponse getNodeResList(String nodeId) {
        if (StringUtil.isEmpty(nodeId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        Map<String, List<ProcessNodeRes>> nodeListMap = processNodeService.getNodeResList(nodeId);

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", nodeListMap);
    }

    /**
     * 获取我的vip信息
     *
     * @param openId 调试时使用，调接口时可以不用理会此参数
     * @return
     */
    @RequestMapping("/getMyVipInfo")
    public ResultResponse getMyVipInfo(String openId) throws Exception {
        //公众号用户信息
        Userinfo userinfo = getCurUserinfo();
        if (null == userinfo && StringUtil.isEmpty(openId)) {
            return new ResultResponse(ResultCode.FAIL, "微信授权失败");
        }
        openId = StringUtil.isEmpty(openId) ? userinfo.getOpenid() : openId;

        return vipDepartmentService.getMyVipInfo(openId);
    }

    /**
     * 入驻申请
     *
     * @param json   入驻申请数据
     *               Demo:{"contactEmail":"30123@qq.com","contactMobile":"18570629027","departmentName":"华彩伟业"}
     * @param openId 调试时使用，调接口时可以不用理会此参数
     * @param code   手机验证码  -- 必传
     * @return
     */
    @RequestMapping("/vipApply")
    public ResultResponse vipApply(String code, String openId, String json) throws Exception {
        if (!StringUtil.areNotEmpty(code, json)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        //公众号用户信息
        Userinfo userinfo = getCurUserinfo();
        if (null == userinfo && StringUtil.isEmpty(openId)) {
            return new ResultResponse(ResultCode.FAIL, "微信授权失败");
        }
        openId = StringUtil.isEmpty(openId) ? userinfo.getOpenid() : openId;
        VipDepartmentVo vipVo = JsonUtil.toObject(json, VipDepartmentVo.class);
        //手机验证码有效性验证
        String randCode =
                (String) getRequest().getSession().getAttribute("j_captcha_cmcc_" + vipVo.getContactMobile());
        if (!code.equals(randCode)) {
            return new ResultResponse(ResultCode.FAIL, "验证码输入有误");
        }

        return vipDepartmentService.vipApply(openId, vipVo);
    }


    /**
     * 获取我的视频信息
     *
     * @param deptId
     * @return
     */
    @RequestMapping("/getMyVideo")
    public ResultResponse getMyVideo(String deptId) throws Exception {

        if (StringUtil.isEmpty(deptId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return vipDepartmentService.getMyVideo(deptId);
    }


    /**
     * 获取我的流程处理信息
     *
     * @param deptId
     * @return
     */
    @RequestMapping("/getMyProcess")
    public ResultResponse getMyProcess(String deptId) throws Exception {

        if (StringUtil.isEmpty(deptId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return vipDepartmentService.getMyProcess(deptId);
    }
}

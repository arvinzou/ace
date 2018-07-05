package com.huacainfo.ace.cu.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.cu.model.CuProject;
import com.huacainfo.ace.cu.service.AnalysisService;
import com.huacainfo.ace.cu.service.CuDonateListService;
import com.huacainfo.ace.cu.service.CuProjectService;
import com.huacainfo.ace.cu.service.CuProjectUseRecordService;
import com.huacainfo.ace.cu.vo.CuDonateListQVo;
import com.huacainfo.ace.cu.vo.CuDonateListVo;
import com.huacainfo.ace.cu.vo.CuProjectUseRecordQVo;
import com.huacainfo.ace.cu.vo.CuProjectUseRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/6/21 17:43
 * @Description:
 */
@RestController
@RequestMapping("/www/report")
public class WWWReportController extends CuBaseController {

    @Autowired
    private AnalysisService analysisService;
    @Autowired
    private CuDonateListService cuDonateListService;
    @Autowired
    private CuProjectUseRecordService cuProjectUseRecordService;
    @Autowired
    private CuProjectService cuProjectService;

    /**
     * 财务公开数额统计
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/financeStatistics")
    public ResultResponse financeStatistics(String projectId) throws Exception {
        Map<String, Object> data = analysisService.financeStatistics(projectId);
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", data);
    }

    /**
     * 查询捐献记录
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findDonateList")
    public ResultResponse findDonateList(String projectId,
                                         int start, int limit, String orderBy) throws Exception {

        CuDonateListQVo condition = new CuDonateListQVo();
        condition.setProjectId(projectId);
        PageResult<CuDonateListVo> list = cuDonateListService.findCuDonateListList(condition, start, limit, orderBy);

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", list);
    }

    /**
     * 查询捐献记录
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findUsedRecord")
    public ResultResponse findUsedRecord(String projectId,
                                         int start, int limit, String orderBy) throws Exception {

        CuProjectUseRecordQVo condition = new CuProjectUseRecordQVo();
        condition.setProjectId(projectId);
        PageResult<CuProjectUseRecordVo> list = cuProjectUseRecordService.findCuProjectUseRecordList(condition,
                start, limit, orderBy);
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", list);
    }

    /**
     * 慈善榜单
     *
     * @param needOpenId 必传 0-否，1-是
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/donateRank")
    public ResultResponse donateRank(String projectId, String needOpenId, String openId,
                                     int start, int limit, String orderBy) throws Exception {
        if (StringUtil.isEmpty(needOpenId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        //慈善一日捐页面使用
        if ("1".equals(needOpenId)) {
            //公众号用户信息
            Userinfo userinfo = getCurUserinfo();
            if (null == userinfo && StringUtil.isEmpty(openId)) {
                return new ResultResponse(ResultCode.FAIL, "微信授权失败");
            }
            openId = StringUtil.isEmpty(openId) ? userinfo.getOpenid() : openId;

            Map<String, Object> respMap = analysisService.donateRank(projectId, needOpenId, openId,
                    start, limit, orderBy);
            return new ResultResponse(ResultCode.SUCCESS, "查询成功", respMap);
        } else {
            openId = "";
            //数据查询
            List<Map<String, Object>> list = analysisService.donateRank(projectId, openId, start, limit, orderBy);

            return new ResultResponse(ResultCode.SUCCESS, "查询成功", list);
        }
    }

    /**
     * 所有项目名称列表
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/projectList")
    public ResultResponse projectList(String projectType) throws Exception {
        if (CommonUtils.isEmpty(projectType)) {
            projectType = "0,1,2";
        }

        List<CuProject> list = cuProjectService.findAllProjectList(projectType);
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", list);
    }
}

package com.huacainfo.ace.jxb.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.jxb.service.ConsultService;
import com.huacainfo.ace.jxb.service.CounselorService;
import com.huacainfo.ace.jxb.vo.CounselorQVo;
import com.huacainfo.ace.jxb.vo.CounselorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Arvin
 * @Date: 2018/7/26 10:40
 * @Description:
 */
@RestController
@RequestMapping("/www/consult")
public class WConsultController extends JxbBaseController {

    @Autowired
    private ConsultService consultService;

    @Autowired
    private CounselorService counselorService;

    /**
     * 获取咨询师列表
     *
     * @return ResultResponse data(list)
     */
    @RequestMapping("/getCounselorList")
    public ResultResponse getCounselorList(CounselorQVo condition, PageParamNoChangeSord page) throws Exception {
        //只显示注册通过的咨询师
        condition.setRegAuditRst("1");
        condition.setConsultState("1");
        if (StringUtil.isNotEmpty(condition.getConsultType())) {
            condition.setConsultTypeArray(condition.getConsultType().split(","));
        }
        PageResult<CounselorVo> rst = counselorService.findCounselorList(
                condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }


        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rst);
    }

    /**
     * 获取咨询师主页
     *
     * @param counselorId 咨询师id
     * @return ResultResponse
     */
    @RequestMapping("/getCounselorDetail")
    public ResultResponse getCounselorDetail(String counselorId) throws Exception {
        if (StringUtil.isEmpty(counselorId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return consultService.getCounselorDetail(counselorId);
    }


    /**
     * 咨询师 在线/离线
     *
     * @param counselorId  咨询师id
     * @param onlineStatus 在线状态 0-离线 1-在线
     * @return ResultResponse
     */
    @RequestMapping("/onOff")
    public ResultResponse onOff(String counselorId, String onlineStatus) throws Exception {
        if (StringUtil.isEmpty(counselorId)) {
            Userinfo userinfo = getCurUserinfo();
            if (null == userinfo || StringUtil.isEmpty(userinfo.getUnionid())) {
                return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
            }

            counselorId = userinfo.getUnionid();
        }

        return consultService.onOff(counselorId, onlineStatus);
    }


}

package com.huacainfo.ace.jxb.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.jxb.service.CounselorService;
import com.huacainfo.ace.jxb.service.PostLevelService;
import com.huacainfo.ace.jxb.service.WithdrawRecordService;
import com.huacainfo.ace.jxb.vo.WithdrawRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Arvin
 * @Date: 2018/7/24 14:16
 * @Description:
 */
@RestController
@RequestMapping("/www/counselor")
public class WCounselorController extends JxbBaseController {
    @Autowired
    private CounselorService counselorService;
    @Autowired
    private PostLevelService postLevelService;
    @Autowired
    private WithdrawRecordService withdrawRecordService;


    /**
     * 咨询师"我"的账户信息
     *
     * @param counselorId 咨询师id
     * @return ResultResponse data=>Map
     */
    @RequestMapping("/accountInfo")
    public ResultResponse accountInfo(String counselorId) {
        if (StringUtil.isEmpty(counselorId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return counselorService.accountInfo(counselorId);
    }

    /**
     * 获取绩效考核数据
     *
     * @param quarter     1-第一季度，2-第二季度，3-第三季度，4-第四季度 必传
     * @param counselorId 咨询师id 可选
     * @return ResultResponse
     */
    @RequestMapping("/examine")
    public ResultResponse examine(String quarter, String counselorId) {

        return postLevelService.examine(quarter, counselorId);
    }

    /**
     * 余额提现申请
     *
     * @param unionid unionid 可选
     * @param openId  openId 可选
     * @param params  必须
     * @return ResultResponse
     */
    @RequestMapping("/withdraw")
    public ResultResponse withdraw(String unionid, String openId, WithdrawRecordVo params) throws Exception {
        if (StringUtil.isEmpty(unionid)) {
            Userinfo userinfo = getCurUserinfo();
            unionid = userinfo.getUnionid();
            openId = userinfo.getOpenid();
        }
        params.setCounselorId(StringUtil.isEmpty(params.getCounselorId()) ? unionid : params.getCounselorId());
        params.setOpenId(openId);


        return withdrawRecordService.withdraw(params);
    }
}

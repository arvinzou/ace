package com.huacainfo.ace.cu.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.cu.service.CuUserService;
import com.huacainfo.ace.cu.vo.CuDonateListVo;
import com.huacainfo.ace.cu.vo.CuProjectApplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by HuaCai008 on 2018/6/14.
 */
@Controller
@RequestMapping("/www/user")
public class WWWUserController extends CuBaseController {

    @Autowired
    private CuUserService cuUserService;

    /**
     * 查询会员信息
     *
     * @param openId 微信openid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findByOpenId")
    @ResponseBody
    public ResultResponse findByOpenId(String openId) throws Exception {
        //公众号用户信息
        if (CommonUtils.isBlank(openId)) {
            Userinfo userinfo = getCurUserinfo();
            if (null == userinfo) {
                return new ResultResponse(ResultCode.FAIL, "微信授权失败");
            }
            openId = userinfo.getOpenid();
        }

        if (StringUtil.isEmpty(openId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", cuUserService.findByOpenId(openId));
    }


    /**
     * 查询会员 -- 爱心记录/爱心积分
     *
     * @param openId 微信openid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findDonateList")
    @ResponseBody
    public ResultResponse findDonateList(String openId, int start, int limit, String orderBy) throws Exception {
        //公众号用户信息
        if (CommonUtils.isBlank(openId)) {
            Userinfo userinfo = getCurUserinfo();
            if (null == userinfo) {
                return new ResultResponse(ResultCode.FAIL, "微信授权失败");
            }
            openId = userinfo.getOpenid();
        }
        if (StringUtil.isEmpty(openId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        PageResult<CuDonateListVo> list = cuUserService.findDonateList(openId, start, limit, orderBy);

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", list);
    }

    /**
     * 查询会员 -- 我的求助
     *
     * @param openId 微信openid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findMyProject")
    @ResponseBody
    public ResultResponse findMyProject(String openId, int start, int limit, String orderBy) throws Exception {
        //公众号用户信息
        if (CommonUtils.isBlank(openId)) {
            Userinfo userinfo = getCurUserinfo();
            if (null == userinfo) {
                return new ResultResponse(ResultCode.FAIL, "微信授权失败");
            }
            openId = userinfo.getOpenid();
        }
        if (StringUtil.isEmpty(openId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        PageResult<CuProjectApplyVo> list = cuUserService.findMyProject(openId, start, limit, orderBy);

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", list);
    }
}

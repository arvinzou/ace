package com.huacainfo.ace.society.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.society.service.BehaviorService;
import com.huacainfo.ace.society.vo.BehaviorQVo;
import com.huacainfo.ace.society.vo.BehaviorVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Arvin
 * @Date: 2018/10/16 16:13
 * @Description:
 */
@RestController
@RequestMapping("/www/behavior")
public class WBehaviorController extends SocietyBaseController {

    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private BehaviorService behaviorService;

    /***
     * 查询随手拍列表
     * @param condition
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findList")
    @ResponseBody
    public ResultResponse findBehaviorList(BehaviorQVo condition,
                                           PageParamNoChangeSord page) throws Exception {

        PageResult<BehaviorVo> rst =
                behaviorService.findBehaviorList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rst);
    }

    /**
     * 提交文明随手拍
     *
     * @param params  参数列表
     * @param unionId 提交人id，可选
     * @return ResultResponse
     * @throws Exception
     */
    @RequestMapping(value = "/submit")
    @ResponseBody
    public ResultResponse submit(String params, String unionId) throws Exception {

        WxUser wxUser = getCurWxUser();//小程序用户
        if (null == wxUser && StringUtil.isEmpty(unionId)) {
            return new ResultResponse(ResultCode.FAIL, "微信鉴权失败");
        }
        unionId = StringUtil.isNotEmpty(unionId) ? unionId : wxUser.getUnionId();

        if (!StringUtil.areNotEmpty(params, unionId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        BehaviorVo vo = JsonUtil.toObject(params, BehaviorVo.class);
        vo.setUserId(unionId);

        return behaviorService.submit(vo);
    }


    /**
     * 送审
     *
     * @param id 主键ID society.behavior.id
     * @return ResultResponse
     * @throws Exception
     */
    @RequestMapping("/sendAudit")
    public ResultResponse sendAudit(String id, String unionId) throws Exception {
        //小程序用户
        WxUser wxUser = getCurWxUser();
        if (null == wxUser && StringUtil.isEmpty(unionId)) {
            return new ResultResponse(ResultCode.FAIL, "微信鉴权失败");
        }
        unionId = StringUtil.isNotEmpty(unionId) ? unionId : wxUser.getUnionId();

        if (StringUtil.isEmpty(id)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return behaviorService.sendAudit(id, unionId);
    }


    /**
     * 获取详情
     *
     * @param id 主键ID society.behavior.id
     * @return ResultResponse
     * @throws Exception
     */
    @RequestMapping("/getDetail")
    public ResultResponse getDetail(String id, String unionId) throws Exception {

        WxUser wxUser = getCurWxUser();//小程序用户
        if (null == wxUser && StringUtil.isEmpty(unionId)) {
            return new ResultResponse(ResultCode.FAIL, "微信鉴权失败");
        }
        unionId = StringUtil.isNotEmpty(unionId) ? unionId : wxUser.getUnionId();

        if (!StringUtil.areNotEmpty(id, unionId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return behaviorService.getDetail(id, unionId);
    }
}


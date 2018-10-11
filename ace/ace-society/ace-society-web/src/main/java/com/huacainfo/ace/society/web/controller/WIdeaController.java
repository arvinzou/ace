package com.huacainfo.ace.society.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.society.service.SubjectIdeaService;
import com.huacainfo.ace.society.vo.SubjectIdeaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Arvin
 * @Date: 2018/10/11 11:13
 * @Description:
 */
@RestController
@RequestMapping("/www/idea")
public class WIdeaController extends SocietyBaseController {

    @Autowired
    private SubjectIdeaService ideaService;

    /**
     * 提交“我的点子”
     *
     * @param params  表单参数
     * @param unionId 提交用户ID
     * @return
     * @throws Exception
     */
    @RequestMapping("/submit")
    public ResultResponse submit(SubjectIdeaVo params, String unionId) throws Exception {

//        getUserInfo(unionId);
//        getCurUserinfo();//公众号用户
//        getCurWxUser();//小程序用户

        if (StringUtil.isNotEmpty(unionId)) {
            params.setUserId(unionId);
        }

        return ideaService.submit(params);
    }

    /**
     * “我的点子” 送审
     *
     * @param ideaId 点子ID
     * @return ResultResponse
     * @throws Exception
     */
    @RequestMapping("/sendAudit")
    public ResultResponse sendAudit(String ideaId, String unionId) throws Exception {

//        getUserInfo(unionId);
//        getCurUserinfo();//公众号用户
//        getCurWxUser();//小程序用户

        if (!StringUtil.areNotEmpty(ideaId, unionId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return ideaService.sendAudit(ideaId, unionId);
    }

    /**
     * 获取点子详情
     *
     * @param ideaId 点子ID
     * @return ResultResponse
     * @throws Exception
     */
    @RequestMapping("/getIdeaDetail")
    public ResultResponse getIdeaDetail(String ideaId, String unionId) throws Exception {

//        getUserInfo(unionId);
//        getCurUserinfo();//公众号用户
//        getCurWxUser();//小程序用户

        if (!StringUtil.areNotEmpty(ideaId, unionId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return ideaService.getIdeaDetail(ideaId);
    }
}

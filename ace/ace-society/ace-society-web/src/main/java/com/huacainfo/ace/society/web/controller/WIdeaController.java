package com.huacainfo.ace.society.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.society.service.SubjectIdeaService;
import com.huacainfo.ace.society.vo.SubjectIdeaQVo;
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
     * @param unionId 提交用户ID
     * @return
     * @throws Exception
     */
    @RequestMapping("/findList")
    public ResultResponse findList(SubjectIdeaQVo condition,
                                   PageParamNoChangeSord page, String unionId) throws Exception {

//        getCurUserinfo();//公众号用户
        /**
         * 0-查询自己， 1-查询所有
         */
        if ("0".equals(condition.getGetAll())) {
            WxUser wxUser = getCurWxUser();//小程序用户
            if ((null == wxUser || StringUtil.isEmpty(wxUser.getUnionId()))
                    && StringUtil.isEmpty(unionId)) {
                return new ResultResponse(ResultCode.FAIL, "微信授权失败");
            }
            condition.setUserId(StringUtil.isEmpty(unionId) ? wxUser.getUnionId() : unionId);
        }

        PageResult<SubjectIdeaVo> rst =
                ideaService.findSubjectIdeaList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rst);
    }

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

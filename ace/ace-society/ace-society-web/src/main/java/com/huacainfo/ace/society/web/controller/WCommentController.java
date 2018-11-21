package com.huacainfo.ace.society.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.society.service.AdmireRecordService;
import com.huacainfo.ace.society.service.CommentRecordService;
import com.huacainfo.ace.society.vo.AdmireRecordQVo;
import com.huacainfo.ace.society.vo.AdmireRecordVo;
import com.huacainfo.ace.society.vo.CommentRecordQVo;
import com.huacainfo.ace.society.vo.CommentRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Arvin
 * @Date: 2018/10/24 17:09
 * @Description:
 */
@RestController
@RequestMapping("/www/comment")
public class WCommentController extends SocietyBaseController {
    @Autowired
    private CommentRecordService commentRecordService;

    @Autowired
    private AdmireRecordService admireRecordService;

    /***
     * 获取评论列表
     * @param condition 查询条件
     * @param page 分页工具
     * @return ResultResponse
     * @throws Exception
     */
    @RequestMapping("/findList")
    public ResultResponse findList(CommentRecordQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<CommentRecordVo> rst = commentRecordService.findCommentRecordList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rst);
    }


    /***
     * 获取评论列表
     * @param condition 查询条件
     * @return ResultResponse
     * @throws Exception
     */
    @RequestMapping("/findAdmireTotal")
    public ResultResponse findAdmireTotal(AdmireRecordQVo condition, String unionId) throws Exception {
        WxUser wxUser = getCurWxUser();//小程序用户
        if (null == wxUser && StringUtil.isEmpty(unionId)) {
            return new ResultResponse(ResultCode.FAIL, "微信鉴权失败");
        }
        unionId = StringUtil.isNotEmpty(unionId) ? unionId : wxUser.getUnionId();
        condition.setUserId(unionId);
        return admireRecordService.findAdmireTotal(condition);
    }


    /**
     * 提交评论
     *
     * @param params  表单参数 ：   bisType  bisId   content;
     * @param unionId 可选
     * @return ResultResponse
     * @throws Exception
     */
    @RequestMapping("/submitComment")
    public ResultResponse submitComment(CommentRecordVo params, String unionId) throws Exception {
        WxUser wxUser = getCurWxUser();//小程序用户
        if (null == wxUser && StringUtil.isEmpty(unionId)) {
            return new ResultResponse(ResultCode.FAIL, "微信鉴权失败");
        }
        unionId = StringUtil.isNotEmpty(unionId) ? unionId : wxUser.getUnionId();
        params.setUserId(unionId);

        if (!StringUtil.areNotEmpty(params.getBisId(), params.getBisType(), params.getContent())) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }


        return commentRecordService.submit(params);
    }


    /**
     * 提交点赞
     * <p>
     * 表单参数 ：   bisType  bisId ;
     *
     * @param unionId 可选
     * @return ResultResponse
     * @throws Exception
     */
    @RequestMapping("/admire")
    public ResultResponse admire(String bisType, String bisId, String unionId) throws Exception {
        WxUser wxUser = getCurWxUser();//小程序用户
        if (null == wxUser && StringUtil.isEmpty(unionId)) {
            return new ResultResponse(ResultCode.FAIL, "微信鉴权失败");
        }
        AdmireRecordVo params = new AdmireRecordVo();
        unionId = StringUtil.isNotEmpty(unionId) ? unionId : wxUser.getUnionId();
        params.setUserId(unionId);
        params.setBisId(bisId);
        params.setBisType(bisType);

        if (!StringUtil.areNotEmpty(params.getBisId(), params.getBisType())) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return admireRecordService.submit(params);
    }


    /**
     * 取消点赞
     * <p>
     * 表单参数 ：   bisType  bisId ;
     *
     * @param unionId 可选
     * @return ResultResponse
     * @throws Exception
     */
    @RequestMapping("/cancelAdmire")
    public ResultResponse cancelAdmire(String bisType, String bisId, String unionId) throws Exception {
        WxUser wxUser = getCurWxUser();//小程序用户
        if (null == wxUser && StringUtil.isEmpty(unionId)) {
            return new ResultResponse(ResultCode.FAIL, "微信鉴权失败");
        }
        unionId = StringUtil.isNotEmpty(unionId) ? unionId : wxUser.getUnionId();

        if (!StringUtil.areNotEmpty(bisType, bisId, unionId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return admireRecordService.cancelAdmire(bisType, bisId, unionId);
    }

}

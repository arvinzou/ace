package com.huacainfo.ace.cu.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.cu.model.CuProcessRecord;
import com.huacainfo.ace.cu.service.CuDayDonateService;
import com.huacainfo.ace.cu.service.CuDonateOrderService;
import com.huacainfo.ace.cu.service.CuProcessRecordService;
import com.huacainfo.ace.cu.service.CuProjectApplyService;
import com.huacainfo.ace.cu.service.CuProjectService;
import com.huacainfo.ace.cu.vo.CuDonateOrderVo;
import com.huacainfo.ace.cu.vo.CuProjectApplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    @Autowired
    private CuProcessRecordService cuProcessRecordService;
    @Autowired
    private CuDayDonateService cuDayDonateService;


    /**
     * 查询项目列表 -字段过滤
     *
     * @param type    项目类型 0-普通项目 1-慈善一日捐 2-个人项目 3-支出项目 4-春节送温暖
     * @param start   分页开始位置  --  必选
     * @param limit   页数  --  必选
     * @param orderBy 排序条件   --  可选，默认时间倒叙
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findListFilter")
    @ResponseBody
    public ResultResponse findListFilter(String type, int start, int limit, String orderBy) throws Exception {
        if (StringUtil.isEmpty(type)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return cuProjectService.findListFilter(type, start, limit, orderBy);
    }

    /**
     * 查询项目列表
     *
     * @param type    项目类型 0-普通项目 1-慈善一日捐 2-个人项目 3-支出项目 4-春节送温暖，5-日行一善
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

    /**
     * 查询 “救急难” -  处理过程
     */
    @RequestMapping(value = "/getApplyProcess")
    @ResponseBody
    public ResultResponse getApplyProcess(String applyId) throws Exception {
        List<CuProcessRecord> list = cuProcessRecordService.findList(applyId);

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", list);
    }

    /**
     * 查询项目 - 捐赠列表 -- 当日一天内的数据结果
     *
     * @param projectId 慈善项目ID
     * @param start     分页开始位置  --  必选
     * @param limit     页数  --  必选
     * @param orderBy   排序条件   --  可选，默认时间倒叙
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findDonateListToday")
    @ResponseBody
    public ResultResponse findDonateListToday(String projectId, int start, int limit, String orderBy) throws Exception {
        if (StringUtil.isEmpty(projectId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        return cuProjectService.findDonateListToday(projectId, start, limit, orderBy);
    }
    
    /**
     * 日行一善 初始化数据接口
     * @param userId
     * @param projectId
     * @return
     * @throws Exception
     */
	@RequestMapping("/init")
	@ResponseBody
	public ResultResponse initDatDonateInfo(String userId, String projectId) throws Exception {
        Userinfo userinfo = getCurUserinfo();
        if (null == userinfo) {
            return new ResultResponse(ResultCode.FAIL, "微信授权失败");
        }
        return cuDayDonateService.initDayDonateData(userinfo.getOpenid(), projectId);
    }
	
	/**
	 * 日行一善 捐献明细接口
	 * @param userId
	 * @param projectId
	 * @return
	 */
	@RequestMapping("/donateDetails")
	@ResponseBody
	public ResultResponse personalDonateDetails(String userId, String projectId) {
        Userinfo userinfo = getCurUserinfo();
        if (null == userinfo) {
            return new ResultResponse(ResultCode.FAIL, "微信授权失败");
        }
		
		return cuDayDonateService.personalDonateDataDetails(userinfo.getOpenid(),projectId);
	}
	
	/**
	 * 日行一善 积分排名接口
	 * @return
	 */
	@RequestMapping("/pointsRank")
	@ResponseBody
	public ResultResponse pointsRank(String  projectId) {
		//公众号用户信息
        Userinfo userinfo = getCurUserinfo();
        if (null == userinfo) {
            return new ResultResponse(ResultCode.FAIL, "微信授权失败");
        }
		return cuDayDonateService.pointsRank(userinfo.getOpenid(),projectId);
	}
}

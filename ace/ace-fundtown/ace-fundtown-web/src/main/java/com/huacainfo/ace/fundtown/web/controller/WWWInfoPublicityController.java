package com.huacainfo.ace.fundtown.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.fundtown.service.VipPublicityService;
import com.huacainfo.ace.fundtown.vo.VipPublicityQVo;
import com.huacainfo.ace.fundtown.vo.VipPublicityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Arvin
 * @Date: 2018/7/6 16:01
 * @Description:
 */
@RestController
@RequestMapping("/www/info")
public class WWWInfoPublicityController extends BisBaseController {

    @Autowired
    private VipPublicityService vipPublicityService;

    /**
     * 获取公示列表
     *
     * @param type    企业类型  0-管理结构 1-基金
     * @param start   页码
     * @param limit   页数
     * @param orderBy 排序条件
     * @return ResultResponse
     */
    @RequestMapping("/vipList")
    public ResultResponse getVipList(int type, int start, int limit, String orderBy) throws Exception {
        VipPublicityQVo condition = new VipPublicityQVo();
        condition.setType(Integer.valueOf(type));

        PageResult<VipPublicityVo> vipList = vipPublicityService.findVipPublicityList(condition, start, limit, orderBy);

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", vipList);
    }


    /**
     * 获取机构/基金详情
     *
     * @param deptId 企业department.id
     * @return list
     */
    @RequestMapping("/vipInfo")
    public ResultResponse getVipInfo(String deptId) {
        if (StringUtil.isEmpty(deptId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        VipPublicityVo voInfo = vipPublicityService.getVipInfo(deptId);

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", voInfo);
    }
}

package com.huacainfo.ace.jxb.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.jxb.service.*;
import com.huacainfo.ace.jxb.vo.*;
import com.huacainfo.ace.portal.service.EvaluatTplService;
import com.huacainfo.ace.portal.vo.EvaluatTplQVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Auther: Arvin
 * @Date: 2018/7/26 10:40
 * @Description:
 */
@RestController
@RequestMapping("/www/index")
public class WIndexController extends JxbBaseController {


    @Autowired
    private CounselorService counselorService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private BaseOrderService baseOrderService;
    @Autowired
    private BannerService bannerService;



    /**
     * 随机获取课程列表
     */
    @RequestMapping("/findListRand")
    public ResultResponse findList() throws Exception {
        CourseQVo condition = new CourseQVo();
        condition.setFine("1");
        PageResult<CourseVo> rst = this.courseService.findCourseList(condition, 0, 4, "RAND()");
        List<CourseVo> list = rst.getRows();
        BaseOrderQVo baseOrder = new BaseOrderQVo();
        baseOrder.setPayStatus("2");
        for (CourseVo item : list) {
            baseOrder.setCommodityId(item.getId());
            ResultResponse rr = baseOrderService.findBaseOrderListSencond(baseOrder, 1, 5, null);
            if (rr.getStatus() == 0) {
                item.setConsumerList(rr.getData());
            }
        }
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rst);
    }

    /**
     * 随机获取课程列表
     */
    @RequestMapping("/findListFree")
    public ResultResponse findListFree() throws Exception {
        CourseQVo condition = new CourseQVo();
        condition.setCostType("0");
        PageResult<CourseVo> rst1 = this.courseService.findCourseList(condition, 0, 10, "RAND()");
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rst1);
    }


    /**
     * 获取咨询师列表
     *
     * @return ResultResponse data(list)
     */
    @RequestMapping("/getCounselorRanking")
    public ResultResponse getCounselorList() throws Exception {
        CounselorQVo condition = new CounselorQVo();
        //只显示注册通过的咨询师
        condition.setRegAuditRst("1");
        condition.setConsultState("1");
        PageResult<CounselorVo> rst = counselorService.findCounselorList(condition, 0, 10, "consultCount desc");
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rst);
    }


    /**
     * 搜索
     *
     * @return ResultResponse data(list)
     */
    @RequestMapping("/search")
    public ResultResponse search(String name) throws Exception {
        CourseQVo condition = new CourseQVo();
        condition.setCostType("1");
        condition.setName(name);
        PageResult<CourseVo> rst = this.courseService.findCourseList(condition, 0, 8, "RAND()");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Course", rst);

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", map);
    }

    /**
     * @return ResultResponse data(list)
     */
    @RequestMapping("/banner")
    public ResultResponse banner() throws Exception {
        BannerQVo bannerQVo = new BannerQVo();
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", bannerService.findBannerList(bannerQVo,0,4,null));
    }
}

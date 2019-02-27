package com.huacainfo.ace.taa.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.taa.model.RoadGps;
import com.huacainfo.ace.taa.service.RoadGpsService;
import com.huacainfo.ace.taa.service.RoadManService;
import com.huacainfo.ace.taa.service.RoadSectionService;
import com.huacainfo.ace.taa.vo.RoadManQVo;
import com.huacainfo.ace.taa.vo.RoadManVo;
import com.huacainfo.ace.taa.vo.RoadSectionQVo;
import com.huacainfo.ace.taa.vo.RoadSectionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2019/1/12 11:36
 * @Description:
 */
@RestController
@RequestMapping("/www/road")
public class WRoadController extends TaaBaseController {


    @Autowired
    private RoadGpsService roadGpsService;
    @Autowired
    private RoadManService roadManService;
    @Autowired
    private RoadSectionService roadSectionService;


    /**
     * 路段采集
     *
     * @param jsonData List<RoadGps> 数据数组 - json格式字符串
     *                 roadSectionId 所属路段
     *                 latitude      纬度
     *                 longitude     经度
     * @param uid      操作用户ID，可选
     * @return ResultResponse
     */
    @RequestMapping("/gather")
    public ResultResponse gather(String jsonData, String uid) throws Exception {
        if (CommonUtils.isBlank(jsonData)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        //微信鉴权信息 --小程序
        WxUser user = getCurWxUser();
        if (StringUtil.isNotEmpty(uid)) {
            user = new WxUser();
            user.setUnionId(uid);
            user.setNickName("test");
        }
        if (user == null) {
            return new ResultResponse(ResultCode.FAIL, "微信授权失败");
        }

        Map<String, Object> map = JsonUtil.toMap(jsonData);
        List<RoadGps> list = JsonUtil.toList(String.valueOf(map.get("list")), RoadGps.class);
        MessageResponse ms = roadGpsService.insertRoadGps(list, parseUser(user));

        return new ResultResponse(ms);
    }

    /**
     * 获取最近路段信息
     *
     * @param lat    纬度坐标
     * @param lon    经度坐标
     * @param radius 扫描半径距离，单位：米
     * @return ResultResponse
     * @throws Exception
     */
    @RequestMapping("/getCloseRoadSection")
    public ResultResponse getCloseRoadSection(String lat, String lon, String radius) throws Exception {
        if (!StringUtil.areNotEmpty(lat, lon, radius)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        try {
            double dLat = Double.parseDouble(lat);
            double dLon = Double.parseDouble(lon);
            int iRadius = Integer.parseInt(radius);
            return roadGpsService.getCloseRoadSection(dLat, dLon, iRadius);
        } catch (NumberFormatException e) {
            return new ResultResponse(ResultCode.FAIL, "坐标转换失败");
        }
    }


    /**
     * 获取所有路长列表
     *
     * @return ResultResponse
     * @throws Exception
     */
    @RequestMapping("/findRoadManList")
    public PageResult<RoadManVo> findRoadManList(RoadManQVo condition) throws Exception {
        PageResult<RoadManVo> rst = roadManService.findRoadManList(condition, 0, 5000, "");
        return rst;
    }

    /**
     * 获取所有路长花名册 - 通讯录形式
     *
     * @return ResultResponse
     * @throws Exception
     */
    @RequestMapping("/roster")
    public ResultResponse roster(String roadManName) throws Exception {
        return roadManService.findRoster(roadManName);
    }


    /**
     * 获取所有路长花名册 - 通讯录形式
     *
     * @param condition category 查询类别  1-已采集 ; 0 - 未采集
     * @return ResultResponse
     * @throws Exception
     */
    @RequestMapping("/findSectionList")
    public ResultResponse findSectionList(String uid, RoadSectionQVo condition,
                                          PageParamNoChangeSord page) throws Exception {
        if (StringUtil.isEmpty(condition.getCategory())) {
            return new ResultResponse(ResultCode.FAIL, "请选择查询类别");
        }

        //微信鉴权信息 --小程序
        WxUser user = getCurWxUser();
        if (StringUtil.isNotEmpty(uid)) {
            user = new WxUser();
            user.setUnionId(uid);
            user.setNickName("test");
        }
        if (user == null) {
            return new ResultResponse(ResultCode.FAIL, "微信授权失败");
        }
        UserProp u = parseUser(user);
        if (CommonUtils.isBlank(condition.getAreaCode())) {
            condition.setAreaCode(u.getAreaCode());
        }
        condition.setDeptId(u.getCorpId());

        PageResult<RoadSectionVo> rst = roadSectionService.findRoadSectionList(condition,
                page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return new ResultResponse(ResultCode.SUCCESS, "SUCCESS", rst);
    }

    /**
     * 重置路段采集数据
     *
     * @param sectionId 路段ID
     * @param uid       用户ID，可选
     * @return ResultResponse
     * @throws Exception
     */
    @RequestMapping("/resetSectionGPS")
    public ResultResponse resetSectionGPS(String sectionId, String uid) throws Exception {
        //微信鉴权信息 --小程序
        WxUser user = getCurWxUser();
        if (StringUtil.isNotEmpty(uid)) {
            user = new WxUser();
            user.setUnionId(uid);
            user.setNickName("test");
        }
        if (user == null) {
            return new ResultResponse(ResultCode.FAIL, "微信授权失败");
        }
        UserProp u = parseUser(user);

        MessageResponse m = roadGpsService.resetSectionGPS(sectionId);
        return new ResultResponse(m);
    }


    /**
     * 获取路段已采集坐标列表
     *
     * @param sectionId 路段ID
     * @return ListResult
     * @throws Exception
     */
    @RequestMapping("/getGPSList")
    public ListResult<Map<String, Object>> getGPSList(String sectionId) throws Exception {

        Map<String, Object> p = new HashMap<>();
        p.put("id", sectionId);
        return this.roadGpsService.getList(p);
    }

}

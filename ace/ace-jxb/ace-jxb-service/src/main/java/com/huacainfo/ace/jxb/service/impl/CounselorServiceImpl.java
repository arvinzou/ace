package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.BaseOrderDao;
import com.huacainfo.ace.jxb.dao.CounselorDao;
import com.huacainfo.ace.jxb.dao.CourseDao;
import com.huacainfo.ace.jxb.dao.StudioDao;
import com.huacainfo.ace.jxb.model.BaseOrder;
import com.huacainfo.ace.jxb.model.Counselor;
import com.huacainfo.ace.jxb.service.CounselorService;
import com.huacainfo.ace.jxb.vo.CounselorQVo;
import com.huacainfo.ace.jxb.vo.CounselorVo;
import com.huacainfo.ace.jxb.vo.CourseQVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.UserinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("counselorService")
/**
 * @author: Arvin
 * @version: 2018-07-20
 * @Description: TODO(咨询师)
 */
public class CounselorServiceImpl implements CounselorService {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CounselorDao counselorDao;

    @Autowired
    private StudioDao studioDao;
    @Autowired
    private UserinfoService userinfoService;

    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private BaseOrderDao baseOrderDao;
    @Autowired
    private CourseDao courseDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(咨询师分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CounselorVo>
     * @author: Arvin
     * @version: 2018-07-20
     */
    @Override
    public PageResult<CounselorVo> findCounselorList(CounselorQVo condition, int start,
                                                     int limit, String orderBy) throws Exception {
        PageResult<CounselorVo> rst = new PageResult<>();
        List<CounselorVo> list = counselorDao.findList(condition, start, limit, orderBy);
        for (CounselorVo item : list) {
            if (CommonUtils.isBlank(item.getImagePhotoUrl())) {
//                UserinfoVo userinfoVo = userinfoService.selectUserinfoByKey(item.getId());
                item.setImagePhotoUrl(item.getHeadimgurl());
            }
        }
        rst.setRows(list);
        if (start <= 1) {
            int allRows = counselorDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public ResultResponse findMyCounselors(CounselorQVo condition, int start, int limit, String orderBy) throws Exception {
        List<CounselorVo> list = counselorDao.findList(condition, start, limit, orderBy);
        int allRows = counselorDao.findCount(condition);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("total", allRows);
        return new ResultResponse(ResultCode.SUCCESS, "单个工作室中的成员", map);
    }

    /**
     * @throws
     * @Title:insertCounselor
     * @Description: TODO(添加咨询师)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-20
     */
    @Override
    public MessageResponse insertCounselor(Counselor o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getMobile())) {
            return new MessageResponse(1, "手机号不能为空！");
        }
        if (CommonUtils.isBlank(o.getImagePhotoUrl())) {
            return new MessageResponse(1, "形象照不能为空！");
        }
        if (CommonUtils.isBlank(o.getIdCard())) {
            return new MessageResponse(1, "身份证号不能为空！");
        }
        if (CommonUtils.isBlank(o.getIdCardImgUrl())) {
            return new MessageResponse(1, "身份证件电子档不能为空！");
        }
        if (CommonUtils.isBlank(o.getCertificateNo())) {
            return new MessageResponse(1, "资格从业证书号不能为空！");
        }
        if (CommonUtils.isBlank(o.getCertificateImgUrl())) {
            return new MessageResponse(1, "资格从业证书电子档不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvidenceImgUrl())) {
            return new MessageResponse(1, "身份证持胸前自拍照不能为空！");
        }
        if (CommonUtils.isBlank(o.getLevel())) {
            return new MessageResponse(1, "级别不能为空！");
        }


        int temp = counselorDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "咨询师名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        counselorDao.insertSelective(o);
        dataBaseLogService.log("添加咨询师", "咨询师", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加咨询师完成！");
    }

    /**
     * @throws
     * @Title:updateCounselor
     * @Description: TODO(更新咨询师)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-20
     */
    @Override
    public MessageResponse updateCounselor(Counselor o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }

        if (!CommonUtils.isBlank(o.getIdCard())) {
            if (CommonUtils.isBlank(o.getName())) {
                return new MessageResponse(1, "姓名不能为空！");
            }
            if (CommonUtils.isBlank(o.getMobile())) {
                return new MessageResponse(1, "手机号不能为空！");
            }
            if (CommonUtils.isBlank(o.getIdCard())) {
                return new MessageResponse(1, "身份证号不能为空！");
            }
            if (CommonUtils.isBlank(o.getIdCardImgUrl())) {
                return new MessageResponse(1, "身份证件电子档不能为空！");
            }
            if (CommonUtils.isBlank(o.getCertificateNo())) {
                return new MessageResponse(1, "资格从业证书号不能为空！");
            }
            if (CommonUtils.isBlank(o.getCertificateImgUrl())) {
                return new MessageResponse(1, "资格从业证书电子档不能为空！");
            }
            if (CommonUtils.isBlank(o.getEvidenceImgUrl())) {
                return new MessageResponse(1, "身份证持胸前自拍照不能为空！");
            }
        }
        if (CommonUtils.isBlank(o.getImagePhotoUrl())) {
            return new MessageResponse(1, "形象照不能为空！");
        }
        counselorDao.updateByPrimaryKeySelective(o);
        dataBaseLogService.log("变更咨询师", "咨询师", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更咨询师完成！");
    }

    /**
     * @throws
     * @Title:selectCounselorByPrimaryKey
     * @Description: TODO(获取咨询师)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Counselor>
     * @author: Arvin
     * @version: 2018-07-20
     */
    @Override
    public SingleResult<CounselorVo> selectCounselorByPrimaryKey(String id) throws Exception {
        SingleResult<CounselorVo> rst = new SingleResult<>();
        rst.setValue(counselorDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCounselorByCounselorId
     * @Description: TODO(删除咨询师)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-20
     */
    @Override
    public MessageResponse deleteCounselorByCounselorId(String id, UserProp userProp)
            throws Exception {
        Counselor counselor = new Counselor();
        counselor.setId(id);
        counselor.setStatus("0");
        counselorDao.updateByPrimaryKeySelective(counselor);
        dataBaseLogService.log("删除咨询师", "咨询师",
                String.valueOf(id), String.valueOf(id), "咨询师", userProp);
        return new MessageResponse(0, "咨询师删除完成！");
    }

    /**
     * 注册老师资料
     *
     * @param mobile   手机号码
     * @param studioId 推荐人userid （portal.users.id）
     * @param userinfo 微信基本信息
     * @return
     */
    @Override
    public ResultResponse register(String mobile, String studioId, Userinfo userinfo) {
        Counselor counselor = counselorDao.selectByPrimaryKey(userinfo.getOpenid());
        if (null == counselor) {
            counselor = new Counselor();
            counselor.setId(userinfo.getUnionid());
            counselor.setStudioId(StringUtil.isEmpty(studioId) ? "0" : studioId);
            counselor.setName(userinfo.getNickname());
            counselor.setMobile(mobile);
            counselor.setLevel("0");
            counselor.setCreateDate(DateUtil.getNowDate());
            counselorDao.insertSelective(counselor);
        } else if (StringUtil.isEmpty(counselor.getMobile())) {
            counselor.setMobile(mobile);
            counselorDao.updateByPrimaryKeySelective(counselor);
        }

        return new ResultResponse(ResultCode.SUCCESS, "注册完成");
    }

    /**
     * 获取咨询师统计数据
     *
     * @param counselorId 咨询师ID
     * @return map
     */
    @Override
    public Map<String, Object> statistic(String counselorId) {

        Map<String, Object> rtnMap = new HashMap<>();
        BaseOrder baseOrder = new BaseOrder();
        baseOrder.setBusinessId(counselorId);
        baseOrder.setPayStatus("2");
        baseOrder.setCategory("1");
        int consultCount = baseOrderDao.orderStatistics(baseOrder);
        rtnMap.put("consultCount", consultCount);
        CourseQVo courseQVo = new CourseQVo();
        courseQVo.setCreateUserId(counselorId);
        int courseCount = courseDao.findCount(courseQVo);
        rtnMap.put("courseCount", courseCount);
//        rtnMap.put("liveCount", 0);
//        rtnMap.put("teacherCount", 0);
        int parentCount = counselorDao.statisticalMember(counselorId);
        rtnMap.put("parentCount", parentCount);
        return rtnMap;
    }

    /**
     * 咨询师"我"的账户信息
     *
     * @param counselorId 咨询师id
     * @return ResultResponse data=>Map
     */
    @Override
    public ResultResponse accountInfo(String counselorId) {


        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("monthIncome", 168);
        rtnMap.put("consultIncome", 800);
        rtnMap.put("courseIncome", 666);
        rtnMap.put("underlingIncome", 288.88);
        rtnMap.put("rewardIncome", 66.66);
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rtnMap);
    }

}

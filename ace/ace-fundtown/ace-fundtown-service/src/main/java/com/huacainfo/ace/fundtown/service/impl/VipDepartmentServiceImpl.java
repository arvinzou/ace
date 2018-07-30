package com.huacainfo.ace.fundtown.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.PropertyUtil;
import com.huacainfo.ace.fundtown.dao.VipDepartmentDao;
import com.huacainfo.ace.fundtown.dao.VipMemberResDao;
import com.huacainfo.ace.fundtown.dao.VipProcessRecordDao;
import com.huacainfo.ace.fundtown.model.VipDepartment;
import com.huacainfo.ace.fundtown.model.VipMemberRes;
import com.huacainfo.ace.fundtown.model.VipProcessRecord;
import com.huacainfo.ace.fundtown.model.VipPublicity;
import com.huacainfo.ace.fundtown.service.VipDepartmentService;
import com.huacainfo.ace.fundtown.service.VipMemberResService;
import com.huacainfo.ace.fundtown.service.VipProcessRecordService;
import com.huacainfo.ace.fundtown.service.VipPublicityService;
import com.huacainfo.ace.fundtown.vo.VipDepartmentQVo;
import com.huacainfo.ace.fundtown.vo.VipDepartmentVo;
import com.huacainfo.ace.fundtown.vo.VipProcessRecordVo;
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

@Service("vipDepartmentService")
public class VipDepartmentServiceImpl implements VipDepartmentService {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private VipDepartmentDao vipDepartmentDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private VipMemberResService vipMemberResService;
    @Autowired
    private VipProcessRecordService vipProcessRecordService;
    @Autowired
    private VipMemberResDao vipMemberResDao;
    @Autowired
    private VipProcessRecordDao vipProcessRecordDao;
    @Autowired
    private VipPublicityService vipPublicityService;

    @Override
    public PageResult<VipDepartmentVo> findDepartmentList(VipDepartmentQVo condition,
                                                          int start, int limit, String orderBy) throws Exception {
        PageResult<VipDepartmentVo> rst = new PageResult<>();
        List<VipDepartmentVo> list = vipDepartmentDao.findDepartmentList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = vipDepartmentDao.findDepartmentCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public MessageResponse insertDepartment(VipDepartment o, UserProp userProp) throws Exception {

        o.setSyid(userProp.getActiveSyId());
        o.setRegDate(new Date());
        o.setCreateTime(new Date());
        o.setStatus("1");
        if (CommonUtils.isBlank(o.getParentDepartmentId())) {
            return new MessageResponse(1, "所属协会不能为空！");
        }
        if (CommonUtils.isBlank(o.getDepartmentName())) {
            return new MessageResponse(1, "企业名称不能为空！");
        }
        int t = vipDepartmentDao.isExit(o);
        if (t > 0) {
            //return new MessageResponse(1, "已存在的部门名称！");
        }

        vipDepartmentDao.insertDepartment(o);
        dataBaseLogService.log("添加新机构", "机构",
                "企业名称：" + o.getDepartmentName()
                        + ",营业执照号：" + o.getBussLicenseNo()
                        + ",企业编号：" + o.getDepartmentId(), "", o.getDepartmentName(), userProp);
        return new MessageResponse(0, "添加新机构完成！");
    }

    @Override
    public MessageResponse updateDepartment(VipDepartment o, UserProp userProp)
            throws Exception {

        if (CommonUtils.isBlank(o.getDepartmentId())) {
            return new MessageResponse(1, "机构编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getDepartmentName())) {
            return new MessageResponse(1, "机构名称不能为空！");
        }
        o.setLastModifyUserId(userProp.getUserId());
        o.setLastModifyTime(new Date());
        vipDepartmentDao.updateDepartmentByPrimaryKey(o);
        dataBaseLogService.log("机构信息变更", "机构", "",
                "企业名称：" + o.getDepartmentName()
                        + ",营业执照号：" + o.getBussLicenseNo()
                        + ",企业编号：" + o.getDepartmentId(), o.getDepartmentName() + "," + o.getDepartmentId(), userProp);
        return new MessageResponse(0, "机构信息变更完成！");
    }


    @Override
    public SingleResult<VipDepartmentVo> selectDepartmentByPrimaryKey(String departmentId) throws Exception {
        SingleResult<VipDepartmentVo> rst = new SingleResult<>();
        VipDepartmentVo departmentVo = vipDepartmentDao.selectDepartmentVoByPrimaryKey(departmentId);
        rst.setValue(departmentVo);
        rst.setStatus(0);
        return rst;
    }


    @Override
    public MessageResponse delDepartmentByPrimaryKey(String departmentId, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(departmentId)) {
            return new MessageResponse(1, "机构编号不能为空！");
        }
        vipDepartmentDao.delDepartmentByPrimaryKey(departmentId);
        dataBaseLogService.log("机构删除", "机构", "", "", departmentId,
                userProp);
        return new MessageResponse(0, "机构删除完成！");
    }

    /**
     * 入驻申请
     *
     * @param vipVo  入驻申请数据
     *               Demo:{"contactEmail":"30123@qq.com","contactMobile":"18570629027","departmentName":"华彩伟业"}
     * @param openId 入驻用户身份识别ID
     * @return 入驻申请结果
     */
    @Override
    public ResultResponse vipApply(String openId, VipDepartmentVo vipVo) throws Exception {
        Userinfo userinfo = userinfoService.findByOpenId(openId, PropertyUtil.getProperty("appid"));
        if (null == userinfo) {
            return new ResultResponse(ResultCode.FAIL, "查询微信授权信息失败");
        }
        //create portal.department
        VipDepartment vip = new VipDepartment();
        vip.setDepartmentId(GUIDUtil.getGUID());
        vip.setDepartmentName(vipVo.getDepartmentName());//名称
        vip.setContactMobile(vipVo.getContactMobile());//邮箱
        vip.setContactEmail(vipVo.getContactEmail());//号码
        vip.setSyid("fundtown");
//        vip.setAreaCode(userProp.getAreaCode());
        vip.setParentDepartmentId("0");
        vip.setStatus("1");//1-申请中 2-vip
        vip.setType("0");
        int insertCount = vipDepartmentDao.insertDepartment(vip);
        if (insertCount <= 0) {
            return new ResultResponse(ResultCode.FAIL, "企业信息注册失败");
        }
        //更新userinfo手机号码
        UserProp userProp = new UserProp();
        userProp.setUserId("0000");
        userProp.setName("system");
        userinfo.setMobile(vipVo.getContactMobile());
        MessageResponse rs = userinfoService.updateUserinfo(userinfo, userProp);
        if (rs.getStatus() != 0) {
            return new ResultResponse(rs);
        }
        //流程记录
        vipProcessRecordService.insertVipProcessRecord(vip, "ALL");


        return new ResultResponse(ResultCode.SUCCESS, "企业注册成功");

    }

    /**
     * 获取我的vip信息
     *
     * @param openId 调试时使用，调接口时可以不用理会此参数
     * @return
     */
    @Override
    public ResultResponse getMyVipInfo(String openId) {
        Userinfo userinfo = userinfoService.findByOpenId(openId, PropertyUtil.getProperty("appid"));
        if (null == userinfo) {
            return new ResultResponse(ResultCode.FAIL, "查询微信授权信息失败");
        }
        VipDepartmentVo vo = vipDepartmentDao.findByMobile(userinfo.getMobile());
//        if (null == vo) {
//            return new ResultResponse(ResultCode.FAIL, "查询企业信息失败");
//        }
        //response
        Map<String, Object> data = new HashMap<>();
        data.put("nickName", userinfo.getNickname());
        data.put("headimgUrl", userinfo.getHeadimgurl());
        data.put("deptId", null == vo ? "" : vo.getDepartmentId());
        data.put("deptName", null == vo ? "" : vo.getDepartmentName());
        data.put("mobile", null == vo ? "" : vo.getContactMobile());
        data.put("email", null == vo ? "" : vo.getContactEmail());
        data.put("vipStatus", null == vo ? "1" : vo.getStatus());

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", data);
    }

    /**
     * 通过手机号码获取部门信息
     *
     * @param mobile portal.department.contact_mobile
     * @return
     */
    @Override
    public VipDepartment findByMobile(String mobile) {
        return vipDepartmentDao.findByMobile(mobile);
    }

    /**
     * 获取我的视频信息
     *
     * @param deptId
     * @return
     */
    @Override
    public ResultResponse getMyVideo(String deptId) {
        List<VipMemberRes> list = vipMemberResService.findByDeptId(deptId, new String[]{"0"});
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", list);
    }

    /**
     * 获取我的流程处理信息
     *
     * @param deptId
     * @return
     */
    @Override
    public ResultResponse getMyProcess(String deptId) {
        List<VipProcessRecordVo> list = vipProcessRecordService.findByDeptId(deptId);
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", list);
    }


    /**
     * vip会员资源文件上传
     *
     * @param deptId   企业ID
     * @param fileName 文件名称
     * @param fileSize 文件大小
     * @param fileUrl  文件资源地址
     * @param category 附件类型 0-视频，1-其他类型文件
     */
    @Override
    public VipMemberRes insertVipMemberRes(String deptId, String category,
                                           String fileName, long fileSize, String fileUrl) {

        VipMemberRes res = new VipMemberRes();
        res.setId(GUIDUtil.getGUID());
        res.setDeptId(deptId);
        res.setCategory(category);
        res.setResName(fileName);
        res.setResSize(fileSize + "");
        res.setResUrl(fileUrl);
        res.setStatus("1");
        res.setCreateUserId("00001");
        res.setCreateUserName("system");
        res.setCreateDate(DateUtil.getNowDate());
        res.setLastModifyDate(DateUtil.getNowDate());

        vipMemberResDao.insertSelective(res);
        return res;
    }

    /**
     * 信息公示
     *
     * @param deptId      企业ID
     * @param curUserProp
     * @return
     */
    @Override
    public MessageResponse publicity(String deptId, UserProp curUserProp) throws Exception {
        VipPublicity vipPublicity = vipPublicityService.getVipInfo(deptId);
        if (null == vipPublicity) {
            vipPublicity = new VipPublicity();
            vipPublicity.setDeptId(deptId);
            return vipPublicityService.insertVipPublicity(vipPublicity, curUserProp);
        }

        return new MessageResponse(ResultCode.SUCCESS, "公示成功");
    }

    /**
     * 企业入驻流程审核
     *
     * @param deptId       企业ID
     * @param nodeId       流程节点ID
     * @param auditResult  审核结果 审核结果 0-待审核 1-审核通过，2-审核拒绝
     * @param auditOpinion 审核意见
     * @param curUserProp
     * @return
     */
    @Override
    public MessageResponse audit(String deptId, String nodeId,
                                 String auditResult, String auditOpinion, UserProp curUserProp) {
        VipDepartment vip = vipDepartmentDao.selectDepartmentVoByPrimaryKey(deptId);
        if (vip == null) {
            return new MessageResponse(ResultCode.FAIL, "企业资料丢失");
        }

        VipProcessRecord record = vipProcessRecordService.findRecord(deptId, nodeId);
        if (null != record) {
            record.setAuditResult(auditResult);
            record.setAuditDate(DateUtil.getNowDate());
            record.setAuditOpinion(auditOpinion);
            record.setLastModifyDate(DateUtil.getNowDate());
            vipProcessRecordDao.updateByPrimaryKeySelective(record);
        } else {
            record = new VipProcessRecord();
            record.setId(GUIDUtil.getGUID());
            record.setDeptId(deptId);
            record.setNodeId(nodeId);
            record.setAuditResult(auditResult);
            record.setAuditDate(DateUtil.getNowDate());
            record.setAuditOpinion(auditOpinion);
            record.setLastModifyDate(DateUtil.getNowDate());
            record.setStatus("1");
            record.setCreateDate(DateUtil.getNowDate());
            record.setCreateUserId("0000001");
            record.setCreateUserName("system");
            vipProcessRecordDao.insert(record);
        }
        //update vip info
        vip.setStatus("2");
        vip.setLastModifyTime(DateUtil.getNowDate());
        vipDepartmentDao.updateDepartmentByPrimaryKey(vip);

        return new MessageResponse(ResultCode.SUCCESS, "审核成功");
    }
}

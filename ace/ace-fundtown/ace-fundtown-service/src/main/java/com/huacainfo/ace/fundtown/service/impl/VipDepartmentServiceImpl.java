package com.huacainfo.ace.fundtown.service.impl;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.fundtown.dao.VipDepartmentDao;
import com.huacainfo.ace.fundtown.model.VipDepartment;
import com.huacainfo.ace.fundtown.service.VipDepartmentService;
import com.huacainfo.ace.fundtown.vo.VipDepartmentQVo;
import com.huacainfo.ace.fundtown.vo.VipDepartmentVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("vipDepartmentService")
public class VipDepartmentServiceImpl implements VipDepartmentService {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private VipDepartmentDao departmentDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    @Override
    public PageResult<VipDepartmentVo> findDepartmentList(VipDepartmentQVo condition,
                                                          int start, int limit, String orderBy) throws Exception {
        PageResult<VipDepartmentVo> rst = new PageResult<>();
        List<VipDepartmentVo> list = departmentDao.findDepartmentList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = departmentDao.findDepartmentCount(condition);
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
        o.setSyid(userProp.getActiveSyId());
        if (CommonUtils.isBlank(o.getParentDepartmentId())) {
            return new MessageResponse(1, "所属协会不能为空！");
        }
        if (CommonUtils.isBlank(o.getDepartmentName())) {
            return new MessageResponse(1, "企业名称不能为空！");
        }
        int t = departmentDao.isExit(o);
        if (t > 0) {
            //return new MessageResponse(1, "已存在的部门名称！");
        }

        departmentDao.insertDepartment(o);
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
        departmentDao.updateDepartmentByPrimaryKey(o);
        dataBaseLogService.log("机构信息变更", "机构", "",
                "企业名称：" + o.getDepartmentName()
                        + ",营业执照号：" + o.getBussLicenseNo()
                        + ",企业编号：" + o.getDepartmentId(), o.getDepartmentName() + "," + o.getDepartmentId(), userProp);
        return new MessageResponse(0, "机构信息变更完成！");
    }


    @Override
    public SingleResult<VipDepartmentVo> selectDepartmentByPrimaryKey(String departmentId) throws Exception {
        SingleResult<VipDepartmentVo> rst = new SingleResult<>();
        VipDepartmentVo departmentVo = departmentDao.selectDepartmentVoByPrimaryKey(departmentId);
        rst.setValue(departmentVo);
        rst.setStatus(0);
        return rst;
    }


    @Override
    public MessageResponse delDepartmentByPrimaryKey(String departmentId, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(departmentId)) {
            return new MessageResponse(1, "机构编号不能为空！");
        }
        departmentDao.delDepartmentByPrimaryKey(departmentId);
        dataBaseLogService.log("机构删除", "机构", "", "", departmentId,
                userProp);
        return new MessageResponse(0, "机构删除完成！");
    }

    /**
     * 入驻申请
     *
     * @param vipVo  入驻申请数据
     * @param openId 入驻用户身份识别ID
     * @return 入驻申请结果
     */
    @Override
    public ResultResponse vipApply(String openId, VipDepartmentVo vipVo) {
        return null;
    }
}

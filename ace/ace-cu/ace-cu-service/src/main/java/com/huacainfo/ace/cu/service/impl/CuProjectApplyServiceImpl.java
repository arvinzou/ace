package com.huacainfo.ace.cu.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.PropertyUtil;
import com.huacainfo.ace.cu.common.constant.ProjectConstant;
import com.huacainfo.ace.cu.dao.CuProjectApplyDao;
import com.huacainfo.ace.cu.dao.CuProjectApplyResDao;
import com.huacainfo.ace.cu.dao.CuProjectDao;
import com.huacainfo.ace.cu.model.CuProject;
import com.huacainfo.ace.cu.model.CuProjectApply;
import com.huacainfo.ace.cu.model.CuProjectApplyRes;
import com.huacainfo.ace.cu.service.CuProcessRecordService;
import com.huacainfo.ace.cu.service.CuProjectApplyService;
import com.huacainfo.ace.cu.service.CuUserService;
import com.huacainfo.ace.cu.vo.CuProjectApplyQVo;
import com.huacainfo.ace.cu.vo.CuProjectApplyVo;
import com.huacainfo.ace.cu.vo.CuUserVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("cuProjectApplyService")
/**
 * @author: Arvin
 * @version: 2018-06-14
 * @Description: TODO(救急难申请表)
 */
public class CuProjectApplyServiceImpl implements CuProjectApplyService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CuProjectApplyDao cuProjectApplyDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private CuUserService cuUserService;
    @Autowired
    private CuProjectApplyResDao cuProjectApplyResDao;
    @Autowired
    private CuProjectDao cuProjectDao;
    @Autowired
    private CuProcessRecordService cuProcessRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(救急难申请表分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CuProjectApplyVo>
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public PageResult<CuProjectApplyVo> findCuProjectApplyList(CuProjectApplyQVo condition, int start,
                                                               int limit, String orderBy) throws Exception {
        PageResult<CuProjectApplyVo> rst = new PageResult<>();
        List<CuProjectApplyVo> list = this.cuProjectApplyDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.cuProjectApplyDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCuProjectApply
     * @Description: TODO(添加救急难申请表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public MessageResponse insertCuProjectApply(CuProjectApply o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getApplyOpenId())) {
            return new MessageResponse(1, "申请人微信openId不能为空！");
        }
        if (CommonUtils.isBlank(o.getTargetAmount())) {
            return new MessageResponse(1, "目标金额不能为空！");
        }
        if (CommonUtils.isBlank(o.getRealName())) {
            return new MessageResponse(1, "受益人真实姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getMobileNumber())) {
            return new MessageResponse(1, "受益人联系号码不能为空！");
        }
        if (CommonUtils.isBlank(o.getIdCard())) {
            return new MessageResponse(1, "受益人身份证号码不能为空！");
        }
        if (CommonUtils.isBlank(o.getDescription())) {
            return new MessageResponse(1, "求助说明不能为空！");
        }
        CuUserVo user = cuUserService.findByOpenId(o.getApplyOpenId());
        if (null == user) {
            return new MessageResponse(1, "申请人信息有误！");
        }

        o.setApplyUserId(user.getId());
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(DateUtil.getNowDate());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setLastModifyDate(DateUtil.getNowDate());
        this.cuProjectApplyDao.insertSelective(o);
        this.dataBaseLogService.log("添加救急难申请表", "救急难申请表", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加救急难申请表完成！");
    }

    /**
     * @throws
     * @Title:updateCuProjectApply
     * @Description: TODO(更新救急难申请表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public MessageResponse updateCuProjectApply(CuProjectApply o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getApplyUserId())) {
            return new MessageResponse(1, "申请人ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getApplyOpenId())) {
            return new MessageResponse(1, "申请人微信openId不能为空！");
        }
        if (CommonUtils.isBlank(o.getTargetAmount())) {
            return new MessageResponse(1, "目标金额不能为空！");
        }
        if (CommonUtils.isBlank(o.getRealName())) {
            return new MessageResponse(1, "受益人真实姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getMobileNumber())) {
            return new MessageResponse(1, "受益人联系号码不能为空！");
        }
        if (CommonUtils.isBlank(o.getIdCard())) {
            return new MessageResponse(1, "受益人身份证号码不能为空！");
        }
        if (CommonUtils.isBlank(o.getDescription())) {
            return new MessageResponse(1, "求助说明不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.cuProjectApplyDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更救急难申请表", "救急难申请表", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更救急难申请表完成！");
    }

    /**
     * @throws
     * @Title:selectCuProjectApplyByPrimaryKey
     * @Description: TODO(获取救急难申请表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CuProjectApply>
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public SingleResult<CuProjectApplyVo> selectCuProjectApplyByPrimaryKey(String id) throws Exception {
        SingleResult<CuProjectApplyVo> rst = new SingleResult<>();
        rst.setValue(this.cuProjectApplyDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCuProjectApplyByCuProjectApplyId
     * @Description: TODO(删除救急难申请表)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public MessageResponse deleteCuProjectApplyByCuProjectApplyId(String id, UserProp userProp) throws Exception {
        this.cuProjectApplyDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除救急难申请表", "救急难申请表",
                String.valueOf(id),
                String.valueOf(id), "救急难申请表", userProp);
        return new MessageResponse(0, "救急难申请表删除完成！");
    }

    /**
     * 救急难 - 提交申请资料
     *
     * @param vo 参考 CuProjectApplyVo.java对象
     * @return
     * @throws Exception
     */
    @Override
    public ResultResponse applyProject(CuProjectApplyVo vo) {
        CuUserVo user = cuUserService.findByOpenId(vo.getApplyOpenId());
        if (null == user) {
            return new ResultResponse(ResultCode.FAIL, "申请人信息有误！");
        }
        //插入申请主表
        String applyId = GUIDUtil.getGUID();
        vo.setId(applyId);
        vo.setApplyUserId(user.getId());
        vo.setCreateDate(DateUtil.getNowDate());
        vo.setStatus("1");
        vo.setCreateUserName("system");
        vo.setCreateUserId("0000-0000");
        vo.setLastModifyDate(DateUtil.getNowDate());
        cuProjectApplyDao.insertSelective(vo);
        //添加附件资料
        String resUrl;
        for (CuProjectApplyRes res : vo.getResList()) {
            res.setApplyId(applyId);
            res.setId(GUIDUtil.getGUID());
            //图片地址处理
            resUrl = res.getResUrl();
            if (!resUrl.startsWith("http")) {
                resUrl = PropertyUtil.getProperty("fastdfs_server") + resUrl;
                res.setResUrl(resUrl);
            }
            res.setCreateDate(DateUtil.getNowDate());
            res.setStatus("1");
            res.setCreateUserName("system");
            res.setCreateUserId("0000-0000");
            res.setLastModifyDate(DateUtil.getNowDate());
            cuProjectApplyResDao.insertSelective(res);
        }
        //流程记录
        cuProcessRecordService.recordSubmit(vo);

        return new ResultResponse(ResultCode.SUCCESS, "添加成功", vo);
    }


    /**
     * 功能描述:  "救急难"审核
     *
     * @param id
     * @param auditResult
     * @param auditOpinion
     * @param curUserProp
     * @param: id cu_project_apply.id
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/8 18:19
     */
    @Override
    public MessageResponse audit(String id, String auditResult, String auditOpinion,
                                 UserProp curUserProp) throws Exception {
        CuProjectApply apply = cuProjectApplyDao.selectByPrimaryKey(id);
        if (null == apply) {
            return new MessageResponse(ResultCode.FAIL, "申请资料丢失");
        }
        //审核通过，生成“未审核项目”
        if ("0".equals(auditResult)) {
            CuProject project = new CuProject();
            project.setProjectName(apply.getTitle());
            project.setTitle(apply.getTitle());
            project.setDescription(apply.getDescription());
            project.setType(ProjectConstant.P_TYPE_PERSONAL);
            project.setStartDate(DateUtil.getNowDate());
            project.setEndDate(DateUtil.getNowDate());
            //
            project.setId(GUIDUtil.getGUID());
            project.setParentId("0");
            project.setStatus("1");
            project.setCreateDate(DateUtil.getNowDate());
            project.setCreateUserName(curUserProp.getName());
            project.setCreateUserId(curUserProp.getUserId());
            project.setLastModifyDate(DateUtil.getNowDate());
            project.setTargetAmount(apply.getTargetAmount());

            cuProjectDao.insertSelective(project);
            apply.setProjectId(project.getId());
        }
        String status = "0".equals(auditResult.trim()) ?
                ProjectConstant.P_STATUS_PASSED : ProjectConstant.P_STATUS_REJECTED;
        apply.setStatus(status);
        apply.setLastModifyDate(DateUtil.getNowDate());
        apply.setLastModifyUserId(curUserProp.getUserId());
        apply.setLastModifyUserName(curUserProp.getName());
        cuProjectApplyDao.updateByPrimaryKeySelective(apply);

        //流程记录
        cuProcessRecordService.recordAccept(auditResult, auditOpinion, apply);

        return new MessageResponse(ResultCode.SUCCESS, "审核完成");
    }

    /**
     * 功能描述: 查询救助资料列表
     *
     * @param applyId
     * @param resTypes
     * @param curUserProp
     * @param: resTypes ","分割多种类型 0-身份证正面 1-身份证反面 2-其他证明图片
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/6/27 17:16
     */
    @Override
    public List<CuProjectApplyRes> findResList(String applyId, String resTypes, UserProp curUserProp) {
        return cuProjectApplyResDao.findListByTypes(applyId, resTypes.split(","));
    }

}

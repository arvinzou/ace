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
import com.huacainfo.ace.cu.common.constant.ProcessRecordConstant;
import com.huacainfo.ace.cu.common.constant.ProjectConstant;
import com.huacainfo.ace.cu.dao.CuProcessRecordDao;
import com.huacainfo.ace.cu.dao.CuProjectApplyDao;
import com.huacainfo.ace.cu.model.CuProcessRecord;
import com.huacainfo.ace.cu.model.CuProject;
import com.huacainfo.ace.cu.model.CuProjectApply;
import com.huacainfo.ace.cu.service.CuProcessRecordService;
import com.huacainfo.ace.cu.vo.CuProcessRecordQVo;
import com.huacainfo.ace.cu.vo.CuProcessRecordVo;
import com.huacainfo.ace.cu.vo.CuProjectApplyVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("cuProcessRecordService")
/**
 * @author: Arvin
 * @version: 2018-07-05
 * @Description: TODO(救急难流程处理记录)
 */
public class CuProcessRecordServiceImpl implements CuProcessRecordService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CuProcessRecordDao cuProcessRecordDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private CuProjectApplyDao cuProjectApplyDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(救急难流程处理记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CuProcessRecordVo>
     * @author: Arvin
     * @version: 2018-07-05
     */
    @Override
    public PageResult<CuProcessRecordVo> findCuProcessRecordList(CuProcessRecordQVo condition, int start,
                                                                 int limit, String orderBy) throws Exception {
        PageResult
                <CuProcessRecordVo> rst = new PageResult<>();
        List
                <CuProcessRecordVo> list = this.cuProcessRecordDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.cuProcessRecordDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCuProcessRecord
     * @Description: TODO(添加救急难流程处理记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    @Override
    public MessageResponse insertCuProcessRecord(CuProcessRecord o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getApplyUserId())) {
            return new MessageResponse(1, "申请人ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getApplyOpenId())) {
            return new MessageResponse(1, "申请人openId不能为空！");
        }
        if (CommonUtils.isBlank(o.getApplyResId())) {
            return new MessageResponse(1, "申请资料ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getRecordDate())) {
            return new MessageResponse(1, "记录时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        int temp = this.cuProcessRecordDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "救急难流程处理记录名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.cuProcessRecordDao.insertSelective(o);
        this.dataBaseLogService.log("添加救急难流程处理记录", "救急难流程处理记录", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加救急难流程处理记录完成！");
    }

    /**
     * @throws
     * @Title:updateCuProcessRecord
     * @Description: TODO(更新救急难流程处理记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    @Override
    public MessageResponse updateCuProcessRecord(CuProcessRecord o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getApplyUserId())) {
            return new MessageResponse(1, "申请人ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getApplyOpenId())) {
            return new MessageResponse(1, "申请人openId不能为空！");
        }
        if (CommonUtils.isBlank(o.getApplyResId())) {
            return new MessageResponse(1, "申请资料ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getRecordDate())) {
            return new MessageResponse(1, "记录时间不能为空！");
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
        this.cuProcessRecordDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更救急难流程处理记录", "救急难流程处理记录", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更救急难流程处理记录完成！");
    }

    /**
     * @throws
     * @Title:selectCuProcessRecordByPrimaryKey
     * @Description: TODO(获取救急难流程处理记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CuProcessRecord>
     * @author: Arvin
     * @version: 2018-07-05
     */
    @Override
    public SingleResult<CuProcessRecordVo> selectCuProcessRecordByPrimaryKey(String id) throws Exception {
        SingleResult
                <CuProcessRecordVo> rst = new SingleResult<>();
        rst.setValue(this.cuProcessRecordDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCuProcessRecordByCuProcessRecordId
     * @Description: TODO(删除救急难流程处理记录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-05
     */
    @Override
    public MessageResponse deleteCuProcessRecordByCuProcessRecordId(String id, UserProp userProp) throws
            Exception {
        this.cuProcessRecordDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除救急难流程处理记录", "救急难流程处理记录",
                String.valueOf(id),
                String.valueOf(id), "救急难流程处理记录", userProp);
        return new MessageResponse(0, "救急难流程处理记录删除完成！");
    }

    /**
     * 插入流程记录
     *
     * @param userId     cu_user.id
     * @param openId     openid
     * @param resId      cu_project_apply.id
     * @param recordDate 录入时间
     * @return
     */
    @Override
    public ResultResponse insertRecord(String userId, String openId, String resId,
                                       int nodeIndex, String nodeDesc, Date recordDate) {
        CuProcessRecord record = new CuProcessRecord();
        record.setId(GUIDUtil.getGUID());
        record.setApplyUserId(userId);
        record.setApplyOpenId(openId);
        record.setApplyResId(resId);
        record.setNodeIndex(nodeIndex);
        record.setNodeDesc(nodeDesc);
        record.setRecordDate(recordDate);
        record.setStatus("2");
        record.setCreateUserId("system");
        record.setCreateUserName("系统用户");
        record.setCreateDate(DateUtil.getNowDate());
        record.setLastModifyDate(DateUtil.getNowDate());

        cuProcessRecordDao.insertSelective(record);

        return new ResultResponse(ResultCode.SUCCESS, "记录成功", record);
    }

    /**
     * 获取“救急难” 处理详情
     *
     * @param applyId 申请资料ID cu_process_record.id
     * @return
     */
    @Override
    public List<CuProcessRecord> findList(String applyId) {
        return cuProcessRecordDao.findListByApplyId(applyId);
    }

    @Override
    public ResultResponse recordSubmit(CuProjectApplyVo vo) {

        return insertRecord(vo.getApplyUserId(), vo.getApplyOpenId(), vo.getId(),
                ProcessRecordConstant.INDEX_0_SUBMIT, ProcessRecordConstant.DESC_0_SUBMIT,
                DateUtil.getNowDate());
    }

    /**
     * 受理申请
     *
     * @param auditResult  受理结果
     * @param auditOpinion 受理意见
     * @param apply        受理资料
     * @return
     */
    @Override
    public ResultResponse recordAccept(String auditResult, String auditOpinion, CuProjectApply apply) {
        String status = "0".equals(auditResult.trim()) ?
                ProjectConstant.P_STATUS_PASSED : ProjectConstant.P_STATUS_REJECTED;

        CuProcessRecord record = new CuProcessRecord();
        record.setId(GUIDUtil.getGUID());
        record.setApplyUserId(apply.getApplyUserId());
        record.setApplyOpenId(apply.getApplyOpenId());
        record.setApplyResId(apply.getId());
        record.setNodeIndex(ProcessRecordConstant.INDEX_1_ACCEPT);
        record.setNodeDesc(ProcessRecordConstant.DESC_1_ACCEPT);
        record.setRecordDate(DateUtil.getNowDate());
        record.setStatus(status);
        record.setRemark(auditOpinion);
        record.setCreateUserId("system");
        record.setCreateUserName("系统用户");
        record.setCreateDate(DateUtil.getNowDate());
        record.setLastModifyDate(DateUtil.getNowDate());

        cuProcessRecordDao.insertSelective(record);

        return new ResultResponse(ResultCode.SUCCESS, "记录成功", record);
    }

    /**
     * 项目审核
     */
    @Override
    public ResultResponse recordProjectAudit(String auditResult, String auditOpinion, CuProject project) {
        String status = "0".equals(auditResult.trim()) ?
                ProjectConstant.P_STATUS_PASSED : ProjectConstant.P_STATUS_REJECTED;

        CuProjectApply apply = cuProjectApplyDao.findByProjectId(project.getId());
        if (null == apply) {
            return new ResultResponse(ResultCode.FAIL, "申请资料丢失");
        }

        CuProcessRecord record = new CuProcessRecord();
        record.setId(GUIDUtil.getGUID());
        record.setApplyUserId(apply.getApplyUserId());
        record.setApplyOpenId(apply.getApplyOpenId());
        record.setApplyResId(apply.getId());
        record.setNodeIndex(ProcessRecordConstant.INDEX_2_AUDITED);
        record.setNodeDesc(ProcessRecordConstant.DESC_2_AUDITED);
        record.setRecordDate(DateUtil.getNowDate());
        record.setStatus(status);
        record.setRemark(auditOpinion);
        record.setCreateUserId("system");
        record.setCreateUserName("系统用户");
        record.setCreateDate(DateUtil.getNowDate());
        record.setLastModifyDate(DateUtil.getNowDate());
        cuProcessRecordDao.insertSelective(record);

        return new ResultResponse(ResultCode.SUCCESS, "记录成功", record);
    }

    /**
     * 项目上线
     */
    @Override
    public ResultResponse recordProjectSetup(CuProject project) {
        CuProjectApply apply = cuProjectApplyDao.findByProjectId(project.getId());
        if (null == apply) {
            return new ResultResponse(ResultCode.FAIL, "申请资料丢失");
        }

        CuProcessRecord record = new CuProcessRecord();
        record.setId(GUIDUtil.getGUID());
        record.setApplyUserId(apply.getApplyUserId());
        record.setApplyOpenId(apply.getApplyOpenId());
        record.setApplyResId(apply.getId());
        record.setNodeIndex(ProcessRecordConstant.INDEX_3_COLLECTING);
        record.setNodeDesc(ProcessRecordConstant.DESC_3_COLLECTING);
        record.setRecordDate(DateUtil.getNowDate());
        record.setStatus("2");
        record.setRemark("");
        record.setCreateUserId("system");
        record.setCreateUserName("系统用户");
        record.setCreateDate(DateUtil.getNowDate());
        record.setLastModifyDate(DateUtil.getNowDate());
        cuProcessRecordDao.insertSelective(record);

        return new ResultResponse(ResultCode.SUCCESS, "记录成功", record);
    }

    /**
     * 项目关闭
     *
     * @param reason  关闭原因
     * @param project
     */
    @Override
    public ResultResponse recordProjectShutDown(String reason, CuProject project) {
        CuProjectApply apply = cuProjectApplyDao.findByProjectId(project.getId());
        if (null == apply) {
            return new ResultResponse(ResultCode.FAIL, "申请资料丢失");
        }

        CuProcessRecord record = new CuProcessRecord();
        record.setId(GUIDUtil.getGUID());
        record.setApplyUserId(apply.getApplyUserId());
        record.setApplyOpenId(apply.getApplyOpenId());
        record.setApplyResId(apply.getId());
        record.setNodeIndex(ProcessRecordConstant.INDEX_4_END);
        record.setNodeDesc(ProcessRecordConstant.DESC_4_END);
        record.setRecordDate(DateUtil.getNowDate());
        record.setStatus("2");
        record.setRemark(reason);
        record.setCreateUserId("system");
        record.setCreateUserName("系统用户");
        record.setCreateDate(DateUtil.getNowDate());
        record.setLastModifyDate(DateUtil.getNowDate());
        cuProcessRecordDao.insertSelective(record);

        return new ResultResponse(ResultCode.SUCCESS, "记录成功", record);
    }

}

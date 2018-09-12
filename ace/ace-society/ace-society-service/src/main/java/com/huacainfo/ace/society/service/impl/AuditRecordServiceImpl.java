package com.huacainfo.ace.society.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.constant.AuditState;
import com.huacainfo.ace.society.dao.AuditRecordDao;
import com.huacainfo.ace.society.model.AuditRecord;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.vo.AuditRecordQVo;
import com.huacainfo.ace.society.vo.AuditRecordVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("auditRecordService")
/**
 * @author: Arvin
 * @version: 2018-09-11
 * @Description: TODO(审核记录)
 */
public class AuditRecordServiceImpl implements AuditRecordService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AuditRecordDao auditRecordDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(审核记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <AuditRecordVo>
     * @author: Arvin
     * @version: 2018-09-11
     */
    @Override
    public PageResult<AuditRecordVo> findAuditRecordList(AuditRecordQVo condition, int start,
                                                         int limit, String orderBy) throws Exception {
        PageResult
                <AuditRecordVo> rst = new PageResult<>();
        List
                <AuditRecordVo> list = this.auditRecordDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.auditRecordDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertAuditRecord
     * @Description: TODO(添加审核记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse insertAuditRecord(AuditRecord o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getBisType())) {
            return new MessageResponse(1, "业务类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getBisId())) {
            return new MessageResponse(1, "业务编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "提交人ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getAuditState())) {
            return new MessageResponse(1, "审核状态不能为空！");
        }


        int temp = this.auditRecordDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "审核记录名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.auditRecordDao.insertSelective(o);
        this.dataBaseLogService.log("添加审核记录", "审核记录", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加审核记录完成！");
    }

    /**
     * @throws
     * @Title:updateAuditRecord
     * @Description: TODO(更新审核记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse updateAuditRecord(AuditRecord o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getBisType())) {
            return new MessageResponse(1, "业务类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getBisId())) {
            return new MessageResponse(1, "业务编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "提交人ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getAuditState())) {
            return new MessageResponse(1, "审核状态不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.auditRecordDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更审核记录", "审核记录", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更审核记录完成！");
    }


    /**
     * @throws
     * @Title:selectAuditRecordByPrimaryKey
     * @Description: TODO(获取审核记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<AuditRecord>
     * @author: Arvin
     * @version: 2018-09-11
     */
    @Override
    public SingleResult
            <AuditRecordVo> selectAuditRecordByPrimaryKey(String id) throws Exception {
        SingleResult
                <AuditRecordVo> rst = new SingleResult<>();
        rst.setValue(this.auditRecordDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteAuditRecordByAuditRecordId
     * @Description: TODO(删除审核记录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse deleteAuditRecordByAuditRecordId(String id, UserProp userProp) throws
            Exception {
        this.auditRecordDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除审核记录", "审核记录",
                String.valueOf(id),
                String.valueOf(id), "审核记录", userProp);
        return new MessageResponse(0, "审核记录删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核审核记录)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse audit(String bisType, String bisId, String userId, String rst, String remark,
                                 UserProp userProp) throws Exception {
        AuditRecord record = auditRecordDao.findBisType(bisType, bisId, userId);
        if (record == null) {
            return new MessageResponse(ResultCode.FAIL, "审核记录丢失");
        }

        record.setAuditState(rst);
        record.setAudtiRemark(remark);
        record.setLastModifyDate(DateUtil.getNowDate());
        record.setLastModifyUserId(userProp.getUserId());
        record.setLastModifyUserName(userProp.getName());
        auditRecordDao.updateByPrimaryKeySelective(record);


        dataBaseLogService.log("审核审核记录", "审核记录",
                String.valueOf(record.getId()), String.valueOf(record.getId()), "审核记录", userProp);
        return new MessageResponse(0, "审核记录审核完成！");
    }

    /**
     * 提交审核
     *
     * @param bisType 业务类型
     * @param bisId   业务单据ID
     * @param userId  用户ID
     * @return ResultResponse
     */
    @Override
    public ResultResponse submit(String id, String bisType, String bisId, String userId) {
        id = StringUtil.isEmpty(id) ? GUIDUtil.getGUID() : id;
        AuditRecord record = new AuditRecord();
        record.setId(id);
        record.setBisId(bisId);
        record.setBisType(bisType);
        record.setUserId(userId);
        record.setAuditState(AuditState.SUBMIT_AUDIT);
        record.setStatus("1");
        record.setCreateUserId("system");
        record.setCreateUserName("system");
        record.setCreateDate(DateUtil.getNowDate());
        auditRecordDao.insertSelective(record);

        return new ResultResponse(ResultCode.SUCCESS, "成功提交审核");
    }

}

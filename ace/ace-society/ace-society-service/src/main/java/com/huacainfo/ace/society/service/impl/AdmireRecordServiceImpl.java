package com.huacainfo.ace.society.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.constant.BisType;
import com.huacainfo.ace.society.dao.AdmireRecordDao;
import com.huacainfo.ace.society.model.AdmireRecord;
import com.huacainfo.ace.society.service.AdmireRecordService;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.vo.AdmireRecordQVo;
import com.huacainfo.ace.society.vo.AdmireRecordVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("admireRecordService")
/**
 * @author: ArvinZou
 * @version: 2018-10-24
 * @Description: TODO(点赞管理)
 */
public class AdmireRecordServiceImpl implements AdmireRecordService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AdmireRecordDao admireRecordDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private AuditRecordService auditRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(点赞管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <AdmireRecordVo>
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @Override
    public PageResult<AdmireRecordVo> findAdmireRecordList(AdmireRecordQVo condition, int start,
                                                           int limit, String orderBy) throws Exception {
        PageResult<AdmireRecordVo> rst = new PageResult<>();
        List<AdmireRecordVo> list = this.admireRecordDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.admireRecordDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertAdmireRecord
     * @Description: TODO(添加点赞管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @Override
    public MessageResponse insertAdmireRecord(AdmireRecord o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getBisType())) {
            return new MessageResponse(1, "业务类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getBisId())) {
            return new MessageResponse(1, "业务ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "点赞人ID不能为空！");
        }


        int temp = this.admireRecordDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "点赞管理名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.admireRecordDao.insert(o);
        this.dataBaseLogService.log("添加点赞管理", "点赞管理", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加点赞管理完成！");
    }

    /**
     * @throws
     * @Title:updateAdmireRecord
     * @Description: TODO(更新点赞管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @Override
    public MessageResponse updateAdmireRecord(AdmireRecord o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getBisType())) {
            return new MessageResponse(1, "业务类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getBisId())) {
            return new MessageResponse(1, "业务ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "点赞人ID不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.admireRecordDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更点赞管理", "点赞管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更点赞管理完成！");
    }

    /**
     * @throws
     * @Title:selectAdmireRecordByPrimaryKey
     * @Description: TODO(获取点赞管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<AdmireRecord>
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @Override
    public SingleResult<AdmireRecordVo> selectAdmireRecordByPrimaryKey(String id) throws Exception {
        SingleResult<AdmireRecordVo> rst = new SingleResult<>();
        rst.setValue(this.admireRecordDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteAdmireRecordByAdmireRecordId
     * @Description: TODO(删除点赞管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @Override
    public MessageResponse deleteAdmireRecordByAdmireRecordId(String id, UserProp userProp) throws
            Exception {
        this.admireRecordDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除点赞管理", "点赞管理", id, id,
                "点赞管理", userProp);
        return new MessageResponse(0, "点赞管理删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核点赞管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception {

        AdmireRecord obj = admireRecordDao.selectByPrimaryKey(id);
        if (obj == null) {
            return new MessageResponse(ResultCode.FAIL, "点赞管理数据丢失");
        }

        //更改审核记录
        MessageResponse auditRs =
                auditRecordService.audit(BisType.SOCIETY_ORG_INFO,
                        obj.getId(), obj.getId(), rst, remark, userProp);
        if (ResultCode.FAIL == auditRs.getStatus()) {
            return auditRs;
        }

        obj.setStatus(rst);
        obj.setLastModifyDate(DateUtil.getNowDate());
        obj.setLastModifyUserId(userProp.getUserId());
        obj.setLastModifyUserName(userProp.getName());
        admireRecordDao.updateStatus(obj);


        dataBaseLogService.log("审核点赞管理", "点赞管理", id, id, "点赞管理", userProp);
        return new MessageResponse(0, "点赞管理审核完成！");
    }

    /**
     * 提交点赞
     *
     * @param params 表单参数 ：   bisType  bisId ;
     * @return ResultResponse
     * @throws Exception
     */
    @Override
    public ResultResponse submit(AdmireRecordVo params) {

        params.setId(GUIDUtil.getGUID());
        params.setStatus("1");
        params.setCreateUserId("system");
        params.setCreateUserName("system");
        params.setCreateDate(new Date());
        admireRecordDao.insert(params);

        return new ResultResponse(ResultCode.SUCCESS, "点赞成功");
    }

    /**
     * 取消点赞
     * <p>
     * 表单参数 ：   bisType  bisId ;
     *
     * @param bisType
     * @param bisId
     * @param userId  可选  @return ResultResponse
     * @throws Exception
     */
    @Override
    public ResultResponse cancelAdmire(String bisType, String bisId, String userId) {

        admireRecordDao.cancelAdmire(bisType, bisId, userId);
        return new ResultResponse(ResultCode.SUCCESS, "取消成功");
    }

    /**
     * 获取点赞数量
     * <p>
     * 表单参数 ：   bisType  bisId ;
     *
     * @param bisType
     * @param bisId
     * @return ResultResponse
     * @throws Exception
     */
    @Override
    public int getAdmireNum(String bisType, String bisId) {
        return admireRecordDao.getAdmireNum(bisType, bisId);
    }

}

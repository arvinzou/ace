package com.huacainfo.ace.society.service.impl;


import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.society.constant.BisType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.society.dao.ActivityDetailDao;
import com.huacainfo.ace.society.model.ActivityDetail;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.service.ActivityDetailService;
import com.huacainfo.ace.society.vo.ActivityDetailVo;
import com.huacainfo.ace.society.vo.ActivityDetailQVo;

@Service("activityDetailService")
/**
 * @author: huacai003
 * @version: 2018-09-13
 * @Description: TODO(活动报道)
 */
public class ActivityDetailServiceImpl implements ActivityDetailService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ActivityDetailDao activityDetailDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private AuditRecordService auditRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(活动报道分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ActivityDetailVo>
     * @author: huacai003
     * @version: 2018-09-13
     */
    @Override
    public PageResult
            <ActivityDetailVo> findActivityDetailList(ActivityDetailQVo condition, int start,
                                                      int limit, String orderBy) throws Exception {
        PageResult
                <ActivityDetailVo> rst = new PageResult<>();
        List
                <ActivityDetailVo> list = this.activityDetailDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.activityDetailDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertActivityDetail
     * @Description: TODO(添加活动报道)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-13
     */
    @Override
    public MessageResponse insertActivityDetail(ActivityDetail o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键-GUID不能为空！");
        }
        if (CommonUtils.isBlank(o.getActivityId())) {
            return new MessageResponse(1, "活动编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "用户编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getIdentity())) {
            return new MessageResponse(1, "参与身份 1 - 志愿者 2 - 参与者不能为空！");
        }


        int temp = this.activityDetailDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "活动报道名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.activityDetailDao.insertSelective(o);
        this.dataBaseLogService.log("添加活动报道", "活动报道", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加活动报道完成！");
    }

    /**
     * @throws
     * @Title:updateActivityDetail
     * @Description: TODO(更新活动报道)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-13
     */
    @Override
    public MessageResponse updateActivityDetail(ActivityDetail o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键-GUID不能为空！");
        }
        if (CommonUtils.isBlank(o.getActivityId())) {
            return new MessageResponse(1, "活动编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "用户编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getIdentity())) {
            return new MessageResponse(1, "参与身份 1 - 志愿者 2 - 参与者不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.activityDetailDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更活动报道", "活动报道", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更活动报道完成！");
    }

    /**
     * @throws
     * @Title:selectActivityDetailByPrimaryKey
     * @Description: TODO(获取活动报道)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ActivityDetail>
     * @author: huacai003
     * @version: 2018-09-13
     */
    @Override
    public SingleResult<ActivityDetailVo> selectActivityDetailByPrimaryKey(String id) throws Exception {
        SingleResult<ActivityDetailVo> rst = new SingleResult<>();
        rst.setValue(this.activityDetailDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:selectActivityDetailByPrimaryKey
     * @Description: TODO(获取活动报道)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ActivityDetail>
     * @author: huacai003
     * @version: 2018-09-13
     */
    @Override
    public ResultResponse personalActivitydetails(String activityId,String unionId) throws Exception {
        if(CommonUtils.isBlank(activityId)){
            return new ResultResponse(ResultCode.FAIL,"缺少参数");
        }
        if(CommonUtils.isBlank(unionId)){
            return new ResultResponse(ResultCode.FAIL,"缺少参数");
        }
        ActivityDetailVo  activityDetailVo=this.activityDetailDao.selectPersonaldetails(activityId,unionId);
        return new ResultResponse(ResultCode.SUCCESS,"获取成功",activityDetailVo);
    }

    /**
     * @throws
     * @Title:deleteActivityDetailByActivityDetailId
     * @Description: TODO(删除活动报道)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-13
     */
    @Override
    public MessageResponse deleteActivityDetailByActivityDetailId(String id, UserProp userProp) throws
            Exception {
        this.activityDetailDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除活动报道", "活动报道",
                String.valueOf(id),
                String.valueOf(id), "活动报道", userProp);
        return new MessageResponse(0, "活动报道删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核活动报道)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-13
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception {

        ActivityDetail obj = activityDetailDao.selectByPrimaryKey(id);
        if (obj == null) {
            return new MessageResponse(ResultCode.FAIL, "活动报道数据丢失");
        }

        //更改审核记录
        MessageResponse auditRs = auditRecordService.audit(BisType.SOCIETY_ORG_INFO, obj.getId(), obj.getId(), rst, remark, userProp);
        if (ResultCode.FAIL == auditRs.getStatus()) {
            return auditRs;
        }

        obj.setStatus(rst);
        obj.setLastModifyDate(DateUtil.getNowDate());
        obj.setLastModifyUserId(userProp.getUserId());
        obj.setLastModifyUserName(userProp.getName());
        activityDetailDao.updateByPrimaryKeySelective(obj);


        dataBaseLogService.log("审核活动报道", "活动报道",
                String.valueOf(id), String.valueOf(id), "活动报道", userProp);
        return new MessageResponse(0, "活动报道审核完成！");
    }

}

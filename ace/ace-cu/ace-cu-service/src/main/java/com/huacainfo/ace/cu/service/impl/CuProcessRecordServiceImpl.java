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
import com.huacainfo.ace.cu.dao.CuProcessRecordDao;
import com.huacainfo.ace.cu.model.CuProcessRecord;
import com.huacainfo.ace.cu.service.CuProcessRecordService;
import com.huacainfo.ace.cu.vo.CuProcessRecordQVo;
import com.huacainfo.ace.cu.vo.CuProcessRecordVo;
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
        record.setStatus("1");
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

}

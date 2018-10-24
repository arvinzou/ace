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
import com.huacainfo.ace.society.dao.CommentRecordDao;
import com.huacainfo.ace.society.model.CommentRecord;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.service.CommentRecordService;
import com.huacainfo.ace.society.vo.CommentRecordQVo;
import com.huacainfo.ace.society.vo.CommentRecordVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("commentRecordService")
/**
 * @author: ArvinZou
 * @version: 2018-10-24
 * @Description: TODO(评论管理)
 */
public class CommentRecordServiceImpl implements CommentRecordService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CommentRecordDao commentRecordDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private AuditRecordService auditRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评论管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CommentRecordVo>
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @Override
    public PageResult<CommentRecordVo> findCommentRecordList(CommentRecordQVo condition, int start,
                                                             int limit, String orderBy) throws Exception {
        PageResult<CommentRecordVo> rst = new PageResult<>();
        List<CommentRecordVo> list = this.commentRecordDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.commentRecordDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCommentRecord
     * @Description: TODO(添加评论管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @Override
    public MessageResponse insertCommentRecord(CommentRecord o, UserProp userProp) throws Exception {

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
            return new MessageResponse(1, "评论人ID不能为空！");
        }


        int temp = this.commentRecordDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "评论管理名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.commentRecordDao.insert(o);
        this.dataBaseLogService.log("添加评论管理", "评论管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加评论管理完成！");
    }

    /**
     * @throws
     * @Title:updateCommentRecord
     * @Description: TODO(更新评论管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @Override
    public MessageResponse updateCommentRecord(CommentRecord o, UserProp userProp) throws Exception {
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
            return new MessageResponse(1, "评论人ID不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.commentRecordDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更评论管理", "评论管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更评论管理完成！");
    }

    /**
     * @throws
     * @Title:selectCommentRecordByPrimaryKey
     * @Description: TODO(获取评论管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CommentRecord>
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @Override
    public SingleResult<CommentRecordVo> selectCommentRecordByPrimaryKey(String id) throws Exception {
        SingleResult<CommentRecordVo> rst = new SingleResult<>();
        rst.setValue(this.commentRecordDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCommentRecordByCommentRecordId
     * @Description: TODO(删除评论管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @Override
    public MessageResponse deleteCommentRecordByCommentRecordId(String id, UserProp userProp) throws
            Exception {
        this.commentRecordDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除评论管理", "评论管理", id, id,
                "评论管理", userProp);
        return new MessageResponse(0, "评论管理删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核评论管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2018-10-24
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {

        CommentRecord obj = commentRecordDao.selectByPrimaryKey(id);
        if (obj == null) {
            return new MessageResponse(ResultCode.FAIL, "评论管理数据丢失");
        }

        //更改审核记录
        MessageResponse auditRs =
                auditRecordService.audit(BisType.SOCIETY_ORG_INFO, obj.getId(), obj.getUserId(),
                        rst, remark, userProp);
        if (ResultCode.FAIL == auditRs.getStatus()) {
            return auditRs;
        }

        obj.setStatus(rst);
        obj.setLastModifyDate(DateUtil.getNowDate());
        obj.setLastModifyUserId(userProp.getUserId());
        obj.setLastModifyUserName(userProp.getName());
        commentRecordDao.updateStatus(obj);


        dataBaseLogService.log("审核评论管理", "评论管理", id, id, "评论管理", userProp);
        return new MessageResponse(0, "评论管理审核完成！");
    }

    /**
     * 提交评论
     *
     * @param params 表单参数 ：   bisType  bisId   content;
     * @return
     * @throws Exception
     */
    @Override
    public ResultResponse submit(CommentRecordVo params) {

        params.setId(GUIDUtil.getGUID());
        params.setStatus("1");
        params.setCreateUserId("system");
        params.setCreateUserName("system");
        params.setCreateDate(new Date());
        commentRecordDao.insert(params);

        return new ResultResponse(ResultCode.SUCCESS, "评论成功");
    }

}

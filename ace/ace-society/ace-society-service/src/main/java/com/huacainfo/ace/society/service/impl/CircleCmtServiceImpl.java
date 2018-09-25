package com.huacainfo.ace.society.service.impl;


import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.constant.ResultCode;
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
import com.huacainfo.ace.society.dao.CircleCmtDao;
import com.huacainfo.ace.society.model.CircleCmt;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.service.CircleCmtService;
import com.huacainfo.ace.society.vo.CircleCmtVo;
import com.huacainfo.ace.society.vo.CircleCmtQVo;

@Service("circleCmtService")
/**
 * @author: 陈晓克
 * @version: 2018-09-20
 * @Description: TODO(评论)
 */
public class CircleCmtServiceImpl implements CircleCmtService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CircleCmtDao circleCmtDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private AuditRecordService auditRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评论分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CircleCmtVo>
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @Override
    public PageResult<CircleCmtVo> findCircleCmtList(CircleCmtQVo condition, int start,
                                            int limit, String orderBy) throws Exception {
        PageResult<CircleCmtVo> rst = new PageResult<>();
        List<CircleCmtVo> list = this.circleCmtDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.circleCmtDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCircleCmt
     * @Description: TODO(添加评论)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @Override
    public MessageResponse insertCircleCmt(CircleCmt o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCircleId())) {
            return new MessageResponse(1, "圈子编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getUid())) {
            return new MessageResponse(1, "用户编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getCreateTime())) {
            return new MessageResponse(1, "入库日期不能为空！");
        }


        o.setId(GUIDUtil.getGUID());
        o.setStatus("1");
        this.circleCmtDao.insert(o);
        this.dataBaseLogService.log("添加评论", "评论", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加评论完成！");
    }

    /**
     * @throws
     * @Title:updateCircleCmt
     * @Description: TODO(更新评论)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @Override
    public MessageResponse updateCircleCmt(CircleCmt o, UserProp userProp) throws Exception {

        this.dataBaseLogService.log("变更评论", "评论", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更评论完成！");
    }

    /**
     * @throws
     * @Title:selectCircleCmtByPrimaryKey
     * @Description: TODO(获取评论)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CircleCmt>
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @Override
    public SingleResult
            <CircleCmtVo> selectCircleCmtByPrimaryKey(String id) throws Exception {
        SingleResult
                <CircleCmtVo> rst = new SingleResult<>();
        rst.setValue(this.circleCmtDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCircleCmtByCircleCmtId
     * @Description: TODO(删除评论)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @Override
    public MessageResponse deleteCircleCmtByCircleCmtId(String id, UserProp userProp) throws
            Exception {
        this.circleCmtDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除评论", "评论", id, id,
                "评论", userProp);
        return new MessageResponse(0, "评论删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核评论)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        this.circleCmtDao.updateStatus(id,rst);

        dataBaseLogService.log("审核评论", "评论", id, id,
                "评论", userProp);
        return new MessageResponse(0, "成功！");
    }

}

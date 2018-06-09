package com.huacainfo.ace.portal.service.impl;


import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.dao.EvaluatCmtDao;
import com.huacainfo.ace.portal.model.EvaluatCmt;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.EvaluatCmtService;
import com.huacainfo.ace.portal.vo.EvaluatCmtVo;
import com.huacainfo.ace.portal.vo.EvaluatCmtQVo;

@Service("evaluatCmtService")
/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description: TODO(评测评论)
 */
public class EvaluatCmtServiceImpl implements EvaluatCmtService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvaluatCmtDao evaluatCmtDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评测评论分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<EvaluatCmtVo>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public PageResult<EvaluatCmtVo> findEvaluatCmtList(EvaluatCmtQVo condition, int start,
                                                       int limit, String orderBy) throws Exception {
        PageResult<EvaluatCmtVo> rst = new PageResult<EvaluatCmtVo>();
        List<EvaluatCmtVo> list = this.evaluatCmtDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.evaluatCmtDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertEvaluatCmt
     * @Description: TODO(添加评测评论)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse insertEvaluatCmt(EvaluatCmt o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvaluatTplId())) {
            return new MessageResponse(1, "所属评测模板不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "评测人不能为空！");
        }
        if (CommonUtils.isBlank(o.getScoreA())) {
            return new MessageResponse(1, "题目易懂性不能为空！");
        }
        if (CommonUtils.isBlank(o.getScoreB())) {
            return new MessageResponse(1, "结果准确性不能为空！");
        }
        if (CommonUtils.isBlank(o.getScoreC())) {
            return new MessageResponse(1, "建议实用性不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        int temp = this.evaluatCmtDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "评测评论名称重复！");
        }
        o.setCreateDate(new Date());
        this.evaluatCmtDao.insert(o);
        return new MessageResponse(0, "添加评测评论完成！");
    }

    /**
     * @throws
     * @Title:updateEvaluatCmt
     * @Description: TODO(更新评测评论)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse updateEvaluatCmt(EvaluatCmt o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvaluatTplId())) {
            return new MessageResponse(1, "所属评测模板不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "评测人不能为空！");
        }
        if (CommonUtils.isBlank(o.getScoreA())) {
            return new MessageResponse(1, "题目易懂性不能为空！");
        }
        if (CommonUtils.isBlank(o.getScoreB())) {
            return new MessageResponse(1, "结果准确性不能为空！");
        }
        if (CommonUtils.isBlank(o.getScoreC())) {
            return new MessageResponse(1, "建议实用性不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        this.evaluatCmtDao.updateByPrimaryKey(o);
        return new MessageResponse(0, "变更评测评论完成！");
    }

    /**
     * @throws
     * @Title:selectEvaluatCmtByPrimaryKey
     * @Description: TODO(获取评测评论)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluatCmt>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public SingleResult<EvaluatCmtVo> selectEvaluatCmtByPrimaryKey(String id) throws Exception {
        SingleResult<EvaluatCmtVo> rst = new SingleResult<EvaluatCmtVo>();
        rst.setValue(this.evaluatCmtDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteEvaluatCmtByEvaluatCmtId
     * @Description: TODO(删除评测评论)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse deleteEvaluatCmtByEvaluatCmtId(String id,
                                                          UserProp userProp) throws Exception {
        this.evaluatCmtDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除评测评论", "评测评论", String.valueOf(id),
                String.valueOf(id), "评测评论", userProp);
        return new MessageResponse(0, "评测评论删除完成！");
    }

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(获取评测评论列表)
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public Map<String, Object> getList(Map<String, Object> params) throws Exception {
        Map<String, Object> rst = new HashMap<>();
        rst.put("status", 0);
        rst.put("data", this.evaluatCmtDao.getList(params));
        return rst;
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取评测评论)
     * @param: @param id
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public Map<String, Object> getById(String id) throws Exception {
        Map<String, Object> rst = new HashMap<>();
        rst.put("status", 0);
        rst.put("data", this.evaluatCmtDao.getById(id));
        return rst;
    }
}

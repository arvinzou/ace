package com.huacainfo.ace.partyschool.service.impl;


import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.partyschool.dao.EvaluationIndexDao;
import com.huacainfo.ace.partyschool.model.EvaluationIndex;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.partyschool.service.EvaluationIndexService;
import com.huacainfo.ace.partyschool.vo.EvaluationIndexVo;
import com.huacainfo.ace.partyschool.vo.EvaluationIndexQVo;

@Service("evaluationIndexService")
/**
 * @author: 王恩
 * @version: 2019-01-04
 * @Description: TODO(评测选项)
 */
public class EvaluationIndexServiceImpl implements EvaluationIndexService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvaluationIndexDao evaluationIndexDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评测选项分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <EvaluationIndexVo>
     * @author: 王恩
     * @version: 2019-01-04
     */
    @Override
    public PageResult<EvaluationIndexVo> findEvaluationIndexList(EvaluationIndexQVo condition, int start,
                                                        int limit, String orderBy) throws Exception {
        PageResult<EvaluationIndexVo> rst = new PageResult<>();
        List<EvaluationIndexVo> list = this.evaluationIndexDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.evaluationIndexDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertEvaluationIndex
     * @Description: TODO(添加评测选项)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-04
     */
    @Override
    public MessageResponse insertEvaluationIndex(EvaluationIndex o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvaluatingId())) {
            return new MessageResponse(1, "所属评测方案不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntroduce())) {
            return new MessageResponse(1, "所属指标内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "指标名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getScore())) {
            return new MessageResponse(1, "分值不能为空！");
        }
        this.evaluationIndexDao.insert(o);
        this.dataBaseLogService.log("添加评测选项", "评测选项", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加评测选项完成！");
    }

    /**
     * @throws
     * @Title:updateEvaluationIndex
     * @Description: TODO(更新评测选项)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-04
     */
    @Override
    public MessageResponse updateEvaluationIndex(EvaluationIndex o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvaluatingId())) {
            return new MessageResponse(1, "所属评测方案不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "指标名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getScore())) {
            return new MessageResponse(1, "分值不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntroduce())) {
            return new MessageResponse(1, "所属指标内容不能为空！");
        }
        this.evaluationIndexDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更评测选项", "评测选项", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更评测选项完成！");
    }

    /**
     * @throws
     * @Title:selectEvaluationIndexByPrimaryKey
     * @Description: TODO(获取评测选项)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluationIndex>
     * @author: 王恩
     * @version: 2019-01-04
     */
    @Override
    public SingleResult
            <EvaluationIndexVo> selectEvaluationIndexByPrimaryKey(String id) throws Exception {
        SingleResult
                <EvaluationIndexVo> rst = new SingleResult<>();
        rst.setValue(this.evaluationIndexDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteEvaluationIndexByEvaluationIndexId
     * @Description: TODO(删除评测选项)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-04
     */
    @Override
    public MessageResponse deleteEvaluationIndexByEvaluationIndexId(String id, UserProp userProp) throws
            Exception {
        this.evaluationIndexDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除评测选项", "评测选项", id, id,
                "评测选项", userProp);
        return new MessageResponse(0, "评测选项删除完成！");
    }


}

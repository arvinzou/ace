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
import com.huacainfo.ace.partyschool.dao.EvaluationRstContentDao;
import com.huacainfo.ace.partyschool.model.EvaluationRstContent;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.partyschool.service.EvaluationRstContentService;
import com.huacainfo.ace.partyschool.vo.EvaluationRstContentVo;
import com.huacainfo.ace.partyschool.vo.EvaluationRstContentQVo;

@Service("evaluationRstContentService")
/**
 * @author: 王恩
 * @version: 2019-01-08
 * @Description: TODO(测评结果管理)
 */
public class EvaluationRstContentServiceImpl implements EvaluationRstContentService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvaluationRstContentDao evaluationRstContentDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(测评结果管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <EvaluationRstContentVo>
     * @author: 王恩
     * @version: 2019-01-08
     */
    @Override
    public PageResult
            <EvaluationRstContentVo> findEvaluationRstContentList(EvaluationRstContentQVo condition, int start,
                                                                  int limit, String orderBy) throws Exception {
        PageResult
                <EvaluationRstContentVo> rst = new PageResult<>();
        List
                <EvaluationRstContentVo> list = this.evaluationRstContentDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.evaluationRstContentDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertEvaluationRstContent
     * @Description: TODO(添加测评结果管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-08
     */
    @Override
    public MessageResponse insertEvaluationRstContent(EvaluationRstContent o, UserProp userProp) throws Exception {

        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getClassScheduleId())) {
            return new MessageResponse(1, "所属课程不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())&&CommonUtils.isBlank(o.getJudge())) {
            return new MessageResponse(1, "建议内容不能为空！");
        }
        o.setUserId(userProp.getUserId());
        o.setCreateDate(new Date());
        this.evaluationRstContentDao.insert(o);
        this.dataBaseLogService.log("添加测评结果管理", "测评结果管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加测评结果管理完成！");
    }

    /**
     * @throws
     * @Title:updateEvaluationRstContent
     * @Description: TODO(更新测评结果管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-08
     */
    @Override
    public MessageResponse updateEvaluationRstContent(EvaluationRstContent o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getClassScheduleId())) {
            return new MessageResponse(1, "所属课程不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "建议内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "评测人不能为空！");
        }
        this.evaluationRstContentDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更测评结果管理", "测评结果管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更测评结果管理完成！");
    }

    /**
     * @throws
     * @Title:selectEvaluationRstContentByPrimaryKey
     * @Description: TODO(获取测评结果管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluationRstContent>
     * @author: 王恩
     * @version: 2019-01-08
     */
    @Override
    public SingleResult
            <EvaluationRstContentVo> selectEvaluationRstContentByPrimaryKey(String id) throws Exception {
        SingleResult
                <EvaluationRstContentVo> rst = new SingleResult<>();
        rst.setValue(this.evaluationRstContentDao.selectVoByPrimaryKey(id));
        return rst;
    }
    @Override
    public SingleResult<EvaluationRstContent> selectEvaluationRstContent(EvaluationRstContent o) throws Exception {
        SingleResult<EvaluationRstContent> rst = new SingleResult<>();
        rst.setValue(this.evaluationRstContentDao.selectEvaluationRstContent(o));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteEvaluationRstContentByEvaluationRstContentId
     * @Description: TODO(删除测评结果管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-08
     */
    @Override
    public MessageResponse deleteEvaluationRstContentByEvaluationRstContentId(String id, UserProp userProp) throws
            Exception {
        this.evaluationRstContentDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除测评结果管理", "测评结果管理", id, id,
                "测评结果管理", userProp);
        return new MessageResponse(0, "测评结果管理删除完成！");
    }

}

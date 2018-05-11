package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.dao.FopQuestionnaireResultDao;
import com.huacainfo.ace.fop.model.FopQuestionnaireResult;
import com.huacainfo.ace.fop.service.FopQuestionnaireResultService;
import com.huacainfo.ace.fop.vo.FopQuestionnaireResultQVo;
import com.huacainfo.ace.fop.vo.FopQuestionnaireResultVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("fopQuestionnaireResultService")
/**
 * @author: Arvin
 * @version: 2018-05-11
 * @Description: (满意度调查)
 */
public class FopQuestionnaireResultServiceImpl implements FopQuestionnaireResultService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopQuestionnaireResultDao fopQuestionnaireResultDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: (满意度调查分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FopQuestionnaireResultVo>
     * @author: Arvin
     * @version: 2018-05-11
     */
    @Override
    public PageResult<FopQuestionnaireResultVo> findFopQuestionnaireResultList(FopQuestionnaireResultQVo condition, int start,
                                                                               int limit, String orderBy) throws Exception {
        PageResult<FopQuestionnaireResultVo> rst = new PageResult<>();
        List<FopQuestionnaireResultVo> list = this.fopQuestionnaireResultDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopQuestionnaireResultDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopQuestionnaireResult
     * @Description: (添加满意度调查)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    @Override
    public MessageResponse insertFopQuestionnaireResult(FopQuestionnaireResult o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getRelationId())) {
            return new MessageResponse(1, "关联ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationType())) {
            return new MessageResponse(1, "关联类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getAnswerId())) {
            return new MessageResponse(1, "答题人ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getAnswerType())) {
            return new MessageResponse(1, "答题人类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getQuestionnaireId())) {
            return new MessageResponse(1, "问卷ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getResult())) {
            return new MessageResponse(1, "调查结果不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        int temp = this.fopQuestionnaireResultDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "满意度调查名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopQuestionnaireResultDao.insertSelective(o);
        this.dataBaseLogService.log("添加满意度调查", "满意度调查", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加满意度调查完成！");
    }

    /**
     * @throws
     * @Title:updateFopQuestionnaireResult
     * @Description: (更新满意度调查)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    @Override
    public MessageResponse updateFopQuestionnaireResult(FopQuestionnaireResult o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationId())) {
            return new MessageResponse(1, "关联ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationType())) {
            return new MessageResponse(1, "关联类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getAnswerId())) {
            return new MessageResponse(1, "答题人ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getAnswerType())) {
            return new MessageResponse(1, "答题人类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getQuestionnaireId())) {
            return new MessageResponse(1, "问卷ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getResult())) {
            return new MessageResponse(1, "调查结果不能为空！");
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
        this.fopQuestionnaireResultDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更满意度调查", "满意度调查", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更满意度调查完成！");
    }

    /**
     * @throws
     * @Title:selectFopQuestionnaireResultByPrimaryKey
     * @Description: (获取满意度调查)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopQuestionnaireResult>
     * @author: Arvin
     * @version: 2018-05-11
     */
    @Override
    public SingleResult<FopQuestionnaireResultVo> selectVoByPrimaryKey(String id) throws Exception {
        SingleResult<FopQuestionnaireResultVo> rst = new SingleResult<>();
        rst.setValue(this.fopQuestionnaireResultDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopQuestionnaireResultByFopQuestionnaireResultId
     * @Description: (删除满意度调查)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    @Override
    public MessageResponse deleteByPrimaryKey(String id, UserProp userProp) throws Exception {
        this.fopQuestionnaireResultDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除满意度调查", "满意度调查", id, id, "满意度调查", userProp);
        return new MessageResponse(0, "满意度调查删除完成！");
    }

}

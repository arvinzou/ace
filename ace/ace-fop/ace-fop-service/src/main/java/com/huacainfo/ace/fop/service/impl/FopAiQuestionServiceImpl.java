package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.dao.FopAiQuestionDao;
import com.huacainfo.ace.fop.model.FopAiQuestion;
import com.huacainfo.ace.fop.service.FopAiQuestionService;
import com.huacainfo.ace.fop.vo.FopAiQuestionQVo;
import com.huacainfo.ace.fop.vo.FopAiQuestionVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("fopAiQuestionService")
/**
 * @author: Arvin
 * @version: 2018-05-11
 * @Description: (AI客服)
 */
public class FopAiQuestionServiceImpl implements FopAiQuestionService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopAiQuestionDao fopAiQuestionDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: (AI客服分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FopAiQuestionVo>
     * @author: Arvin
     * @version: 2018-05-11
     */
    @Override
    public PageResult<FopAiQuestionVo> findFopAiQuestionList(FopAiQuestionQVo condition, int start,
                                                             int limit, String orderBy) throws Exception {
        PageResult<FopAiQuestionVo> rst = new PageResult<>();
        List<FopAiQuestionVo> list = this.fopAiQuestionDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopAiQuestionDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopAiQuestion
     * @Description: (添加AI客服)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    @Override
    public MessageResponse insertFopAiQuestion(FopAiQuestion o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getQuestion())) {
            return new MessageResponse(1, "问题不能为空！");
        }
        if (CommonUtils.isBlank(o.getAnswer())) {
            return new MessageResponse(1, "答案不能为空！");
        }

        int temp = this.fopAiQuestionDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "AI题库名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setAccCount((long) 1);
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setLastModifyDate(DateUtil.getNowDate());
        this.fopAiQuestionDao.insertSelective(o);
        this.dataBaseLogService.log("添加AI客服", "AI客服", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加AI客服完成！");
    }

    /**
     * @throws
     * @Title:updateFopAiQuestion
     * @Description: (更新AI客服)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    @Override
    public MessageResponse updateFopAiQuestion(FopAiQuestion o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getQuestion())) {
            return new MessageResponse(1, "问题不能为空！");
        }
        if (CommonUtils.isBlank(o.getAnswer())) {
            return new MessageResponse(1, "答案不能为空！");
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
        this.fopAiQuestionDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更AI客服", "AI客服", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更AI客服完成！");
    }

    /**
     * @throws
     * @Title:selectFopAiQuestionByPrimaryKey
     * @Description: (获取AI客服)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopAiQuestion>
     * @author: Arvin
     * @version: 2018-05-11
     */
    @Override
    public SingleResult<FopAiQuestionVo> selectFopAiQuestionByPrimaryKey(String id) throws Exception {
        SingleResult<FopAiQuestionVo> rst = new SingleResult<>();
        rst.setValue(this.fopAiQuestionDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopAiQuestionByFopAiQuestionId
     * @Description: (删除AI客服)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    @Override
    public MessageResponse deleteFopAiQuestionByFopAiQuestionId(String id, UserProp userProp) throws Exception {
        FopAiQuestion fopAiQuestion = fopAiQuestionDao.selectByPrimaryKey(id);
        if (null == fopAiQuestion) {
            return new MessageResponse(ResultCode.FAIL, "记录丢失");
        }

        fopAiQuestion.setStatus("0");
        fopAiQuestion.setLastModifyDate(new Date());
        fopAiQuestion.setLastModifyUserName(userProp.getName());
        fopAiQuestion.setLastModifyUserId(userProp.getUserId());
        fopAiQuestionDao.updateByPrimaryKeySelective(fopAiQuestion);
        dataBaseLogService.log("删除AI客服", "AI客服", id, id, "AI客服", userProp);
        return new MessageResponse(0, "AI客服删除完成！");
    }

}

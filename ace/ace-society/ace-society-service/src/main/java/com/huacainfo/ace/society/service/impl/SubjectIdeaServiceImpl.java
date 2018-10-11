package com.huacainfo.ace.society.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.constant.AuditState;
import com.huacainfo.ace.society.constant.BisType;
import com.huacainfo.ace.society.dao.SubjectIdeaAnnexDao;
import com.huacainfo.ace.society.dao.SubjectIdeaDao;
import com.huacainfo.ace.society.model.SubjectIdea;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.service.SubjectIdeaService;
import com.huacainfo.ace.society.vo.SubjectIdeaAnnexQVo;
import com.huacainfo.ace.society.vo.SubjectIdeaAnnexVo;
import com.huacainfo.ace.society.vo.SubjectIdeaQVo;
import com.huacainfo.ace.society.vo.SubjectIdeaVo;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service("subjectIdeaService")
/**
 * @author: Arvin
 * @version: 2018-09-13
 * @Description: TODO(议题点子)
 */
public class SubjectIdeaServiceImpl implements SubjectIdeaService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SubjectIdeaDao subjectIdeaDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private AuditRecordService auditRecordService;
    @Autowired
    private SubjectIdeaAnnexDao subjectIdeaAnnexDao;
    @Autowired
    private SqlSessionTemplate sqlSession;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(议题点子分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SubjectIdeaVo>
     * @author: Arvin
     * @version: 2018-09-13
     */
    @Override
    public PageResult<SubjectIdeaVo> findSubjectIdeaList(SubjectIdeaQVo condition, int start, int limit, String orderBy) throws Exception {
        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);
        SubjectIdeaDao dao = session.getMapper(SubjectIdeaDao.class);
        PageResult<SubjectIdeaVo> rst = new PageResult<>();
        try {
            List<SubjectIdeaVo> list = dao.findList(condition, start, limit, orderBy);
            rst.setRows(list);
            if (start <= 1) {
                int allRows = dao.findCount(condition);
                rst.setTotal(allRows);
            }
        } catch (Exception e) {
            session.close();
        } finally {
            session.close();
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertSubjectIdea
     * @Description: TODO(添加议题点子)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @Override
    public MessageResponse insertSubjectIdea(SubjectIdea o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键-GUID不能为空！");
        }
        if (CommonUtils.isBlank(o.getSubjectId())) {
            return new MessageResponse(1, "议题编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "提交人ID不能为空！");
        }


        int temp = this.subjectIdeaDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "议题点子名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.subjectIdeaDao.insertSelective(o);
        this.dataBaseLogService.log("添加议题点子", "议题点子", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加议题点子完成！");
    }

    /**
     * @throws
     * @Title:updateSubjectIdea
     * @Description: TODO(更新议题点子)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @Override
    public MessageResponse updateSubjectIdea(SubjectIdea o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键-GUID不能为空！");
        }
        if (CommonUtils.isBlank(o.getSubjectId())) {
            return new MessageResponse(1, "议题编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "提交人ID不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.subjectIdeaDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更议题点子", "议题点子", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更议题点子完成！");
    }

    /**
     * @throws
     * @Title:selectSubjectIdeaByPrimaryKey
     * @Description: TODO(获取议题点子)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SubjectIdea>
     * @author: Arvin
     * @version: 2018-09-13
     */
    @Override
    public SingleResult<SubjectIdeaVo> selectSubjectIdeaByPrimaryKey(String id) throws Exception {
        SingleResult<SubjectIdeaVo> rst = new SingleResult<>();
        SubjectIdeaVo subjectIdeaVo = this.subjectIdeaDao.selectVoByPrimaryKey(id);

        SubjectIdeaAnnexQVo annexQVo = new SubjectIdeaAnnexQVo();
        annexQVo.setId(id);
        List<SubjectIdeaAnnexVo> listAnnex = subjectIdeaAnnexDao.findList(annexQVo, 0, 99, null);
        subjectIdeaVo.setListSubjectIdeaAnnexVo(listAnnex);
        rst.setValue(subjectIdeaVo);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteSubjectIdeaBySubjectIdeaId
     * @Description: TODO(删除议题点子)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @Override
    public MessageResponse deleteSubjectIdeaBySubjectIdeaId(String id, UserProp userProp) throws
            Exception {
        this.subjectIdeaDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除议题点子", "议题点子",
                String.valueOf(id),
                String.valueOf(id), "议题点子", userProp);
        return new MessageResponse(0, "议题点子删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核议题点子)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {

        SubjectIdea obj = subjectIdeaDao.selectByPrimaryKey(id);
        if (obj == null) {
            return new MessageResponse(ResultCode.FAIL, "议题点子数据丢失");
        }

        //更改审核记录
        MessageResponse auditRs =
                auditRecordService.audit(BisType.SUBJECT_IDEA,
                        obj.getId(), obj.getId(), rst, remark, userProp);
        if (ResultCode.FAIL == auditRs.getStatus()) {
            return auditRs;
        }

        obj.setStatus(rst);
        obj.setLastModifyDate(DateUtil.getNowDate());
        obj.setLastModifyUserId(userProp.getUserId());
        obj.setLastModifyUserName(userProp.getName());
        subjectIdeaDao.updateByPrimaryKeySelective(obj);


        dataBaseLogService.log("审核议题点子", "议题点子",
                String.valueOf(id), String.valueOf(id), "议题点子", userProp);
        return new MessageResponse(0, "议题点子审核完成！");
    }

    /**
     * 提交“我的点子”
     *
     * @param params 表单参数
     * @return ResultResponse
     * @throws Exception
     */
    @Override
    public ResultResponse submit(SubjectIdeaVo params) {

        if (StringUtil.isEmpty(params.getUserId())) {
            return new ResultResponse(ResultCode.FAIL, "提交人ID不能为空");
        }
        if (StringUtil.isEmpty(params.getTitle())) {
            return new ResultResponse(ResultCode.FAIL, "标题不能为空");
        }
        if (StringUtil.isEmpty(params.getSolution())) {
            return new ResultResponse(ResultCode.FAIL, "解决方案不能为空");
        }
        String ideaId = GUIDUtil.getGUID();
        params.setId(ideaId);
        params.setSubjectId("0");
        params.setStatus("1");
        params.setCreateDate(new Date());
        params.setCreateUserName("system");
        params.setCreateUserId("8888");
        //附件保存
        if (!CollectionUtils.isEmpty(params.getListSubjectIdeaAnnexVo())) {
            for (SubjectIdeaAnnexVo annex : params.getListSubjectIdeaAnnexVo()) {
                annex.setId(GUIDUtil.getGUID());
                annex.setIdeaId(ideaId);
                annex.setStatus("1");
                annex.setCreateDate(new Date());
                annex.setCreateUserName("system");
                annex.setCreateUserId("8888");
                subjectIdeaAnnexDao.insert(annex);
            }
        }
        subjectIdeaDao.insert(params);

        return new ResultResponse(ResultCode.SUCCESS, "提交成功");
    }

    /**
     * “我的点子” 送审
     *
     * @param ideaId  点子ID
     * @param unionId
     * @return ResultResponse
     * @throws Exception
     */
    @Override
    public ResultResponse sendAudit(String ideaId, String unionId) {
        SubjectIdea idea = subjectIdeaDao.selectByPrimaryKey(ideaId);
        if (null == idea) {
            return new ResultResponse(ResultCode.FAIL, "“点子”数据丢失");
        }
        //
        if (AuditState.SUBMIT_AUDIT.equals(idea.getStatus())) {
            return new ResultResponse(ResultCode.FAIL, "请勿重复送审");
        }
        //提交审核
        auditRecordService.submit(GUIDUtil.getGUID(), BisType.SUBJECT_IDEA, ideaId, unionId);
        //更新单据状态
        SubjectIdea record = new SubjectIdea();
        record.setId(ideaId);
        record.setStatus(AuditState.SUBMIT_AUDIT);
        record.setLastModifyDate(DateUtil.getNowDate());
        record.setLastModifyUserId("system");
        record.setLastModifyUserId("8888");
        subjectIdeaDao.updateStatus(record);

        return new ResultResponse(ResultCode.SUCCESS, "送审成功");
    }

    /**
     * 获取点子详情
     *
     * @param ideaId 点子ID
     * @return ResultResponse
     * @throws Exception
     */
    @Override
    public ResultResponse getIdeaDetail(String ideaId) throws Exception {
        SubjectIdeaVo ideaVo = selectSubjectIdeaByPrimaryKey(ideaId).getValue();
        if (ideaVo == null) {
            return new ResultResponse(ResultCode.FAIL, "数据丢失");
        }


        return new ResultResponse(ResultCode.SUCCESS, "查询成功", ideaVo);
    }

}

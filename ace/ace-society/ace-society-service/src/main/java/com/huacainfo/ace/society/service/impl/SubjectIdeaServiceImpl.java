package com.huacainfo.ace.society.service.impl;


import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.society.constant.BisType;
import com.huacainfo.ace.society.dao.SubjectIdeaAnnexDao;
import com.huacainfo.ace.society.service.SubjectIdeaAnnexService;
import com.huacainfo.ace.society.vo.SubjectIdeaAnnexQVo;
import com.huacainfo.ace.society.vo.SubjectIdeaAnnexVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.society.dao.SubjectIdeaDao;
import com.huacainfo.ace.society.model.SubjectIdea;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.service.SubjectIdeaService;
import com.huacainfo.ace.society.vo.SubjectIdeaVo;
import com.huacainfo.ace.society.vo.SubjectIdeaQVo;
@Service("subjectIdeaService")
/**
* @author: Arvin
* @version: 2018-09-13
* @Description:  TODO(议题点子)
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

/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(议题点子分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<SubjectIdeaVo>
    * @throws
    * @author: Arvin
    * @version: 2018-09-13
    */
    @Override
    public PageResult
    <SubjectIdeaVo> findSubjectIdeaList(SubjectIdeaQVo condition, int start,
        int limit, String orderBy) throws Exception {
        PageResult
        <SubjectIdeaVo> rst = new PageResult<>();
            List
            <SubjectIdeaVo> list = this.subjectIdeaDao.findList(condition,
                start, limit, orderBy);
                rst.setRows(list);
                if (start <= 1) {
                int allRows = this.subjectIdeaDao.findCount(condition);
                rst.setTotal(allRows);
                }
                return rst;
                }

                /**
                *
                * @Title:insertSubjectIdea
                * @Description: TODO(添加议题点子)
                * @param: @param o
                * @param: @param userProp
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
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
                o.getId(),o.getId(), userProp);

                return new MessageResponse(0, "添加议题点子完成！");
                }

                /**
                *
                * @Title:updateSubjectIdea
                * @Description: TODO(更新议题点子)
                * @param: @param o
                * @param: @param userProp
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
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
                *
                * @Title:selectSubjectIdeaByPrimaryKey
                * @Description: TODO(获取议题点子)
                * @param: @param id
                * @param: @throws Exception
                * @return: SingleResult<SubjectIdea>
                * @throws
                * @author: Arvin
                * @version: 2018-09-13
                */
                @Override
                public SingleResult
                <SubjectIdeaVo> selectSubjectIdeaByPrimaryKey(String id) throws Exception {
                        SingleResult<SubjectIdeaVo> rst = new SingleResult<>();
                        SubjectIdeaVo subjectIdeaVo = this.subjectIdeaDao.selectVoByPrimaryKey(id);

                        SubjectIdeaAnnexQVo annexQVo = new SubjectIdeaAnnexQVo();
                        annexQVo.setId(id);
                        List<SubjectIdeaAnnexVo> listAnnex = subjectIdeaAnnexDao.findList(annexQVo,0, 99, null);
                        subjectIdeaVo.setListSubjectIdeaAnnexVo(listAnnex);
                        rst.setValue(subjectIdeaVo);
                        return rst;
                    }

                        /**
                        *
                        * @Title:deleteSubjectIdeaBySubjectIdeaId
                        * @Description: TODO(删除议题点子)
                        * @param: @param id
                        * @param: @param userProp
                        * @param: @throws Exception
                        * @return: MessageResponse
                        * @throws
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
                        *
                        * @Title:audit
                        * @Description: TODO(审核议题点子)
                        * @param: @param id bean.id
                        * @param: @param rst 审核结果 3-通过 4-拒绝
                        * @param: @param remark 审核备注
                        * @param: @throws Exception
                        * @return: MessageResponse
                        * @throws
                        * @author: Arvin
                        * @version: 2018-09-13
                        */
                        @Override
                        public MessageResponse audit(String id,String rst, String remark,
                        UserProp userProp) throws Exception {

                        SubjectIdea obj = subjectIdeaDao.selectByPrimaryKey(id);
                        if (obj == null) {
                        return new MessageResponse(ResultCode.FAIL, "议题点子数据丢失");
                        }

                        //更改审核记录
                        MessageResponse auditRs =
                        auditRecordService.audit(BisType.SUBJECT_IDEA, obj.getId(), obj.getId(), rst, remark,
                        userProp);
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

                        }

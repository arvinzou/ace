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
import com.huacainfo.ace.society.dao.SubjectIdeaAnnexDao;
import com.huacainfo.ace.society.model.SubjectIdeaAnnex;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.service.SubjectIdeaAnnexService;
import com.huacainfo.ace.society.vo.SubjectIdeaAnnexVo;
import com.huacainfo.ace.society.vo.SubjectIdeaAnnexQVo;
@Service("subjectIdeaAnnexService")
/**
* @author: Arvin
* @version: 2018-09-13
* @Description:  TODO(议题点子附件)
*/
public class SubjectIdeaAnnexServiceImpl implements SubjectIdeaAnnexService {
Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
private SubjectIdeaAnnexDao subjectIdeaAnnexDao;
@Autowired
private DataBaseLogService dataBaseLogService;
@Autowired
private AuditRecordService auditRecordService;

/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(议题点子附件分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<SubjectIdeaAnnexVo>
    * @throws
    * @author: Arvin
    * @version: 2018-09-13
    */
    @Override
    public PageResult
    <SubjectIdeaAnnexVo> findSubjectIdeaAnnexList(SubjectIdeaAnnexQVo condition, int start,
        int limit, String orderBy) throws Exception {
        PageResult
        <SubjectIdeaAnnexVo> rst = new PageResult<>();
            List
            <SubjectIdeaAnnexVo> list = this.subjectIdeaAnnexDao.findList(condition,
                start, limit, orderBy);
                rst.setRows(list);
                if (start <= 1) {
                int allRows = this.subjectIdeaAnnexDao.findCount(condition);
                rst.setTotal(allRows);
                }
                return rst;
                }

                /**
                *
                * @Title:insertSubjectIdeaAnnex
                * @Description: TODO(添加议题点子附件)
                * @param: @param o
                * @param: @param userProp
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
                * @author: Arvin
                * @version: 2018-09-13
                */
                @Override
                public MessageResponse insertSubjectIdeaAnnex(SubjectIdeaAnnex o, UserProp userProp) throws Exception {

                if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键-GUID不能为空！");
}
if (CommonUtils.isBlank(o.getIdeaId())) {
return new MessageResponse(1, "议题点子编号不能为空！");
}


                int temp = this.subjectIdeaAnnexDao.isExit(o);
                if (temp > 0) {
                return new MessageResponse(1, "议题点子附件名称重复！");
                }

                o.setId(GUIDUtil.getGUID());
                o.setCreateDate(new Date());
                o.setStatus("1");
                o.setCreateUserName(userProp.getName());
                o.setCreateUserId(userProp.getUserId());
                this.subjectIdeaAnnexDao.insertSelective(o);
                this.dataBaseLogService.log("添加议题点子附件", "议题点子附件", "",
                o.getId(),o.getId(), userProp);

                return new MessageResponse(0, "添加议题点子附件完成！");
                }

                /**
                *
                * @Title:updateSubjectIdeaAnnex
                * @Description: TODO(更新议题点子附件)
                * @param: @param o
                * @param: @param userProp
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
                * @author: Arvin
                * @version: 2018-09-13
                */
                @Override
                public MessageResponse updateSubjectIdeaAnnex(SubjectIdeaAnnex o, UserProp userProp) throws Exception {
                if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键-GUID不能为空！");
}
if (CommonUtils.isBlank(o.getIdeaId())) {
return new MessageResponse(1, "议题点子编号不能为空！");
}


                o.setLastModifyDate(new Date());
                o.setLastModifyUserName(userProp.getName());
                o.setLastModifyUserId(userProp.getUserId());
                this.subjectIdeaAnnexDao.updateByPrimaryKeySelective(o);
                this.dataBaseLogService.log("变更议题点子附件", "议题点子附件", "",
                o.getId(), o.getId(), userProp);

                return new MessageResponse(0, "变更议题点子附件完成！");
                }

                /**
                *
                * @Title:selectSubjectIdeaAnnexByPrimaryKey
                * @Description: TODO(获取议题点子附件)
                * @param: @param id
                * @param: @throws Exception
                * @return: SingleResult<SubjectIdeaAnnex>
                * @throws
                * @author: Arvin
                * @version: 2018-09-13
                */
                @Override
                public SingleResult
                <SubjectIdeaAnnexVo> selectSubjectIdeaAnnexByPrimaryKey(String id) throws Exception {
                    SingleResult
                    <SubjectIdeaAnnexVo> rst = new SingleResult<>();
                        rst.setValue(this.subjectIdeaAnnexDao.selectVoByPrimaryKey(id));
                        return rst;
                        }

                        /**
                        *
                        * @Title:deleteSubjectIdeaAnnexBySubjectIdeaAnnexId
                        * @Description: TODO(删除议题点子附件)
                        * @param: @param id
                        * @param: @param userProp
                        * @param: @throws Exception
                        * @return: MessageResponse
                        * @throws
                        * @author: Arvin
                        * @version: 2018-09-13
                        */
                        @Override
                        public MessageResponse deleteSubjectIdeaAnnexBySubjectIdeaAnnexId(String id, UserProp userProp) throws
                        Exception {
                        this.subjectIdeaAnnexDao.deleteByPrimaryKey(id);
                        this.dataBaseLogService.log("删除议题点子附件", "议题点子附件",
                        String.valueOf(id),
                        String.valueOf(id), "议题点子附件", userProp);
                        return new MessageResponse(0, "议题点子附件删除完成！");
                        }


                        /**
                        *
                        * @Title:audit
                        * @Description: TODO(审核议题点子附件)
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

                        SubjectIdeaAnnex obj = subjectIdeaAnnexDao.selectByPrimaryKey(id);
                        if (obj == null) {
                        return new MessageResponse(ResultCode.FAIL, "议题点子附件数据丢失");
                        }

                        //更改审核记录
                        MessageResponse auditRs =
                        auditRecordService.audit(BisType.SOCIETY_ORG_INFO, obj.getId(), obj.getId(), rst, remark,
                        userProp);
                        if (ResultCode.FAIL == auditRs.getStatus()) {
                        return auditRs;
                        }

                        obj.setStatus(rst);
                        obj.setLastModifyDate(DateUtil.getNowDate());
                        obj.setLastModifyUserId(userProp.getUserId());
                        obj.setLastModifyUserName(userProp.getName());
                        subjectIdeaAnnexDao.updateByPrimaryKeySelective(obj);


                        dataBaseLogService.log("审核议题点子附件", "议题点子附件",
                        String.valueOf(id), String.valueOf(id), "议题点子附件", userProp);
                        return new MessageResponse(0, "议题点子附件审核完成！");
                        }

                        }

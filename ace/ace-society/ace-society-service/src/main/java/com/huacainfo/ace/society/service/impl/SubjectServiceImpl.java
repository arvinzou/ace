package com.huacainfo.ace.society.service.impl;


import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.tools.DateUtil;
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
import com.huacainfo.ace.society.dao.SubjectDao;
import com.huacainfo.ace.society.model.Subject;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.service.SubjectService;
import com.huacainfo.ace.society.vo.SubjectVo;
import com.huacainfo.ace.society.vo.SubjectQVo;
@Service("subjectService")
/**
* @author: lcan
* @version: 2018-09-12
* @Description:  TODO(方案提议)
*/
public class SubjectServiceImpl implements SubjectService {
Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
private SubjectDao subjectDao;
@Autowired
private DataBaseLogService dataBaseLogService;

/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(方案提议分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<SubjectVo>
    * @throws
    * @author: lcan
    * @version: 2018-09-12
    */
    @Override
    public PageResult
    <SubjectVo> findSubjectList(SubjectQVo condition, int start,
        int limit, String orderBy) throws Exception {
        PageResult
        <SubjectVo> rst = new PageResult<>();
            List
            <SubjectVo> list = this.subjectDao.findList(condition,
                start, limit, orderBy);
                rst.setRows(list);
                if (start <= 1) {
                int allRows = this.subjectDao.findCount(condition);
                rst.setTotal(allRows);
                }
                return rst;
                }

                /**
                *
                * @Title:insertSubject
                * @Description: TODO(添加方案提议)
                * @param: @param o
                * @param: @param userProp
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
                * @author: lcan
                * @version: 2018-09-12
                */
                @Override
                public MessageResponse insertSubject(Subject o, UserProp userProp) throws Exception {

                    o.setId(GUIDUtil.getGUID());
                    if (CommonUtils.isBlank(o.getTitle())) {
                        return new MessageResponse(1, "议题标题不能为空！");
                    }
                    if(CommonUtils.isBlank(o.getContent())){
                        return new MessageResponse(1, "议题描述不能为空！");
                    }
                    if(CommonUtils.isBlank(o.getRewardPoints())){
                        return new MessageResponse(1, "奖励积分不能为空！");
                    }
                    int temp = this.subjectDao.isExit(o);
                    if (temp > 0) {
                    return new MessageResponse(1, "方案提议名称重复！");
                    }

                    o.setCreateDate(new Date());
                    o.setStatus("2");
                    o.setCreateUserName(userProp.getName());
                    o.setCreateUserId(userProp.getUserId());
                    this.subjectDao.insertSelective(o);
                    this.dataBaseLogService.log("添加方案提议", "方案提议", "",
                    o.getId(),o.getId(), userProp);

                    return new MessageResponse(0, "添加方案提议完成！");
                }

                /**
                *
                * @Title:updateSubject
                * @Description: TODO(更新方案提议)
                * @param: @param o
                * @param: @param userProp
                * @param: @throws Exception
                * @return: MessageResponse
                * @throws
                * @author: lcan
                * @version: 2018-09-12
                */
                @Override
                public MessageResponse updateSubject(Subject o, UserProp userProp) throws Exception {
                if (CommonUtils.isBlank(o.getId())) {
                    return new MessageResponse(1, "主键-GUID不能为空！");
                }
                if (CommonUtils.isBlank(o.getTitle())) {
                    return new MessageResponse(1, "议题标题不能为空！");
                }

                o.setLastModifyDate(new Date());
                o.setLastModifyUserName(userProp.getName());
                o.setLastModifyUserId(userProp.getUserId());
                this.subjectDao.updateByPrimaryKeySelective(o);
                this.dataBaseLogService.log("变更方案提议", "方案提议", "",
                o.getId(), o.getId(), userProp);

                return new MessageResponse(0, "变更方案提议完成！");
                }

                /**
                *
                * @Title:selectSubjectByPrimaryKey
                * @Description: TODO(获取方案提议)
                * @param: @param id
                * @param: @throws Exception
                * @return: SingleResult<Subject>
                * @throws
                * @author: lcan
                * @version: 2018-09-12
                */
                @Override
                public SingleResult
                <SubjectVo> selectSubjectByPrimaryKey(String id) throws Exception {
                    SingleResult
                    <SubjectVo> rst = new SingleResult<>();
                        rst.setValue(this.subjectDao.selectVoByPrimaryKey(id));
                        return rst;
                        }

                        /**
                        *
                        * @Title:deleteSubjectBySubjectId
                        * @Description: TODO(删除方案提议)
                        * @param: @param id
                        * @param: @param userProp
                        * @param: @throws Exception
                        * @return: MessageResponse
                        * @throws
                        * @author: lcan
                        * @version: 2018-09-12
                        */
                        @Override
                        public MessageResponse deleteSubjectBySubjectId(String id, UserProp userProp) throws
                        Exception {
                        this.subjectDao.deleteByPrimaryKey(id);
                        this.dataBaseLogService.log("删除方案提议", "方案提议",
                        String.valueOf(id),
                        String.valueOf(id), "方案提议", userProp);
                        return new MessageResponse(0, "方案提议删除完成！");
                        }


                        /**
                        *
                        * @Title:audit
                        * @Description: TODO(审核方案提议)
                        * @param: @param id bean.id
                        * @param: @param rst 审核结果 3-通过 4-拒绝
                        * @param: @param remark 审核备注
                        * @param: @throws Exception
                        * @return: MessageResponse
                        * @throws
                        * @author: lcan
                        * @version: 2018-09-12
                        */
                        @Override
                        public MessageResponse audit(String id,String rst, String remark, UserProp userProp) throws Exception {

                            Subject subject = subjectDao.selectByPrimaryKey(id);
                            if(null == subject){
                                return new MessageResponse(ResultCode.FAIL, "方案提议信息丢失！");
                            }
                            subject.setId(id);
                            subject.setStatus(rst);
                            subject.setLastModifyDate(DateUtil.getNowDate());
                            subject.setLastModifyUserId(userProp.getUserId());
                            subject.setLastModifyUserName(userProp.getName());
                            subjectDao.updateByPrimaryKeySelective(subject);
                            dataBaseLogService.log("审核方案提议", "方案提议",
                            String.valueOf(id), String.valueOf(id), "方案提议", userProp);
                            return new MessageResponse(0, "方案提议审核完成！");
                        }

                        }

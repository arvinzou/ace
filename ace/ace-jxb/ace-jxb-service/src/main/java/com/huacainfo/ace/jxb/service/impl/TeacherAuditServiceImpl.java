package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.TeacherAuditDao;
import com.huacainfo.ace.jxb.model.TeacherAudit;
import com.huacainfo.ace.jxb.service.TeacherAuditService;
import com.huacainfo.ace.jxb.vo.TeacherAuditQVo;
import com.huacainfo.ace.jxb.vo.TeacherAuditVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("teacherAuditService")
/**
 * @author: Arvin
 * @version: 2018-07-21
 * @Description: TODO(咨询师审核记录)
 */
public class TeacherAuditServiceImpl implements TeacherAuditService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TeacherAuditDao teacherAuditDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(咨询师审核记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TeacherAuditVo>
     * @author: Arvin
     * @version: 2018-07-21
     */
    @Override
    public PageResult
            <TeacherAuditVo> findTeacherAuditList(TeacherAuditQVo condition, int start,
                                                  int limit, String orderBy) throws Exception {
        PageResult<TeacherAuditVo> rst = new PageResult<>();
        List<TeacherAuditVo> list = teacherAuditDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = teacherAuditDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertTeacherAudit
     * @Description: TODO(添加咨询师审核记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-21
     */
    @Override
    public MessageResponse insertTeacherAudit(TeacherAudit o, UserProp userProp) throws Exception {


        if (CommonUtils.isBlank(o.getCounselorId())) {
            return new MessageResponse(1, "咨询师主键不能为空！");
        }
        o.setAuditor(userProp.getName());
        if (CommonUtils.isBlank(o.getAuditor())) {
            return new MessageResponse(1, "审核人不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatement())) {
            return new MessageResponse(1, "审核说明不能为空！");
        }
        if (CommonUtils.isBlank(o.getRst())) {
            return new MessageResponse(1, "审核结果不能为空！");
        }


//        int temp = teacherAuditDao.isExit(o);
//        if (temp > 0) {
//            return new MessageResponse(1, "咨询师审核记录名称重复！");
//        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        teacherAuditDao.insertSelective(o);
        dataBaseLogService.log("添加咨询师审核记录", "咨询师审核记录", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加咨询师审核记录完成！");
    }

    /**
     * @throws
     * @Title:updateTeacherAudit
     * @Description: TODO(更新咨询师审核记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-21
     */
    @Override
    public MessageResponse updateTeacherAudit(TeacherAudit o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCounselorId())) {
            return new MessageResponse(1, "咨询师主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getAuditor())) {
            return new MessageResponse(1, "审核人不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatement())) {
            return new MessageResponse(1, "审核说明不能为空！");
        }
        if (CommonUtils.isBlank(o.getRst())) {
            return new MessageResponse(1, "审核结果不能为空！");
        }


        teacherAuditDao.updateByPrimaryKeySelective(o);
        dataBaseLogService.log("变更咨询师审核记录", "咨询师审核记录", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更咨询师审核记录完成！");
    }

    /**
     * @throws
     * @Title:selectTeacherAuditByPrimaryKey
     * @Description: TODO(获取咨询师审核记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TeacherAudit>
     * @author: Arvin
     * @version: 2018-07-21
     */
    @Override
    public SingleResult<TeacherAuditVo> selectTeacherAuditByPrimaryKey(String id) throws Exception {
        SingleResult<TeacherAuditVo> rst = new SingleResult<>();
        rst.setValue(teacherAuditDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteTeacherAuditByTeacherAuditId
     * @Description: TODO(删除咨询师审核记录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-21
     */
    @Override
    public MessageResponse deleteTeacherAuditByTeacherAuditId(String id, UserProp userProp) throws
            Exception {
        teacherAuditDao.deleteByPrimaryKey(id);
        dataBaseLogService.log("删除咨询师审核记录", "咨询师审核记录",
                String.valueOf(id),
                String.valueOf(id), "咨询师审核记录", userProp);
        return new MessageResponse(0, "咨询师审核记录删除完成！");
    }

}

package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.CourseAuditDao;
import com.huacainfo.ace.jxb.model.CourseAudit;
import com.huacainfo.ace.jxb.service.CourseAuditService;
import com.huacainfo.ace.jxb.vo.CourseAuditQVo;
import com.huacainfo.ace.jxb.vo.CourseAuditVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("courseAuditService")
/**
 * @author: Arvin
 * @version: 2018-08-06
 * @Description: TODO(课程审核)
 */
public class CourseAuditServiceImpl implements CourseAuditService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CourseAuditDao courseAuditDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(课程审核分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CourseAuditVo>
     * @author: Arvin
     * @version: 2018-08-06
     */
    @Override
    public PageResult<CourseAuditVo> findCourseAuditList(CourseAuditQVo condition, int start,
                                                         int limit, String orderBy) throws Exception {
        PageResult<CourseAuditVo> rst = new PageResult<>();
        List<CourseAuditVo> list = this.courseAuditDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.courseAuditDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCourseAudit
     * @Description: TODO(添加课程审核)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @Override
    public MessageResponse insertCourseAudit(CourseAudit o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCourseId())) {
            return new MessageResponse(1, "课程主键不能为空！");
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


        int temp = this.courseAuditDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "课程审核名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        this.courseAuditDao.insertSelective(o);
        this.dataBaseLogService.log("添加课程审核", "课程审核", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加课程审核完成！");
    }

    /**
     * @throws
     * @Title:updateCourseAudit
     * @Description: TODO(更新课程审核)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @Override
    public MessageResponse updateCourseAudit(CourseAudit o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCourseId())) {
            return new MessageResponse(1, "课程主键不能为空！");
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


        this.courseAuditDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更课程审核", "课程审核", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更课程审核完成！");
    }

    /**
     * @throws
     * @Title:selectCourseAuditByPrimaryKey
     * @Description: TODO(获取课程审核)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CourseAudit>
     * @author: Arvin
     * @version: 2018-08-06
     */
    @Override
    public SingleResult<CourseAuditVo> selectCourseAuditByPrimaryKey(String id) throws Exception {
        SingleResult<CourseAuditVo> rst = new SingleResult<>();
        rst.setValue(this.courseAuditDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCourseAuditByCourseAuditId
     * @Description: TODO(删除课程审核)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @Override
    public MessageResponse deleteCourseAuditByCourseAuditId(String id, UserProp userProp) throws
            Exception {
        this.courseAuditDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除课程审核", "课程审核",
                String.valueOf(id),
                String.valueOf(id), "课程审核", userProp);
        return new MessageResponse(0, "课程审核删除完成！");
    }

}

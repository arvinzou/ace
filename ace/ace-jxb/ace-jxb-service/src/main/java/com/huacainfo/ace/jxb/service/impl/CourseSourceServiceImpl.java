package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.CourseSourceDao;
import com.huacainfo.ace.jxb.model.CourseSource;
import com.huacainfo.ace.jxb.service.CourseSourceService;
import com.huacainfo.ace.jxb.vo.CourseSourceQVo;
import com.huacainfo.ace.jxb.vo.CourseSourceVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("courseSourceService")
/**
 * @author: Arvin
 * @version: 2018-08-06
 * @Description: TODO(课程资源)
 */
public class CourseSourceServiceImpl implements CourseSourceService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CourseSourceDao courseSourceDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(课程资源分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CourseSourceVo>
     * @author: Arvin
     * @version: 2018-08-06
     */
    @Override
    public PageResult<CourseSourceVo> findCourseSourceList(CourseSourceQVo condition, int start,
                                                           int limit, String orderBy) throws Exception {
        PageResult<CourseSourceVo> rst = new PageResult<>();
        List<CourseSourceVo> list = this.courseSourceDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.courseSourceDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCourseSource
     * @Description: TODO(添加课程资源)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @Override
    public MessageResponse insertCourseSource(CourseSource o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getCourseId())) {
            return new MessageResponse(1, "课程主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getPartId())) {
            return new MessageResponse(1, "所属章节不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "课程名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getFree())) {
            return new MessageResponse(1, "可试听不能为空！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        this.courseSourceDao.insertSelective(o);
        this.dataBaseLogService.log("添加课程资源", "课程资源", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加课程资源完成！");
    }

    /**
     * @throws
     * @Title:updateCourseSource
     * @Description: TODO(更新课程资源)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @Override
    public MessageResponse updateCourseSource(CourseSource o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCourseId())) {
            return new MessageResponse(1, "课程主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getPartId())) {
            return new MessageResponse(1, "所属章节不能为空！");
        }
        if (CommonUtils.isBlank(o.getFree())) {
            return new MessageResponse(1, "可试听不能为空！");
        }
        this.courseSourceDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更课程资源", "课程资源", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更课程资源完成！");
    }

    /**
     * @throws
     * @Title:selectCourseSourceByPrimaryKey
     * @Description: TODO(获取课程资源)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CourseSource>
     * @author: Arvin
     * @version: 2018-08-06
     */
    @Override
    public SingleResult<CourseSourceVo> selectCourseSourceByPrimaryKey(String id) throws Exception {
        SingleResult<CourseSourceVo> rst = new SingleResult<>();
        rst.setValue(this.courseSourceDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCourseSourceByCourseSourceId
     * @Description: TODO(删除课程资源)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @Override
    public MessageResponse deleteCourseSourceByCourseSourceId(String id, UserProp userProp) throws
            Exception {
        this.courseSourceDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除课程资源", "课程资源",
                String.valueOf(id),
                String.valueOf(id), "课程资源", userProp);
        return new MessageResponse(0, "课程资源删除完成！");
    }

}

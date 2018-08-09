package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.constant.CourseConstant;
import com.huacainfo.ace.jxb.dao.CourseDao;
import com.huacainfo.ace.jxb.dao.CourseSourceDao;
import com.huacainfo.ace.jxb.model.CourseSource;
import com.huacainfo.ace.jxb.service.CourseService;
import com.huacainfo.ace.jxb.vo.CourseQVo;
import com.huacainfo.ace.jxb.vo.CourseVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service("courseService")
/**
 * @author: Arvin
 * @version: 2018-08-06
 * @Description: TODO(课程)
 */
public class CourseServiceImpl implements CourseService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private CourseSourceDao courseSourceDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(课程分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CourseVo>
     * @author: Arvin
     * @version: 2018-08-06
     */
    @Override
    public PageResult<CourseVo> findCourseList(CourseQVo condition, int start,
                                               int limit, String orderBy) throws Exception {
        PageResult<CourseVo> rst = new PageResult<>();
        List<CourseVo> list = courseDao.findList(condition, start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = courseDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCourse
     * @Description: TODO(添加课程)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @Override
    public MessageResponse insertCourse(CourseVo o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getType())) {
            return new MessageResponse(1, "课程类别不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "内容类别不能为空！");
        }
        if (CommonUtils.isBlank(o.getMediType())) {
            return new MessageResponse(1, "媒体类别不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "课程名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntroduce())) {
            return new MessageResponse(1, "课程介绍不能为空！");
        }
        if (CommonUtils.isBlank(o.getCover())) {
            return new MessageResponse(1, "课程封面不能为空！");
        }
        if (CommonUtils.isBlank(o.getCostType())) {
            return new MessageResponse(1, "费用类型不能为空！");
        }

        int temp = this.courseDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "课程名称重复！");
        }

        String courseId = GUIDUtil.getGUID();
        //单课程，直接增加课程资源
        /*if (CourseConstant.COURSE_TYPE_SINGLE.equals(o.getType())) {
            CourseSource source = o.getCourseSource();
            if (null == source || StringUtil.isEmpty(source.getFree())) {
                return new MessageResponse(ResultCode.FAIL, "课程资源资料不全");
            }
            source.setId(GUIDUtil.getGUID());
            source.setCourseId(courseId);
            source.setPartId("0");//无所属章节
            source.setName(o.getName());
            source.setCreateDate(DateUtil.getNowDate());
            int iCount = courseSourceDao.insert(source);
            if (iCount <= 0) {
                return new MessageResponse(ResultCode.FAIL, "课程资源添加失败");
            }
        }*/

        o.setDemandNum(1);
        o.setLikeNum(1);
        o.setId(courseId);
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserId(userProp.getUserId());
        this.courseDao.insertSelective(o);
        this.dataBaseLogService.log("添加课程", "课程", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加课程完成！");
    }

    /**
     * @throws
     * @Title:updateCourse
     * @Description: TODO(更新课程)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @Override
    public MessageResponse updateCourse(CourseVo o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getType())) {
            return new MessageResponse(1, "课程类别不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "内容类别不能为空！");
        }
        if (CommonUtils.isBlank(o.getMediType())) {
            return new MessageResponse(1, "媒体类别不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "课程名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntroduce())) {
            return new MessageResponse(1, "课程介绍不能为空！");
        }
        if (CommonUtils.isBlank(o.getCover())) {
            return new MessageResponse(1, "课程封面不能为空！");
        }
        if (CommonUtils.isBlank(o.getCostType())) {
            return new MessageResponse(1, "费用类型不能为空！");
        }

        //单课程，直接增加课程资源
        if (CourseConstant.COURSE_TYPE_SINGLE.equals(o.getType())) {
            CourseSource params = o.getCourseSource();
            if (null == params || StringUtil.isEmpty(params.getFree())) {
                return new MessageResponse(ResultCode.FAIL, "课程资源资料不全");
            }
            //
            List<CourseSource> sourceList = courseSourceDao.findByCourseId(o.getId());
            CourseSource source;
            if (CollectionUtils.isEmpty(sourceList)) {
                source = new CourseSource();
                source.setId(GUIDUtil.getGUID());
                source.setCourseId(o.getId());
                source.setPartId("0");//无所属章节
                source.setName(o.getName());
                source.setCreateDate(DateUtil.getNowDate());
                int iCount = courseSourceDao.insert(source);
                if (iCount <= 0) {
                    return new MessageResponse(ResultCode.FAIL, "课程资源添加失败");
                }
            } else {
                source = sourceList.get(0);
                source.setName(o.getName());
                source.setMediUrl(params.getMediUrl());
                source.setDuration(params.getDuration());
                source.setFree(params.getFree());
                courseSourceDao.updateByPrimaryKeySelective(source);
            }
        }

        courseDao.updateByPrimaryKeySelective(o);
        dataBaseLogService.log("变更课程", "课程", "", o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "变更课程完成！");
    }

    /**
     * @throws
     * @Title:selectCourseByPrimaryKey
     * @Description: TODO(获取课程)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Course>
     * @author: Arvin
     * @version: 2018-08-06
     */
    @Override
    public SingleResult<CourseVo> selectCourseByPrimaryKey(String id) throws Exception {
        SingleResult<CourseVo> rst = new SingleResult<>();
        CourseVo vo = courseDao.selectVoByPrimaryKey(id);
        if (CourseConstant.COURSE_TYPE_SINGLE.equals(vo.getType())) {
            vo.setCourseSource(courseSourceDao.findByCourseId(id).get(0));
        }
        rst.setValue(vo);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCourseByCourseId
     * @Description: TODO(删除课程)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @Override
    public MessageResponse deleteCourseByCourseId(String id, UserProp userProp) throws
            Exception {
        this.courseDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除课程", "课程",
                String.valueOf(id),
                String.valueOf(id), "课程", userProp);
        return new MessageResponse(0, "课程删除完成！");
    }

}

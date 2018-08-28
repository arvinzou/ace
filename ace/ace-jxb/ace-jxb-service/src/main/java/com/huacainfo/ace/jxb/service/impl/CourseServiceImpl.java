package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.constant.CourseConstant;
import com.huacainfo.ace.jxb.dao.CourseAuditDao;
import com.huacainfo.ace.jxb.dao.CourseDao;
import com.huacainfo.ace.jxb.dao.CoursePartDao;
import com.huacainfo.ace.jxb.dao.CourseSourceDao;
import com.huacainfo.ace.jxb.model.Course;
import com.huacainfo.ace.jxb.model.CourseAudit;
import com.huacainfo.ace.jxb.model.CourseSource;
import com.huacainfo.ace.jxb.service.CounselorService;
import com.huacainfo.ace.jxb.service.CourseService;
import com.huacainfo.ace.jxb.vo.CounselorVo;
import com.huacainfo.ace.jxb.vo.CoursePartVo;
import com.huacainfo.ace.jxb.vo.CourseQVo;
import com.huacainfo.ace.jxb.vo.CourseVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
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
    @Autowired
    private CourseAuditDao courseAuditDao;
    @Autowired
    private CounselorService counselorService;
    @Autowired
    private CoursePartDao coursePartDao;
    @Autowired
    private SqlSessionTemplate sqlSession;

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
        List<CourseVo> list = courseDao.findList(condition, start, limit, orderBy);
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
        o.setStatus("1");
        o.setLineState("0");
        o.setId(courseId);
        o.setCreateDate(new Date());
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
//        if (CourseConstant.COURSE_TYPE_SINGLE.equals(o.getType())) {
//            CourseSource params = o.getCourseSource();
//            if (null == params || StringUtil.isEmpty(params.getFree())) {
//                return new MessageResponse(ResultCode.FAIL, "课程资源资料不全");
//            }
//            //
//            List<CourseSource> sourceList = courseSourceDao.findByCourseId(o.getId());
//            CourseSource source;
//            if (CollectionUtils.isEmpty(sourceList)) {
//                source = new CourseSource();
//                source.setId(GUIDUtil.getGUID());
//                source.setCourseId(o.getId());
//                source.setPartId("0");//无所属章节
//                source.setName(o.getName());
//                source.setCreateDate(DateUtil.getNowDate());
//                source.setFree(params.getFree());
//                source.setMediUrl(params.getMediUrl());
//                source.setDuration(params.getDuration());
//                int iCount = courseSourceDao.insert(source);
//                if (iCount <= 0) {
//                    return new MessageResponse(ResultCode.FAIL, "课程资源添加失败");
//                }
//            } else {
//                source = sourceList.get(0);
//                source.setName(o.getName());
//                source.setMediUrl(params.getMediUrl());
//                source.setDuration(params.getDuration());
//                source.setFree(params.getFree());
//                courseSourceDao.updateByPrimaryKeySelective(source);
//            }
//        }

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
        //咨询师信息
        CounselorVo counselor = counselorService.selectCounselorByPrimaryKey(vo.getCreateUserId()).getValue();
        vo.setCounselor(counselor);

        if (CourseConstant.COURSE_TYPE_SINGLE.equals(vo.getType())) {
            List<CourseSource> list = courseSourceDao.findByCourseId(id);
            if (!CollectionUtils.isEmpty(list)) {
                vo.setCourseSource(list.get(0));
            }
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

    /**
     * 课程审核
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public MessageResponse audit(CourseAudit record, UserProp curUserProp) {
        if (!"jxb".equals(curUserProp.getAccount())) {
            return new MessageResponse(ResultCode.FAIL, "当前登录账户无审核权限");
        }
        record.setAuditor(curUserProp.getName());
        //重复审核校验
        CourseVo courseVo = courseDao.selectVoByPrimaryKey(record.getCourseId());

        //审核动作
        CourseAudit audit = courseAuditDao.findByCourseId(courseVo.getId());
        if (null == audit) {
            record.setId(GUIDUtil.getGUID());
            record.setCreateDate(DateUtil.getNowDate());
            courseAuditDao.insertSelective(record);
        } else {
            audit.setRst(record.getRst());
            audit.setAuditor(record.getAuditor());
            audit.setStatement(record.getStatement());
            audit.setCreateDate(DateUtil.getNowDate());
            courseAuditDao.updateByPrimaryKeySelective(audit);
        }

        return new MessageResponse(ResultCode.SUCCESS, "审核完成");

    }

    /***
     * 获取课程章节信息
     * @param courseId 课程ID
     * @return List<CoursePartVo>
     */
    @Override
    public List<CoursePartVo> findCoursePartInfo(String courseId) {
        //sql
        SqlSession session = getSqlSession();
        CoursePartDao dao = session.getMapper(CoursePartDao.class);
        //query
        try {
            List<CoursePartVo> list = dao.findByCourseId(courseId);
            return list;
        } catch (Exception e) {
            if (session != null) {
                session.close();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    /**
     * 标记更新课程 上/下线状态
     * <p>
     *
     * @param courseId 课程ID
     * @param state    在架状态 0 - 下架 1 - 上架
     * @return MessageResponse
     */
    @Override
    public MessageResponse updateLineState(String courseId, String state,
                                           UserProp userProp) throws Exception {
        Course course = courseDao.selectVoByPrimaryKey(courseId);
        if (null == course) {
            return new MessageResponse(ResultCode.FAIL, "课程资料丢失");
        }

        return updateLineState(course, state, userProp);
    }

    private MessageResponse updateLineState(Course course, String state, UserProp userProp) throws Exception {
        course.setLineState(state);
//        0 - 下架 1 - 上架
        if ("0".equals(state)) {
            course.setOfflineDate(DateUtil.getNowDate());
//            course.setOnlineDate(null);
        } else if ("1".equals(state)) {
            course.setOnlineDate(DateUtil.getNowDate());
//            course.setOfflineDate(null);
        }
        courseDao.updateByPrimaryKeySelective(course);

        return new MessageResponse(ResultCode.SUCCESS, "0".equals(state) ? "下架成功" : "上架成功");
    }

    private SqlSession getSqlSession() {
        SqlSession session = sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);

        return session;
    }


}

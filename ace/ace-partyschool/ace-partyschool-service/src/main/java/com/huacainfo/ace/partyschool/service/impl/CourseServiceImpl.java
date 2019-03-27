package com.huacainfo.ace.partyschool.service.impl;


import java.util.*;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.partyschool.dao.ClassScheduleDao;
import com.huacainfo.ace.partyschool.dao.CourseTeacherDao;
import com.huacainfo.ace.partyschool.model.CourseTeacher;
import com.huacainfo.ace.partyschool.vo.ClassScheduleVo;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.partyschool.dao.CourseDao;
import com.huacainfo.ace.partyschool.model.Course;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.partyschool.service.CourseService;
import com.huacainfo.ace.partyschool.vo.CourseVo;
import com.huacainfo.ace.partyschool.vo.CourseQVo;

@Service("courseService")
/**
 * @author: 王恩
 * @version: 2019-01-02
 * @Description: TODO(课程管理)
 */
public class CourseServiceImpl implements CourseService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private CourseTeacherDao courseTeacherDao;
    @Autowired
    private SqlSessionTemplate sqlSession;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(课程管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CourseVo>
     * @author: 王恩
     * @version: 2019-01-02
     */
    @Override
    public PageResult<CourseVo> findCourseList(CourseQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<CourseVo> rst = new PageResult<>();
        List<CourseVo> list = this.courseDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.courseDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }


    @Override
    public ResultResponse findListClassifiedName(CourseQVo condition, int start, int limit, String orderBy) throws Exception {
        Map<String, List<CourseVo>> map = new HashMap<String, List<CourseVo>>();
        List<CourseVo> list = this.courseDao.findList(condition, start, limit, orderBy);
        List<CourseVo> itemList;
        for (CourseVo item : list) {
            String teacherName = item.getTeacherName();
            itemList = map.get(teacherName);
            if (CommonUtils.isBlank(itemList)) {
                itemList = new ArrayList<CourseVo>();
            }
            itemList.add(item);
            map.put(teacherName, itemList);
        }
        return new ResultResponse(ResultCode.SUCCESS, "老师分组的课程列表", map);
    }

    /**
     * @throws
     * @Title:insertCourse
     * @Description: TODO(添加课程管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-02
     */
    @Override
    public MessageResponse insertCourse(CourseQVo o, UserProp userProp) throws Exception {
        String cid = GUIDUtil.getGUID();
        o.setId(cid);
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "类别不能为空！");
        }
        o.setTeacherId(o.getTeacherIds().get(0));
        if (CommonUtils.isBlank(o.getTeacherId())) {
            return new MessageResponse(1, "老师ID不能为空！");
        }
        int temp = this.courseDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "课程管理名称重复！");
        }
        o.setStatus("1");
        o.setCreateDate(new Date());
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.courseDao.insert(o);
        List<String> list = o.getTeacherIds();
        CourseTeacher courseTeacher = new CourseTeacher();
        courseTeacher.setCourse_id(cid);
        for (int i = 0; i < list.size(); i++) {
            courseTeacher.setId(GUIDUtil.getGUID());
            courseTeacher.setType(String.valueOf(i));
            courseTeacher.setTeacher_id(list.get(i));
            this.courseTeacherDao.insert(courseTeacher);
        }
        this.dataBaseLogService.log("添加课程管理", "课程管理", "", o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "添加课程管理完成！");
    }

    /**
     * @throws
     * @Title:updateCourse
     * @Description: TODO(更新课程管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-02
     */
    @Override
    public MessageResponse updateCourse(CourseQVo o, UserProp userProp) throws Exception {
        Course course = this.courseDao.selectByPrimaryKey(o.getId());
        if (course == null) {
            return new MessageResponse(1, "课程管理数据丢失！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "类别不能为空！");
        }
        o.setTeacherId(o.getTeacherIds().get(0));
        if (CommonUtils.isBlank(o.getTeacherId())) {
            return new MessageResponse(1, "老师ID不能为空！");
        }
        course.setLastModifyDate(new Date());
        course.setLastModifyUserName(userProp.getName());
        course.setLastModifyUserId(userProp.getUserId());
        course.setName(o.getName());
        course.setCategory(o.getCategory());
        course.setEvaluatingId(o.getEvaluatingId());
        course.setTeacherId(o.getTeacherId());
        this.courseDao.updateByPrimaryKey(course);
        this.courseTeacherDao.deleteByPrimaryKey(o.getId());
        List<String> list = o.getTeacherIds();
        CourseTeacher courseTeacher = new CourseTeacher();
        courseTeacher.setCourse_id(o.getId());
        for (int i = 0; i < list.size(); i++) {
            courseTeacher.setId(GUIDUtil.getGUID());
            courseTeacher.setType(String.valueOf(i));
            courseTeacher.setTeacher_id(list.get(i));
            this.courseTeacherDao.insert(courseTeacher);
        }
        this.dataBaseLogService.log("变更课程管理", "课程管理", "",
                o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "变更课程管理完成！");
    }

    /**
     * @throws
     * @Title:selectCourseByPrimaryKey
     * @Description: TODO(获取课程管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Course>
     * @author: 王恩
     * @version: 2019-01-02
     */
    @Override
    public SingleResult<CourseVo> selectCourseByPrimaryKey(String id) throws Exception {

        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);
        CourseDao dao = session.getMapper(CourseDao.class);
        SingleResult<CourseVo> rst = new SingleResult<>();
        try {
            CourseVo courseVo  = dao.selectVoByPrimaryKey(id);
            rst.setValue(courseVo);
        }catch (Exception e){
            session.close();
        }finally {
            session.close();
        }


        return rst;
    }

    /**
     * @throws
     * @Title:deleteCourseByCourseId
     * @Description: TODO(删除课程管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-02
     */
    @Override
    public MessageResponse deleteCourseByCourseId(String id, UserProp userProp) throws
            Exception {
        this.courseDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除课程管理", "课程管理", id, id,
                "课程管理", userProp);
        return new MessageResponse(0, "课程管理删除完成！");
    }


    @Override
    public MessageResponse softdel(String id, UserProp userProp) throws Exception {

        Course obj = courseDao.selectByPrimaryKey(id);
        if (obj == null) {
            return new MessageResponse(ResultCode.FAIL, "课程管理数据丢失");
        }
        obj.setStatus("0");
        obj.setLastModifyDate(DateUtil.getNowDate());
        obj.setLastModifyUserId(userProp.getUserId());
        obj.setLastModifyUserName(userProp.getName());
        courseDao.updateByPrimaryKey(obj);
        dataBaseLogService.log("审核课程管理", "课程管理", id, id,
                "课程管理", userProp);
        return new MessageResponse(0, "删除成功！");
    }

}

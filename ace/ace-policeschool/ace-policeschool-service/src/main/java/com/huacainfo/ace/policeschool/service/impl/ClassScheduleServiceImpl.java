package com.huacainfo.ace.policeschool.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateCalculateKit;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.policeschool.dao.ClassScheduleDao;
import com.huacainfo.ace.policeschool.dao.EvaluationRstDao;
import com.huacainfo.ace.policeschool.model.ClassSchedule;
import com.huacainfo.ace.policeschool.service.ClassScheduleService;
import com.huacainfo.ace.policeschool.service.SignService;
import com.huacainfo.ace.policeschool.vo.AccountVo;
import com.huacainfo.ace.policeschool.vo.ClassScheduleQVo;
import com.huacainfo.ace.policeschool.vo.ClassScheduleVo;
import com.huacainfo.ace.policeschool.vo.EvaluationRstQVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("classScheduleService")
/**
 * @author: 王恩
 * @version: 2019-01-06
 * @Description: TODO(课程表管理)
 */
public class ClassScheduleServiceImpl implements ClassScheduleService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ClassScheduleDao classScheduleDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private SqlSessionTemplate sqlSession;
    @Autowired
    private SignService signService;
    @Autowired
    private EvaluationRstDao evaluationRstDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(课程表管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ClassScheduleVo>
     * @author: 王恩
     * @version: 2019-01-06
     */
    @Override
    public PageResult<ClassScheduleVo> findClassScheduleList(ClassScheduleQVo condition, int start,
                                                             int limit, String orderBy) throws Exception {
        PageResult<ClassScheduleVo> rst = new PageResult<>();
        List<ClassScheduleVo> list = this.classScheduleDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.classScheduleDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(课程表管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ClassScheduleVo>
     * @author: 王恩
     * @version: 2019-01-06
     */
    @Override
    public PageResult<ClassScheduleVo> LearnedCourses(ClassScheduleQVo condition, int start, int limit, String orderBy) throws Exception {
        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);
        ClassScheduleDao dao = session.getMapper(ClassScheduleDao.class);
        PageResult<ClassScheduleVo> rst = new PageResult<>();
        try {
            List<ClassScheduleVo> list = dao.LearnedCourses(condition, start, limit, orderBy);
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
//

    @Override
    public ResultResponse MyClassSchedule(ClassScheduleQVo condition, int start, int limit, String orderBy, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(condition.getStartTimeStr()) && CommonUtils.isBlank(condition.getWeekDate())) {
            new ResultResponse(ResultCode.FAIL, "没有传入时间");
        }
        if (CommonUtils.isBlank(condition.getClassList()) || condition.getClassList().size() == 0) {
            if (userProp == null) {
                new ResultResponse(ResultCode.FAIL, "没有登陆");
            }
            AccountVo accountVo = (AccountVo) signService.getAcctInfo(userProp.getAccount()).getData();
            List<String> classList = new ArrayList<>();
            if ("teacher".equals(accountVo.getRegType())) {
                condition.setcTeacherId(userProp.getUserId());
            } else if ("student".equals(accountVo.getRegType())) {
                classList.add(accountVo.getStudent().getClassId());
            } else {
                return new ResultResponse(ResultCode.FAIL, "该身份没有课表");
            }
            condition.setClassList(classList);
        }
        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);
        ClassScheduleDao dao = session.getMapper(ClassScheduleDao.class);
        ResultResponse rst = new ResultResponse();
        try {
            List<ClassScheduleVo> list = dao.LearnedCourses(condition, start, limit, orderBy);
            rst.setStatus(ResultCode.SUCCESS);
            rst.setData(list);
        } catch (Exception e) {
            session.close();
        } finally {
            session.close();
        }
        return rst;
    }

    @Override
    public ResultResponse notDoneTestList(ClassScheduleQVo condition, int start, int limit, String orderBy, UserProp userProp) throws Exception {
        if (userProp == null) {
            new ResultResponse(ResultCode.FAIL, "没有登陆");
        }
        AccountVo accountVo = (AccountVo) signService.getAcctInfo(userProp.getAccount()).getData();
        if ("teacher".equals(accountVo.getRegType())) {
            return new ResultResponse(ResultCode.FAIL, "该身份没有评测");
        } else if ("student".equals(accountVo.getRegType())) {
            condition.setClassesId(accountVo.getStudent().getClassId());
            condition.setErUserId(userProp.getUserId());
        } else {
            return new ResultResponse(ResultCode.FAIL, "该身份没有评测");
        }
        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);
        ClassScheduleDao dao = session.getMapper(ClassScheduleDao.class);
        ResultResponse rst = new ResultResponse();
        Map<String, Object> map = new HashMap<>();
        try {
            List<ClassScheduleVo> list = dao.notDoneTestList(condition, start, limit, orderBy);
            if (start < 1) {
                int notDoneSize = this.classScheduleDao.notDoneTestSize(condition);
                EvaluationRstQVo evaluationRstQVo = new EvaluationRstQVo();
                evaluationRstQVo.setUserId(userProp.getUserId());
                int doneSize = this.evaluationRstDao.getDoneSize(evaluationRstQVo);
                map.put("notDoneSize", notDoneSize);
                map.put("doneSize", doneSize);
            }
            map.put("list", list);
        } catch (Exception e) {
            session.close();
        } finally {
            session.close();
        }
        return new ResultResponse(ResultCode.SUCCESS, "获取成功", map);
    }


    @Override
    public ResultResponse doneTestList(ClassScheduleQVo condition, int start, int limit, String orderBy, UserProp userProp) throws Exception {
        if (userProp == null) {
            new ResultResponse(ResultCode.FAIL, "没有登陆");
        }
        AccountVo accountVo = (AccountVo) signService.getAcctInfo(userProp.getAccount()).getData();
        if ("teacher".equals(accountVo.getRegType())) {
            return new ResultResponse(ResultCode.FAIL, "该身份没有评测");
        } else if ("student".equals(accountVo.getRegType())) {
            condition.setClassesId(accountVo.getStudent().getClassId());
            condition.setErUserId(userProp.getUserId());
        } else {
            return new ResultResponse(ResultCode.FAIL, "该身份没有评测");
        }
        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);
        ClassScheduleDao dao = session.getMapper(ClassScheduleDao.class);
        ResultResponse rst = new ResultResponse();
        Map<String, Object> map = new HashMap<>();
        try {
            List<ClassScheduleVo> list = dao.doneTestList(condition, start, limit, orderBy);
            map.put("list", list);
        } catch (Exception e) {
            session.close();
        } finally {
            session.close();
        }
        return new ResultResponse(ResultCode.SUCCESS, "获取成功", map);
    }

    /**
     * @throws
     * @Title:insertClassSchedule
     * @Description: TODO(添加课程表管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-06
     */
    @Override
    public MessageResponse insertClassSchedule(ClassSchedule o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getClassesId())) {
            return new MessageResponse(1, "班级不能为空！");
        }
        if (CommonUtils.isBlank(o.getStartTime())) {
            return new MessageResponse(1, "开始日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getEndTime())) {
            return new MessageResponse(1, "结束日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getTeacherId())) {
            return new MessageResponse(1, "老师不能为空！");
        }
        if (CommonUtils.isBlank(o.getCourseId())) {
            return new MessageResponse(1, "课程不能为空！");
        }

        o.setStatus("1");
        this.classScheduleDao.insert(o);
        this.dataBaseLogService.log("添加课程表管理", "课程表管理", "",
                o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "添加课程表管理完成！");
    }

    /**
     * @throws
     * @Title:updateClassSchedule
     * @Description: TODO(更新课程表管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-06
     */
    @Override
    public MessageResponse updateClassSchedule(ClassSchedule o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getClassesId())) {
            return new MessageResponse(1, "班级不能为空！");
        }
        if (CommonUtils.isBlank(o.getStartTime())) {
            return new MessageResponse(1, "开始日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getEndTime())) {
            return new MessageResponse(1, "结束日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getTeacherId())) {
            return new MessageResponse(1, "老师不能为空！");
        }
        if (CommonUtils.isBlank(o.getCourseId())) {
            return new MessageResponse(1, "课程不能为空！");
        }
        this.classScheduleDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更课程表管理", "课程表管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更课程表管理完成！");
    }

    /**
     * @throws
     * @Title:selectClassScheduleByPrimaryKey
     * @Description: TODO(获取课程表管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ClassSchedule>
     * @author: 王恩
     * @version: 2019-01-06
     */
    @Override
    public SingleResult<ClassScheduleVo> selectClassScheduleByPrimaryKey(String id) throws Exception {
        SingleResult<ClassScheduleVo> rst = new SingleResult<>();
        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);
        ClassScheduleDao dao = session.getMapper(ClassScheduleDao.class);
        try {
            ClassScheduleVo classScheduleVo = dao.selectVoByPrimaryKey(id);
            rst.setValue(classScheduleVo);
        } catch (Exception e) {
            session.close();
        } finally {
            session.close();
        }
        return rst;
    }

    /**
     * @throws
     * @Title:deleteClassScheduleByClassScheduleId
     * @Description: TODO(删除课程表管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-06
     */
    @Override
    public MessageResponse deleteClassScheduleByClassScheduleId(String id, UserProp userProp) throws
            Exception {
        this.classScheduleDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除课程表管理", "课程表管理", id, id,
                "课程表管理", userProp);
        return new MessageResponse(0, "课程表管理删除完成！");
    }


    /**
     * 获取数据集合
     *
     * @return ResultResponse
     */
    @Override
    public List<ClassScheduleVo> findList(ClassScheduleQVo condition, int start, int limit, String orderBy) {
        start = start < 0 ? 0 : start;
        limit = limit < 5 ? 5 : limit;
        return this.classScheduleDao.findList(condition, start, limit, orderBy);
    }

    /**
     * 教官结课处理
     *
     * @param teacherId     教官用户ID
     * @param clsScheduleId 课程ID
     */
    @Override
    public ResultResponse closeClass(String teacherId, String clsScheduleId) {
        //
        ClassSchedule cs = this.classScheduleDao.selectByPrimaryKey(clsScheduleId);
        if (cs == null) {
            return new ResultResponse(ResultCode.FAIL, "课程信息丢失");
        }
        if (!teacherId.equals(cs.getTeacherId())) {
            return new ResultResponse(ResultCode.FAIL, "操作人与上课老师不符");
        }
        if (!"1".equals(cs.getStatus())) {
            return new ResultResponse(ResultCode.FAIL, "课程状态有误");
        }
        //数据更新
        classScheduleDao.updateStatus(clsScheduleId, "2");

        return new ResultResponse(ResultCode.SUCCESS, "操作成功");
    }

    /**
     * 教官课时统计报表
     *
     * @param teacherId 教官用户ID
     * @return ResultResponse
     */
    @Override
    public ResultResponse classReport(String teacherId) {

        //统计数据
        Map<String, String> params = dataPack(teacherId);
        Map<String, Object> data = classScheduleDao.dataReport(params);
        //图表数据
        String yearStr = DateUtil.toStr(DateUtil.getNowDate(), "yyyy");
        Map<String, Object> chart = classScheduleDao.chartReport(teacherId, yearStr);

        //结果返回
        Map<String, Object> rst = new HashMap<>();
        rst.put("data", data);
        rst.put("chart", chart);
        return new ResultResponse(ResultCode.SUCCESS, "ok", rst);
    }

    /**
     * 统计参数组装
     */
    private Map<String, String> dataPack(String teacherId) {
        //当前系统时间
        Date now = DateUtil.getNowDate();
        //请求参数
        Map<String, String> params = new HashMap<>();
        params.put("teacherId", teacherId);
        //本周
        params.put("weekStart", DateCalculateKit.getWeekMorningStr(now));
        params.put("weekEnd", DateCalculateKit.getWeekNightStr(now));
        //本月
        params.put("monthStr", DateUtil.toStr(now, "yyyy-MM"));
        //本季度
        params.put("seasonStart", DateCalculateKit.getQuarterStartStr(now));
        params.put("seasonEnd", DateCalculateKit.getQuarterEndStr(now));
        //本年
        params.put("yearStr", DateUtil.toStr(now, "yyyy"));

        return params;
    }
}

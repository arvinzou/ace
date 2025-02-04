package com.huacainfo.ace.policeschool.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.policeschool.dao.ClassesDao;
import com.huacainfo.ace.policeschool.dao.ClassroomDao;
import com.huacainfo.ace.policeschool.dao.StudentDao;
import com.huacainfo.ace.policeschool.dao.TeacherDao;
import com.huacainfo.ace.policeschool.model.Classes;
import com.huacainfo.ace.policeschool.model.Classroom;
import com.huacainfo.ace.policeschool.model.Student;
import com.huacainfo.ace.policeschool.model.Teacher;
import com.huacainfo.ace.policeschool.service.ClassesService;
import com.huacainfo.ace.policeschool.service.SignService;
import com.huacainfo.ace.policeschool.vo.AccountVo;
import com.huacainfo.ace.policeschool.vo.ClassesQVo;
import com.huacainfo.ace.policeschool.vo.ClassesVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("classesService")
/**
 * @author: Arvin
 * @version: 2019-01-03
 * @Description: TODO(班级管理)
 */
public class ClassesServiceImpl implements ClassesService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ClassesDao classesDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private SignService signService;
    @Autowired
    private SqlSessionTemplate sqlSession;
    @Autowired
    private ClassroomDao classroomDao;
    @Autowired
    private TeacherDao teacherDao;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(班级管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ClassesVo>
     * @author: Arvin
     * @version: 2019-01-03
     */
    @Override
    public PageResult<ClassesVo> findClassesList(ClassesQVo condition, int start,
                                                 int limit, String orderBy) throws Exception {
        PageResult<ClassesVo> rst = new PageResult<>();
        List<ClassesVo> list = this.classesDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.classesDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertClasses
     * @Description: TODO(添加班级管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-03
     */
    @Override
    public MessageResponse insertClasses(Classes o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getStartDate())) {
            return new MessageResponse(1, "开始日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getEndDate())) {
            return new MessageResponse(1, "结束日期不能为空！");
        }
        o.setStatus("1");
        //外键约束条件限制
        MessageResponse fms = fKeyCheck(o);
        if (fms.getStatus() == 1) {
            return fms;
        }
        String id = GUIDUtil.getGUID();
        if (CommonUtils.isBlank(o.getHeadmaster())) {
            o.setHeadmaster("0");
        }
        int temp = this.classesDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "班级管理名称重复！");
        }
        o.setId(id);
        o.setCreateDate(new Date());
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.classesDao.insert(o);
        this.dataBaseLogService.log("添加班级管理", "班级管理", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加班级管理完成！");
    }

    /**
     * @throws
     * @Title:updateClasses
     * @Description: TODO(更新班级管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-03
     */
    @Override
    public MessageResponse updateClasses(Classes o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getStartDate())) {
            return new MessageResponse(1, "开始日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getEndDate())) {
            return new MessageResponse(1, "结束日期不能为空！");
        }
        o.setStatus("1");
        //外键约束条件限制
        MessageResponse fms = fKeyCheck(o);
        if (fms.getStatus() == 1) {
            return fms;
        }
        if (CommonUtils.isBlank(o.getHeadmaster())) {
            o.setHeadmaster("0");
        }
        int temp = this.classesDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "班级管理名称重复！");
        }
        o.setCreateDate(new Date());
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        classesDao.updateByPrimaryKey(o);
        dataBaseLogService.log("变更班级管理", "班级管理", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更班级管理完成！");
    }

    private MessageResponse fKeyCheck(Classes o) {
        if (StringUtil.isNotEmpty(o.getClassroomId())) {
            Classroom c = classroomDao.selectByPrimaryKey(o.getClassroomId());
            if (c == null) {
                return new MessageResponse(ResultCode.FAIL, "教室不存在");
            }
        }
        if (StringUtil.isNotEmpty(o.getHeadmaster())) {
            Teacher obj = teacherDao.selectByPrimaryKey(o.getHeadmaster());
            if (obj == null) {
                return new MessageResponse(ResultCode.FAIL, "班主任不存在");
            }
        }
        if (StringUtil.isNotEmpty(o.getTid1())) {
            Teacher obj = teacherDao.selectByPrimaryKey(o.getTid1());
            if (obj == null) {
                return new MessageResponse(ResultCode.FAIL, "跟班老师不存在");
            }
        }
        if (StringUtil.isNotEmpty(o.getTid2())) {
            Student obj = studentDao.selectByPrimaryKey(o.getTid2());
            if (obj == null) {
                return new MessageResponse(ResultCode.FAIL, "跟班干部不存在");
            }
        }


        return new MessageResponse(ResultCode.SUCCESS, "SUCCESS");
    }

    /**
     * @throws
     * @Title:selectClassesByPrimaryKey
     * @Description: TODO(获取班级管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Classes>
     * @author: Arvin
     * @version: 2019-01-03
     */
    @Override
    public SingleResult<ClassesVo> selectClassesByPrimaryKey(String id) throws Exception {
        SingleResult<ClassesVo> rst = new SingleResult<>();
        rst.setValue(this.classesDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:selectClassesByPrimaryKeyV
     * @Description: TODO(班级须知)
     * @param: @param id
     * @param: @throws Exceptiono
     * @return: ResultResponse
     * @author: Arvin
     * @version: 2019-01-03
     */
    @Override
    public ResultResponse selectClassesByPrimaryKeyVo(UserProp userProp, String classId) throws Exception {
        if (CommonUtils.isBlank(classId)) {
            if (userProp == null) {
                return new ResultResponse(ResultCode.FAIL, "请先跳转登录");
            }
            AccountVo accountVo = (AccountVo) signService.getAcctInfo(userProp.getAccount()).getData();
            classId = accountVo.getStudent().getClassId();
        }
        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);
        ClassesDao dao = session.getMapper(ClassesDao.class);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("list", dao.getClassesInfo(classId));
            map.put("count", this.studentDao.findStudentCount(classId));
        } catch (Exception e) {
            session.close();
        } finally {
            session.close();
        }
        return new ResultResponse(0, "班级须知获取完成！", map);
    }

    /**
     * @throws
     * @Title:deleteClassesByClassesId
     * @Description: TODO(删除班级管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-03
     */
    @Override
    public MessageResponse deleteClassesByClassesId(String id, UserProp userProp) throws
            Exception {
        Classes classes = classesDao.selectVoByPrimaryKey(id);
        if (null == classes) {
            return new MessageResponse(ResultCode.FAIL, "记录数据丢失！");
        }
        classes.setStatus("2");
        classes.setClassroomId(classes.getClassroomId());
        classes.setHeadmaster(classes.getHeadmaster());
        classes.setTid1(classes.getTid1());
        classes.setTid2(classes.getTid2());
        classes.setLastModifyUserId(userProp.getUserId());
        classes.setLastModifyUserName(userProp.getName());
        classes.setLastModifyDate(DateUtil.getNowDate());
        this.classesDao.updateByPrimaryKey(classes);
        this.dataBaseLogService.log("删除班级管理", "班级管理", id, id,
                "班级管理", userProp);
        return new MessageResponse(0, "班级注销完成！");
    }

    /**
     * 根据指定条件查询班级信息
     *
     * @param params params
     * @return Map
     */
    @Override
    public Map<String, Object> findByQ(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<>();
        List<Map<String, String>> list = classesDao.findByQ(params);
        rst.put("total", list.size());
        rst.put("rows", list);

        return rst;
    }

    /**
     * 恢复班级
     *
     * @param id          did
     * @param curUserProp
     * @return MessageResponse
     */
    @Override
    public MessageResponse recover(String id, UserProp curUserProp) throws Exception {
        Classes classes = classesDao.selectVoByPrimaryKey(id);
        if (null == classes) {
            return new MessageResponse(ResultCode.FAIL, "记录数据丢失！");
        }
        classes.setStatus("1");
        classes.setClassroomId(classes.getClassroomId());
        classes.setHeadmaster(classes.getHeadmaster());
        classes.setTid1(classes.getTid1());
        classes.setTid2(classes.getTid2());
        classes.setLastModifyUserId(curUserProp.getUserId());
        classes.setLastModifyUserName(curUserProp.getName());
        classes.setLastModifyDate(DateUtil.getNowDate());
        this.classesDao.updateByPrimaryKey(classes);
        this.dataBaseLogService.log("恢复班级管理", "班级管理", id, id,
                "班级管理", curUserProp);
        return new MessageResponse(0, "班级恢复完成！");
    }

    @Override
    public ResultResponse getMyClasses(UserProp userProp) throws Exception {
        return new ResultResponse(ResultCode.SUCCESS, "成功", this.classesDao.findMyClassesList(userProp.getUserId()));
    }

    @Override
    public ResultResponse getAllClasses(UserProp userProp) throws Exception {
        return new ResultResponse(ResultCode.SUCCESS, "成功", this.classesDao.findAllClassesList());
    }

    /**
     * 每天凌晨1点执行
     * 1、关闭时间已到期的班级
     * 2、注销已毕业班级的学员账户信息
     */
    @Override
    public void graduation() {
        classesDao.graduation();
    }

    @Override
    public ListResult<Map<String, Object>> getClassList() {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(classesDao.getClassList());

        return rst;
    }


}

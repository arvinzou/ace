package com.huacainfo.ace.partyschool.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.partyschool.constant.CommConstant;
import com.huacainfo.ace.partyschool.dao.StudentDao;
import com.huacainfo.ace.partyschool.model.Student;
import com.huacainfo.ace.partyschool.service.SignService;
import com.huacainfo.ace.partyschool.service.StudentService;
import com.huacainfo.ace.partyschool.vo.StudentQVo;
import com.huacainfo.ace.partyschool.vo.StudentVo;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("studentService")
/**
 * @author: Arvin
 * @version: 2018-12-29
 * @Description: TODO(学员管理)
 */
public class StudentServiceImpl implements StudentService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private SignService signService;
    @Autowired
    private UsersService usersService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(学员管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <StudentVo>
     * @author: Arvin
     * @version: 2018-12-29
     */
    @Override
    public PageResult<StudentVo> findStudentList(StudentQVo condition, int start,
                                                 int limit, String orderBy) throws Exception {
        PageResult<StudentVo> rst = new PageResult<>();
        List<StudentVo> list = this.studentDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.studentDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertStudent
     * @Description: TODO(添加学员管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-12-29
     */
    @Override
    public MessageResponse insertStudent(Student o, UserProp userProp) throws Exception {


        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getMobile())) {
            return new MessageResponse(1, "手机号不能为空！");
        }
//        if (CommonUtils.isBlank(o.getIdCard())) {
//            return new MessageResponse(1, "身份证不能为空！");
//        }
        if (CommonUtils.isBlank(o.getClassId())) {
            return new MessageResponse(1, "班级不能为空！");
        }


        int temp = this.studentDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(ResultCode.FAIL, "学员手机号码重复");
        }

        String sid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(sid);
        o.setPid("0");
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.studentDao.insert(o);
        this.dataBaseLogService.log("添加学员管理", "学员管理", "", o.getId(), o.getId(), userProp);


        return new MessageResponse(0, "添加学员成功！");
    }

    /**
     * @throws
     * @Title:updateStudent
     * @Description: TODO(更新学员管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-12-29
     */
    @Override
    public MessageResponse updateStudent(Student o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getMobile())) {
            return new MessageResponse(1, "手机号不能为空！");
        }
        if (CommonUtils.isBlank(o.getClassId())) {
            return new MessageResponse(1, "班级不能为空！");
        }
        //
        Student oldData = studentDao.selectByPrimaryKey(o.getId());
        if (oldData == null) {
            return new MessageResponse(1, "数据丢失！");
        }
        //号码有变动时，进行校验
        if (!oldData.getMobile().equals(o.getMobile())) {
            MessageResponse m = signService.updateAccount(o.getId(), o.getMobile());
            if (m.getStatus() == ResultCode.FAIL) {
                return m;
            }
        }
        oldData.setMobile(o.getMobile());
        oldData.setName(o.getName());
        oldData.setSex(o.getSex());
        oldData.setIdCard(o.getIdCard());
        oldData.setPolitical(o.getPolitical());
        oldData.setWorkUnitName(o.getWorkUnitName());
        oldData.setPostName(o.getPostName());
        if (StringUtil.isNotEmpty(o.getClassId())) {
            oldData.setClassId(o.getClassId());
            oldData.setPid("0");//影响学员分组
        }
        oldData.setRemark(o.getRemark());
        oldData.setLastModifyDate(new Date());
        oldData.setLastModifyUserName(userProp.getName());
        oldData.setLastModifyUserId(userProp.getUserId());
        this.studentDao.updateByPrimaryKey(oldData);
        this.dataBaseLogService.log("变更学员管理", "学员管理", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更学员成功！");
    }

    /**
     * @throws
     * @Title:selectStudentByPrimaryKey
     * @Description: TODO(获取学员管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Student>
     * @author: Arvin
     * @version: 2018-12-29
     */
    @Override
    public SingleResult<StudentVo> selectStudentByPrimaryKey(String id) throws Exception {
        SingleResult<StudentVo> rst = new SingleResult<>();
        rst.setValue(this.studentDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteStudentByStudentId
     * @Description: TODO(删除学员管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-12-29
     */
    @Override
    public MessageResponse deleteStudentByStudentId(String id, UserProp userProp) throws
            Exception {

        Student student = studentDao.selectByPrimaryKey(id);
        Users users = usersService.selectUsersByPrimaryKey(id).getValue();
        if (null == student || null == users) {
            return new MessageResponse(ResultCode.FAIL, "数据丢失！");
        }
        //注销账户
        int i = signService.updateUsersStatus(id, SignServiceImpl.ACCOUNT_INVALID);
        //注销学员信息
        studentDao.updateStatus(id, "0");//已注销
        dataBaseLogService.log("注销学员", "注销学员", id, id, "学员管理", userProp);
        return new MessageResponse(0, "注销成功！");
    }

    /**
     * 判断身份证是否已存在
     *
     * @param idCard 身份证号码
     * @return boolean
     */
    @Override
    public boolean isExistByIdCard(String idCard) {
        int i = studentDao.isExistByIdCard(idCard);

        return i > 0;
    }

    /**
     * 添加学员
     *
     * @param data     params
     * @param userProp operator
     * @return MessageResponse
     */
    @Override
    public MessageResponse addStudent(Student data, UserProp userProp) throws Exception {
        String uid = GUIDUtil.getGUID();
        //主键
        data.setId(uid);
        //注册portal.users
        String regType = CommConstant.STUDENT;
        String openId = "";
        String name = data.getName();
        String account = data.getMobile();
        String pwd = "123456";
        String mobile = data.getMobile();
        String sex = "1";
        //String.valueOf(signService.getCarInfo(data.getIdCard()).get("sex"));
        String sysId = "partyschool";
        String deptId = "7bccdbc14dc04d6d9926656d75e3c720";
        String roleId = "510fd9f9-9612-4415-9eac-2f361f96ee40";//select * from portal.role t where t.syid='partyschool'
        MessageResponse ms2 = signService.insertUsers(regType, uid, openId, name, account, pwd,
                mobile, sex, sysId, deptId, roleId, SignServiceImpl.ACCOUNT_VALID);
        if (ResultCode.FAIL == ms2.getStatus()) {
            return ms2;
        }

        MessageResponse ms = insertStudent(data, userProp);
        if (ResultCode.FAIL == ms.getStatus()) {
            throw new CustomException(ms.getErrorMessage());
        }

        return ms;
    }

    /**
     * 批量导入学员
     *
     * @param list     导入数据
     * @param userProp
     * @param clsId    班级ID  @return MessageResponse
     * @throws Exception
     */
    @Override
    public MessageResponse insertImportXls(List<Map<String, Object>> list,
                                           UserProp userProp, String clsId) throws Exception {
        String importDateTime = DateUtil.getNow();
        int i = 1;
        MessageResponse iMs;
        for (Map<String, Object> row : list) {
            Student o = new Student();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setClassId(clsId);//班级信息
            o.setRemark("批量导入学员：" + importDateTime);
            logger.info(o.toString());
            if (CommonUtils.isBlank(o.getName())) {
                continue;
//                return new MessageResponse(1, "行" + i + ",名称不能为空！");
            }
            if (CommonUtils.isBlank(o.getMobile())) {
                return new MessageResponse(1, "行" + i + ",手机号码不能为空！");
            }
            if (CommonUtils.isBlank(o.getWorkUnitName())) {
                return new MessageResponse(1, "行" + i + ",单位全称不能为空！");
            }

            int t = studentDao.isExist(o);
            if (t > 0) {
                return new MessageResponse(1, "行" + i + ",该学员已被导入系统！");
            } else {
                try {
                    iMs = addStudent(o, userProp);
                    if (ResultCode.FAIL == iMs.getStatus()) {
                        return iMs;
                    }
                } catch (CustomException e) {
                    logger.info("学员导入异常：{}", e);
                    return new MessageResponse(1, "行" + i + ",身份证号码不能为空！");
                }
            }
            i++;
        }

        dataBaseLogService.log("批量导入学员", "批量导入学员", "", "rs.xls", "rs.xls", userProp);
        return new MessageResponse(ResultCode.SUCCESS, "导入成功！");
    }

    /**
     * 账户恢复
     *
     * @param id          did
     * @param curUserProp
     * @return MessageResponse
     */
    @Override
    public MessageResponse recover(String id, UserProp curUserProp) throws Exception {
        Student obj = studentDao.selectByPrimaryKey(id);
        Users users = usersService.selectUsersByPrimaryKey(id).getValue();
        if (null == obj || null == users) {
            return new MessageResponse(ResultCode.FAIL, "数据丢失！");
        }
        //注销账户
        int i = signService.updateUsersStatus(id, SignServiceImpl.ACCOUNT_VALID);
        //注销学员信息
        studentDao.updateStatus(id, SignServiceImpl.ACCOUNT_VALID);//已注销

        return new MessageResponse(0, "账户恢复成功！");
    }

    /**
     * @param userProp
     * @return SingleResult<String>
     */
    @Override
    public SingleResult<Map<String, Object>> getRoleClassId(UserProp userProp) {
        SingleResult<Map<String, Object>> rst = new SingleResult();
        Map<String, Object> e = new HashMap<>();
        Map<String, String> o = this.studentDao.selectUserClassInfo(userProp.getUserId());
        if (o.get("role").equals("student")) {
            e.put("classId", o.get("classId"));
            e.put("role", o.get("role"));
            rst.setValue(e);
        } else {
            String classId = this.studentDao.selectTeacherClassInfoById(userProp.getUserId());
            e.put("classId", classId);
            e.put("role", "teacher");
            List<Map<String, String>> list = this.studentDao.selectTeacherClasses(userProp.getUserId());
            if (list.size() == 0) {
                Map<String, String> t = new HashMap();
                e.put("classId", "c000000999800009");
                list.add(t);
            }
            e.put("list", list);

            rst.setValue(e);
        }
        return rst;
    }

    @Override
    public MessageResponse deleteStudents(List<String> students) {
        this.studentDao.deleteStudents(students);
        return new MessageResponse(ResultCode.SUCCESS, "成功");
    }

}

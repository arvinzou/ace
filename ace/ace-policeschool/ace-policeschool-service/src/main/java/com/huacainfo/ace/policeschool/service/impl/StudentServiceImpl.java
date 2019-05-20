package com.huacainfo.ace.policeschool.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.*;
import com.huacainfo.ace.policeschool.constant.CommConstant;
import com.huacainfo.ace.policeschool.dao.StudentDao;
import com.huacainfo.ace.policeschool.model.Student;
import com.huacainfo.ace.policeschool.service.SignService;
import com.huacainfo.ace.policeschool.service.StudentService;
import com.huacainfo.ace.policeschool.vo.StudentQVo;
import com.huacainfo.ace.policeschool.vo.StudentVo;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
        if (CommonUtils.isBlank(o.getIdCard())) {
            return new MessageResponse(ResultCode.FAIL, "身份证不能为空！");
        }
        if (CommonUtils.isBlank(o.getBadgeNum())) {
            return new MessageResponse(ResultCode.FAIL, "警号不能为空！");
        }
        if (CommonUtils.isBlank(o.getClassId())) {
            return new MessageResponse(1, "班级不能为空！");
        }


        int temp = this.studentDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(ResultCode.FAIL, "警号重复或手机号码重复！");
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
            return new MessageResponse(ResultCode.FAIL, "主键不能为空！");
        }
        String IdCard = o.getIdCard();
        Boolean check = IDCardUtil.isIDCard(IdCard);
        if (!check || CommonUtils.isBlank(check)) {
            new MessageResponse(ResultCode.FAIL, "身份证号码错误");
        }
        if(CommonUtils.isBlank(o.getNativePlace())){
            o.setNativePlace(IDCardUtil.getNativeCode(IdCard));
        }
        o.setSex(IDCardUtil.getSexCode(IdCard));
        o.setBirthDate(IDCardUtil.getbirthDay(IdCard, "-"));

        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(ResultCode.FAIL, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getBadgeNum())) {
            return new MessageResponse(ResultCode.FAIL, "警号不能为空！");
        }
        if (CommonUtils.isBlank(o.getClassId())) {
            return new MessageResponse(ResultCode.FAIL, "班级不能为空！");
        }
        //
        Student oldData = studentDao.selectByPrimaryKey(o.getId());
        if (oldData == null) {
            return new MessageResponse(ResultCode.FAIL, "数据丢失！");
        }
        //号码有变动时，进行校验
        if (!oldData.getBadgeNum().equals(o.getBadgeNum())) {
            MessageResponse m = signService.updateAccount(o.getId(), o.getBadgeNum());
            if (m.getStatus() == ResultCode.FAIL) {
                return m;
            }
        }
        oldData.setBadgeNum(o.getBadgeNum());
        oldData.setBirthDate(o.getBirthDate());
        oldData.setCollege(o.getCollege());
        oldData.setNativePlace(o.getNativePlace());
        oldData.setMobile(o.getMobile());
        oldData.setName(o.getName());
        oldData.setSex(o.getSex());
        oldData.setIdCard(o.getIdCard());
        oldData.setPolitical(o.getPolitical());
        oldData.setWorkUnitName(o.getWorkUnitName());
        oldData.setPostName(o.getPostName());
        oldData.setDorm(o.getDorm());
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public MessageResponse addStudent(Student data, UserProp userProp) throws Exception {
        String IdCard = data.getIdCard();
        Boolean check = IDCardUtil.isIDCard(IdCard);
        if (!check && CommonUtils.isBlank(check)) {
            new MessageResponse(ResultCode.FAIL, "身份证号码错误");
        }
        if(CommonUtils.isBlank(data.getNativePlace())){
            data.setNativePlace(IDCardUtil.getNativeCode(IdCard));
        }
        data.setSex(IDCardUtil.getSexCode(IdCard));
        data.setBirthDate(IDCardUtil.getbirthDay(IdCard, "-"));
        String uid = GUIDUtil.getGUID();
        //主键
        data.setId(uid);
        //注册portal.users
        String regType = CommConstant.STUDENT;
        String openId = "";
        String name = data.getName();
        String account = data.getBadgeNum();
        String pwd = data.getIdCard().substring(data.getIdCard().length() - 6, data.getIdCard().length());
        String mobile = data.getMobile();
        String sex = data.getSex();//String.valueOf(signService.getCarInfo(data.getIdCard()).get("sex"));
        String sysId = "policeschool";
        String deptId = "0004";
        String roleId = "ede24712-e13c-47d5-8cab-fd54589e3fe1";//select * from portal.role t where t.syid='policeschool'
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

            if (CommonUtils.isBlank(o.getName())) {
                return new MessageResponse(1, "序号[" + o.getIndex() + "],姓名不能为空！");
            }
            if (CommonUtils.isBlank(o.getMobile())) {
                return new MessageResponse(1, "序号[" + o.getIndex() + "],手机不能为空！");
            }
            if (StringUtil.isNotEmpty(o.getPolitical())) {
                switch (o.getPolitical()) {
                    case "党员":
                        o.setPolitical("party");
                        break;
                    case "群众":
                        o.setPolitical("public");
                        break;
                    case "团员":
                        o.setPolitical("member");
                        break;
                    default:
                        break;
                }
            }

            Student t = studentDao.findByBadgeNum(o.getBadgeNum());
            if (t != null) {
                //增加更新功能 党校项目同理
                iMs = updateStudentInfo(t, o);
                if (ResultCode.FAIL == iMs.getStatus()) {
                    return iMs;
                }
            } else {
                try {
                    iMs = addStudent(o, userProp);
                    if (ResultCode.FAIL == iMs.getStatus()) {
                        return iMs;
                    }
                } catch (CustomException e) {
                    logger.error("学员导入异常：{}", e);
                    return new MessageResponse(1, "学员导入异常");
                }
            }
            i++;
        }

        dataBaseLogService.log("批量导入学员", "批量导入学员", "", "rs.xls", "rs.xls", userProp);
        return new MessageResponse(ResultCode.SUCCESS, "导入成功！");
    }


    private MessageResponse updateStudentInfo(Student t, Student o) {
        String pwd = o.getIdCard().substring(o.getIdCard().length() - 6);
        //修改登录密码
        ResultResponse rs = signService.updatePwd(o.getBadgeNum(), o.getMobile(), pwd);
        if (rs.getStatus() == ResultCode.FAIL) {
            return new MessageResponse(ResultCode.FAIL, rs.getInfo());
        }
        //变更账户为有效
        signService.updateUsersStatus(t.getId(), SignServiceImpl.ACCOUNT_VALID);

        //修改
        t.setName(o.getName());
        t.setClassId(o.getClassId());
        t.setIdCard(o.getIdCard());
        //次要信息
        t.setPolitical(o.getPolitical());
        t.setCollege(o.getCollege());
        t.setMobile(o.getMobile());
        t.setPostName(o.getPostName());
        t.setWorkUnitName(o.getWorkUnitName());
        t.setStatus("1");
        t.setLastModifyDate(DateUtil.getNowDate());
        studentDao.updateByPrimaryKey(t);

        return new MessageResponse(ResultCode.SUCCESS, "更新成功");
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
            List<String> classId = this.studentDao.selectTeacherClassInfoById(userProp.getUserId());
            StringBuilder s=new StringBuilder();
            for(int i=0;i<classId.size();i++){
                s.append(classId.get(i)+'-');
            }
            e.put("classId", s.toString());
            e.put("role", "teacher");
            rst.setValue(e);
        }
        return rst;
    }

}

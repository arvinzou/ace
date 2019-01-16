package com.huacainfo.ace.partyschool.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.partyschool.constant.CommConstant;
import com.huacainfo.ace.partyschool.dao.TeacherDao;
import com.huacainfo.ace.partyschool.model.Teacher;
import com.huacainfo.ace.partyschool.service.SignService;
import com.huacainfo.ace.partyschool.service.TeacherService;
import com.huacainfo.ace.partyschool.vo.TeacherQVo;
import com.huacainfo.ace.partyschool.vo.TeacherVo;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("teacherService")
/**
 * @author: Arvin
 * @version: 2019-01-02
 * @Description: TODO(教职工管理)
 */
public class TeacherServiceImpl implements TeacherService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private SignService signService;
    @Autowired
    private UsersService usersService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(教职工管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TeacherVo>
     * @author: Arvin
     * @version: 2019-01-02
     */
    @Override
    public PageResult<TeacherVo> findTeacherList(TeacherQVo condition, int start,
                                                 int limit, String orderBy) throws Exception {
        PageResult<TeacherVo> rst = new PageResult<>();
        List<TeacherVo> list = this.teacherDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.teacherDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertTeacher
     * @Description: TODO(添加教职工管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-02
     */
    @Override
    public MessageResponse insertTeacher(Teacher o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getMobile())) {
            return new MessageResponse(1, "手机号不能为空！");
        }

        int temp = this.teacherDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(1, "手机号重复");
        }

        o.setCategory(StringUtil.isEmpty(o.getCategory()) ? "1" : o.getCategory());//默认值

        String tid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(tid);
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.teacherDao.insert(o);
        this.dataBaseLogService.log("添加教职工管理", "教职工管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加成功！");
    }

    /**
     * @throws
     * @Title:updateTeacher
     * @Description: TODO(更新教职工管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-02
     */
    @Override
    public MessageResponse updateTeacher(Teacher o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getMobile())) {
            return new MessageResponse(1, "手机号不能为空！");
        }
        boolean b = signService.isExistByMobile(o.getMobile());
        if (b) {
            return new MessageResponse(1, "手机号码不能重复！");
        }
        //
        Teacher oldData = teacherDao.selectByPrimaryKey(o.getId());
        if (oldData == null) {
            return new MessageResponse(1, "数据丢失！");
        }
        oldData.setMobile(o.getMobile());
        oldData.setName(o.getName());
        oldData.setSex(o.getSex());
        oldData.setIdCard(o.getIdCard());
        oldData.setPolitical(o.getPolitical());
        oldData.setWorkUnitName(o.getWorkUnitName());
        oldData.setPostName(o.getPostName());
        oldData.setIntroduce(o.getIntroduce());
        oldData.setPhotoUrl(o.getPhotoUrl());
        oldData.setRemark(o.getRemark());
        oldData.setLastModifyDate(new Date());
        oldData.setLastModifyUserName(userProp.getName());
        oldData.setLastModifyUserId(userProp.getUserId());
        teacherDao.updateByPrimaryKey(oldData);
        this.dataBaseLogService.log("变更教职工管理", "教职工管理", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更成功！");
    }

    /**
     * @throws
     * @Title:selectTeacherByPrimaryKey
     * @Description: TODO(获取教职工管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Teacher>
     * @author: Arvin
     * @version: 2019-01-02
     */
    @Override
    public SingleResult<TeacherVo> selectTeacherByPrimaryKey(String id) throws Exception {
        SingleResult<TeacherVo> rst = new SingleResult<>();
        rst.setValue(this.teacherDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteTeacherByTeacherId
     * @Description: TODO(删除教职工管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-02
     */
    @Override
    public MessageResponse deleteTeacherByTeacherId(String id, UserProp userProp) throws Exception {

        Teacher obj = teacherDao.selectByPrimaryKey(id);
        Users users = usersService.selectUsersByPrimaryKey(id).getValue();
        if (null == obj || null == users) {
            return new MessageResponse(ResultCode.FAIL, "数据丢失！");
        }
        //注销账户
        users.setStauts(SignServiceImpl.ACCOUNT_INVALID);
        int i = signService.updateUsersStatus(id, SignServiceImpl.ACCOUNT_INVALID);
        //注销学员信息
        teacherDao.updateStatus(id, "0");//已注销
        dataBaseLogService.log("注销教职工", "注销教职工", id, id, "教职工管理", userProp);
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
        int i = teacherDao.isExistByIdCard(idCard);

        return i > 0;
    }

    /**
     * 新增教职工
     *
     * @param data     Teacher
     * @param userProp userProp
     * @return MessageResponse
     */
    @Override
    public MessageResponse addTeacher(Teacher data, UserProp userProp) throws Exception {
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
        String sex = data.getSex();//String.valueOf(signService.getCarInfo(data.getIdCard()).get("sex"));
        String sysId = "partyschool";
        String deptId = "0004";
        String roleId = "9f8f9043-73e1-4438-bf8a-ef681431df74";
        ;//select * from portal.role t where t.syid='partyschool'
        MessageResponse ms2 = signService.insertUsers(regType, uid, openId, name, account, pwd,
                mobile, sex, sysId, deptId, roleId, SignServiceImpl.ACCOUNT_VALID);
        if (ResultCode.FAIL == ms2.getStatus()) {
            return ms2;
        }

        MessageResponse ms = insertTeacher(data, userProp);
        if (ResultCode.FAIL == ms.getStatus()) {
            throw new CustomException(ms.getErrorMessage());
        }

        return ms;
    }

    /**
     * 账户恢复
     *
     * @param id       did
     * @param userProp userProp
     * @return MessageResponse
     */
    @Override
    public MessageResponse recover(String id, UserProp userProp) throws Exception {
        Teacher obj = teacherDao.selectByPrimaryKey(id);
        Users users = usersService.selectUsersByPrimaryKey(id).getValue();
        if (null == obj || null == users) {
            return new MessageResponse(ResultCode.FAIL, "数据丢失！");
        }
        //注销账户
        int i = signService.updateUsersStatus(id, SignServiceImpl.ACCOUNT_VALID);
        //注销学员信息
        teacherDao.updateStatus(id, SignServiceImpl.ACCOUNT_VALID);//已注销

        return new MessageResponse(0, "账户恢复成功！");
    }


}

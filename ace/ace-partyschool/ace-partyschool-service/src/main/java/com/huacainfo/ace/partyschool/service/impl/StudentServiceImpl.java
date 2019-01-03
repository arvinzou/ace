package com.huacainfo.ace.partyschool.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.partyschool.dao.StudentDao;
import com.huacainfo.ace.partyschool.model.Student;
import com.huacainfo.ace.partyschool.service.StudentService;
import com.huacainfo.ace.partyschool.vo.StudentQVo;
import com.huacainfo.ace.partyschool.vo.StudentVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getPid())) {
            return new MessageResponse(1, "父ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getMobile())) {
            return new MessageResponse(1, "手机号不能为空！");
        }
        if (CommonUtils.isBlank(o.getIdCard())) {
            return new MessageResponse(1, "身份证不能为空！");
        }
        if (CommonUtils.isBlank(o.getClassId())) {
            return new MessageResponse(1, "班级不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }


        int temp = this.studentDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(1, "学员管理名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.studentDao.insert(o);
        this.dataBaseLogService.log("添加学员管理", "学员管理", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加学员管理完成！");
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
        if (CommonUtils.isBlank(o.getPid())) {
            return new MessageResponse(1, "父ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getMobile())) {
            return new MessageResponse(1, "手机号不能为空！");
        }
        if (CommonUtils.isBlank(o.getIdCard())) {
            return new MessageResponse(1, "身份证不能为空！");
        }
        if (CommonUtils.isBlank(o.getClassId())) {
            return new MessageResponse(1, "班级不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.studentDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更学员管理", "学员管理", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更学员管理完成！");
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
        this.studentDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除学员管理", "学员管理", id, id,
                "学员管理", userProp);
        return new MessageResponse(0, "学员管理删除完成！");
    }




}

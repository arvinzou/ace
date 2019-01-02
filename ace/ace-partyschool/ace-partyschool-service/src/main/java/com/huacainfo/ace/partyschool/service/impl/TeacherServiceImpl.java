package com.huacainfo.ace.partyschool.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.partyschool.dao.TeacherDao;
import com.huacainfo.ace.partyschool.model.Teacher;
import com.huacainfo.ace.partyschool.service.TeacherService;
import com.huacainfo.ace.partyschool.vo.TeacherQVo;
import com.huacainfo.ace.partyschool.vo.TeacherVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
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

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "类别不能为空！");
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
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        int temp = this.teacherDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(1, "教职工名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
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
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "类别不能为空！");
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
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.teacherDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更教职工管理", "教职工管理", "",
                o.getId(), o.getId(), userProp);

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
    public MessageResponse deleteTeacherByTeacherId(String id, UserProp userProp) throws
            Exception {
        this.teacherDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除教职工管理", "教职工管理", id, id,
                "教职工管理", userProp);
        return new MessageResponse(0, "教职工删除完成！");
    }


}

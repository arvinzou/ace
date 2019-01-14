package com.huacainfo.ace.partyschool.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.partyschool.dao.ClassroomDao;
import com.huacainfo.ace.partyschool.model.Classroom;
import com.huacainfo.ace.partyschool.service.ClassroomService;
import com.huacainfo.ace.partyschool.vo.ClassroomQVo;
import com.huacainfo.ace.partyschool.vo.ClassroomVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("classroomService")
/**
 * @author: Arvin
 * @version: 2019-01-03
 * @Description: TODO(教室管理)
 */
public class ClassroomServiceImpl implements ClassroomService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ClassroomDao classroomDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(教室管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ClassroomVo>
     * @author: Arvin
     * @version: 2019-01-03
     */
    @Override
    public PageResult<ClassroomVo> findClassroomList(ClassroomQVo condition, int start,
                                            int limit, String orderBy) throws Exception {
        PageResult<ClassroomVo> rst = new PageResult<>();
        List<ClassroomVo> list = this.classroomDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.classroomDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertClassroom
     * @Description: TODO(添加教室管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-03
     */
    @Override
    public MessageResponse insertClassroom(Classroom o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }


        int temp = this.classroomDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "教室管理名称重复！");
        }
        o.setCreateDate(new Date());
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.classroomDao.insert(o);
        this.dataBaseLogService.log("添加教室管理", "教室管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加教室管理完成！");
    }

    /**
     * @throws
     * @Title:updateClassroom
     * @Description: TODO(更新教室管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-03
     */
    @Override
    public MessageResponse updateClassroom(Classroom o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }

        o.setCreateDate(new Date());
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.classroomDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更教室管理", "教室管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更教室管理完成！");
    }

    /**
     * @throws
     * @Title:selectClassroomByPrimaryKey
     * @Description: TODO(获取教室管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Classroom>
     * @author: Arvin
     * @version: 2019-01-03
     */
    @Override
    public SingleResult<ClassroomVo> selectClassroomByPrimaryKey(String id) throws Exception {
        SingleResult<ClassroomVo> rst = new SingleResult<>();
        rst.setValue(this.classroomDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteClassroomByClassroomId
     * @Description: TODO(删除教室管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-03
     */
    @Override
    public MessageResponse deleteClassroomByClassroomId(String id, UserProp userProp) throws
            Exception {
        this.classroomDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除教室管理", "教室管理", id, id,
                "教室管理", userProp);
        return new MessageResponse(0, "教室管理删除完成！");
    }

}

package com.huacainfo.ace.partyschool.service.impl;


import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.partyschool.dao.ClassScheduleDao;
import com.huacainfo.ace.partyschool.model.ClassSchedule;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.partyschool.service.ClassScheduleService;
import com.huacainfo.ace.partyschool.vo.ClassScheduleVo;
import com.huacainfo.ace.partyschool.vo.ClassScheduleQVo;

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
    public PageResult
            <ClassScheduleVo> findClassScheduleList(ClassScheduleQVo condition, int start,
                                                    int limit, String orderBy) throws Exception {
        PageResult
                <ClassScheduleVo> rst = new PageResult<>();
        List
                <ClassScheduleVo> list = this.classScheduleDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.classScheduleDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
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

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getClassesId())) {
            return new MessageResponse(1, "班级不能为空！");
        }
        if (CommonUtils.isBlank(o.getCourseDate())) {
            return new MessageResponse(1, "日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getCourseIndex())) {
            return new MessageResponse(1, "课节am:上午 pm:下午不能为空！");
        }
        if (CommonUtils.isBlank(o.getTeacherId())) {
            return new MessageResponse(1, "老师不能为空！");
        }
        if (CommonUtils.isBlank(o.getCourseId())) {
            return new MessageResponse(1, "课程不能为空！");
        }


        int temp = this.classScheduleDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "课程表管理名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
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
        if (CommonUtils.isBlank(o.getCourseDate())) {
            return new MessageResponse(1, "日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getCourseIndex())) {
            return new MessageResponse(1, "课节am:上午 pm:下午不能为空！");
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
    public SingleResult
            <ClassScheduleVo> selectClassScheduleByPrimaryKey(String id) throws Exception {
        SingleResult
                <ClassScheduleVo> rst = new SingleResult<>();
        rst.setValue(this.classScheduleDao.selectVoByPrimaryKey(id));
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
}

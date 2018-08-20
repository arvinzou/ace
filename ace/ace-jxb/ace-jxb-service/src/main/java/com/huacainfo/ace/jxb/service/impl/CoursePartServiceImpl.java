package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.CoursePartDao;
import com.huacainfo.ace.jxb.model.CoursePart;
import com.huacainfo.ace.jxb.service.CoursePartService;
import com.huacainfo.ace.jxb.vo.CoursePartQVo;
import com.huacainfo.ace.jxb.vo.CoursePartVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("coursePartService")
/**
 * @author: Arvin
 * @version: 2018-08-06
 * @Description: TODO(课程章节)
 */
public class CoursePartServiceImpl implements CoursePartService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CoursePartDao coursePartDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(课程章节分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CoursePartVo>
     * @author: Arvin
     * @version: 2018-08-06
     */
    @Override
    public PageResult<CoursePartVo> findCoursePartList(CoursePartQVo condition, int start,
                                                       int limit, String orderBy) throws Exception {
        PageResult<CoursePartVo> rst = new PageResult<>();
        List<CoursePartVo> list = this.coursePartDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.coursePartDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCoursePart
     * @Description: TODO(添加课程章节)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @Override
    public MessageResponse insertCoursePart(CoursePart o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getCourseId())) {
            return new MessageResponse(1, "课程主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "章节名称不能为空！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        this.coursePartDao.insertSelective(o);
        this.dataBaseLogService.log("添加课程章节", "课程章节", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加课程章节完成！");
    }

    /**
     * @throws
     * @Title:updateCoursePart
     * @Description: TODO(更新课程章节)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @Override
    public MessageResponse updateCoursePart(CoursePart o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCourseId())) {
            return new MessageResponse(1, "课程主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "章节名称不能为空！");
        }

        this.coursePartDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更课程章节", "课程章节", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更课程章节完成！");
    }

    /**
     * @throws
     * @Title:selectCoursePartByPrimaryKey
     * @Description: TODO(获取课程章节)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CoursePart>
     * @author: Arvin
     * @version: 2018-08-06
     */
    @Override
    public SingleResult<CoursePartVo> selectCoursePartByPrimaryKey(String id) throws Exception {
        SingleResult<CoursePartVo> rst = new SingleResult<>();
        rst.setValue(this.coursePartDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCoursePartByCoursePartId
     * @Description: TODO(删除课程章节)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    @Override
    public MessageResponse deleteCoursePartByCoursePartId(String id, UserProp userProp) throws
            Exception {
        this.coursePartDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除课程章节", "课程章节",
                String.valueOf(id),
                String.valueOf(id), "课程章节", userProp);
        return new MessageResponse(0, "课程章节删除完成！");
    }

}

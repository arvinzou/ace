package com.huacainfo.ace.partyschool.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.partyschool.dao.ClassesDao;
import com.huacainfo.ace.partyschool.model.Classes;
import com.huacainfo.ace.partyschool.service.ClassesService;
import com.huacainfo.ace.partyschool.vo.ClassesQVo;
import com.huacainfo.ace.partyschool.vo.ClassesVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
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
    private DataBaseLogService dataBaseLogService;

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
    public PageResult
            <ClassesVo> findClassesList(ClassesQVo condition, int start,
                                        int limit, String orderBy) throws Exception {
        PageResult
                <ClassesVo> rst = new PageResult<>();
        List
                <ClassesVo> list = this.classesDao.findList(condition,
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
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }


        int temp = this.classesDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "班级管理名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.classesDao.insert(o);
        this.dataBaseLogService.log("添加班级管理", "班级管理", "",
                o.getId(), o.getId(), userProp);

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
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.classesDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更班级管理", "班级管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更班级管理完成！");
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
    public SingleResult
            <ClassesVo> selectClassesByPrimaryKey(String id) throws Exception {
        SingleResult
                <ClassesVo> rst = new SingleResult<>();
        rst.setValue(this.classesDao.selectVoByPrimaryKey(id));
        return rst;
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
        this.classesDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除班级管理", "班级管理", id, id,
                "班级管理", userProp);
        return new MessageResponse(0, "班级管理删除完成！");
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

}

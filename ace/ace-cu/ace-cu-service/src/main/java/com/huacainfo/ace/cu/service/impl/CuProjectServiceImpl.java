package com.huacainfo.ace.cu.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.cu.dao.CuProjectDao;
import com.huacainfo.ace.cu.model.CuProject;
import com.huacainfo.ace.cu.service.CuDonateListService;
import com.huacainfo.ace.cu.service.CuProjectService;
import com.huacainfo.ace.cu.service.CuProjectUseRecordService;
import com.huacainfo.ace.cu.vo.*;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("cuProjectService")
/**
 * @author: Arvin
 * @version: 2018-06-13
 * @Description: TODO(慈善项目)
 */
public class CuProjectServiceImpl implements CuProjectService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CuProjectDao cuProjectDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private CuProjectUseRecordService cuProjectUseRecordService;
    @Autowired
    private CuDonateListService cuDonateListService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(慈善项目分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CuProjectVo>
     * @author: Arvin
     * @version: 2018-06-13
     */
    @Override
    public PageResult<CuProjectVo> findCuProjectList(CuProjectQVo condition, int start,
                                                     int limit, String orderBy) throws Exception {
        PageResult<CuProjectVo> rst = new PageResult<>();
        List<CuProjectVo> list = this.cuProjectDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.cuProjectDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCuProject
     * @Description: TODO(添加慈善项目)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-13
     */
    @Override
    public MessageResponse insertCuProject(CuProject o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getProjectName())) {
            return new MessageResponse(1, "项目名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getType())) {
            return new MessageResponse(1, "项目类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getDescription())) {
            return new MessageResponse(1, "项目详情不能为空！");
        }
        if (CommonUtils.isBlank(o.getStartDate())) {
            return new MessageResponse(1, "项目开始时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getEndDate())) {
            return new MessageResponse(1, "项目结束时间不能为空！");
        }

        int temp = this.cuProjectDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "慈善项目名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setParentId("0");
        o.setStatus("1");
        o.setCreateDate(DateUtil.getNowDate());
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setLastModifyDate(DateUtil.getNowDate());
        this.cuProjectDao.insertSelective(o);
        this.dataBaseLogService.log("添加慈善项目", "慈善项目", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加慈善项目完成！");
    }

    /**
     * @throws
     * @Title:updateCuProject
     * @Description: TODO(更新慈善项目)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-13
     */
    @Override
    public MessageResponse updateCuProject(CuProject o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getProjectName())) {
            return new MessageResponse(1, "项目名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getType())) {
            return new MessageResponse(1, "项目类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getDescription())) {
            return new MessageResponse(1, "项目详情不能为空！");
        }
        if (CommonUtils.isBlank(o.getStartDate())) {
            return new MessageResponse(1, "项目开始时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getEndDate())) {
            return new MessageResponse(1, "项目结束时间不能为空！");
        }

        int temp = this.cuProjectDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "慈善项目名称重复！");
        }

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.cuProjectDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更慈善项目", "慈善项目", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更慈善项目完成！");
    }

    /**
     * @throws
     * @Title:selectCuProjectByPrimaryKey
     * @Description: TODO(获取慈善项目)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CuProject>
     * @author: Arvin
     * @version: 2018-06-13
     */
    @Override
    public SingleResult<CuProjectVo> selectCuProjectByPrimaryKey(String id) throws Exception {
        SingleResult<CuProjectVo> rst = new SingleResult<>();
        rst.setValue(this.cuProjectDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCuProjectByCuProjectId
     * @Description: TODO(删除慈善项目)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-13
     */
    @Override
    public MessageResponse deleteCuProjectByCuProjectId(String id, UserProp userProp) throws Exception {
        this.cuProjectDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除慈善项目", "慈善项目",
                String.valueOf(id),
                String.valueOf(id), "慈善项目", userProp);
        return new MessageResponse(0, "慈善项目删除完成！");
    }

    /**
     * 查询项目列表
     *
     * @param type    项目类型 0-普通项目 1-专项项目 2-个人项目 3-支出项目
     * @param start   分页开始位置  --  必选
     * @param limit   页数  --  必选
     * @param orderBy 排序条件   --  可选，默认时间倒叙
     * @return
     * @throws Exception
     */
    @Override
    public ResultResponse findList(String type, int start, int limit, String orderBy) throws Exception {
        CuProjectQVo condition = new CuProjectQVo();
        condition.setType(type);

        PageResult<CuProjectVo> rs = findCuProjectList(condition, start, limit, orderBy);
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rs);
    }

    /**
     * 查询项目详情
     *
     * @param projectId 慈善项目ID
     * @return
     * @throws Exception
     */
    @Override
    public ResultResponse findDetail(String projectId) {
        CuProjectVo vo = cuProjectDao.selectVoByPrimaryKey(projectId);
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", vo);
    }

    /**
     * 查询项目使用记录
     *
     * @param projectId 慈善项目ID
     * @param start     分页开始位置  --  必选
     * @param limit     页数  --  必选
     * @param orderBy   排序条件   --  可选，默认时间倒叙
     * @return
     * @throws Exception
     */
    @Override
    public ResultResponse findUsedRecordList(String projectId, int start, int limit, String orderBy) throws Exception {
        CuProjectUseRecordQVo condition = new CuProjectUseRecordQVo();
        condition.setProjectId(projectId);

        PageResult<CuProjectUseRecordVo> rs = cuProjectUseRecordService.findCuProjectUseRecordList(condition,
                start, limit, orderBy);
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rs);
    }

    /**
     * 查询项目 - 捐赠列表
     *
     * @param projectId 慈善项目ID
     * @param start     分页开始位置  --  必选
     * @param limit     页数  --  必选
     * @param orderBy   排序条件   --  可选，默认时间倒叙
     * @return
     * @throws Exception
     */
    @Override
    public ResultResponse findDonateList(String projectId, int start, int limit, String orderBy) throws Exception {
        CuDonateListQVo condition = new CuDonateListQVo();
        condition.setProjectId(projectId);

        PageResult<CuDonateListVo> rs = cuDonateListService.findCuDonateListList(condition,
                start, limit, orderBy);
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rs);
    }

}

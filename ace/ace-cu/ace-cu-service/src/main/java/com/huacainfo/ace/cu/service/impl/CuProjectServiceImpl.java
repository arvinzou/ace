package com.huacainfo.ace.cu.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.cu.common.constant.ProjectConstant;
import com.huacainfo.ace.cu.dao.CuProjectDao;
import com.huacainfo.ace.cu.model.CuDonateOrder;
import com.huacainfo.ace.cu.model.CuProject;
import com.huacainfo.ace.cu.model.CuProjectUseRecord;
import com.huacainfo.ace.cu.service.CuDonateListService;
import com.huacainfo.ace.cu.service.CuProcessRecordService;
import com.huacainfo.ace.cu.service.CuProjectService;
import com.huacainfo.ace.cu.service.CuProjectUseRecordService;
import com.huacainfo.ace.cu.vo.*;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private CuProcessRecordService cuProcessRecordService;

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
        o.setParentId(CommonUtils.isEmpty(o.getParentId()) ? "0" : o.getParentId());
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

        if (ProjectConstant.P_TYPE_PAY_OUT.equals(o.getType())) {
            CuProject p = cuProjectDao.selectByPrimaryKey(o.getId());
            if (o.getTargetAmount().compareTo(p.getTargetAmount()) != 0) {
                return new MessageResponse(1, "支出项目，支出金额不得调整！");
            }
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
        condition.setStatus("2");//'2' : "通过";
        condition.setStarted("1");//项目是否启动  0-否 1-true

        PageResult<CuProjectVo> rs = findCuProjectList(condition, start, limit, orderBy);
        List<CuProjectVo> list = rs.getRows();
        for (CuProjectVo projectVo : list) {
            setBalanceDays(projectVo);
            //统计 今日募集、今日捐赠人数
            if (ProjectConstant.P_TYPE_SPECIAL.equals(projectVo.getType())) {
                dataStatistics(projectVo);
            }
        }

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rs);
    }

    /**
     * 数据统计
     *
     * @param projectVo
     */
    private void dataStatistics(CuProjectVo projectVo) {
        //今日时间
        Map<String, String> dateMap = DateUtil.getDateMap(DateUtil.getNow());
        String today = dateMap.get("year") + "-" + dateMap.get("month") + "-" + dateMap.get("day");
        //查询条件
        Map<String, Object> condition = new HashMap<>();
        condition.put("projectId", projectVo.getId());
        condition.put("startDate", today + " 00:00:00");
        condition.put("endDate", today + " 23:59:59");
        //数值录入
        BigDecimal todayDonateAmount = cuDonateListService.findDonateAmount(condition);
        int todayDonateCount = cuDonateListService.findDonateCount(condition);
        projectVo.setTodayDonateAmount(null == todayDonateAmount ? BigDecimal.ZERO : todayDonateAmount);
        projectVo.setTodayDonateCount(todayDonateCount);
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

    @Override
    public CuProjectVo selectVoByPrimaryKey(String projectId) {

        CuProjectVo vo = cuProjectDao.selectVoByPrimaryKey(projectId);
        return setBalanceDays(vo);
    }

    /**
     * 项目付款成功，增加“已筹集”，“结余”金额
     *
     * @param order 订单信息
     * @return 处理结果
     */
    @Override
    public ResultResponse pay(CuDonateOrder order) {
        CuProject project = cuProjectDao.selectByPrimaryKey(order.getProjectId());
        BigDecimal amount = order.getDonateAmount();
        BigDecimal collectAmount = null == project.getCollectAmount() ? BigDecimal.ZERO : project.getCollectAmount();
        BigDecimal balanceAmount = null == project.getBalanceAmount() ? BigDecimal.ZERO : project.getBalanceAmount();
        project.setCollectAmount(collectAmount.add(amount));
        project.setBalanceAmount(balanceAmount.add(amount));
        project.setLastModifyDate(DateUtil.getNowDate());

        int count = cuProjectDao.updateByPrimaryKeySelective(project);
        if (count == 1) {
            return new ResultResponse(ResultCode.SUCCESS, "更新成功", project);
        }

        return new ResultResponse(ResultCode.FAIL, "更新失败", project);
    }

    /**
     * 功能描述:  慈善项目审核
     *
     * @param: id cu_project.id
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/8 18:19
     */
    @Override
    public MessageResponse audit(String id, String auditResult, String auditOpinion, UserProp userProp) {
        CuProject project = cuProjectDao.selectByPrimaryKey(id);
        if (null == project) {
            return new MessageResponse(ResultCode.FAIL, "项目资料丢失");
        }
        if (ProjectConstant.P_STATUS_PASSED.equals(project.getStatus())) {
            return new MessageResponse(ResultCode.FAIL, "项目已审核，请勿重复审核!");
        }

        String status = "0".equals(auditResult.trim()) ?
                ProjectConstant.P_STATUS_PASSED : ProjectConstant.P_STATUS_REJECTED;
        project.setStatus(status);
        project.setLastModifyDate(DateUtil.getNowDate());
        project.setLastModifyUserId(userProp.getUserId());
        project.setLastModifyUserName(userProp.getName());
        cuProjectDao.updateByPrimaryKeySelective(project);

        //个人项目流程记录
        if (ProjectConstant.P_TYPE_PERSONAL.equals(project.getType())) {
            cuProcessRecordService.recordProjectAudit(auditResult, auditOpinion, project);
        }


        return new MessageResponse(ResultCode.FAIL, "审核成功!");
    }

    /**
     * 分类插入项目
     *
     * @param obj
     * @param type
     * @param curUserProp
     * @return
     */
    @Override
    public MessageResponse insertCuProjectByType(CuProject obj, String type, UserProp curUserProp) throws Exception {
        if (ProjectConstant.P_TYPE_PAY_OUT.equals(type)) {
            return insertPayOutProject(obj, curUserProp);
        }
        return insertCuProject(obj, curUserProp);
    }

    @Override
    public List<CuProject> findAllProjectList(String projectType) {

        return cuProjectDao.findListByType(projectType.split(","));
    }

    /**
     * 项目启动/上线
     *
     * @param projectId
     * @return
     */
    @Override
    public MessageResponse setup(String projectId, UserProp userProp) {
        CuProject project = cuProjectDao.selectByPrimaryKey(projectId);
        if (null == project) {
            return new MessageResponse(ResultCode.FAIL, "项目资料丢失");
        }

        project.setStarted("1");//0-下线 1-上线
        project.setLastModifyDate(DateUtil.getNowDate());
        project.setLastModifyUserId(userProp.getUserId());
        project.setLastModifyUserName(userProp.getName());
        cuProjectDao.updateByPrimaryKeySelective(project);

        //个人项目流程记录
        if (ProjectConstant.P_TYPE_PERSONAL.equals(project.getType())) {
            cuProcessRecordService.recordProjectSetup(project);
        }
        return new MessageResponse(ResultCode.SUCCESS, "上线成功");
    }

    /**
     * 项目关闭
     *
     * @param projectId
     */
    @Override
    public MessageResponse shutDown(String projectId, String reason, UserProp userProp) {
        CuProject project = cuProjectDao.selectByPrimaryKey(projectId);
        if (null == project) {
            return new MessageResponse(ResultCode.FAIL, "项目资料丢失");
        }

        project.setStarted("0");//0-下线 1-上线
        project.setLastModifyDate(DateUtil.getNowDate());
        project.setLastModifyUserId(userProp.getUserId());
        project.setLastModifyUserName(userProp.getName());
        cuProjectDao.updateByPrimaryKeySelective(project);

        //个人项目流程记录
        if (ProjectConstant.P_TYPE_PERSONAL.equals(project.getType())) {
            cuProcessRecordService.recordProjectShutDown(reason, project);
        }
        return new MessageResponse(ResultCode.SUCCESS, "关闭成功");
    }

    /**
     * 查询项目 - 捐赠列表 -- 当日一天内的数据结果
     *
     * @param projectId 慈善项目ID
     * @param start     分页开始位置  --  必选
     * @param limit     页数  --  必选
     * @param orderBy   排序条件   --  可选，默认时间倒叙
     * @return
     * @throws Exception
     */
    @Override
    public ResultResponse findDonateListToday(String projectId, int start, int limit, String orderBy) throws Exception {

        CuDonateListQVo condition = new CuDonateListQVo();
        condition.setProjectId(projectId);
        condition.setTodayDate(DateUtil.getNow().substring(0, 10));

        PageResult<CuDonateListVo> rs = cuDonateListService.findCuDonateListList(condition,
                start, limit, orderBy);
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rs);
    }

    /**
     * 新增支出项目& 使用记录
     *
     * @param o
     * @param userProp
     * @return
     */
    private MessageResponse insertPayOutProject(CuProject o, UserProp userProp) throws Exception {
        //生成支出项目资料
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
        if (o.getTargetAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return new MessageResponse(1, "支出金额输入有误！");
        }
        //源头项目
        CuProject srcProject = cuProjectDao.selectByPrimaryKey(o.getParentId());
        if (null == srcProject) {
            return new MessageResponse(1, "源项目资料丢失！");
        }
        int temp = this.cuProjectDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "慈善项目名称重复！");
        }
        o.setId(GUIDUtil.getGUID());
//        o.setParentId("0");
        o.setStatus("1");
        o.setCreateDate(DateUtil.getNowDate());
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setLastModifyDate(DateUtil.getNowDate());
        cuProjectDao.insertSelective(o);
        dataBaseLogService.log("添加慈善项目", "慈善项目", "", o.getId(), o.getId(), userProp);

        //生成使用记录;
        CuProjectUseRecord record = new CuProjectUseRecord();
        record.setProjectId(o.getParentId());
        record.setUseProjectId(o.getId());
        record.setUseAmount(o.getTargetAmount());
        MessageResponse recordRs = cuProjectUseRecordService.insertCuProjectUseRecord(record, userProp);
        if (!recordRs.getState()) {
            throw new CustomException(ResultCode.FAIL, recordRs.getErrorMessage());
        }
        //调整源项目支出金额
        BigDecimal useAmount = o.getTargetAmount();
        BigDecimal payOutAmount = null == srcProject.getPayoutAmount() ?
                useAmount : srcProject.getPayoutAmount().add(useAmount);
        BigDecimal balanceAmount = srcProject.getCollectAmount().subtract(payOutAmount);
        if (balanceAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new CustomException(ResultCode.FAIL, "项目资金不足");
        }
        srcProject.setPayoutAmount(payOutAmount);
        srcProject.setBalanceAmount(balanceAmount);
        srcProject.setLastModifyUserName(userProp.getName());
        srcProject.setLastModifyUserId(userProp.getUserId());
        srcProject.setLastModifyDate(DateUtil.getNowDate());
        cuProjectDao.updateByPrimaryKeySelective(srcProject);

        return new MessageResponse(ResultCode.SUCCESS, "成功添加使用记录！");
    }

    /**
     * 计算项目剩余天数
     *
     * @param projectVo
     * @return
     */
    private CuProjectVo setBalanceDays(CuProjectVo projectVo) {
        long balanceDays = 0;
        if (null != projectVo.getEndDate()) {
            balanceDays = getDiffDays(DateUtil.getNowDate(), projectVo.getEndDate());
        }
        projectVo.setBalanceDays(balanceDays < 0 ? 0 : balanceDays);

        return projectVo;
    }

    /**
     * 计算2个date时间的 差距天数
     *
     * @param begin
     * @param end
     * @return
     */
    private long getDiffDays(Date begin, Date end) {
        long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
        return between / (24 * 3600);
    }
}

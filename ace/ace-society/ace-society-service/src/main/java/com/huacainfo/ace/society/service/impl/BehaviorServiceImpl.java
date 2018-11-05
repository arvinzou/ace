package com.huacainfo.ace.society.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.constant.AuditState;
import com.huacainfo.ace.society.constant.BisType;
import com.huacainfo.ace.society.constant.CoinConfigType;
import com.huacainfo.ace.society.dao.BehaviorAnnexDao;
import com.huacainfo.ace.society.dao.BehaviorDao;
import com.huacainfo.ace.society.model.Behavior;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.service.BehaviorService;
import com.huacainfo.ace.society.service.PointsRecordService;
import com.huacainfo.ace.society.vo.BehaviorAnnexQVo;
import com.huacainfo.ace.society.vo.BehaviorAnnexVo;
import com.huacainfo.ace.society.vo.BehaviorQVo;
import com.huacainfo.ace.society.vo.BehaviorVo;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service("behaviorService")
/**
 * @author: lcan
 * @version: 2018-09-11
 * @Description: TODO(市民行为详情)
 */
public class BehaviorServiceImpl implements BehaviorService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BehaviorDao behaviorDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private BehaviorAnnexDao behaviorAnnexDao;
    @Autowired
    private AuditRecordService auditRecordService;
    @Autowired
    private SqlSessionTemplate sqlSession;
    @Autowired
    private PointsRecordService pointsRecordService;

    private SqlSession getSqlSession() {
        SqlSession session = sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);

        return session;
    }

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(市民行为详情分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <BehaviorVo>
     * @author: lcan
     * @version: 2018-09-11
     */
    @Override
    public PageResult<BehaviorVo> findBehaviorList(BehaviorQVo condition, int start,
                                                   int limit, String orderBy) throws Exception {
        //sql
        SqlSession session = getSqlSession();
        BehaviorDao dao = session.getMapper(BehaviorDao.class);
        //query
        PageResult<BehaviorVo> rst = new PageResult<>();
        try {
            List<BehaviorVo> list = dao.findList(condition, start, limit, orderBy);
            rst.setRows(list);
            if (start <= 1) {
                int allRows = behaviorDao.findCount(condition);
                rst.setTotal(allRows);
            }
            return rst;
        } catch (Exception e) {
            logger.error("{}", e);
            if (session != null) {
                session.close();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertBehavior
     * @Description: TODO(添加市民行为详情)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: lcan
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse insertBehavior(Behavior o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键-GUID不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "提交人不能为空！");
        }
        if (CommonUtils.isBlank(o.getSubmitDate())) {
            return new MessageResponse(1, "反馈日期不能为空！");
        }


        int temp = this.behaviorDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "市民行为详情名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.behaviorDao.insertSelective(o);
        this.dataBaseLogService.log("添加市民行为详情", "市民行为详情", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加市民行为详情完成！");
    }

    /**
     * @throws
     * @Title:updateBehavior
     * @Description: TODO(更新市民行为详情)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: lcan
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse updateBehavior(Behavior o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键-GUID不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "提交人不能为空！");
        }
        if (CommonUtils.isBlank(o.getSubmitDate())) {
            return new MessageResponse(1, "反馈日期不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.behaviorDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更市民行为详情", "市民行为详情", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更市民行为详情完成！");
    }

    /**
     * @throws
     * @Title:selectBehaviorByPrimaryKey
     * @Description: TODO(获取市民行为详情)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Behavior>
     * @author: lcan
     * @version: 2018-09-11
     */
    @Override
    public SingleResult<BehaviorVo> selectBehaviorByPrimaryKey(String id) throws Exception {
        SingleResult<BehaviorVo> rst = new SingleResult<>();
        BehaviorVo behaviorVo = behaviorDao.selectVoByPrimaryKey(id);

        //市民随手拍详情
        BehaviorAnnexQVo annexQVo = new BehaviorAnnexQVo();
        annexQVo.setBehaviorId(id);
        List<BehaviorAnnexVo> annexList = behaviorAnnexDao.findList(annexQVo, 0, 99, null);
        behaviorVo.setBehaviorAnnexList(annexList);
        rst.setValue(behaviorVo);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteBehaviorByBehaviorId
     * @Description: TODO(删除市民行为详情)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: lcan
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse deleteBehaviorByBehaviorId(String id, UserProp userProp) throws
            Exception {
        this.behaviorDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除市民行为详情", "市民行为详情",
                String.valueOf(id),
                String.valueOf(id), "市民行为详情", userProp);
        return new MessageResponse(0, "市民行为详情删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核市民行为详情)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: lcan
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse audit(String id, String userId, String userType,
                                 String rst, String remark, UserProp userProp) throws Exception {
        Behavior behavior = behaviorDao.selectByPrimaryKey(id);
        if (null == behavior) {
            return new MessageResponse(ResultCode.FAIL, "市民文明拍信息丢失！");
        }
        //更改审核记录
        MessageResponse auditRs = auditRecordService.audit(BisType.BEHAVIOR, behavior.getId(),
                userId, rst, remark, userProp);
        if (ResultCode.FAIL == auditRs.getStatus()) {
            return auditRs;
        }
        behavior.setId(id);
        behavior.setStatus(rst);
        behavior.setLastModifyDate(new Date());
        behavior.setLastModifyUserId(userProp.getUserId());
        behavior.setLastModifyUserName(userProp.getName());
        behaviorDao.updateByPrimaryKeySelective(behavior);
        //积分变动
        if ("3".equals(rst)) {
            ResultResponse pointsRst = pointsRecordService.addPoints(userId, CoinConfigType.TAKE_A_PHOTO, id);
            logger.debug(JsonUtil.toJson(pointsRst));
        }

        dataBaseLogService.log("审核市民行为详情", "市民行为详情",
                String.valueOf(id), String.valueOf(id), "市民行为详情", userProp);
        return new MessageResponse(0, "市民行为详情审核完成！");
    }

    /**
     * 提交文明随手拍
     *
     * @param params 提交参数
     */
    @Override
    public ResultResponse submit(BehaviorVo params) {
        if (StringUtil.isEmpty(params.getUserId())) {
            return new ResultResponse(ResultCode.FAIL, "提交人ID不能为空");
        }
        if (StringUtil.isEmpty(params.getType())) {
            return new ResultResponse(ResultCode.FAIL, "行为类型不能为空");
        }
        if (StringUtil.isEmpty(params.getCompendium())) {
            return new ResultResponse(ResultCode.FAIL, "简述不能为空");
        }
        String behaviorId = GUIDUtil.getGUID();
        params.setSubmitDate(DateUtil.getNowDate());

        params.setId(behaviorId);
        params.setStatus("1");
        params.setCreateDate(new Date());
        params.setCreateUserName("system");
        params.setCreateUserId("8888");
        behaviorDao.insert(params);
        //附件保存
        if (!CollectionUtils.isEmpty(params.getBehaviorAnnexList())) {
            for (BehaviorAnnexVo annex : params.getBehaviorAnnexList()) {
                annex.setId(GUIDUtil.getGUID());
                annex.setBehaviorId(behaviorId);
                annex.setStatus("1");
                annex.setCreateDate(new Date());
                annex.setCreateUserName("system");
                annex.setCreateUserId("8888");
                behaviorAnnexDao.insert(annex);
            }
        }
        //自动送审
        sendAudit(behaviorId, params.getUserId());

        return new ResultResponse(ResultCode.SUCCESS, "提交成功");
    }

    /**
     * 送审
     *
     * @param id      主键ID society.behavior.id
     * @param unionId
     * @return ResultResponse
     * @throws Exception
     */
    @Override
    public ResultResponse sendAudit(String id, String unionId) {
        Behavior obj = behaviorDao.selectByPrimaryKey(id);
        if (null == obj) {
            return new ResultResponse(ResultCode.FAIL, "数据记录丢失");
        }
        //
        if (AuditState.SUBMIT_AUDIT.equals(obj.getStatus())) {
            return new ResultResponse(ResultCode.FAIL, "请勿重复送审");
        }
        //提交审核
        auditRecordService.submit(GUIDUtil.getGUID(), BisType.BEHAVIOR, id, unionId);
        //更新单据状态
        Behavior record = new Behavior();
        record.setId(id);
        record.setStatus(AuditState.SUBMIT_AUDIT);
        record.setLastModifyDate(DateUtil.getNowDate());
        record.setLastModifyUserId("system");
        record.setLastModifyUserId("8888");
        behaviorDao.updateStatus(record);

        return new ResultResponse(ResultCode.SUCCESS, "送审成功");
    }

    /**
     * 获取详情
     *
     * @param id      主键ID society.behavior.id
     * @param unionId
     * @return ResultResponse
     * @throws Exception
     */
    @Override
    public ResultResponse getDetail(String id, String unionId) throws Exception {
        BehaviorVo obj = selectBehaviorByPrimaryKey(id).getValue();
        if (obj == null) {
            return new ResultResponse(ResultCode.FAIL, "数据记录丢失");
        }

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", obj);
    }

}

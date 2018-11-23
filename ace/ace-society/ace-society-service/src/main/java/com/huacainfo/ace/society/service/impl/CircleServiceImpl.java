package com.huacainfo.ace.society.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.StringUtils;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.constant.AuditState;
import com.huacainfo.ace.society.constant.BisType;
import com.huacainfo.ace.society.constant.CoinConfigType;
import com.huacainfo.ace.society.dao.CircleDao;
import com.huacainfo.ace.society.dao.CircleImgDao;
import com.huacainfo.ace.society.dao.CircleLogDao;
import com.huacainfo.ace.society.dao.CoinConfigDao;
import com.huacainfo.ace.society.model.*;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.service.CircleService;
import com.huacainfo.ace.society.service.PointsRecordService;
import com.huacainfo.ace.society.vo.CircleQVo;
import com.huacainfo.ace.society.vo.CircleVo;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("circleService")
public class CircleServiceImpl implements CircleService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CircleDao circleDao;
    @Autowired
    private CircleImgDao circleImgDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private CircleLogDao circleLogDao;
    @Autowired
    private PointsRecordService pointsRecordService;

    @Autowired
    private AuditRecordService auditRecordService;
    @Autowired
    private SqlSessionTemplate sqlSession;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(圈子分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CircleVo>
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @Override
    public PageResult<CircleVo> findCircleList(CircleQVo condition, int start, int limit, String orderBy) throws Exception {
        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);
        CircleDao dao = session.getMapper(CircleDao.class);
        PageResult<CircleVo> rst = new PageResult<>();
        try {
            List<CircleVo> list = dao.findList(condition, start, limit, orderBy);
            rst.setRows(list);
            if (start <= 1) {
                int allRows = dao.findCount(condition);
                rst.setTotal(allRows);
            }
        }catch (Exception e){
            session.close();
        }finally {
            session.close();
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCircle
     * @Description: TODO(添加圈子)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @Override
    public MessageResponse insertCircle(Circle o, List<CircleImg> imgs) throws Exception {
        if (CommonUtils.isBlank(o.getCorpId())) {
            return new MessageResponse(1, "单位编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getUid())) {
            return new MessageResponse(1, "用户编号不能为空！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setStatus("1");
        o.setCreateTime(new Date());
        for (CircleImg img : imgs) {
            img.setId(GUIDUtil.getGUID());
            img.setCircleId(o.getId());
            img.setCreateTime(new Date());
            this.circleImgDao.insert(img);
        }
        this.circleDao.insert(o);

        //自动送审
        sendAudit(o.getId(), o.getUid());
        return new MessageResponse(0, "提交成功！");
    }

    /**
     * @throws
     * @Title:updateCircle
     * @Description: TODO(更新圈子)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @Override
    public MessageResponse updateCircle(Circle o, List<CircleImg> imgs) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "单位编号不能为空！");
        }
        o.setStatus("1");
        o.setCreateTime(new Date());
        this.circleImgDao.deleteByCircleId(o.getId());
        for (CircleImg img : imgs) {
            img.setId(GUIDUtil.getGUID());
            img.setCircleId(o.getId());
            img.setCreateTime(new Date());
            this.circleImgDao.insert(img);
        }
        this.circleDao.updateByPrimaryKey(o);
        return new MessageResponse(0, "提交成功！");
    }

    /**
     * @throws
     * @Title:selectCircleByPrimaryKey
     * @Description: TODO(获取圈子)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Circle>
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @Override
    public SingleResult
            <CircleVo> selectCircleByPrimaryKey(String id) throws Exception {
        SingleResult<CircleVo> rst = new SingleResult<>();
        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);
        CircleDao dao = session.getMapper(CircleDao.class);
        try {
            rst.setValue(dao.selectVoByPrimaryKey(id));
        }catch (Exception e){
            session.close();
        }finally {
            session.close();
        }

        return rst;
    }

    /**
     * @throws
     * @Title:deleteCircleByCircleId
     * @Description: TODO(删除圈子)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @Override
    public MessageResponse deleteCircleByCircleId(String id, UserProp userProp) throws
            Exception {
        this.circleDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除圈子", "圈子", id, id,
                "圈子", userProp);
        return new MessageResponse(0, "圈子删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核圈子)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    @Override
    public MessageResponse audit(String id,String rst,String text, UserProp userProp) throws Exception {
        if (StringUtils.isEmpty(id)) {
            return new MessageResponse(ResultCode.FAIL, "报道ID不能为空!");
        }
        if (StringUtils.isEmpty(rst)) {
            return new MessageResponse(ResultCode.FAIL, "审核结果不能为空!");
        }
        if (StringUtils.isEmpty(text)) {
            return new MessageResponse(ResultCode.FAIL, "审核说明不能为空!");
        }
        Circle circle=circleDao.selectVoByPrimaryKey(id);
        if(CommonUtils.isBlank(circle)){
            return new MessageResponse(ResultCode.FAIL, "邻里圈子数据丢失!");
        }
        //更改审核记录
        MessageResponse auditRs = auditRecordService.audit(BisType.CIRCLE, circle.getId(),
                circle.getUid(), rst, text, userProp);
        if (ResultCode.FAIL == auditRs.getStatus()) {
            return auditRs;
        }
        String logId=GUIDUtil.getGUID();
        CircleLog log=new CircleLog();
        log.setId(logId);
        log.setPid(id);
        log.setAuditor(userProp.getName());
        log.setRst(rst);
        log.setStatement(text);
        log.setCreateDate(new Date());
        if("3".equals(rst)){
            ResultResponse pointsRst = pointsRecordService.addPoints(circle.getUid(), CoinConfigType.GROUP, id);
            if(pointsRst.getStatus()==1){
                return new MessageResponse(pointsRst.getStatus(), pointsRst.getInfo());
            }
        }
        this.circleLogDao.insert(log);
        this.circleDao.updateStatus(id,rst,logId);

        dataBaseLogService.log("审核圈子", "圈子", id, id,"圈子", userProp);
        return new MessageResponse(ResultCode.SUCCESS, "审核成功！");
    }

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(圈子获取列表)
     * @param: @param start 开始行号
     * @param: @param limit 页面展示行数
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-10-15
     */
    @Override
    public List<Rpt> getList(String uid,String status,int start, int limit)  throws Exception{
        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);
        CircleDao dao = session.getMapper(CircleDao.class);
        try {
            return dao.getList(status,uid,start,limit);
        }catch (Exception e){
           this.logger.error("{}",e);
            session.close();
        }finally {
            session.close();
        }
        return null;
    }
    /**
     * @throws
     * @Title:updateAddLike
     * @Description: TODO(点赞)
     * @param: @param id
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-10-16
     */
    @Override
    public MessageResponse updateAddLike(String id){
        this.circleDao.updateAddLike(id);
        return new MessageResponse(0, "点赞成功！");
    }

    /**
     * 邻里圈子自动送审
     * @param id
     * @param unionId
     * @return
     */
    @Override
    public ResultResponse sendAudit(String id, String unionId) {
        CircleVo circleVo = circleDao.selectVoByPrimaryKey(id);
        if (null == circleVo) {
            return new ResultResponse(ResultCode.FAIL, "邻里圈子数据丢失！");
        }
        //
        if (AuditState.SUBMIT_AUDIT.equals(circleVo.getStatus())) {
            return new ResultResponse(ResultCode.FAIL, "请勿重复送审");
        }
        //提交审核
        auditRecordService.submit(GUIDUtil.getGUID(), BisType.CIRCLE, id, unionId);
        //更新单据状态
        Circle record = new Circle();
        record.setId(id);
        record.setStatus(AuditState.SUBMIT_AUDIT);
        circleDao.updateByPrimaryKey(record);

        return new ResultResponse(ResultCode.SUCCESS, "送审成功");
    }
}

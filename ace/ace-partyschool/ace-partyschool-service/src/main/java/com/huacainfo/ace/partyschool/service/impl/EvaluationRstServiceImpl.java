package com.huacainfo.ace.partyschool.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.pushmsg.CommonUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.partyschool.dao.ClassScheduleDao;
import com.huacainfo.ace.partyschool.model.EvaluationRstContent;
import com.huacainfo.ace.partyschool.service.EvaluationRstContentService;
import com.huacainfo.ace.partyschool.vo.ClassScheduleVo;
import com.huacainfo.ace.partyschool.vo.EvaluationRstContentQVo;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.partyschool.dao.EvaluationRstDao;
import com.huacainfo.ace.partyschool.model.EvaluationRst;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.partyschool.service.EvaluationRstService;
import com.huacainfo.ace.partyschool.vo.EvaluationRstVo;
import com.huacainfo.ace.partyschool.vo.EvaluationRstQVo;

@Service("evaluationRstService")
/**
 * @author: 王恩
 * @version: 2019-01-08
 * @Description: TODO(测评结果管理)
 */
public class EvaluationRstServiceImpl implements EvaluationRstService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvaluationRstDao evaluationRstDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private SqlSessionTemplate sqlSession;
    @Autowired
    private EvaluationRstContentService evaluationRstContentService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(测评结果管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <EvaluationRstVo>
     * @author: 王恩
     * @version: 2019-01-08
     */
    @Override
    public PageResult<EvaluationRstVo> findEvaluationRstList(EvaluationRstQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<EvaluationRstVo> rst = new PageResult<>();
        List<EvaluationRstVo> list = this.evaluationRstDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        return rst;
    }




    @Override
    public PageResult<EvaluationRstVo> findEvaluationRstListVo(EvaluationRstQVo condition, int start,
                                                               int limit, String orderBy) throws Exception {
        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);
        EvaluationRstDao dao = session.getMapper(EvaluationRstDao.class);
        PageResult<EvaluationRstVo> rst = new PageResult<>();
        try {
            List<EvaluationRstVo> list = dao.findListVo(condition, start, limit, orderBy);
            rst.setRows(list);
            if (start <= 1) {
                int allRows = dao.findCountVo(condition);
                rst.setTotal(allRows);
            }
        } catch (Exception e) {
            session.close();
        } finally {
            session.close();
        }
        return rst;
    }

    @Override
    public ResultResponse insertEvaluationRstList(List<EvaluationRst> list, EvaluationRstContent obj, UserProp userProp) throws Exception{
            for(EvaluationRst item:list){
                MessageResponse mr=insertEvaluationRst(item,userProp);
                if(mr.getStatus()==1){
                   throw  new Exception(mr.getErrorMessage());
                }
            }
            if(!CommonUtils.isBlank(obj.getContent())){
                evaluationRstContentService.insertEvaluationRstContent(obj,userProp);
            }
            return new ResultResponse(ResultCode.SUCCESS,"提交成功");
    }
    /**
     * @throws
     * @Title:insertEvaluationRst
     * @Description: TODO(添加测评结果管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-08
     */
    @Override
    public MessageResponse insertEvaluationRst(EvaluationRst o, UserProp userProp) throws Exception {

        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvaluatingId())) {
            return new MessageResponse(1, "所属评测方案不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "指标名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getClassScheduleId())) {
            return new MessageResponse(1, "所属课程不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntroduce())) {
            return new MessageResponse(1, "指标内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getScore())) {
            return new MessageResponse(1, "分值不能为空！");
        }
        o.setUserId(userProp.getUserId());
        o.setCreateDate(new Date());
        this.evaluationRstDao.insert(o);
        this.dataBaseLogService.log("添加测评结果管理", "测评结果管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加测评结果管理完成！");
    }

    /**
     * @throws
     * @Title:updateEvaluationRst
     * @Description: TODO(更新测评结果管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-08
     */
    @Override
    public MessageResponse updateEvaluationRst(EvaluationRst o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvaluatingId())) {
            return new MessageResponse(1, "所属评测方案不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "指标名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getClassScheduleId())) {
            return new MessageResponse(1, "所属课程不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntroduce())) {
            return new MessageResponse(1, "指标内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getScore())) {
            return new MessageResponse(1, "分值不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "评测人不能为空！");
        }
        this.evaluationRstDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更测评结果管理", "测评结果管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更测评结果管理完成！");
    }

    /**
     * @throws
     * @Title:selectEvaluationRstByPrimaryKey
     * @Description: TODO(获取测评结果管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluationRst>
     * @author: 王恩
     * @version: 2019-01-08
     */
    @Override
    public SingleResult<EvaluationRstVo> selectEvaluationRstByPrimaryKey(String id) throws Exception {
        SingleResult
                <EvaluationRstVo> rst = new SingleResult<>();
        rst.setValue(this.evaluationRstDao.selectVoByPrimaryKey(id));
        return rst;
    }

    @Override
    public ResultResponse statistics(String classScheduleId) throws Exception {
        List<Map<String,String>> map=this.evaluationRstDao.statistics(classScheduleId);
        return new ResultResponse(0,"成功",map);
    }



    /**
     * @throws
     * @Title:deleteEvaluationRstByEvaluationRstId
     * @Description: TODO(删除测评结果管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-08
     */
    @Override
    public MessageResponse deleteEvaluationRstByEvaluationRstId(String id, UserProp userProp) throws
            Exception {
        this.evaluationRstDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除测评结果管理", "测评结果管理", id, id,
                "测评结果管理", userProp);
        return new MessageResponse(0, "测评结果管理删除完成！");
    }

}

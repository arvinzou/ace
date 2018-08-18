package com.huacainfo.ace.portal.service.impl;


import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.portal.dao.EvaluatDataDao;
import com.huacainfo.ace.portal.model.EvaluatData;
import com.huacainfo.ace.portal.service.EvaluatDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.dao.EvaluatGaugeDao;
import com.huacainfo.ace.portal.model.EvaluatGauge;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.EvaluatGaugeService;
import com.huacainfo.ace.portal.vo.EvaluatGaugeVo;
import com.huacainfo.ace.portal.vo.EvaluatGaugeQVo;

@Service("evaluatGaugeService")
/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description: TODO(评测量表)
 */
public class EvaluatGaugeServiceImpl implements EvaluatGaugeService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvaluatGaugeDao evaluatGaugeDao;

    @Autowired
    private EvaluatDataDao evaluatDataDao;

    @Autowired
    private EvaluatDataService evaluatDataService;

    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评测量表分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<EvaluatGaugeVo>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public PageResult<EvaluatGaugeVo> findEvaluatGaugeList(EvaluatGaugeQVo condition, int start,
                                                           int limit, String orderBy) throws Exception {
        PageResult<EvaluatGaugeVo> rst = new PageResult<EvaluatGaugeVo>();
        List<EvaluatGaugeVo> list = this.evaluatGaugeDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.evaluatGaugeDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public ResultResponse getEvaluation(EvaluatGaugeQVo condition, Userinfo userinfo) throws Exception {
        PageResult<EvaluatGaugeVo> rst = new PageResult<EvaluatGaugeVo>();
        EvaluatData evaluatData = new EvaluatData();
        evaluatData.setEvaluatTplId(condition.getEvaluatTplId());
        evaluatData.setScore(condition.getScore());
        evaluatData.setCreateUserId(userinfo.getUnionid());
        evaluatDataService.insertData(evaluatData);
        int ranking = evaluatDataService.getRanking(evaluatData);
        EvaluatGauge list = this.evaluatGaugeDao.getEvaluation(condition);
        Map map = new HashMap();
        map.put("evaluatGauge", list);
        map.put("ranking", ranking + 1);
        return new ResultResponse(0, "评价获取成功", map);
    }


    @Override
    public ResultResponse getMyhistoryRes(EvaluatGaugeQVo condition, Userinfo userinfo) throws Exception {
        EvaluatData evaluatData = new EvaluatData();
        evaluatData.setEvaluatTplId(condition.getEvaluatTplId());
        evaluatData.setCreateUserId(userinfo.getUnionid());
        EvaluatData eval = evaluatDataDao.latestResults(evaluatData);
        condition.setScore(eval.getScore());
        EvaluatGauge list = this.evaluatGaugeDao.getEvaluation(condition);
        Map map = new HashMap();
        map.put("evaluatGauge", list);
        return new ResultResponse(0, "评价获取成功", map);
    }

    /**
     * @throws
     * @Title:insertEvaluatGauge
     * @Description: TODO(添加评测量表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse insertEvaluatGauge(EvaluatGauge o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvaluatTplId())) {
            return new MessageResponse(1, "所属评测模板不能为空！");
        }
        if (CommonUtils.isBlank(o.getScoreStart())) {
            return new MessageResponse(1, "区间开始不能为空！");
        }
        if (CommonUtils.isBlank(o.getScoreEnd())) {
            return new MessageResponse(1, "区间截止不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        o.setCreateDate(new Date());
        this.evaluatGaugeDao.insertSelective(o);
        this.dataBaseLogService.log("添加评测量表", "评测量表", "", o.getEvaluatTplId(),
                o.getEvaluatTplId(), userProp);
        return new MessageResponse(0, "添加评测量表完成！");
    }

    /**
     * @throws
     * @Title:updateEvaluatGauge
     * @Description: TODO(更新评测量表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse updateEvaluatGauge(EvaluatGauge o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvaluatTplId())) {
            return new MessageResponse(1, "所属评测模板不能为空！");
        }
        if (CommonUtils.isBlank(o.getScoreStart())) {
            return new MessageResponse(1, "区间开始不能为空！");
        }
        if (CommonUtils.isBlank(o.getScoreEnd())) {
            return new MessageResponse(1, "区间截止不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        this.evaluatGaugeDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更评测量表", "评测量表", "", o.getEvaluatTplId(),
                o.getEvaluatTplId(), userProp);
        return new MessageResponse(0, "变更评测量表完成！");
    }

    /**
     * @throws
     * @Title:selectEvaluatGaugeByPrimaryKey
     * @Description: TODO(获取评测量表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluatGauge>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public SingleResult<EvaluatGaugeVo> selectEvaluatGaugeByPrimaryKey(String id) throws Exception {
        SingleResult<EvaluatGaugeVo> rst = new SingleResult<EvaluatGaugeVo>();
        rst.setValue(this.evaluatGaugeDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteEvaluatGaugeByEvaluatGaugeId
     * @Description: TODO(删除评测量表)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse deleteEvaluatGaugeByEvaluatGaugeId(String id,
                                                              UserProp userProp) throws Exception {
        this.evaluatGaugeDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除评测量表", "评测量表", String.valueOf(id),
                String.valueOf(id), "评测量表", userProp);
        return new MessageResponse(0, "评测量表删除完成！");
    }

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(获取评测量表列表)
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public Map<String, Object> getList(Map<String, Object> params) throws Exception {
        Map<String, Object> rst = new HashMap<>();
        rst.put("status", 0);
        rst.put("data", this.evaluatGaugeDao.getList(params));
        return rst;
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取评测量表)
     * @param: @param id
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public Map<String, Object> getById(String id) throws Exception {
        Map<String, Object> rst = new HashMap<>();
        rst.put("status", 0);
        rst.put("data", this.evaluatGaugeDao.getById(id));
        return rst;
    }
}

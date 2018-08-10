package com.huacainfo.ace.portal.service.impl;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonTreeUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.dao.EvaluatCaseDao;
import com.huacainfo.ace.portal.dao.EvaluatDataDao;
import com.huacainfo.ace.portal.dao.EvaluatGaugeDao;
import com.huacainfo.ace.portal.model.EvaluatGauge;
import com.huacainfo.ace.portal.service.EvaluatGaugeService;
import org.apache.ibatis.jdbc.SqlRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.dao.EvaluatTplDao;
import com.huacainfo.ace.portal.model.EvaluatTpl;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.EvaluatTplService;
import com.huacainfo.ace.portal.vo.EvaluatTplVo;
import com.huacainfo.ace.portal.vo.EvaluatTplQVo;

@Service("evaluatTplService")
/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description: TODO(评测)
 */
public class EvaluatTplServiceImpl implements EvaluatTplService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvaluatTplDao evaluatTplDao;

    @Autowired
    private EvaluatGaugeService evaluatGaugeService;

    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private EvaluatDataDao evaluatDataDao;

    @Autowired
    private EvaluatGaugeDao evaluatGaugeDao;

    @Autowired
    private EvaluatCaseDao evaluatCaseDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评测分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<EvaluatTplVo>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public PageResult<EvaluatTplVo> findEvaluatTplList(EvaluatTplQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<EvaluatTplVo> rst = new PageResult<EvaluatTplVo>();
        List<EvaluatTplVo> list = this.evaluatTplDao.findList(condition, start, limit, orderBy);
        for (EvaluatTplVo item : list) {
            List<EvaluatGauge> listGa = this.evaluatGaugeDao.findLists(item.getId());
            EvaluatGauge evaluatGauge = null;
            switch (listGa.size()) {
                case 10:
                    evaluatGauge = listGa.get(9);
                    item.setContent9(evaluatGauge.getContent());
                    item.setInterval9(evaluatGauge.getScoreEnd());
                case 9:
                    evaluatGauge = listGa.get(8);
                    item.setContent8(evaluatGauge.getContent());
                    item.setInterval8(evaluatGauge.getScoreEnd());
                case 8:
                    evaluatGauge = listGa.get(7);
                    item.setContent7(evaluatGauge.getContent());
                    item.setInterval7(evaluatGauge.getScoreEnd());
                case 7:
                    evaluatGauge = listGa.get(6);
                    item.setContent6(evaluatGauge.getContent());
                    item.setInterval6(evaluatGauge.getScoreEnd());
                case 6:
                    evaluatGauge = listGa.get(5);
                    item.setContent5(evaluatGauge.getContent());
                    item.setInterval5(evaluatGauge.getScoreEnd());
                case 5:
                    evaluatGauge = listGa.get(4);
                    item.setContent4(evaluatGauge.getContent());
                    item.setInterval4(evaluatGauge.getScoreEnd());
                case 4:
                    evaluatGauge = listGa.get(3);
                    item.setContent3(evaluatGauge.getContent());
                    item.setInterval3(evaluatGauge.getScoreEnd());
                case 3:
                    evaluatGauge = listGa.get(2);
                    item.setContent2(evaluatGauge.getContent());
                    item.setInterval2(evaluatGauge.getScoreEnd());
                case 2:
                    evaluatGauge = listGa.get(1);
                    item.setContent1(evaluatGauge.getContent());
                    item.setInterval1(evaluatGauge.getScoreEnd());
                case 1:
                    evaluatGauge = listGa.get(0);
                    item.setContent0(evaluatGauge.getContent());
                    item.setInterval0(evaluatGauge.getScoreEnd());
            }
        }
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.evaluatTplDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评测分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<EvaluatTplVo>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public PageResult<EvaluatTplVo> findEvaluatTplListVo(EvaluatTplQVo condition, int page, int limit, String orderBy) throws Exception {
        PageResult<EvaluatTplVo> rst = new PageResult<EvaluatTplVo>();
        List<EvaluatTplVo> list = this.evaluatTplDao.findList(condition, (page - 1) * limit, limit, orderBy);
        for (EvaluatTplVo item : list) {
            List<EvaluatGauge> listGa = this.evaluatGaugeDao.findLists(item.getId());
            item.setGaugelist(listGa);
        }
        rst.setRows(list);
        if (page <= 1) {
            int allRows = this.evaluatTplDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评测分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<EvaluatTplVo>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public ResultResponse getEvaluatTplList(EvaluatTplQVo condition, int page, int limit, String orderBy) throws Exception {
        int start = (page - 1) * limit;
        List<EvaluatTplVo> list = this.evaluatTplDao.findList(condition, start, limit, orderBy);
        return new ResultResponse(0, "获取测试模板", list);
    }

    /**
     * @throws
     * @Title:insertEvaluatTpl
     * @Description: TODO(添加评测)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse insertEvaluatTpl(String jsons, UserProp userProp)
            throws Exception {
        EvaluatTpl o = JSON.parseObject(jsons, EvaluatTpl.class);
        String evaluatTplId = GUIDUtil.getGUID();
        o.setId(evaluatTplId);
        o.setSyid(userProp.getActiveSyId());
        JSONObject jsonObject = JSONObject.parseObject(jsons);
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getCover())) {
            return new MessageResponse(1, "封面不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntroduce())) {
            return new MessageResponse(1, "测评介绍不能为空！");
        }
        if (CommonUtils.isBlank(o.getScore())) {
            return new MessageResponse(1, "满分值不能为空！");
        }
//        int temp = this.evaluatTplDao.isExit(o);
//        if (temp > 0) {
//            return new MessageResponse(1, "评测名称重复！");
//        }

        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.evaluatTplDao.insert(o);

        if (!CommonUtils.isBlank(jsonObject.getString("interval1"))) {
            MessageResponse rr = insertGauge(jsonObject, userProp, evaluatTplId, o.getScore());
            if (rr.getStatus() == 1) {
                throw new CustomException(rr.getErrorMessage());
            }
        }

        this.dataBaseLogService.log("添加评测", "评测", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "添加评测完成！");
    }


    /**
     * @throws
     * @Title:insertEvaluatTpl
     * @Description: TODO(添加评测)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse insertEvaluatTplVo(EvaluatTpl o, List<EvaluatGauge> lists, UserProp userProp)
            throws Exception {

        String evaluatTplId = GUIDUtil.getGUID();
        o.setId(evaluatTplId);
        o.setSyid(userProp.getActiveSyId());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getCover())) {
            return new MessageResponse(1, "封面不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntroduce())) {
            return new MessageResponse(1, "测评介绍不能为空！");
        }
        int temp = this.evaluatTplDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "评测名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.evaluatTplDao.insert(o);
        int topScore = 10000;
        for (EvaluatGauge item : lists) {
            item.setEvaluatTplId(evaluatTplId);
            item.setScoreStart(topScore);
            topScore = item.getScoreEnd();
            this.evaluatGaugeService.insertEvaluatGauge(item, userProp);
        }
        this.dataBaseLogService.log("添加评测", "评测", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "添加评测完成！");
    }


    /**
     * @throws
     * @Title:updateEvaluatTpl
     * @Description: TODO(更新评测)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse updateEvaluatTplVo(EvaluatTpl o, List<EvaluatGauge> lists, UserProp userProp) throws Exception {
        String evaluatTplId = o.getId();
        if (CommonUtils.isBlank(evaluatTplId)) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getCover())) {
            return new MessageResponse(1, "封面不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntroduce())) {
            return new MessageResponse(1, "测评介绍不能为空！");
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.evaluatTplDao.updateByPrimaryKey(o);
        int topScore = 10000;
        this.evaluatGaugeDao.deleteByEvaluatTplId(evaluatTplId);
        for (EvaluatGauge item : lists) {
            item.setEvaluatTplId(evaluatTplId);
            item.setScoreStart(topScore);
            topScore = item.getScoreEnd();
            this.evaluatGaugeService.insertEvaluatGauge(item, userProp);
        }
        this.dataBaseLogService.log("变更评测", "评测", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "变更评测完成！");
    }

    /**
     * @throws
     * @Title:updateEvaluatTpl
     * @Description: TODO(更新评测)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse updateEvaluatTpl(String jsons, UserProp userProp) throws Exception {
        EvaluatTpl o = JSON.parseObject(jsons, EvaluatTpl.class);
        String evaluatTplId = o.getId();
        JSONObject jsonObject = JSONObject.parseObject(jsons);
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getCover())) {
            return new MessageResponse(1, "封面不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntroduce())) {
            return new MessageResponse(1, "测评介绍不能为空！");
        }
        if (CommonUtils.isBlank(o.getScore())) {
            return new MessageResponse(1, "满分值不能为空！");
        }

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.evaluatTplDao.updateByPrimaryKey(o);

        this.evaluatGaugeDao.deleteByEvaluatTplId(evaluatTplId);
        if (!CommonUtils.isBlank(jsonObject.getString("interval1"))) {
            MessageResponse rr = insertGauge(jsonObject, userProp, evaluatTplId, o.getScore());
            if (rr.getStatus() == 1) {
                throw new CustomException(rr.getErrorMessage());
            }
        }

        this.dataBaseLogService.log("变更评测", "评测", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "变更评测完成！");
    }

    /**
     * @throws
     * @Title:selectEvaluatTplByPrimaryKey
     * @Description: TODO(获取评测)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluatTpl>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public SingleResult<EvaluatTplVo> selectEvaluatTplByPrimaryKey(String id) throws Exception {
        SingleResult<EvaluatTplVo> rst = new SingleResult<EvaluatTplVo>();
        EvaluatTplVo evaluatTplVo = this.evaluatTplDao.selectByPrimaryKey(id);
        evaluatTplVo.setGaugelist(this.evaluatGaugeDao.findLists(evaluatTplVo.getId()));
        rst.setValue(evaluatTplVo);
        return rst;
    }


    @Override
    public ResultResponse getEvaluatTplByPrimaryKey(String id) throws Exception {
        return new ResultResponse(0, "模板详情", this.evaluatTplDao.selectByPrimaryKey(id));
    }

    /**
     * @throws
     * @Title:deleteEvaluatTplByEvaluatTplId
     * @Description: TODO(删除评测)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse deleteEvaluatTplByEvaluatTplId(String id, UserProp userProp) throws Exception {
        this.evaluatGaugeDao.deleteByEvaluatTplId(id);
        this.evaluatCaseDao.deleteByPrimaryKey(id);
        this.evaluatDataDao.deleteByEvaluatTplId(id);
        this.evaluatTplDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除评测", "评测", String.valueOf(id),
                String.valueOf(id), "评测", userProp);
        return new MessageResponse(0, "评测删除完成！");
    }

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(获取评测列表)
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public Map<String, Object> getList(Map<String, Object> params) throws Exception {
        Map<String, Object> rst = new HashMap<>();
        rst.put("status", 0);
        rst.put("data", this.evaluatTplDao.getList(params));
        return rst;
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取评测)
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
        rst.put("data", this.evaluatTplDao.getById(id));
        return rst;
    }


    @Override
    public List<Tree> selectEvaluatTplTreeList(String pid, String syid) throws Exception {
        logger.info("=================getDictTreeList-pid===>{}", pid);
        CommonTreeUtils commonTreeUtils = new CommonTreeUtils(
                this.evaluatTplDao.selectEvaluatTplTreeList(pid, syid));
        return commonTreeUtils.getTreeList(pid);
    }

    private MessageResponse insertGauge(JSONObject jsonObject, UserProp userProp, String evaluatTplId, int score) throws Exception {
        EvaluatGauge evaluatGauge = new EvaluatGauge();
        evaluatGauge.setEvaluatTplId(evaluatTplId);
        evaluatGauge.setScoreStart(score);
        for (int i = 0; i < 10; i++) {
            if (CommonUtils.isBlank(jsonObject.getString("interval" + i))) {
                continue;
            }
            score = Integer.parseInt(jsonObject.getString("interval" + i));
            evaluatGauge.setScoreEnd(score);
            evaluatGauge.setContent(jsonObject.getString("content" + i));
            this.evaluatGaugeService.insertEvaluatGauge(evaluatGauge, userProp);
            evaluatGauge.setScoreStart(score);
        }
        return new MessageResponse(0, "完成");
    }


}

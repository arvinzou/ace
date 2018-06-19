package com.huacainfo.ace.portal.service.impl;


import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.huacainfo.ace.common.pushmsg.CommonUtil;
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
import com.huacainfo.ace.portal.dao.EvaluatCaseSubDao;
import com.huacainfo.ace.portal.model.EvaluatCaseSub;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.EvaluatCaseSubService;
import com.huacainfo.ace.portal.vo.EvaluatCaseSubVo;
import com.huacainfo.ace.portal.vo.EvaluatCaseSubQVo;

@Service("evaluatCaseSubService")
/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description: TODO(题目选项)
 */
public class EvaluatCaseSubServiceImpl implements EvaluatCaseSubService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvaluatCaseSubDao evaluatCaseSubDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(题目选项分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<EvaluatCaseSubVo>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public PageResult<EvaluatCaseSubVo> findEvaluatCaseSubList(EvaluatCaseSubQVo condition, int start,
                                                               int limit, String orderBy) throws Exception {
        PageResult<EvaluatCaseSubVo> rst = new PageResult<EvaluatCaseSubVo>();
        List<EvaluatCaseSubVo> list = this.evaluatCaseSubDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.evaluatCaseSubDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertEvaluatCaseSub
     * @Description: TODO(添加题目选项)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse insertEvaluatCaseSub(EvaluatCaseSub o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvaluatCaseId())) {
            return new MessageResponse(1, "所属评测不能为空！");
        }
        if (CommonUtils.isBlank(o.getCntImg()) && CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "选项不能为空！");
        }
//        int temp = this.evaluatCaseSubDao.isExit(o);
//        if (temp > 0) {
//            return new MessageResponse(1, "题目选项名称重复！");
//        }
        o.setCreateDate(new Date());
        this.evaluatCaseSubDao.insertSelective(o);
        this.dataBaseLogService.log("添加题目选项", "题目选项", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "添加题目选项完成！");
    }

    /**
     * @throws
     * @Title:updateEvaluatCaseSub
     * @Description: TODO(更新题目选项)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse updateEvaluatCaseSub(EvaluatCaseSub o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvaluatCaseId())) {
            return new MessageResponse(1, "所属评测不能为空！");
        }
        this.evaluatCaseSubDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更题目选项", "题目选项", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "变更题目选项完成！");
    }

    /**
     * @throws
     * @Title:selectEvaluatCaseSubByPrimaryKey
     * @Description: TODO(获取题目选项)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluatCaseSub>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public SingleResult<EvaluatCaseSubVo> selectEvaluatCaseSubByPrimaryKey(String id) throws Exception {
        SingleResult<EvaluatCaseSubVo> rst = new SingleResult<EvaluatCaseSubVo>();
        rst.setValue(this.evaluatCaseSubDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteEvaluatCaseSubByEvaluatCaseSubId
     * @Description: TODO(删除题目选项)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse deleteEvaluatCaseSubByEvaluatCaseSubId(String id,
                                                                  UserProp userProp) throws Exception {
        this.evaluatCaseSubDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除题目选项", "题目选项", String.valueOf(id),
                String.valueOf(id), "题目选项", userProp);
        return new MessageResponse(0, "题目选项删除完成！");
    }

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(获取题目选项列表)
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public Map<String, Object> getList(Map<String, Object> params) throws Exception {
        Map<String, Object> rst = new HashMap<>();
        rst.put("status", 0);
        rst.put("data", this.evaluatCaseSubDao.getList(params));
        return rst;
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取题目选项)
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
        rst.put("data", this.evaluatCaseSubDao.getById(id));
        return rst;
    }
}

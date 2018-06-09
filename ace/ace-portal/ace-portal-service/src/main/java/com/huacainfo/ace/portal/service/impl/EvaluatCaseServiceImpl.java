package com.huacainfo.ace.portal.service.impl;


import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.dao.EvaluatCaseDao;
import com.huacainfo.ace.portal.model.EvaluatCase;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.EvaluatCaseService;
import com.huacainfo.ace.portal.vo.EvaluatCaseVo;
import com.huacainfo.ace.portal.vo.EvaluatCaseQVo;

@Service("evaluatCaseService")
/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description: TODO(题目)
 */
public class EvaluatCaseServiceImpl implements EvaluatCaseService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvaluatCaseDao evaluatCaseDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(题目分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<EvaluatCaseVo>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public PageResult<EvaluatCaseVo> findEvaluatCaseList(EvaluatCaseQVo condition, int start,
                                                         int limit, String orderBy) throws Exception {
        PageResult<EvaluatCaseVo> rst = new PageResult<EvaluatCaseVo>();
        List<EvaluatCaseVo> list = this.evaluatCaseDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.evaluatCaseDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertEvaluatCase
     * @Description: TODO(添加题目)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse insertEvaluatCase(EvaluatCase o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvaluatTplId())) {
            return new MessageResponse(1, "所属评测模板不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getType())) {
            return new MessageResponse(1, "选题类型(1单选2多选)不能为空！");
        }
        if (CommonUtils.isBlank(o.getScore())) {
            return new MessageResponse(1, "分值不能为空！");
        }
        if (CommonUtils.isBlank(o.getSolution())) {
            return new MessageResponse(1, "答案不能为空！");
        }
        int temp = this.evaluatCaseDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "题目名称重复！");
        }
        o.setCreateDate(new Date());
        this.evaluatCaseDao.insert(o);
        this.dataBaseLogService.log("添加题目", "题目", "", o.getTitle(),
                o.getTitle(), userProp);
        return new MessageResponse(0, "添加题目完成！");
    }

    /**
     * @throws
     * @Title:updateEvaluatCase
     * @Description: TODO(更新题目)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse updateEvaluatCase(EvaluatCase o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvaluatTplId())) {
            return new MessageResponse(1, "所属评测模板不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getType())) {
            return new MessageResponse(1, "选题类型(1单选2多选)不能为空！");
        }
        if (CommonUtils.isBlank(o.getScore())) {
            return new MessageResponse(1, "分值不能为空！");
        }
        if (CommonUtils.isBlank(o.getSolution())) {
            return new MessageResponse(1, "答案不能为空！");
        }
        this.evaluatCaseDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更题目", "题目", "", o.getTitle(),
                o.getTitle(), userProp);
        return new MessageResponse(0, "变更题目完成！");
    }

    /**
     * @throws
     * @Title:selectEvaluatCaseByPrimaryKey
     * @Description: TODO(获取题目)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluatCase>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public SingleResult<EvaluatCaseVo> selectEvaluatCaseByPrimaryKey(String id) throws Exception {
        SingleResult<EvaluatCaseVo> rst = new SingleResult<EvaluatCaseVo>();
        rst.setValue(this.evaluatCaseDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteEvaluatCaseByEvaluatCaseId
     * @Description: TODO(删除题目)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse deleteEvaluatCaseByEvaluatCaseId(String id,
                                                            UserProp userProp) throws Exception {
        this.evaluatCaseDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除题目", "题目", String.valueOf(id),
                String.valueOf(id), "题目", userProp);
        return new MessageResponse(0, "题目删除完成！");
    }

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(获取题目列表)
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public Map<String, Object> getList(Map<String, Object> params) throws Exception {
        Map<String, Object> rst = new HashMap<>();
        rst.put("status", 0);
        rst.put("data", this.evaluatCaseDao.getList(params));
        return rst;
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取题目)
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
        rst.put("data", this.evaluatCaseDao.getById(id));
        return rst;
    }
}

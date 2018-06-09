package com.huacainfo.ace.portal.service.impl;


import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    private DataBaseLogService dataBaseLogService;

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
    public PageResult<EvaluatTplVo> findEvaluatTplList(EvaluatTplQVo condition, int start,
                                                       int limit, String orderBy) throws Exception {
        PageResult<EvaluatTplVo> rst = new PageResult<EvaluatTplVo>();
        List<EvaluatTplVo> list = this.evaluatTplDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.evaluatTplDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
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
    public MessageResponse insertEvaluatTpl(EvaluatTpl o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
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
        if (CommonUtils.isBlank(o.getQrcoteUrl())) {
            return new MessageResponse(1, "二维码不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntroduce())) {
            return new MessageResponse(1, "测评介绍不能为空！");
        }
        if (CommonUtils.isBlank(o.getScore())) {
            return new MessageResponse(1, "满分值不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
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
    public MessageResponse updateEvaluatTpl(EvaluatTpl o, UserProp userProp)
            throws Exception {
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
        if (CommonUtils.isBlank(o.getQrcoteUrl())) {
            return new MessageResponse(1, "二维码不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntroduce())) {
            return new MessageResponse(1, "测评介绍不能为空！");
        }
        if (CommonUtils.isBlank(o.getScore())) {
            return new MessageResponse(1, "满分值不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.evaluatTplDao.updateByPrimaryKey(o);
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
        rst.setValue(this.evaluatTplDao.selectByPrimaryKey(id));
        return rst;
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
    public MessageResponse deleteEvaluatTplByEvaluatTplId(String id,
                                                          UserProp userProp) throws Exception {
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
}

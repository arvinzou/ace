package com.huacainfo.ace.portal.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
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
import com.huacainfo.ace.portal.dao.EvaluatTypeDao;
import com.huacainfo.ace.portal.model.EvaluatType;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.EvaluatTypeService;
import com.huacainfo.ace.portal.vo.EvaluatTypeVo;
import com.huacainfo.ace.portal.vo.EvaluatTypeQVo;

@Service("evaluatTypeService")
/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description: TODO(评测类型)
 */
public class EvaluatTypeServiceImpl implements EvaluatTypeService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvaluatTypeDao evaluatTypeDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评测类型分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<EvaluatTypeVo>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public PageResult<EvaluatTypeVo> findEvaluatTypeList(EvaluatTypeQVo condition, int start,
                                                         int limit, String orderBy) throws Exception {
        PageResult<EvaluatTypeVo> rst = new PageResult<EvaluatTypeVo>();
        List<EvaluatTypeVo> list = this.evaluatTypeDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.evaluatTypeDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertEvaluatType
     * @Description: TODO(添加评测类型)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse insertEvaluatType(EvaluatType o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        o.setSyid(userProp.getActiveSyId());
        int temp = this.evaluatTypeDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "评测类型名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.evaluatTypeDao.insertSelective(o);
        this.dataBaseLogService.log("添加评测类型", "评测类型", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "添加评测类型完成！");
    }

    /**
     * @throws
     * @Title:updateEvaluatType
     * @Description: TODO(更新评测类型)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse updateEvaluatType(EvaluatType o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.evaluatTypeDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更评测类型", "评测类型", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "变更评测类型完成！");
    }

    /**
     * @throws
     * @Title:selectEvaluatTypeByPrimaryKey
     * @Description: TODO(获取评测类型)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluatType>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public SingleResult<EvaluatTypeVo> selectEvaluatTypeByPrimaryKey(String id) throws Exception {
        SingleResult<EvaluatTypeVo> rst = new SingleResult<EvaluatTypeVo>();
        rst.setValue(this.evaluatTypeDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteEvaluatTypeByEvaluatTypeId
     * @Description: TODO(删除评测类型)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public MessageResponse deleteEvaluatTypeByEvaluatTypeId(String id,
                                                            UserProp userProp) throws Exception {
        this.evaluatTypeDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除评测类型", "评测类型", String.valueOf(id),
                String.valueOf(id), "评测类型", userProp);
        return new MessageResponse(0, "评测类型删除完成！");
    }

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(获取评测类型列表)
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public Map<String, Object> getList(Map<String, Object> params) throws Exception {
        Map<String, Object> rst = new HashMap<String, Object>();
        rst.put("status", 0);
        rst.put("data", this.evaluatTypeDao.getList(params));
        return rst;
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取评测类型)
     * @param: @param id
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @Override
    public Map<String, Object> getById(String id) throws Exception{
        Map<String, Object> rst = new HashMap<>();
        rst.put("status", 0);
        rst.put("data", this.evaluatTypeDao.getById(id));
        return rst;
    }

    @Override
    public Map<String, Object> selectType(Map<String, Object> params) throws Exception {
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, String>> list = this.evaluatTypeDao.selectType(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }


}

package com.huacainfo.ace.glink.service.impl;


import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.glink.dao.LtLnkObjectDao;
import com.huacainfo.ace.glink.model.LtLnkObject;
import com.huacainfo.ace.glink.service.LtLnkObjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.glink.dao.LtStrategyDao;
import com.huacainfo.ace.glink.model.LtStrategy;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.glink.service.LtStrategyService;
import com.huacainfo.ace.glink.vo.LtStrategyVo;
import com.huacainfo.ace.glink.vo.LtStrategyQVo;

@Service("ltStrategyService")
/**
 * @author: huacai003
 * @version: 2019-04-10
 * @Description: TODO(策略管理)
 */
public class LtStrategyServiceImpl implements LtStrategyService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LtStrategyDao ltStrategyDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private LtLnkObjectService ltLnkObjectService;



    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(策略管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <LtStrategyVo>
     * @author: huacai003
     * @version: 2019-04-10
     */
    @Override
    public PageResult<LtStrategyVo> findLtStrategyList(LtStrategyQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<LtStrategyVo> rst = new PageResult<>();
        List<LtStrategyVo> list = this.ltStrategyDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.ltStrategyDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertLtStrategy
     * @Description: TODO(添加策略管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse insertLtStrategy(LtStrategy o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getCode())) {
            return new MessageResponse(1, "策略编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "策略名称不能为空！");
        }
        int temp = this.ltStrategyDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "策略管理名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.ltStrategyDao.insert(o);
        this.dataBaseLogService.log("添加策略管理", "策略管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateLtStrategy
     * @Description: TODO(更新策略管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse updateLtStrategy(LtStrategy o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCode())) {
            return new MessageResponse(1, "策略编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "策略名称不能为空！");
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.ltStrategyDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更策略管理", "策略管理", "",
                o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateLtStrategy
     * @Description: TODO(更新策略管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse updateLtStrategyVo(LtStrategy o, UserProp userProp) throws Exception {
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.ltStrategyDao.updateByPrimaryKeyVo(o);
        this.dataBaseLogService.log("变更策略管理", "策略管理", "",
                o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectLtStrategyByPrimaryKey
     * @Description: TODO(获取策略管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LtStrategy>
     * @author: huacai003
     * @version: 2019-04-10
     */
    @Override
    public SingleResult<LtStrategyVo> selectLtStrategyByPrimaryKey(String id) throws Exception {
        SingleResult<LtStrategyVo> rst = new SingleResult<>();
        rst.setValue(this.ltStrategyDao.selectByPrimaryKeyVo(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteLtStrategyByLtStrategyId
     * @Description: TODO(删除策略管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse deleteLtStrategyByLtStrategyId(String id, UserProp userProp) throws
            Exception {
        this.ltStrategyDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除策略管理", "策略管理", id, id,
                "策略管理", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核策略管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {
        dataBaseLogService.log("审核策略管理", "策略管理", id, id,
                "策略管理", userProp);
        return new MessageResponse(0, "审核成功！");
    }


    /**
     * @throws
     * @Title:importXls
     * @Description: TODO(Excel导入资源数据)
     * @param: @param list
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-10
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            LtStrategy o = new LtStrategy();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            o.setCreateUserId(userProp.getUserId());
            o.setCreateUserName(userProp.getName());
            o.setStatus("1");

            this.logger.info(o.toString());
            if (true) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getId())) {
                return new MessageResponse(1, "主键不能为空！");
            }
            if (CommonUtils.isBlank(o.getCode())) {
                return new MessageResponse(1, "策略编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getName())) {
                return new MessageResponse(1, "策略名称不能为空！");
            }
            if (CommonUtils.isBlank(o.getStatus())) {
                return new MessageResponse(1, "状态 不能为空！");
            }

            int t = this.ltStrategyDao.isExit(o);
            if (t > 0) {
                this.ltStrategyDao.updateByPrimaryKey(o);

            } else {
                this.ltStrategyDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("策略管理导入", "策略管理", "",
                "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
    }


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult
     * <Map
     * <String
     * ,Object>>
     * @author: huacai003
     * @version: 2019-04-10
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.ltStrategyDao.getList(p));
        return rst;
    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map
     * <String
     * ,Object>
     * @author: huacai003
     * @version: 2019-04-10
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<>();
        List<Map<String, Object>> list = this.ltStrategyDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除策略管理）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse deleteLtStrategyByLtStrategyIds(String[] id, UserProp userProp)
            throws Exception {
        this.ltStrategyDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除策略管理", "策略管理", id[0],
                id[0], "策略管理", userProp);
        return new MessageResponse(0, "删除成功！");

    }


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse updateStatus(String id, LtLnkObject ltLnkObject, UserProp userProp) throws
            Exception {
        this.ltStrategyDao.updateStatus(id, "2");
        this.ltLnkObjectService.insertLtLnkObject(ltLnkObject,userProp);
        this.dataBaseLogService.log("跟新状态", "策略管理", id, id,
                "策略管理", userProp);
        return new MessageResponse(0, "成功！");
    }

}

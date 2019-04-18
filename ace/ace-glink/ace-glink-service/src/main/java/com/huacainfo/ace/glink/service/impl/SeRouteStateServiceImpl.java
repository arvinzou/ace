package com.huacainfo.ace.glink.service.impl;


import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.glink.dao.SeRouteStateDao;
import com.huacainfo.ace.glink.model.SeRouteState;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.glink.service.SeRouteStateService;
import com.huacainfo.ace.glink.vo.SeRouteStateVo;
import com.huacainfo.ace.glink.vo.SeRouteStateQVo;

@Service("seRouteStateService")
/**
 * @author: huacai003
 * @version: 2019-04-18
 * @Description: TODO(路由器运行状态)
 */
public class SeRouteStateServiceImpl implements SeRouteStateService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeRouteStateDao seRouteStateDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(路由器运行状态分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SeRouteStateVo>
     * @author: huacai003
     * @version: 2019-04-18
     */
    @Override
    public PageResult<SeRouteStateVo> findSeRouteStateList(SeRouteStateQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<SeRouteStateVo> rst = new PageResult<>();
        List<SeRouteStateVo> list = seRouteStateDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.seRouteStateDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertSeRouteState
     * @Description: TODO(添加路由器运行状态)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse insertSeRouteState(SeRouteState o, UserProp userProp) throws Exception {
        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getNodeID())) {
            return new MessageResponse(1, "配电箱编号不能为空！");
        }
        int temp = this.seRouteStateDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "路由器运行状态名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus(1);
        this.seRouteStateDao.insert(o);
        this.dataBaseLogService.log("添加路由器运行状态", "路由器运行状态", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateSeRouteState
     * @Description: TODO(更新路由器运行状态)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse updateSeRouteState(SeRouteState o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getNodeID())) {
            return new MessageResponse(1, "配电箱编号不能为空！");
        }
        this.seRouteStateDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更路由器运行状态", "路由器运行状态", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectSeRouteStateByPrimaryKey
     * @Description: TODO(获取路由器运行状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeRouteState>
     * @author: huacai003
     * @version: 2019-04-18
     */
    @Override
    public SingleResult<SeRouteStateVo> selectSeRouteStateByPrimaryKey(String id) throws Exception {
        SingleResult
                <SeRouteStateVo> rst = new SingleResult<>();
        rst.setValue(this.seRouteStateDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteSeRouteStateBySeRouteStateId
     * @Description: TODO(删除路由器运行状态)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse deleteSeRouteStateBySeRouteStateId(String id, UserProp userProp) throws
            Exception {
        this.seRouteStateDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除路由器运行状态", "路由器运行状态", id, id,
                "路由器运行状态", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核路由器运行状态)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {
        dataBaseLogService.log("审核路由器运行状态", "路由器运行状态", id, id,
                "路由器运行状态", userProp);
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
     * @version: 2019-04-18
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            SeRouteState o = new SeRouteState();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            o.setStatus(1);
            this.logger.info(o.toString());
            if (true) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getId())) {
                return new MessageResponse(1, "主键不能为空！");
            }
            if (CommonUtils.isBlank(o.getNodeID())) {
                return new MessageResponse(1, "配电箱编号不能为空！");
            }

            int t = this.seRouteStateDao.isExit(o);
            if (t > 0) {
                this.seRouteStateDao.updateByPrimaryKey(o);

            } else {
                this.seRouteStateDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("路由器运行状态导入", "路由器运行状态", "",
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
     * @version: 2019-04-18
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.seRouteStateDao.getList(p));
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
     * @version: 2019-04-18
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<>();
        List<Map<String, Object>> list = this.seRouteStateDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除路由器运行状态）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse deleteSeRouteStateBySeRouteStateIds(String[] id, UserProp userProp)
            throws Exception {

        this.seRouteStateDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除路由器运行状态", "路由器运行状态", id[0],
                id[0], "路由器运行状态", userProp);
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
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws
            Exception {
        this.seRouteStateDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "路由器运行状态", id, id,
                "路由器运行状态", userProp);
        return new MessageResponse(0, "成功！");
    }

}

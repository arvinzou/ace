package com.huacainfo.ace.glink.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.glink.dao.TopNodeDao;
import com.huacainfo.ace.glink.model.TopNode;
import com.huacainfo.ace.glink.service.TopNodeService;
import com.huacainfo.ace.glink.vo.TopNodeQVo;
import com.huacainfo.ace.glink.vo.TopNodeVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("topNodeService")
/**
 * @author: huacai003
 * @version: 2019-04-09
 * @Description: TODO(节点管理)
 */
public class TopNodeServiceImpl implements TopNodeService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopNodeDao topNodeDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(节点管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TopNodeVo>
     * @author: huacai003
     * @version: 2019-04-09
     */
    @Override
    public PageResult<TopNodeVo> findTopNodeList(TopNodeQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<TopNodeVo> rst = new PageResult<>();
        List<TopNodeVo> list = this.topNodeDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.topNodeDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertTopNode
     * @Description: TODO(添加节点管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse insertTopNode(TopNode o, UserProp userProp) throws Exception {

        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getCode())) {
            return new MessageResponse(1, "节点编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "节点名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getAddress())) {
            return new MessageResponse(1, "详细地址不能为空！");
        }
        if (CommonUtils.isBlank(o.getIpv4())) {
            return new MessageResponse(1, "IPV4地址不能为空！");
        }
        if (CommonUtils.isBlank(o.getPort())) {
            return new MessageResponse(1, "端口号不能为空！");
        }
        if (CommonUtils.isBlank(o.getResolutionWidth())) {
            return new MessageResponse(1, "分辨率-宽不能为空！");
        }
        if (CommonUtils.isBlank(o.getResolutionHeight())) {
            return new MessageResponse(1, "分辨率-高不能为空！");
        }
        o.setStatus("1");
        int temp = this.topNodeDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "节点管理名称重复！");
        }

        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.topNodeDao.insert(o);
        this.dataBaseLogService.log("添加节点管理", "节点管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateTopNode
     * @Description: TODO(更新节点管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse updateTopNode(TopNode o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCode())) {
            return new MessageResponse(1, "节点编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "节点名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getAddress())) {
            return new MessageResponse(1, "详细地址不能为空！");
        }
        if (CommonUtils.isBlank(o.getIpv4())) {
            return new MessageResponse(1, "IPV4地址不能为空！");
        }
        if (CommonUtils.isBlank(o.getPort())) {
            return new MessageResponse(1, "端口号不能为空！");
        }
        if (CommonUtils.isBlank(o.getResolutionWidth())) {
            return new MessageResponse(1, "分辨率-宽不能为空！");
        }
        if (CommonUtils.isBlank(o.getResolutionHeight())) {
            return new MessageResponse(1, "分辨率-高不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.topNodeDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更节点管理", "节点管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectTopNodeByPrimaryKey
     * @Description: TODO(获取节点管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TopNode>
     * @author: huacai003
     * @version: 2019-04-09
     */
    @Override
    public SingleResult<TopNodeVo> selectTopNodeByPrimaryKey(String id) throws Exception {
        SingleResult<TopNodeVo> rst = new SingleResult<>();
        rst.setValue(this.topNodeDao.selectByPrimaryKeyVo(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteTopNodeByTopNodeId
     * @Description: TODO(删除节点管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse deleteTopNodeByTopNodeId(String id, UserProp userProp) throws
            Exception {
        this.topNodeDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除节点管理", "节点管理", id, id,
                "节点管理", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核节点管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核节点管理", "节点管理", id, id,
                "节点管理", userProp);
        return new MessageResponse(0, "审核成功！");
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
     * @version: 2019-04-09
     */
    @Override
    public ListResult<Map<String,Object>> getList(Map<String,Object> p) throws Exception {
        ListResult<Map<String,Object>> rst = new ListResult<>();
        rst.setValue(this.topNodeDao.getList(p));

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
     * @version: 2019-04-09
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, Object>> list = this.topNodeDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除节点管理）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse deleteTopNodeByTopNodeIds(String[] id, UserProp userProp)
            throws Exception {

        this.topNodeDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除节点管理", "节点管理", id[0],
                id[0], "节点管理", userProp);
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
     * @version: 2019-04-09
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws
            Exception {
        this.topNodeDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "节点管理", id, id,
                "节点管理", userProp);
        return new MessageResponse(0, "成功！");
    }

    /**
     * 批量导入学员
     *
     * @param list     导入数据
     * @param userProp
     * @throws Exception
     */
    @Override
    public MessageResponse insertImportXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        String importDateTime = DateUtil.getNow();
        int i = 1;
        int total=0;
        MessageResponse iMs;
        for (Map<String, Object> row : list) {
            TopNode o = new TopNode();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setRemark("批量导入节点：" + importDateTime);
            if (CommonUtils.isBlank(o.getCode())) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getName())) {
                return new MessageResponse(1, "行" + i + ",节点名称不能为空！");
            }
            if (CommonUtils.isBlank(o.getAddress())) {
                return new MessageResponse(1, "行" + i + ",详细地址不能为空！");
            }
            if (CommonUtils.isBlank(o.getIpv4())) {
                return new MessageResponse(1, "行" + i + ",Ipv4不能为空！");
            }
            if (CommonUtils.isBlank(o.getPort())) {
                return new MessageResponse(1, "行" + i + ",端口号不能为空！");
            }
            if (CommonUtils.isBlank(o.getResolutionHeight())) {
                return new MessageResponse(1, "行" + i + ",分辨率高不能为空！");
            }
            if (CommonUtils.isBlank(o.getResolutionWidth())) {
                return new MessageResponse(1, "行" + i + ",分辨率宽不能为空！");
            }
            int t = this.topNodeDao.isExit(o);
            if (t > 0) {
                continue;
            } else {
                o.setId(GUIDUtil.getGUID());
                o.setCreateDate(new Date());
                o.setStatus("1");
                o.setCreateUserName(userProp.getName());
                o.setCreateUserId(userProp.getUserId());
                this.topNodeDao.insert(o);
                total++;
            }
            i++;
            i++;
        }

        dataBaseLogService.log("批量导入节点", "批量导入节点", "", "rs.xls", "rs.xls", userProp);
        return new MessageResponse(ResultCode.SUCCESS, "导入成功"+total+"条数据");
    }

}

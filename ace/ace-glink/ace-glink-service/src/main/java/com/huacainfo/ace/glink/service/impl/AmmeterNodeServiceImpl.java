package com.huacainfo.ace.glink.service.impl;


import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

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
import com.huacainfo.ace.glink.dao.AmmeterNodeDao;
import com.huacainfo.ace.glink.model.AmmeterNode;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.glink.service.AmmeterNodeService;
import com.huacainfo.ace.glink.vo.AmmeterNodeVo;
import com.huacainfo.ace.glink.vo.AmmeterNodeQVo;

@Service("ammeterNodeService")
/**
 * @author: luocan
 * @version: 2019-04-15
 * @Description: TODO(节点能耗信息)
 */
public class AmmeterNodeServiceImpl implements AmmeterNodeService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AmmeterNodeDao ammeterNodeDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(节点能耗信息分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <AmmeterNodeVo>
     * @author: luocan
     * @version: 2019-04-15
     */
    @Override
    public PageResult<AmmeterNodeVo> findAmmeterNodeList(AmmeterNodeQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<AmmeterNodeVo> rst = new PageResult<>();
        List<AmmeterNodeVo> list = ammeterNodeDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.ammeterNodeDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertAmmeterNode
     * @Description: TODO(添加节点能耗信息)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse insertAmmeterNode(AmmeterNode o, UserProp userProp) throws Exception {
        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getNodeCode())) {
            return new MessageResponse(1, "节点编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getItemDecipt())) {
            return new MessageResponse(1, "字段描述不能为空！");
        }
        if (CommonUtils.isBlank(o.getItemKey())) {
            return new MessageResponse(1, "字段键不能为空！");
        }
        if (CommonUtils.isBlank(o.getItemValue())) {
            return new MessageResponse(1, "字段值不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }


        int temp = this.ammeterNodeDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "节点能耗信息名称重复！");
        }


        o.setCreateDate(new Date());
        o.setStatus("1");
        this.ammeterNodeDao.insert(o);
        this.dataBaseLogService.log("添加节点能耗信息", "节点能耗信息", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateAmmeterNode
     * @Description: TODO(更新节点能耗信息)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse updateAmmeterNode(AmmeterNode o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getNodeCode())) {
            return new MessageResponse(1, "节点编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getItemDecipt())) {
            return new MessageResponse(1, "字段描述不能为空！");
        }
        if (CommonUtils.isBlank(o.getItemKey())) {
            return new MessageResponse(1, "字段键不能为空！");
        }
        if (CommonUtils.isBlank(o.getItemValue())) {
            return new MessageResponse(1, "字段值不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }

        this.ammeterNodeDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更节点能耗信息", "节点能耗信息", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectAmmeterNodeByPrimaryKey
     * @Description: TODO(获取节点能耗信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<AmmeterNode>
     * @author: luocan
     * @version: 2019-04-15
     */
    @Override
    public SingleResult<AmmeterNodeVo> selectAmmeterNodeByPrimaryKey(String id) throws Exception {
        SingleResult<AmmeterNodeVo> rst = new SingleResult<>();
        rst.setValue(this.ammeterNodeDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteAmmeterNodeByAmmeterNodeId
     * @Description: TODO(删除节点能耗信息)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse deleteAmmeterNodeByAmmeterNodeId(String id, UserProp userProp) throws
            Exception {
        this.ammeterNodeDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除节点能耗信息", "节点能耗信息", id, id,
                "节点能耗信息", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核节点能耗信息)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核节点能耗信息", "节点能耗信息", id, id,
                "节点能耗信息", userProp);
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
     * @author: luocan
     * @version: 2019-04-15
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map
                <String
                        , Object> row : list) {
            AmmeterNode o = new AmmeterNode();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            o.setStatus("1");

            this.logger.info(o.toString());
            if (true) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getId())) {
                return new MessageResponse(1, "主键不能为空！");
            }
            if (CommonUtils.isBlank(o.getNodeCode())) {
                return new MessageResponse(1, "节点编码不能为空！");
            }
            if (CommonUtils.isBlank(o.getItemDecipt())) {
                return new MessageResponse(1, "字段描述不能为空！");
            }
            if (CommonUtils.isBlank(o.getItemKey())) {
                return new MessageResponse(1, "字段键不能为空！");
            }
            if (CommonUtils.isBlank(o.getItemValue())) {
                return new MessageResponse(1, "字段值不能为空！");
            }
            if (CommonUtils.isBlank(o.getStatus())) {
                return new MessageResponse(1, "状态 不能为空！");
            }

            int t = this.ammeterNodeDao.isExit(o);
            if (t > 0) {
                this.ammeterNodeDao.updateByPrimaryKey(o);

            } else {
                this.ammeterNodeDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("节点能耗信息导入", "节点能耗信息", "",
                "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
    }

}

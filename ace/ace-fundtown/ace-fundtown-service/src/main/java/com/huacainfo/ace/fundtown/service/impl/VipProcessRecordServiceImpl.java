package com.huacainfo.ace.fundtown.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fundtown.dao.VipProcessRecordDao;
import com.huacainfo.ace.fundtown.model.VipProcessRecord;
import com.huacainfo.ace.fundtown.service.VipProcessRecordService;
import com.huacainfo.ace.fundtown.vo.VipProcessRecordQVo;
import com.huacainfo.ace.fundtown.vo.VipProcessRecordVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("vipProcessRecordService")
/**
 * @author: Arvin
 * @version: 2018-07-03
 * @Description: TODO(入驻成员-流程节点记录)
 */
public class VipProcessRecordServiceImpl implements VipProcessRecordService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private VipProcessRecordDao vipProcessRecordDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(入驻成员-流程节点记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <VipProcessRecordVo>
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public PageResult<VipProcessRecordVo> findVipProcessRecordList(VipProcessRecordQVo condition, int start,
                                                                   int limit, String orderBy) throws Exception {
        PageResult<VipProcessRecordVo> rst = new PageResult<>();
        List<VipProcessRecordVo> list = this.vipProcessRecordDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.vipProcessRecordDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertVipProcessRecord
     * @Description: TODO(添加入驻成员-流程节点记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public MessageResponse insertVipProcessRecord(VipProcessRecord o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeptId())) {
            return new MessageResponse(1, "入驻企业id不能为空！");
        }
        if (CommonUtils.isBlank(o.getNodeId())) {
            return new MessageResponse(1, "流程节点ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        int temp = this.vipProcessRecordDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "入驻成员-流程节点记录名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.vipProcessRecordDao.insertSelective(o);
        this.dataBaseLogService.log("添加入驻成员-流程节点记录", "入驻成员-流程节点记录", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加入驻成员-流程节点记录完成！");
    }

    /**
     * @throws
     * @Title:updateVipProcessRecord
     * @Description: TODO(更新入驻成员-流程节点记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public MessageResponse updateVipProcessRecord(VipProcessRecord o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeptId())) {
            return new MessageResponse(1, "入驻企业id不能为空！");
        }
        if (CommonUtils.isBlank(o.getNodeId())) {
            return new MessageResponse(1, "流程节点ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.vipProcessRecordDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更入驻成员-流程节点记录", "入驻成员-流程节点记录", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更入驻成员-流程节点记录完成！");
    }

    /**
     * @throws
     * @Title:selectVipProcessRecordByPrimaryKey
     * @Description: TODO(获取入驻成员-流程节点记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<VipProcessRecord>
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public SingleResult<VipProcessRecordVo> selectVipProcessRecordByPrimaryKey(String id) throws Exception {
        SingleResult<VipProcessRecordVo> rst = new SingleResult<>();
        rst.setValue(this.vipProcessRecordDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteVipProcessRecordByVipProcessRecordId
     * @Description: TODO(删除入驻成员-流程节点记录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    @Override
    public MessageResponse deleteVipProcessRecordByVipProcessRecordId(String id, UserProp userProp) throws
            Exception {
        this.vipProcessRecordDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除入驻成员-流程节点记录", "入驻成员-流程节点记录",
                String.valueOf(id),
                String.valueOf(id), "入驻成员-流程节点记录", userProp);
        return new MessageResponse(0, "入驻成员-流程节点记录删除完成！");
    }

}

package com.huacainfo.ace.society.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.dao.SpaceOccupyInfoDao;
import com.huacainfo.ace.society.model.SpaceOccupyInfo;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.service.SpaceOccupyInfoService;
import com.huacainfo.ace.society.vo.SpaceOccupyInfoQVo;
import com.huacainfo.ace.society.vo.SpaceOccupyInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("spaceOccupyInfoService")
/**
 * @author: Arvin
 * @version: 2018-09-14
 * @Description: TODO(场地占用情况)
 */
public class SpaceOccupyInfoServiceImpl implements SpaceOccupyInfoService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SpaceOccupyInfoDao spaceOccupyInfoDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private AuditRecordService auditRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(场地占用情况分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SpaceOccupyInfoVo>
     * @author: Arvin
     * @version: 2018-09-14
     */
    @Override
    public PageResult<SpaceOccupyInfoVo> findSpaceOccupyInfoList(SpaceOccupyInfoQVo condition, int start,
                                                                 int limit, String orderBy) throws Exception {
        PageResult<SpaceOccupyInfoVo> rst = new PageResult<>();
        List<SpaceOccupyInfoVo> list = this.spaceOccupyInfoDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.spaceOccupyInfoDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertSpaceOccupyInfo
     * @Description: TODO(添加场地占用情况)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-14
     */
    @Override
    public MessageResponse insertSpaceOccupyInfo(SpaceOccupyInfo o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getSpaceId())) {
            return new MessageResponse(1, "场地编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrderId())) {
            return new MessageResponse(1, "订单ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getReserveDate())) {
            return new MessageResponse(1, "预订日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getReserveInterval())) {
            return new MessageResponse(1, "预订时间区间不能为空！");
        }

        o.setId(StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId());


        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.spaceOccupyInfoDao.insert(o);
        this.dataBaseLogService.log("添加场地占用情况", "场地占用情况", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加场地占用情况完成！");
    }

    /**
     * @throws
     * @Title:updateSpaceOccupyInfo
     * @Description: TODO(更新场地占用情况)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-14
     */
    @Override
    public MessageResponse updateSpaceOccupyInfo(SpaceOccupyInfo o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getSpaceId())) {
            return new MessageResponse(1, "场地编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrderId())) {
            return new MessageResponse(1, "订单ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getReserveDate())) {
            return new MessageResponse(1, "预订日期不能为空！");
        }
        if (CommonUtils.isBlank(o.getReserveInterval())) {
            return new MessageResponse(1, "预订时间区间不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.spaceOccupyInfoDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更场地占用情况", "场地占用情况", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更场地占用情况完成！");
    }

    /**
     * @throws
     * @Title:selectSpaceOccupyInfoByPrimaryKey
     * @Description: TODO(获取场地占用情况)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SpaceOccupyInfo>
     * @author: Arvin
     * @version: 2018-09-14
     */
    @Override
    public SingleResult<SpaceOccupyInfoVo> selectSpaceOccupyInfoByPrimaryKey(String id) throws Exception {
        SingleResult<SpaceOccupyInfoVo> rst = new SingleResult<>();
        rst.setValue(this.spaceOccupyInfoDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteSpaceOccupyInfoBySpaceOccupyInfoId
     * @Description: TODO(删除场地占用情况)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-14
     */
    @Override
    public MessageResponse deleteSpaceOccupyInfoBySpaceOccupyInfoId(String id, UserProp userProp) throws
            Exception {
        this.spaceOccupyInfoDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除场地占用情况", "场地占用情况", id, id,
                "场地占用情况", userProp);
        return new MessageResponse(0, "场地占用情况删除完成！");
    }


    /**
     * 查询场地占用情况
     *
     * @param condition 查询条件
     * @return ResultResponse
     */
    @Override
    public List<SpaceOccupyInfoVo> spaceOccupyInfo(SpaceOccupyInfoQVo condition) {

        return spaceOccupyInfoDao.findList(condition, 0, 10, "");
    }

}

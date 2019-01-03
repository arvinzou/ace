package com.huacainfo.ace.taa.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.taa.dao.RoadDao;
import com.huacainfo.ace.taa.model.Road;
import com.huacainfo.ace.taa.service.RoadService;
import com.huacainfo.ace.taa.vo.RoadQVo;
import com.huacainfo.ace.taa.vo.RoadVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("roadService")
/**
 * @author: 陈晓克
 * @version: 2019-01-03
 * @Description: TODO(道路)
 */
public class RoadServiceImpl implements RoadService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoadDao roadDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(道路分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <RoadVo>
     * @author: 陈晓克
     * @version: 2019-01-03
     */
    @Override
    public PageResult<RoadVo> findRoadList(RoadQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<RoadVo> rst = new PageResult<>();
        List<RoadVo> list = this.roadDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.roadDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertRoad
     * @Description: TODO(添加道路)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-03
     */
    @Override
    public MessageResponse insertRoad(Road o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "级别不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }

        int temp = this.roadDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "道路名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.roadDao.insert(o);
        this.dataBaseLogService.log("添加道路", "道路", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加道路完成！");
    }

    /**
     * @throws
     * @Title:updateRoad
     * @Description: TODO(更新道路)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-03
     */
    @Override
    public MessageResponse updateRoad(Road o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "级别不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.roadDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更道路", "道路", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更道路完成！");
    }

    /**
     * @throws
     * @Title:selectRoadByPrimaryKey
     * @Description: TODO(获取道路)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Road>
     * @author: 陈晓克
     * @version: 2019-01-03
     */
    @Override
    public SingleResult
            <RoadVo> selectRoadByPrimaryKey(String id) throws Exception {
        SingleResult<RoadVo> rst = new SingleResult<>();
        rst.setValue(this.roadDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadByRoadId
     * @Description: TODO(删除道路)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-03
     */
    @Override
    public MessageResponse deleteRoadByRoadId(String id, UserProp userProp) throws
            Exception {
        this.roadDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除道路", "道路", id, id,
                "道路", userProp);
        return new MessageResponse(0, "道路删除完成！");
    }




}

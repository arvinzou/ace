package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.dao.SchedulerMappedDao;
import com.huacainfo.ace.portal.model.SchedulerMapped;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.SchedulerMappedService;
import com.huacainfo.ace.portal.vo.SchedulerMappedQVo;
import com.huacainfo.ace.portal.vo.SchedulerMappedVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("schedulerMappedService")
/**
 * @author: ArvinZou
 * @version: 2019-04-12
 * @Description: TODO(调度配置)
 */
public class SchedulerMappedServiceImpl implements SchedulerMappedService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SchedulerMappedDao schedulerMappedDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(调度配置分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SchedulerMappedVo>
     * @author: ArvinZou
     * @version: 2019-04-12
     */
    @Override
    public PageResult<SchedulerMappedVo> findVoList(SchedulerMappedQVo condition, int start,
                                                    int limit, String orderBy) throws Exception {
        PageResult<SchedulerMappedVo> rst = new PageResult<>();
        List<SchedulerMappedVo> list = this.schedulerMappedDao.findVoList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.schedulerMappedDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertSchedulerMapped
     * @Description: TODO(添加调度配置)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-04-12
     */
    @Override
    public MessageResponse insertSchedulerMapped(SchedulerMapped o, UserProp userProp) throws Exception {


        o.setId(GUIDUtil.getGUID());
        o.setStatus("1");
        o.setCreateDate(new Date());
        schedulerMappedDao.insert(o);

        dataBaseLogService.log("添加调度配置", "调度配置", "", o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "添加调度配置完成！");
    }

    /**
     * @throws
     * @Title:updateSchedulerMapped
     * @Description: TODO(更新调度配置)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-04-12
     */
    @Override
    public MessageResponse updateSchedulerMapped(SchedulerMapped o, UserProp userProp) {

        this.schedulerMappedDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更调度配置",
                "调度配置", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更调度配置完成！");
    }

    /**
     * @throws
     * @Title:selectSchedulerMappedByPrimaryKey
     * @Description: TODO(获取调度配置)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SchedulerMapped>
     * @author: ArvinZou
     * @version: 2019-04-12
     */
    @Override
    public SchedulerMapped selectByPrimaryKey(String id) {
        return this.schedulerMappedDao.selectByPrimaryKey(id);

    }

    /**
     * @throws
     * @Title:deleteSchedulerMappedBySchedulerMappedId
     * @Description: TODO(删除调度配置)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-04-12
     */
    @Override
    public MessageResponse deleteByPrimaryKey(String id, UserProp userProp) throws
            Exception {
        this.schedulerMappedDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除调度配置", "调度配置", id, id,
                "调度配置", userProp);
        return new MessageResponse(0, "调度配置删除完成！");
    }

}

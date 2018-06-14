package com.huacainfo.ace.cu.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.cu.dao.CuProjectUseRecordDao;
import com.huacainfo.ace.cu.model.CuProjectUseRecord;
import com.huacainfo.ace.cu.service.CuProjectUseRecordService;
import com.huacainfo.ace.cu.vo.CuProjectUseRecordQVo;
import com.huacainfo.ace.cu.vo.CuProjectUseRecordVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("cuProjectUseRecordService")
/**
 * @author: Arvin
 * @version: 2018-06-14
 * @Description: TODO(慈善项目-使用记录)
 */
public class CuProjectUseRecordServiceImpl implements CuProjectUseRecordService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CuProjectUseRecordDao cuProjectUseRecordDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(慈善项目-使用记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CuProjectUseRecordVo>
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public PageResult<CuProjectUseRecordVo> findCuProjectUseRecordList(CuProjectUseRecordQVo condition, int start,
                                                                       int limit, String orderBy) throws Exception {
        PageResult<CuProjectUseRecordVo> rst = new PageResult<>();
        List<CuProjectUseRecordVo> list = this.cuProjectUseRecordDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.cuProjectUseRecordDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCuProjectUseRecord
     * @Description: TODO(添加慈善项目-使用记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public MessageResponse insertCuProjectUseRecord(CuProjectUseRecord o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getProjectId())) {
            return new MessageResponse(1, "所属项目ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getUseProjectId())) {
            return new MessageResponse(1, "支出项目ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        int temp = this.cuProjectUseRecordDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "慈善项目-使用记录名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.cuProjectUseRecordDao.insertSelective(o);
        this.dataBaseLogService.log("添加慈善项目-使用记录", "慈善项目-使用记录", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加慈善项目-使用记录完成！");
    }

    /**
     * @throws
     * @Title:updateCuProjectUseRecord
     * @Description: TODO(更新慈善项目-使用记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public MessageResponse updateCuProjectUseRecord(CuProjectUseRecord o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getProjectId())) {
            return new MessageResponse(1, "所属项目ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getUseProjectId())) {
            return new MessageResponse(1, "支出项目ID不能为空！");
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
        this.cuProjectUseRecordDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更慈善项目-使用记录", "慈善项目-使用记录", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更慈善项目-使用记录完成！");
    }

    /**
     * @throws
     * @Title:selectCuProjectUseRecordByPrimaryKey
     * @Description: TODO(获取慈善项目-使用记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CuProjectUseRecord>
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public SingleResult<CuProjectUseRecordVo> selectCuProjectUseRecordByPrimaryKey(String id) throws Exception {
        SingleResult<CuProjectUseRecordVo> rst = new SingleResult<>();
        rst.setValue(this.cuProjectUseRecordDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCuProjectUseRecordByCuProjectUseRecordId
     * @Description: TODO(删除慈善项目-使用记录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public MessageResponse deleteCuProjectUseRecordByCuProjectUseRecordId(String id, UserProp userProp) throws Exception {
        this.cuProjectUseRecordDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除慈善项目-使用记录", "慈善项目-使用记录",
                String.valueOf(id),
                String.valueOf(id), "慈善项目-使用记录", userProp);
        return new MessageResponse(0, "慈善项目-使用记录删除完成！");
    }

}

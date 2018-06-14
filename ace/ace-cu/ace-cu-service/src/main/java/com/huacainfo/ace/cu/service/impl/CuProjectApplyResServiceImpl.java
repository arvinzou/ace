package com.huacainfo.ace.cu.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.cu.dao.CuProjectApplyResDao;
import com.huacainfo.ace.cu.model.CuProjectApplyRes;
import com.huacainfo.ace.cu.service.CuProjectApplyResService;
import com.huacainfo.ace.cu.vo.CuProjectApplyResQVo;
import com.huacainfo.ace.cu.vo.CuProjectApplyResVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("cuProjectApplyResService")
/**
 * @author: Arvin
 * @version: 2018-06-14
 * @Description: TODO(救急难申请表)
 */
public class CuProjectApplyResServiceImpl implements CuProjectApplyResService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CuProjectApplyResDao cuProjectApplyResDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(救急难申请表分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CuProjectApplyResVo>
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public PageResult<CuProjectApplyResVo> findCuProjectApplyResList(CuProjectApplyResQVo condition, int start,
                                                                     int limit, String orderBy) throws Exception {
        PageResult<CuProjectApplyResVo> rst = new PageResult<>();
        List<CuProjectApplyResVo> list = this.cuProjectApplyResDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.cuProjectApplyResDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCuProjectApplyRes
     * @Description: TODO(添加救急难申请表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public MessageResponse insertCuProjectApplyRes(CuProjectApplyRes o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getApplyId())) {
            return new MessageResponse(1, "申请ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getType())) {
            return new MessageResponse(1, "资料类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getResUrl())) {
            return new MessageResponse(1, "资料地址不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        int temp = this.cuProjectApplyResDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "救急难申请表名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.cuProjectApplyResDao.insertSelective(o);
        this.dataBaseLogService.log("添加救急难申请表", "救急难申请表", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加救急难申请表完成！");
    }

    /**
     * @throws
     * @Title:updateCuProjectApplyRes
     * @Description: TODO(更新救急难申请表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public MessageResponse updateCuProjectApplyRes(CuProjectApplyRes o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getApplyId())) {
            return new MessageResponse(1, "申请ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getType())) {
            return new MessageResponse(1, "资料类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getResUrl())) {
            return new MessageResponse(1, "资料地址不能为空！");
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
        this.cuProjectApplyResDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更救急难申请表", "救急难申请表", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更救急难申请表完成！");
    }

    /**
     * @throws
     * @Title:selectCuProjectApplyResByPrimaryKey
     * @Description: TODO(获取救急难申请表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CuProjectApplyRes>
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public SingleResult<CuProjectApplyResVo> selectCuProjectApplyResByPrimaryKey(String id) throws Exception {
        SingleResult<CuProjectApplyResVo> rst = new SingleResult<>();
        rst.setValue(this.cuProjectApplyResDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCuProjectApplyResByCuProjectApplyResId
     * @Description: TODO(删除救急难申请表)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    @Override
    public MessageResponse deleteCuProjectApplyResByCuProjectApplyResId(String id, UserProp userProp) throws Exception {
        this.cuProjectApplyResDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除救急难申请表", "救急难申请表",
                String.valueOf(id),
                String.valueOf(id), "救急难申请表", userProp);
        return new MessageResponse(0, "救急难申请表删除完成！");
    }

}

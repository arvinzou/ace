package com.huacainfo.ace.cu.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.cu.dao.CuProjectResDao;
import com.huacainfo.ace.cu.model.CuProjectRes;
import com.huacainfo.ace.cu.service.CuProjectResService;
import com.huacainfo.ace.cu.vo.CuProjectResQVo;
import com.huacainfo.ace.cu.vo.CuProjectResVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("cuProjectResService")
/**
 * @author: Arvin
 * @version: 2018-08-11
 * @Description: TODO(项目资源)
 */
public class CuProjectResServiceImpl implements CuProjectResService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CuProjectResDao cuProjectResDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(项目资源分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CuProjectResVo>
     * @author: Arvin
     * @version: 2018-08-11
     */
    @Override
    public PageResult<CuProjectResVo> findCuProjectResList(CuProjectResQVo condition, int start,
                                                           int limit, String orderBy) throws Exception {
        PageResult<CuProjectResVo> rst = new PageResult<>();
        List<CuProjectResVo> list = this.cuProjectResDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.cuProjectResDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCuProjectRes
     * @Description: TODO(添加项目资源)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-11
     */
    @Override
    public MessageResponse insertCuProjectRes(CuProjectRes o, UserProp userProp) throws Exception {


        int temp = this.cuProjectResDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "项目资源名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.cuProjectResDao.insertSelective(o);
        this.dataBaseLogService.log("添加项目资源", "项目资源", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加项目资源完成！");
    }

    /**
     * @throws
     * @Title:updateCuProjectRes
     * @Description: TODO(更新项目资源)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-11
     */
    @Override
    public MessageResponse updateCuProjectRes(CuProjectRes o, UserProp userProp) throws Exception {


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.cuProjectResDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更项目资源", "项目资源", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更项目资源完成！");
    }

    /**
     * @throws
     * @Title:selectCuProjectResByPrimaryKey
     * @Description: TODO(获取项目资源)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CuProjectRes>
     * @author: Arvin
     * @version: 2018-08-11
     */
    @Override
    public SingleResult<CuProjectResVo> selectCuProjectResByPrimaryKey(String id) throws Exception {
        SingleResult<CuProjectResVo> rst = new SingleResult<>();
        rst.setValue(this.cuProjectResDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCuProjectResByCuProjectResId
     * @Description: TODO(删除项目资源)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-11
     */
    @Override
    public MessageResponse deleteCuProjectResByCuProjectResId(String id, UserProp userProp) throws
            Exception {
        this.cuProjectResDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除项目资源", "项目资源",
                String.valueOf(id),
                String.valueOf(id), "项目资源", userProp);
        return new MessageResponse(0, "项目资源删除完成！");
    }

}

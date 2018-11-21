package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.StudioImgDao;
import com.huacainfo.ace.jxb.model.StudioImg;
import com.huacainfo.ace.jxb.service.StudioImgService;
import com.huacainfo.ace.jxb.vo.StudioImgQVo;
import com.huacainfo.ace.jxb.vo.StudioImgVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("studioImgService")
/**
 * @author: Arvin
 * @version: 2018-07-25
 * @Description: TODO(工作室图片)
 */
public class StudioImgServiceImpl implements StudioImgService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StudioImgDao studioImgDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(工作室图片分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <StudioImgVo>
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public PageResult<StudioImgVo> findStudioImgList(StudioImgQVo condition, int start,
                                                     int limit, String orderBy) throws Exception {
        PageResult<StudioImgVo> rst = new PageResult<>();
        List<StudioImgVo> list = this.studioImgDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.studioImgDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertStudioImg
     * @Description: TODO(添加工作室图片)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse insertStudioImg(StudioImg o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getStudioId())) {
            return new MessageResponse(1, "所属工作室不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getImgUrl())) {
            return new MessageResponse(1, "图片地址不能为空！");
        }


        int temp = this.studioImgDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "工作室图片名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
//        o.setStatus("1");
//        o.setCreateUserName(userProp.getName());
//        o.setCreateUserId(userProp.getUserId());
        this.studioImgDao.insertSelective(o);
        this.dataBaseLogService.log("添加工作室图片", "工作室图片", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加工作室图片完成！");
    }

    /**
     * @throws
     * @Title:updateStudioImg
     * @Description: TODO(更新工作室图片)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse updateStudioImg(StudioImg o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getStudioId())) {
            return new MessageResponse(1, "所属工作室不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getImgUrl())) {
            return new MessageResponse(1, "图片地址不能为空！");
        }


//        o.setLastModifyDate(new Date());
//        o.setLastModifyUserName(userProp.getName());
//        o.setLastModifyUserId(userProp.getUserId());
        this.studioImgDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更工作室图片", "工作室图片", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更工作室图片完成！");
    }

    /**
     * @throws
     * @Title:selectStudioImgByPrimaryKey
     * @Description: TODO(获取工作室图片)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<StudioImg>
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public SingleResult<StudioImgVo> selectStudioImgByPrimaryKey(String id) throws Exception {
        SingleResult<StudioImgVo> rst = new SingleResult<>();
        rst.setValue(this.studioImgDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteStudioImgByStudioImgId
     * @Description: TODO(删除工作室图片)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse deleteStudioImgByStudioImgId(String id, UserProp userProp) throws
            Exception {
        this.studioImgDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除工作室图片", "工作室图片",
                String.valueOf(id),
                String.valueOf(id), "工作室图片", userProp);
        return new MessageResponse(0, "工作室图片删除完成！");
    }

}

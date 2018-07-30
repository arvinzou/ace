package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.StudioDao;
import com.huacainfo.ace.jxb.model.Studio;
import com.huacainfo.ace.jxb.service.StudioService;
import com.huacainfo.ace.jxb.vo.StudioQVo;
import com.huacainfo.ace.jxb.vo.StudioVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("studioService")
/**
 * @author: Arvin
 * @version: 2018-07-25
 * @Description: TODO(工作室)
 */
public class StudioServiceImpl implements StudioService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StudioDao studioDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(工作室分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <StudioVo>
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public PageResult<StudioVo> findStudioList(StudioQVo condition, int start,
                                               int limit, String orderBy) throws Exception {
        PageResult<StudioVo> rst = new PageResult<>();
        List<StudioVo> list = this.studioDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.studioDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertStudio
     * @Description: TODO(添加工作室)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse insertStudio(Studio o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCounselorId())) {
            return new MessageResponse(1, "负责人不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntroduce())) {
            return new MessageResponse(1, "介绍不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        int temp = this.studioDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "工作室名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("0");
        this.studioDao.insertSelective(o);
        this.dataBaseLogService.log("添加工作室", "工作室", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加工作室完成！");
    }

    /**
     * @throws
     * @Title:updateStudio
     * @Description: TODO(更新工作室)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse updateStudio(Studio o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCounselorId())) {
            return new MessageResponse(1, "负责人不能为空！");
        }
        if (CommonUtils.isBlank(o.getIntroduce())) {
            return new MessageResponse(1, "介绍不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        this.studioDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更工作室", "工作室", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更工作室完成！");
    }

    /**
     * @throws
     * @Title:selectStudioByPrimaryKey
     * @Description: TODO(获取工作室)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Studio>
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public SingleResult<StudioVo> selectStudioByPrimaryKey(String id) throws Exception {
        SingleResult<StudioVo> rst = new SingleResult<>();
        rst.setValue(this.studioDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteStudioByStudioId
     * @Description: TODO(删除工作室)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    @Override
    public MessageResponse deleteStudioByStudioId(String id, UserProp userProp) throws Exception {
        this.studioDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除工作室", "工作室",
                String.valueOf(id),
                String.valueOf(id), "工作室", userProp);
        return new MessageResponse(0, "工作室删除完成！");
    }

    /**
     * 工作室审核
     *
     * @param studioId
     * @param auditRs     审核结果 1-审核通过 2-审核拒绝
     * @param curUserProp
     * @return
     */
    @Override
    public MessageResponse audit(String studioId, String auditRs, UserProp curUserProp) throws Exception {
        StudioVo studioVo = studioDao.selectVoByPrimaryKey(studioId);
        if (null == studioVo) {
            return new MessageResponse(ResultCode.FAIL, "工作室资料丢失");
        }

        studioVo.setStatus(auditRs);
        return updateStudio(studioVo, curUserProp);
    }

}

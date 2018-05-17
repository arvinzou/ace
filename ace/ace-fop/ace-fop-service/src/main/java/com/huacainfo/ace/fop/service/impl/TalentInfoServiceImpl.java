package com.huacainfo.ace.fop.service.impl;


import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.fop.dao.TalentInfoDao;
import com.huacainfo.ace.fop.model.TalentInfo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.fop.service.TalentInfoService;
import com.huacainfo.ace.fop.vo.TalentInfoVo;
import com.huacainfo.ace.fop.vo.TalentInfoQVo;

@Service("talentInfoService")
/**
 * @author: huacai003
 * @version: 2018-05-17
 * @Description: TODO(人才信息)
 */
public class TalentInfoServiceImpl implements TalentInfoService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TalentInfoDao talentInfoDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(人才信息分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TalentInfoVo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public PageResult
            <TalentInfoVo> findTalentInfoList(TalentInfoQVo condition, int start,
                                              int limit, String orderBy) throws Exception {
        PageResult
                <TalentInfoVo> rst = new PageResult
                <TalentInfoVo>();
        List
                <TalentInfoVo> list = this.talentInfoDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.talentInfoDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertTalentInfo
     * @Description: TODO(添加人才信息)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse insertTalentInfo(TalentInfo o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationId())) {
            return new MessageResponse(1, "关联ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationType())) {
            return new MessageResponse(1, "关联类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getReleaseDate())) {
            return new MessageResponse(1, "发布时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        int temp = this.talentInfoDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "人才信息名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.talentInfoDao.insertSelective(o);
        this.dataBaseLogService.log("添加人才信息", "人才信息", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加人才信息完成！");
    }

    /**
     * @throws
     * @Title:updateTalentInfo
     * @Description: TODO(更新人才信息)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse updateTalentInfo(TalentInfo o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationId())) {
            return new MessageResponse(1, "关联ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationType())) {
            return new MessageResponse(1, "关联类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getReleaseDate())) {
            return new MessageResponse(1, "发布时间不能为空！");
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
        this.talentInfoDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更人才信息", "人才信息", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更人才信息完成！");
    }

    /**
     * @throws
     * @Title:selectTalentInfoByPrimaryKey
     * @Description: TODO(获取人才信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TalentInfo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public SingleResult
            <TalentInfoVo> selectTalentInfoByPrimaryKey(String id) throws Exception {
        SingleResult
                <TalentInfoVo> rst = new SingleResult
                <TalentInfoVo>();
        rst.setValue(this.talentInfoDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteTalentInfoByTalentInfoId
     * @Description: TODO(删除人才信息)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse deleteTalentInfoByTalentInfoId(String id, UserProp
            userProp) throws Exception {
        this.talentInfoDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除人才信息", "人才信息",
                String.valueOf(id),
                String.valueOf(id), "人才信息", userProp);
        return new MessageResponse(0, "人才信息删除完成！");
    }

}

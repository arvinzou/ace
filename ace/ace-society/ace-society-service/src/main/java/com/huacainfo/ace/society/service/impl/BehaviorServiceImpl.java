package com.huacainfo.ace.society.service.impl;


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
import com.huacainfo.ace.society.dao.BehaviorDao;
import com.huacainfo.ace.society.model.Behavior;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.service.BehaviorService;
import com.huacainfo.ace.society.vo.BehaviorVo;
import com.huacainfo.ace.society.vo.BehaviorQVo;

@Service("behaviorService")
/**
 * @author: lcan
 * @version: 2018-09-11
 * @Description: TODO(市民行为详情)
 */
public class BehaviorServiceImpl implements BehaviorService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BehaviorDao behaviorDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(市民行为详情分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <BehaviorVo>
     * @author: lcan
     * @version: 2018-09-11
     */
    @Override
    public PageResult
            <BehaviorVo> findBehaviorList(BehaviorQVo condition, int start,
                                          int limit, String orderBy) throws Exception {
        PageResult
                <BehaviorVo> rst = new PageResult<>();
        List
                <BehaviorVo> list = this.behaviorDao.findList(condition, start, start + limit, orderBy);
        logger.debug("*****************************************" + condition.getTitle());
        logger.info("*****************************************" + condition.getTitle());
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.behaviorDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertBehavior
     * @Description: TODO(添加市民行为详情)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: lcan
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse insertBehavior(Behavior o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键-GUID不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "提交人不能为空！");
        }
        if (CommonUtils.isBlank(o.getSubmitDate())) {
            return new MessageResponse(1, "反馈日期不能为空！");
        }


        int temp = this.behaviorDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "市民行为详情名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.behaviorDao.insertSelective(o);
        this.dataBaseLogService.log("添加市民行为详情", "市民行为详情", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加市民行为详情完成！");
    }

    /**
     * @throws
     * @Title:updateBehavior
     * @Description: TODO(更新市民行为详情)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: lcan
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse updateBehavior(Behavior o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键-GUID不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "提交人不能为空！");
        }
        if (CommonUtils.isBlank(o.getSubmitDate())) {
            return new MessageResponse(1, "反馈日期不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.behaviorDao.updateByPrimaryKeySelective(o.getId());
        this.dataBaseLogService.log("变更市民行为详情", "市民行为详情", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更市民行为详情完成！");
    }

    /**
     * @throws
     * @Title:selectBehaviorByPrimaryKey
     * @Description: TODO(获取市民行为详情)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Behavior>
     * @author: lcan
     * @version: 2018-09-11
     */
    @Override
    public SingleResult
            <BehaviorVo> selectBehaviorByPrimaryKey(String id) throws Exception {
        SingleResult
                <BehaviorVo> rst = new SingleResult<>();
        rst.setValue(this.behaviorDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteBehaviorByBehaviorId
     * @Description: TODO(删除市民行为详情)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: lcan
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse deleteBehaviorByBehaviorId(String id, UserProp userProp) throws
            Exception {
        this.behaviorDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除市民行为详情", "市民行为详情",
                String.valueOf(id),
                String.valueOf(id), "市民行为详情", userProp);
        return new MessageResponse(0, "市民行为详情删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核市民行为详情)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: lcan
     * @version: 2018-09-11
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {

        behaviorDao.updateByPrimaryKeySelective(id);


        dataBaseLogService.log("审核市民行为详情", "市民行为详情",
                String.valueOf(id), String.valueOf(id), "市民行为详情", userProp);
        return new MessageResponse(0, "市民行为详情审核完成！");
    }

}

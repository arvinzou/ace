package com.huacainfo.ace.society.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.dao.FeedBackDao;
import com.huacainfo.ace.society.model.FeedBack;
import com.huacainfo.ace.society.service.FeedBackService;
import com.huacainfo.ace.society.vo.FeedBackQVo;
import com.huacainfo.ace.society.vo.FeedBackVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("feedBackService")
/**
 * @author: Arvin
 * @version: 2018-11-27
 * @Description: TODO(问题反馈)
 */
public class FeedBackServiceImpl implements FeedBackService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FeedBackDao feedBackDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(问题反馈分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FeedBackVo>
     * @author: Arvin
     * @version: 2018-11-27
     */
    @Override
    public PageResult<FeedBackVo> findFeedBackList(FeedBackQVo condition, int start,
                                                   int limit, String orderBy) throws Exception {
        PageResult<FeedBackVo> rst = new PageResult<>();
        List<FeedBackVo> list = this.feedBackDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.feedBackDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertFeedBack
     * @Description: TODO(添加问题反馈)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-27
     */
    @Override
    public MessageResponse insertFeedBack(FeedBack o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "用户ID不能为空！");
        }


        int temp = this.feedBackDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(1, "问题反馈名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.feedBackDao.insert(o);
        this.dataBaseLogService.log("添加问题反馈", "问题反馈", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加问题反馈完成！");
    }

    /**
     * @throws
     * @Title:updateFeedBack
     * @Description: TODO(更新问题反馈)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-27
     */
    @Override
    public MessageResponse updateFeedBack(FeedBack o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "用户ID不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.feedBackDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更问题反馈", "问题反馈", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更问题反馈完成！");
    }

    /**
     * @throws
     * @Title:selectFeedBackByPrimaryKey
     * @Description: TODO(获取问题反馈)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FeedBack>
     * @author: Arvin
     * @version: 2018-11-27
     */
    @Override
    public SingleResult<FeedBackVo> selectFeedBackByPrimaryKey(String id) throws Exception {
        SingleResult<FeedBackVo> rst = new SingleResult<>();
        rst.setValue(this.feedBackDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFeedBackByFeedBackId
     * @Description: TODO(删除问题反馈)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-27
     */
    @Override
    public MessageResponse deleteFeedBackByFeedBackId(String id, UserProp userProp) throws
            Exception {
        this.feedBackDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除问题反馈", "问题反馈", id, id,
                "问题反馈", userProp);
        return new MessageResponse(0, "问题反馈删除完成！");
    }

    /**
     * 提交问题反馈
     *
     * @param params 输入参数
     * @return ResultResponse
     */
    @Override
    public ResultResponse submit(FeedBackVo params) {
        params.setId(GUIDUtil.getGUID());
        params.setCreateDate(DateUtil.getNowDate());
        params.setCreateUserId("system");
        params.setCreateUserName("system");

        feedBackDao.insert(params);

        return new ResultResponse(ResultCode.SUCCESS, "提交成功");
    }

}

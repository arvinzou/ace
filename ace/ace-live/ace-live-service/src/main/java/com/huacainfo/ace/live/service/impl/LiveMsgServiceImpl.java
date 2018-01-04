package com.huacainfo.ace.live.service.impl;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.live.dao.LiveMsgDao;
import com.huacainfo.ace.live.model.LiveMsg;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.live.service.LiveMsgService;
import com.huacainfo.ace.live.vo.LiveMsgVo;
import com.huacainfo.ace.live.vo.LiveMsgQVo;

@Service("liveMsgService")
/**
 * @author: 陈晓克
 * @version: 2018-01-03
 * @Description: TODO(互动内容)
 */
public class LiveMsgServiceImpl implements LiveMsgService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LiveMsgDao liveMsgDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(互动内容分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<LiveMsgVo>
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @Override
    public PageResult<LiveMsgVo> findLiveMsgList(LiveMsgQVo condition, int start,
                                                 int limit, String orderBy) throws Exception {
        PageResult<LiveMsgVo> rst = new PageResult<LiveMsgVo>();
        List<LiveMsgVo> list = this.liveMsgDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.liveMsgDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertLiveMsg
     * @Description: TODO(添加互动内容)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @Override
    public MessageResponse insertLiveMsg(LiveMsg o)
            throws Exception {
        o.setId(UUID.randomUUID().toString());
        if (CommonUtils.isBlank(o.getRid())) {
            return new MessageResponse(1, "直播间编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getUid())) {
            return new MessageResponse(1, "用户编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "聊天内容不能为空！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        this.liveMsgDao.insert(o);
        return new MessageResponse(0, "添加互动内容完成！");
    }

    /**
     * @throws
     * @Title:updateLiveMsg
     * @Description: TODO(更新互动内容)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @Override
    public MessageResponse updateLiveMsg(String id, String status)
            throws Exception {
        if (CommonUtils.isBlank(id)) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(status)) {
            return new MessageResponse(1, "状态不能为空！");
        }
        this.liveMsgDao.updateByPrimaryKey(id, status);
        return new MessageResponse(0, "变更互动内容完成！");
    }

    /**
     * @throws
     * @Title:selectLiveMsgByPrimaryKey
     * @Description: TODO(获取互动内容)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LiveMsg>
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @Override
    public SingleResult<LiveMsgVo> selectLiveMsgByPrimaryKey(String id) throws Exception {
        SingleResult<LiveMsgVo> rst = new SingleResult<LiveMsgVo>();
        rst.setValue(this.liveMsgDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteLiveMsgByLiveMsgId
     * @Description: TODO(删除互动内容)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @Override
    public MessageResponse deleteLiveMsgByLiveMsgId(String id,
                                                    UserProp userProp) throws Exception {
        this.liveMsgDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除互动内容", "互动内容", String.valueOf(id),
                String.valueOf(id), "互动内容", userProp);
        return new MessageResponse(0, "互动内容删除完成！");
    }
}

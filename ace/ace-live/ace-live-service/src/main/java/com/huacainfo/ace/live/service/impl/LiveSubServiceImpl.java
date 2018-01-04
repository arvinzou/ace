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
import com.huacainfo.ace.live.dao.LiveSubDao;
import com.huacainfo.ace.live.model.LiveSub;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.live.service.LiveSubService;
import com.huacainfo.ace.live.vo.LiveSubVo;
import com.huacainfo.ace.live.vo.LiveSubQVo;

@Service("liveSubService")
/**
 * @author: 陈晓克
 * @version: 2018-01-03
 * @Description: TODO(图文直播)
 */
public class LiveSubServiceImpl implements LiveSubService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LiveSubDao liveSubDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(图文直播分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<LiveSubVo>
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @Override
    public PageResult<LiveSubVo> findLiveSubList(LiveSubQVo condition, int start,
                                                 int limit, String orderBy) throws Exception {
        PageResult<LiveSubVo> rst = new PageResult<LiveSubVo>();
        List<LiveSubVo> list = this.liveSubDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.liveSubDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertLiveSub
     * @Description: TODO(添加图文直播)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @Override
    public MessageResponse insertLiveSub(LiveSub o)
            throws Exception {
        o.setId(UUID.randomUUID().toString());
        //o.setId(String.valueOf(new Date().getTime()));
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getRid())) {
            return new MessageResponse(1, "直播间编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getUid())) {
            return new MessageResponse(1, "用户编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "直播内容不能为空！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        this.liveSubDao.insert(o);
        return new MessageResponse(0, "添加图文直播完成！");
    }

    /**
     * @throws
     * @Title:updateLiveSub
     * @Description: TODO(更新图文直播)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @Override
    public MessageResponse updateLiveSub(String id, String status)
            throws Exception {
        if (CommonUtils.isBlank(id)) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(status)) {
            return new MessageResponse(1, "状态不能为空！");
        }
        this.liveSubDao.updateByPrimaryKey(id, status);
        return new MessageResponse(0, "变更图文直播完成！");
    }

    /**
     * @throws
     * @Title:selectLiveSubByPrimaryKey
     * @Description: TODO(获取图文直播)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LiveSub>
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @Override
    public SingleResult<LiveSubVo> selectLiveSubByPrimaryKey(String id) throws Exception {
        SingleResult<LiveSubVo> rst = new SingleResult<LiveSubVo>();
        rst.setValue(this.liveSubDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteLiveSubByLiveSubId
     * @Description: TODO(删除图文直播)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @Override
    public MessageResponse deleteLiveSubByLiveSubId(String id, UserProp userProp) throws Exception {
        this.liveSubDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除图文直播", "图文直播", String.valueOf(id),
                String.valueOf(id), "图文直播", userProp);
        return new MessageResponse(0, "图文直播删除完成！");
    }
}

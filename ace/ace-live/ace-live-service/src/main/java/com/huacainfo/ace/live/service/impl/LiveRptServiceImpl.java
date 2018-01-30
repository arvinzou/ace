package com.huacainfo.ace.live.service.impl;


import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.live.dao.LiveImgDao;
import com.huacainfo.ace.live.dao.LiveRptDao;
import com.huacainfo.ace.live.model.LiveImg;
import com.huacainfo.ace.live.model.LiveRpt;
import com.huacainfo.ace.live.service.LiveRptService;
import com.huacainfo.ace.live.vo.LiveRptQVo;
import com.huacainfo.ace.live.vo.LiveRptVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("liveRptService")
/**
 * @author: 陈晓克
 * @version: 2018-01-03
 * @Description: TODO(图文直播)
 */
public class LiveRptServiceImpl implements LiveRptService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LiveRptDao liveRptDao;
    @Autowired
    private LiveImgDao liveImgDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private KafkaProducerService kafkaProducerService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(图文直播分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<LiveRptVo>
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @Override
    public PageResult<LiveRptVo> findLiveRptList(LiveRptQVo condition, int start,
                                                 int limit, String orderBy) throws Exception {
        PageResult<LiveRptVo> rst = new PageResult<LiveRptVo>();
        List<LiveRptVo> list = this.liveRptDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.liveRptDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertLiveRpt
     * @Description: TODO(添加图文直播)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @Override
    public MessageResponse insertLiveRpt(LiveRpt o, List<LiveImg> imgs)
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
        o.setCreateTime(new Date());
        o.setStatus("1");
        this.liveRptDao.insert(o);
        for (LiveImg img : imgs) {
            img.setId(UUID.randomUUID().toString());
            img.setRptId(o.getId());
            img.setCreateTime(new Date());
            this.liveImgDao.insert(img);
        }
        Map<String, String> data = new HashMap<String, String>();
        data.put("rid", "rsub");
        data.put("message", JSON.toJSON(o).toString());
        this.logger.info("{}", data);
        this.kafkaProducerService.sendMsg("LIVE-MSG-QM", data);
        return new MessageResponse(0, "添加图文直播完成！");
    }

    /**
     * @throws
     * @Title:updateLiveRpt
     * @Description: TODO(更新图文直播)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @Override
    public MessageResponse updateLiveRpt(LiveRpt o)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }

        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "直播内容不能为空！");
        }
        this.liveRptDao.updateByPrimaryKey(o);
        return new MessageResponse(0, "变更图文直播完成！");
    }


    /**
     * @throws
     * @Title:updateLiveRpt
     * @Description: TODO(更新图文直播)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @Override
    public MessageResponse updateLiveRptStatus(String id, String status)
            throws Exception {
        if (CommonUtils.isBlank(id)) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(status)) {
            return new MessageResponse(1, "状态不能为空！");
        }
        this.liveRptDao.updateStatusByPrimaryKey(id, status);
        return new MessageResponse(0, "变更图文直播完成！");
    }

    /**
     * @param data
     * @return
     */
    @Override
    public MessageResponse updateSequence(String data) {

        List<Map<String, Object>> dataList = JsonUtil.toObject(data, List.class);
        String rptId;
        int sort;
        for (Map<String, Object> map : dataList) {
            rptId = (String) map.get("id");
            sort = (int) map.get("index");

            liveRptDao.updateSortByPrimaryKey(rptId, sort);
        }

        return new MessageResponse(0, "OK！");
    }

    /**
     * @throws
     * @Title:selectLiveRptByPrimaryKey
     * @Description: TODO(获取图文直播)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LiveRpt>
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @Override
    public SingleResult<LiveRptVo> selectLiveRptByPrimaryKey(String id) throws Exception {
        SingleResult<LiveRptVo> rst = new SingleResult<LiveRptVo>();
        rst.setValue(this.liveRptDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteLiveRptByLiveRptId
     * @Description: TODO(删除图文直播)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-03
     */
    @Override
    public MessageResponse deleteLiveRptByLiveRptId(String id, UserProp userProp) throws Exception {
        this.liveRptDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除图文直播", "图文直播", String.valueOf(id),
                String.valueOf(id), "图文直播", userProp);
        return new MessageResponse(0, "图文直播删除完成！");
    }

    /**
     * @throws
     * @Title:updateSortByPrimaryKey
     * @Description: TODO(更新图文直播顺序)
     * @param: @param id
     * @param: @param sort
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-07
     */
    @Override
    public MessageResponse updateSortByPrimaryKey(String id, int sort) throws Exception {
        this.liveRptDao.updateSortByPrimaryKey(id, sort);
        return new MessageResponse(0, "OK！");
    }
}

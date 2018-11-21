package com.huacainfo.ace.live.service.impl;


import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.live.dao.LiveCmtDao;
import com.huacainfo.ace.live.dao.LiveDao;
import com.huacainfo.ace.live.dao.LiveRptDao;
import com.huacainfo.ace.live.model.LiveCmt;
import com.huacainfo.ace.live.service.LiveCmtService;
import com.huacainfo.ace.live.vo.LiveCmtQVo;
import com.huacainfo.ace.live.vo.LiveCmtVo;
import com.huacainfo.ace.live.vo.LiveRptVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @author: 陈晓克
 * @version: 2018-01-13
 * @Description: TODO(评论)
 */
@Service("liveCmtService")
public class LiveCmtServiceImpl implements LiveCmtService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LiveCmtDao liveCmtDao;
    @Autowired
    private LiveDao liveDao;
    @Autowired
    private LiveRptDao liveRptDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评论分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<LiveCmtVo>
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    @Override
    public PageResult<LiveCmtVo> findLiveCmtList(LiveCmtQVo condition, int start,
                                                 int limit, String orderBy) throws Exception {
        PageResult<LiveCmtVo> rst = new PageResult<LiveCmtVo>();
        List<LiveCmtVo> list = this.liveCmtDao.findList(condition,
                start,  limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.liveCmtDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertLiveCmt
     * @Description: TODO(添加评论)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    @Override
    public MessageResponse insertLiveCmt(LiveCmt o)
            throws Exception {
        o.setId(UUID.randomUUID().toString());
        if (CommonUtils.isBlank(o.getRptId())) {
            return new MessageResponse(1, "报道编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getUid())) {
            return new MessageResponse(1, "用户编号不能为空！");
        }
        String content=o.getContent();
        if (CommonUtils.isBlank(content)) {
            return new MessageResponse(1, "聊天内容不能为空！");
        }
        o.setStatus("1");
        o.setCreateTime(new Date());
        this.liveCmtDao.insert(o);

        LiveRptVo rpt=this.liveRptDao.selectByPrimaryKey(o.getRptId());
        String rid=rpt.getRid();
        Map<String, String> data = new HashMap<String, String>();
        data.put("rid", rid);
        data.put("cmd", "reload.rpt");
        this.logger.info("{}", data);
        this.kafkaProducerService.sendMsg("topic.sys.msg.live.client", data);
        return new MessageResponse(0, "添加评论完成！");
    }

    /**
     * @throws
     * @Title:updateLiveCmt
     * @Description: TODO(更新评论)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    @Override
    public MessageResponse updateLiveCmt(String id, String status)
            throws Exception {
        if (CommonUtils.isBlank(id)) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(status)) {
            return new MessageResponse(1, "状态不能为空！");
        }
        this.liveCmtDao.updateByPrimaryKey(id, status);
        return new MessageResponse(0, "变更互动内容完成！");
    }

    /**
     * @throws
     * @Title:selectLiveCmtByPrimaryKey
     * @Description: TODO(获取评论)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LiveCmt>
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    @Override
    public SingleResult<LiveCmtVo> selectLiveCmtByPrimaryKey(String id) throws Exception {
        SingleResult<LiveCmtVo> rst = new SingleResult<LiveCmtVo>();
        rst.setValue(this.liveCmtDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteLiveCmtByLiveCmtId
     * @Description: TODO(删除评论)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-01-13
     */
    @Override
    public MessageResponse deleteLiveCmtByLiveCmtId(String id,
                                                    UserProp userProp) throws Exception {
        this.liveCmtDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除评论", "评论", String.valueOf(id),
                String.valueOf(id), "评论", userProp);
        return new MessageResponse(0, "评论删除完成！");
    }

    @Override
    public List<String> findSensitiveWordsList(String deptId) throws Exception {
        List<String> list = this.liveCmtDao.findSensitiveWordsListBydeptId(deptId);
        return list;
    }

    @Override
    public MessageResponse updateStatus(String id, String status)
            throws Exception {
        if (CommonUtils.isBlank(id)) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(status)) {
            return new MessageResponse(1, "状态不能为空！");
        }
        this.liveCmtDao.updateStatus(id, status);
        return new MessageResponse(0, "OK！");
    }
}

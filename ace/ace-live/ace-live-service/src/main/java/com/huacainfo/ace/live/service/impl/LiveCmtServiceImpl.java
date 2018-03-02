package com.huacainfo.ace.live.service.impl;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.huacainfo.ace.live.dao.LiveDao;
import com.huacainfo.ace.live.model.Live;
import com.huacainfo.ace.portal.model.SensitiveWords;
import com.huacainfo.ace.portal.vo.SensitiveWordsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.live.dao.LiveCmtDao;
import com.huacainfo.ace.live.model.LiveCmt;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.live.service.LiveCmtService;
import com.huacainfo.ace.live.vo.LiveCmtVo;
import com.huacainfo.ace.live.vo.LiveCmtQVo;

@Service("liveCmtService")
/**
 * @author: 陈晓克
 * @version: 2018-01-13
 * @Description: TODO(评论)
 */
public class LiveCmtServiceImpl implements LiveCmtService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LiveCmtDao liveCmtDao;
    @Autowired
    private LiveDao liveDao;

    @Autowired
    private DataBaseLogService dataBaseLogService;

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
                start, start + limit, orderBy);
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
    public MessageResponse insertLiveCmt(LiveCmt o, String corpId)
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
        PageResult<SensitiveWordsVo> rst = new PageResult<>();
        SensitiveWords condition=new SensitiveWords();
        condition.setDeptId(corpId);
        List<SensitiveWordsVo> list = this.liveCmtDao.findSensitiveWordsList(condition);
        for(SensitiveWordsVo sw:list){
            String x="";
            String word=sw.getWord();
            if(content.indexOf(word)==-1){
               continue;
            }
            for(int i=0;i<sw.getWord().length();i++){
                x+="*";
            }
            content.replaceAll(word,x);
        }
        rst.setRows(list);
        o.setStatus("1");
        o.setCreateTime(new Date());
        this.liveCmtDao.insert(o);
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
    public Live findLiveByPrimaryKey(String id) {
        return liveDao.selectByPrimaryKey(id);
    }

}

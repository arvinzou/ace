package com.huacainfo.ace.partyschool.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.partyschool.dao.NoticeDao;
import com.huacainfo.ace.partyschool.dao.NoticeStatusDao;
import com.huacainfo.ace.partyschool.model.Notice;
import com.huacainfo.ace.partyschool.service.NoticeStatusService;
import com.huacainfo.ace.partyschool.service.SclNoticeService;
import com.huacainfo.ace.partyschool.vo.NoticeQVo;
import com.huacainfo.ace.partyschool.vo.NoticeStatusQVo;
import com.huacainfo.ace.partyschool.vo.NoticeVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sclNoticeService")
/**
 * @author: Arvin
 * @version: 2019-01-06
 * @Description: TODO(通知公告)
 */
public class SclNoticeServiceImpl implements SclNoticeService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private NoticeStatusDao noticeStatusDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private NoticeStatusService noticeStatusService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(通知公告分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <NoticeVo>
     * @author: Arvin
     * @version: 2019-01-06
     */
    @Override
    public PageResult<NoticeVo> findNoticeList(NoticeQVo condition, int start,
                                               int limit, String orderBy) throws Exception {
        PageResult<NoticeVo> rst = new PageResult<>();
        List<NoticeVo> list = this.noticeDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.noticeDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public ResultResponse findNoticeById(String id) throws Exception {
        if (CommonUtils.isBlank(id)){
            return new ResultResponse(1, "序号不能为空!");
        }
        Notice notice = this.noticeDao.selectByPrimaryKey(id);
        return new ResultResponse(ResultCode.SUCCESS,"获取成功",notice);
    }

    @Override
    public ResultResponse findNoticeLists(NoticeStatusQVo condition, int start, int limit, String orderBy, UserProp userProp) throws Exception {
        condition.setUserId(userProp.getUserId());
        List<NoticeVo> noticeVo = new ArrayList<>();
        Map<String, Object> map = new HashMap<String, Object>();
        condition.setStatus("1");
        noticeVo = this.noticeDao.findMyNoticeList(condition, start, limit, orderBy);
        map.put("list", noticeVo);
        condition.setStatus("2");
        noticeVo = this.noticeDao.findMyNoticeList(condition, start, limit, orderBy);
        map.put("history", noticeVo);
        map.put("count", this.noticeDao.findUnreadCount(userProp.getUserId()));
        return new ResultResponse(0, "通知公告获取完成！", map);
    }

    @Override
    public ResultResponse findPulicNoticeLists(String classesId) throws Exception {
        List<NoticeVo> list = this.noticeDao.findPublicNoticeList(classesId);
        return new ResultResponse(0, "通知公告获取完成！", list);
    }

    /**
     * @throws
     * @Title:insertNotice
     * @Description: TODO(添加通知公告)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-06
     */
    @Override
    public MessageResponse insertNotice(Notice o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "序号不能为空!");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空!");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "类别不能为空!");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "内容不能为空!");
        }
        int temp = this.noticeDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "通知公告名称重复！");
        }
        o.setStatus("1");
        o.setPublisher(userProp.getName());
        o.setPushDate(new java.util.Date());
        this.noticeDao.insert(o);
        this.dataBaseLogService.log("添加通知公告", "通知公告", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateNotice
     * @Description: TODO(更新通知公告)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-06
     */
    @Override
    public MessageResponse updateNotice(Notice o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "序号不能为空!");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空!");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "类别不能为空!");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "内容不能为空!");
        }
        this.noticeDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更通知公告", "通知公告", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectNoticeByPrimaryKey
     * @Description: TODO(获取通知公告)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Notice>
     * @author: Arvin
     * @version: 2019-01-06
     */
    @Override
    public SingleResult<NoticeVo> updateAndSelectNoticeVoById(String id, String server, UserProp userProp) throws Exception {
        SingleResult<NoticeVo> rst = new SingleResult<>();
        NoticeVo o = this.noticeDao.selectVoByPrimaryKey(id);
        o.setFiles(this.noticeDao.selectFilesById(id, server));
        o.setUsers(this.noticeDao.selectUsersById(id));
        rst.setValue(o);
        this.noticeDao.updateStatus(id, userProp.getUserId());
        return rst;
    }


    /**
     * @throws
     * @Title:deleteNoticeByNoticeId
     * @Description: TODO(删除通知公告)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-06
     */
    @Override
    public MessageResponse deleteNoticeByNoticeId(String id, UserProp userProp) throws
            Exception {
        this.noticeDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除通知公告", "通知公告", id, id,
                "通知公告", userProp);
        return new MessageResponse(0, "通知公告删除完成！");
    }
}

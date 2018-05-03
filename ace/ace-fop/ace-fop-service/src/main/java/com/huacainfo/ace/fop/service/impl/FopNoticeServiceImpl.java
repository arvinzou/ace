package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.dao.FopNoticeDao;
import com.huacainfo.ace.fop.model.FopNotice;
import com.huacainfo.ace.fop.service.FopNoticeService;
import com.huacainfo.ace.fop.vo.FopNoticeQVo;
import com.huacainfo.ace.fop.vo.FopNoticeVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("fopNoticeService")
/**
 * @author: Arvin
 * @version: 2018-05-03
 * @Description: 通知公告
 */
public class FopNoticeServiceImpl implements FopNoticeService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopNoticeDao fopNoticeDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

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
     * <FopNoticeVo>
     * @author: Arvin
     * @version: 2018-05-03
     */
    @Override
    public PageResult<FopNoticeVo> findFopNoticeList(FopNoticeQVo condition, int start,
                                                     int limit, String orderBy) throws Exception {
        PageResult
                <FopNoticeVo> rst = new PageResult
                <FopNoticeVo>();
        List
                <FopNoticeVo> list = this.fopNoticeDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopNoticeDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @param start
     * @param limit
     * @return
     * @throws Exception
     */
    @Override
    public PageResult<FopNoticeVo> findNoticeList(int start, int limit) throws Exception {
        FopNoticeQVo condition = new FopNoticeQVo();
        PageResult<FopNoticeVo> rst = new PageResult<FopNoticeVo>();
        List<FopNoticeVo> list = this.fopNoticeDao.findList(condition, start, limit, null);
        rst.setRows(list);
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopNotice
     * @Description: TODO(添加通知公告)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-03
     */
    @Override
    public MessageResponse insertFopNotice(FopNotice o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getCoverUrl())) {
            return new MessageResponse(1, "封面地址不能为空！");
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

        int temp = this.fopNoticeDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "通知公告名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopNoticeDao.insertSelective(o);
        this.dataBaseLogService.log("添加通知公告", "通知公告", "",
                o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "添加通知公告完成！");
    }

    /**
     * @throws
     * @Title:updateFopNotice
     * @Description: TODO(更新通知公告)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-03
     */
    @Override
    public MessageResponse updateFopNotice(FopNotice o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getCoverUrl())) {
            return new MessageResponse(1, "封面地址不能为空！");
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
        this.fopNoticeDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更通知公告", "通知公告", "",
                o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更通知公告完成！");
    }

    /**
     * @throws
     * @Title:selectFopNoticeByPrimaryKey
     * @Description: TODO(获取通知公告)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopNotice>
     * @author: Arvin
     * @version: 2018-05-03
     */
    @Override
    public SingleResult<FopNoticeVo> selectFopNoticeByPrimaryKey(String id) throws Exception {
        SingleResult
                <FopNoticeVo> rst = new SingleResult
                <FopNoticeVo>();
        rst.setValue(this.fopNoticeDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopNoticeByFopNoticeId
     * @Description: TODO(删除通知公告)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-03
     */
    @Override
    public MessageResponse deleteFopNoticeByFopNoticeId(String id,
                                                        UserProp userProp) throws Exception {
        this.fopNoticeDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除通知公告", "通知公告",
                String.valueOf(id),
                String.valueOf(id), "通知公告", userProp);
        return new MessageResponse(0, "通知公告删除完成！");
    }
}

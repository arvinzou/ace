package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.dao.FopLawPaperDao;
import com.huacainfo.ace.fop.model.FopLawPaper;
import com.huacainfo.ace.fop.service.FopLawPaperService;
import com.huacainfo.ace.fop.vo.FopLawPaperQVo;
import com.huacainfo.ace.fop.vo.FopLawPaperVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("fopLawPaperService")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(法律文书)
 */
public class FopLawPaperServiceImpl implements FopLawPaperService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopLawPaperDao fopLawPaperDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(法律文书分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopLawPaperVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public PageResult<FopLawPaperVo> findFopLawPaperList(FopLawPaperQVo condition, int start,
                                                         int limit, String orderBy) throws Exception {
        PageResult<FopLawPaperVo> rst = new PageResult<FopLawPaperVo>();
        List<FopLawPaperVo> list = this.fopLawPaperDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopLawPaperDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopLawPaper
     * @Description: TODO(添加法律文书)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse insertFopLawPaper(FopLawPaper o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "文书标题不能为空！");
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

        int temp = this.fopLawPaperDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "法律文书名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopLawPaperDao.insertSelective(o);
        this.dataBaseLogService.log("添加法律文书", "法律文书", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "添加法律文书完成！");
    }

    /**
     * @throws
     * @Title:updateFopLawPaper
     * @Description: TODO(更新法律文书)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse updateFopLawPaper(FopLawPaper o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "文书标题不能为空！");
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
        this.fopLawPaperDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更法律文书", "法律文书", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更法律文书完成！");
    }

    /**
     * @throws
     * @Title:selectFopLawPaperByPrimaryKey
     * @Description: TODO(获取法律文书)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopLawPaper>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public SingleResult<FopLawPaperVo> selectFopLawPaperByPrimaryKey(String id) throws Exception {
        SingleResult<FopLawPaperVo> rst = new SingleResult<FopLawPaperVo>();
        rst.setValue(this.fopLawPaperDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopLawPaperByFopLawPaperId
     * @Description: TODO(删除法律文书)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse deleteFopLawPaperByFopLawPaperId(String id,
                                                            UserProp userProp) throws Exception {
        this.fopLawPaperDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除法律文书", "法律文书", String.valueOf(id),
                String.valueOf(id), "法律文书", userProp);
        return new MessageResponse(0, "法律文书删除完成！");
    }
}

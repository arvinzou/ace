package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.dao.FopVisitDao;
import com.huacainfo.ace.fop.model.FopVisit;
import com.huacainfo.ace.fop.service.FopVisitService;
import com.huacainfo.ace.fop.vo.FopVisitQVo;
import com.huacainfo.ace.fop.vo.FopVisitVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("fopVisitService")
/**
 * @author: 陈晓克
 * @version: 2018-11-02
 * @Description: TODO(企业走访)
 */
public class FopVisitServiceImpl implements FopVisitService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopVisitDao fopVisitDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业走访分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FopVisitVo>
     * @author: 陈晓克
     * @version: 2018-11-02
     */
    @Override
    public PageResult
            <FopVisitVo> findFopVisitList(FopVisitQVo condition, int start,
                                          int limit, String orderBy) throws Exception {
        PageResult
                <FopVisitVo> rst = new PageResult<>();
        List
                <FopVisitVo> list = this.fopVisitDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopVisitDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopVisit
     * @Description: TODO(添加企业走访)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-02
     */
    @Override
    public MessageResponse insertFopVisit(FopVisit o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCompanyId())) {
            return new MessageResponse(1, "企业ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "活动标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getVisitDate())) {
            return new MessageResponse(1, "走访时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "走访内容不能为空！");
        }
        int temp = this.fopVisitDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "企业走访名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopVisitDao.insert(o);
        this.dataBaseLogService.log("添加企业走访", "企业走访", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "成功！");
    }

    /**
     * @throws
     * @Title:updateFopVisit
     * @Description: TODO(更新企业走访)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-02
     */
    @Override
    public MessageResponse updateFopVisit(FopVisit o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "活动标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getVisitDate())) {
            return new MessageResponse(1, "走访时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "走访内容不能为空！");
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.fopVisitDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更企业走访", "企业走访", "",
                o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "成功！");
    }

    /**
     * @throws
     * @Title:selectFopVisitByPrimaryKey
     * @Description: TODO(获取企业走访)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopVisit>
     * @author: 陈晓克
     * @version: 2018-11-02
     */
    @Override
    public SingleResult
            <FopVisitVo> selectFopVisitByPrimaryKey(String id) throws Exception {
        SingleResult
                <FopVisitVo> rst = new SingleResult<>();
        rst.setValue(this.fopVisitDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopVisitByFopVisitId
     * @Description: TODO(删除企业走访)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-02
     */
    @Override
    public MessageResponse deleteFopVisitByFopVisitId(String id, UserProp userProp) throws
            Exception {
        this.fopVisitDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除企业走访", "企业走访", id, id,
                "企业走访", userProp);
        return new MessageResponse(0, "企业走访删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核企业走访)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-11-02
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {

        return new MessageResponse(0, "企业走访审核完成！");
    }

}

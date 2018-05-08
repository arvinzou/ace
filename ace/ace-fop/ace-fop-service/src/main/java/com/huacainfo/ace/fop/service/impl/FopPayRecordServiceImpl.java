package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.dao.FopPayRecordDao;
import com.huacainfo.ace.fop.model.FopPayRecord;
import com.huacainfo.ace.fop.service.FopPayRecordService;
import com.huacainfo.ace.fop.vo.FopPayRecordQVo;
import com.huacainfo.ace.fop.vo.FopPayRecordVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("fopPayRecordService")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: (缴费记录)
 */
public class FopPayRecordServiceImpl implements FopPayRecordService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopPayRecordDao fopPayRecordDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: (缴费记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopPayRecordVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public PageResult<FopPayRecordVo> findFopPayRecordList(FopPayRecordQVo condition, int start,
                                                           int limit, String orderBy) throws Exception {
        PageResult<FopPayRecordVo> rst = new PageResult<FopPayRecordVo>();
        List<FopPayRecordVo> list = this.fopPayRecordDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopPayRecordDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopPayRecord
     * @Description: (添加缴费记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse insertFopPayRecord(FopPayRecord o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getRelationId())) {
            return new MessageResponse(1, "关联ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationType())) {
            return new MessageResponse(1, "关联类型不能为空！");
        }


        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopPayRecordDao.insertSelective(o);
        this.dataBaseLogService.log("添加缴费记录", "缴费记录", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "添加缴费记录完成！");
    }

    /**
     * @throws
     * @Title:updateFopPayRecord
     * @Description: (更新缴费记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse updateFopPayRecord(FopPayRecord o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationId())) {
            return new MessageResponse(1, "关联ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationType())) {
            return new MessageResponse(1, "关联类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayYear())) {
            return new MessageResponse(1, "缴费年度不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayCategory())) {
            return new MessageResponse(1, "缴费项目不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayLevel())) {
            return new MessageResponse(1, "缴费级别不能为空！");
        }
        if (CommonUtils.isBlank(o.getPayAmount())) {
            return new MessageResponse(1, "缴纳金额不能为空！");
        }
        if (CommonUtils.isBlank(o.getDendline())) {
            return new MessageResponse(1, "有效截止日期不能为空！");
        }

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.fopPayRecordDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更缴费记录", "缴费记录", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更缴费记录完成！");
    }

    /**
     * @throws
     * @Title:selectFopPayRecordByPrimaryKey
     * @Description: (获取缴费记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopPayRecord>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public SingleResult<FopPayRecordVo> selectFopPayRecordByPrimaryKey(String id) throws Exception {
        SingleResult<FopPayRecordVo> rst = new SingleResult<FopPayRecordVo>();
        rst.setValue(this.fopPayRecordDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopPayRecordByFopPayRecordId
     * @Description: (删除缴费记录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse deleteFopPayRecordByFopPayRecordId(String id,
                                                              UserProp userProp) throws Exception {
        this.fopPayRecordDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除缴费记录", "缴费记录", String.valueOf(id),
                String.valueOf(id), "缴费记录", userProp);
        return new MessageResponse(0, "缴费记录删除完成！");
    }


    /**
     * 功能描述: 添加缴费记录
     *
     * @param record   支付流水
     * @param userProp 操作员
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/7 16:18
     */
    @Override
    public MessageResponse submitPayRecord(FopPayRecord record, UserProp userProp) throws Exception {

        return insertFopPayRecord(record, userProp);
    }
}

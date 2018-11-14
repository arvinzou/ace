package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.WithdrawWxLogDao;
import com.huacainfo.ace.jxb.model.WithdrawWxLog;
import com.huacainfo.ace.jxb.service.WithdrawWxLogService;
import com.huacainfo.ace.jxb.vo.WithdrawWxLogQVo;
import com.huacainfo.ace.jxb.vo.WithdrawWxLogVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("withdrawWxLogService")
/**
 * @author: Arvin
 * @version: 2018-11-14
 * @Description: TODO(企业支付接口日志)
 */
public class WithdrawWxLogServiceImpl implements WithdrawWxLogService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WithdrawWxLogDao withdrawWxLogDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业支付接口日志分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <WithdrawWxLogVo>
     * @author: Arvin
     * @version: 2018-11-14
     */
    @Override
    public PageResult<WithdrawWxLogVo> findWithdrawWxLogList(WithdrawWxLogQVo condition, int start,
                                                             int limit, String orderBy) throws Exception {
        PageResult<WithdrawWxLogVo> rst = new PageResult<>();
        List<WithdrawWxLogVo> list = this.withdrawWxLogDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.withdrawWxLogDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertWithdrawWxLog
     * @Description: TODO(添加企业支付接口日志)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-14
     */
    @Override
    public MessageResponse insertWithdrawWxLog(WithdrawWxLog o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getRecordId())) {
            return new MessageResponse(1, "提现记录ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        int temp = this.withdrawWxLogDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "企业支付接口日志名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        this.withdrawWxLogDao.insert(o);
        this.dataBaseLogService.log("添加企业支付接口日志", "企业支付接口日志", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加企业支付接口日志完成！");
    }

    /**
     * @throws
     * @Title:updateWithdrawWxLog
     * @Description: TODO(更新企业支付接口日志)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-14
     */
    @Override
    public MessageResponse updateWithdrawWxLog(WithdrawWxLog o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getRecordId())) {
            return new MessageResponse(1, "提现记录ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        this.withdrawWxLogDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更企业支付接口日志", "企业支付接口日志", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更企业支付接口日志完成！");
    }

    /**
     * @throws
     * @Title:selectWithdrawWxLogByPrimaryKey
     * @Description: TODO(获取企业支付接口日志)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<WithdrawWxLog>
     * @author: Arvin
     * @version: 2018-11-14
     */
    @Override
    public SingleResult<WithdrawWxLogVo> selectWithdrawWxLogByPrimaryKey(String id) throws Exception {
        SingleResult<WithdrawWxLogVo> rst = new SingleResult<>();
        rst.setValue(this.withdrawWxLogDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @Description: TODO(删除企业支付接口日志)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-11-14
     */
    @Override
    public MessageResponse deleteWithdrawWxLogByWithdrawWxLogId(String id, UserProp userProp) throws Exception {

        withdrawWxLogDao.deleteByPrimaryKey(id);
        dataBaseLogService.log("删除企业支付接口日志", "企业支付接口日志", id, id,
                "企业支付接口日志", userProp);
        return new MessageResponse(0, "企业支付接口日志删除完成！");
    }


}

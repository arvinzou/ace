package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.AccountFlowRecordDao;
import com.huacainfo.ace.jxb.model.AccountFlowRecord;
import com.huacainfo.ace.jxb.service.AccountFlowRecordService;
import com.huacainfo.ace.jxb.vo.AccountFlowRecordQVo;
import com.huacainfo.ace.jxb.vo.AccountFlowRecordVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service("accountFlowRecordService")
/**
 * @author: Arvin
 * @version: 2018-08-07
 * @Description: TODO(账户资金流水)
 */
public class AccountFlowRecordServiceImpl implements AccountFlowRecordService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AccountFlowRecordDao accountFlowRecordDao;

    /**
     * @Description: 账户资金流水分页查询
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<AccountFlowRecordVo>
     * @author: Arvin
     * @version: 2018-08-07
     */
    @Override
    public PageResult<AccountFlowRecordVo> findList(AccountFlowRecordQVo condition, int start,
                                                    int limit, String orderBy) throws Exception {
        PageResult<AccountFlowRecordVo> rst = new PageResult<>();
        List<AccountFlowRecordVo> list = accountFlowRecordDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.accountFlowRecordDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * 添加账户资金流水
     *
     * @param userId   用户ID
     * @param userType 用户类型
     * @param bisType  业务类型
     * @param amount   金额数值
     * @param srcId    源头单据id
     * @param srcType  源头单据类型
     * @return int
     * @throws Exception
     */
    @Override
    public int insertRecord(String userId, String userType, String bisType,
                            BigDecimal amount, String srcId, String srcType) throws Exception {
        AccountFlowRecord o = new AccountFlowRecord();
        o.setUserId(userId);
        o.setUserType(userType);
        o.setBisType(bisType);
        o.setAmount(amount);
        o.setSrcId(srcId);
        o.setSrcType(srcType);

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        return accountFlowRecordDao.insertSelective(o);
    }
}

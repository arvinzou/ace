package com.huacainfo.ace.society.service.impl;


import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.society.constant.BisType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.society.dao.CoinConfigDao;
import com.huacainfo.ace.society.model.CoinConfig;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.service.CoinConfigService;
import com.huacainfo.ace.society.vo.CoinConfigVo;
import com.huacainfo.ace.society.vo.CoinConfigQVo;

@Service("coinConfigService")
/**
 * @author: huacai003
 * @version: 2018-09-17
 * @Description: TODO(爱心币配置)
 */
public class CoinConfigServiceImpl implements CoinConfigService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CoinConfigDao coinConfigDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private AuditRecordService auditRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(爱心币配置分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CoinConfigVo>
     * @author: huacai003
     * @version: 2018-09-17
     */
    @Override
    public PageResult
            <CoinConfigVo> findCoinConfigList(CoinConfigQVo condition, int start,
                                              int limit, String orderBy) throws Exception {
        PageResult
                <CoinConfigVo> rst = new PageResult<>();
        List
                <CoinConfigVo> list = this.coinConfigDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.coinConfigDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCoinConfig
     * @Description: TODO(添加爱心币配置)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-17
     */
    @Override
    public MessageResponse insertCoinConfig(CoinConfig o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键-GUID不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "类型");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "配置名称不能为空");
        }
        int temp = this.coinConfigDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "爱心币配置名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.coinConfigDao.insert(o);
        this.dataBaseLogService.log("添加爱心币配置", "爱心币配置", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加爱心币配置完成！");
    }

    /**
     * @throws
     * @Title:updateCoinConfig
     * @Description: TODO(更新爱心币配置)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-17
     */
    @Override
    public MessageResponse updateCoinConfig(CoinConfig o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键-GUID不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "类型不能为空！");
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.coinConfigDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更爱心币配置", "爱心币配置", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更爱心币配置完成！");
    }

    @Override
    public MessageResponse softDel(String id, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(id)) {
            return new MessageResponse(1, "主键-GUID不能为空！");
        }
        this.coinConfigDao.softDel(id);
        this.dataBaseLogService.log("变更爱心币配置", "爱心币配置", "",
                id, id, userProp);

        return new MessageResponse(0, "变更爱心币配置完成！");
    }

    /**
     * @throws
     * @Title:selectCoinConfigByPrimaryKey
     * @Description: TODO(获取爱心币配置)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CoinConfig>
     * @author: huacai003
     * @version: 2018-09-17
     */
    @Override
    public SingleResult
            <CoinConfigVo> selectCoinConfigByPrimaryKey(String id) throws Exception {
        SingleResult
                <CoinConfigVo> rst = new SingleResult<>();
        rst.setValue(this.coinConfigDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCoinConfigByCoinConfigId
     * @Description: TODO(删除爱心币配置)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-17
     */
    @Override
    public MessageResponse deleteCoinConfigByCoinConfigId(String id, UserProp userProp) throws
            Exception {
        this.coinConfigDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除爱心币配置", "爱心币配置", id, id,
                "爱心币配置", userProp);
        return new MessageResponse(0, "爱心币配置删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核爱心币配置)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-09-17
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {

        CoinConfig obj = coinConfigDao.selectByPrimaryKey(id);
        if (obj == null) {
            return new MessageResponse(ResultCode.FAIL, "爱心币配置数据丢失");
        }

        //更改审核记录
        MessageResponse auditRs =
                auditRecordService.audit(BisType.SOCIETY_ORG_INFO, obj.getId(), obj.getId(), rst, remark,
                        userProp);
        if (ResultCode.FAIL == auditRs.getStatus()) {
            return auditRs;
        }

        obj.setStatus(rst);
        obj.setLastModifyDate(DateUtil.getNowDate());
        obj.setLastModifyUserId(userProp.getUserId());
        obj.setLastModifyUserName(userProp.getName());
        coinConfigDao.updateStatus(obj);


        dataBaseLogService.log("审核爱心币配置", "爱心币配置", id, id,
                "爱心币配置", userProp);
        return new MessageResponse(0, "爱心币配置审核完成！");
    }

}

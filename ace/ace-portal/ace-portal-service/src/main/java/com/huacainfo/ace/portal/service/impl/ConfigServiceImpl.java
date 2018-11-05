package com.huacainfo.ace.portal.service.impl;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.dao.ConfigMapper;
import com.huacainfo.ace.portal.model.Config;
import com.huacainfo.ace.portal.service.ConfigService;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.vo.ConfigQVo;
import com.huacainfo.ace.portal.vo.ConfigVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ConfigMapper configMapper;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    public PageResult<ConfigVo> findConfigList(ConfigQVo condition, int start,
                                               int limit, String orderBy) throws Exception {
        PageResult<ConfigVo> rst = new PageResult<ConfigVo>();
        List<ConfigVo> list = this.configMapper.findList(condition, start,
                start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.configMapper.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    public MessageResponse insertConfig(Config o, UserProp userProp)
            throws Exception {
        o.setCreateTime(new Date());
        o.setId(UUID.randomUUID().toString());
        o.setDeptId(userProp.getCorpId());
        if (CommonUtils.isBlank(o.getConfigKey())) {
            return new MessageResponse(1, "Key不能为空！");
        }
        if (CommonUtils.isBlank(o.getConfigName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getConfigValue())) {
            return new MessageResponse(1, "系统参数值不能为空！");
        }

        int temp = this.configMapper.isExitByName(o.getConfigName());
        if (temp > 0) {
            return new MessageResponse(1, "已存在此名称的数据！");
        }
        this.configMapper.insert(o);
        this.dataBaseLogService.log("添加系统参数", "系统参数", "", o.getConfigName(),
                o.getConfigName(), userProp);
        return new MessageResponse(0, "添加系统参数完成！");
    }

    public MessageResponse updateConfig(Config o, UserProp userProp)
            throws Exception {
        o.setCreateTime(new Date());
        o.setDeptId(userProp.getCorpId());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "id不能为空！");
        }
        if (CommonUtils.isBlank(o.getConfigKey())) {
            return new MessageResponse(1, "Key不能为空！");
        }
        if (CommonUtils.isBlank(o.getConfigName())) {
            return new MessageResponse(1, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getConfigValue())) {
            return new MessageResponse(1, "系统参数值不能为空！");
        }
        this.configMapper.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更系统参数", "系统参数", "", o.getConfigName(),
                o.getConfigName(), userProp);
        return new MessageResponse(0, "变更系统参数完成！");
    }

    public SingleResult<Config> selectConfigByPrimaryKey(String id)
            throws Exception {
        SingleResult<Config> rst = new SingleResult<Config>();
        rst.setValue(this.configMapper.selectByPrimaryKey(id));
        return rst;
    }

    public MessageResponse deleteConfigByConfigId(String id,
                                                  UserProp userProp) throws Exception {
        MessageResponse rst = new MessageResponse();
        this.configMapper.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除系统参数", "系统参数", String.valueOf(id), String.valueOf(id),
                "系统参数", userProp);
        return rst;
    }

    public MessageResponse insertDeptConfig(String deptId) {
        MessageResponse rst = new MessageResponse();
        if (CommonUtils.isBlank(deptId)) {
            return new MessageResponse(1, "deptId不能为空！");
        }
        List<Config> list = this.configMapper.selectBasicConfigListByCategory("02");
        for (Config o : list) {
            o.setDeptId(deptId);
            o.setId(UUID.randomUUID().toString());
            this.configMapper.insert(o);
        }
        return rst;
    }

    /**
     * 根据key查找配置信息
     *
     * @param syId   系统标识
     * @param cfgKey key
     * @return ConfigVo
     */
    @Override
    public ConfigVo findByKey(String syId, String cfgKey) {
        return configMapper.findByKey(syId, cfgKey);
    }

}

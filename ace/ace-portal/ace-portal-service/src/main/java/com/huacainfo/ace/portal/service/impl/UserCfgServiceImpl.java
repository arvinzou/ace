package com.huacainfo.ace.portal.service.impl;


import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.portal.dao.UserCfgDao;
import com.huacainfo.ace.portal.model.UserCfg;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.UserCfgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("userCfgService")
public class UserCfgServiceImpl implements UserCfgService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserCfgDao userCfgDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     *
     * @param userProp
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectUserCfgByUserId(UserProp userProp) throws Exception {
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, Object>> list = this.userCfgDao.selectUserCfgByUserId(userProp.getUserId());
        rst.put("total", list.size());
        for (Map<String, Object> o : list) {
            o.put("editor", JSON.parseObject((String) o.get("editor"), Object.class));
        }
        rst.put("rows", list);
        return rst;
    }

    public MessageResponse saveOrUpdateUserCfg(List<UserCfg> list, UserProp userProp) throws Exception {
        for (UserCfg o : list) {
            o.setUserId(userProp.getUserId());
            int temp = this.userCfgDao.isExit(o);
            if (temp > 0) {
                this.userCfgDao.updateByPrimaryKey(o);
            } else {
                o.setId(UUID.randomUUID().toString());

                this.userCfgDao.insert(o);
            }
            this.dataBaseLogService.log("变更个性化配置", "个性化配置", "", o.getCfgKey(),
                    o.getCfgKey(), userProp);
        }
        return new MessageResponse(0, "变更个性化配置完成！");
    }
}

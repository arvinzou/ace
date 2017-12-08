package com.huacainfo.ace.rvc.dao;

import com.huacainfo.ace.rvc.model.RvcBaseUser;
import org.springframework.stereotype.Component;

@Component
public interface RvcBaseUserDao {
    int deleteByPrimaryKey(String id);

    int insert(RvcBaseUser record);

    int insertSelective(RvcBaseUser record);

    RvcBaseUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RvcBaseUser record);

    int updateByPrimaryKey(RvcBaseUser record);

    /**
     * 根据浪潮 userId 获取用户信息
     *
     * @param userId 浪潮 userId
     * @return RvcBaseUser
     */
    RvcBaseUser getByUserId(String userId);
}
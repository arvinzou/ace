package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.Resources;
import com.huacainfo.ace.portal.model.SyCfg;
import com.huacainfo.ace.portal.model.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SysInitDao
 * @Description TODO
 * @Author HuaCai008
 * @Date 2019/3/5 9:49
 */
public interface SysInitDao {

    int insertSyCfg(SyCfg syCfg);

    List<Resources> findResList();

    int insertUsers(@Param("user") Users o,
                    @Param("roleId") String roleId);
}

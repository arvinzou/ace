package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.portal.model.Users;
import org.apache.ibatis.annotations.Param;

/**
 * Created by chenxiaoke on 2018/7/12.
 */
public interface RegDao {

    int insertReg(Users record);

    int isExitByTel(@Param("tel")String tel);


}

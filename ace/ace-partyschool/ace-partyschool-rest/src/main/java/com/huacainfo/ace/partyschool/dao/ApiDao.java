package com.huacainfo.ace.partyschool.dao;


import java.util.List;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2019/1/3 10:11
 * @Description:
 */
public interface ApiDao {

    List<Map<String, Object>> findCustomerList();
}

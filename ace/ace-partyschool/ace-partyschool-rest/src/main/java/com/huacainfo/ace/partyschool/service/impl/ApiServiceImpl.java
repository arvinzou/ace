package com.huacainfo.ace.partyschool.service.impl;

import com.huacainfo.ace.partyschool.dao.ApiDao;
import com.huacainfo.ace.partyschool.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2019/1/4 11:54
 * @Description:
 */
@Service("/apiService")
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiDao aipDao;


    @Override
    public List<Map<String, Object>> findCustomerList() {
        return aipDao.findCustomerList();
    }
}

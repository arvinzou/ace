package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.OrderComplaint;

public interface OrderComplaintDao {
    int deleteByPrimaryKey(String id);

    int insert(OrderComplaint record);

    OrderComplaint selectByPrimaryKey(String id);

    int updateByPrimaryKey(OrderComplaint record);

    int isExit(OrderComplaint record);
}
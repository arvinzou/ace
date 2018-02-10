package com.huacainfo.ace.rvc.hngdapi.pojo.response;

import com.huacainfo.ace.rvc.hngdapi.pojo.Head;
import com.huacainfo.ace.rvc.hngdapi.pojo.Organization;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * Created by Arvin on 2018/2/6.
 */
public class OrganizationResp {

    private List<Head> head;

    private List<Organization> rows;

    public Head getHead() {
        if (null == head)
            return null;

        return head.get(0);
    }

    public void setHead(List<Head> head) {
        this.head = head;
    }


    public List<Organization> getRows() {
        return rows;
    }

    public void setRows(List<Organization> rows) {
        this.rows = rows;
    }
}

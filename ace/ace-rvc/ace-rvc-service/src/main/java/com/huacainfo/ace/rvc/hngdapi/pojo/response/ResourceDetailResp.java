package com.huacainfo.ace.rvc.hngdapi.pojo.response;

import com.huacainfo.ace.rvc.hngdapi.pojo.Head;
import com.huacainfo.ace.rvc.hngdapi.pojo.ResourceDetail;

import java.util.List;

/**
 * Created by Arvin on 2018/2/6.
 */
public class ResourceDetailResp {
    private List<Head> head;

    private List<ResourceDetail> rows;

    public List<Head> getHead() {
        return head;
    }

    public void setHead(List<Head> head) {
        this.head = head;
    }

    public List<ResourceDetail> getRows() {
        return rows;
    }

    public void setRows(List<ResourceDetail> rows) {
        this.rows = rows;
    }
}

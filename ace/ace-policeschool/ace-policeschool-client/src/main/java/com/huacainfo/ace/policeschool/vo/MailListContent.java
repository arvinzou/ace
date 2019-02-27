package com.huacainfo.ace.policeschool.vo;

import java.util.List;

/**
 * Created by chenxiaoke on 2019/1/12.
 */
public class MailListContent implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private List<Person> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getList() {
        return list;
    }

    public void setList(List<Person> list) {
        this.list = list;
    }
}

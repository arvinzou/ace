package com.huacainfo.ace.portal.model;

import java.util.List;

/**
 * Created by chenxiaoke on 2018/5/7.
 */
public class PageData implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private java.util.List<Article> articles;

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

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}

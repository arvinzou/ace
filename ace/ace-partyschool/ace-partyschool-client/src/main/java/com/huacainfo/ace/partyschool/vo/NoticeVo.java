package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.Notice;
import java.util.List;
import java.util.Map;
public class NoticeVo extends Notice {

    private static final long serialVersionUID = 1L;

    private Integer pushed;

    private Integer viewed;

    private List<Map<String,Object>> users;

    private List<Map<String,Object>> files;

    public Integer getPushed() {
        return pushed;
    }

    public void setPushed(Integer pushed) {
        this.pushed = pushed;
    }

    public Integer getViewed() {
        return viewed;
    }

    public void setViewed(Integer viewed) {
        this.viewed = viewed;
    }

    public List<Map<String, Object>> getUsers() {
        return users;
    }

    public void setUsers(List<Map<String, Object>> users) {
        this.users = users;
    }

    public List<Map<String, Object>> getFiles() {
        return files;
    }

    public void setFiles(List<Map<String, Object>> files) {
        this.files = files;
    }
}

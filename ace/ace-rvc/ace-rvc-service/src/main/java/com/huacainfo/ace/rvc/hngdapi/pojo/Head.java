package com.huacainfo.ace.rvc.hngdapi.pojo;

/**
 * Created by Arvin on 2018/2/6.
 */
public class Head {

//    <head>
//<result result_code="0"message="成功"size="2"/>
//</head>

    private String result_code;

    private String message;

    private String size;

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getResult_code() {
        return this.result_code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return this.size;
    }
}

package com.huacainfo.ace.common.exception;


/**
 * Created by Arvin on 2016-06-24.
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
        this.msg = message;
    }

    private int errCode;
    private int errCode2;
    private String errorCode3;
    private String msg;

    public CustomException(int errCode, String message) {
        super(message);
        this.errCode = errCode;
        this.msg = message;
    }

    public CustomException(int errCode, int errCode2, String errorCode3, String message) {
        super(message);
        this.errCode = errCode;
        this.errCode2 = errCode2;
        this.errorCode3 = errorCode3;
        this.msg = message;
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
        this.msg = message;
    }


    public String getMsg() {
        if (errCode == 0)
            return msg;
        else if (errCode2 == 0) {
            return errCode + ":" + msg;
        }
        return errCode + "-" + errCode2 + "-" + errorCode3 + ":" + msg;
    }
}
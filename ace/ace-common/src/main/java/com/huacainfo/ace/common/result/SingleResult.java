package com.huacainfo.ace.common.result;

public class SingleResult<T> extends MessageResponse {
    private static final long serialVersionUID = 1L;

    public SingleResult(int status, String message) {
        super(status, message);
    }

    public SingleResult(int status, String message, String errorStack) {
        super(status, message, errorStack);
    }

    public SingleResult(T t) {
        super();
        value = t;
    }

    public SingleResult() {
        super();
    }

    /**
     * 其他结果
     */
    private T value;

    /**
     * 其他结果
     */
    public T getValue() {
        return value;
    }

    /**
     * 其他结果
     */
    public void setValue(T value) {
        this.value = value;
    }

}

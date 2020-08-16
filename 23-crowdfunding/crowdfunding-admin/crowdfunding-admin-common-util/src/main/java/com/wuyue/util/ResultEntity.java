package com.wuyue.util;

/**
 * @author DeltaV235
 * @version 1.0
 * @className ResultEntity
 * @description 统一整个项目中AJAX返回的结果,未来也可以用于分布式架构各个模块间调用时返回的统一类型
 * @date 2020/5/6 15:31
 */
public class ResultEntity<T> {
    private String result;
    private String message;
    private T data;

    private static final String SUCCESS = "SUCCESS";
    private static final String FAILED = "FAILED";

    public static <E> ResultEntity<E> successWithoutData() {
        return new ResultEntity<>(SUCCESS, null, null);
    }

    public static <E> ResultEntity<E> successWithData(E data) {
        return new ResultEntity<>(SUCCESS, null, data);
    }

    public static <E> ResultEntity<E> failed(String message) {
        return new ResultEntity<>(FAILED, message, null);
    }

    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public ResultEntity() {
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

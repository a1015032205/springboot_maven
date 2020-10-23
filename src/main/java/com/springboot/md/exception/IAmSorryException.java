package com.springboot.md.exception;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-10-23 21:33
 * @Description:
 */

public class IAmSorryException extends RuntimeException {

    public static IAmSorryException create(int code) {
        throw new IAmSorryException(code);
    }

    public static IAmSorryException create(String message) {
        throw new IAmSorryException(message);
    }

    public static IAmSorryException create(int code, String message) {
        throw new IAmSorryException(code, message);
    }

    public static IAmSorryException create(int code, String message, Exception exception) {
        throw new IAmSorryException(code, message, exception);
    }

    public static IAmSorryException create(int code, String message, Object data) {
        throw new IAmSorryException(code, message, data);
    }

    private int code = 400;

    private Object data;

    public IAmSorryException() {
        throw IAmSorryException.create("I have nothing");
    }

    private IAmSorryException(int code) {
        this.code = code;
    }

    public IAmSorryException(String message) {
        super(message);
    }

    private IAmSorryException(int code, String message) {
        super(message);
        this.code = code;
    }

    private IAmSorryException(String message, Exception ex) {
        super(message, ex);
    }

    private IAmSorryException(int code, String message, Exception ex) {
        super(message, ex);
        this.code = code;
    }

    private IAmSorryException(int code, String message, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }
}

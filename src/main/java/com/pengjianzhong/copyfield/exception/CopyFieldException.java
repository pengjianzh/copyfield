package com.pengjianzhong.copyfield.exception;

/**
 * CopyField异常
 *
 * @author pengjianzhong
 * @date 2021/1/6 11:01
 */
public class CopyFieldException extends Exception {

    public CopyFieldException() {
        super();
    }

    public CopyFieldException(String msg) {
        super(msg);
    }

    public CopyFieldException(Throwable throwable) {
        super(throwable);
    }

    public CopyFieldException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

}
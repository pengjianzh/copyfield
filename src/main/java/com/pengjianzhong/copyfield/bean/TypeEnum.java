package com.pengjianzhong.copyfield.bean;

/**
 * @author pengjianzhong
 * @date 2021/1/11 17:07
 **/
public enum TypeEnum {


    /**
     * 基于源对象
     */
    SOURCE("1", "源对象"),

    /**
     * 基于目标对象
     */
    TARGET("2", "目标对象");

    /**
     * code
     */
    private final String code;

    /**
     * 描述
     */
    private final String msg;

     TypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

}

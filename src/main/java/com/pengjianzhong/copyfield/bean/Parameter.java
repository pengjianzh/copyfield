package com.pengjianzhong.copyfield.bean;

import com.pengjianzhong.copyfield.convert.Convert;

/**
 * 转换参数
 *
 * @author pengjianzhong
 * @date 2021/1/11 16:48
 */
public class Parameter {

    /**
     * 源属性名
     */
    private String originName;

    /**
     * 目标属性名
     */
    private String targetname;

    /**
     * 转换器对象
     */
    private Convert convert;

    public Parameter(String originName, String targetname, Convert convert) {
        this.originName = originName;
        this.targetname = targetname;
        this.convert = convert;
    }

    public Parameter(String originName, String targetname) {
        this.originName = originName;
        this.targetname = targetname;
    }

    public Parameter() {
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getTargetname() {
        return targetname;
    }

    public void setTargetname(String targetname) {
        this.targetname = targetname;
    }

    public Convert getConvert() {
        return convert;
    }

    public void setConvert(Convert convert) {
        this.convert = convert;
    }
}
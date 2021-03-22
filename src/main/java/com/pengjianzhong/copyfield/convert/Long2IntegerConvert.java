package com.pengjianzhong.copyfield.convert;

/**
 * @author pengjianzhong
 * @date 2021/1/6 14:07
 */
public class Long2IntegerConvert implements Convert {

    @Override
    public Object convert(Object value) {
        if (null == value) {
            return null;
        }
        if (!(value instanceof Long)) {
            throw new IllegalArgumentException();
        }
        return ((Long) value).intValue();
    }
}
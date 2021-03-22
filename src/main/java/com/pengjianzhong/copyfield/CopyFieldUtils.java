package com.pengjianzhong.copyfield;

import com.pengjianzhong.copyfield.annotation.CopyField;
import com.pengjianzhong.copyfield.bean.Parameter;
import com.pengjianzhong.copyfield.bean.TypeEnum;
import com.pengjianzhong.copyfield.convert.Convert;
import com.pengjianzhong.copyfield.convert.ConvertManager;
import com.pengjianzhong.copyfield.exception.CopyFieldException;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 属性复制
 *
 * @author pengjianzhong
 * @date 2021/1/5 16:16
 **/
public final class CopyFieldUtils {

    private static final String COPY_ERROR = "复制属性出错";


    private CopyFieldUtils() {
    }

    /**
     * 使用源对象的注解进行解析
     *
     * @param source 源对象
     * @param target 目标对象
     * @author pengjianzhong
     * @date 2021/1/6 11:22
     **/
    public static void copy(Object source, Object target, TypeEnum type) throws CopyFieldException {
        List<Parameter> parameters;
        if (TypeEnum.SOURCE.equals(type)) {
            parameters = buildParameters(source, type);
        } else {
            parameters = buildParameters(target, type);
        }
        try {
            for (Parameter parameter : parameters) {
                Object value = getValue(source, parameter.getOriginName());
                setValue(value, target, parameter.getConvert(), parameter.getTargetname());
            }
        } catch (Exception e) {
            throw new CopyFieldException(COPY_ERROR, e);
        }
    }


    /**
     * 获取源对象的属性值
     *
     * @param source     源对象
     * @param sourceName 属性名
     * @return {@link Object}
     * @author pengjianzhong
     * @date 2021/1/6 17:57
     **/
    private static Object getValue(Object source, String sourceName)
            throws InvocationTargetException, IllegalAccessException {
        PropertyDescriptor sourcedescriptor;
        try {
            sourcedescriptor = new PropertyDescriptor(sourceName, source.getClass());
        } catch (IntrospectionException e) {
            return null;
        }
        Method method = sourcedescriptor.getReadMethod();
        if (null == method) {
            return null;
        }
        return method.invoke(source);
    }


    /**
     * 给目标对象设置值
     *
     * @param value           源对象的属性值
     * @param target          模板对象
     * @param targetFieldName 目标对象属性名
     * @author pengjianzhong
     * @date 2021/1/6 17:48
     **/
    private static void setValue(Object value, Object target, Convert convert, String targetFieldName)
            throws InvocationTargetException, IllegalAccessException {
        if (null == value) {
            return;
        }
        PropertyDescriptor targetdescriptor;
        try {
            targetdescriptor = new PropertyDescriptor(targetFieldName, target.getClass());
        } catch (IntrospectionException e) {
            return;
        }
        if (null != convert) {
            value = convert.convert(value);
        }
        Method setMethod = targetdescriptor.getWriteMethod();
        setMethod.invoke(target, value);
    }

    /**
     * 设置目标属性值，这里是向map中put
     *
     * @param key   key
     * @param value 原始value
     * @param map   map
     * @author pengjianzhong
     * @date 2021/1/7 18:01
     **/
    private static void putMap(String key, Object value, Map<String, Object> map, Convert convert) {
        if (null != convert) {
            value = convert.convert(value);
        }
        map.put(key, value);
    }


    /**
     * 获取类的所有属性，包括父类
     *
     * @param fields 属性List
     * @param clazz  类对象
     * @author pengjianzhong
     * @date 2021/1/5 17:02
     **/
    private static void getAllDeclaredFields(List<Field> fields, Class<?> clazz) {
        Field[] fields1 = clazz.getDeclaredFields();
        Collections.addAll(fields, fields1);
        if (clazz.getSuperclass().equals(Object.class)) {
            return;
        }
        getAllDeclaredFields(fields, clazz.getSuperclass());
    }


    /**
     * javabean 转map
     *
     * @param source javabean
     * @param map    map
     * @author pengjianzhong
     * @date 2021/1/7 17:51
     **/
    public static void beanToMap(Object source, Map<String, Object> map) throws CopyFieldException {
        List<Parameter> parameters = buildParameters(source, TypeEnum.SOURCE);
        try {
            for (Parameter parameter : parameters) {
                Object value = getValue(source, parameter.getOriginName());
                putMap(parameter.getTargetname(), value, map, parameter.getConvert());
            }
        } catch (Exception e) {
            throw new CopyFieldException(COPY_ERROR, e);
        }
    }

    /**
     * mapToBean
     *
     * @param map    map
     * @param target javabean
     * @author pengjianzhong
     * @date 2021/1/7 18:27
     **/
    public static void mapToBean(Map<String, Object> map, Object target) throws CopyFieldException {
        List<Parameter> parameters = buildParameters(target, TypeEnum.TARGET);
        try {
            for (Parameter parameter : parameters) {
                Object value = map.get(parameter.getOriginName());
                setValue(value, target, parameter.getConvert(), parameter.getTargetname());
            }
        } catch (Exception e) {
            throw new CopyFieldException(COPY_ERROR, e);
        }
    }

    /**
     * 构造Parameters
     *
     * @param clazz 类对象
     * @param type  类型
     * @return {@link List}
     * @author pengjianzhong
     * @date 2021/1/11 16:58
     **/
    private static List<Parameter> buildParameters(Object clazz, TypeEnum type) {
        List<Parameter> parameters = new ArrayList<>();
        List<Field> fields = new ArrayList<>();
        getAllDeclaredFields(fields, clazz.getClass());
        for (Field field : fields) {
            String filedName = field.getName();
            String anoFiledName;
            Convert convert;
            CopyField copyField = field.getAnnotation(CopyField.class);
            if (null == copyField) {
                parameters.add(new Parameter(filedName, filedName));
                continue;
            }
            convert = ConvertManager.getConvert(copyField.convert());
            anoFiledName = copyField.name();
            if (anoFiledName.length() == 0) {
                anoFiledName = filedName;
            }
            if (TypeEnum.SOURCE.equals(type)) {
                parameters.add(new Parameter(filedName, anoFiledName, convert));
            } else {
                parameters.add(new Parameter(anoFiledName, filedName, convert));
            }
        }
        return parameters;
    }

}

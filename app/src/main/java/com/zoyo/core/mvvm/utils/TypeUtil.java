package com.zoyo.core.mvvm.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TypeUtil {

    /**
     * @param object Class对象 比如Student extend Person<String>,此时student即是object.也可以是Activity/Fragment对象
     * @param index
     * @param <T>
     * @return
     */
    public static <T> T getClassType(Object object, int index) {
        //获取集成的(上层)类
        Type superclass = object.getClass().getGenericSuperclass();
        //ParameterizedType参数化类型,暂时理解为类上是否有泛型
        if (superclass instanceof ParameterizedType) {
            //获取的即是泛型<Class>中的Class类型,index指定泛型<String,String..>第几个Type
            return (T) ((ParameterizedType) superclass).getActualTypeArguments()[index];
        }
        return null;
    }


    /**
     * 获取泛型实例
     *
     * @param object
     * @param index
     * @param <T>
     * @return
     */
    public static <T> T getClassInstance(Object object, int index) {
        //获取集成的(上层)类
        Type superclass = object.getClass().getGenericSuperclass();
        //ParameterizedType参数化类型,暂时理解为类上是否有泛型
        if (superclass instanceof ParameterizedType) {
            //获取的即是泛型<Class>中的Class类型,index指定泛型<String,String..>第几个Type
            try {
                return ((Class<T>) ((ParameterizedType) superclass).getActualTypeArguments()[index]).newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取ViewModel的Class
     *
     * @return
     */
    public static Class getClassType(Object object, int index, Class clazz) {
        Class modelClass;
        //获取集成的(上层)类
        Type superclass = object.getClass().getGenericSuperclass();
        //ParameterizedType参数化类型,暂时理解为类上是否有泛型
        if (superclass instanceof ParameterizedType) {
            //获取的即是泛型<Class>中的Class类型,index指定泛型<String,String..>第几个Type
            modelClass = (Class) ((ParameterizedType) superclass).getActualTypeArguments()[index];
        } else {
            modelClass = clazz;
        }
        return modelClass;
    }
}

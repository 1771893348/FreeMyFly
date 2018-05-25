/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 反射工具类
 * Created by wgw on 2018/5/25.
 */

public class ReflectUtil {
    /**
     * 通过类路径获取Class
     *
     * @author wgw <br>
     *         Description <br>
     *         LastModified 2014-5-9 Content <br>
     */
    public static Class<?> getClass(String packagePath) throws ClassNotFoundException {
        return Class.forName(packagePath);
    }

    /**
     * 获取的Activity的Class
     *
     * @author wgw <br>
     *         Description packageRoot：模块跟路径<br>
     *         activityPath：Activity完整路径或 模块下Activity路径 LastModified 2014-5-9
     *         Content <br>
     */
    public static Class<?> getModelClass(String packageRoot, String activityPath) throws ClassNotFoundException {
        if (activityPath.indexOf(".") == 0) {
            activityPath = packageRoot + activityPath;
        }
        return getClass(activityPath);
    }

    /**
     *
     * @author wgw <br>
     *         Description 模拟get方法<br>
     *         LastModified 2013-12-9 Content <br>
     *         *@param obj 操作的对象<br>
     *         *@param att 操作的属性<br>
     * @param <T>
     */
    public static <T> T getter(Object obj, String att) {
        T t = null;
        Field field;
        try {
            field = obj.getClass().getDeclaredField(att);
            field.setAccessible(true);
            t = (T) field.get(obj);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     *
     * @author wgw <br>
     *         Description 通过属性名，添加属性<br>
     *         LastModified 2014-2-13 Content <br>
     */
    public static void setter(Object root, String name, Object value) {

        Field field;
        try {
            field = root.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(root, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @author wgw <br>
     *         Description 获取实体属性名称列表<br>
     *         LastModified 2013-12-9 Content <br>
     */
    public static List<String> getFieldList(Object object) {
        List<String> fieldHashList = new ArrayList<String>();
        Class<?> cls = object.getClass();
        Field[] fieldlist = cls.getDeclaredFields();
        for (int i = 0; i < fieldlist.length; i++) {
            Field fld = fieldlist[i];
            fieldHashList.add(fld.getName());
        }
        return fieldHashList;
    }

    /**
     *
     * @author wgw <br>
     *         Description 获取实体属性名称和类别键值对<br>
     *         LastModified 2013-12-9 Content <br>
     */
    public static HashMap<String, Class<?>> getFieldMap(Object object) {
        HashMap<String, Class<?>> fieldHashMap = new HashMap<String, Class<?>>();
        Class<?> cls = object.getClass();
        Field[] fieldlist = cls.getDeclaredFields();
        for (int i = 0; i < fieldlist.length; i++) {
            Field fld = fieldlist[i];
            fieldHashMap.put(fld.getName(), fld.getType());
        }
        return fieldHashMap;
    }

    /**
     * @author wgw <br>
     *
     *         Description 获取一个新的实例<br>
     *         LastModified 2013-12-10 Content <br>
     */
    public static Object getNewBean(Object object) {
        try {
            object = object.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;

    }
}
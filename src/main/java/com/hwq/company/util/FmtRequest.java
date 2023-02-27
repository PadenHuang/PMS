package com.hwq.company.util;


import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * //请求处理工具类
 */
public class FmtRequest {

    /**
     * 根据请求的参数情况反射得到实体类的对象
     * (请求的参数名与实体类的属性一致)
     * 一个参数名对应一个属性值
     * @param req
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseModel(HttpServletRequest req,Class<T> clazz){//解析请求过程数据，获得实体类
        T obj=null;
        try {
            obj = clazz.newInstance();//反射得到对象obj
        }catch (InstantiationException|IllegalAccessException e) {
            e.printStackTrace();
            return null;//异常结束反射
        }
        Map<String, String[]> map = req.getParameterMap();//请求中所有参数构成的集合
        for (Map.Entry<String,String[]>entry : map.entrySet()) {//遍历得到每个参数的参数名和参数值
            String name = entry.getKey();//参数名
            if (name.equals("action")) //跳过action
                continue;
            try {
                Field field= clazz.getDeclaredField(name);//得到参数名name对应的实体类的属性
                field.setAccessible(true);//设置可以进行访问
                field.set(obj,entry.getValue()[0]);//obj对象赋值(一对一)
            } catch (NoSuchFieldException|SecurityException|IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return obj;//返回obj
    }

    /**
     * 根据映射关系反射得到实体类的对象
     * @param req
     * @param clazz
     * @param fields key=属性名 value=参数名
     * @param <T>
     * @return
     */
    public static <T> T parseModel(HttpServletRequest req,Class<T> clazz,Map< String, String> fields){
        T obj=null;
        try {
            obj = clazz.newInstance();//反射得到对象obj
        }catch (InstantiationException|IllegalAccessException e) {
            e.printStackTrace();
            return null;//异常结束反射
        }
        for (Map.Entry<String,String>entry : fields.entrySet()) {//遍历得到实体类的属性名和参数名
            String name = entry.getKey();//实体类的属性名
            String value = req.getParameter(entry.getValue());//参数名
            try {
                Field field= clazz.getDeclaredField(name);//反射出实体类的属性名
                field.setAccessible(true);//设置可以进行访问
                field.set(obj,value);//obj对象赋值
            } catch (NoSuchFieldException|SecurityException|IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return obj;//返回obj
    }

    public static void write(Writer wr,Object val){
        if (val instanceof Collection<?>) {//json转换线性集合
            write(wr,new JSONArray((Collection<?>) val).toString());
        }else if (val instanceof String) {//json转换字符串
            write(wr,val.toString());
        }else if (val instanceof Map< ? , ? >) {//json转换map集合
            write(wr,new JSONObject((Map< ? , ? >) val).toString());
        }else {//json转换自定义类型
            write(wr,new JSONObject(val).toString());
//            System.out.println(val);
        }
    }

    public static void write(Writer wr,String val){//写回val
        try {
//            System.out.println(wr);
//            System.out.println(val);
            wr.write(val);
            wr.flush();
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        wr = null;
    }
}

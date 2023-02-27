package com.hwq.company.util;

import java.util.Collection;
import java.util.Map;

/**
 * //工具类
 * //判空
 */
public final class FmtEmpty {

    public  static  boolean isEmpty(String str){
        return str==null||str.trim().isEmpty()?true:false;
    }

    public  static  boolean isEmpty(Object str){
        return str==null||isEmpty(str.toString())?true:false;
    }

    public  static  boolean isEmpty(Collection <?>list){
        return list==null||list.isEmpty()?true:false;
    }

    public  static  boolean isEmpty(Map<?,?> map){
        return map==null||map.isEmpty()?true:false;
    }

    public  static  boolean isEmpty(Object[] array){
        return array==null||array.length == 0 ?true:false;
    }


    private FmtEmpty() {
    }
}

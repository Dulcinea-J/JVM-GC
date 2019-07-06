package com.atguigu.ref;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 *@ClassName WeakHashMapDemo
 *@Description TODO
 *@Author WeakHashMapDemo
 *@Date 2019/7/5 16:39
 *@Version 1.0
 **/

/**
 * WeakHashMap只要经过GC，就会被移除WeakHashMap中的所有数据
 * 一般用来做对高速缓存或者内存的开发
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        myHashMap();
        System.out.println("***********");
        myWeakHashMap();
    }

    private static void myWeakHashMap() {

        WeakHashMap<Integer,String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "HashMap";

        map.put(key,value);
        System.out.println(map);

        key = null;

        System.out.println(map);
//        System.out.println("===============");
//        map.put(key,value);
//        System.out.println(map);

        System.gc();
        System.out.println(map + "\t" + map.size());
    }

    private static void myHashMap() {

        HashMap<Integer,String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";

        map.put(key,value);
        System.out.println(map);

        key = null;

        System.out.println(map);
//        System.out.println("===============");
//        map.put(key,value);
//        System.out.println(map);

        System.gc();
        System.out.println(map + "\t" + map.size());
    }
}

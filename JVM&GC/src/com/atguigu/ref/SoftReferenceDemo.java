package com.atguigu.ref;

import java.lang.ref.SoftReference;

/**
 * @Author: Dulcinea
 * @Description: ${description}
 * @Date: 2019/7/5 15:21
 * @Version: 1.0
 */

/**
 * 软引用和弱引用的使用场景
 *
 * 假如有一个应用需要读取大量的本地图片
 *      如果每次读取图片都从硬盘读取则会严重影响性能
 *      如果一次性全部加载到内存中又可能造成内存溢出
 *
 *  此时可以使用软引用可以解决这个问题
 *
 *      设计思路是：用一个HashMap来保存图片的路径和相应图片对象关联的软引用之间的映射关系，
 *      在内存不足时，JVM会自动回收这些缓存图片对象占用的空间，从而有效的避免了OOM的问题
 *
 *      case: Map<String,SoftReference<BitMap>> imageCache = new HashMap<String,SoftReference<BitMap>>();
 */

public class SoftReferenceDemo {

    /**
     * 软引用内存够用的时候就保留，不够用的时候就回收！
     */
    public static void softRef_Memory_Enough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());


        o1 = null;
        // 手动GC
        System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());

    }

    /**
     * JVM配置，故意产生大对象并配置小的内存，让他内存不够用了导致OOM，看软引用的回收情况
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRef_Memory_NotEnough(){

        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());


        o1 = null;

        try {
            byte[] bytes = new byte[30*1024*1024];
        } catch (Throwable e){
            e.printStackTrace();
        } finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }

    }

    public static void main(String[] args) {

//        softRef_Memory_Enough();



        softRef_Memory_NotEnough();

    }
}

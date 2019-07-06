package com.atguigu.javaHeapSpace;

import java.util.Random;

/**
 *@ClassName JavaHeapSpaceDemo
 *@Description TODO
 *@Author JavaHeapSpaceDemo
 *@Date 2019/7/6 15:38
 *@Version 1.0
 **/
// 先设置堆内存最小和最大内存为20m，new一个80m的大对象把heapSpace撑爆，产生
    //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space错误
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
//        String str = "atguigu";
//
//        while (true)
//        {
//            str+= str + new Random().nextInt(111111) + new Random().nextInt(222222);
//        }

        byte[] bytes = new byte[80*1024*1024];
    }
}

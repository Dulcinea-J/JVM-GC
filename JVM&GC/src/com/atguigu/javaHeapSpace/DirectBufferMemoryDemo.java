package com.atguigu.javaHeapSpace;


import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

import static sun.misc.VM.maxDirectMemory;

/**
 *@ClassName DirectBufferMemoryDemo
 *@Description TODO
 *@Author DirectBufferMemoryDemo
 *@Date 2019/7/6 16:46
 *@Version 1.0
 **/

/**
 * JVM参数配置演示
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 * 故障现象：
 * Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
 *
 * 导致原因：
 *    写NIO程序经常使用ByteBuffer来读取或者写入数据，这是一种基于通道（Channel）与缓冲区（Buffer）的I/0方式
 *    他可以使用Native函数库直接分配堆外内存，然后通过一个存储在java堆里面的DirectByteBuffer对象作为这块内存的引用进行操作
 *    这样能在一些场景中显著提高性能，因为避免了在java堆和Native堆中来回复制数据
 *
 * ByteBuffer.allocate(capablity) 第一种方式是分配JVM堆内存；属于GC管辖范围，由于需要拷贝，所以速度相对较慢
 *
 * ByteBuffer.allocateDirect(capablity) 第二种方式是分配OS本地内存，不属于GC管辖范围，由于不需要内存拷贝所以速度相对较快
 *
 * 但如果不断分配本地内存，堆内存很少使用，那么JVM就不需要执行GC，DirectByteBuffer对象们就不会被回收
 * 这时候堆内存充足，但本地内存可能已经使用光了，再次尝试分配本地内存就会出现OutOfMemoryError,那程序就直接崩溃了
 *
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory:"+ (sun.misc.VM.maxDirectMemory()/(double)1024/1024) + "MB");

        try {
            TimeUnit.SECONDS.sleep(1 ); } catch (InterruptedException e) {e.printStackTrace(); }

        // -XX:MaxDirectMemorySize=5m 我们配置为5MB，但是实际使用6MB，故意使坏
        ByteBuffer bb = ByteBuffer.allocateDirect(6*1024*1024);
    }
}

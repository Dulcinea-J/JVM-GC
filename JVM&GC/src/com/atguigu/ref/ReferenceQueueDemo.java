package com.atguigu.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 *@ClassName ReferenceQueueDemo
 *@Description TODO
 *@Author ReferenceQueueDemo
 *@Date 2019/7/5 17:09
 *@Version 1.0
 **/

/**
 * 虚引用，被回收前需要被引用队列（ReferenceQueue）保存一下
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o1,referenceQueue);

        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());


        System.out.println("========");
        o1 = null;
        System.gc();
        Thread.sleep(500);

        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
    }
}

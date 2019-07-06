package com.atguigu.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 *@ClassName PhantomReferenceDemo
 *@Description TODO
 *@Author PhantomReferenceDemo
 *@Date 2019/7/5 17:25
 *@Version 1.0
 **/

/**
 * 虚引用需要java.lang.ref.PhantomReference类来实现
 *
 * 顾名思义，就是形同虚设，与其他几种引用都不同，虚引用并不会决定对象的生命周期。
 * 如果一个对象仅持有虚引用，那么他就和没有任何引用一样，在任何时候都可能被垃圾回收器回收。
 * 他不能单独使用也不能通过他访问对象，虚引用必须和引用队列（ReferenceQueue）联合使用
 *
 * 虚引用的主要作用是跟踪对象那个被垃圾回收的状态。仅仅是提供了一中确保对象被finalize以后，做某些事情的机制
 * PhantomReference的get方法总是返回null，因此无法访问对应的引用对象。其意义在于说明一个对象已经进入finalize阶段，可以被gc回收，用来实现比finalize机制更灵活的回收操作
 *
 * 换句话说，设置虚拟引用关联的唯一目的，就是在整个对象被收集器回收的时候收到一个系统通知或者后续添加进一步的处理
 * java技术允许使用finalize()方法在垃圾收集器将对象从内存中清除出去之前必要得清理工作
 *
 * java提供了4种引用类型，在垃圾回收的时候，都有各自的特点
 * ReferenceQueue是用来配合引用工作的，没有ReferenceQueue一样可以运行
 *
 * 创建引用的时候可以个指定关联的队列，当GC释放对象内存的时候，会将引用加入到引用队列，
 * 如果程序发现某个虚引用已经被加入到引用队列，那么就可以在所引用的对象的内存被回收之前采取必要的行动
 * 这相当于一种通知机制
 *
 * 当关联的引用的队列中有数据的时候，意味着引用指向的堆内存中的对象被回收。通过这种方式，JVM允许我们在对象被销毁后
 * 做一些我们自己想做的事情
 *
 *
 *
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1,referenceQueue);

        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("========");

        o1 = null;
        System.gc();
        Thread.sleep(500);

        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());


    }
}

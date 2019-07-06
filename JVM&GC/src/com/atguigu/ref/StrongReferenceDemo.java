package com.atguigu.ref;/**
 * @Author: Dulcinea
 * @Description: ${description}
 * @Date: 2019/7/5 14:57
 * @Version: 1.0
 */

/**
 * 强引用，软引用，弱引用，虚引用分别是什么？
 *
 * 当内存不足，JVM垃圾开始回收，对于强引用的对象，就算是出现了OOM也不会对该对象进行回收，死都不收
 *
 * 强引用是我们最常见的普通对象引用，只有还有强引用只想一个对象，就能表明对象还"活着"，垃圾回收器不会碰这种对象。
 * 在java中最常见的就是强引用，把一个对象赋给一个引用变量，这个引用变量就是一个强引用。
 * 当一个对象被强引用变量引用时，它处于可达状态，它是不可能被垃圾回收机制回收的，即使该对象永远都不会被用到JVM也不会回收，因此强引用是造成内存泄露的主要原因之一
 *
 * 对于一个普通的对象，如果没有其他的引用关系，只要超过了引用的作用域或者显式地将相应（强）引用赋值为null,一般认为就是可以被垃圾收集了的（当然具体回收时机还是要看垃圾收集策略）
 *
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object(); //这样定义的默认就是强引用
        Object obj2 = obj1;//obj2引用赋值
        System.out.println(obj2);
        obj1 = null;//置空
        System.gc();
        System.out.println(obj2);
        System.out.println(obj1);
    }
}

package com.atguigu.jvmGCRoot;

/**
 *@ClassName GCRootDemo
 *@Description TODO
 *@Author GCRootDemo
 *@Date 2019/7/4 22:18
 *@Version 1.0
 **/

import javax.xml.bind.SchemaOutputResolver;

/**
 * JVM垃圾回收的时候如何确定垃圾？是否知道什么是GC Roots?
 *  垃圾：简单一点的讲就是内存中已经不再被使用到的空间就是垃圾
 *
 *如何确定垃圾：
 *      1.引用计数法
 *      2.枚举根节点做可达性分析：
 * 所谓GC Roots或者说tracing GC的根集合就是一组必须活跃的引用
 * 基本意思就是通过一系列名为GC Roots的独显作为起始点，从这个被称为GC Roots的对象开始向下搜索，如果一个对象到
 * GC Roots没有任何引用链相连时，则说明该对象不可用。
 *
 *
 * 在java中，可作为GC Roots的对象有：
 *
 * 1.虚拟机栈（栈帧中的本地变量表）中引用的对象
 * 2.方法区中的类静态属性引用的对象；
 * 3.方法区中常量引用的对象；
 * 4.本地方法栈中JNI（即一般说的Native方法）中引用的对象
 *
 *
 */
public class GCRootDemo {

    private byte[] byteArray = new byte[100*1024*1024];

//    t2,方法区中的类静态属性引用的对象
//    private static GCRootDemo2 t2 = new GCRootDemo2();

//    t3,方法区中常量引用的对象；
//    private static final GCRootDemo3 t3 = new GCRootDemo3(8);

    public static void m1(){
        // 1.虚拟机栈（栈帧中的本地变量表）中引用的对象
        // t1方法栈中的引用对象
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }

    public static void main(String[] args) {
        m1();
    }
}

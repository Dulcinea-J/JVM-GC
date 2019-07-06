package com.atguigu.helloGC;

/**
 *@ClassName HelloGCDemo
 *@Description TODO
 *@Author HelloGCDemo
 *@Date 2019/7/4 22:54
 *@Version 1.0
 **/

/**
 * 如何查看一个正咋运行中的java程序，它的某个jvm参数是否开启？具体值是多少
 *
 * jps -l 查看进程号
 *
 * jinfo -flag PrintGCDetails 进程号  ->查看某个进程有没有开启GCDetails收集细节
 *
 * xx参数：
 *      1.Boolean类型
 *         1.1 -XX：+或-某个属性值
 *                  +表示开启
 *                  -表示关闭
 *      2.kv设值类型
 *          公式：-XX:属性key=属性value
 *          case:-XX:MetaSpaceSize=128m       //元空间大小
 *               -XX:MaxTenuringThreshold=15  //养老区最大存活次数
 *      3.jinfo举例，如何查看当前运行程序的配置
 *          jinfo -flag 参数名 进程号
 *          //E:\IdeaWorkSpace\JVM&GC>jinfo -flag MetaspaceSize 10952
 *             -XX:MetaspaceSize=536870912
 *          或者
 *          jinfo -flags 进程号  //查看某个进程的所有参数配置
 *          //E:\IdeaWorkSpace\JVM&GC>jinfo -flags 10952
 *              Attaching to process ID 10952, please wait...
 *              Debugger attached successfully.
 *              Client compiler detected.
 *              JVM version is 25.131-b11
 *              Non-default VM flags: -XX:InitialHeapSize=16777216 -XX:MaxHea
 *              pSize=268435456 -XX:MaxNewSize=89456640 -XX:MetaspaceSize=536
 *              870912 -XX:MinHeapDeltaBytes=131072 -XX:NewSize=5570560 -XX:O
 *              ldSize=11206656 -XX:+UseFastUnorderedTimeStamps -XX:-UseLarge
 *              PagesIndividualAllocation
 *              Command line:  -Dvisualvm.id=309358573061900 -XX:MetaspaceSiz
 *              e=512m -javaagent:E:\IntelliJ IDEA 2018.3.6\lib\idea_rt.jar=5
 *              7358:E:\IntelliJ IDEA 2018.3.6\bin -Dfile.encoding=UTF-8
 *
 * 运行中调整参数
 *          from .初始默认值..to 自我期望值
 *
 *  坑题：两个经典参数：-Xms 和-Xmx 是属于-X参数还是属于-XX参数
 *          -Xms->等价于-XX：InitialHeapSize
 *          -Xmx->等价于-XX：MaxHeapSize
 *
 *          -Xms默认等于机器内存的1/64
 *          -Xmx默认等于机器内存的1/4
 *
 * 你说你做过JVM调优和参数配置，请问如何盘点查看JVM系统的默认值
 *   查看参数盘点家底
 *      主要查看初始默认设置
 *      java -XX:+PrintFlagsInitial
 *      主要查看修改更新
 *      java -XX:+PrintFlagsFinal -version
 *      // bool UseLargePagesInMetaspace                  = false
 *                              {product}
 *        bool UseLargePagesIndividualAllocation        := false
 *                              {pd product}
 *                                                      := ->表示自己配置或者jvm修改的值
 *      PrintFlagsFinal举例，运行java命令的同时打印出参数
 *      case: java -XX:+PrintFlagsFinal -Xms=512m 类名
 *
 *      最终要的作用是查看GC
 *      java -XX:+PrintCommandLineFlags -version
 *
 *   //E:\IdeaWorkSpace\JVM&GC>java -XX:+PrintCommandLineFlags -version
 *      -XX:InitialHeapSize=16777216 -XX:MaxHeapSize=268435456 -XX:+PrintCommandLineFlags -XX:-UseLargePagesIndividualAllocation
 *      java version "1.8.0_131"
 *      Java(TM) SE Runtime Environment (build 1.8.0_131-b11)
 *      Java HotSpot(TM) Client VM (build 25.131-b11, mixed mode)
 *
 *
 * 你平时工作用过的JVM常用基本配置参数有哪些？
 *    常用参数：-Xss // 设置单个线程栈的大小，一般默认为512K~1024K
 *              等价于-XX:ThreadStackSize
 *
 *             -XX:+PrintGCDetails //打印GC详情
 *
 *             -XX:SurvivorRatio //设置新生代中Eden和s0/s1空间的比例
 *             默认-XX:SurvivorRatio=8,Eden:s0:s1=8:1:1
 *             假如
 *             -XX：SurvivorRation=4,Eden:s0:s1=4:1:1
 *             Survivor值就是设置eden区的比例占多少，s0,s1相同
 *
 *             配置年轻代与老年代在堆结构的占比
 *             默认
 *             -XX:NewRatio=2 新生代占比1，老年代2，年轻代占整个堆的1/3
 *             假如
 *             -XX:NewRatio=4 新生代占1，老年代占4，年轻代占整个堆的1/5
 *
 *             -XX:MaxTenuringThreshold //设置垃圾最大年龄，java8中规定只能是0-15之间的数字
 */
public class HelloGCDemo {
    public static void main(String[] args) throws InterruptedException {

        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("TOTAL_MEMORY(-Xms)=" + totalMemory + "(字节)" + totalMemory/(double)(1024*1024) + "MB");
        System.out.println("TOTAL_MEMORY(-Xmx)=" + maxMemory + "(字节)" + maxMemory/(double)(1024*1024) + "MB");

        System.out.println("*****HelloGC");

        Thread.sleep(Integer.MAX_VALUE);
    }
}

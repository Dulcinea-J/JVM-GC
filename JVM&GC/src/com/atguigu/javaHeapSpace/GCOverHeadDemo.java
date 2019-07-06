package com.atguigu.javaHeapSpace;
import java.util.ArrayList;
import java.util.List;

/**
 *@ClassName GCOverHeadDemo
 *@Description TODO
 *@Author GCOverHeadDemo
 *@Date 2019/7/6 15:56
 *@Version 1.0
 **/

/**
 * JVM参数配置演示
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 *GC回收时间过长时会抛出OutOfMemoryError。过长的定义是，超过98%的时间用来做GC并且回收不到2%的堆内存
 * 连续多次GC都只回收了不到2%的极端情况下才会抛出。假如不抛出GC overhead limit 错误会发生什么情况呢？
 * 那就是GC清理的这么点内存很快会被再次填满，迫使GC再次执行，这样就形成恶性循环
 * CPU使用率一直是100%，而GC却没有任何成果
 */

public class GCOverHeadDemo {
    public static void main(String[] args)
    {
        int i = 0;
        List<String> list = new ArrayList<>();

        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Throwable e) {
            System.out.println("*******i:"+i);
            e.printStackTrace();
            throw e;
        }
    }
}

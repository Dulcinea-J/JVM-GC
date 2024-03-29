package com.atguigu.javaHeapSpace;

/**
 *@ClassName UnableCreateNewThreadDemo
 *@Description TODO
 *@Author UnableCreateNewThreadDemo
 *@Date 2019/7/6 17:28
 *@Version 1.0
 **/

import java.util.concurrent.TimeUnit;

/**
 * 高并发请求服务器时，经常出现如下异常：java.lang.OutOfMemoryError: unable to create new native thread
 * 准确的讲该native thread异常与对应的平台有关
 *
 * 导致原因：
 * 1.你的应用创建了太多线程了，一个应用程序创建多个线程，超过系统承载极限
 * 2.你的服务器并不允许你的应用程序创建怎么多想爱你陈，Linux系统默认允许创建单个进程可以创建的线程数是1024个(root用户无限制，其他用户默认是1024，但是系统本身也有线程在跑，所以实际值没这么高)
 * ulimit -u //查看当前用户的默认线程数
 *
 * Linux中用户线程上限调整
 * vim /etc/security/limits.d/90-nproc.conf
 *
 *
 * 解决办法：
 * 1.想办法降低你应用程序创建线程的数量，分析应用是否真的需要创建这么多线程，如果不是，改代码将线程数降到最低
 * 2.对于有的应用，确实需要创建很多线程，远超过Linux系统的默认1024个线程的限制，可以通过修改Linux服务器配置，扩大Linux默认限制
 *
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for (int i = 0;  ; i++) {
            System.out.println("****** i = "+ i);
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE ); } catch (InterruptedException e) {e.printStackTrace(); }
            }, "" + i).start();

        }
    }
}

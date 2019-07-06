package com.atguigu.ref;

import java.lang.ref.WeakReference;

/**
 * @Author: Dulcinea
 * @Description: ${description}
 * @Date: 2019/7/5 16:00
 * @Version: 1.0
 */

/**
 * 弱引用不管内存够不够，只要发生GC，弱引用就会被回收
 *
 * D:\develop\Java\jdk1.8.0_131\bin\java.exe -Dvisualvm.id=331650179526800 "-javaagent:E:\IntelliJ IDEA 2018.3.6\lib\idea_rt.jar=61407:E:\IntelliJ IDEA 2018.3.6\bin" -Dfile.encoding=UTF-8 -classpath D:\develop\Java\jdk1.8.0_131\jre\lib\charsets.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\deploy.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\access-bridge-32.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\cldrdata.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\dnsns.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\jaccess.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\jfxrt.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\localedata.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\nashorn.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\sunec.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\sunjce_provider.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\sunmscapi.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\sunpkcs11.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\zipfs.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\javaws.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\jce.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\jfr.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\jfxswt.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\jsse.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\management-agent.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\plugin.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\resources.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\rt.jar;E:\IdeaWorkSpace\JVM&GC\out\production\JVM&GC com.atguigu.ref.WeakReferenceDemo
 * java.lang.Object@140e19d
 * java.lang.Object@140e19d
 * ===========
 * null
 * null
 *
 * Process finished with exit code 0
 */

public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1 = null;
        // 手动GC
        System.gc();
        System.out.println("===========");

        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}

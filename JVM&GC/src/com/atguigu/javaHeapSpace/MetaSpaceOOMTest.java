package com.atguigu.javaHeapSpace;/**
 * @Author: Dulcinea
 * @Description: ${description}
 * @Date: 2019/7/6 18:00
 * @Version: 1.0
 */

/**
 *@ClassName MetaSpaceOOMTest
 *@Description TODO
 *@Author MetaSpaceOOMTest
 *@Date 2019/7/6 18:00
 *@Version 1.0
 **/


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * JVM参数
 * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 *
 * 模拟MetaSpace空间溢出，我们不断生成类往元空间灌，类暂居的空间总是会超过MetaSpace指定的空间大小的
 */
public class MetaSpaceOOMTest {

    static class OOMTest{}

    public static void main(String[] args) {
        int i = 0;//模拟计数多少次以后发生异常

        try {
            while (true)
            {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor(){

                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable{
                        return methodProxy.invokeSuper(o,args);
                    }
                });
                enhancer.create();
            }

        } catch (Throwable e){
            System.out.println("******多少次后发生了异常： "+ i);
            e.printStackTrace();
        }

    }
}

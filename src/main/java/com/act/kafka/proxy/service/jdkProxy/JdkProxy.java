package com.act.kafka.proxy.service.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName JdkProxy
 * @Description TODO
 * @Autor Administrator
 * @Date 2019/4/9 11:05
 * @Version 1.0
 **/
public class JdkProxy implements InvocationHandler {
    private Object target;

    /**
     * 绑定业务对象并返回一个代理类
     */
    public Object bind(Object target) {
        this.target = target;  //接收业务实现类对象参数

        //通过反射机制，创建一个代理类对象实例并返回。用户进行方法调用时使用
        //创建代理对象时，需要传递该业务类的类加载器（用来获取业务实现类的元数据，在包装方法是调用真正的业务方法）、接口、handler实现类
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("预处理操作jdk");
        // 调用真正的业务方法
        result = method.invoke(target, args);
        System.out.println("调用后处理jdk");
        return result;
    }
}

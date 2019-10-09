package com.mingrisoft.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Agency implements InvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理人员卖房子前");

        Object object=method.invoke(proxy,args);

        System.out.println("代理人员卖房子后");//

        return object;
    }
}

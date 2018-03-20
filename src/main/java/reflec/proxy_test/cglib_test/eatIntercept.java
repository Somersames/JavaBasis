package reflec.proxy_test.cglib_test;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class eatIntercept implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // Object 代表的是需要被代理的类，
        // objects 一些参数数组
        //
        System.out.println("a");
        return methodProxy.invokeSuper(o,objects);
        //用invoke的话会出现无限递归，导致栈溢出；
    }
}

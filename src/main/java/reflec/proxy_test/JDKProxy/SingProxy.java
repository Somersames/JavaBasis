package reflec.proxy_test.JDKProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SingProxy implements InvocationHandler {
    private Object target;
    public SingProxy(Object target) {
        this.target=target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("a");
       return method.invoke(target,args);
    }


}

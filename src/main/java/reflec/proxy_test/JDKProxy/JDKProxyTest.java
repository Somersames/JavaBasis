package reflec.proxy_test.JDKProxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class JDKProxyTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> proxyclass= Proxy.getProxyClass(JDKProxyTest.class.getClassLoader(),Singer.class);/**/
        final Constructor<?> constructor =proxyclass.getConstructor(InvocationHandler.class);
//        Singer singer =new SingerImpl();
        final InvocationHandler invocationHandler =new SingProxy(constructor);
        Singer sing = (Singer) constructor.newInstance(invocationHandler);
        sing.sing();
    }
}

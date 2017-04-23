package reflec.aop.cglibtest;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by szh on 2017/4/21.
 */
public class CGLIBProxy implements MethodInterceptor {
    private Object target;
    private ProxyUtil proxyUtil ;
    public CGLIBProxy(Object target) throws ClassNotFoundException {
        this.target = target;
        proxyUtil =new ProxyUtil();
    }

    public <T> T getProxy(){
//        Enhancer enhancer =new Enhancer();
//        enhancer.setSuperclass(this.target.getClass());
//        enhancer.setCallback(this);
//        return (T) enhancer.create();
        return (T) new Enhancer().create(this.target.getClass(),this);
    }
    public <T> T getProxy(Class<?> clazz){
//        Enhancer enhancer =new Enhancer();
//        enhancer.setSuperclass(this.target.getClass());
//        enhancer.setCallback(this);
//        return (T) enhancer.create();
        return (T) new Enhancer().create(this.target.getClass(),this);
    }
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
//        System.out.println("---------before");
//        Object result =proxy.invokeSuper(obj, args);
////        System.out.println(obj.toString());
////        System.out.println(args.toString());
//        System.out.println("------------after");
//        return result;

        ProxyEntity proxyEntity =new ProxyEntity(proxy,this.target.getClass(),obj,method,args);
        return proxyUtil.generateEntity(proxyEntity);

    }
    //反射调用
    public void  before(){

    }
    //反射调用
    public void after(){

    }
}

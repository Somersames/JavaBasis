package reflec.aop.cglibtest;

import org.junit.Test;
import reflec.aop.After;
import reflec.aop.Before;
import reflec.aop.MyAspect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by szh on 2017/4/22.
 */
public class Reflect {
    Map<String,String> map ;   //存入的是方法名以及其注解
    Map<String,String> clazzMap;
    public Reflect() throws ClassNotFoundException {
        map=new HashMap<>();
        clazzMap =new HashMap<>();
        getAnnotationClass();
    }

    public Map<String, String> getMap() {
        return map;
    }
    @Test
    public  void getAnnotationClass() throws ClassNotFoundException {
        String clazzName="reflec.aop.cglibtest.Player";
        Class<?>  clazz =Class.forName(clazzName,false,Thread.currentThread().getContextClassLoader());
        if (clazz.isAnnotationPresent(MyAspect.class)) {
            Method[] methods =clazz.getDeclaredMethods();
            for (Method method :methods) {
                if (method.isAnnotationPresent(Before.class)) {
                   Before before =method.getAnnotation(Before.class); // 获取注解 ,在这里如果是clazz.getAnnotation()获取的是类注解
                    String beforeValue=before.value();
                    map.put(method.getName()+ "-"+clazzName+"-"+"before",beforeValue.substring(0,beforeValue.length()-2)); // 存入的是方法名和注解名
                }
                if (method.isAnnotationPresent(After.class)) {
                    After after =method.getAnnotation(After.class); // 获取注解 ,在这里如果是clazz.getAnnotation()获取的是类注解
                    String afterValue=after.value();
                    map.put(method.getName()+ "-"+clazzName+"-"+"after",afterValue.substring(0,afterValue.length()-2));
                }
            }
        }
    }
}

package reflec.aop.cglibtest;


import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by szh on 2017/4/22.
 */
public class ProxyUtil {
    /*
    加载出所有的before注解
    生成invokesuper方法
    加载所有的after方法
     */
    Reflect reflect;

    public ProxyUtil() throws ClassNotFoundException {
        reflect = new Reflect();
    }

    public void getMethod(String name) {
        Map<String, String> map = new HashMap<>();

    }

    //该方法负责代理
    public Object generateEntity(ProxyEntity proxyEntity) throws Throwable {
        String proxyMethodValue = proxyEntity.getMethod().toString().substring(proxyEntity.getMethod().toString().lastIndexOf(" ") + 1, proxyEntity.getMethod().toString().indexOf("("));
        Map<String, String> methodMap = reflect.getMap();
        for (Map.Entry<String, String> map : methodMap.entrySet()) {
            if (map.getValue().equals(proxyMethodValue)) {
                String[] str = mapKeyDivision(map.getKey());
                if (str[2].equals("before")) {
                    /* 加载该类 */
                    Class<?> clazz = Class.forName(str[1], false, Thread.currentThread().getContextClassLoader());
                    Method method = clazz.getDeclaredMethod(str[0]);
                    /* 这一步需要原始的类 */
                    method.invoke(clazz.newInstance(), null);
                }
            }
        }
        return doAfter(proxyEntity,methodMap);
    }
    private Object  doAfter(ProxyEntity proxyEntity,Map<String,String> map) throws Throwable {
        Object object = proxyEntity.getMethodProxy().invokeSuper(proxyEntity.getObject(), proxyEntity.getArgs());  // 调用方法
        String proxyMethodValue = proxyEntity.getMethod().toString().substring(proxyEntity.getMethod().toString().lastIndexOf(" ") + 1, proxyEntity.getMethod().toString().indexOf("("));
        for(Map.Entry<String,String> aMap:map.entrySet()){
            if (aMap.getValue().equals(proxyMethodValue)){
                String[] str =mapKeyDivision(aMap.getKey());
                    if(str[2].equals("after")){
                        Class<?> clazz = Class.forName(str[1], false, Thread.currentThread().getContextClassLoader()); // 加载该类
                        Method method = clazz.getDeclaredMethod(str[0]);
                        method.invoke(clazz.newInstance(), null); // 这一步需要原始的类
                    }
                }
            }
        return object;
    }

    private String[] mapKeyDivision(String value) {
        String[] str = new String[10];
        str[0] = value.substring(0, value.indexOf("-"));
        str[1] = value.substring(value.indexOf("-") + 1, value.lastIndexOf("-"));
        str[2]=value.substring(value.lastIndexOf("-")+1,value.length());
        return str;
        /*
             beforeSing-reflec.aop.cglibtest.Player
             beforeSing
             reflec.aop.cglibtest.Player
         */
    }
    @Test
    public void mapTest(){
        String value="beforeSing-reflec.aop.cglibtest.Player-before";
        String[] str = new String[10];
        str[0] = value.substring(0, value.indexOf("-"));
        str[1] = value.substring(value.indexOf("-") + 1, value.lastIndexOf("-"));
        str[2]=value.substring(value.lastIndexOf("-")+1,value.length());
        System.out.println(str[0]);
        System.out.println(str[1]);
        System.out.println(str[2]);
        /*
             reflec.aop.cglibtest.Music.sing------------------beforeValue
              beforeSing
             reflec.aop.cglibtest.Player
             before
         */
    }
}

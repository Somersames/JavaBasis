package reflec.anno.test;

import reflec.anno.Entity;
import reflec.anno.Resources;
import reflec.anno.Weather;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by szh on 2017/4/19.
 */
public class Weather_reflec {
    List<Object> objectList ;

    public Weather_reflec() {
        objectList= new ArrayList<Object>();
    }

    public  void get_ref(Object object) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//        Class<?> clazzz=Class.forName("reflec.anno.Weather",true,Thread.currentThread().getContextClassLoader());
//        Object obj =clazzz.newInstance();
        Class<?> clazz =object.getClass();
        if(clazz.isAnnotationPresent(Entity.class)){
            Field[] fields =clazz.getDeclaredFields();
            for(Field field :fields){
                if(field.isAnnotationPresent(Resources.class)){
                    System.out.println(field.getName());
                    System.out.println(field.getType().getName()); // 获取其类名，可以从Set里面获取值获取其类名然后动态加载
                    Class<?> rainClass =Class.forName(field.getType().getName(),false,Thread.currentThread().getContextClassLoader());
//                    Object object= rainClass.newInstance();
//                    Object value =field.get(rainClass);
                    field.setAccessible(true);
                    field.set(object,rainClass.newInstance());
//                    Method method1 =clazz.getDeclaredMethod("setRain",rainClass);
//                    method1.invoke(clazz.newInstance(),object);
//                    Method method =clazz.getDeclaredMethod("weather_rain");
//                    method.invoke(object);
                    objectList.add(object);
//                    Class<?> fieldClass =field.getType();
//                    Constructor constructor=fieldClass.getConstructor(); // 在这里可以向其传值
                }
            }
//            Method[] methods =clazz.getDeclaredMethods();
//            for(Method method :methods){
//                method.invoke(clazz.newInstance());
//            }


        }
    }
    public static void getRef() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz=Class.forName("reflec.anno.Weather",false,Thread.currentThread().getContextClassLoader());  //加载类
        Object object =clazz.newInstance();     //实例化

    }
    public List<Object> returnList(){
        return objectList;
    }
//    public static  void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        Weather_reflec weather_reflec =new Weather_reflec();
//        Weather weather =new Weather();
//        weather_reflec.get_ref(weather);
//        Weather weather1=(Weather) weather_reflec.objectList.get(0);
//        weather1.weather_rain();
////       weather.weather_rain();
//    }
}

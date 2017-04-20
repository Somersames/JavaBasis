package reflec.anno.test;

import reflec.anno.Weather;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by szh on 2017/4/20.
 */
public class WeatherPrediction {
    public static void main(String args[]) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

       WeatherPrediction weatherPrediction =new WeatherPrediction();
        Weather weather =(Weather)weatherPrediction.springDo();
        weather.weather_rain();
    }
    /*
    模拟Spring启动是的动作
     */
    public Object springDo() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Weather_reflec weather_reflec =new Weather_reflec(); // 这个类是在Spring启动的时候就需要加载的
        Weather weather =new Weather();                     //启动的时候扫描类注解
        weather_reflec.get_ref(weather);                    // 容器来new操作
        Weather weather1=(Weather) weather_reflec.returnList().get(0);
        return weather1;
    }
}

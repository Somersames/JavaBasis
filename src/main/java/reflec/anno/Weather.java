package reflec.anno;


import reflec.anno.test.Weather_reflec;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by szh on 2017/4/19.
 */
@Entity
public class Weather {
    @Resources
    Rain rain;

    public void weather_rain() {
        rain.rain();
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

}

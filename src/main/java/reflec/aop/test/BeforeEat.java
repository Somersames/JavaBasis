package reflec.aop.test;

import reflec.aop.After;
import reflec.aop.Before;
import reflec.aop.MyAspect;

/**
 * Created by szh on 2017/4/21.
 */
@MyAspect
public class BeforeEat {
    @Before("reflec.aop.test.Eat.startEat()")
    public void beforeStart() {
        System.out.println("开始吃饭前的准备");
    }

    @After("reflec.aop.test.Eat.startEat()")
    public void afterEat() {
        System.out.println("吃饭完毕以后");
    }
}

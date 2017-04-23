package reflec.aop.cglibtest;

import reflec.aop.After;
import reflec.aop.Before;
import reflec.aop.MyAspect;

/**
 * Created by szh on 2017/4/22.
 */
@MyAspect
public class Player {
    @Before("reflec.aop.cglibtest.Music.sing()")
    public void beforeSing() {
        System.out.println("开始唱歌前的准备");
    }

    @After("reflec.aop.cglibtest.Music.sing()")
    public void afterSing() {
        System.out.println("唱完之后开始评分");
    }
}

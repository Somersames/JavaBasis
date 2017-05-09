package Thread.threaddemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by szh on 2017/3/31.
 */
public class ThreadDoSomething {
    static int i=0;
    public String dosomething() {
        try {
            Thread.sleep(1000);
            System.out.println("测试线程" + i);
            i++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "测试线程" + i;
    }
}

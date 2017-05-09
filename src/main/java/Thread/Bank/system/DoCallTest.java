package Thread.Bank.system;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by szh on 2017/5/9.
 */
public class DoCallTest {
    static Executor executor = Executors.newFixedThreadPool(10);
    static int num=0;
    public static void main(String args[]) throws InterruptedException {
        for(int i=0;i<19;i++) {
            Runnable runnable = new DoCall();
           executor.execute(runnable);
        }
        Thread.sleep(1000);
    }
}

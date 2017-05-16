package Thread.myfuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by szh on 2017/5/16.
 */
public class FutureTest {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    private void futest() throws InterruptedException {
        Callable<Integer> callable =new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int num =0;
                while (!Thread.currentThread().isInterrupted()){
                    System.out.println(num);
                    num++;
                }
                return num;
            }
        };
        Future<Integer> future =executorService.submit(callable);
        Thread.currentThread().sleep(2);
        future.cancel(true);
        executorService.shutdown();
    }
    public static void main(String args[]) throws InterruptedException {
        new FutureTest().futest();
    }
}

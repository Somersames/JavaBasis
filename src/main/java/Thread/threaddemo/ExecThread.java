package Thread.threaddemo;

import java.util.concurrent.*;

/**
 * Created by szh on 2017/3/31.
 * Executor框架测试
 */
public class ExecThread {
    public void doprocess()
    {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++){
        executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
               ThreadDoSomething th =new ThreadDoSomething();
                return th.dosomething();
            }
        });
        }
    }
    public void doFuture() throws ExecutionException, InterruptedException {
        ExecutorService executorService =Executors.newFixedThreadPool(10);
        Callable<String> task =new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName();
            }
        };
        for(int i=0;i<15;i++) {
            Future<String> future = executorService.submit(task);
            Thread.sleep(i*1000);
            System.out.println(future.get());
            System.out.println(future.isDone());
        }
    }
    public static void main(String args[]) throws ExecutionException, InterruptedException {
        ExecThread execThread =new ExecThread();
//        execThread.doprocess();
        execThread.doFuture();
    }
}

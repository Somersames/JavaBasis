package Thread.myfuture;

import org.omg.CORBA.INTERNAL;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by szh on 2017/5/16.
 */
public class FutureBasic extends  Thread{
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    static Future<Integer> future =null;
    private void doBasic() throws InterruptedException {

        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
//                Thread.currentThread().sleep(1000);
                int temp = 0;
                for (int i = 0; i < 100; i++) {
                    System.out.println(i + "-----------------------i");
                    if (i > 50) {

                            System.out.println(Thread.currentThread().isInterrupted()+"---------是否已中断");
//                            Thread.currentThread().sleep(500); // 在这里Thread.sleep会重新设置终端状态
                            System.out.println(Thread.currentThread().getName());

                    }
                }
                Thread.currentThread().sleep(1000);
                return temp;
            }
        };
        future = executorService.submit(callable);
//        Thread.currentThread().sleep(1500);
        System.out.println(Thread.currentThread().isInterrupted()+"-------------------sleep方法后面的");
        future.cancel(true); //当设置取消任务的时候只是
        System.out.println(Thread.currentThread().isInterrupted()+"-------------------cancle方法后面的");
    }
    public static void main(String args[]) throws InterruptedException {
        FutureBasic futureBasic =new FutureBasic();
        futureBasic.doBasic();
        Thread.currentThread().sleep(3000);
//        future.cancel(true);
    }

}

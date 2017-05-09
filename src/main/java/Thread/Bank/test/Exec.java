package Thread.Bank.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by szh on 2017/5/9.
 */
public class Exec {
    Executor executor = Executors.newFixedThreadPool(10);
    private static List<Integer> list =new ArrayList<>();
    int  num=0;
    public  synchronized void dotest(){
        Runnable task =new Runnable() {
            @Override
            public void run() {
                    Exec.list.add(num);
                    System.out.println(Thread.currentThread().getName());
//                try {
//                    Thread.currentThread().sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        };
        num++;
        executor.execute(task); //在这里需要循环10次提交才行

    }
    private void real(){

    }

    public static void main(String args[]) throws InterruptedException {
        /*
        这样调用只会产生10个线程池，应该在executor这里循环调用产生10个任务提交
         for(int i=0 ;i<15;i++){
            new Exec().dotest();
        }
         */
        Exec exec =new Exec(); // 在这里之所以用synchronize不行是因为并不是多个线程在提交，而是在在方法内部有10个runnable并且在提交，这样就锁不住了
        for(int i=0 ;i<18;i++){
            exec.dotest();
        }

        Thread.sleep(1000);
        for(Integer i :Exec.list){
            System.out.println(i);
        }
    }
}
